package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import testproject.GameEnvironment;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameOverScreen {

	private JFrame frame;
	private GameEnvironment env;
	
	public GameOverScreen(GameEnvironment incomingEnv) {
		env = incomingEnv;
		initialize();
		frame.setVisible(true);
	}
	
	public void closeWindow() {
		frame.dispose();
	}
	
	public void finishedWindow() {
		env.closeGameOverScreen(this);
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameOverScreen window = new GameOverScreen();
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
	public GameOverScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 741, 518);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel gameOverLabel = new JLabel("Game Over");
		gameOverLabel.setFont(new Font("SimSun", Font.BOLD, 20));
		gameOverLabel.setHorizontalAlignment(SwingConstants.CENTER);
		gameOverLabel.setBounds(212, 10, 239, 59);
		frame.getContentPane().add(gameOverLabel);
		
		JLabel playerNameLabel = new JLabel("Player Name:");
		playerNameLabel.setFont(new Font("SimSun", Font.BOLD, 18));
		playerNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		playerNameLabel.setBounds(45, 100, 195, 42);
		frame.getContentPane().add(playerNameLabel);
		
		JLabel playerName = new JLabel(env.getPlayer().getName());
		playerName.setHorizontalAlignment(SwingConstants.CENTER);
		playerName.setFont(new Font("SimSun", Font.BOLD, 18));
		playerName.setBounds(421, 100, 195, 42);
		frame.getContentPane().add(playerName);
		
		JLabel currentGoldLabel = new JLabel("Gold:");
		currentGoldLabel.setHorizontalAlignment(SwingConstants.CENTER);
		currentGoldLabel.setFont(new Font("SimSun", Font.BOLD, 18));
		currentGoldLabel.setBounds(45, 156, 195, 42);
		frame.getContentPane().add(currentGoldLabel);
		
		JLabel currentGold = new JLabel(Integer.toString(env.getPlayer().getGold()));
		currentGold.setHorizontalAlignment(SwingConstants.CENTER);
		currentGold.setFont(new Font("SimSun", Font.BOLD, 18));
		currentGold.setBounds(421, 152, 195, 42);
		frame.getContentPane().add(currentGold);
		
		JButton restartButton = new JButton("Restart Game");
		restartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		restartButton.setFont(new Font("SimSun", Font.PLAIN, 18));
		restartButton.setBounds(45, 372, 203, 59);
		frame.getContentPane().add(restartButton);
		
		JButton exitGameButton = new JButton("Exit Game");
		exitGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeWindow();
			}
		});
		exitGameButton.setFont(new Font("SimSun", Font.PLAIN, 18));
		exitGameButton.setBounds(421, 372, 203, 59);
		frame.getContentPane().add(exitGameButton);
		
		JLabel daysCompletedLabel = new JLabel("Days Completed:");
		daysCompletedLabel.setHorizontalAlignment(SwingConstants.CENTER);
		daysCompletedLabel.setFont(new Font("SimSun", Font.BOLD, 18));
		daysCompletedLabel.setBounds(45, 208, 195, 42);
		frame.getContentPane().add(daysCompletedLabel);
		
		JLabel daysCompleted = new JLabel(Integer.toString(env.getCurDay()));
		daysCompleted.setHorizontalAlignment(SwingConstants.CENTER);
		daysCompleted.setFont(new Font("SimSun", Font.BOLD, 18));
		daysCompleted.setBounds(421, 204, 195, 42);
		frame.getContentPane().add(daysCompleted);
		
		JLabel scoreLabel = new JLabel("Score:");
		scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		scoreLabel.setFont(new Font("SimSun", Font.BOLD, 18));
		scoreLabel.setBounds(45, 260, 195, 42);
		frame.getContentPane().add(scoreLabel);
		
		JLabel currentScore = new JLabel(Integer.toString(env.getPlayer().getScore()));
		currentScore.setHorizontalAlignment(SwingConstants.CENTER);
		currentScore.setFont(new Font("SimSun", Font.BOLD, 18));
		currentScore.setBounds(421, 260, 195, 42);
		frame.getContentPane().add(currentScore);
	}
}
