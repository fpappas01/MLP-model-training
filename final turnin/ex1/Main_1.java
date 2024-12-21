import java.io.IOException;
import java.util.Scanner;

public class Main_1 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the value for H1: ");
        int H1 = scanner.nextInt();

        System.out.print("Enter the value for H2: ");
        int H2 = scanner.nextInt();

        System.out.print("Do you want a third hidden layer? (1 for yes, 0 for no): ");
        int hasH3 = scanner.nextInt();
        int H3 = 0;
        if (hasH3 == 1) {
            System.out.print("Enter the value for H3: ");
            H3 = scanner.nextInt();
        }

        System.out.print("Do you want to use ReLU or Tahn for the last hidden layer? (1 for reLU, 0 for Tahn): ");
        int reluChoice = scanner.nextInt();
        boolean isRelu = reluChoice == 1;

        System.out.print("Enter the value for B (batch size): ");
        int B = scanner.nextInt();

        scanner.close();
        SortMLP sortMLP = new SortMLP(2, 4, H1, H2, H3, 0.001, 0.1, B, isRelu);
        sortMLP.execute();
    }
}
