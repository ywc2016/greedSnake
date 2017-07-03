import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList;

public class SpecialFood extends Food {
	private LinkedList<Point> linkedList = new LinkedList<Point>();

	public LinkedList<Point> getLinkedList() {
		return linkedList;
	}

	public Point creatspecialFood() {
		// System.out.println("creatiron");
		this.x = (int) (Math.random() * Parameters.REC_LENGTH);
		this.y = (int) (Math.random() * Parameters.REC_WIDTH);
		//System.out.println(this.x);
		//System.out.println(this.y);
		return new Point(this.x, this.y);

	}

	public void clear() {
		this.linkedList.clear();
	}

	public void showMe(Graphics g) {
		// System.out.println("iron.showMe"+linkedList.size());
		// for(Point point : linkedList){
		// System.out.print(point.x);System.out.println(point.y);
		// }
		g.setColor(Color.MAGENTA);
		for (Point point : this.linkedList) {
			g.fillOval(point.x * Parameters.REC_LENGTH, point.y
					* Parameters.REC_WIDTH, Parameters.REC_LENGTH,
					Parameters.REC_WIDTH);
		}
	}

}
