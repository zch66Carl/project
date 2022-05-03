package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Battle {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Battle window = new Battle();
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
	public Battle() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 879, 612);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Wild Battle");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("SimSun", Font.BOLD, 14));
		lblNewLabel.setBounds(27, 10, 165, 41);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(27, 59, 487, 466);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Battle.class.getResource("/GUI/images/goat_resized.jpg")));
		lblNewLabel_2.setBounds(185, 369, 60, 60);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("");
		lblNewLabel_2_1.setIcon(new ImageIcon(Battle.class.getResource("/GUI/images/eagle_resized.jpg")));
		lblNewLabel_2_1.setBounds(185, 46, 60, 60);
		panel.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_1 = new JLabel("miss");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(185, 152, 54, 15);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("-35");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(185, 319, 54, 15);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_5 = new JLabel("25");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_5.setBounds(143, 21, 54, 15);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_5_1 = new JLabel("65");
		lblNewLabel_5_1.setBounds(223, 21, 54, 15);
		panel.add(lblNewLabel_5_1);
		
		JLabel lblNewLabel_6 = new JLabel("/");
		lblNewLabel_6.setBounds(207, 21, 14, 15);
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_5_2 = new JLabel("45");
		lblNewLabel_5_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_5_2.setBounds(143, 441, 54, 15);
		panel.add(lblNewLabel_5_2);
		
		JLabel lblNewLabel_6_1 = new JLabel("/");
		lblNewLabel_6_1.setBounds(207, 441, 14, 15);
		panel.add(lblNewLabel_6_1);
		
		JLabel lblNewLabel_5_1_1 = new JLabel("77");
		lblNewLabel_5_1_1.setBounds(223, 441, 54, 15);
		panel.add(lblNewLabel_5_1_1);
		
		JButton btnNewButton = new JButton("Attack");
		btnNewButton.setBounds(564, 327, 177, 41);
		frame.getContentPane().add(btnNewButton);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBackground(Color.WHITE);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Small Healing Potion", "Medium Healing Potion", "Large Healing Potion", "Small Attack Potion", "Medium Attack Potion", "Large Attack Potion"}));
		comboBox.setBounds(564, 378, 177, 23);
		frame.getContentPane().add(comboBox);
		
		JLabel lblNewLabel_4 = new JLabel("2");
		lblNewLabel_4.setBounds(751, 382, 54, 15);
		frame.getContentPane().add(lblNewLabel_4);
		
		JButton btnUseItem = new JButton("Use Item");
		btnUseItem.setBounds(564, 411, 177, 41);
		frame.getContentPane().add(btnUseItem);
		
		JButton btnEscapeBattle = new JButton("Escape Battle");
		btnEscapeBattle.setBounds(564, 484, 177, 41);
		frame.getContentPane().add(btnEscapeBattle);
		
		JButton btnChangeMonster = new JButton("Change Monster");
		btnChangeMonster.setBounds(564, 276, 177, 41);
		frame.getContentPane().add(btnChangeMonster);
	}
}
