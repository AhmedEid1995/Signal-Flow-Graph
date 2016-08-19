package FrontEnd;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

public class UserGuide {

 static  JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserGuide window = new UserGuide();
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
	public UserGuide() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 26));
		frame.setBounds(100, 100, 1200, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblHowToUse = new JLabel("User Guide");
		lblHowToUse.setFont(new Font("Segoe Print", Font.BOLD | Font.ITALIC, 26));
		lblHowToUse.setForeground(Color.RED);
		lblHowToUse.setBounds(422, 25, 238, 36);
		frame.getContentPane().add(lblHowToUse);
		
		JLabel lblNewLabel = new JLabel("What Is Signal Flow Graph ?! ");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 71, 567, 58);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Signal flow graph of control system is further simplification of block diagram of control system. Here, the blocks of transfer function, summing symbols and take off points are eliminated by branches and ");
		lblNewLabel_1.setBounds(10, 140, 1164, 34);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("nodes.");
		lblNewLabel_2.setBounds(10, 185, 473, 34);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblHowToUse_1 = new JLabel("How To Use ?");
		lblHowToUse_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblHowToUse_1.setForeground(Color.RED);
		lblHowToUse_1.setBounds(10, 227, 136, 36);
		frame.getContentPane().add(lblHowToUse_1);
		
		JLabel lblNewLabel_3 = new JLabel("To draw a Node , press add node nutton from the list of buttons then select position with mouse and click left click .");
		lblNewLabel_3.setBounds(10, 262, 1018, 28);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("To add edge, pres add edge button from the list of buttons and select 2 nodes with mouse then enter the gain of edge and press enter .");
		lblNewLabel_4.setBounds(10, 301, 888, 28);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblForSelfLoop = new JLabel("For self loop , press add edge button from the list of buttons and select the node twice then enter the gain and press enter .");
		lblForSelfLoop.setBounds(10, 333, 918, 28);
		frame.getContentPane().add(lblForSelfLoop);
		
		JLabel lblToCalculateThe = new JLabel("To calculate the gain press the button calculate from the list of buttons then select Source node and end node by mouse .");
		lblToCalculateThe.setBounds(10, 372, 803, 14);
		frame.getContentPane().add(lblToCalculateThe);
	}
}
