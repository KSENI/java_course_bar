public class World {
	public static void main(String[] args) {
        printPointAndDistance(new Point(3, 5), new Point(7, 8));
        printPointAndDistance(new Point(1, 5), new Point(1, 8));
        printPointAndDistance(new Point(3, 1), new Point(7, 1));
        printPointAndDistance(new Point(0, 0), new Point(0, 0));
    }

    private static void printPointAndDistance(Point point1, Point point2) {
	    double distance = point1.distance(point2);
        System.out.println("Первая точка: x=" + point1.x + ", y=" + point1.y +
                " Вторая точка: x=" + point2.x + ", y=" + point2.y + " Расстояние между точками = " + distance);
    }
}