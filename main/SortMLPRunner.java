import java.util.Iterator;
import java.util.Random;
import java.util.TreeSet;
import java.io.BufferedWriter;
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileWriter;   // Import the FileWriter class

public class SortMLPRunner {
    private int H1;
    private int H2;
    private int H3;
    private double learningRate;
    private double terminationThreshold;
    private int BatchNumber;
    private SortMLP sortMLP;
    private TreeSet<MLPSolution> sols = new TreeSet<>();
    
    public SortMLPRunner(int H1, int H2, int H3, double learningRate, double terminationThreshold, int BatchNumber) {
        this.H1 = H1;
        this.H2 = H2;
        this.H3 = H3;
        this.learningRate = learningRate;
        this.terminationThreshold = terminationThreshold;
        this.BatchNumber = BatchNumber;
        this.sortMLP = new SortMLP(2, 4, H1, H2, H3, learningRate, terminationThreshold, BatchNumber);
    }

    public SortMLPRunner(int H1, int H2, double learningRate, double terminationThreshold, int BatchNumber) {
        this.H1 = H1;
        this.H2 = H2;
        this.learningRate = learningRate;
        this.terminationThreshold = terminationThreshold;
        this.BatchNumber = BatchNumber;
        this.sortMLP = new SortMLP(2, 4, H1, H2, 0, learningRate, terminationThreshold, BatchNumber);
    }

    public int[] getBestNetwork() throws IOException {
        int[] res = new int[3];
        double generizationAbility = 0, tmp = 0;
        int counter = 0;
        do {
            H1 = getRandomInt(1, 15);
            H2 = getRandomInt(1, 15);
            H3 = (getRandomDouble() <= 0.5) ? 0 : getRandomInt(1, 15);  
            this.sortMLP = new SortMLP(2, 4, H1, H2, H3, learningRate, terminationThreshold, BatchNumber);
            this.sortMLP.execute();
            tmp = this.sortMLP.getGeneralizationAbility();
            sols.add(new MLPSolution(tmp, H1, H2, H3));
            if (generizationAbility < tmp) {
                generizationAbility = tmp;
                res[0] = H1;
                res[1] = H2;
                res[2] = H3;
            }
            counter++;
        } while (counter < 500);
        return res;
    }

    public void compareNetworks() throws IOException {
       Iterator it = sols.descendingIterator();
       MLPSolution sol;
       BufferedWriter myWriter = new BufferedWriter(new FileWriter("res.txt"));
       while (it.hasNext()) {
            sol = (MLPSolution) it.next();
            myWriter.write(sol.getGenerizationAbility() + " " + sol.getH1() +  " " + sol.getH2() + " " + sol.getH3());
            myWriter.newLine();
       }
       myWriter.close();
    }

    private int getRandomInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(min, max + 1);
    }

    private double getRandomDouble() {
        Random random = new Random();
        return random.nextDouble();
    }

}
