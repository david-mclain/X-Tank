package XTank;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class MapCreator {
	private static List<Rectangle> obstacles;
	
	public static void create() {
		obstacles = new ArrayList<>();
		obstacles.add(new Rectangle(150, 200, 350, 20));
		obstacles.add(new Rectangle(150, 400, 250, 20));
	}
	
	public static List<Rectangle> get() {
		return obstacles;
	}
}
