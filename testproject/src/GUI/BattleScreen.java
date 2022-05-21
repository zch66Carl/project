package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;


import game.GameEnvironment;
import game.Item;
import game.Player;
import game.monsters.Monster;


import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Battle screen where player fights chosen opponent. Options to switch monster, use items and escape battle.
 * @author chenz
 *
 */
public class BattleScreen {

	private JFrame frame;
	private ScreenManager scrMan;
	private GameEnvironment env;
	private Player battle;
	
	public BattleScreen(ScreenManager incScrMan, Player battle) {
		scrMan = incScrMan;
		env = scrMan.getEnv();
		this.battle = battle;
		initialize();
		frame.setVisible(true);
		JOptionPane.showMessageDialog(frame, battle.getName()+" joins battle!");
	}
	
	public void closeWindow() {
		frame.dispose();
	}
	
	public void finishedWindow() {
		scrMan.closeBattleScreen(this);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BattleScreen window = new BattleScreen();
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
	public BattleScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 879, 612);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel battleType = new JLabel("Battle");
		battleType.setHorizontalAlignment(SwingConstants.CENTER);
		battleType.setFont(new Font("SimSun", Font.BOLD, 14));
		battleType.setBounds(27, 10, 165, 41);
		frame.getContentPane().add(battleType);
		
		JPanel panel = new JPanel();
		panel.setBounds(27, 59, 488, 344);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel playerMonsterName = new JLabel(env.getPlayer().getActiveMonster().getName());
		playerMonsterName.setIcon(null);
		playerMonsterName.setBounds(185, 210, 60, 60);
		panel.add(playerMonsterName);
		
		JLabel enemyMonsterName = new JLabel(battle.getActiveMonster().getName());
		enemyMonsterName.setIcon(null);
		enemyMonsterName.setBounds(185, 46, 60, 60);
		panel.add(enemyMonsterName);
		
		JLabel enemyCurrentHealth = new JLabel(Integer.toString(battle.getActiveMonster().getHealth()));
		enemyCurrentHealth.setHorizontalAlignment(SwingConstants.RIGHT);
		enemyCurrentHealth.setBounds(143, 21, 54, 15);
		panel.add(enemyCurrentHealth);
		
		JLabel enemyMaxHealth = new JLabel(Integer.toString(battle.getActiveMonster().getMaxHealth()));
		enemyMaxHealth.setBounds(223, 21, 54, 15);
		panel.add(enemyMaxHealth);
		
		JLabel lblNewLabel_6 = new JLabel("/");
		lblNewLabel_6.setBounds(207, 21, 14, 15);
		panel.add(lblNewLabel_6);
		
		JLabel playerCurrentHealth = new JLabel(Integer.toString(env.getPlayer().getActiveMonster().getHealth()));
		playerCurrentHealth.setHorizontalAlignment(SwingConstants.RIGHT);
		playerCurrentHealth.setBounds(143, 282, 54, 15);
		panel.add(playerCurrentHealth);
		
		JLabel lblNewLabel_6_1 = new JLabel("/");
		lblNewLabel_6_1.setBounds(207, 282, 14, 15);
		panel.add(lblNewLabel_6_1);
		
		JLabel playerMaxHealth = new JLabel(Integer.toString(env.getPlayer().getActiveMonster().getMaxHealth()));
		playerMaxHealth.setBounds(223, 282, 54, 15);
		panel.add(playerMaxHealth);
		
