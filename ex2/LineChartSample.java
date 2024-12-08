// // import javax.swing.*;
// // import java.awt.*;
// // import java.util.ArrayList;
// // import java.util.List;

// // public class Plotter extends JPanel {
// //     private final DataClusterer dataClusterer;
// //     private Point[] members = new Point[1000];
// //     private Point[] centroids;

// //     public Plotter(DataClusterer dataClusterer) {
// //         this.dataClusterer = dataClusterer;
// //         dataClusterer.execute();
// //         Solution bestSolution = dataClusterer.getBestSolution();
// //         centroids = bestSolution.getCentroids();
// //         Group[] groups = dataClusterer.getGroups();
// //         Point[] points;
// //         ArrayList<Point> tmpMembers = new ArrayList<>();
// //         for (Group group : groups) {
// //             tmpMembers.addAll(group.getMembers());
// //             for (int i = 0; i < tmpMembers.size(); i++) {
// //                 members[i] = tmpMembers.get(i);
// //             }
// //         }
// //         System.out.println(members.length);
// //     }

// //     // public void createPlotOneM() {

// //     // }

// //     // public void createPlotMultipleM() {

// //     // }


// //     @Override
// //     protected void paintComponent(Graphics g) {
// //         super.paintComponent(g);
// //         System.out.println("Panel dimensions: " + getWidth() + "x" + getHeight());
// //         Graphics2D g2d = (Graphics2D) g;

// //         // Enable anti-aliasing for smoother graphics
// //         g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

// //         // Draw members as "+"
// //         drawPoints(g2d, members, Color.BLUE, "+");

// //         // Draw centroids as "*"
// //         drawPoints(g2d, centroids, Color.RED, "*");

// //         g2d.drawString("+", 100, 100);
        
// //     }

// //     private void drawPoints(Graphics2D g2d, Point[] points, Color color, String symbol) {
// //         g2d.setColor(color);
// //         for (Point p : points) {
// //             g2d.drawString(symbol, p.getX(), p.getY());
// //         }
// //     }

// //     public static void main(String[] args) {   
// //         // Create and set up the frame
// //         JFrame frame = new JFrame("Plot");
// //         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
// //         frame.setSize(400, 400);
// //         DataClusterer dataClusterer = new DataClusterer(4);
// //         frame.add(new Plotter(dataClusterer));
// //         frame.setVisible(true);
// //     }
// // }

// import javafx.application.Application;
// import javafx.scene.Scene;
// import javafx.scene.chart.LineChart;
// import javafx.scene.chart.NumberAxis;
// import javafx.scene.chart.XYChart;
// import javafx.stage.Stage;
 
 
// public class LineChartSample extends Application {
 
//     @Override public void start(Stage stage) {
//         stage.setTitle("Line Chart Sample");
//         //defining the axes
//         final NumberAxis xAxis = new NumberAxis();
//         final NumberAxis yAxis = new NumberAxis();
//         xAxis.setLabel("Number of Month");
//         //creating the chart
//         final LineChart<Number,Number> lineChart = 
//                 new LineChart<Number,Number>(xAxis,yAxis);
                
//         lineChart.setTitle("Stock Monitoring, 2010");
//         //defining a series
//         XYChart.Series series = new XYChart.Series();
//         series.setName("My portfolio");
//         //populating the series with data
//         series.getData().add(new XYChart.Data(1, 23));
//         series.getData().add(new XYChart.Data(2, 14));
//         series.getData().add(new XYChart.Data(3, 15));
//         series.getData().add(new XYChart.Data(4, 24));
//         series.getData().add(new XYChart.Data(5, 34));
//         series.getData().add(new XYChart.Data(6, 36));
//         series.getData().add(new XYChart.Data(7, 22));
//         series.getData().add(new XYChart.Data(8, 45));
//         series.getData().add(new XYChart.Data(9, 43));
//         series.getData().add(new XYChart.Data(10, 17));
//         series.getData().add(new XYChart.Data(11, 29));
//         series.getData().add(new XYChart.Data(12, 25));
        
//         Scene scene  = new Scene(lineChart,800,600);
//         lineChart.getData().add(series);
       
//         stage.setScene(scene);
//         stage.show();
//     }
 
//     public static void main(String[] args) {
//         launch(args);
//     }
// }
