import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList;

class Snake extends LinkedList implements Cloneable {

	static final int UP = 1, DOWN = -1, LEFT = 2, RIGHT = -2;
	private static final int FOOD_SCORE = 10;
	private static final int SPECIAL_FOOD_SCORE = 20;

	public static int getSpecialFoodScore() {
		return SPECIAL_FOOD_SCORE;
	}

	public void setNewDirection(int newDirection) {
		this.newDirection = newDirection;
	}

	private int newDirection;
	private int oldDirection;
	private LinkedList<Point> linkedList = new LinkedList<Point>();
	private boolean life;
	private int score = 0;
	private boolean isPlayer = false;

	public void setOldDirection(int oldDirection) {
		this.oldDirection = oldDirection;
	}

	@Override
	public Snake clone() {
		// TODO Auto-generated method stub
		Snake snakeClone = (Snake) (super.clone());
		snakeClone.linkedList = new LinkedList<Point>();
		for (Point p : this.linkedList) {
			snakeClone.linkedList.add(p);
		}
		return snakeClone;
	}

	public void compulsoryMove(int steps) {
		if (!this.life)
			return;
		if (oldDirection + newDirection != 0) {
			oldDirection = newDirection;
		}
		switch (oldDirection) {
		case UP:
			for (int i = 0; i < steps; i++) {
				this.linkedList.removeLast();
				this.linkedList.addFirst(new Point(this.getSnakeHead().x, this
						.getSnakeHead().y - 1));
			}
			break;
		case DOWN:
			for (int i = 0; i < steps; i++) {
				this.linkedList.removeLast();
				this.linkedList.addFirst(new Point(this.getSnakeHead().x, this
						.getSnakeHead().y + 1));
				// System.out.println("down");
			}
			break;
		case LEFT:
			for (int i = 0; i < steps; i++) {
				this.linkedList.removeLast();
				this.linkedList.addFirst(new Point(this.getSnakeHead().x - 1,
						this.getSnakeHead().y));
				// System.out.println("left");
			}
			break;
		case RIGHT:
			for (int i = 0; i < steps; i++) {
				this.linkedList.removeLast();
				this.linkedList.addFirst(new Point(this.getSnakeHead().x + 1,
						this.getSnakeHead().y));
				// System.out.println("right");
			}
			break;

		default:
			break;
		}
		if (this.getSnakeHead().y < 0)
			this.getSnakeHead().y = Parameters.WINDOW_WIDTH;
		if (this.getSnakeHead().y > Parameters.WINDOW_WIDTH)
			this.getSnakeHead().y = 0;
		if (this.getSnakeHead().x < 0)
			this.getSnakeHead().x = Parameters.WINDOW_LENGTH;
		if (this.getSnakeHead().x > Parameters.WINDOW_LENGTH)
			this.getSnakeHead().x = 0;
	}

	public boolean isPlayer() {
		return isPlayer;
	}

	public void setPlayer(boolean isPlayer) {
		this.isPlayer = isPlayer;
	}

