package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import testproject.GameEnvironment;
import testproject.Item;
import testproject.Player;
import testproject.monsters.Monster;

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

public class BattleScreen {

	private JFrame frame;
	private GameEnvironment env;
	private Player battle;
	
	public BattleScreen(GameEnvironment incomingEnv,Player battle) {
		env = incomingEnv;
		this.battle = battle;
		initialize();
		frame.setVisible(true);
		JOptionPane.showMessageDialog(frame, battle.getName()+" joins battle!");
	}
	
	public void closeWindow() {
		frame.dispose();
	}
	
	public void finishedWindow() {
		env.closeBattleScreen(this);
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
		panel.setBounds(27, 59, 487, 466);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel playerMonsterIcon = new JLabel("");
		playerMonsterIcon.setIcon(new ImageIcon(BattleScreen.class.getResource("/GUI/images/goat_resized.jpg")));
		playerMonsterIcon.setBounds(185, 369, 60, 60);
		panel.add(playerMonsterIcon);
		
		JLabel enemyMonsterIcon = new JLabel("");
		enemyMonsterIcon.setIcon(new ImageIcon(BattleScreen.class.getResource("/GUI/images/eagle_resized.jpg")));
		enemyMonsterIcon.setBounds(185, 46, 60, 60);
		panel.add(enemyMonsterIcon);
		
		JLabel enemyCurrentHealth = new JLabel(Integer.toString(battle.getTeam().get(battle.getActiveMonsterIndex()).getHealth()));
		enemyCurrentHealth.setHorizontalAlignment(SwingConstants.RIGHT);
		enemyCurrentHealth.setBounds(143, 21, 54, 15);
		panel.add(enemyCurrentHealth);
		
		JLabel enemyMaxHealth = new JLabel(Integer.toString(battle.getTeam().get(battle.getActiveMonsterIndex()).getMaxHealth()));
		enemyMaxHealth.setBounds(223, 21, 54, 15);
		panel.add(enemyMaxHealth);
		
		JLabel lblNewLabel_6 = new JLabel("/");
		lblNewLabel_6.setBounds(207, 21, 14, 15);
		panel.add(lblNewLabel_6);
		
		JLabel playerCurrentHealth = new JLabel(Integer.toString(env.getPlayer().getTeam().get(env.getPlayer().getActiveMonsterIndex()).getHealth()));
		playerCurrentHealth.setHorizontalAlignment(SwingConstants.RIGHT);
		playerCurrentHealth.setBounds(143, 441, 54, 15);
		panel.add(playerCurrentHealth);
		
		JLabel lblNewLabel_6_1 = new JLabel("/");
		lblNewLabel_6_1.setBounds(207, 441, 14, 15);
		panel.add(lblNewLabel_6_1);
		
		JLabel playerMaxHealth = new JLabel(Integer.toString(env.getPlayer().getTeam().get(env.getPlayer().getActiveMonsterIndex()).getMaxHealth()));
		playerMaxHealth.setBounds(223, 441, 54, 15);
		panel.add(playerMaxHealth);
		
		JButton attackButton = new JButton("Attack");
		attackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
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
					env.launchMainScreen();
				}
				if(!env.getPlayer().checkIfActiveMonster()) {
					JOptionPane.showMessageDialog(frame, "You Lost");
					finishedWindow();
					env.launchMainScreen();
				}
				
				
				
			}
		});
		attackButton.setBounds(564, 327, 177, 41);
		frame.getContentPane().add(attackButton);
		
		Item[] itemList = new Item[env.getPlayer().getInventory().size()];
		for(int i=0;i<itemList.length;i++) {
			itemList[i] = env.getPlayer().getInventory().get(i);
		}
		JComboBox itemSelectionDropDownBox = new JComboBox();
		itemSelectionDropDownBox.setBackground(Color.WHITE);
		itemSelectionDropDownBox.setModel(new DefaultComboBoxModel(itemList));
		itemSelectionDropDownBox.setBounds(564, 378, 177, 23);
		frame.getContentPane().add(itemSelectionDropDownBox);
		
		
		
		JButton escapeBattleButton = new JButton("Escape Battle");
		escapeBattleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
				env.launchMainScreen();
			}
		});
		escapeBattleButton.setBounds(564, 484, 177, 41);
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
		useItemButton.setBounds(564, 411, 177, 41);
		frame.getContentPane().add(useItemButton);
	}
}
