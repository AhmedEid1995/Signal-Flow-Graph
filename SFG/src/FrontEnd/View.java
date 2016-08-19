package FrontEnd;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.File;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class View extends JFrame {

	private JPanel contentPane;
	private DefaultListModel<String> log;
	private DefaultTableModel edges;
	private DefaultTableModel loopData;
	private DefaultTableModel forwardData;
	private Control controller;
	private JTextField textField;
	
	 static View frame;
	/**
	 * Launch the application.
	 */
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					 frame = new View();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public View() throws IOException {
		setTitle("SIGNAL FLOW GRAPH");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1357, 710);

		DrawPanel panel = new DrawPanel();
		controller = Control.getInstance(panel, this);
		panel.addMouseListener(controller);
		panel.addMouseMotionListener(controller);

	

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 5, 5));
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();

		panel.setBackground(Color.LIGHT_GRAY);
		
		scrollPane.setViewportView(panel);
	
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, null, TitledBorder.LEADING, TitledBorder.TOP, null, null));

		log = new DefaultListModel<String>();

		JScrollPane scrollPane_3 = new JScrollPane();
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		
		JScrollPane scrollPane_4 = new JScrollPane();

		JLabel lblGain = new JLabel("T.F :");
		lblGain.setFont(new Font("Traditional Arabic", Font.BOLD, 18));
		JScrollPane scrollPane_1 = new JScrollPane();
		textField = new JTextField();
		textField.setBackground(Color.WHITE);
		textField.setEditable(false);
		textField.setColumns(27);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		GroupLayout gl_contentPane1 = new GroupLayout(contentPane);
		gl_contentPane1
				.setHorizontalGroup(gl_contentPane1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane1.createSequentialGroup().addContainerGap()
								.addGroup(gl_contentPane1.createParallelGroup(Alignment.TRAILING)
										.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 169,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(scrollPane_3, GroupLayout.PREFERRED_SIZE, 169,
												GroupLayout.PREFERRED_SIZE)
										)
								.addGap(18)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE).addGap(
										18)
						.addGroup(gl_contentPane1.createParallelGroup(Alignment.LEADING)
								.addComponent( scrollPane_1 , GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
								.addComponent(scrollPane_4, GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
								.addGroup(gl_contentPane1.createSequentialGroup()
										.addComponent(lblGain, GroupLayout.PREFERRED_SIZE, 52,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(textField, GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)))
						.addContainerGap()));
		gl_contentPane1.setVerticalGroup(gl_contentPane1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane1.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPane1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane1.createSequentialGroup()
										.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 427,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(scrollPane_3, GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
										)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 619,
								Short.MAX_VALUE).addGroup(
										gl_contentPane1.createSequentialGroup()
												.addComponent(scrollPane_4, GroupLayout.PREFERRED_SIZE, 259,
														GroupLayout.PREFERRED_SIZE)
												.addGap(18)
												.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 269,
														Short.MAX_VALUE)
								.addGap(44)
								.addGroup(gl_contentPane1.createParallelGroup(Alignment.LEADING)
										.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												400)
										.addComponent(lblGain, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												400))))
						.addContainerGap()));

		edges = new DefaultTableModel(new String[][] {}, new String[] { "Edge", "Gain" });
		JTable table = new JTable(edges);
		table.setName("edgeTable");
		table.addMouseListener(controller);
		scrollPane_3.setViewportView(table);

		forwardData = new DefaultTableModel(new String[][] {}, new String[] { "Forward Path", "Gain" });
		JTable forwardTable = new JTable(forwardData);
		forwardTable.setName("forwardTable");
		forwardTable.addMouseListener(controller);

		scrollPane_4.setViewportView(forwardTable);
	//	tabbedPane.addTab("Forward Paths", null, scrollPane_2, null);

		loopData = new DefaultTableModel(new String[][] {}, new String[] { "Loop", "Gain" });
		JTable loopTable = new JTable(loopData);
		loopTable.setName("loopTable");
		loopTable.addMouseListener(controller);

		
		scrollPane_1.setViewportView(loopTable);
		//tabbedPane.addTab("Loops", null, scrollPane_1, null);

		JList<String> list = new JList<>(log);
		list.addMouseListener(controller);

		//scrollPane_3.setViewportView(list);

		JButton button = new JButton("Add Node");
		button.addActionListener(controller);

		JButton button_1 = new JButton("Add Edge");
		button_1.addActionListener(controller);

	//	JButton button_2 = new JButton("Loops");
	//	button_2.addActionListener(controller);

//		JButton button_3 = new JButton("Forward Paths");
//		button_3.addActionListener(controller);

		JButton button_4 = new JButton("Calculate Gain");
		button_4.addActionListener(controller);

		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1
				.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1
								.createSequentialGroup().addGap(
										18)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
							//			.addComponent(button_3, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
										.addComponent(button_4, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
							//			.addComponent(button_2, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
										.addComponent(button_1, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
										.addComponent(button, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE))
				.addGap(21)));
		gl_panel_1
				.setVerticalGroup(
						gl_panel_1
								.createParallelGroup(
										Alignment.TRAILING)
								.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup().addGap(35)
										.addComponent(button, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE).addGap(18)
										.addComponent(button_1, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
										.addGap(18)
										//.addComponent(button_2, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
										//.addGap(18)
									//	.addComponent(button_3, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
										.addGap(18)
										.addComponent(button_4, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
										.addGap(57)));
		panel_1.setLayout(gl_panel_1);
		contentPane.setLayout(gl_contentPane1);
	}

	public void addLog(String s) {
		if (log.size() > 20)
			log.remove(0);

		log.addElement(s);

	}

	public void clearLoopData() {
		
		int length = loopData.getRowCount();
		for (int i = 0; i < length; i++) {
			loopData.removeRow(i);
		}

	}

	public void addLoop(String[] rowData) {
		loopData.addRow(rowData);
	}

	public void clearForwardData() {
		int length = forwardData.getRowCount();
		for (int i = 0; i < length; i++) {
			forwardData.removeRow(i);
		}
	}

	public void addForwardPaths(String[] rowData) {
		forwardData.addRow(rowData);
	}

	public void addEdge(String[] rowData) {
		edges.addRow(rowData);
	}

	public void deleteLoop(int row) {
		loopData.removeRow(row);
	}

	public void deleteForwardPaths(int row) {
		forwardData.removeRow(row);
	}

	public void removeEdge(int row) {
		edges.removeRow(row);
	}

	public void setGainText(String s) {
		textField.setText(s);
	}

	public void setEdgeValue(int row, int col, int val) {
		edges.setValueAt(val, row, col);
	}

}
