import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList;

class Poison {
	private LinkedList<Point> linkedList = new LinkedList<Point>();

	public LinkedList<Point> getLinkedList() {
		return linkedList;
	}

	public void setLinkedList(LinkedList<Point> linkedList) {
		this.linkedList = linkedList;
	}

	public void creatpoison(int n) {

		// System.out.println("Poison.create");
		switch (n) {
		case 0:
			for (int i = 0; i <= Parameters.WINDOW_WIDTH; i++) {
				this.linkedList.add(new Point(0, i));
			}
			for (int i = 0; i <= Parameters.WINDOW_WIDTH; i++) {
				this.linkedList.add(new Point(Parameters.WINDOW_LENGTH - 1, i));
			}
			break;
		case 2:
		case 3:
			System.out.println("creat poison2");
			for (int i = Parameters.WINDOW_LENGTH / 6; i < Parameters.WINDOW_LENGTH / 3; i++) {
				this.linkedList.add(new Point(i, Parameters.WINDOW_WIDTH / 3));
			}
			for (int i = Parameters.WINDOW_LENGTH / 6; i < Parameters.WINDOW_LENGTH / 3; i++) {
				this.linkedList.add(new Point(i,
						Parameters.WINDOW_WIDTH / 3 * 2));
			}
			for (int i = Parameters.WINDOW_LENGTH / 3 * 2; i < Parameters.WINDOW_LENGTH / 6 * 5; i++) {
				this.linkedList.add(new Point(i, Parameters.WINDOW_WIDTH / 3));
			}
			for (int i = Parameters.WINDOW_LENGTH / 3 * 2; i < Parameters.WINDOW_LENGTH / 6 * 5; i++) {
				this.linkedList.add(new Point(i,
						Parameters.WINDOW_WIDTH / 3 * 2));
			}
			for (int i = Parameters.WINDOW_WIDTH / 6 - 1; i < Parameters.WINDOW_WIDTH / 3 + 1; i++) {
				this.linkedList.add(new Point(Parameters.WINDOW_LENGTH / 3, i));
			}
			for (int i = Parameters.WINDOW_WIDTH / 3 * 2; i < Parameters.WINDOW_WIDTH / 6 * 5 + 2; i++) {
				this.linkedList.add(new Point(Parameters.WINDOW_LENGTH / 3, i));
			}
			for (int i = Parameters.WINDOW_WIDTH / 6 - 1; i < Parameters.WINDOW_WIDTH / 3; i++) {
				this.linkedList.add(new Point(Parameters.WINDOW_LENGTH / 3 * 2,
						i));
			}
			for (int i = Parameters.WINDOW_WIDTH / 3 * 2; i < Parameters.WINDOW_WIDTH / 6 * 5 + 2; i++) {
				this.linkedList.add(new Point(Parameters.WINDOW_LENGTH / 3 * 2,
						i));
			}
			break;
		case 1:
			for (int i = Parameters.WINDOW_LENGTH / 3 - 2; i < Parameters.WINDOW_LENGTH / 3 * 2 + 3; i++) {
				this.linkedList.add(new Point(i, Parameters.WINDOW_WIDTH / 2));
			}
			for (int i = Parameters.WINDOW_WIDTH / 3 - 2; i < Parameters.WINDOW_WIDTH / 3 * 2 + 5; i++) {
				this.linkedList.add(new Point(Parameters.WINDOW_LENGTH / 2, i));
			}
			break;

		// break;
		default:
			break;
		}
	}

	public int getSize() {
		return this.linkedList.size();
	}

	public Point getNPoint(int n) {
		return this.linkedList.get(n);
	}

	public void showMe(Graphics g) {
		// System.out.println("Poison.showMe");
		for (Point p : linkedList) {
			// System.out.println(p.x);
			// System.out.println(p.y);
			g.setColor(Color.GRAY);
			g.fillOval(p.x * Parameters.REC_LENGTH, p.y * Parameters.REC_WIDTH,
					Parameters.REC_LENGTH, Parameters.REC_WIDTH);
		}
	}

	public void clear() {
		this.linkedList.clear();
	}
}