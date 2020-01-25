import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {

    @Test
    public void pointsInOneVerticalLine() {
        double factualDistance = new Point(5, 5).distance(new Point(5, 1));
        double expectedDistance = 4.00;
        Assert.assertEquals(expectedDistance,factualDistance);
    }
    @Test
    public void pointsInOneHorizontalLine() {
        double factualDistance = new Point(1, 5).distance(new Point(1, 8));
        double expectedDistance = 3.00;
        Assert.assertEquals(expectedDistance,factualDistance);
    }
    @Test
    public void pointsInOnePoint() {
        double factualDistance = new Point(0, 0).distance(new Point(0, 0));
        double expectedDistance = 0.00;
        Assert.assertEquals(expectedDistance,factualDistance);
    }
    @Test
    public void points() {
        double factualDistance = new Point(3, 5).distance(new Point(7, 8));
        double expectedDistance = 5.00;
        Assert.assertEquals(expectedDistance,factualDistance);
    }

 }