	Snake() {

	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void initialSnake(int n, int d) {
		this.oldDirection = this.newDirection = RIGHT;
		int x4 = Parameters.WINDOW_LENGTH / 2, y4 = Parameters.WINDOW_WIDTH / 2;
		for (int i = 0; i < 3; i++) {
			this.linkedList.addLast(new Point(x4--, y4));
		}
		life = true;
		switch (d) {
		case 0:
			this.oldDirection = this.newDirection = RIGHT;
			switch (n) {
			case 0:
				break;
			case 1:
				this.linkedList.clear();
				int x1 = Parameters.WINDOW_LENGTH / 2,
				y1 = Parameters.WINDOW_WIDTH / 2 + 3;
				for (int i = 0; i < 3; i++) {
					this.linkedList.addLast(new Point(x1--, y1));
				}
				break;
			case 2:
				this.linkedList.clear();
				int x5 = Parameters.WINDOW_LENGTH / 2,
				y5 = Parameters.WINDOW_WIDTH / 2 - 3;
				for (int i = 0; i < 3; i++) {
					this.linkedList.addLast(new Point(x5--, y5));
				}

				break;
			}
			break;
		case 1:
		case 2:
		case 3:
			this.oldDirection = this.newDirection = UP;
			switch (n) {
			case 0:
				// System.out.println("crazy red snake");
				this.linkedList.clear();
				int x2 = 1,
				y2 = Parameters.WINDOW_WIDTH / 2;
				for (int i = 0; i < 3; i++) {
					this.linkedList.add(new Point(x2, y2++));
				}
				break;
			case 1:
				// System.out.println("crazy blue snake");
				this.linkedList.clear();
				int x3 = Parameters.WINDOW_LENGTH - 1,
				y3 = Parameters.WINDOW_WIDTH / 2;
				for (int i = 0; i < 3; i++) {
					this.linkedList.add(new Point(x3, y3++));
				}
				break;
			case 2:
				this.oldDirection = this.newDirection = RIGHT;
				// System.out.println("crazy yellow snake");
				this.linkedList.clear();
				int x6 = Parameters.WINDOW_LENGTH / 2,
				y6 = 1;
				for (int i = 0; i < 3; i++) {
					this.linkedList.add(new Point(x6, y6++));
				}
				break;
			default:
				break;
			}
		default:
			break;
		}

	}

	public void clear() {
		this.linkedList.clear();
	}

	public Point getNPoint(int n) {
		return this.linkedList.get(n);
	}

	public int getLength() {
		return this.linkedList.size();
	}

	public Point getTail() {
		return this.linkedList.getLast();
	}

	public void addTail() {
		// System.out.println("addtail");
		this.linkedList.addLast(getTail());
	}

	public void removeHead() {
		// System.out.println("remove head");
		this.linkedList.removeFirst();
	}

	public void removeTail() {
		this.linkedList.removeLast();
	}

	public void showMe(Graphics g, int n) {
		// System.out.println("Snake.showMe");
		for (Point p : linkedList) {
			// System.out.println(p.x + " " + p.y);
		}
		if (n == 1) {
			g.setColor(Color.red);
		} else if (n == 2) {
			g.setColor(Color.blue);
		} else {
			g.setColor(Color.yellow);
		}
		// int i=0;
		for (Point p : linkedList) {

			// System.out.println(p.x);
			// System.out.println(p.y);
			// if(i==0){
			// g.fillOval(p.x * Parameters.REC_LENGTH, p.y *
			// Parameters.REC_WIDTH
			// , Parameters.REC_LENGTH, Parameters.REC_WIDTH);
			// i++;
			// }
			// else {
			g.fill3DRect(p.x * Parameters.REC_LENGTH, p.y
					* Parameters.REC_WIDTH, Parameters.REC_LENGTH,
					Parameters.REC_WIDTH, true);
			// }
		}

	}

	public Point getSnakeHead() {
		return this.linkedList.getFirst();
	}

	public void changeDirection(int direction) {
		System.out.println("newdir to " + direction);
		this.newDirection = direction;
	}

	public int getNewDirection() {
		return this.newDirection;
	}

	public int getOldDirection() {
		return this.oldDirection;
	}

	public void move() {
		if (!this.life)
			return;
		// System.out.println("Snake.move");
		this.linkedList.removeLast();
		if (oldDirection + newDirection != 0) {
			oldDirection = newDirection;
		}
		switch (this.oldDirection) {
		case UP:
			this.linkedList.addFirst(new Point(this.getSnakeHead().x, this
					.getSnakeHead().y - 1));
			// System.out.println("up");
			break;
		case DOWN:
			this.linkedList.addFirst(new Point(this.getSnakeHead().x, this
					.getSnakeHead().y + 1));
			// System.out.println("down");
			break;
		case LEFT:
			this.linkedList.addFirst(new Point(this.getSnakeHead().x - 1, this
					.getSnakeHead().y));
			// System.out.println("left");
			break;
		case RIGHT:
			this.linkedList.addFirst(new Point(this.getSnakeHead().x + 1, this
					.getSnakeHead().y));
			// System.out.println("right");
			break;
		}
		if (this.getSnakeHead().y < 0)
			this.getSnakeHead().y = Parameters.WINDOW_WIDTH;
		if (this.getSnakeHead().y > Parameters.WINDOW_WIDTH)
			this.getSnakeHead().y = 0;
		if (this.getSnakeHead().x < 0)
			this.getSnakeHead().x = Parameters.WINDOW_LENGTH;
		if (this.getSnakeHead().x > Parameters.WINDOW_LENGTH)
			this.getSnakeHead().x = 0;
	}

	public void eatFood(int difficulty, int snakeId) {
		// System.out.println("Snake.eatFood");
		addTail();
		if (difficulty == 2 || difficulty == 3)
			addTail();
		if (snakeId == 2)
			addTail();
		score += FOOD_SCORE;
	}

	public void eatPoison() {
		// System.out.println("Snake.eatPoison");
	}

	public boolean getLife() {
		return this.life;
	}

	public void die(int n) {
		// System.out.println(n + "Snake.die");
		this.life = false;
		if (Controller.gamemode == 2) {
			// clear();
		}
	}

	public void addLength() {
		// System.out.println("Snake.addLength");
	}

	public void eatSpecial() {
		if ((int) (Math.random() * 5) != 0) {
			this.addTail();
			// this.addTail();
			// this.addTail();
		} else {
			if (this.getLength() > 5) {
				this.removeTail();
				this.removeTail();
				this.removeTail();
				this.removeTail();
			} else {
				for (; linkedList.size() > 2;) {
					linkedList.removeFirst();
				}
			}
		}
		score += SPECIAL_FOOD_SCORE;
	}

	public LinkedList<Point> getLinkedList() {
		// TODO Auto-generated method stub
		return this.linkedList;
	}

	public void revive() {
		// TODO Auto-generated method stub
		this.life = true;
	}

}