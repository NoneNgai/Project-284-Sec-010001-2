import java.awt.Toolkit;
import javax.swing.JFrame;

public class UIFrame extends JFrame {

	private MainPanel cp = new MainPanel();
	
	public UIFrame() {
		this.add(cp);
		this.setSize(cp.getSize());
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - this.getSize().width)/2,(Toolkit.getDefaultToolkit().getScreenSize().height - this.getSize().height)/2);
	}
	
	public static void main(String[] args) {
		UIFrame UI = new UIFrame();
	}

}
