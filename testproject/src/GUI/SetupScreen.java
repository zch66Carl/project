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
	private JTextField playerNameTextBox;
	private JTextField renameMonsterTextBox;

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
		
		JLabel welcomeMessage = new JLabel("Welcome to Monster Battles");
		welcomeMessage.setFont(new Font("SimSun", Font.BOLD, 18));
		welcomeMessage.setBounds(37, 10, 266, 43);
		frame.getContentPane().add(welcomeMessage);
		
		JLabel playerNameLabel = new JLabel("Please Enter Player Name:\r\n\r\n");
		playerNameLabel.setToolTipText("");
		playerNameLabel.setFont(new Font("SimSun", Font.PLAIN, 14));
		playerNameLabel.setBounds(37, 63, 175, 43);
		frame.getContentPane().add(playerNameLabel);
		
		playerNameTextBox = new JTextField();
		playerNameTextBox.setToolTipText("3 to 15 characters without numbers or special character");
		playerNameTextBox.setBounds(243, 74, 121, 21);
		frame.getContentPane().add(playerNameTextBox);
		playerNameTextBox.setColumns(10);
		
		JLabel daysLabel = new JLabel("Days:");
		daysLabel.setFont(new Font("SimSun", Font.PLAIN, 14));
		daysLabel.setBounds(436, 70, 40, 29);
		frame.getContentPane().add(daysLabel);
		
		JComboBox daysSelection = new JComboBox();
		daysSelection.setModel(new DefaultComboBoxModel(new String[] {"5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"}));
		daysSelection.setBounds(486, 73, 34, 23);
		frame.getContentPane().add(daysSelection);
		
		JLabel startingMonsterLabel = new JLabel("Choose starting monsters:");
		startingMonsterLabel.setFont(new Font("SimSun", Font.PLAIN, 14));
		startingMonsterLabel.setBounds(37, 116, 175, 29);
		frame.getContentPane().add(startingMonsterLabel);
		
		JComboBox startingMonsterSelection = new JComboBox();
		startingMonsterSelection.setModel(new DefaultComboBoxModel(new String[] {"Goat", "Tiger", "Monkey"}));
		startingMonsterSelection.setBounds(243, 119, 121, 23);
		frame.getContentPane().add(startingMonsterSelection);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(432, 120, 357, 185);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel monsterInfoLabel = new JLabel("Monster Information");
		monsterInfoLabel.setFont(new Font("SimSun", Font.BOLD, 14));
		monsterInfoLabel.setBounds(105, 0, 149, 31);
		panel.add(monsterInfoLabel);
		
		JLabel maxHealthLabel = new JLabel("Max Health:");
		maxHealthLabel.setBounds(10, 29, 73, 25);
		panel.add(maxHealthLabel);
		
		JLabel damageLabel = new JLabel("Damage:");
		damageLabel.setBounds(10, 64, 73, 25);
		panel.add(damageLabel);
		
		JLabel currentHealthLabel = new JLabel("Current Health:");
		currentHealthLabel.setBounds(10, 102, 90, 15);
		panel.add(currentHealthLabel);
		
		JLabel abilityLabel = new JLabel("Ability:");
		abilityLabel.setBounds(10, 136, 84, 15);
		panel.add(abilityLabel);
		
		JLabel maxHealth = new JLabel("800");
		maxHealth.setBounds(183, 34, 54, 15);
		panel.add(maxHealth);
		
		JLabel damage = new JLabel("15-150");
		damage.setBounds(183, 69, 54, 15);
		panel.add(damage);
		
		JLabel currentHealth = new JLabel("35");
		currentHealth.setBounds(183, 102, 54, 15);
		panel.add(currentHealth);
		
		JLabel ability = new JLabel("None");
		ability.setBounds(183, 136, 54, 15);
		panel.add(ability);
		
		JButton addToTeamButton = new JButton("Add to team");
		addToTeamButton.setBounds(243, 152, 109, 23);
		frame.getContentPane().add(addToTeamButton);
		
		JLabel lblNewLabel_9 = new JLabel("(Number of days the game will last)");
		lblNewLabel_9.setFont(new Font("SimSun", Font.PLAIN, 14));
		lblNewLabel_9.setBounds(530, 77, 259, 15);
		frame.getContentPane().add(lblNewLabel_9);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(37, 185, 327, 120);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel chosenMonsterLabel = new JLabel("Chosen Monster");
		chosenMonsterLabel.setHorizontalAlignment(SwingConstants.CENTER);
		chosenMonsterLabel.setFont(new Font("SimSun", Font.BOLD, 14));
		chosenMonsterLabel.setBounds(67, 0, 168, 31);
		panel_1.add(chosenMonsterLabel);
		
		JLabel monsterName = new JLabel("Goat");
		monsterName.setFont(new Font("SimSun", Font.PLAIN, 14));
		monsterName.setBounds(25, 49, 54, 15);
		panel_1.add(monsterName);
		
		renameMonsterTextBox = new JTextField();
		renameMonsterTextBox.setBounds(90, 46, 66, 21);
		panel_1.add(renameMonsterTextBox);
		renameMonsterTextBox.setColumns(10);
		
		JButton renameMonsterButton = new JButton("Rename Monster");
		renameMonsterButton.setFont(new Font("SimSun", Font.PLAIN, 14));
		renameMonsterButton.setBounds(166, 45, 151, 23);
		panel_1.add(renameMonsterButton);
		
		JLabel difficultyLabel = new JLabel("Select Difficulty:");
		difficultyLabel.setFont(new Font("SimSun", Font.PLAIN, 14));
		difficultyLabel.setBounds(37, 333, 141, 29);
		frame.getContentPane().add(difficultyLabel);
		
		JComboBox difficultySelection = new JComboBox();
		difficultySelection.setModel(new DefaultComboBoxModel(new String[] {"Easy", "Normal", "Hard"}));
		difficultySelection.setBounds(243, 336, 121, 23);
		frame.getContentPane().add(difficultySelection);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(37, 391, 327, 143);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel difficulty = new JLabel("Easy");
		difficulty.setFont(new Font("SimSun", Font.BOLD, 14));
		difficulty.setBounds(133, 0, 55, 24);
		panel_2.add(difficulty);
		
		JLabel startingGoldLabel = new JLabel("Starting Gold:");
		startingGoldLabel.setBounds(10, 29, 84, 15);
		panel_2.add(startingGoldLabel);
		
		JLabel goldWinLabel = new JLabel("Gold Win(Based on Level):");
		goldWinLabel.setBounds(10, 65, 171, 15);
		panel_2.add(goldWinLabel);
		
		JLabel enemyStrengthLabel = new JLabel("Enemy Strength(%Increase):");
		enemyStrengthLabel.setBounds(10, 106, 171, 15);
		panel_2.add(enemyStrengthLabel);
		
		JLabel startingGold = new JLabel("500");
		startingGold.setBounds(191, 29, 54, 15);
		panel_2.add(startingGold);
		
		JLabel goldWinPercentage = new JLabel("100%");
		goldWinPercentage.setBounds(191, 65, 54, 15);
		panel_2.add(goldWinPercentage);
		
		JLabel enemyStrengthIncreasePercentage = new JLabel("75%");
		enemyStrengthIncreasePercentage.setBounds(191, 106, 54, 15);
		panel_2.add(enemyStrengthIncreasePercentage);
		
		JButton startGameButton = new JButton("Start Game");
		startGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		startGameButton.setFont(new Font("SimSun", Font.PLAIN, 18));
		startGameButton.setBounds(436, 391, 278, 85);
		frame.getContentPane().add(startGameButton);
	}
}
