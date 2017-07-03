import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

class Food extends Point {
	public Food() {
		// TODO Auto-generated constructor stub
		// this.x=(Integer) null;
		// this.y=(Integer) null;
	}

	public void creatFood() {
		// Random random= new Random();
		//System.out.println("Food.creatFood");
		this.x = (int) (Math.random() * Parameters.WINDOW_LENGTH);
		this.y = (int) (Math.random() * Parameters.WINDOW_WIDTH);
		//System.out.println(this.x);
		//System.out.println(this.y);
	}

	public void showMe(Graphics g) {
		//System.out.println("Food.showMe");

		if (Controller.getGameStatus()) {
			g.setColor(Color.cyan);
			g.fillOval(this.x * Parameters.REC_LENGTH, this.y
					* Parameters.REC_WIDTH, Parameters.REC_LENGTH,
					Parameters.REC_WIDTH);
		}

	}
}