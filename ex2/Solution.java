
public class Solution {
    private Point[] centroids;
    private double totalError;

    public Solution(Point[] centroids, double totalError) {
        this.centroids = centroids;
        this.totalError = totalError;
    }

    public Point[] getCentroids() {
        return centroids;
    }
    public void setCentroids(Point[] centroids) {
        this.centroids = centroids;
    }
    public double getTotalError() {
        return totalError;
    }
    public void setTotalError(double totalError) {
        this.totalError = totalError;
    }
}
