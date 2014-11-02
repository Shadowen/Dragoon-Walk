import javax.swing.JFrame;

public class MainFrame extends JFrame {

	public static void main(String[] args) {
		new MainFrame();
	}

	private DrawPanel dp;

	public MainFrame() {
		setSize(640, 480);
		setLocationRelativeTo(null);
		dp = new DrawPanel();
		setContentPane(dp);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
}
