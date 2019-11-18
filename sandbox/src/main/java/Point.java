public class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double distance(Point p2) {
        double deltaX = Math.abs(this.x - p2.x);
        double deltaY = Math.abs(this.y - p2.y);
        if (deltaX > 0 && deltaY > 0) {
            return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        } else if (deltaX == 0) {
            return deltaY;
        } else {
            return deltaX;
        }
    }

}
