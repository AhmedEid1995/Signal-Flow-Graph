package FrontEnd;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Gui {
	private JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui window = new Gui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Gui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(50, 50, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel title = new JLabel("            SIGNAL FLOW GRAPH");
		title.setFont(new Font("Tahoma", Font.BOLD, 21));
		title.setForeground(Color.RED);
		title.setBounds(70, 28, 362, 67);
		frame.getContentPane().add(title);
		
		JButton btnStart = new JButton("    START");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				View.main();
				frame.setVisible(false);
			}
		});
		btnStart.setFont(new Font("Times New Roman", Font.ITALIC, 16));
		btnStart.setForeground(Color.RED);
		btnStart.setBounds(120, 135, 235, 54);
		frame.getContentPane().add(btnStart);
		
		JButton btnNewButton = new JButton("HOW TO USE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserGuide x = new UserGuide();
				x.frame.setVisible(true);
				x.frame.setTitle("User Guide");
				
			}
		});
		btnNewButton.setFont(new Font("Segoe Script", Font.PLAIN, 20));
		btnNewButton.setBounds(120, 258, 235, 54);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("EXIT");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnNewButton_1.setFont(new Font("Segoe Print", Font.PLAIN, 20));
		btnNewButton_1.setBounds(120, 364, 235, 54);
		frame.getContentPane().add(btnNewButton_1);
		
	
	}
}
