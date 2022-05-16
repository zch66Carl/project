package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;

import testproject.GameEnvironment;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ItemScreen {

	private JFrame frame;
	private GameEnvironment env;
	
	public ItemScreen(GameEnvironment incomingEnv) {
		env = incomingEnv;
		initialize();
		frame.setVisible(true);
	}
	
	public void closeWindow() {
		frame.dispose();
	}
	
	public void finishedWindow() {
		env.closeItemsScreen(this);
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ItemScreen window = new ItemScreen();
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
	public ItemScreen() {
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
		
		JLabel itemScreenLabel = new JLabel("Player 1's items");
		itemScreenLabel.setFont(new Font("SimSun", Font.PLAIN, 14));
		itemScreenLabel.setBounds(45, 10, 140, 35);
		frame.getContentPane().add(itemScreenLabel);
		
		JLabel smallHealingPotionLabel = new JLabel("Small Healing Potion");
		smallHealingPotionLabel.setBounds(45, 55, 128, 35);
		frame.getContentPane().add(smallHealingPotionLabel);
		
		JLabel mediumHealingPotionLabel = new JLabel("Medium Healing Potion");
		mediumHealingPotionLabel.setBounds(45, 100, 128, 35);
		frame.getContentPane().add(mediumHealingPotionLabel);
		
		JLabel largeHealingPotionLabel = new JLabel("Large Healing Potion");
		largeHealingPotionLabel.setBounds(45, 145, 128, 35);
		frame.getContentPane().add(largeHealingPotionLabel);
		
		JLabel smallAttackPotionLabel = new JLabel("Small Attack Potion");
		smallAttackPotionLabel.setBounds(266, 55, 128, 35);
		frame.getContentPane().add(smallAttackPotionLabel);
		
		JLabel mediumAttackPotionLabel = new JLabel("Medium Attack Potion");
		mediumAttackPotionLabel.setBounds(266, 100, 128, 35);
		frame.getContentPane().add(mediumAttackPotionLabel);
		
		JLabel largeAttackPotionLabel = new JLabel("Large Attack Potion");
		largeAttackPotionLabel.setBounds(266, 145, 128, 35);
		frame.getContentPane().add(largeAttackPotionLabel);
		
		JLabel smallHealingPotionAmount = new JLabel("1");
		smallHealingPotionAmount.setBounds(202, 65, 54, 15);
		frame.getContentPane().add(smallHealingPotionAmount);
		
		JLabel mediumHealingPotionAmount = new JLabel("3");
		mediumHealingPotionAmount.setBounds(202, 110, 54, 15);
		frame.getContentPane().add(mediumHealingPotionAmount);
		
		JLabel largeHealingPotionAmount = new JLabel("5");
		largeHealingPotionAmount.setBounds(202, 155, 54, 15);
		frame.getContentPane().add(largeHealingPotionAmount);
		
		JLabel smallAttackPotionAmount = new JLabel("12");
		smallAttackPotionAmount.setBounds(404, 65, 54, 15);
		frame.getContentPane().add(smallAttackPotionAmount);
		
		JLabel mediumAttackPotionAmount = new JLabel("2");
		mediumAttackPotionAmount.setBounds(404, 110, 54, 15);
		frame.getContentPane().add(mediumAttackPotionAmount);
		
		JLabel largeAttackPotionAmount = new JLabel("0");
		largeAttackPotionAmount.setBounds(404, 155, 54, 15);
		frame.getContentPane().add(largeAttackPotionAmount);
		
		JLabel specialItemLabel = new JLabel("Special Item");
		specialItemLabel.setBounds(468, 65, 82, 15);
		frame.getContentPane().add(specialItemLabel);
		
		JLabel specialItemAmount = new JLabel("2");
		specialItemAmount.setBounds(600, 65, 54, 15);
		frame.getContentPane().add(specialItemAmount);
		
		JButton shopButton = new JButton("Shop");
		shopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				env.launchShopScreen();
				closeWindow();
			}
		});
		shopButton.setBounds(123, 213, 133, 29);
		frame.getContentPane().add(shopButton);
		
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		backButton.setBounds(417, 213, 133, 29);
		frame.getContentPane().add(backButton);
	}
}
