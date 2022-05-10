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
		
		JLabel shopLabel = new JLabel("Shop");
		shopLabel.setHorizontalAlignment(SwingConstants.CENTER);
		shopLabel.setFont(new Font("SimSun", Font.BOLD, 14));
		shopLabel.setBounds(528, 10, 103, 35);
		frame.getContentPane().add(shopLabel);
		
		JLabel goldLabel = new JLabel("Gold");
		goldLabel.setBounds(31, 20, 54, 15);
		frame.getContentPane().add(goldLabel);
		
		JLabel currentGold = new JLabel("500");
		currentGold.setBounds(95, 20, 54, 15);
		frame.getContentPane().add(currentGold);
		
		JPanel panel = new JPanel();
		panel.setBounds(31, 86, 667, 171);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel healingPotionLabel = new JLabel("Healing Potions");
		healingPotionLabel.setFont(new Font("SimSun", Font.PLAIN, 14));
		healingPotionLabel.setBounds(21, 10, 105, 15);
		panel.add(healingPotionLabel);
		
		JLabel attackPotionLabel = new JLabel("Attack Potions");
		attackPotionLabel.setFont(new Font("SimSun", Font.PLAIN, 14));
		attackPotionLabel.setBounds(21, 43, 105, 15);
		panel.add(attackPotionLabel);
		
		JButton buySmallHealingPotionButton = new JButton("Small-30");
		buySmallHealingPotionButton.setFont(new Font("SimSun", Font.PLAIN, 14));
		buySmallHealingPotionButton.setBounds(214, 6, 105, 23);
		panel.add(buySmallHealingPotionButton);
		
		JButton buyMediumHealingPotionButton = new JButton("Medium-50");
		buyMediumHealingPotionButton.setFont(new Font("SimSun", Font.PLAIN, 14));
		buyMediumHealingPotionButton.setBounds(378, 6, 105, 23);
		panel.add(buyMediumHealingPotionButton);
		
		JButton buyLargeHealingPotionButton = new JButton("Large-80");
		buyLargeHealingPotionButton.setFont(new Font("SimSun", Font.PLAIN, 14));
		buyLargeHealingPotionButton.setBounds(538, 6, 105, 23);
		panel.add(buyLargeHealingPotionButton);
		
		JButton buySmallAttackPotionButton = new JButton("Small-35");
		buySmallAttackPotionButton.setFont(new Font("SimSun", Font.PLAIN, 14));
		buySmallAttackPotionButton.setBounds(214, 39, 105, 23);
		panel.add(buySmallAttackPotionButton);
		
		JButton buyMediumAttackPotionButton = new JButton("Medium-60");
		buyMediumAttackPotionButton.setFont(new Font("SimSun", Font.PLAIN, 14));
		buyMediumAttackPotionButton.setBounds(378, 39, 105, 23);
		panel.add(buyMediumAttackPotionButton);
		
		JButton buyLargeAttackPotionButton = new JButton("Large-95");
		buyLargeAttackPotionButton.setFont(new Font("SimSun", Font.PLAIN, 14));
		buyLargeAttackPotionButton.setBounds(538, 39, 105, 23);
		panel.add(buyLargeAttackPotionButton);
		
		JLabel specialItemLabel = new JLabel("Special Items");
		specialItemLabel.setFont(new Font("SimSun", Font.PLAIN, 14));
		specialItemLabel.setBounds(21, 76, 105, 15);
		panel.add(specialItemLabel);
		
		JButton buySpecialItemButton = new JButton("Escape-300");
		buySpecialItemButton.setEnabled(false);
		buySpecialItemButton.setFont(new Font("SimSun", Font.PLAIN, 14));
		buySpecialItemButton.setBounds(214, 72, 105, 23);
		panel.add(buySpecialItemButton);
		
		JLabel monsterLabel = new JLabel("Monsters");
		monsterLabel.setFont(new Font("SimSun", Font.PLAIN, 14));
		monsterLabel.setBounds(21, 113, 105, 15);
		panel.add(monsterLabel);
		
		JComboBox monsterSelectionBox = new JComboBox();
		monsterSelectionBox.setFont(new Font("SimSun", Font.PLAIN, 14));
		monsterSelectionBox.setModel(new DefaultComboBoxModel(new String[] {"Monster 1, Max HP 500, Current HP 35, Damage 15-115, Flying, Price 125", "Monster 2, Max HP 250, Current HP 25, Damage 18-160, Price 90", "Monster 3, Max HP 650, Current HP 70, Damage 15-75, Price 100"}));
		monsterSelectionBox.setBounds(113, 109, 530, 23);
		panel.add(monsterSelectionBox);
		
		JButton buyMonsterButton = new JButton("Buy");
		buyMonsterButton.setFont(new Font("SimSun", Font.PLAIN, 14));
		buyMonsterButton.setBounds(325, 138, 93, 23);
		panel.add(buyMonsterButton);
		
		JLabel buyLabel = new JLabel("Buy");
		buyLabel.setFont(new Font("SimSun", Font.BOLD, 14));
		buyLabel.setBounds(31, 61, 54, 15);
		frame.getContentPane().add(buyLabel);
		
		JButton inventoryButton = new JButton("Inventory");
		inventoryButton.setBounds(184, 16, 93, 23);
		frame.getContentPane().add(inventoryButton);
		
		JLabel sellLabel = new JLabel("Sell");
		sellLabel.setFont(new Font("SimSun", Font.BOLD, 14));
		sellLabel.setBounds(31, 279, 54, 15);
		frame.getContentPane().add(sellLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(31, 311, 667, 171);
		frame.getContentPane().add(panel_1);
		
		JLabel sellHealingPotionLabel = new JLabel("Healing Potions");
		sellHealingPotionLabel.setFont(new Font("SimSun", Font.PLAIN, 14));
		sellHealingPotionLabel.setBounds(21, 10, 105, 15);
		panel_1.add(sellHealingPotionLabel);
		
		JLabel sellAttackPotionLabel = new JLabel("Attack Potions");
		sellAttackPotionLabel.setFont(new Font("SimSun", Font.PLAIN, 14));
		sellAttackPotionLabel.setBounds(21, 43, 105, 15);
		panel_1.add(sellAttackPotionLabel);
		
		JButton sellSmallHealingPotionButton = new JButton("Small-20");
		sellSmallHealingPotionButton.setFont(new Font("SimSun", Font.PLAIN, 14));
		sellSmallHealingPotionButton.setBounds(214, 6, 105, 23);
		panel_1.add(sellSmallHealingPotionButton);
		
		JButton sellMediumHealingPotionButton = new JButton("Medium-25");
		sellMediumHealingPotionButton.setFont(new Font("SimSun", Font.PLAIN, 14));
		sellMediumHealingPotionButton.setBounds(378, 6, 105, 23);
		panel_1.add(sellMediumHealingPotionButton);
		
		JButton sellLargeHealingPotionButton = new JButton("Large-40");
		sellLargeHealingPotionButton.setFont(new Font("SimSun", Font.PLAIN, 14));
		sellLargeHealingPotionButton.setBounds(538, 6, 105, 23);
		panel_1.add(sellLargeHealingPotionButton);
		
		JButton sellSmallAttackPotionButton = new JButton("Small-25");
		sellSmallAttackPotionButton.setFont(new Font("SimSun", Font.PLAIN, 14));
		sellSmallAttackPotionButton.setBounds(214, 39, 105, 23);
		panel_1.add(sellSmallAttackPotionButton);
		
		JButton sellMediumAttackPotionButton = new JButton("Medium-30");
		sellMediumAttackPotionButton.setFont(new Font("SimSun", Font.PLAIN, 14));
		sellMediumAttackPotionButton.setBounds(378, 39, 105, 23);
		panel_1.add(sellMediumAttackPotionButton);
		
		JButton sellLargeAttackPotionButton = new JButton("Large-45");
		sellLargeAttackPotionButton.setFont(new Font("SimSun", Font.PLAIN, 14));
		sellLargeAttackPotionButton.setBounds(538, 39, 105, 23);
		panel_1.add(sellLargeAttackPotionButton);
		
		JLabel sellSpecialItemLabel = new JLabel("Special Items");
		sellSpecialItemLabel.setFont(new Font("SimSun", Font.PLAIN, 14));
		sellSpecialItemLabel.setBounds(21, 76, 105, 15);
		panel_1.add(sellSpecialItemLabel);
		
		JButton sellSpecialItemButton = new JButton("Escape-300");
		sellSpecialItemButton.setFont(new Font("SimSun", Font.PLAIN, 14));
		sellSpecialItemButton.setBounds(214, 72, 105, 23);
		panel_1.add(sellSpecialItemButton);
		
		JLabel sellMonsterLabel = new JLabel("Monsters");
		sellMonsterLabel.setFont(new Font("SimSun", Font.PLAIN, 14));
		sellMonsterLabel.setBounds(21, 113, 105, 15);
		panel_1.add(sellMonsterLabel);
		
		JComboBox sellMonsterSelectionBox = new JComboBox();
		sellMonsterSelectionBox.setModel(new DefaultComboBoxModel(new String[] {"Goat, Sell Price 300"}));
		sellMonsterSelectionBox.setFont(new Font("SimSun", Font.PLAIN, 14));
		sellMonsterSelectionBox.setBounds(113, 109, 530, 23);
		panel_1.add(sellMonsterSelectionBox);
		
		JButton sellMonsterButton = new JButton("Sell");
		sellMonsterButton.setFont(new Font("SimSun", Font.PLAIN, 14));
		sellMonsterButton.setBounds(325, 138, 93, 23);
		panel_1.add(sellMonsterButton);
		
		JLabel smallHealingPotionQuantity = new JLabel("2");
		smallHealingPotionQuantity.setBounds(329, 10, 17, 15);
		panel_1.add(smallHealingPotionQuantity);
		
		JLabel mediumHealingPotionQuantity = new JLabel("0");
		mediumHealingPotionQuantity.setBounds(487, 10, 54, 15);
		panel_1.add(mediumHealingPotionQuantity);
		
		JLabel largeHealingPotionQuantity = new JLabel("2");
		largeHealingPotionQuantity.setBounds(650, 10, 17, 15);
		panel_1.add(largeHealingPotionQuantity);
		
		JLabel smallAttackPotionQuantity = new JLabel("1");
		smallAttackPotionQuantity.setBounds(329, 43, 17, 15);
		panel_1.add(smallAttackPotionQuantity);
		
		JLabel specialItemQuantity = new JLabel("2");
		specialItemQuantity.setBounds(329, 76, 17, 15);
		panel_1.add(specialItemQuantity);
		
		JLabel mediumAttackPotionQuantity = new JLabel("5");
		mediumAttackPotionQuantity.setBounds(487, 43, 17, 15);
		panel_1.add(mediumAttackPotionQuantity);
		
		JLabel largeAttackPotionQuantity = new JLabel("2");
		largeAttackPotionQuantity.setBounds(650, 43, 17, 15);
		panel_1.add(largeAttackPotionQuantity);
		
		JLabel lblNewLabel_9_2 = new JLabel("2");
		lblNewLabel_9_2.setBounds(677, 321, 54, 15);
		frame.getContentPane().add(lblNewLabel_9_2);
		
		JButton mainScreenButton = new JButton("Main Screen");
		mainScreenButton.setBounds(287, 16, 119, 23);
		frame.getContentPane().add(mainScreenButton);
	}
}
