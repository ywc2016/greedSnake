import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class GameFrame extends JFrame {
	GameFrame() {

	}

	// private Snake snake1 = new Snake();
	// private Snake snake2 = new Snake();
	private LinkedList<Snake> snake;
	private Food food = new Food();
	private Poison poison = new Poison();
	private JLabel label = new JLabel();

	GameFrame(LinkedList<Snake> snake, Food food, Poison poison) {
		super("Greed Snake For Multiplayers");
		this.snake = snake;
		this.food = food;
		this.poison = poison;
		this.setSize(Parameters.WINDOW_LENGTH * Parameters.REC_LENGTH + 250,
				Parameters.WINDOW_WIDTH * Parameters.REC_WIDTH + 40);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		// private Graphic g = this.getGraphics;
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		// setBackGround();
		// ���ñ���
		// super("JFram���ñ���ͼƬ(Cannel_2020)");
		// ���ô�С
		// setSize(500, 400);
		// ����λ��
		// setLocation(200, 50);
		// ����ͼƬ��·���������·�����߾���·��������ͼƬ����"java��Ŀ��"���ļ��£�

	}

	public void setBackGround() {
		// TODO Auto-generated method stub
		this.getLayeredPane().remove(label);
		String path = null;
		switch (Controller.difficulty) {
		case 0:
			path = "data/2.jpg";
			break;
		case 1:
			path = "data/3.jpg";
			break;
		case 2:
			path = "data/4.jpg";
			break;
		default:
			break;
		}
		// ����ͼƬ
		ImageIcon background = new ImageIcon(path);
		// �ѱ���ͼƬ��ʾ��һ����ǩ����
		label = new JLabel(background);
		// �ѱ�ǩ�Ĵ�Сλ������ΪͼƬ�պ�����������
		label.setBounds(0, 0, this.getWidth(), this.getHeight());
		// �����ݴ���ת��ΪJPanel���������÷���setOpaque()��ʹ���ݴ���͸��
		JPanel imagePanel = (JPanel) this.getContentPane();
		imagePanel.setOpaque(false);
		// �ѱ���ͼƬ��ӵ��ֲ㴰�����ײ���Ϊ����
		this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
		// ���ÿɼ�
		// setVisible(true);
		// ��رհ�ťʱ�˳�
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
