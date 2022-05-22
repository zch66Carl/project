package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import game.GameEnvironment;
import game.Player;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

/**
 * The main screen of game, shows name, gold, current day of game. Navigate to other screen.
 * @author chenz
 *
 */
public class MainScreen {

	private JFrame frame;
	private ScreenManager scrMan;
	private GameEnvironment env;
	
	public MainScreen(ScreenManager incScrMan) {
		scrMan = incScrMan;
		env = scrMan.getEnv();
		initialize();
		frame.setVisible(true);
	}
	
	public void closeWindow() {
		frame.dispose();
	}
	
	public void finishedWindow() {
		scrMan.closeMainScreen(this);
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
		
		JLabel playerName = new JLabel(env.getPlayer().getName());
		playerName.setFont(new Font("SimSun", Font.PLAIN, 14));
		playerName.setBounds(40, 10, 116, 33);
		frame.getContentPane().add(playerName);
		
		JLabel goldLabel = new JLabel("Gold");
		goldLabel.setFont(new Font("SimSun", Font.PLAIN, 14));
		goldLabel.setBounds(170, 19, 54, 15);
		frame.getContentPane().add(goldLabel);
		
		JLabel currentGold = new JLabel(Integer.toString(env.getPlayer().getGold()));
		currentGold.setFont(new Font("SimSun", Font.PLAIN, 14));
		currentGold.setBounds(216, 19, 54, 15);
		frame.getContentPane().add(currentGold);
		
		JLabel dayLabel = new JLabel("Day");
		dayLabel.setFont(new Font("SimSun", Font.PLAIN, 16));
		dayLabel.setBounds(369, 18, 54, 15);
		frame.getContentPane().add(dayLabel);
		
		JLabel currentDay = new JLabel(Integer.toString(env.getCurDay()));
		currentDay.setFont(new Font("SimSun", Font.PLAIN, 14));
		currentDay.setBounds(422, 19, 54, 15);
		frame.getContentPane().add(currentDay);
		
		JButton itemButton = new JButton("Items");
		itemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scrMan.launchItemsScreen();
				finishedWindow();
			}
		});
		itemButton.setFont(new Font("SimSun", Font.PLAIN, 14));
		itemButton.setBounds(40, 88, 165, 66);
		frame.getContentPane().add(itemButton);
		
		JButton teamButton = new JButton("Team");
		teamButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scrMan.launchTeamScreen();
				finishedWindow();
			}
		});
		teamButton.setFont(new Font("SimSun", Font.PLAIN, 14));
		teamButton.setBounds(230, 88, 165, 66);
		frame.getContentPane().add(teamButton);
		
		JButton btnShop = new JButton("Shop");
		btnShop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scrMan.launchShopScreen();
				finishedWindow();
			}
		});
		btnShop.setFont(new Font("SimSun", Font.PLAIN, 14));
		btnShop.setBounds(422, 88, 165, 66);
		frame.getContentPane().add(btnShop);
		
		JButton battleButton = new JButton("Battle");
		battleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(env.getBattles().size() == 0 && env.getWildBattleMonster() == null) {
						JOptionPane.showMessageDialog(frame, "No battles left to fight.");
						throw new RuntimeException("No battles.");
					}
					if(!env.getPlayer().checkIfActiveMonster()) {
						JOptionPane.showMessageDialog(frame, "Can't battle as team are all fainted.");
						throw new RuntimeException("All fainted");
					}
					
					String[] battles = new String[env.getBattles().size() + (env.getWildBattleMonster()!=null ? 1 : 0)];
					for(int i=0;i<battles.length;i++) {
						if(i==env.getBattles().size()) battles[i] = "Wild Battle.";
						else battles[i] = env.getBattles().get(i).getName();
					}
					
					String selection = (String) JOptionPane.showInputDialog(frame,"Choose a battle to fight:", "Battle Selection", JOptionPane.PLAIN_MESSAGE,null,battles,null);
	
					int choice = -1;
					for(int i=0; i<battles.length; i++) {
						if(battles[i] == selection) choice = i;
					}
					
					if(choice!=-1) {
						scrMan.launchBattleScreen(choice);
						finishedWindow();
					}
				} catch (Exception excep) {
					scrMan.launchMainScreen();
				}
				
			}
		});
		battleButton.setFont(new Font("SimSun", Font.PLAIN, 14));
		battleButton.setBounds(40, 187, 165, 66);
		frame.getContentPane().add(battleButton);
		
		JLabel scoreLabel = new JLabel("Score");
		scoreLabel.setFont(new Font("SimSun", Font.PLAIN, 14));
		scoreLabel.setBounds(269, 19, 54, 15);
		frame.getContentPane().add(scoreLabel);
		
		JLabel currentScore = new JLabel(Integer.toString(env.getPlayer().getScore()));
		currentScore.setFont(new Font("SimSun", Font.PLAIN, 14));
		currentScore.setBounds(321, 19, 54, 15);
		frame.getContentPane().add(currentScore);
		
		JLabel lblNewLabel_3_1 = new JLabel("of");
		lblNewLabel_3_1.setFont(new Font("SimSun", Font.PLAIN, 16));
		lblNewLabel_3_1.setBounds(454, 18, 54, 15);
		frame.getContentPane().add(lblNewLabel_3_1);
		
		JLabel totalDays = new JLabel(Integer.toString(env.getNumDays()));
		totalDays.setFont(new Font("SimSun", Font.PLAIN, 14));
		totalDays.setBounds(486, 19, 54, 15);
		frame.getContentPane().add(totalDays);
		
		JButton endDayButton = new JButton("End Day");
		endDayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> messages = env.postDayLogic();
				String full = "Overnight:";
				for(String str : messages) full += "\n" + str;
				full += "\nYour monsters rested up.";
				JOptionPane.showMessageDialog(frame, full);
				
				env.preDayLogic();
				if(env.getCurDay()>env.getNumDays()) {
					scrMan.launchGameOverScreen();
					finishedWindow();
				}
				currentDay.setText(Integer.toString(env.getCurDay()));
			}
		});
		endDayButton.setFont(new Font("SimSun", Font.PLAIN, 14));
		endDayButton.setBounds(230, 187, 165, 66);
		frame.getContentPane().add(endDayButton);
		
		String[] difficulty = {"Easy","Normal","Hard"};
		JLabel lblNewLabel = new JLabel(difficulty[env.getDifficulty() - 1]);
		lblNewLabel.setBounds(550, 19, 54, 15);
		frame.getContentPane().add(lblNewLabel);
		
		JButton endGameButton = new JButton("End Game");
		endGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scrMan.launchGameOverScreen();
				finishedWindow();
			}
		});
		endGameButton.setFont(new Font("Dialog", Font.PLAIN, 14));
		endGameButton.setBounds(423, 187, 164, 66);
		frame.getContentPane().add(endGameButton);
	}
}