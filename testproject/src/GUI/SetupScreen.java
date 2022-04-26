package GUI;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.DropMode;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class SetupScreen {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SetupScreen window = new SetupScreen();
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
	public SetupScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 680);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome to Monster Battles");
		lblNewLabel.setFont(new Font("SimSun", Font.BOLD, 18));
		lblNewLabel.setBounds(37, 10, 266, 43);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Please Enter Player Name:\r\n\r\n");
		lblNewLabel_1.setToolTipText("");
		lblNewLabel_1.setFont(new Font("SimSun", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(37, 63, 175, 43);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setToolTipText("3 to 15 characters without numbers or special character");
		textField.setBounds(243, 74, 121, 21);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Days:");
		lblNewLabel_2.setFont(new Font("SimSun", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(436, 70, 40, 29);
		frame.getContentPane().add(lblNewLabel_2);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"}));
		comboBox.setBounds(486, 73, 34, 23);
		frame.getContentPane().add(comboBox);
		
		JLabel lblNewLabel_3 = new JLabel("Choose starting monsters:");
		lblNewLabel_3.setFont(new Font("SimSun", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(37, 116, 175, 29);
		frame.getContentPane().add(lblNewLabel_3);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Goat", "Tiger", "Monkey"}));
		comboBox_1.setBounds(243, 119, 121, 23);
		frame.getContentPane().add(comboBox_1);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(432, 120, 357, 185);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("Monster Information");
		lblNewLabel_4.setFont(new Font("SimSun", Font.BOLD, 14));
		lblNewLabel_4.setBounds(105, 0, 149, 31);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Max Health:");
		lblNewLabel_5.setBounds(10, 29, 73, 25);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Damage:");
		lblNewLabel_6.setBounds(10, 64, 73, 25);
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Current Health:");
		lblNewLabel_7.setBounds(10, 102, 90, 15);
		panel.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Ability:");
		lblNewLabel_8.setBounds(10, 136, 84, 15);
		panel.add(lblNewLabel_8);
		
		JLabel lblNewLabel_20 = new JLabel("800");
		lblNewLabel_20.setBounds(183, 34, 54, 15);
		panel.add(lblNewLabel_20);
		
		JLabel lblNewLabel_21 = new JLabel("15-150");
		lblNewLabel_21.setBounds(183, 69, 54, 15);
		panel.add(lblNewLabel_21);
		
		JLabel lblNewLabel_22 = new JLabel("35");
		lblNewLabel_22.setBounds(183, 102, 54, 15);
		panel.add(lblNewLabel_22);
		
		JLabel lblNewLabel_23 = new JLabel("None");
		lblNewLabel_23.setBounds(183, 136, 54, 15);
		panel.add(lblNewLabel_23);
		
		JButton btnNewButton = new JButton("Add to team");
		btnNewButton.setBounds(243, 152, 109, 23);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_9 = new JLabel("(Number of days the game will last)");
		lblNewLabel_9.setFont(new Font("SimSun", Font.PLAIN, 14));
		lblNewLabel_9.setBounds(530, 77, 259, 15);
		frame.getContentPane().add(lblNewLabel_9);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(37, 185, 327, 120);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_10 = new JLabel("Chosen Monster");
		lblNewLabel_10.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_10.setFont(new Font("SimSun", Font.BOLD, 14));
		lblNewLabel_10.setBounds(67, 0, 168, 31);
		panel_1.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("Goat");
		lblNewLabel_11.setFont(new Font("SimSun", Font.PLAIN, 14));
		lblNewLabel_11.setBounds(25, 49, 54, 15);
		panel_1.add(lblNewLabel_11);
		
		textField_1 = new JTextField();
		textField_1.setBounds(90, 46, 66, 21);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Rename Monster");
		btnNewButton_1.setFont(new Font("SimSun", Font.PLAIN, 14));
		btnNewButton_1.setBounds(166, 45, 151, 23);
		panel_1.add(btnNewButton_1);
		
		JLabel lblNewLabel_12 = new JLabel("Select Difficulty:");
		lblNewLabel_12.setFont(new Font("SimSun", Font.PLAIN, 14));
		lblNewLabel_12.setBounds(37, 333, 141, 29);
		frame.getContentPane().add(lblNewLabel_12);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"Easy", "Normal", "Hard"}));
		comboBox_2.setBounds(243, 336, 121, 23);
		frame.getContentPane().add(comboBox_2);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(37, 391, 327, 143);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_13 = new JLabel("Easy");
		lblNewLabel_13.setFont(new Font("SimSun", Font.BOLD, 14));
		lblNewLabel_13.setBounds(133, 0, 55, 24);
		panel_2.add(lblNewLabel_13);
		
		JLabel lblNewLabel_14 = new JLabel("Starting Gold:");
		lblNewLabel_14.setBounds(10, 29, 84, 15);
		panel_2.add(lblNewLabel_14);
		
		JLabel lblNewLabel_15 = new JLabel("Gold Win(Based on Level):");
		lblNewLabel_15.setBounds(10, 65, 171, 15);
		panel_2.add(lblNewLabel_15);
		
		JLabel lblNewLabel_16 = new JLabel("Enemy Strength(%Increase):");
		lblNewLabel_16.setBounds(10, 106, 171, 15);
		panel_2.add(lblNewLabel_16);
		
		JLabel lblNewLabel_17 = new JLabel("500");
		lblNewLabel_17.setBounds(191, 29, 54, 15);
		panel_2.add(lblNewLabel_17);
		
		JLabel lblNewLabel_18 = new JLabel("100%");
		lblNewLabel_18.setBounds(191, 65, 54, 15);
		panel_2.add(lblNewLabel_18);
		
		JLabel lblNewLabel_19 = new JLabel("75%");
		lblNewLabel_19.setBounds(191, 106, 54, 15);
		panel_2.add(lblNewLabel_19);
		
		JButton btnNewButton_2 = new JButton("Start Game");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_2.setFont(new Font("SimSun", Font.PLAIN, 18));
		btnNewButton_2.setBounds(436, 391, 278, 85);
		frame.getContentPane().add(btnNewButton_2);
	}
}
