package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import game.GameEnvironment;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Game over screen shows player score, days passed, and options to restart or end game.
 * @author chenz
 *
 */
public class GameOverScreen {

	private JFrame frmMonsterBattler;
	private ScreenManager scrMan;
	private GameEnvironment env;
	
	public GameOverScreen(ScreenManager incScrMan) {
		scrMan = incScrMan;
		env = scrMan.getEnv();
		initialize();
		frmMonsterBattler.setVisible(true);
	}
	
	public void closeWindow() {
		frmMonsterBattler.dispose();
	}
	
	public void finishedWindow() {
		scrMan.closeGameOverScreen(this);
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameOverScreen window = new GameOverScreen();
					window.frmMonsterBattler.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GameOverScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMonsterBattler = new JFrame();
		frmMonsterBattler.setTitle("Monster Battler");
		frmMonsterBattler.setBounds(100, 100, 741, 518);
		frmMonsterBattler.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMonsterBattler.getContentPane().setLayout(null);
		
		JLabel gameOverLabel = new JLabel("Game Over");
		gameOverLabel.setFont(new Font("SimSun", Font.BOLD, 20));
		gameOverLabel.setHorizontalAlignment(SwingConstants.CENTER);
		gameOverLabel.setBounds(212, 10, 239, 59);
		frmMonsterBattler.getContentPane().add(gameOverLabel);
		
		JLabel playerNameLabel = new JLabel("Player Name:");
		playerNameLabel.setFont(new Font("SimSun", Font.BOLD, 18));
		playerNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		playerNameLabel.setBounds(45, 100, 195, 42);
		frmMonsterBattler.getContentPane().add(playerNameLabel);
		
		JLabel playerName = new JLabel(env.getPlayer().getName());
		playerName.setHorizontalAlignment(SwingConstants.CENTER);
		playerName.setFont(new Font("SimSun", Font.BOLD, 18));
		playerName.setBounds(421, 100, 195, 42);
		frmMonsterBattler.getContentPane().add(playerName);
		
		JLabel currentGoldLabel = new JLabel("Gold:");
		currentGoldLabel.setHorizontalAlignment(SwingConstants.CENTER);
		currentGoldLabel.setFont(new Font("SimSun", Font.BOLD, 18));
		currentGoldLabel.setBounds(45, 156, 195, 42);
		frmMonsterBattler.getContentPane().add(currentGoldLabel);
		
		JLabel currentGold = new JLabel(Integer.toString(env.getPlayer().getGold()));
		currentGold.setHorizontalAlignment(SwingConstants.CENTER);
		currentGold.setFont(new Font("SimSun", Font.BOLD, 18));
		currentGold.setBounds(421, 152, 195, 42);
		frmMonsterBattler.getContentPane().add(currentGold);
		
		JButton restartButton = new JButton("Restart Game");
		restartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		restartButton.setFont(new Font("SimSun", Font.PLAIN, 18));
		restartButton.setBounds(45, 372, 203, 59);
		frmMonsterBattler.getContentPane().add(restartButton);
		
		JButton exitGameButton = new JButton("Exit Game");
		exitGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		exitGameButton.setFont(new Font("SimSun", Font.PLAIN, 18));
		exitGameButton.setBounds(421, 372, 203, 59);
		frmMonsterBattler.getContentPane().add(exitGameButton);
		
		JLabel daysCompletedLabel = new JLabel("Days Completed:");
		daysCompletedLabel.setHorizontalAlignment(SwingConstants.CENTER);
		daysCompletedLabel.setFont(new Font("SimSun", Font.BOLD, 18));
		daysCompletedLabel.setBounds(45, 251, 195, 42);
		frmMonsterBattler.getContentPane().add(daysCompletedLabel);
		
		JLabel daysCompleted = new JLabel(Integer.toString(env.getCurDay()));
		daysCompleted.setHorizontalAlignment(SwingConstants.CENTER);
		daysCompleted.setFont(new Font("SimSun", Font.BOLD, 18));
		daysCompleted.setBounds(421, 247, 195, 42);
		frmMonsterBattler.getContentPane().add(daysCompleted);
		
		JLabel scoreLabel = new JLabel("Score:");
		scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		scoreLabel.setFont(new Font("SimSun", Font.BOLD, 18));
		scoreLabel.setBounds(45, 303, 195, 42);
		frmMonsterBattler.getContentPane().add(scoreLabel);
		
		JLabel currentScore = new JLabel(Integer.toString(env.getPlayer().getScore()));
		currentScore.setHorizontalAlignment(SwingConstants.CENTER);
		currentScore.setFont(new Font("SimSun", Font.BOLD, 18));
		currentScore.setBounds(421, 303, 195, 42);
		frmMonsterBattler.getContentPane().add(currentScore);
		
		JLabel spendGoldLabel = new JLabel("Spent Gold:");
		spendGoldLabel.setHorizontalAlignment(SwingConstants.CENTER);
		spendGoldLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		spendGoldLabel.setBounds(45, 197, 195, 42);
		frmMonsterBattler.getContentPane().add(spendGoldLabel);
		
		JLabel spentGold = new JLabel(Integer.toString(env.getShop().getGoldSpent()));
		spentGold.setHorizontalAlignment(SwingConstants.CENTER);
		spentGold.setFont(new Font("Dialog", Font.BOLD, 18));
		spentGold.setBounds(421, 193, 195, 42);
		frmMonsterBattler.getContentPane().add(spentGold);
	}
}
