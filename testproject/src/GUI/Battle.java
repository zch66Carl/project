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
		
		JLabel battleType = new JLabel("Wild Battle");
		battleType.setHorizontalAlignment(SwingConstants.CENTER);
		battleType.setFont(new Font("SimSun", Font.BOLD, 14));
		battleType.setBounds(27, 10, 165, 41);
		frame.getContentPane().add(battleType);
		
		JPanel panel = new JPanel();
		panel.setBounds(27, 59, 487, 466);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel playerMonsterIcon = new JLabel("");
		playerMonsterIcon.setIcon(new ImageIcon(Battle.class.getResource("/GUI/images/goat_resized.jpg")));
		playerMonsterIcon.setBounds(185, 369, 60, 60);
		panel.add(playerMonsterIcon);
		
		JLabel enemyMonsterIcon = new JLabel("");
		enemyMonsterIcon.setIcon(new ImageIcon(Battle.class.getResource("/GUI/images/eagle_resized.jpg")));
		enemyMonsterIcon.setBounds(185, 46, 60, 60);
		panel.add(enemyMonsterIcon);
		
		JLabel enemyActionDisplay = new JLabel("miss");
		enemyActionDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		enemyActionDisplay.setBounds(185, 152, 54, 15);
		panel.add(enemyActionDisplay);
		
		JLabel playerActionDisplay = new JLabel("-35");
		playerActionDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		playerActionDisplay.setBounds(185, 319, 54, 15);
		panel.add(playerActionDisplay);
		
		JLabel enemyCurrentHealth = new JLabel("25");
		enemyCurrentHealth.setHorizontalAlignment(SwingConstants.RIGHT);
		enemyCurrentHealth.setBounds(143, 21, 54, 15);
		panel.add(enemyCurrentHealth);
		
		JLabel enemyMaxHealth = new JLabel("65");
		enemyMaxHealth.setBounds(223, 21, 54, 15);
		panel.add(enemyMaxHealth);
		
		JLabel lblNewLabel_6 = new JLabel("/");
		lblNewLabel_6.setBounds(207, 21, 14, 15);
		panel.add(lblNewLabel_6);
		
		JLabel playerCurrentHealth = new JLabel("45");
		playerCurrentHealth.setHorizontalAlignment(SwingConstants.RIGHT);
		playerCurrentHealth.setBounds(143, 441, 54, 15);
		panel.add(playerCurrentHealth);
		
		JLabel lblNewLabel_6_1 = new JLabel("/");
		lblNewLabel_6_1.setBounds(207, 441, 14, 15);
		panel.add(lblNewLabel_6_1);
		
		JLabel playerMaxHealth = new JLabel("77");
		playerMaxHealth.setBounds(223, 441, 54, 15);
		panel.add(playerMaxHealth);
		
		JButton attackButton = new JButton("Attack");
		attackButton.setBounds(564, 327, 177, 41);
		frame.getContentPane().add(attackButton);
		
		JComboBox itemSelectionDropDownBox = new JComboBox();
		itemSelectionDropDownBox.setBackground(Color.WHITE);
		itemSelectionDropDownBox.setModel(new DefaultComboBoxModel(new String[] {"Small Healing Potion", "Medium Healing Potion", "Large Healing Potion", "Small Attack Potion", "Medium Attack Potion", "Large Attack Potion"}));
		itemSelectionDropDownBox.setBounds(564, 378, 177, 23);
		frame.getContentPane().add(itemSelectionDropDownBox);
		
		JLabel itemQuantity = new JLabel("2");
		itemQuantity.setBounds(751, 382, 54, 15);
		frame.getContentPane().add(itemQuantity);
		
		JButton useItemButton = new JButton("Use Item");
		useItemButton.setBounds(564, 411, 177, 41);
		frame.getContentPane().add(useItemButton);
		
		JButton escapeBattleButton = new JButton("Escape Battle");
		escapeBattleButton.setBounds(564, 484, 177, 41);
		frame.getContentPane().add(escapeBattleButton);
		
		JButton changeMonsterButton = new JButton("Change Monster");
		changeMonsterButton.setBounds(564, 276, 177, 41);
		frame.getContentPane().add(changeMonsterButton);
	}
}
