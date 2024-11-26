import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TotalyRandomIPSampling {


    protected final Database dataset;

    public TotalyRandomIPSampling(Database dataset) {
        this.dataset = dataset;
    }

    public IP drawIP() {
        /*
         * Method that draws a random IP without ensuring to have a non empty coverage set
         * */

        ArrayList<Integer> ip = new ArrayList<>();


        for(int m=0; m < dataset.columnsNumberNumerical; m++){

            int IndexBound1 = (int) (Math.random() * (dataset.distinctValues.get(m).size() ));
            int IndexBound2 = (int) (Math.random() * (dataset.distinctValues.get(m).size()));

            ip.add(Math.min(dataset.distinctValues.get(m).get(IndexBound1), dataset.distinctValues.get(m).get(IndexBound2)));
            ip.add(Math.max(dataset.distinctValues.get(m).get(IndexBound1), dataset.distinctValues.get(m).get(IndexBound2)));

        }
        return new IP(ip, dataset, false);
    }


}
