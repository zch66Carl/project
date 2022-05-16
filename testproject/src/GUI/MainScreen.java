package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import testproject.GameEnvironment;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainScreen {

	private JFrame frame;
	private GameEnvironment env;
	
	public MainScreen(GameEnvironment incomingEnv) {
		env = incomingEnv;
		initialize();
		frame.setVisible(true);
	}
	
	public void closeWindow() {
		frame.dispose();
	}
	
	public void finishedWindow() {
		env.closeMainScreen(this);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreen window = new MainScreen();
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
	public MainScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 685, 378);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel playerName = new JLabel("Player 1");
		playerName.setFont(new Font("SimSun", Font.PLAIN, 14));
		playerName.setBounds(40, 10, 116, 33);
		frame.getContentPane().add(playerName);
		
		JLabel goldLabel = new JLabel("Gold");
		goldLabel.setFont(new Font("SimSun", Font.PLAIN, 14));
		goldLabel.setBounds(135, 19, 54, 15);
		frame.getContentPane().add(goldLabel);
		
		JLabel currentGold = new JLabel("500");
		currentGold.setFont(new Font("SimSun", Font.PLAIN, 14));
		currentGold.setBounds(177, 19, 54, 15);
		frame.getContentPane().add(currentGold);
		
		JLabel dayLabel = new JLabel("Day");
		dayLabel.setFont(new Font("SimSun", Font.PLAIN, 16));
		dayLabel.setBounds(444, 18, 54, 15);
		frame.getContentPane().add(dayLabel);
		
		JLabel currentDay = new JLabel("5");
		currentDay.setFont(new Font("SimSun", Font.PLAIN, 14));
		currentDay.setBounds(483, 19, 54, 15);
		frame.getContentPane().add(currentDay);
		
		JButton itemButton = new JButton("Items");
		itemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				env.launchItemsScreen();
				finishedWindow();
			}
		});
		itemButton.setFont(new Font("SimSun", Font.PLAIN, 14));
		itemButton.setBounds(40, 88, 165, 66);
		frame.getContentPane().add(itemButton);
		
		JButton teamButton = new JButton("Team");
		teamButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				env.launchTeamScreen();
				finishedWindow();
			}
		});
		teamButton.setFont(new Font("SimSun", Font.PLAIN, 14));
		teamButton.setBounds(230, 88, 165, 66);
		frame.getContentPane().add(teamButton);
		
		JButton btnShop = new JButton("Shop");
		btnShop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				env.launchShopScreen();
				finishedWindow();
			}
		});
		btnShop.setFont(new Font("SimSun", Font.PLAIN, 14));
		btnShop.setBounds(422, 88, 165, 66);
		frame.getContentPane().add(btnShop);
		
		JButton battleButton = new JButton("Battle");
		battleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				env.launchBattleScreen();
				finishedWindow();
			}
		});
		battleButton.setFont(new Font("SimSun", Font.PLAIN, 14));
		battleButton.setBounds(144, 187, 165, 66);
		frame.getContentPane().add(battleButton);
		
		JLabel scoreLabel = new JLabel("Score");
		scoreLabel.setFont(new Font("SimSun", Font.PLAIN, 14));
		scoreLabel.setBounds(255, 19, 54, 15);
		frame.getContentPane().add(scoreLabel);
		
		JLabel currentScore = new JLabel("2350");
		currentScore.setFont(new Font("SimSun", Font.PLAIN, 14));
		currentScore.setBounds(305, 19, 54, 15);
		frame.getContentPane().add(currentScore);
		
		JLabel lblNewLabel_3_1 = new JLabel("of");
		lblNewLabel_3_1.setFont(new Font("SimSun", Font.PLAIN, 16));
		lblNewLabel_3_1.setBounds(506, 19, 54, 15);
		frame.getContentPane().add(lblNewLabel_3_1);
		
		JLabel totalDays = new JLabel("15");
		totalDays.setFont(new Font("SimSun", Font.PLAIN, 14));
		totalDays.setBounds(547, 19, 54, 15);
		frame.getContentPane().add(totalDays);
		
		JButton sleepButton = new JButton("Sleep");
		sleepButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				env.getPlayer().refreshTeam();
			}
		});
		sleepButton.setFont(new Font("SimSun", Font.PLAIN, 14));
		sleepButton.setBounds(333, 187, 165, 66);
		frame.getContentPane().add(sleepButton);
	}

}
