package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Shop {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Shop window = new Shop();
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
	public Shop() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 747, 603);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Shop");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("SimSun", Font.BOLD, 14));
		lblNewLabel.setBounds(528, 10, 103, 35);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Gold");
		lblNewLabel_1.setBounds(31, 20, 54, 15);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("500");
		lblNewLabel_2.setBounds(95, 20, 54, 15);
		frame.getContentPane().add(lblNewLabel_2);
		
		JPanel panel = new JPanel();
		panel.setBounds(31, 86, 667, 171);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Healing Potions");
		lblNewLabel_3.setFont(new Font("SimSun", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(21, 10, 105, 15);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Attack Potions");
		lblNewLabel_4.setFont(new Font("SimSun", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(21, 43, 105, 15);
		panel.add(lblNewLabel_4);
		
		JButton btnNewButton = new JButton("Small-30");
		btnNewButton.setFont(new Font("SimSun", Font.PLAIN, 14));
		btnNewButton.setBounds(214, 6, 105, 23);
		panel.add(btnNewButton);
		
		JButton btnMedium = new JButton("Medium-50");
		btnMedium.setFont(new Font("SimSun", Font.PLAIN, 14));
		btnMedium.setBounds(378, 6, 105, 23);
		panel.add(btnMedium);
		
		JButton btnLarge = new JButton("Large-80");
		btnLarge.setFont(new Font("SimSun", Font.PLAIN, 14));
		btnLarge.setBounds(538, 6, 105, 23);
		panel.add(btnLarge);
		
		JButton btnSmall = new JButton("Small-35");
		btnSmall.setFont(new Font("SimSun", Font.PLAIN, 14));
		btnSmall.setBounds(214, 39, 105, 23);
		panel.add(btnSmall);
		
		JButton btnMedium_1 = new JButton("Medium-60");
		btnMedium_1.setFont(new Font("SimSun", Font.PLAIN, 14));
		btnMedium_1.setBounds(378, 39, 105, 23);
		panel.add(btnMedium_1);
		
		JButton btnLarge_1 = new JButton("Large-95");
		btnLarge_1.setFont(new Font("SimSun", Font.PLAIN, 14));
		btnLarge_1.setBounds(538, 39, 105, 23);
		panel.add(btnLarge_1);
		
		JLabel lblNewLabel_6 = new JLabel("Special Items");
		lblNewLabel_6.setFont(new Font("SimSun", Font.PLAIN, 14));
		lblNewLabel_6.setBounds(21, 76, 105, 15);
		panel.add(lblNewLabel_6);
		
		JButton btnNewButton_2 = new JButton("Escape-300");
		btnNewButton_2.setEnabled(false);
		btnNewButton_2.setFont(new Font("SimSun", Font.PLAIN, 14));
		btnNewButton_2.setBounds(214, 72, 105, 23);
		panel.add(btnNewButton_2);
		
		JLabel lblNewLabel_7 = new JLabel("Monsters");
		lblNewLabel_7.setFont(new Font("SimSun", Font.PLAIN, 14));
		lblNewLabel_7.setBounds(21, 113, 105, 15);
		panel.add(lblNewLabel_7);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("SimSun", Font.PLAIN, 14));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Monster 1, Max HP 500, Current HP 35, Damage 15-115, Flying, Price 125", "Monster 2, Max HP 250, Current HP 25, Damage 18-160, Price 90", "Monster 3, Max HP 650, Current HP 70, Damage 15-75, Price 100"}));
		comboBox.setBounds(113, 109, 530, 23);
		panel.add(comboBox);
		
		JButton btnNewButton_3 = new JButton("Buy");
		btnNewButton_3.setFont(new Font("SimSun", Font.PLAIN, 14));
		btnNewButton_3.setBounds(325, 138, 93, 23);
		panel.add(btnNewButton_3);
		
		JLabel lblNewLabel_5 = new JLabel("Buy");
		lblNewLabel_5.setFont(new Font("SimSun", Font.BOLD, 14));
		lblNewLabel_5.setBounds(31, 61, 54, 15);
		frame.getContentPane().add(lblNewLabel_5);
		
		JButton btnNewButton_1 = new JButton("Inventory");
		btnNewButton_1.setBounds(184, 16, 93, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_8 = new JLabel("Sell");
		lblNewLabel_8.setFont(new Font("SimSun", Font.BOLD, 14));
		lblNewLabel_8.setBounds(31, 279, 54, 15);
		frame.getContentPane().add(lblNewLabel_8);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(31, 311, 667, 171);
		frame.getContentPane().add(panel_1);
		
		JLabel lblNewLabel_3_1 = new JLabel("Healing Potions");
		lblNewLabel_3_1.setFont(new Font("SimSun", Font.PLAIN, 14));
		lblNewLabel_3_1.setBounds(21, 10, 105, 15);
		panel_1.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_4_1 = new JLabel("Attack Potions");
		lblNewLabel_4_1.setFont(new Font("SimSun", Font.PLAIN, 14));
		lblNewLabel_4_1.setBounds(21, 43, 105, 15);
		panel_1.add(lblNewLabel_4_1);
		
		JButton btnSmall_2 = new JButton("Small-20");
		btnSmall_2.setFont(new Font("SimSun", Font.PLAIN, 14));
		btnSmall_2.setBounds(214, 6, 105, 23);
		panel_1.add(btnSmall_2);
		
		JButton btnMedium_3 = new JButton("Medium-25");
		btnMedium_3.setFont(new Font("SimSun", Font.PLAIN, 14));
		btnMedium_3.setBounds(378, 6, 105, 23);
		panel_1.add(btnMedium_3);
		
		JButton btnLarge_3 = new JButton("Large-40");
		btnLarge_3.setFont(new Font("SimSun", Font.PLAIN, 14));
		btnLarge_3.setBounds(538, 6, 105, 23);
		panel_1.add(btnLarge_3);
		
		JButton btnSmall_3 = new JButton("Small-25");
		btnSmall_3.setFont(new Font("SimSun", Font.PLAIN, 14));
		btnSmall_3.setBounds(214, 39, 105, 23);
		panel_1.add(btnSmall_3);
		
		JButton btnMedium_1_1 = new JButton("Medium-30");
		btnMedium_1_1.setFont(new Font("SimSun", Font.PLAIN, 14));
		btnMedium_1_1.setBounds(378, 39, 105, 23);
		panel_1.add(btnMedium_1_1);
		
		JButton btnLarge_1_1 = new JButton("Large-45");
		btnLarge_1_1.setFont(new Font("SimSun", Font.PLAIN, 14));
		btnLarge_1_1.setBounds(538, 39, 105, 23);
		panel_1.add(btnLarge_1_1);
		
		JLabel lblNewLabel_6_1 = new JLabel("Special Items");
		lblNewLabel_6_1.setFont(new Font("SimSun", Font.PLAIN, 14));
		lblNewLabel_6_1.setBounds(21, 76, 105, 15);
		panel_1.add(lblNewLabel_6_1);
		
		JButton btnNewButton_2_1 = new JButton("Escape-300");
		btnNewButton_2_1.setFont(new Font("SimSun", Font.PLAIN, 14));
		btnNewButton_2_1.setBounds(214, 72, 105, 23);
		panel_1.add(btnNewButton_2_1);
		
		JLabel lblNewLabel_7_1 = new JLabel("Monsters");
		lblNewLabel_7_1.setFont(new Font("SimSun", Font.PLAIN, 14));
		lblNewLabel_7_1.setBounds(21, 113, 105, 15);
		panel_1.add(lblNewLabel_7_1);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Goat, Sell Price 300"}));
		comboBox_1.setFont(new Font("SimSun", Font.PLAIN, 14));
		comboBox_1.setBounds(113, 109, 530, 23);
		panel_1.add(comboBox_1);
		
		JButton btnNewButton_3_1 = new JButton("Sell");
		btnNewButton_3_1.setFont(new Font("SimSun", Font.PLAIN, 14));
		btnNewButton_3_1.setBounds(325, 138, 93, 23);
		panel_1.add(btnNewButton_3_1);
		
		JLabel lblNewLabel_9 = new JLabel("2");
		lblNewLabel_9.setBounds(329, 10, 17, 15);
		panel_1.add(lblNewLabel_9);
		
		JLabel lblNewLabel_9_1 = new JLabel("0");
		lblNewLabel_9_1.setBounds(487, 10, 54, 15);
		panel_1.add(lblNewLabel_9_1);
		
		JLabel lblNewLabel_9_3 = new JLabel("2");
		lblNewLabel_9_3.setBounds(650, 10, 17, 15);
		panel_1.add(lblNewLabel_9_3);
		
		JLabel lblNewLabel_9_4 = new JLabel("1");
		lblNewLabel_9_4.setBounds(329, 43, 17, 15);
		panel_1.add(lblNewLabel_9_4);
		
		JLabel lblNewLabel_9_5 = new JLabel("2");
		lblNewLabel_9_5.setBounds(329, 76, 17, 15);
		panel_1.add(lblNewLabel_9_5);
		
		JLabel lblNewLabel_9_6 = new JLabel("5");
		lblNewLabel_9_6.setBounds(487, 43, 17, 15);
		panel_1.add(lblNewLabel_9_6);
		
		JLabel lblNewLabel_9_7 = new JLabel("2");
		lblNewLabel_9_7.setBounds(650, 43, 17, 15);
		panel_1.add(lblNewLabel_9_7);
		
		JLabel lblNewLabel_9_2 = new JLabel("2");
		lblNewLabel_9_2.setBounds(677, 321, 54, 15);
		frame.getContentPane().add(lblNewLabel_9_2);
		
		JButton btnNewButton_4 = new JButton("Main Screen");
		btnNewButton_4.setBounds(287, 16, 119, 23);
		frame.getContentPane().add(btnNewButton_4);
	}
}
