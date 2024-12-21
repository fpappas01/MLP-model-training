import java.io.IOException;

public class Main_2 {
    public static void main(String[] args) throws IOException {
        // EXERCISE 2
         DataClusterer dataClusterer;
         int M = 4;
         for (int i = 0; i < 5; i++) {
             dataClusterer = new DataClusterer(M);
             System.out.println("This is for M = " + M);
             dataClusterer.execute();
             Solution sol = dataClusterer.getBestSolution();
             M += 2;
         }
    }
}
