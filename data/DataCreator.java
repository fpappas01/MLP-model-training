
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class DataCreator {

    private final Map<String, ArrayList<Point>> dataSDT = new HashMap<>();
    private final Point[] dataSDO = new Point[1000];


    public DataCreator() {
        dataSDT.put("C1", new ArrayList<Point>());
        dataSDT.put("C2", new ArrayList<Point>());
        dataSDT.put("C3", new ArrayList<Point>());
        dataSDT.put("C4", new ArrayList<Point>());
    }

    public Map<String, ArrayList<Point>> getDataSDT() {
        return dataSDT;
    }

    public Point[] getDataSDO() {
        return dataSDO;
    }    

    private Point getRandomPoint(float xMin, float xMax, float yMin, float yMax) {
        Random random = new Random();
        float x = xMin + random.nextFloat() * (xMax - xMin);
        float y = yMin + random.nextFloat() * (yMax - yMin);
        return new Point(x, y);
    }

    //  middleManGetRandomPoint is only used for testing, will get deleted later
    // Because getRandomPoint is private
    public Point middleManGetRandomPoint(float xMin, float xMax, float yMin, float yMax) {
        return getRandomPoint(xMin, xMax, yMin, yMax);
    }
    
    //  middleManGetPointCategory is only used for testing, will get deleted later
    // Because getPointCategory is private
    public String middleManGetPointCategory(Point p) {
        return getPointCategory(p);
    }

    private String getPointCategory(Point p) {
        float x = p.getX();
        float y = p.getY();
        String category = "";

        if (Math.pow(x - 0.5, 2) + Math.pow(y - 0.5, 2) < 0.2) {
            if (y > 0.5)
                category = "C1";
            else if (y < 0.5)
                category = "C2";
        }

        else if (Math.pow(x + 0.5, 2) + Math.pow(y + 0.5, 2) < 0.2) {
            if (y > -0.5)
                category = "C1";
            else if (y < -0.5)
                category = "C2";
        }

        else if (Math.pow(x - 0.5, 2) + Math.pow(y + 0.5, 2) < 0.2) {
            if (y > -0.5)
                category = "C1";
            else if (y < -0.5)
                category = "C2";
        }

        else if (Math.pow(x + 0.5, 2) + Math.pow(y - 0.5, 2) < 0.2) {
            if (y > 0.5)
                category = "C1";
            else if (y < 0.5)
                category = "C2";
        }

        else if (x * y > 0)
            category = "C3";

        else if (x * y < 0)
            category = "C4";

        return category;
    }

    public void createDataSDT() {
        Point p;
        String category = "";
        for (int i = 0; i < 8000; i++) {
            p = getRandomPoint(-1, 1, -1, 1);
            category = getPointCategory(p);
            dataSDT.get(category).add(p);
        }
    }

    public void createDataSDO() {
        float xMin = 0, xMax = 0, yMin = 0, yMax = 0;
        for (int i = 0; i < 1000; i++) {
            if (i >= 800) {
                xMin = -2; xMax = 0;
                yMin = 0; yMax = 2;
            }   

            else if (i >= 700) {
                xMin = -0.4f; xMax = 0;
                yMin = 0; yMax = 0.4f;    
            }

            else if (i >= 600) {
                xMin = -1.2f; xMax = -0.8f;
                yMin = 0; yMax = 0.4f;
            }
            
            else if (i >= 500) {
                xMin = -2f; xMax = -1.6f;
                yMin = 0; yMax = 0.4f;
            }
            
            else if (i >= 400) {
                xMin = -0.6f; xMax = -0.2f;
                yMin = 0.8f; yMax = 1.2f; 
            }

            else if (i >= 300) {
                xMin = -1.8f; xMax = -1.4f;
                yMin = 0.8f; yMax = 1.2f;
            }

            else if (i >= 200) {
                xMin = -0.4f; xMax = 0;
                yMin = 1.6f; yMax = 2;
            }

            else if (i >= 100) {
                xMin = -1.2f; xMax = -0.8f;
                yMin = 1.6f; yMax = 2;
            }

            else if (i >= 0) {
                xMin = -2; xMax = -1.6f;
                yMin = 1.6f; yMax = 2;
            }
            dataSDO[i] = getRandomPoint(xMin, xMax, yMin, yMax);
        }
    }

}
