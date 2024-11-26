import java.util.ArrayList;
import java.util.List;

public class IP {

    protected ArrayList<Integer> IP;
    protected ArrayList<Integer> coverage;
    protected long hash;
    protected final Database dataset;

    protected double volume;
    protected boolean closed;


    public IP(ArrayList<Integer> IP, Database dataset, boolean closed) {
        this.IP = IP;
        this.dataset = dataset;
        this.closed = closed;
        this.coverage = processCoverage(dataset);
        this.volume = processVolume();
    }


    public ArrayList<Integer> processCoverage(Database db) {
        /*
         * Method that process the coverage of the current IP
         * Return: Arraylist where each cell contains a covered object
         * */

        ArrayList<Integer> cov = new ArrayList<>();
        long val = 0;
        for (int g = 0; g < db.getObjectNumber(); g++) {
            boolean included = true;
            for (int m = 0; m < db.getColumnsNumberNumerical(); m++) {
                if (this.IP.get(m * 2) > db.getObject(g).get(m) || this.IP.get((m * 2) + 1) < db.getObject(g).get(m)) {
                    included = false;
                    break;
                }
            }
            if (included) {
                cov.add(g);
                val = (val + (long) (g + 1) * 13 * (g + 7));
            }
        }
        this.hash = val;
        return cov;
    }


    public double processVolume() {
        /*
         * Method that process the volume of the current IP
         * Retrun: double value
         *
         *  */
        ArrayList<Integer>closedIP;
        if(this.closed){
            //System.out.println("we are here");
            closedIP= this.getIP();
        }else{
            closedIP= this.closureOperator();

        }

        double volume = 1;
        for (int i = 0; i < closedIP.size(); i += 2) {
            double diff = closedIP.get(i + 1) - closedIP.get(i);

            if (diff > 0) {

                volume *= diff;
            }
        }
        //System.out.println("Volume:"+ volume);
        return volume;
    }

    public ArrayList<Integer> closureOperator(){
        ArrayList<Integer> CIP= new ArrayList<>();
        ArrayList<Integer> coverage= processCoverage(this.dataset);
        for(int m =0; m < dataset.columnsNumberNumerical; m++ ){
            int lb = Integer.MAX_VALUE;
            int ub = Integer.MIN_VALUE;
            for(int g: coverage){
                if(dataset.getObject(g).get(m)< lb){
                    lb =dataset.getObject(g).get(m);
                }
                if(dataset.getObject(g).get(m)> ub){
                    ub =dataset.getObject(g).get(m);
                }
            }
            if(lb != Integer.MAX_VALUE && ub != Integer.MIN_VALUE){
                CIP.add(lb);
                CIP.add(ub);
            }else{
                //System.out.println("ERROR: We should not be here");
            }
        }
        return CIP;
    }


    public void showIP() {
        System.out.println("------IP DESCRIPTION: ");
        for (int i = 0; i < IP.size() - 1; i += 2) {
            System.out.print("[" + IP.get(i) + ", " + IP.get(i + 1) + "]");

        }
        System.out.println("\n");
    }

    public void showCoverage() {
        System.out.println("-------IP COVERAGE");
        System.out.print("{");
        for (Integer g : this.coverage) {
            System.out.print(g);
            System.out.print(",");
        }
        System.out.print("}");
        System.out.println("\n");
    }


    public ArrayList<Integer> getIP() {
        return IP;
    }

    public void setIP(ArrayList<Integer> IP) {
        this.IP = IP;
    }

    public ArrayList<Integer> getCoverage() {
        return coverage;
    }

    public long getHash() {
        return hash;
    }

    public double getVolume() {
        return volume;
    }
}
