import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // EXERCISE 1
        SortMLP sortMLP = new SortMLP(2, 4, 4, 5, 3, 0.001, 0.1, 200);
        sortMLP.execute();  
        // SortMLPRunner sortMLPRunner = new SortMLPRunner(2, 3, 6, 0.001, 0.1, 200);
        // int[] r = sortMLPRunner.getBestNetwork();
        // System.out.println(r[0]);
        // System.out.println(r[1]);
        // System.out.println(r[2]);
        // sortMLPRunner.compareNetworks();
        
        // EXERCISE 2
        // DataClusterer dataClusterer;
        // int M = 4;
        // for (int i = 0; i < 5; i++) {
        //     dataClusterer = new DataClusterer(M);
        //     System.out.println("This is for M = " + M);
        //     dataClusterer.execute();
        //     M += 2;
        // }
    }
}
