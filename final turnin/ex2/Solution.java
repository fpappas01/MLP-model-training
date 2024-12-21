
public class Solution {
    private PointSDO[] centroids;
    private double totalError;

    public Solution(PointSDO[] centroids, double totalError) {
        this.centroids = centroids;
        this.totalError = totalError;
    }

    public PointSDO[] getCentroids() {
        return centroids;
    }
    public void setCentroids(PointSDO[] centroids) {
        this.centroids = centroids;
    }
    public double getTotalError() {
        return totalError;
    }
    public void setTotalError(double totalError) {
        this.totalError = totalError;
    }
}
