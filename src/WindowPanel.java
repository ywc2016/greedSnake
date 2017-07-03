import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.LinkedList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

public class WindowPanel extends JPanel implements ActionListener {

	private boolean isChanged = false;
	private ActionListener actionListener;
	private LinkedList<Snake> snake;
	JButton button1 = new JButton("单人游戏");
	JButton button2 = new JButton("双人游戏");
	JButton button3 = new JButton("斗地主");
	JButton button4 = new JButton("阅读帮助");
	JButton button = new JButton("欢迎来到贪吃蛇多人版！\n请选择游戏人数");

	WindowPanel(LinkedList<Snake> snake) {
		// this.actionListener = actionListener;
		this.snake = snake;
		this.add(button);
		button.setBounds(40, 0, 300, 40);
		button.setHorizontalTextPosition(SwingConstants.CENTER);
		this.add(button1);
		button1.setBounds(40, 40, 300, 40);
		this.add(button2);
		button2.setBounds(40, 80, 300, 40);
		this.add(button3);
		button3.setBounds(40, 120, 300, 40);
		this.add(button4);
		button4.setBounds(40, 160, 300, 40);
		button.addActionListener(this);
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		button4.addActionListener(this);
		this.setLocation(200, 30);
		this.setLayout(null);
		button.setBackground(Color.green);
		// setLocation(0, 0);
		setSize(Parameters.WINDOW_LENGTH * Parameters.REC_LENGTH,
				Parameters.WINDOW_WIDTH * Parameters.REC_WIDTH + 70);
		this.setOpaque(false);
		this.setVisible(true);
		// this.setLocationRelativeTo(null);

		// repaint();
		PlayerCheckBoxPanel playerCheckBoxPanel = new PlayerCheckBoxPanel();
		this.add(playerCheckBoxPanel);
		playerCheckBoxPanel.setBounds(40, 240, 300, 80);

	}

	public void change() {
		button1.setText("简单模式");
		button2.setText("困难模式");
		button3.setText("IMBA模式");
		button4.setText("无尽模式");
		button.setText("上一步");
		isChanged = true;
	}

	public boolean isChanged() {
		return isChanged;
	}

	public void setChanged(boolean isChanged) {
		this.isChanged = isChanged;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// TODO Auto-generated method stub
		if (e.getSource() == button) {
			// System.exit(0);
			reSet();
		}
		if (e.getSource() == button1) {
			if (!isChanged) {
				// System.exit(0);
				Controller.gamemode = 0;
				change();

				// this.setVisible(false);
			} else {
				Controller.difficulty = 0;
				this.setVisible(false);
				Controller.gameStatus = true;
				// reSet();
			}
		}

		if (e.getSource() == button2) {
			if (!isChanged) {
				Controller.gamemode = 1;
				change();
				// this.setVisible(false);
			} else {
				Controller.difficulty = 1;
				this.setVisible(false);
				Controller.gameStatus = true;
			}
		}

		if (e.getSource() == button3) {
			if (!isChanged) {
				Controller.gamemode = 2;
				change();
				// this.setVisible(false);
			} else {
				Controller.difficulty = 2;
				this.setVisible(false);
				Controller.gameStatus = true;
			}
		}
		if (e.getSource() == button4) {
			if (!isChanged)
				JOptionPane.showMessageDialog(null, Controller.help);
			else {
				Controller.difficulty = 3;
				this.setVisible(false);
				Controller.gameStatus = true;
				System.out.println("game true");
			}
		}

	}

	public void reSet() {
		// TODO Auto-generated method stub
		System.out.println("reset");
		this.setVisible(true);
		isChanged = false;
		button1.setText("单人游戏");
		button2.setText("双人游戏");
		button3.setText("斗地主");
		button4.setText("阅读帮助");
		button.setText("欢迎来到贪吃蛇多人版！\n请选择游戏人数");
		repaint();
		System.out.println("reset over");
	}

	class PlayerCheckBoxPanel extends JPanel implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == jrb1) {
				Controller.isPlayerList[0] = true;
				System.out.println("p1 player");
			}
			if (e.getSource() == jrb2) {
				Controller.isPlayerList[0] = false;
				System.out.println("p1 comp");
			}

			if (e.getSource() == jrb3) {
				Controller.isPlayerList[1] = true;
			}
			if (e.getSource() == jrb4) {
				Controller.isPlayerList[1] = false;
			}

			if (e.getSource() == jrb5) {
				Controller.isPlayerList[2] = true;
			}
			if (e.getSource() == jrb6) {
				Controller.isPlayerList[2] = false;
			}

		}

		JLabel jlb1 = new JLabel("玩家1");
		JLabel jlb2 = new JLabel("玩家2");
		JLabel jlb3 = new JLabel("玩家3");
		ButtonGroup bg1 = new ButtonGroup();
		ButtonGroup bg2 = new ButtonGroup();
		ButtonGroup bg3 = new ButtonGroup();
		JRadioButton jrb1 = new JRadioButton("玩家");
		JRadioButton jrb2 = new JRadioButton("电脑", true);
		JRadioButton jrb3 = new JRadioButton("玩家");
		JRadioButton jrb4 = new JRadioButton("电脑", true);
		JRadioButton jrb5 = new JRadioButton("玩家");
		JRadioButton jrb6 = new JRadioButton("电脑", true);

		public PlayerCheckBoxPanel() {
			this.setVisible(true);
			this.setLayout(new GridLayout(3, 2, 30, 20));
			bg1.add(jrb1);
			bg1.add(jrb2);
			bg2.add(jrb3);
			bg2.add(jrb4);
			bg3.add(jrb5);
			bg3.add(jrb6);
			this.add(jlb1);
			this.add(jrb1);
			this.add(jrb2);
			this.add(jlb2);
			this.add(jrb3);
			this.add(jrb4);
			this.add(jlb3);
			this.add(jrb5);
			this.add(jrb6);
			jrb1.addItemListener(this);
			jrb2.addItemListener(this);
			jrb3.addItemListener(this);
			jrb4.addItemListener(this);
			jrb5.addItemListener(this);
			jrb6.addItemListener(this);
		}
	}

}
