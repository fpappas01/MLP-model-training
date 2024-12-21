
import java.util.Random;

public class DataCreatorSDO {

    private final PointSDO[] dataSDO = new PointSDO[1000];

    public PointSDO[] getDataSDO() {
        return dataSDO;
    }    

    private PointSDO getRandomPoint(float xMin, float xMax, float yMin, float yMax) {
        Random random = new Random();
        float x = xMin + random.nextFloat() * (xMax - xMin);
        float y = yMin + random.nextFloat() * (yMax - yMin);
        return new PointSDO(x, y);
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
