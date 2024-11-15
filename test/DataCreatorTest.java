import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class DataCreatorTest {

    DataCreator dataCreator = new DataCreator();

    @Test
    void testGetRandomPoint() {
        Point p;
        float x, y;
        int num = 100000;
        for (int i = 0; i < num; i++) {
            p = dataCreator.middleManGetRandomPoint(-1, 1, -1, 1);
            x = p.getX();
            y = p.getY();
            assertTrue(x >= -1 && x <= 1 && y >= -1 && y <= 1);
        }
    }

    @Test
    void testGetPointCategory() {
        Point p;
        int num = 100000;
        String category = "";
        for (int i = 0; i < num; i++) {
            p = dataCreator.middleManGetRandomPoint(-1, 1, -1, 1);
            category = dataCreator.middleManGetPointCategory(p);
            assertTrue(
                    category.equals("C1") || category.equals("C2") || category.equals("C3") || category.equals("C4"));
        }
    }

    @Test
    void testCreateDataSDT() {
        dataCreator.createDataSDT();
        Map<String, ArrayList<Point>> dataSDT = dataCreator.getDataSDT();
        int totalMapSize = dataSDT.get("C1").size() + dataSDT.get("C2").size() + dataSDT.get("C3").size()
                + dataSDT.get("C4").size();
        assertTrue(totalMapSize == 8000);

    }

    @Test
    void testCreateDataSDO() {
        dataCreator.createDataSDO();
        Point[] data = dataCreator.getDataSDO();
        float x = 0, y = 0;
        for (int i = 0; i < 1000; i++) {
            x = data[i].getX();
            y = data[i].getY();
            if (i >= 800) {

                assertTrue(x >= -2 && x <= 0 && y >= 0 && y <= 2);
            }

            else if (i >= 700) {

                assertTrue(x >= -0.4f && x <= 0 && y >= 0 && y <= 0.4f);
            }

            else if (i >= 600) {

                assertTrue(x >= -1.2f && x <= -0.8f && y >= 0 && y <= 0.4f);
            }

            else if (i >= 500) {

                assertTrue(x >= -2f && x <= -1.6f && y >= 0 && y <= 0.4f);
            }

            else if (i >= 400) {

                assertTrue(x >= -0.6f && x <= -0.2f && y >= 0.8f && y <= 1.2f);
            }

            else if (i >= 300) {

                assertTrue(x >= -1.8f && x <= -1.4f && y >= 0.8f && y <= 1.2f);
            }

            else if (i >= 200) {

                assertTrue(x >= -0.4f && x <= 0 && y >= 1.6f && y <= 2);
            }

            else if (i >= 100) {

                assertTrue(x >= -1.2f && x <= -0.8f && y >= 1.6f && y <= 2);
            }

            else if (i >= 0) {
                assertTrue(x >= -2 && x <= -1.6f && y >= 1.6f && y <= 2);
            }
        }
    }
}
