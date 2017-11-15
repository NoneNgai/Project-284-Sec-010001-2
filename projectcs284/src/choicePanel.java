
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.awt.BorderLayout;

public class choicePanel extends JPanel {

	private String fileString;

	/**
	 * Create the panel.
	 */
	public choicePanel() {
		this.setSize(new Dimension(650, 492));
		setBackground(MyColor.DARKNAVY.getColor());
		setLayout(null);

		////////////////////////////////////// IMPORTFILE
		////////////////////////////////////// //////////////////////////////////////////////////////////////

		JButton btnImportFile = new JButton(" Import File", new ImageIcon("resource//image//importfile.png"));
		setButton(btnImportFile);
		btnImportFile.setBounds(329, 200, 321, 29);

		btnImportFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					readFile();
				} catch (NumberFormatException | ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		btnImportFile.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent event) {
				highlightButtons(event.getLocationOnScreen(), btnImportFile);
			}

			public void mouseExited(MouseEvent event) {
				btnImportFile.setContentAreaFilled(false);
			}

		});
		add(btnImportFile);

		////////////////////////////////////// SUBJECT
		////////////////////////////////////// //////////////////////////////////////////////////////////////

		JButton btnSubject = new JButton("  Subject", new ImageIcon("resource//image//editfile.png"));
		btnSubject.setBounds(329, 240, 321, 29);
		setButton(btnSubject);
		btnSubject.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent event) {
				highlightButtons(event.getLocationOnScreen(), btnSubject);
			}

			public void mouseExited(MouseEvent event) {
				btnSubject.setContentAreaFilled(false);
			}
		});
		add(btnSubject);

		////////////////////////////////////// FILL SCORE
		////////////////////////////////////// //////////////////////////////////////////////////////////////

		JButton btnFillScore = new JButton("  Fill Score", new ImageIcon("resource//image//editfile.png"));
		btnFillScore.setBounds(329, 280, 321, 29);
		setButton(btnFillScore);
		btnFillScore.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent event) {
				highlightButtons(event.getLocationOnScreen(), btnFillScore);
			}

			public void mouseExited(MouseEvent event) {
				btnFillScore.setContentAreaFilled(false);
			}
		});
		add(btnFillScore);

		////////////////////////////////////// HISTORY
		////////////////////////////////////// //////////////////////////////////////////////////////////////

		JButton btnHistory = new JButton("  History", new ImageIcon("resource//image//history.png"));
		btnHistory.setBounds(329, 320, 321, 29);
		setButton(btnHistory);
		btnHistory.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent event) {
				highlightButtons(event.getLocationOnScreen(), btnHistory);
			}

			public void mouseExited(MouseEvent event) {
				btnHistory.setContentAreaFilled(false);
			}
		});
		add(btnHistory);

		////////////////////////////////////// LOG OUT
		////////////////////////////////////// //////////////////////////////////////////////////////////////

		JButton btnLogOut = new JButton("  Log Out", new ImageIcon("resource//image//logout.png"));
		btnLogOut.setBounds(329, 360, 321, 29);
		setButton(btnLogOut);
		add(btnLogOut);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(MyColor.NAVY.getColor());
		panel.setBounds(0, -12, 328, 504);
		add(panel);

		JLabel lblHello = new JLabel("HELLO");
		lblHello.setForeground(MyColor.WHITEALPHA.getColor());
		lblHello.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblHello.setBounds(105, 318, 120, 65);
		panel.add(lblHello);

		JLabel picLabel = new JLabel(new ImageIcon("resource//image//city2.png"));
		picLabel.setBounds(0, -12, 328, 504);
		panel.add(picLabel);

		JLabel lblOption = new JLabel("OPTION");
		lblOption.setForeground(Color.WHITE);
		lblOption.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblOption.setBounds(385, 100, 209, 46);
		add(lblOption);

	}

	public void setButton(JButton btn) {
		btn.setBorderPainted(false);
		btn.setFocusPainted(false);
		btn.setContentAreaFilled(false);
		btn.setOpaque(false);
		btn.setForeground(MyColor.CORAL.getColor());
		btn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		System.out.println("Maw Meow");
	}

	public void highlightButtons(Point cursor, JButton button) {
		Point buttonLocation = button.getLocationOnScreen();
		double west = buttonLocation.getX();
		double east = buttonLocation.getX() + button.getWidth();
		double north = buttonLocation.getY();
		double south = buttonLocation.getY() + button.getHeight();
		boolean inRow = cursor.getX() > west && cursor.getX() < east;
		boolean inCol = cursor.getY() > north && cursor.getY() < south;
		boolean inBounds = inRow || inCol;
		button.setBackground(inBounds ? MyColor.NAVY.getColor() : null);
		button.setContentAreaFilled(true);
	}

	public void readFile() throws IOException, FileNotFoundException, ClassNotFoundException, NumberFormatException {
		JFileChooser choose = new JFileChooser(".");
		int state = choose.showOpenDialog(null);

		if (state == JFileChooser.APPROVE_OPTION) {

			FileInputStream inFile = new FileInputStream(choose.getSelectedFile());
			InputStreamReader is = new InputStreamReader(inFile, "UTF-8");
			BufferedReader reader = new BufferedReader(is);
			String s = reader.readLine();
			int count =1;
			while (s != null) {
				System.out.println(s);
				s = reader.readLine();
				if(count ==14)
				{
					
				}
				count++;
			}
			System.out.println(count);
			reader.close();
			is.close();
			inFile.close();

		}

	}

	public String getFileString() {
		return fileString;
	}
}
