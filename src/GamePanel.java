import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
	// private Snake snake1 = new Snake();
	// private Snake snake2 = new Snake();
	private LinkedList<Snake> snake;
	private Food food = new Food();
	private Poison poison = new Poison();
	private SpecialFood specialFood = new SpecialFood();

	public GamePanel(LinkedList<Snake> snake, Food food, Poison poison,
			SpecialFood specialFood) {
		this.snake = snake;
		this.food = food;
		this.poison = poison;
		this.specialFood = specialFood;
		setSize(Parameters.WINDOW_LENGTH * Parameters.REC_LENGTH + 250,
				Parameters.WINDOW_WIDTH * Parameters.REC_WIDTH + 40);
		this.setOpaque(false);
		this.setVisible(false);
		this.setLayout(null);
		// 加入记分牌
		ScorePanel scorePanel = new ScorePanel();
		this.add(scorePanel, BorderLayout.EAST);
		scorePanel.setSize(250, Parameters.WINDOW_WIDTH * Parameters.REC_WIDTH);
		scorePanel.setVisible(true);
		// scorePanel.setOpaque(false);
		scorePanel.setBackground(Color.black);
		scorePanel.setLocation(
				Parameters.WINDOW_LENGTH * Parameters.REC_LENGTH, 0);
		// scorePanel.add(new Button("记分牌"));
		scorePanel.setBorder(BorderFactory.createTitledBorder("分组框")); // 设置面板边框，实现分组框的效果，此句代码为关键代码

		scorePanel.setBorder(BorderFactory.createLineBorder(Color.green));// 设置面板边框颜色
	}

	public void reDisplay() {
		// System.out.println("GamePanel.reDisplay");

		this.repaint();
	}

	public void paintWhenStrat(Graphics g) {
		// System.out.println("paintWhenStrat");
		int i = 1;
		if (snake.size() != 0) {
			for (Snake s : snake) {
				s.showMe(g, i++);
			}
		}
		food.showMe(g);
		poison.showMe(g);
		specialFood.showMe(g);
	}

	protected void paintComponent(Graphics g) {
		// System.out.println("GamePanel.paintComponent");

		super.paintComponent(g);
		// if(!Controller.getGameStatus()){
		// Image

		// }
		// else {
		paintWhenStrat(g);
		// }

	}

	class ScorePanel extends JPanel {

		private String playerOne = "palyer1(red)";
		private String playerTwo = "player2(blue)";
		private String playerThree = "player3(yellow)";
		private String[] str = { playerOne, playerTwo, playerThree };

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);// 这里不能省略，否则不能显示背景颜色
			// TODO Auto-generated method stub
			g.setFont(new Font("Tahoma", Font.BOLD, 20));
			g.setColor(Color.blue);
			int x = 5, y = 30;
			int i = 0, j = 0;
			for (Snake s : snake) {
				g.drawString(
						str[i++] + ":"
								+ String.valueOf(snake.get(j++).getScore()), x,
						y);
				y += 100;
			}

		}

	}

}
