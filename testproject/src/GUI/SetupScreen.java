package GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import game.GameEnvironment;
import game.Generation;
import game.Item;
import game.Player;
import game.monsters.Monster;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;

/**
 * Entry point to the GUI application.
 * Initial screen to setup the game, ask player to enter name, choose starting monster, set number of days for the game and the difficulty.
 */
public class SetupScreen {
	private JFrame frame;
	private JTextField playerNameTextBox;
	private JComboBox<String> daysSelection;
	private JComboBox<String> startingMonsterSelection;
	private JComboBox<String> difficultySelection;
	
	private GameEnvironment env;
	
	Monster monsterOne;
	Monster monsterTwo;
	Monster monsterThree;
	
	/**
	 * Initializes the gui for the screen and creates a new game environment.
	 */
	public SetupScreen() {
		env = new GameEnvironment();
		monsterOne = Generation.generatePlayerMonster(1, 1);
		monsterTwo = Generation.generatePlayerMonster(1, 1);
		monsterThree = Generation.generatePlayerMonster(1, 1);
		
		initialize();
		frame.setVisible(true);
	}
	
	/**
	 * Closes the window.
	 */
	private void closeWindow() {
		frame.dispose();
	}
	
	/**
	 * Checks for necersary data and launches the main game.
	 */
	private void startGame() {
		String playerName = playerNameTextBox.getText();
		if(Player.isValidName(playerName)) {
			env.setNumDays(daysSelection.getSelectedIndex()+5);
			ArrayList<Monster> team = new ArrayList<Monster>();
			int monstInd = startingMonsterSelection.getSelectedIndex();
			team.add(monstInd==0 ? monsterOne : (monstInd==1 ? monsterTwo : monsterThree));
			
			int diff = difficultySelection.getSelectedIndex()+1;//1-3
			int gold = (4 - diff) * 15; //30 on normal, 45 on easy, 15 on hard.
			env.setPlayer(new Player(playerName,gold,team,new ArrayList<Item>()));
			env.setDifficulty(diff);
			env.preDayLogic();//Initialize the first day's battles.
			new MainScreen(env);
			closeWindow();
		}
		else {
			JOptionPane.showMessageDialog(frame, "Invalid Name, should be 3-15 characters and fully alphabetic.");
		}
	}
	
	/**
	 * Displays a popup detailing how to play the game.
	 */
	private void howToPlay() {
		String howToPlayString = ""
				+ "Become the strongest by fighting other monster trainers and improving your monster's skills.\n"
				+ "Buy items and monsters in the shop to give yourself an edge over the competition.\n"
				+ "Make sure you fight a few battles each day so that your team isn't left in the dust.\n"
				+ "Experiment with different attack types, and play around with your team order.\n"
				+ "If you really wan't a challenge, fight the high level wild monsters that appear every few days.\n"
				+ "Don't forget to have fun.";
		JOptionPane.showMessageDialog(frame, howToPlayString);
	}
	
	/**
	 * Initialize the contents of the frame and contains methods for the gui inputs.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Monster Battler");
		frame.setBounds(100, 100, 857, 491);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel welcomeMessage = new JLabel("Welcome to Monster Battles");
		welcomeMessage.setFont(new Font("SimSun", Font.BOLD, 18));
		welcomeMessage.setBounds(37, 10, 470, 43);
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
		daysLabel.setBounds(425, 70, 40, 29);
		frame.getContentPane().add(daysLabel);
		
		daysSelection = new JComboBox<String>();
		daysSelection.setModel(new DefaultComboBoxModel<String>(new String[] {"5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"}));
		daysSelection.setBounds(472, 73, 71, 23);
		frame.getContentPane().add(daysSelection);
		
		JLabel startingMonsterLabel = new JLabel("Choose starting monsters:");
		startingMonsterLabel.setFont(new Font("SimSun", Font.PLAIN, 14));
		startingMonsterLabel.setBounds(37, 116, 175, 29);
		frame.getContentPane().add(startingMonsterLabel);
		
		startingMonsterSelection = new JComboBox<String>();
		String[] monsterStr = new String[] {monsterOne.basicStr(),monsterTwo.basicStr(),monsterThree.basicStr()};
		startingMonsterSelection.setModel(new DefaultComboBoxModel<String>(monsterStr));
		startingMonsterSelection.setSelectedIndex(0);
		startingMonsterSelection.setBounds(243, 119, 546, 23);
		frame.getContentPane().add(startingMonsterSelection);
		
		JLabel daysInfo = new JLabel("(Number of days the game will last)");
		daysInfo.setFont(new Font("SimSun", Font.PLAIN, 14));
		daysInfo.setBounds(549, 77, 240, 15);
		frame.getContentPane().add(daysInfo);
		
		JLabel difficultyLabel = new JLabel("Select Difficulty:");
		difficultyLabel.setFont(new Font("SimSun", Font.PLAIN, 14));
		difficultyLabel.setBounds(37, 183, 141, 29);
		frame.getContentPane().add(difficultyLabel);
		
		difficultySelection = new JComboBox<String>();
		difficultySelection.setModel(new DefaultComboBoxModel<String>(new String[] {"Easy", "Normal", "Hard"}));
		difficultySelection.setBounds(243, 186, 121, 23);
		frame.getContentPane().add(difficultySelection);
		
		JButton startGameButton = new JButton("Start Game");
		startGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startGame();
			}
		}
						
				
				
			
		);
		startGameButton.setFont(new Font("SimSun", Font.PLAIN, 18));
		startGameButton.setBounds(37, 283, 364, 85);
		frame.getContentPane().add(startGameButton);
		
		JTextPane difficultyInfo = new JTextPane();
		difficultyInfo.setEditable(false);
		difficultyInfo.setText("On higher difficulties, you will earn less gold, and face harder opponents. Warning: Hard difficulty is very difficult in the early days of the game.");
		difficultyInfo.setBounds(394, 183, 390, 51);
		frame.getContentPane().add(difficultyInfo);
		
		JButton howToPlayButton = new JButton("How To Play");
		howToPlayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				howToPlay();
			}
		});
		howToPlayButton.setFont(new Font("Dialog", Font.PLAIN, 18));
		howToPlayButton.setBounds(425, 283, 364, 85);
		frame.getContentPane().add(howToPlayButton);
	}
	
	/**
	 * Entry point to the gui application.
	 * @param args String[]. The command line arguments (unused).
	 */
	public static void main(String[] args) {
		new SetupScreen();
	}
}
