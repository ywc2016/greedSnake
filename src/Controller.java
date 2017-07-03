import java.awt.Point;
import java.util.LinkedList;

import javax.swing.JOptionPane;

class Controller {
	public static boolean isPlayerList[] = { false, false, false };
	private LinkedList<Snake> snake;
	private WindowPanel windowPanel;
	private Food food;
	private Poison poison;
	private GameFrame gameFrame;
	private GamePanel gamePanel;
	public static boolean gameStatus;
	private static int speed = 120;
	static int difficulty;
	private SpecialFood specialFood;
	private boolean replay = false;
	public static final String help = "��Ϸ���������������淨�����ˣ�˫�˺Ͷ�����ģʽ��\n"
			+ "������ģʽ�������������������߲���ʤ���������߳Ե�ʳ����������Ϊ������\n"
			+ "ÿ��ģʽ����4���Ѷȡ��������Ѷ�������ʳ���ɫʳ��Ժ���С�������̣����޾�ģʽ��������������"
			+ "��  �����Ե�����Ϸ�ٶ�\n" + "�ո�P��������ͣ��Ϸ," + "F1����λ\n"
			+ "��Ϸ�����߳Ե�ʯͷ���Լ���������߶��ֵ�����,����С��2��������\n"
			+ "���һ���������ҿ��ƺ��ߣ���Ҷ���WSAD������ɫ��,�������IKJL���ƻ�ɫ��.\n";
	static int gamemode = 0;

	Controller() {
	}

	/**
	 * @param snake
	 * @param food
	 * @param poison
	 * @param gamePanel
	 */
	public static boolean getGameStatus() {
		return gameStatus;
	}

	public void setGameStatus(boolean b) {
		this.gameStatus = b;
	}

	public Controller(LinkedList<Snake> snake, Food food, Poison poison,
			GameFrame gameFrame, GamePanel gamePanel, SpecialFood ironCurtain,
			WindowPanel windowPanel) {
		super();
		this.snake = snake;
		this.food = food;
		this.poison = poison;
		this.gameFrame = gameFrame;
		this.gamePanel = gamePanel;
		this.specialFood = ironCurtain;
		this.windowPanel = windowPanel;
		// this.snake=snake;
		speed = 120;
		this.difficulty = 0;
	}

	public void setDifficulty(int n) {
		this.difficulty = n;
	}

	public int getDifficulty() {
		return this.difficulty;
	}

	public boolean isEateFood(LinkedList<Snake> snake, int n) {
		// System.out.println("iseatfood");

		if (snake.get(n).getSnakeHead().equals(this.food)) {
			// System.out.println("eat food");
			return true;
		} else {
			return false;
		}
	}

	public boolean isEatOpponent(Snake s, LinkedList<Snake> sList) {
		for (int i = 0; i < sList.size(); i++) {
			if (s.getLinkedList().equals(sList.get(i).getLinkedList())) {
				// System.out.println(n + " " + i);
				// System.out.print(snake.get(i).getLinkedList().getFirst().x);
				// System.out.print(" " + snake.get(n).getSnakeHead().x);
				// System.out.println("leap itself");
				continue;
			}
			for (Point sp : sList.get(i).getLinkedList()) {
				if (s.getSnakeHead().equals(sp)) {
					System.out.println("eat oppo");
					return true;
				}
			}

		}
		return false;
	}

	public boolean isEatOpponent(int n) {
		// System.out.println("is eat oppo");
		return isEatOpponent(snake.get(n), snake);
	}

	public boolean isFoodOverlap() {
		// System.out.println("isfoodoverlap");
		// entered
		for (Snake s : snake) {
			for (int i = 0; i < s.getLength(); i++) {

				if (food.equals(s.getNPoint(i)))
					return true;
			}
		}
		for (int i = 0; i < poison.getSize(); i++) {
			// System.out.print(food.x-poison.getNPoint(i).x);System.out.print(food.y-poison.getNPoint(i).y);
			if (food.equals(poison.getNPoint(i)))
				return true;
		}
		// System.out.println("not food overlap");
		return false;
	}

