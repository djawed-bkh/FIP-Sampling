import java.util.ArrayList;


public class Main {

    public static void main(String[] args) {
        // Initialize the database with empty lists
        Database db = new Database(new ArrayList<>(), new ArrayList<>());


        //Benchmark
        String [] databases={"AP","balance-scale", "BK", "cancer", "CH", "diabetes", "glass","heart", "Iris","NT", "LW","sonar", "yacht" };


        for (String database : databases) {

            db.readDB("./benchmark/" + database + "_T.dat");
            System.out.println("\n");
            System.out.println("Database: " + database);
            //db.showDB();                      // show the database
            //db.getDBStatistics();             //statistics of the database


            //UNCOMMENT THE APPROACH 1, 2 OR 3 if you want to test the execution of the methods:

            //1 : FIPS approch
            //2 : Uniform Interval pattern sampling approach, with non-empty coverage.
            //3 : Uniform Interval pattern sampling approach, without non-empty coverage.


            /*
            // 1
             IPSampling ips = new IPSampling(db);

            for (int i = 0; i < 5; i++) {
            IP ip= ips.drawIP();
            ip.showIP();
            ip.showCoverage();
            }*/

            /*
            //2
            System.out.println("Random méthod[ensure non empty coverage] \n");
            RandomIPSampling rips = new RandomIPSampling(db);
                for (int i = 0; i < 10; i++) {
                    IP randomIP = rips.drawIP();
                    randomIP.showIP();
                    randomIP.showCoverage();

                }*/



            /*
            // 3
            System.out.println("Random méthod[dos not ensure non empty coverage] \n");
            TotalyRandomIPSampling Trips = new TotalyRandomIPSampling(db);
            for (int i = 0; i < 10; i++) {
                IP randomIP = Trips.drawIP();
                randomIP.showIP();
                randomIP.showCoverage();

            }*/




        }


    }

}