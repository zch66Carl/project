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

import game.GameEnvironment;
import game.Generation;
import game.Item;
import game.Player;
import game.monsters.Monster;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;

/**
 * Initial screen to setup the game, ask player to enter name, choose starting monster, set number of days for the game.
 * @author chenz
 *
 */
public class SetupScreen {

	private JFrame frame;
	private JTextField playerNameTextBox;
	private ScreenManager scrMan;
	private GameEnvironment env;
	
	public SetupScreen(ScreenManager incScrMan) {
		scrMan = incScrMan;
		env = scrMan.getEnv();
		initialize();
		frame.setVisible(true);
	}
	
	public void closeWindow() {
		frame.dispose();
	}
	
	public void finishedWindow() {
		scrMan.closeSetupScreen(this);
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
		frame.setBounds(100, 100, 857, 491);
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
		playerNameTextBox.setBounds(243, 74, 175, 21);
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
		
		Monster monsterOne = Generation.generatePlayerMonster(1, 1);
		Monster monsterTwo = Generation.generatePlayerMonster(1, 1);
		Monster monsterThree = Generation.generatePlayerMonster(1, 1);
		JComboBox startingMonsterSelection = new JComboBox();
		startingMonsterSelection.setModel(new DefaultComboBoxModel(new Monster[] {monsterOne,monsterTwo,monsterThree}));
		startingMonsterSelection.setSelectedIndex(0);
		startingMonsterSelection.setBounds(243, 119, 546, 23);
		frame.getContentPane().add(startingMonsterSelection);
		
		Monster selected = new Monster("Default Monster", 1);
		selected = (Monster) startingMonsterSelection.getSelectedItem();
		
		JLabel lblNewLabel_9 = new JLabel("(Number of days the game will last)");
		lblNewLabel_9.setFont(new Font("SimSun", Font.PLAIN, 14));
		lblNewLabel_9.setBounds(530, 77, 259, 15);
		frame.getContentPane().add(lblNewLabel_9);
		
		JLabel difficultyLabel = new JLabel("Select Difficulty:");
		difficultyLabel.setFont(new Font("SimSun", Font.PLAIN, 14));
		difficultyLabel.setBounds(37, 183, 141, 29);
		frame.getContentPane().add(difficultyLabel);
		
		JComboBox difficultySelection = new JComboBox();
		difficultySelection.setModel(new DefaultComboBoxModel(new String[] {"Easy", "Normal", "Hard"}));
		difficultySelection.setBounds(243, 186, 121, 23);
		frame.getContentPane().add(difficultySelection);
		
		JButton startGameButton = new JButton("Start Game");
		startGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String playerName = playerNameTextBox.getText();
				if(Player.isValidName(playerName)) {
					env.setNumDays(daysSelection.getSelectedIndex()+5);
					ArrayList<Monster> team = new ArrayList<Monster>();
					team.add((Monster) startingMonsterSelection.getSelectedItem());
					
					int diff = difficultySelection.getSelectedIndex()+1;
					int gold = (3 - diff) * 15; //15 on normal, 30 on easy, 0 on hard.
					env.setPlayer(new Player(playerName,gold,team,new ArrayList<Item>()));
					env.setDifficulty(diff);
					finishedWindow();
				}
				else {
					JOptionPane.showMessageDialog(frame, "Invalid Name, should be 3-15 characters and fully alphabetic.");
				}
			}
		}
						
				
				
			
		);
		startGameButton.setFont(new Font("SimSun", Font.PLAIN, 18));
		startGameButton.setBounds(37, 283, 278, 85);
		frame.getContentPane().add(startGameButton);
		
		JTextPane txtpnOnHigherDifficulties = new JTextPane();
		txtpnOnHigherDifficulties.setText("On higher difficulties, you will earn less gold, and face harder opponents.");
		txtpnOnHigherDifficulties.setBounds(394, 183, 390, 43);
		frame.getContentPane().add(txtpnOnHigherDifficulties);
	}
}
