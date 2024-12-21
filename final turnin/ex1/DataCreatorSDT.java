
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class DataCreatorSDT {

    private final Map<String, ArrayList<PointSDT>> dataSDT = new HashMap<>();


    public DataCreatorSDT() {
        dataSDT.put("C1", new ArrayList<PointSDT>());
        dataSDT.put("C2", new ArrayList<PointSDT>());
        dataSDT.put("C3", new ArrayList<PointSDT>());
        dataSDT.put("C4", new ArrayList<PointSDT>());
    }

    public Map<String, ArrayList<PointSDT>> getDataSDT() {
        return dataSDT;
    }


    private PointSDT getRandomPoint(float xMin, float xMax, float yMin, float yMax) {
        Random random = new Random();
        float x = xMin + random.nextFloat() * (xMax - xMin);
        float y = yMin + random.nextFloat() * (yMax - yMin);
        return new PointSDT(x, y);
    }

    private String getPointCategory(PointSDT p) {
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
        PointSDT p;
        String category = "";
        for (int i = 0; i < 8000; i++) {
            p = getRandomPoint(-1, 1, -1, 1);
            category = getPointCategory(p);
            dataSDT.get(category).add(p);
        }
    }
}
