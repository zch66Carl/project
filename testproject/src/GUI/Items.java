package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.JButton;

public class Items {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Items window = new Items();
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
	public Items() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 356);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Player 1's items");
		lblNewLabel.setFont(new Font("SimSun", Font.PLAIN, 14));
		lblNewLabel.setBounds(45, 10, 140, 35);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Small Healing Potion");
		lblNewLabel_1.setBounds(45, 55, 128, 35);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Medium Healing Potion");
		lblNewLabel_1_1.setBounds(45, 100, 128, 35);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Large Healing Potion");
		lblNewLabel_1_2.setBounds(45, 145, 128, 35);
		frame.getContentPane().add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Small Attack Potion");
		lblNewLabel_1_3.setBounds(266, 55, 128, 35);
		frame.getContentPane().add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Medium Attack Potion");
		lblNewLabel_1_4.setBounds(266, 100, 128, 35);
		frame.getContentPane().add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_5 = new JLabel("Large Attack Potion");
		lblNewLabel_1_5.setBounds(266, 145, 128, 35);
		frame.getContentPane().add(lblNewLabel_1_5);
		
		JLabel lblNewLabel_2 = new JLabel("1");
		lblNewLabel_2.setBounds(202, 65, 54, 15);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("3");
		lblNewLabel_2_1.setBounds(202, 110, 54, 15);
		frame.getContentPane().add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("5");
		lblNewLabel_2_2.setBounds(202, 155, 54, 15);
		frame.getContentPane().add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_3 = new JLabel("12");
		lblNewLabel_2_3.setBounds(404, 65, 54, 15);
		frame.getContentPane().add(lblNewLabel_2_3);
		
		JLabel lblNewLabel_2_4 = new JLabel("2");
		lblNewLabel_2_4.setBounds(404, 110, 54, 15);
		frame.getContentPane().add(lblNewLabel_2_4);
		
		JLabel lblNewLabel_2_5 = new JLabel("0");
		lblNewLabel_2_5.setBounds(404, 155, 54, 15);
		frame.getContentPane().add(lblNewLabel_2_5);
		
		JLabel lblNewLabel_3 = new JLabel("Special Item");
		lblNewLabel_3.setBounds(468, 65, 82, 15);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("2");
		lblNewLabel_4.setBounds(600, 65, 54, 15);
		frame.getContentPane().add(lblNewLabel_4);
		
		JButton btnNewButton = new JButton("Shop");
		btnNewButton.setBounds(123, 213, 133, 29);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnExit = new JButton("Back");
		btnExit.setBounds(417, 213, 133, 29);
		frame.getContentPane().add(btnExit);
	}
}
