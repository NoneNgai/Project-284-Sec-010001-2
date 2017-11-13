import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
	private JPanel panelImport,panelFillScore;
	private JTextField scoreField;
	private JTextField IDtextField;
	private JTable table;
	private ReadWriteExcelFile r = new ReadWriteExcelFile();

	public MainPanel() {
		
		/////// Create Panel and Material
		
		setSize(new Dimension(1000,700));
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
		panelHeader.add(lblheader,BorderLayout.EAST);
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
		
		/////// Button on Sidepane
		
		JButton btnImportFile = new JButton("  Import File",new ImageIcon("resource//image//import.png"));
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
				setBgSidePanel(bgPanelImport,169);
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
		
		JButton btnFillScore = new JButton("  Fill Score", new ImageIcon("resource//image//edit_propoty.png"));
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
				setBgSidePanel(bgPanelFillScore,277);
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
	
	/////////////////////////////////////// This is method that make UI more beautiful /clap clap
	
	public void highlightButtons(Point cursor,JButton button) {
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
	
	public void setBgSidePanel(JPanel panel,int y) {
		panel.setBackground(MyColor.CORAL.getColor());
		panel.setBounds(0, y, 249, 43);
		sidepane.add(panel);
	}
	
	public void removeBgOther(JPanel panel) {
		if (panel.equals(bgPanelFillScore)) {
			sidepane.remove(bgPanelImport);
			sidepane.repaint();
			sidepane.revalidate();
		}
		if (panel.equals(bgPanelImport)) {
			sidepane.remove(bgPanelFillScore);
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
		lblUpdateStatus.setFont(new Font("Segoe UI Light", Font.PLAIN,22));
		lblUpdateStatus.setBounds(262, 149, 328, 41);
		panelImport.add(lblUpdateStatus);
		
		JTextField fileTextField = new JTextField();
		fileTextField.setForeground(MyColor.MIDNIGHTBLUE.getColor());
		fileTextField.setBorder(BorderFactory.createLineBorder(MyColor.MIDNIGHTBLUE.getColor(),2));
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
				JFileChooser fileopen = new JFileChooser();
				FileFilter filter = new FileNameExtensionFilter("Excel Workbook (*.xlsx)","xlsx");
				FileFilter filter2 = new FileNameExtensionFilter("Excel 97-2009 Workbook (*.xls)","xls");
				fileopen.addChoosableFileFilter(filter);
				fileopen.addChoosableFileFilter(filter2);
				
				int ret = fileopen.showDialog(null, "Import");
				
				if (ret == JFileChooser.APPROVE_OPTION) {
					String fileString = fileopen.getSelectedFile().toString();
					fileTextField.setText(fileString);
				}
				else {
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
					}
					else {
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
		
		String[] item = {"","Quiz 1","Quiz 2"};
		JComboBox comboBox = new JComboBox(item);
		comboBox.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		comboBox.setBounds(266, 82, 222, 30);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//if (comboBox.getSelectedItem().equals("Quiz 1")) {
					String head[] = {"Student ID","Name","Score"};
					
					table = new JTable(r.getTable(),head);
					panelFillScore.add(table);
					
					JScrollPane scrollPane = new JScrollPane(table);
					scrollPane.setBounds(175, 192, 406, 310);
					scrollPane.setBorder(BorderFactory.createLineBorder(MyColor.MIDNIGHTBLUE.getColor(), 2));
					panelFillScore.add(scrollPane);
			//	}
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
		btnOK.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent event) {
		        highlightButtons(event.getLocationOnScreen(), btnOK);
		    }
			
			public void mouseExited(MouseEvent event) {
				btnOK.setBackground(MyColor.MIDNIGHTBLUE.getColor());
			}
		});
		panelFillScore.add(btnOK);
		
		scoreField = new JTextField();
		scoreField.setBounds(415, 135, 120, 35);
		panelFillScore.add(scoreField);
		scoreField.setBackground(MyColor.MIDNIGHTBLUE.getColor());
		scoreField.setForeground(MyColor.GRAY.getColor());
		scoreField.setHorizontalAlignment(JTextField.CENTER);
		scoreField.setFont(new Font("Segoe UI Semibold",Font.BOLD,14));
		scoreField.setEnabled(false);
		scoreField.setText("Add score...");
		scoreField.setBorder(BorderFactory.createEmptyBorder());
		scoreField.setColumns(10);
		scoreField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				scoreField.setBackground(MyColor.CORAL.getColor());
				scoreField.setForeground(MyColor.MIDNIGHTBLUE.getColor());
				scoreField.setText("");
			}
			public void focusLost(FocusEvent e) {
				scoreField.setBackground(MyColor.MIDNIGHTBLUE.getColor());
				scoreField.setForeground(MyColor.GRAY.getColor());
				if (scoreField.getText().trim().isEmpty()) {
					scoreField.setText("Add Score...");
				}
			}
		});
		
		JButton btnAdd = new JButton(new ImageIcon("resource//image//add.png"));
		btnAdd.setBackground(MyColor.MIDNIGHTBLUE.getColor());
		btnAdd.setBorderPainted(false);
		btnAdd.setFocusable(false);
		btnAdd.setBounds(534, 135, 47, 35);
		btnAdd.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent event) {
			    highlightButtons(event.getLocationOnScreen(), btnAdd);
			}
				
			public void mouseExited(MouseEvent event) {
				btnAdd.setBackground(MyColor.MIDNIGHTBLUE.getColor());
			}
		});
		panelFillScore.add(btnAdd);
		
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
		IDtextField.setBounds(175, 135, 156, 35);
		panelFillScore.add(IDtextField);
		
		JButton btnID = new JButton(new ImageIcon("resource//image//search.png"));
		btnID.setFocusable(false);
		btnID.setBorderPainted(false);
		btnID.setBackground(new Color(25, 56, 81));
		btnID.setBounds(331, 135, 47, 35);
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
}
