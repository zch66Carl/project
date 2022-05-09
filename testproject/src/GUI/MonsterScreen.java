package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class MonsterScreen {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MonsterScreen window = new MonsterScreen();
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
	public MonsterScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 555, 487);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Eagle");
		lblNewLabel.setFont(new Font("SimSun", Font.PLAIN, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(201, 10, 119, 33);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(31, 53, 451, 185);
		frame.getContentPane().add(panel);
		
		JLabel lblNewLabel_4 = new JLabel("Monster Information");
		lblNewLabel_4.setFont(new Font("SimSun", Font.BOLD, 14));
		lblNewLabel_4.setBounds(137, 0, 149, 31);
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
		
		JLabel lblNewLabel_23 = new JLabel("Fly");
		lblNewLabel_23.setBounds(183, 136, 54, 15);
		panel.add(lblNewLabel_23);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBounds(296, 29, 134, 119);
		panel.add(lblNewLabel_3);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setIcon(new ImageIcon(MonsterScreen.class.getResource("/GUI/images/eagle_resized.jpg")));
		
		JLabel lblNewLabel_1 = new JLabel("Rename Monster:");
		lblNewLabel_1.setFont(new Font("SimSun", Font.PLAIN, 14));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setBounds(31, 263, 112, 15);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(201, 260, 171, 21);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Add to team(Position):");
		lblNewLabel_2.setFont(new Font("SimSun", Font.PLAIN, 14));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2.setBounds(31, 309, 171, 15);
		frame.getContentPane().add(lblNewLabel_2);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
		comboBox.setBounds(235, 305, 34, 23);
		frame.getContentPane().add(comboBox);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.setBounds(279, 305, 93, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.setFont(new Font("SimSun", Font.PLAIN, 16));
		btnNewButton_1.setBounds(186, 357, 134, 49);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Remove");
		btnNewButton_2.setBounds(382, 305, 93, 23);
		frame.getContentPane().add(btnNewButton_2);
	}
}