	public boolean isGameOver() {
		if (gamemode != 2) {
			for (Snake s : snake) {
				if (!s.getLife())
					return true;
			}
		} else {
			if (!snake.get(2).getLife())
				return true;
			else if (!snake.get(0).getLife() && !snake.get(1).getLife()) {
				return true;
			}
		}
		return false;
	}

	public void snakeDie(int n) {
		snake.get(n).die(n);
		if (difficulty == 3) {
			snake.get(n).initialSnake(n, difficulty);
			snake.get(n).revive();
		}
	}

	public void afterMove(int n) {
		// System.out.println("aftermove");
		if (isEatOpponent(n))
			// game over
			// System.out.println("eatoppo");
			snakeDie(n);
		if (isSnakeEatPoison(n))
			// game over
			// System.out.println("eat poinson");
			snakeDie(n);
		if (isSnakeEatItself(n))
			// game over
			// System.out.println("eat itself");
			snakeDie(n);

		if (isEateFood(snake, n)) {
			// System.out.println("eatfood!");
			snake.get(n).eatFood(difficulty, n);
			while (true) {
				this.food.creatFood();
				if (!isFoodOverlap())
					break;
			}
		}

		if (isEatSpecial(n)) {
			// System.out.println("eatiron");
			snake.get(n).eatSpecial();

			// if(isZero(n))
			// snake[n].die(n);
		}

	}

	public void gameOver() {
		if (gamemode == 1) {
			if (!snake.get(0).getLife()) {
				if (!snake.get(1).getLife())
					JOptionPane.showMessageDialog(null, "ƽ�֣�");
				else {
					JOptionPane.showMessageDialog(null, "���߻�ʤ��");
				}
			} else if (!snake.get(1).getLife())
				JOptionPane.showMessageDialog(null, "���߻�ʤ��");
		} else if (gamemode == 0) {
			JOptionPane.showMessageDialog(null, "��Ϸ������");
		} else if (gamemode == 2) {
			if (!snake.get(2).getLife()) {
				if (!snake.get(0).getLife() && !snake.get(1).getLife()) {
					JOptionPane.showMessageDialog(null, "ƽ�֣�");
				} else
					JOptionPane.showMessageDialog(null, "ũ���ʤ��");
			} else {
				JOptionPane.showMessageDialog(null, "������ʤ��");
			}
		}
	}

	// ��ʵ��������ķ�����������ʵ������Ϸ����
	// public void newGame(){
	// System.out.println("Controller.newGame");
	// }
	public static void speedDown() {
		if (speed <= 400)
			speed += 20;
	}

	public static void speedUp() {
		if (speed >= 50)
			speed -= 20;
	}

	public static void setSpeed(int n) {
		switch (n) {
		case 0:
			speed = 100;
			break;
		case 1:
			speed = 150;
			break;
		case 2:
			speed = 300;
			break;
		default:
			break;
		}
	}

	public int chooseSpeed() {
		Object[] options = { "����", "����", "����" };
		int n = JOptionPane.showOptionDialog(null, "��ѡ����Ϸ�ٶ�", "��Ϸ�ٶ�",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
				null, options, options[1]);
		return n;
	}

	public boolean ifRetry() {
		String[] options = { "�Ҳ�����", "����!" };
		int a = JOptionPane.showOptionDialog(null, "��Ϸ��������������", "̰����˫�˰�",
				JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				options, options[0]);
		return a == 0;
	}

	public void controllerClear() {
		snake.clear();
		poison.clear();
		specialFood.clear();
	}

	public void retry() {
		// for(Snake s: snake){
		// s.clear();
		// }
		replay = false;
		controllerClear();
		newGame();
	}

	public int chooseGameMode() {
		String[] options = { "������Ϸ", "˫����Ϸ", "������", "�Ķ�����" };
		return JOptionPane.showOptionDialog(null, "��ӭ����̰���߶��˰棡", "̰���߶��˰�",
				JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				options, options[0]);
	}

	public int chooseGameDifficulty() {

		String[] dif = { "������", "���ѵ�", "IMBA", "�޾�ģʽ" };
		return JOptionPane.showOptionDialog(null, "��ѡ����Ϸ�Ѷ�", "̰���߶��˰�",
				JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				dif, dif[0]);
	}

