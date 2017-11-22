/// LINE 539 FILL SCORE
/// LINE 707 FILL CRITERIA 
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.CardLayout;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableModel;
import javax.swing.Icon;
import javax.swing.JSeparator;
import javax.swing.JTable;

import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class MainPanel extends JPanel {
	private final JPanel sidepane = new JPanel();
	private JPanel bgPanelFillScore = new JPanel();
	private JPanel bgPanelImport = new JPanel();
	private JPanel bgPanelSubject = new JPanel();
	private JPanel panelImport, panelFillScore, Subjectpanel;
	private JTextField scoreField;
	private JTextField IDtextField;
	private JTable table, tableFillscore;
	private ReadWriteExcelFile r = new ReadWriteExcelFile();
	private ExamResult exam;
	private JButton btnFillScore;
	private StudentList std;
	private JComboBox comboBox;
	private ExamCriteria ec = new ExamCriteria();
	private String subject,manyquiz;

	public MainPanel() {

		/////// Create Panel and Material

		setSize(new Dimension(1000, 700));
		setLayout(null);
		setBackground(MyColor.GRAY.getColor());
		sidepane.setBackground(MyColor.MIDNIGHTBLUE.getColor());
		sidepane.setBounds(0, 0, 250, 700);
		add(sidepane);
		sidepane.setLayout(null);

		JPanel rightSidePanel = new JPanel();
		rightSidePanel.setBackground(MyColor.GRAY.getColor());
		rightSidePanel.setBounds(250, 88, 750, 614);
		add(rightSidePanel);
		rightSidePanel.setLayout(new CardLayout(0, 0));

		JPanel panelHeader = new JPanel();
		panelHeader.setBackground(MyColor.MIDNIGHTBLUE.getColor());
		panelHeader.setBounds(253, 2, 737, 86);
		add(panelHeader);
		panelHeader.setLayout(new BorderLayout(0, 0));

		JLabel lblheader = new JLabel("Welcome  ");
		panelHeader.add(lblheader, BorderLayout.EAST);
		lblheader.setForeground(MyColor.GRAY.getColor());
		lblheader.setFont(new Font("Segoe UI Light", Font.PLAIN, 40));

		JSeparator separator = new JSeparator();
		panelHeader.add(separator, BorderLayout.SOUTH);
		separator.setBackground(MyColor.CORAL.getColor());
		separator.setForeground(MyColor.CORAL.getColor());

		JLabel lblNavigation = new JLabel("Navigation");
		lblNavigation.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		lblNavigation.setForeground(MyColor.CORAL.getColor());
		lblNavigation.setBounds(10, 134, 127, 27);
		sidepane.add(lblNavigation);

		/////// Button on left-bottom

		JButton btnLogout = new JButton(new ImageIcon("resource//image//logout.png"));
		btnLogout.setBounds(200, 625, 40, 36);
		btnLogout.setContentAreaFilled(false);
		btnLogout.setBorderPainted(false);
		btnLogout.setFocusable(false);
		btnLogout.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent event) {
				highlightButtons(event.getLocationOnScreen(), btnLogout);
			}

			public void mouseExited(MouseEvent event) {
				btnLogout.setContentAreaFilled(false);
			}
		});
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		sidepane.add(btnLogout);

		JButton btnHome = new JButton(new ImageIcon("resource//image//home.png"));
		btnHome.setFocusable(false);
		btnHome.setContentAreaFilled(false);
		btnHome.setBorderPainted(false);
		btnHome.setBounds(155, 625, 40, 36);
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rightSidePanel.removeAll();
				rightSidePanel.repaint();
				rightSidePanel.revalidate();
				lblheader.setText("Welcome  ");
			}
		});
		btnHome.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent event) {
				highlightButtons(event.getLocationOnScreen(), btnHome);
			}

			public void mouseExited(MouseEvent event) {
				btnHome.setContentAreaFilled(false);
			}
		});
		sidepane.add(btnHome);

		/////// Set panel on Right panel

		importPanel();
		fillScorePanel();
		subjectPanel();

		/////// Button on Sidepane

		JButton btnImportFile = new JButton("  Import File", new ImageIcon("resource//image//import.png"));
		btnImportFile.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 15));
		btnImportFile.setBounds(0, 169, 249, 43);
		btnImportFile.setContentAreaFilled(false);
		btnImportFile.setForeground(MyColor.GRAY.getColor());
		btnImportFile.setFocusable(false);
		btnImportFile.setBorderPainted(false);
		btnImportFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rightSidePanel.removeAll();
				rightSidePanel.add(panelImport);
				rightSidePanel.repaint();
				rightSidePanel.revalidate();
				lblheader.setText("Import File  ");
				setBgSidePanel(bgPanelImport, 169);
				removeBgOther(bgPanelImport);
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
		sidepane.add(btnImportFile);

		JButton btnSubject = new JButton("  Subject", new ImageIcon("resource//image//edit.png"));
		btnSubject.setForeground(new Color(219, 227, 229));
		btnSubject.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		btnSubject.setFocusable(false);
		btnSubject.setContentAreaFilled(false);
		btnSubject.setBorderPainted(false);
		btnSubject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rightSidePanel.removeAll();
				rightSidePanel.add(Subjectpanel);
				rightSidePanel.repaint();
				rightSidePanel.revalidate();
				lblheader.setText("Subject  ");
				setBgSidePanel(bgPanelSubject, 227);
				removeBgOther(bgPanelSubject);
			}
		});
		btnSubject.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent event) {
				highlightButtons(event.getLocationOnScreen(), btnSubject);
			}

			public void mouseExited(MouseEvent event) {
				btnSubject.setContentAreaFilled(false);
			}
		});
		btnSubject.setBounds(0, 223, 249, 43);
		sidepane.add(btnSubject);

		btnFillScore = new JButton("  Fill Score", new ImageIcon("resource//image//edit_propoty.png"));
		btnFillScore.setForeground(new Color(219, 227, 229));
		btnFillScore.setFocusable(false);
		btnFillScore.setContentAreaFilled(false);
		btnFillScore.setBorderPainted(false);
		btnFillScore.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		btnFillScore.setBounds(0, 277, 249, 43);
		btnFillScore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rightSidePanel.removeAll();
				rightSidePanel.add(panelFillScore);
				rightSidePanel.repaint();
				rightSidePanel.revalidate();
				lblheader.setText("Fill Score  ");
				setBgSidePanel(bgPanelFillScore, 277);
				removeBgOther(bgPanelFillScore);
			}
		});
		btnFillScore.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent event) {
				highlightButtons(event.getLocationOnScreen(), btnFillScore);
			}

			public void mouseExited(MouseEvent event) {
				btnFillScore.setContentAreaFilled(false);
			}
		});
		sidepane.add(btnFillScore);

		JButton btnHistory = new JButton("  History", new ImageIcon("resource//image//history.png"));
		btnHistory.setForeground(new Color(219, 227, 229));
		btnHistory.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		btnHistory.setFocusable(false);
		btnHistory.setContentAreaFilled(false);
		btnHistory.setBorderPainted(false);
		btnHistory.setBounds(0, 331, 249, 43);
		btnHistory.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent event) {
				highlightButtons(event.getLocationOnScreen(), btnHistory);
			}

			public void mouseExited(MouseEvent event) {
				btnHistory.setContentAreaFilled(false);
			}
		});
		sidepane.add(btnHistory);

		JButton btnExport = new JButton("  Export File", new ImageIcon("resource//image//export.png"));
		btnExport.setForeground(new Color(219, 227, 229));
		btnExport.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		btnExport.setFocusable(false);
		btnExport.setContentAreaFilled(false);
		btnExport.setBorderPainted(false);
		btnExport.setBounds(0, 385, 249, 43);
		btnExport.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent event) {
				highlightButtons(event.getLocationOnScreen(), btnExport);
			}

			public void mouseExited(MouseEvent event) {
				btnExport.setContentAreaFilled(false);
			}
		});
		sidepane.add(btnExport);
	}

	/////////////////////////////////////// This is method that make UI more
	/////////////////////////////////////// beautiful /clap clap

	public void highlightButtons(Point cursor, JButton button) {
		Point buttonLocation = button.getLocationOnScreen();
		double west = buttonLocation.getX();
		double east = buttonLocation.getX() + button.getWidth();
		double north = buttonLocation.getY();
		double south = buttonLocation.getY() + button.getHeight();
		boolean inRow = cursor.getX() > west && cursor.getX() < east;
		boolean inCol = cursor.getY() > north && cursor.getY() < south;
		boolean inBounds = inRow || inCol;
		button.setBackground(inBounds ? MyColor.CORAL.getColor() : null);
		button.setContentAreaFilled(true);
	}

	public void setBgSidePanel(JPanel panel, int y) {
		panel.setBackground(MyColor.CORAL.getColor());
		panel.setBounds(0, y, 249, 43);
		sidepane.add(panel);
	}

	public void removeBgOther(JPanel panel) {
		if (panel.equals(bgPanelFillScore)) {
			sidepane.remove(bgPanelImport);
			sidepane.remove(bgPanelSubject);
			sidepane.repaint();
			sidepane.revalidate();
		}
		if (panel.equals(bgPanelImport)) {
			sidepane.remove(bgPanelFillScore);
			sidepane.remove(bgPanelSubject);
			sidepane.repaint();
			sidepane.revalidate();
		}
		if (panel.equals(bgPanelSubject)) {
			sidepane.remove(bgPanelFillScore);
			sidepane.remove(bgPanelImport);
			sidepane.repaint();
			sidepane.revalidate();
		}
	}

	////////////////////////////////////// Method build panel on right panel

	public void importPanel() {
		panelImport = new JPanel();
		panelImport.setBackground(MyColor.GRAY.getColor());
		panelImport.setLayout(null);

		JLabel lblUpdateStatus = new JLabel("didn't upload file yet.");
		lblUpdateStatus.setForeground(MyColor.RED.getColor());
		lblUpdateStatus.setFont(new Font("Segoe UI Light", Font.PLAIN, 22));
		lblUpdateStatus.setBounds(262, 149, 328, 41);
		panelImport.add(lblUpdateStatus);

		JTextField fileTextField = new JTextField();
		fileTextField.setForeground(MyColor.MIDNIGHTBLUE.getColor());
		fileTextField.setBorder(BorderFactory.createLineBorder(MyColor.MIDNIGHTBLUE.getColor(), 2));
		fileTextField.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		fileTextField.setEditable(false);
		fileTextField.setBounds(138, 212, 409, 41);
		panelImport.add(fileTextField);
		fileTextField.setColumns(10);

		JButton btnAddfile = new JButton(new ImageIcon("resource//image//addfile.png"));
		btnAddfile.setBounds(545, 212, 45, 41);
		btnAddfile.setBackground(MyColor.MIDNIGHTBLUE.getColor());
		btnAddfile.setBorderPainted(false);
		btnAddfile.setFocusable(false);
		btnAddfile.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				table = null;
				comboBox.setSelectedItem(0);

				JFileChooser fileopen = new JFileChooser();
				FileFilter filter = new FileNameExtensionFilter("Excel Workbook (*.xlsx)", "xlsx");
				FileFilter filter2 = new FileNameExtensionFilter("Excel 97-2009 Workbook (*.xls)", "xls");
				fileopen.addChoosableFileFilter(filter);
				fileopen.addChoosableFileFilter(filter2);

				int ret = fileopen.showDialog(null, "Import");

				if (ret == JFileChooser.APPROVE_OPTION) {
					String fileString = fileopen.getSelectedFile().toString();

					fileTextField.setText(fileString);
					btnFillScore.setEnabled(true);

				} else {
					fileTextField.setText("");

				}

			}
		});
		btnAddfile.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent event) {
				highlightButtons(event.getLocationOnScreen(), btnAddfile);
			}

			public void mouseExited(MouseEvent event) {
				btnAddfile.setBackground(MyColor.MIDNIGHTBLUE.getColor());
			}
		});
		panelImport.add(btnAddfile);

		JButton btnOK = new JButton((Icon) null);
		btnOK.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
		btnOK.setForeground(MyColor.GRAY.getColor());
		btnOK.setText("OK");
		btnOK.setFocusable(false);
		btnOK.setBorderPainted(false);
		btnOK.setBackground(new Color(22, 56, 81));
		btnOK.setBounds(353, 286, 97, 41);
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (!fileTextField.getText().trim().isEmpty()) {
						r.readFile(fileTextField.getText());
						if (r.UpdateFileStatus()) {
							lblUpdateStatus.setForeground(MyColor.GREEN.getColor());
							lblUpdateStatus.setText("DONE");
						}
					} else {
						lblUpdateStatus.setForeground(MyColor.RED.getColor());
						lblUpdateStatus.setText("Please select file first");
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnOK.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent event) {
				highlightButtons(event.getLocationOnScreen(), btnOK);
			}

			public void mouseExited(MouseEvent event) {
				btnOK.setBackground(MyColor.MIDNIGHTBLUE.getColor());
			}
		});
		panelImport.add(btnOK);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setForeground(new Color(219, 227, 229));
		btnCancel.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
		btnCancel.setFocusable(false);
		btnCancel.setBorderPainted(false);
		btnCancel.setBackground(new Color(22, 56, 81));
		btnCancel.setBounds(460, 286, 130, 41);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileTextField.setText("");
			}
		});
		btnCancel.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent event) {
				highlightButtons(event.getLocationOnScreen(), btnCancel);
			}

			public void mouseExited(MouseEvent event) {
				btnCancel.setBackground(MyColor.MIDNIGHTBLUE.getColor());
			}
		});
		panelImport.add(btnCancel);

		JLabel lblStatus = new JLabel("File Status : ");
		lblStatus.setForeground(MyColor.MIDNIGHTBLUE.getColor());
		lblStatus.setFont(new Font("Segoe UI Light", Font.BOLD, 22));
		lblStatus.setBounds(138, 149, 149, 41);
		panelImport.add(lblStatus);

	}

	public void fillScorePanel() {
		exam = new ExamResult();
		std = new StudentList();

		panelFillScore = new JPanel();
		panelFillScore.setBackground(MyColor.GRAY.getColor());
		panelFillScore.setLayout(null);

		JLabel lblSubjectTitle = new JLabel("Subject :");
		lblSubjectTitle.setForeground(MyColor.MIDNIGHTBLUE.getColor());
		lblSubjectTitle.setFont(new Font("Segoe UI Light", Font.BOLD, 25));
		lblSubjectTitle.setBounds(286, 30, 125, 30);
		panelFillScore.add(lblSubjectTitle);

		JLabel lblSubjectname = new JLabel("CS284");
		lblSubjectname.setForeground(MyColor.RED.getColor());
		lblSubjectname.setFont(new Font("Segoe UI Light", Font.PLAIN, 25));
		lblSubjectname.setBounds(394, 30, 74, 30);
		panelFillScore.add(lblSubjectname);

		String[] item = { "","Midterm","Final" };
		comboBox = new JComboBox(item);
		comboBox.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		comboBox.setBounds(266, 82, 222, 30);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String head[] = { "Student ID", "Name", "Score" };
				
				table = new JTable(r.getTable(), head);
				/*for (int i = 0; i < table.getRowCount(); i++) {
					 table.setValueAt("0", i, 2);
					
				}*/
				panelFillScore.add(table);

				JScrollPane scrollPane = new JScrollPane(table);
				scrollPane.setBounds(175, 192, 406, 310);
				scrollPane.setBorder(BorderFactory.createLineBorder(MyColor.MIDNIGHTBLUE.getColor(), 2));
				panelFillScore.add(scrollPane);
			
			}
		});
		panelFillScore.add(comboBox);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setForeground(new Color(219, 227, 229));
		btnCancel.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
		btnCancel.setFocusable(false);
		btnCancel.setBorderPainted(false);
		btnCancel.setBackground(new Color(22, 56, 81));
		btnCancel.setBounds(588, 526, 140, 40);
		btnCancel.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent event) {
				highlightButtons(event.getLocationOnScreen(), btnCancel);
			}

			public void mouseExited(MouseEvent event) {
				btnCancel.setBackground(MyColor.MIDNIGHTBLUE.getColor());
			}
		});
		panelFillScore.add(btnCancel);

		JButton btnOK = new JButton("OK");
		btnOK.setForeground(new Color(219, 227, 229));
		btnOK.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
		btnOK.setFocusable(false);
		btnOK.setBorderPainted(false);
		btnOK.setBackground(new Color(22, 56, 81));
		btnOK.setBounds(473, 526, 105, 40);
		btnOK.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnOK.setBackground(MyColor.MIDNIGHTBLUE.getColor());

			}

			@Override
			public void mouseEntered(MouseEvent e) {

				highlightButtons(e.getLocationOnScreen(), btnOK);
			}
