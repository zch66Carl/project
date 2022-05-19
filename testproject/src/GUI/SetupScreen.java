package GUI;
import java.awt.EventQueue;
import testproject.Generation;
import testproject.Item;
import testproject.Player;

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
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import testproject.GameEnvironment;
import testproject.monsters.Monster;

import javax.swing.JList;
public class SetupScreen {

	private JFrame frame;
	private JTextField playerNameTextBox;
	private GameEnvironment env;
	
	public SetupScreen(GameEnvironment incomingEnv) {
		env = incomingEnv;
		initialize();
		frame.setVisible(true);
	}
	
	public void closeWindow() {
		frame.dispose();
	}
	
	public void finishedWindow() {
		env.closeSetupScreen(this);
	}

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
		
		Monster monsterOne = Generation.generateMonster(0, 0, false, false);
		Monster monsterTwo = Generation.generateMonster(0, 0, false, false);
		Monster monsterThree = Generation.generateMonster(0, 0, false, false);
		JComboBox startingMonsterSelection = new JComboBox();
		startingMonsterSelection.setModel(new DefaultComboBoxModel(new Monster[] {monsterOne,monsterTwo,monsterThree}));
		startingMonsterSelection.setSelectedIndex(0);
		startingMonsterSelection.setBounds(243, 119, 121, 23);
		frame.getContentPane().add(startingMonsterSelection);
		
		
			
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(37, 203, 357, 185);
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
		
		Monster selected = new Monster("Default Monster", 1);
		selected = (Monster) startingMonsterSelection.getSelectedItem();
		JLabel maxHealth = new JLabel(Integer.toString(selected.getMaxHealth()));
		maxHealth.setBounds(183, 34, 54, 15);
		panel.add(maxHealth);
		
		JLabel damage = new JLabel(Integer.toString(selected.getDamage()));
		damage.setBounds(183, 69, 54, 15);
		panel.add(damage);
		
		JLabel currentHealth = new JLabel(Integer.toString(selected.getHealth()));
		currentHealth.setBounds(183, 102, 54, 15);
		panel.add(currentHealth);
		
		
		JLabel ability = new JLabel("None");
		ability.setBounds(183, 136, 54, 15);
		panel.add(ability);
		
		JLabel lblNewLabel_9 = new JLabel("(Number of days the game will last)");
		lblNewLabel_9.setFont(new Font("SimSun", Font.PLAIN, 14));
		lblNewLabel_9.setBounds(530, 77, 259, 15);
		frame.getContentPane().add(lblNewLabel_9);
		
		JLabel difficultyLabel = new JLabel("Select Difficulty:");
		difficultyLabel.setFont(new Font("SimSun", Font.PLAIN, 14));
		difficultyLabel.setBounds(436, 116, 141, 29);
		frame.getContentPane().add(difficultyLabel);
		
		JComboBox difficultySelection = new JComboBox();
		difficultySelection.setModel(new DefaultComboBoxModel(new String[] {"Easy", "Normal", "Hard"}));
		difficultySelection.setBounds(587, 119, 121, 23);
		frame.getContentPane().add(difficultySelection);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_2.setBounds(436, 203, 418, 185);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel difficulty = new JLabel("Easy");
		difficulty.setFont(new Font("SimSun", Font.BOLD, 14));
		difficulty.setBounds(190, 10, 55, 24);
		panel_2.add(difficulty);
		
		JLabel startingGoldLabel = new JLabel("Starting Gold:");
		startingGoldLabel.setBounds(10, 49, 84, 15);
		panel_2.add(startingGoldLabel);
		
		JLabel goldWinLabel = new JLabel("Gold Win(Based on Level):");
		goldWinLabel.setBounds(10, 85, 171, 15);
		panel_2.add(goldWinLabel);
		
		JLabel enemyStrengthLabel = new JLabel("Enemy Strength:");
		enemyStrengthLabel.setBounds(10, 126, 171, 15);
		panel_2.add(enemyStrengthLabel);
		
		JLabel startingGold = new JLabel("500");
		startingGold.setBounds(191, 49, 54, 15);
		panel_2.add(startingGold);
		
		JLabel goldWinPercentage = new JLabel("150%");
		goldWinPercentage.setBounds(191, 85, 54, 15);
		panel_2.add(goldWinPercentage);
		
		JLabel enemyStrengthIncreasePercentage = new JLabel("75%");
		enemyStrengthIncreasePercentage.setBounds(191, 126, 54, 15);
		panel_2.add(enemyStrengthIncreasePercentage);
		
		JLabel lblNormal = new JLabel("Normal");
		lblNormal.setFont(new Font("SimSun", Font.BOLD, 14));
		lblNormal.setBounds(255, 10, 55, 24);
		panel_2.add(lblNormal);
		
		JLabel lblHard = new JLabel("Hard");
		lblHard.setFont(new Font("SimSun", Font.BOLD, 14));
		lblHard.setBounds(336, 10, 55, 24);
		panel_2.add(lblHard);
		
		JLabel startingGold_1 = new JLabel("500");
		startingGold_1.setBounds(256, 49, 54, 15);
		panel_2.add(startingGold_1);
		
		JLabel startingGold_2 = new JLabel("500");
		startingGold_2.setBounds(337, 49, 54, 15);
		panel_2.add(startingGold_2);
		
		JLabel startingGold_3 = new JLabel("100%");
		startingGold_3.setBounds(256, 85, 54, 15);
		panel_2.add(startingGold_3);
		
		JLabel startingGold_4 = new JLabel("75%");
		startingGold_4.setBounds(337, 85, 54, 15);
		panel_2.add(startingGold_4);
		
		JLabel startingGold_5 = new JLabel("100%");
		startingGold_5.setBounds(256, 126, 54, 15);
		panel_2.add(startingGold_5);
		
		JLabel startingGold_6 = new JLabel("120%");
		startingGold_6.setBounds(336, 126, 54, 15);
		panel_2.add(startingGold_6);
		
		JButton startGameButton = new JButton("Start Game");
		startGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String playerName = playerNameTextBox.getText();
				int diff = difficultySelection.getSelectedIndex();
				env.setNumDays(Integer.valueOf((String) daysSelection.getSelectedItem()));
				ArrayList<Monster> team = new ArrayList<Monster>();
				team.add((Monster) startingMonsterSelection.getSelectedItem());
				env.setPlayer(new Player(playerName,500,team,new ArrayList<Item>()));
				env.setDifficulty(diff);
				finishedWindow();
				
			}
		});
		startGameButton.setFont(new Font("SimSun", Font.PLAIN, 18));
		startGameButton.setBounds(277, 451, 278, 85);
		frame.getContentPane().add(startGameButton);
		
		JButton btnNewButton = new JButton("Show Information");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Monster selected = new Monster("Default Monster", 1);
				selected = (Monster) startingMonsterSelection.getSelectedItem();
				maxHealth.setText(Integer.toString(selected.getMaxHealth()));
				damage.setText(Integer.toString(selected.getDamage()));
				currentHealth.setText(Integer.toString(selected.getHealth()));
			
			}
		});
		btnNewButton.setBounds(212, 152, 162, 23);
		frame.getContentPane().add(btnNewButton);
	}
}
