import java.util.LinkedList;

public class GreedSnake {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpecialFood specialFood = new SpecialFood();
		// specialFood.clear();
		// Snake snake1 = new Snake();
		// Snake snake2 = new Snake();
		LinkedList<Snake> snake = new LinkedList<Snake>();
		// Snake s=new Snake();
		// snake.add(s);
		Food food = new Food();
		Poison poison = new Poison();
		GamePanel gamepanel = new GamePanel(snake, food, poison, specialFood);
		GameFrame gameframe = new GameFrame(snake, food, poison);

		gamepanel.setOpaque(false);

		MoveListener moveListener = new MoveListener(snake);
		gameframe.addKeyListener(moveListener);
		gameframe.setFocusable(true);
		// ButtonListener buttonListener = new ButtonListener();
		WindowPanel windowPanel = new WindowPanel(snake);
		gameframe.add(windowPanel);
		gameframe.add(gamepanel);
		// gameframe.repaint();
		Controller controller = new Controller(snake, food, poison, gameframe,
				gamepanel, specialFood, windowPanel);
		moveListener.setController(controller);
		controller.newGame();
	}

}