///////////////////////////////////////// FILL SCORE ACTIONLISTENER ////////////////////////////////
			@Override
			public void mouseClicked(MouseEvent e) {
			
				for (int i = 0; i < table.getRowCount(); i++) {
					exam.add("" + table.getValueAt(i, 2));
					std.addID(Long.valueOf((String) table.getValueAt(i, 0)));

				}

				try {
					exam.saveList(std.getIDList(), exam.getScoreList(), comboBox.getSelectedItem() + "");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
///////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		panelFillScore.add(btnOK);

		IDtextField = new JTextField();
		IDtextField.setHorizontalAlignment(SwingConstants.CENTER);
		IDtextField.setForeground(new Color(219, 227, 229));
		IDtextField.setText("Search by ID");
		IDtextField.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		IDtextField.setColumns(10);
		IDtextField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				IDtextField.setBackground(MyColor.CORAL.getColor());
				IDtextField.setForeground(MyColor.MIDNIGHTBLUE.getColor());
				if (IDtextField.getText().equals("Search by ID")) {
					IDtextField.setText("");
				}
			}

			public void focusLost(FocusEvent e) {
				IDtextField.setBackground(MyColor.MIDNIGHTBLUE.getColor());
				IDtextField.setForeground(MyColor.GRAY.getColor());
				if (IDtextField.getText().trim().isEmpty()) {
					IDtextField.setText("Search by ID");
				}

			}
		});
		IDtextField.setBorder(BorderFactory.createEmptyBorder());
		IDtextField.setBackground(new Color(25, 56, 81));
		IDtextField.setBounds(277, 135, 156, 35);
		panelFillScore.add(IDtextField);

		JButton btnID = new JButton(new ImageIcon("resource//image//search.png"));
		btnID.setFocusable(false);
		btnID.setBorderPainted(false);
		btnID.setBackground(new Color(25, 56, 81));
		btnID.setBounds(433, 135, 47, 35);
		btnID.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent event) {
				highlightButtons(event.getLocationOnScreen(), btnID);
			}

			public void mouseExited(MouseEvent event) {
				btnID.setBackground(MyColor.MIDNIGHTBLUE.getColor());
			}
		});
		panelFillScore.add(btnID);
	}

	public void subjectPanel() {
		JLabel labelSubjectName = new JLabel("Subject :");
		JButton btnCriteria = new JButton("Criteria");
		JLabel lblFillTheseInfomamation = new JLabel("Fill these information");
		JLabel labelTeacherName = new JLabel("Teacher Name :");
		JLabel label_2 = new JLabel("How many quiz in this class :");
		JTextField textFieldSubject = new JTextField();
		JTextField textFieldTeacher = new JTextField();
		JTextField textFieldManyQuiz = new JTextField();

		Subjectpanel = new JPanel();
		Subjectpanel.setBackground(MyColor.GRAY.getColor());
		Subjectpanel.setLayout(null);

		JPanel panelSubjectSidepane;
		JPanel bgSubject = new JPanel();
		JPanel bgCriteria = new JPanel();

		panelSubjectSidepane = new JPanel();
		panelSubjectSidepane.setBounds(3, 11, 740, 48);
		panelSubjectSidepane.setBackground(MyColor.MIDNIGHTBLUE.getColor());
		Subjectpanel.add(panelSubjectSidepane);
		panelSubjectSidepane.setLayout(null);

		JPanel SubjectdownPanel = new JPanel();
		SubjectdownPanel.setBounds(10, 71, 733, 489);
		SubjectdownPanel.setLayout(null);
		Subjectpanel.add(SubjectdownPanel);

		JPanel panelSubjectInfo = new JPanel();
		panelSubjectInfo.setBounds(0, 0, 733, 489);
		SubjectdownPanel.add(panelSubjectInfo);
		panelSubjectInfo.setLayout(null);
		panelSubjectInfo.setBackground(MyColor.GRAY.getColor());

		JPanel panelCriteria = new JPanel();
		panelCriteria.setBounds(0, 0, 733, 489);
		panelCriteria.setBackground(MyColor.GRAY.getColor());
		SubjectdownPanel.add(panelCriteria);
		panelCriteria.setLayout(null);

		JLabel lblFill = new JLabel("Fill full score and criteria");
		lblFill.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 35));
		lblFill.setBounds(37, 21, 384, 63);
		panelCriteria.add(lblFill);

		JButton btnSubjectinfo = new JButton("Subject");
		btnSubjectinfo.setBounds(0, 0, 168, 48);
		panelSubjectSidepane.add(btnSubjectinfo);
		btnSubjectinfo.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 22));
		btnSubjectinfo.setForeground(MyColor.GRAY.getColor());
		btnSubjectinfo.setFocusable(false);
		btnSubjectinfo.setBorder(BorderFactory.createEmptyBorder());
		btnSubjectinfo.setContentAreaFilled(false);
		btnSubjectinfo.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				highlightButtons(e.getLocationOnScreen(), btnSubjectinfo);

			}

			public void mouseExited(MouseEvent e) {
				btnSubjectinfo.setContentAreaFilled(false);
			}
		});

		btnSubjectinfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SubjectdownPanel.removeAll();
				SubjectdownPanel.add(panelSubjectInfo);
				SubjectdownPanel.repaint();
				SubjectdownPanel.revalidate();
				bgSubject.setBackground(MyColor.CORAL.getColor());
				bgSubject.setBounds(0, 0, 168, 48);
				panelSubjectSidepane.add(bgSubject);
				panelSubjectSidepane.remove(bgCriteria);
				panelSubjectSidepane.repaint();
				panelSubjectSidepane.revalidate();
			}
		});

		bgSubject.setBackground(MyColor.CORAL.getColor());
		bgSubject.setBounds(0, 0, 168, 48);
		panelSubjectSidepane.add(bgSubject);

		JButton btnOKC = new JButton("OK");
		btnOKC.setBounds(600, 426, 105, 36);
		panelCriteria.add(btnOKC);
		btnOKC.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnOKC.setForeground(MyColor.GRAY.getColor());
		btnOKC.setBackground(MyColor.MIDNIGHTBLUE.getColor());
		btnOKC.setFocusable(false);
		btnOKC.setBorder(BorderFactory.createEmptyBorder());
		btnOKC.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				highlightButtons(e.getLocationOnScreen(), btnOKC);
			}
			
