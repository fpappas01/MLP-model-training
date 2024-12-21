import java.util.ArrayList;
import java.util.Random;

public class DataClusterer {
    private int M; // number of teams/centroids
    private PointSDO[] data;
    private Group[] groups;
    private final ArrayList<Solution> solutions;
    private final DataCreatorSDO dataCreatorSDO = new DataCreatorSDO();

    public DataClusterer(int M) {
        this.M = M;
        dataCreatorSDO.createDataSDO();
        this.data = dataCreatorSDO.getDataSDO();
        this.groups = new Group[M];
        solutions = new ArrayList<>();
    }

    public Group[] getGroups() {
        return this.groups;
    }

    public void kMeans() {
        PointSDO[] previousCentroids = new PointSDO[M];
        int[] randomIndexes = getRandomInts();
        double distance = Double.MAX_VALUE;
        double distTemp = 0;
        int index = 0;
        for (int i = 0; i < groups.length; i++) {
            groups[i].setCentroid(data[randomIndexes[i]]); 
        }
        do {
            for (Group group : groups) {
                group.getMembers().clear();
            }
            for (PointSDO point : data) {
                for (int i = 0; i < groups.length; i++) {
                    distTemp = Math.min(distance, getEuclideanDistance(point, groups[i].getCentroid()));
                    if (distTemp != distance) {
                        index = i;
                        distance = distTemp;
                    }
                }
                groups[index].addMember(point);
                distance = Double.MAX_VALUE;
            }
            previousCentroids = getCurrentCentroids();
            updateCentroids();
        }
        while (centroidsChange(previousCentroids));
    }

    public double getTotalError() {
        updateGroupErrors();
        double totalError = 0;
        for (Group group : groups) {
            totalError += group.getGroupError();
        }
        return totalError;
    }

    private void  updateGroupErrors() {
        double sum = 0;
        for (Group group : groups) {
            for (PointSDO member : group.getMembers()) {
                sum += Math.pow(getEuclideanDistance(member, group.getCentroid()), 2);
            }
            group.setGroupError(sum);
            sum = 0;
        }
    }

    public void execute() {
        PointSDO[] centroids = new PointSDO[M];
        double totalError;
        for (int i = 0; i < M; i++) {
            groups[i] = new Group(null, new ArrayList<PointSDO>(), 0);
        }
        for (int i = 0; i < 20; i++) {
            kMeans();
            for (int j = 0; j < groups.length; j++) {
                centroids[j] = groups[j].getCentroid();
            }
            totalError = getTotalError();
            System.out.println("Total Error: " + i + " -> " +  totalError);
            solutions.add(new Solution(centroids, totalError));
        }
        System.out.println("Best: " + getBestSolution().getTotalError());
    }

    public Solution getBestSolution() {
        double minError = Double.MAX_VALUE;
        double tempError;
        int index = 0;
        for (int i = 0; i < solutions.size(); i++) {
            tempError = solutions.get(i).getTotalError();
            if (tempError < minError) {
                index = i;
                minError = tempError;
            }
        }
        return solutions.get(index);
    }

    private double getEuclideanDistance(PointSDO point, PointSDO centroid) {
        double yAxis = Math.pow(point.getY() - centroid.getY(), 2);
        double xAxis = Math.pow(point.getX() - centroid.getX(), 2);
        return Math.sqrt(yAxis + xAxis);
    }

    private void updateCentroids() {
        for (int i = 0; i < groups.length; i++) {
            groups[i].setCentroid(calculateNewCentroid(groups[i]));
        }
    }

    private PointSDO[] getCurrentCentroids() {
        PointSDO[] curCentroids = new PointSDO[M];
        for (int i = 0; i < curCentroids.length; i++) {
            curCentroids[i] = groups[i].getCentroid();
        }
        return curCentroids;
    }

    private PointSDO calculateNewCentroid(Group group) {
        float xSum = 0, ySum = 0;
        for (PointSDO member : group.getMembers()) {
            xSum += member.getX();
            ySum += member.getY();
        }
        xSum = xSum / group.getMembers().size();
        ySum = ySum / group.getMembers().size();
        return new PointSDO(xSum, ySum);
    }

    private int[] getRandomInts() {
        Random random = new Random();
        int[] nums = new int[M];
        for (int i = 0; i < M; i++) {
            nums[i] = random.nextInt(data.length);
        }
        return nums;
    }

    private boolean centroidsChange(PointSDO[] previousCentroids) {
        PointSDO[] currentCentroids = new PointSDO[previousCentroids.length];
        for (int i = 0; i < currentCentroids.length; i++) {
            currentCentroids[i] = groups[i].getCentroid();
            if (getEuclideanDistance(previousCentroids[i], currentCentroids[i]) > 0.00001) return true;
        }
        return false;
    }
    
}