		JButton attackButton = new JButton("Attack");
		attackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				env.getPlayer().preTurnLogic();
				JOptionPane.showMessageDialog(frame, env.getPlayer().getActiveMonster().makeMove(0, battle.getActiveMonster()));
				enemyCurrentHealth.setText(Integer.toString(battle.getActiveMonster().getHealth()));
				if(battle.checkIfActiveMonster()) {
					battle.preTurnLogic();
					JOptionPane.showMessageDialog(frame, battle.makeRandomMove(env.getPlayer().getActiveMonster()));
					enemyCurrentHealth.setText(Integer.toString(battle.getActiveMonster().getHealth()));
					playerCurrentHealth.setText(Integer.toString(env.getPlayer().getActiveMonster().getHealth()));
					
				} else {
					env.getPlayer().rewardPostBattle(env.getCurDay(), env.getDifficulty(), false);
					JOptionPane.showMessageDialog(frame, "You Won");
					finishedWindow();
					scrMan.launchMainScreen();
				}
				if(!env.getPlayer().checkIfActiveMonster()) {
					JOptionPane.showMessageDialog(frame, "You Lost");
					finishedWindow();
					scrMan.launchMainScreen();
				}
				
				
				
			}
		});
		attackButton.setBounds(27, 446, 177, 41);
		frame.getContentPane().add(attackButton);
		
		Item[] itemList = new Item[env.getPlayer().getInventory().size()];
		for(int i=0;i<itemList.length;i++) {
			itemList[i] = env.getPlayer().getInventory().get(i);
		}
		JComboBox itemSelectionDropDownBox = new JComboBox();
		itemSelectionDropDownBox.setBackground(Color.WHITE);
		itemSelectionDropDownBox.setModel(new DefaultComboBoxModel(itemList));
		itemSelectionDropDownBox.setBounds(564, 328, 177, 23);
		frame.getContentPane().add(itemSelectionDropDownBox);
		
		
		
		JButton escapeBattleButton = new JButton("Escape Battle");
		escapeBattleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
				scrMan.launchMainScreen();
			}
		});
		escapeBattleButton.setBounds(564, 446, 177, 41);
		frame.getContentPane().add(escapeBattleButton);
		
		
		
		
		DefaultListModel<Monster> monsterListModel = new DefaultListModel<>();
		monsterListModel.addAll(env.getPlayer().getTeam());
		JList<Monster> monsterList = new JList<>(monsterListModel);
		monsterList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		monsterList.setBounds(564, 59, 177, 193);
		frame.getContentPane().add(monsterList);
		monsterList.getSelectedValue();
		
		JButton changeMonsterButton = new JButton("Change Monster");
		changeMonsterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				env.getPlayer().setActiveMonster(monsterList.getSelectedValue());
				playerCurrentHealth.setText(Integer.toString(env.getPlayer().getActiveMonster().getHealth()));
				playerMaxHealth.setText(Integer.toString(env.getPlayer().getActiveMonster().getHealth()));
				JOptionPane.showMessageDialog(frame, "Switched to "+env.getPlayer().getActiveMonster());
			}
		});
		changeMonsterButton.setBounds(564, 276, 177, 41);
		frame.getContentPane().add(changeMonsterButton);
		
		JButton useItemButton = new JButton("Use Item");
		useItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					((Item) itemSelectionDropDownBox.getSelectedItem()).useItem(monsterList.getSelectedValue());
					Item[] currentItems = new Item[env.getPlayer().getInventory().size()];
					for(int i=0;i<currentItems.length;i++) {
						currentItems[i] = env.getPlayer().getInventory().get(i);
						
					}
					
					itemSelectionDropDownBox.removeItem(itemSelectionDropDownBox.getSelectedItem());
					
					} 
					catch(NullPointerException excep) {
						JOptionPane.showMessageDialog(frame, "No Item To Use");
					}
					catch(Exception excep) {
						JOptionPane.showMessageDialog(frame, "Select Item and Target Monster to use");
					}
				}
			
		});
		useItemButton.setBounds(564, 362, 177, 41);
		frame.getContentPane().add(useItemButton);
		
		JButton useAbilityButton = new JButton("Use Ability");
		useAbilityButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, env.getPlayer().getActiveMonster().makeMove(1, battle.getActiveMonster()));
				
			}
		});
		useAbilityButton.setBounds(338, 446, 177, 41);
		frame.getContentPane().add(useAbilityButton);
		
		JLabel availableMonsterLabel = new JLabel("Available Monster");
		availableMonsterLabel.setHorizontalAlignment(SwingConstants.CENTER);
		availableMonsterLabel.setBounds(564, 23, 177, 23);
		frame.getContentPane().add(availableMonsterLabel);
	}
}