///////////////////////////////////////// FILL CRITERIA ACTIONLISTENER ////////////////////////////////
			public void mouseClicked(MouseEvent e) {
				int a = 0,b = 0;
					for (int i = 0; i < tableFillscore.getRowCount(); i++) {
						try {
						 a = Integer.valueOf((String)tableFillscore.getValueAt(i, 1));
						 b = Integer.valueOf((String )tableFillscore.getValueAt(i, 2));
						ec.add("" + tableFillscore.getValueAt(i, 0),a,b);
							
						} catch (NumberFormatException n) {
					System.out.println("Please Enter Number Not String ");
				
				}
					}
				try {
					ec.saveList(ec.getTypeList(), ec.getMaxList(), ec.getPercentList(), subject,manyquiz);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			public void mouseExited(MouseEvent e) {
				btnOKC.setBackground(MyColor.MIDNIGHTBLUE.getColor());
			}
		});
///////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		btnCriteria.setForeground(new Color(219, 227, 229));
		btnCriteria.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 22));
		btnCriteria.setFocusable(false);
		btnCriteria.setContentAreaFilled(false);
		btnCriteria.setBorder(BorderFactory.createEmptyBorder());
		btnCriteria.setBounds(178, 0, 168, 48);
		btnCriteria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				SubjectdownPanel.removeAll();
				String head[] = { "Name", "Full Score", "Criteria (%)" };

				tableFillscore = new JTable(ec.getTable(), head);

				JScrollPane scrollPane = new JScrollPane(tableFillscore);
				scrollPane.setBounds(147, 95, 432, 365);
				scrollPane.setBorder(BorderFactory.createLineBorder(MyColor.MIDNIGHTBLUE.getColor(), 2));
				panelCriteria.add(scrollPane);

				SubjectdownPanel.add(panelCriteria);
				SubjectdownPanel.repaint();
				SubjectdownPanel.revalidate();
				bgCriteria.setBackground(MyColor.CORAL.getColor());
				bgCriteria.setBounds(178, 0, 168, 48);
				panelSubjectSidepane.add(bgCriteria);
				panelSubjectSidepane.remove(bgSubject);
				panelSubjectSidepane.repaint();
				panelSubjectSidepane.revalidate();
			}
		});
		btnCriteria.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				highlightButtons(e.getLocationOnScreen(), btnCriteria);
			}

			public void mouseExited(MouseEvent e) {
				btnCriteria.setContentAreaFilled(false);
			}
		});
		panelSubjectSidepane.add(btnCriteria);

		lblFillTheseInfomamation.setBounds(46, 56, 375, 50);
		panelSubjectInfo.add(lblFillTheseInfomamation);
		lblFillTheseInfomamation.setFont(new Font("Segoe UI Semilight", Font.BOLD, 37));

		JButton btnOk = new JButton("OK");
		btnOk.setBounds(591, 366, 105, 36);
		panelSubjectInfo.add(btnOk);
		btnOk.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnOk.setForeground(MyColor.GRAY.getColor());
		btnOk.setBackground(MyColor.MIDNIGHTBLUE.getColor());
		btnOk.setFocusable(false);
		btnOk.setBorder(BorderFactory.createEmptyBorder());
		btnOk.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				highlightButtons(e.getLocationOnScreen(), btnOk);
			}

			public void mouseClicked(MouseEvent e) {

				subject = textFieldSubject.getText();
				manyquiz =  textFieldManyQuiz.getText();
				ec.setSize(Integer.valueOf(textFieldManyQuiz.getText()));

			}

			public void mouseExited(MouseEvent e) {
				btnOk.setBackground(MyColor.MIDNIGHTBLUE.getColor());
			}
		});

		JPanel panelinfo = new JPanel();
		panelinfo.setLayout(null);
		panelinfo.setBorder(BorderFactory.createLineBorder(MyColor.MIDNIGHTBLUE.getColor(), 2));
		panelinfo.setBackground(new Color(219, 227, 229));
		panelinfo.setBounds(46, 125, 650, 215);
		panelSubjectInfo.add(panelinfo);

		labelSubjectName.setForeground(new Color(25, 56, 81));
		labelSubjectName.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 24));
		labelSubjectName.setBounds(24, 30, 103, 48);
		panelinfo.add(labelSubjectName);

		labelTeacherName.setForeground(new Color(25, 56, 81));
		labelTeacherName.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 24));
		labelTeacherName.setBounds(24, 87, 163, 40);
		panelinfo.add(labelTeacherName);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(new Color(25, 56, 81));
		separator_1.setBackground(new Color(219, 227, 229));
		separator_1.setBounds(134, 66, 423, 2);
		panelinfo.add(separator_1);

		textFieldSubject.setForeground(new Color(25, 56, 81));
		textFieldSubject.setFont(new Font("Segoe UI Light", Font.PLAIN, 21));
		textFieldSubject.setColumns(10);
		textFieldSubject.setBorder(BorderFactory.createEmptyBorder());
		textFieldSubject.setBackground(new Color(219, 227, 229));
		textFieldSubject.setBounds(137, 35, 416, 28);
		panelinfo.add(textFieldSubject);

		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(new Color(25, 56, 81));
		separator_2.setBackground(new Color(219, 227, 229));
		separator_2.setBounds(197, 116, 360, 2);
		panelinfo.add(separator_2);

		textFieldTeacher.setForeground(new Color(25, 56, 81));
		textFieldTeacher.setFont(new Font("Segoe UI Light", Font.PLAIN, 21));
		textFieldTeacher.setColumns(10);
		textFieldTeacher.setBorder(BorderFactory.createEmptyBorder());
		textFieldTeacher.setBackground(new Color(219, 227, 229));
		textFieldTeacher.setBounds(198, 85, 356, 28);
		panelinfo.add(textFieldTeacher);

		label_2.setForeground(new Color(25, 56, 81));
		label_2.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 24));
		label_2.setBounds(24, 137, 305, 40);
		panelinfo.add(label_2);

		textFieldManyQuiz.setForeground(new Color(25, 56, 81));
		textFieldManyQuiz.setFont(new Font("Segoe UI Light", Font.PLAIN, 21));
		textFieldManyQuiz.setColumns(10);
		textFieldManyQuiz.setBorder(BorderFactory.createEmptyBorder());
		textFieldManyQuiz.setBackground(new Color(219, 227, 229));
		textFieldManyQuiz.setBounds(333, 139, 223, 28);
		panelinfo.add(textFieldManyQuiz);

		JSeparator separator_3 = new JSeparator();
		separator_3.setForeground(new Color(25, 56, 81));
		separator_3.setBackground(new Color(219, 227, 229));
		separator_3.setBounds(330, 170, 227, 2);
		panelinfo.add(separator_3);
	}
}
