import java.awt.RenderingHints.Key;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

public class MoveListener implements KeyListener {
	private LinkedList<Snake> snake;
	private static boolean isStop = false;
	private Controller controller;

	public MoveListener(LinkedList<Snake> snake) {
		// TODO Auto-generated constructor stub
		this.snake = snake;
	}

	public static boolean getIsStop() {
		return isStop;
	}

	public void backSpacePressed() {
		System.out.println("backspace pressed");
		isStop = !isStop;
	}
	
	public void keyPressed(KeyEvent e) {
		System.out.println("Controller.KeyPressed");
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			this.snake.get(0).changeDirection(Snake.UP);
			break;
		case KeyEvent.VK_DOWN:
			this.snake.get(0).changeDirection(Snake.DOWN);
			break;
		case KeyEvent.VK_LEFT:
			this.snake.get(0).changeDirection(Snake.LEFT);
			break;
		case KeyEvent.VK_RIGHT:
			this.snake.get(0).changeDirection(Snake.RIGHT);
			break;
		case KeyEvent.VK_W:
			this.snake.get(1).changeDirection(Snake.UP);
			break;
		case KeyEvent.VK_S:
			this.snake.get(1).changeDirection(Snake.DOWN);
			break;
		case KeyEvent.VK_A:
			this.snake.get(1).changeDirection(Snake.LEFT);
			break;
		case KeyEvent.VK_D:
			this.snake.get(1).changeDirection(Snake.RIGHT);
			break;
		case KeyEvent.VK_I:
			this.snake.get(2).changeDirection(Snake.UP);
			break;
		case KeyEvent.VK_K:
			this.snake.get(2).changeDirection(Snake.DOWN);
			break;
		case KeyEvent.VK_J:
			this.snake.get(2).changeDirection(Snake.LEFT);
			break;
		case KeyEvent.VK_L:
			this.snake.get(2).changeDirection(Snake.RIGHT);
			break;
		case KeyEvent.VK_SPACE:
		case KeyEvent.VK_P:
			backSpacePressed();
			break;
		case KeyEvent.VK_OPEN_BRACKET:
			Controller.speedUp();
			break;
		case KeyEvent.VK_CLOSE_BRACKET:
			Controller.speedDown();
			break;
		case KeyEvent.VK_F1:
			// controller.controllerClear();
			controller.setGameReplay(true);
			// controller.retry();
			// controller.newGame();
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void setController(Controller controller) {
		// TODO Auto-generated method stub
		this.controller = controller;
	}

}
