import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Objects;

public class IPSampling {

    protected final Database dataset;
    ArrayList<BigDecimal> CumulativeObjectsweights = new ArrayList<>();
    BigDecimal ObjectstotalWeight = BigDecimal.ZERO;

    public IPSampling(Database dataset) {
        this.dataset = dataset;
        ProcessObjectsWeights();
    }


    public void ProcessObjectsWeights() {
        /*
         * Method that process the weight of each object of the database through to the NIP formula
         *
         * */

        for (int g = 0; g < this.dataset.getObjectNumber(); g++) {
            BigDecimal objectWeight = BigDecimal.ONE;

            for (int m = 0; m < this.dataset.getColumnsNumberNumerical(); m++) {
                int valIndex = this.dataset.getObject(g).get(m);

                if (valIndex == -1) {
                    System.out.println("ERROR: We should not be here");
                }
                BigDecimal valIndexBD = BigDecimal.valueOf(valIndex);
                BigDecimal distinctValuesSizeBD = BigDecimal.valueOf(dataset.getDistinctValues().get(m).size());

                //NIP(g)
                BigDecimal component = valIndexBD
                        .add(distinctValuesSizeBD.subtract(valIndexBD))
                        .add(valIndexBD.multiply(distinctValuesSizeBD.subtract(valIndexBD)))
                        .add(BigDecimal.ONE);
                objectWeight = objectWeight.multiply(component);
            }

            if (CumulativeObjectsweights.isEmpty()) {
                CumulativeObjectsweights.add(objectWeight);
            } else {
                BigDecimal lastCumulativeWeight = CumulativeObjectsweights.get(CumulativeObjectsweights.size() - 1);
                CumulativeObjectsweights.add(lastCumulativeWeight.add(objectWeight));
            }

            ObjectstotalWeight = ObjectstotalWeight.add(objectWeight);

        }
        if(Objects.equals(CumulativeObjectsweights.get(0), CumulativeObjectsweights.get(CumulativeObjectsweights.size() - 1))){
            System.out.println("WARINING: The CumulativeObjectsweights contains the same weights");
        }
    }

    public IP drawIP() {

        /* Method that draws an interval pattern proportional to fréquency
         *
         * Return: sampled IP
         */
        ArrayList<Integer> resultingIP = new ArrayList<>();

        int drawnObject1 = DrawObject();           // draw object proportionally to w(g)
        for (int m = 0; m < dataset.getColumnsNumberNumerical(); m++) {
            int value = dataset.getObject(drawnObject1).get(m);
            int indexlb = (int) (Math.random() * value);            //WARNING: works only for normalized data
            int indexub = (value + (int) (Math.random() * ((dataset.getDistinctValues().get(m).size()) - value))); //WARNING: works only for normalized data
            resultingIP.add(dataset.distinctValues.get(m).get(indexlb));
            resultingIP.add(dataset.distinctValues.get(m).get(indexub));
        }
        return new IP(resultingIP, dataset, false);
    }


    public IP drawClosedIP() {

        /* Method that draws a CLOSED interval pattern proportional to fréquency
         *
         * Return: sampled IP
         */
        ArrayList<Integer> resultingIP = new ArrayList<>();

        int drawnObject1 = DrawObject();           // draw object proportionally to w(g)
        for (int m = 0; m < dataset.getColumnsNumberNumerical(); m++) {
            int value = dataset.getObject(drawnObject1).get(m);
            int indexlb = (int) (Math.random() * value);            //WARNING: works only for normalized data
            int indexub = (value + (int) (Math.random() * ((dataset.getDistinctValues().get(m).size()) - value))); //WARNING: works only for normalized data
            resultingIP.add(dataset.distinctValues.get(m).get(indexlb));
            resultingIP.add(dataset.distinctValues.get(m).get(indexub));
        }
        IP myip= new IP(resultingIP, dataset, false);




        return new IP(myip.closureOperator(), dataset, true);
    }






    public int DrawObject() {
        /*
         * Method that draws an object index proportionally to it weight
         * Return: drawn object
         * */

        BigDecimal drawnobject = BigDecimal.valueOf(Math.random()).multiply(ObjectstotalWeight);
        return FindIndex(0, dataset.getObjectNumber(), drawnobject);
    }

    private int FindIndex(int left, int right, BigDecimal drawnobject) {
        /*
         * Method that searching a cell in CumulativeObjectsweights that has a cumulative weight smaller than  drawnobject
         *
         * Return: The cell corresponding to the object respecting the condition
         * */

        int middle = (left + right) / 2;

        // Using BigDecimal comparison methods
        if (CumulativeObjectsweights.get(middle).compareTo(drawnobject) >= 0) {
            if (middle == 0 || CumulativeObjectsweights.get(middle - 1).compareTo(drawnobject) < 0) {
                return middle;
            } else {
                return FindIndex(left, middle, drawnobject);
            }
        } else {
            return FindIndex(middle + 1, right, drawnobject);
        }
    }


}