	public void setGameParameters() {
		setGameMode(0);
		boolean b = false;
		// int c;
		for (; !b;) {
			switch (chooseGameMode()) {
			// ˫��
			case 1:
				// System.out.println("c="+c);
				setDifficulty(chooseGameDifficulty());
				setGameMode(1);
				// setSpeed(chooseSpeed());
				// JOptionPane.showMessageDialog(null, "׼��������");
				b = true;
				break;
			// ����
			case 0:
				setDifficulty(chooseGameDifficulty());
				setGameMode(0);
				b = true;
				break;
			// ����
			case 2:
				setDifficulty(chooseGameDifficulty());
				setGameMode(2);
				b = true;
				break;

			case 3:
				JOptionPane.showMessageDialog(null, help);
				break;

			default:
				JOptionPane.showMessageDialog(null, help);
				break;
			}
		}
	}

	public void newGame() {
		// System.out.println("new game");
		gameStatus = false;

		gamePanel.setVisible(false);
		windowPanel.reSet();
		// gameFrame.repaint();
		while (true) {
			try {
				Thread.sleep(10);
			} catch (Exception e) {
				// TODO: handle exception
			}
			if (getGameStatus()) {
				gamePanel.setVisible(true);
				run();
			}
		}
	}

	public void run() {
		controllerClear();
		setGameStatus(true);
		poison.creatpoison(getDifficulty());
		createSnake();
		int i = 0, j = 0;
		System.out.println("init snake");
		for (Snake s : snake) {
			s.initialSnake(i++, getDifficulty());
		}
		// ����ʳ��
		while (true) {
			this.food.creatFood();
			if (!isFoodOverlap()) {
				break;
			}
		}
		// ��̬ģʽ�´�������ʳ��
		if (difficulty == 2 || difficulty == 3) {
			int m = 0;
			while (m < 2) {
				Point point;
				point = specialFood.creatspecialFood();
				if (!(isSpecialOverLap(point))) {
					specialFood.getLinkedList().add(point);
					// System.out.println("addiron");
					m++;
				}
			}
		}
		// ������Ϸ
		for (; gameStatus;) {
			// �˹�����ȷ���ߵ��ƶ�����
			artSnakeMove();
			// �Ƿ���ͣ
			if (!MoveListener.getIsStop()) {
				for (Snake s : snake) {
					s.move();
				}
				j = 0;
				for (Snake s : snake) {
					afterMove(j++);
				}
				this.gamePanel.reDisplay();
			}

			// �Ƿ�λ
			if (replay) {
				retry();
			}
			// �Ƿ���Ϸ����
			if (isGameOver() && gameStatus) {
				// System.out.println(snake[0].getLife()?1:0);
				// System.out.println(snake[1].getLife()?1:0);
				gameOver();
				setGameStatus(false);

				if (!ifRetry()) {
					retry();
				} else {
					run();
				}
			}
			// �����߳�
			try {
				Thread.sleep(speed);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void artEatFood(Snake s) {
		int fx = food.x;
		int fy = food.y;
		int sx = s.getSnakeHead().x;
		int sy = s.getSnakeHead().y;
		if (sx < fx) {
			if (sy < fy) {
				switch (s.getOldDirection()) {
				case Snake.UP:
					s.changeDirection(Snake.RIGHT);
					break;
				case Snake.DOWN:
					s.changeDirection(Snake.RIGHT);
					break;
				case Snake.LEFT:
					s.changeDirection(Snake.DOWN);
					break;
				case Snake.RIGHT:
					s.changeDirection(Snake.RIGHT);
					break;
				default:
					break;
				}
			} else if (sy > fy) {
				switch (s.getOldDirection()) {
				case Snake.UP:
					s.changeDirection(Snake.RIGHT);
					break;
				case Snake.DOWN:
					s.changeDirection(Snake.RIGHT);
					break;
				case Snake.LEFT:
					s.changeDirection(Snake.UP);
					break;
				case Snake.RIGHT:
					s.changeDirection(Snake.RIGHT);
					break;
				default:
					break;
				}
			} else if (sy == fy) {
				switch (s.getOldDirection()) {
				case Snake.UP:
					s.changeDirection(Snake.RIGHT);
					break;
				case Snake.DOWN:
					s.changeDirection(Snake.RIGHT);
					break;
				case Snake.LEFT:
					s.changeDirection(Snake.UP);
					break;
				case Snake.RIGHT:
					s.changeDirection(Snake.RIGHT);
					break;
				default:
					break;
				}
			}

		} else if (sx > fx) {
			if (sy < fy) {
				switch (s.getOldDirection()) {
				case Snake.UP:
					s.changeDirection(Snake.LEFT);
					break;
				case Snake.DOWN:
					s.changeDirection(Snake.LEFT);
					System.out.println("down to left");
					break;
				case Snake.LEFT:
					s.changeDirection(Snake.LEFT);
					break;
				case Snake.RIGHT:
					s.changeDirection(Snake.DOWN);
					break;
				default:
					break;
				}
			} else if (sy > fy) {
				switch (s.getOldDirection()) {
				case Snake.UP:
					s.changeDirection(Snake.LEFT);
					break;
				case Snake.DOWN:
					s.changeDirection(Snake.LEFT);
					break;
				case Snake.LEFT:
					s.changeDirection(Snake.LEFT);
					break;
				case Snake.RIGHT:
					s.changeDirection(Snake.UP);
					break;
				default:
					break;
				}
			} else if (sy == fy) {
				switch (s.getOldDirection()) {
				case Snake.UP:
					s.changeDirection(Snake.LEFT);
					break;
				case Snake.DOWN:
					s.changeDirection(Snake.LEFT);
					break;
				case Snake.LEFT:
					s.changeDirection(Snake.LEFT);
					break;
				case Snake.RIGHT:
					s.changeDirection(Snake.UP);
					break;
				default:
					break;
				}
			}
		} else if (sx == fx) {
			if (sy < fy) {
				switch (s.getOldDirection()) {
				case Snake.UP:
					s.changeDirection(Snake.RIGHT);
					break;
				case Snake.DOWN:
					s.changeDirection(Snake.DOWN);
					break;
				case Snake.LEFT:
					s.changeDirection(Snake.DOWN);
					break;
				case Snake.RIGHT:
					s.changeDirection(Snake.DOWN);
					break;
				default:
					break;
				}
			} else if (sy > fy) {
				switch (s.getOldDirection()) {
				case Snake.UP:
					s.changeDirection(Snake.UP);
					break;
				case Snake.DOWN:
					s.changeDirection(Snake.UP);
					break;
				case Snake.LEFT:
					s.changeDirection(Snake.UP);
					break;
				case Snake.RIGHT:
					s.changeDirection(Snake.RIGHT);
					break;
				default:
					break;
				}
			} else if (sy == fy) {
				/*
				 * switch (s.getOldDirection()) { case Snake.UP:
				 * s.changeDirection(Snake.RIGHT); break; case Snake.DOWN:
				 * s.changeDirection(Snake.RIGHT); case Snake.LEFT:
				 * s.changeDirection(Snake.UP); case Snake.RIGHT:
				 * s.changeDirection(Snake.RIGHT); default: break; }
				 */
			}
		}
		/*
		 * if (sx < fx) { if (s.getOldDirection() == Snake.UP ||
		 * s.getOldDirection() == Snake.DOWN) { s.changeDirection(Snake.RIGHT);
		 * } else { if (sy < fy) { s.changeDirection(Snake.DOWN); } else if (sy
		 * > fy) { s.changeDirection(Snake.UP); }else if(sy==fy){
		 * 
		 * } } } else if (sx > fx) { if (s.getOldDirection() == Snake.UP ||
		 * s.getOldDirection() == Snake.DOWN) { s.changeDirection(Snake.LEFT); }
		 * else { if (sy < fy) { s.changeDirection(Snake.DOWN); } else if (sy >
		 * fy) { s.changeDirection(Snake.UP); } } }
		 */

		/*
		 * if (s.getSnakeHead().x > x) { System.out.println("to left");
		 * s.changeDirection(Snake.LEFT); } else if (s.getSnakeHead().x < x) {
		 * System.out.println("to right"); s.changeDirection(Snake.RIGHT); } if
		 * (s.getSnakeHead().y > y) { System.out.println("to up");
		 * s.changeDirection(Snake.UP); } else if (s.getSnakeHead().y < y) {
		 * System.out.println("to down"); s.changeDirection(Snake.DOWN); }
		 */
	}

	public void artSnakesEatFood() {
		// System.out.println("art snakes eat food");
		for (Snake s : snake) {
			if (!s.isPlayer()) {
				artEatFood(s);
			}
		}
	}

	private void artSnakeMove() {
		// TODO Auto-generated method stub
		boolean eatFood = true;
		boolean ranMove = false;

		if (Math.random() * 5 < 4) {
			eatFood = true;
			ranMove = false;
		} else {
			eatFood = false;
			ranMove = true;
		}
		if (eatFood == true) {
			artSnakesEatFood();
		} else {
			compRandomMove();
		}

		compDodge();
	}

	private void compRandomMove() {
		// TODO Auto-generated method stub
		System.out.println("randMove");
		int a;
		for (Snake s : snake) {
			if (!s.isPlayer()) {
				a = (int) (Math.random() * 11);
				switch (a) {
				case 0:
					s.setNewDirection(Snake.UP);
					break;
				case 1:
					s.setNewDirection(Snake.DOWN);
					break;
				case 2:
					s.setNewDirection(Snake.LEFT);
					break;
				case 3:
					s.setNewDirection(Snake.RIGHT);
					break;
				default:

					break;
				}
			}
		}
	}

	// ���Ե����ܹ���
	public void compDodge() {
		for (Snake s : snake) {
			if (!s.isPlayer()) {
				// ���任�Ĵη���

				turnDirWhenIfDanger(s);

			}
		}
	}

	private void turnDirWhenIfDanger(Snake s) {
		Snake tempSnake = new Snake();
		LinkedList<Snake> tempSnakeList = new LinkedList<Snake>();
		for (int i = 1; i < 2; i++) {
			tempSnake = copyOfSnakeAfterSteps(s, i);
			tempSnakeList = copyOfSnakeListAfterSteps(snake, i);
			if (!isNoDanger(tempSnake, tempSnakeList)) {

				calcuChangeDir(s);

			}
		}
	}

	public int distanceToDanger(int newDirection, Snake s) {
		Point shp = new Point();
		shp.x = s.getSnakeHead().x;
		shp.y = s.getSnakeHead().y;
		for (int i = 1; i < 5; i++) {
			switch (newDirection) {
			case Snake.UP:
				shp.y--;
				break;
			case Snake.DOWN:
				shp.y++;
				break;
			case Snake.LEFT:
				shp.x--;
				break;
			case Snake.RIGHT:
				shp.x++;
				break;

			default:
				break;
			}
			if (isPointDanger(s, shp)) {
				System.out.println("distance to danger " + i);
				return i;
			}
		}
		System.out.println("distance to danger 5");
		return 5;
	}

	// TODO Auto-generated method stub
	private void calcuChangeDir(Snake s) {
		if (s.getOldDirection() + s.getNewDirection() == 0) {
			s.setNewDirection(s.getOldDirection());
		}

		switch (s.getOldDirection()) {
		case Snake.UP:
			switch (s.getNewDirection()) {
			case Snake.UP:
				if (distanceToDanger(Snake.LEFT, s) > distanceToDanger(
						Snake.RIGHT, s)) {
					s.changeDirection(Snake.LEFT);
				} else {
					s.changeDirection(Snake.RIGHT);
				}
				break;
			case Snake.DOWN:

				break;
			case Snake.LEFT:
				if (distanceToDanger(Snake.UP, s) > distanceToDanger(
						Snake.RIGHT, s)) {
					s.changeDirection(Snake.UP);
				} else {
					s.changeDirection(Snake.RIGHT);
				}
				break;
			case Snake.RIGHT:
				if (distanceToDanger(Snake.LEFT, s) > distanceToDanger(
						Snake.UP, s)) {
					s.changeDirection(Snake.LEFT);
				} else {
					s.changeDirection(Snake.UP);
				}
				break;

			default:
				break;
			}
			break;
		case Snake.DOWN:
			switch (s.getNewDirection()) {
			case Snake.UP:

				break;
			case Snake.DOWN:
				if (distanceToDanger(Snake.LEFT, s) > distanceToDanger(
						Snake.RIGHT, s)) {
					s.changeDirection(Snake.LEFT);
				} else {
					s.changeDirection(Snake.RIGHT);
				}
				break;
			case Snake.LEFT:
				if (distanceToDanger(Snake.RIGHT, s) > distanceToDanger(
						Snake.DOWN, s)) {
					s.changeDirection(Snake.RIGHT);
				} else {
					s.changeDirection(Snake.DOWN);
				}
				break;
			case Snake.RIGHT:
				if (distanceToDanger(Snake.LEFT, s) > distanceToDanger(
						Snake.DOWN, s)) {
					s.changeDirection(Snake.LEFT);
				} else {
					s.changeDirection(Snake.DOWN);
				}
				break;

			default:
				break;
			}

			break;
		case Snake.LEFT:
			switch (s.getNewDirection()) {
			case Snake.UP:
				if (distanceToDanger(Snake.LEFT, s) > distanceToDanger(
						Snake.DOWN, s)) {
					s.changeDirection(Snake.LEFT);
				} else {
					s.changeDirection(Snake.DOWN);
				}
				break;
			case Snake.DOWN:
				if (distanceToDanger(Snake.LEFT, s) > distanceToDanger(
						Snake.UP, s)) {
					s.changeDirection(Snake.LEFT);
				} else {
					s.changeDirection(Snake.UP);
				}
				break;
			case Snake.LEFT:
				if (distanceToDanger(Snake.DOWN, s) > distanceToDanger(
						Snake.UP, s)) {
					s.changeDirection(Snake.DOWN);
				} else {
					s.changeDirection(Snake.UP);
				}
				break;
			case Snake.RIGHT:

				break;

			default:
				break;
			}

			break;
		case Snake.RIGHT:
			switch (s.getNewDirection()) {
			case Snake.UP:
				if (distanceToDanger(Snake.RIGHT, s) > distanceToDanger(
						Snake.DOWN, s)) {
					s.changeDirection(Snake.RIGHT);
				} else {
					s.changeDirection(Snake.DOWN);
				}
				break;
			case Snake.DOWN:
				if (distanceToDanger(Snake.RIGHT, s) > distanceToDanger(
						Snake.UP, s)) {
					s.changeDirection(Snake.RIGHT);
				} else {
					s.changeDirection(Snake.UP);
				}
				break;
			case Snake.LEFT:

				break;
			case Snake.RIGHT:
				if (distanceToDanger(Snake.DOWN, s) > distanceToDanger(
						Snake.UP, s)) {
					s.changeDirection(Snake.DOWN);
				} else {
					s.changeDirection(Snake.UP);
				}
				break;

			default:
				break;
			}

			break;

		default:
			break;
		}

	}

	/***
	 * �ж�ĳ���Ƿ���Σ��
	 * 
	 * @return
	 */

	public boolean isPointDanger(Snake s, Point p) {
		if (isPointPoison(p)) {
			return true;
		}
		if (isPointOpponent(p, s)) {
			return true;
		}
		if (isPointSelf(p, s)) {
			return true;
		}
		return false;
	}

	private boolean isPointSelf(Point p, Snake s) {
		// TODO Auto-generated method stub
		for (Point pointOfSelf : s.getLinkedList()) {
			if (!pointOfSelf.equals(s.getSnakeHead())) {
				if (pointOfSelf.equals(p)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean isPointOpponent(Point p, Snake s) {
		// TODO Auto-generated method stub
		for (Snake a : snake) {
			if (!(a == s)) {
				for (Point pointOfOppo : a.getLinkedList()) {
					if (p.equals(pointOfOppo)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private boolean isPointPoison(Point p) {
		// TODO Auto-generated method stub
		for (Point poisonPoint : poison.getLinkedList()) {
			if (p.x == poisonPoint.x && p.y == poisonPoint.y) {
				return true;
			}
		}
		return false;
	}

	/***
	 * ����n���Ժ�Snake����ĸ���
	 * 
	 * @param s
	 * @param steps
	 * @return
	 */

	public Snake copyOfSnakeAfterSteps(Snake s, int steps) {
		Snake snake = s.clone();
		snake.compulsoryMove(steps);
		return snake;
	}

	/***
	 * ����n���Ժ�Snake���������ĸ���
	 * 
	 * @param snake
	 * @param steps
	 * @return
	 */
	public LinkedList<Snake> copyOfSnakeListAfterSteps(LinkedList<Snake> snake,
			int steps) {
		LinkedList<Snake> ls = new LinkedList<Snake>();
		Snake tempSnake = new Snake();
		for (Snake s : snake) {
			tempSnake = s.clone();
			tempSnake.compulsoryMove(steps);
			ls.add(tempSnake);
		}
		return ls;
	}

	private boolean isNoDanger(Snake testSnake, LinkedList<Snake> testSnakeList) {

		if (isSnakeEatItself(testSnake)
				|| isEatOpponent(testSnake, testSnakeList)
				|| isSnakeEatPoison(testSnake)) {
			System.out.println("in danger");
			return false;
		}

		System.out.println("no danger");
		return true;
	}

	private void setGameMode(int i) {
		// TODO Auto-generated method stub
		gamemode = i;
	}

	public boolean isSnakeEatPoison(Snake s) {
		for (int i = 0; i < poison.getSize(); i++) {
			if (s.getSnakeHead().equals(poison.getNPoint(i)))
				return true;
		}
		return false;
	}

	public boolean isSnakeEatPoison(int n) {
		// System.out.println("is eat poison");
		return isSnakeEatPoison(snake.get(n));
	}

	public boolean isSnakeEatItself(Snake s) {
		// System.out.println("is eat itself");
		for (int i = 1; i < s.getLength(); i++) {
			if (s.getSnakeHead().equals(s.getNPoint(i)))
				return true;
		}
		return false;
	}

	public boolean isSnakeEatItself(int n) {
		return isSnakeEatItself(snake.get(n));
	}

	public boolean isSpecialOverLap(Point p) {

		for (Snake s : snake) {
			for (int i = 0; i < s.getLength(); i++) {

				if (p.equals(s.getNPoint(i)))
					return true;
			}
		}

		for (int i = 0; i < poison.getSize(); i++) {
			// System.out.print(food.x-poison.getNPoint(i).x);System.out.print(food.y-poison.getNPoint(i).y);
			if (p.equals(poison.getNPoint(i)))
				return true;
		}

		if (food.equals(p))
			return true;
		// System.out.println("not iron overlap");
		return false;
	}

	public boolean isEatSpecial(int n) {
		Point point = new Point();
		for (int i = 0; i < specialFood.getLinkedList().size(); i++) {
			if (snake.get(n).getSnakeHead()
					.equals(specialFood.getLinkedList().get(i))) {
				specialFood.getLinkedList().remove(i);
				while (true) {
					if (!(isSpecialOverLap(point = specialFood
							.creatspecialFood()))) {
						specialFood.getLinkedList().add(i, point);
						break;
					}
				}
				return true;
			}
		}
		return false;
	}

	public boolean isZero(int n) {
		// System.out.println(n + "body zero");
		return snake.get(n).getLength() == 1;
	}

	public void createSnake() {
		switch (gamemode) {
		case 0:
			snake.add(new Snake());
			snake.get(0).setPlayer(isPlayerList[0]);
			break;
		case 1:
			snake.add(new Snake());
			snake.add(new Snake());
			snake.get(0).setPlayer(isPlayerList[0]);
			snake.get(1).setPlayer(isPlayerList[1]);
			break;
		case 2:
			snake.add(new Snake());
			snake.add(new Snake());
			snake.add(new Snake());
			snake.get(0).setPlayer(isPlayerList[0]);
			snake.get(1).setPlayer(isPlayerList[1]);
			snake.get(2).setPlayer(isPlayerList[2]);
			break;
		default:
			break;
		}
	}

	public void setGameReplay(boolean b) {
		// TODO Auto-generated method stub
		replay = b;

	}
}
