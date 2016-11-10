
public class OverlapRectangle {
	public static boolean overlap(Point topLeftA, Point bottomRightA, Point topLeftB, Point bottomRightB) {
		if (topLeftA.x >= bottomRightB.x || topLeftB.x >= bottomRightA.x) {  //important equal
		return false;
		}
		if (bottomRightA.y >= topLeftB.y || bottomRightB.y >= topLeftA.y) {
		return false;
		}
		return true;
		}
}
