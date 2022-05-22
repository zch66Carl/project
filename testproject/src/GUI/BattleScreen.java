package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import commandline.IO;
import commandline.TeamSizeLimit;
import game.GameEnvironment;
import game.Item;
import game.Player;
import game.monsters.Monster;


import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.beans.PropertyChangeEvent;

/**
 * Battle screen where player fights chosen opponent. Options to switch monster, use items and escape battle.
 * @author chenz
 *
 */
public class BattleScreen {

	private JFrame frame;
	private JLabel battleType;
	private JLabel playerMonsterName;
	private JLabel enemyMonsterName;
	private JLabel enemyHealthLabel;
	private JLabel playerHealthLabel;
	private JLabel enemyTeamSizeLabel;
	
	private JComboBox itemSelectionDropDownBox;
	private JComboBox attackOptions;
	private JComboBox monsterSwitch;
	private JComboBox itemUsableMonsters;
	
	
	private ScreenManager scrMan;
	private GameEnvironment env;
	private Player pla;
	private int enemyTeamIndex;
	private Player enemyTeam;
	private Monster wildMonster;
	private boolean isWildBattle;
	private Monster enemy;
	
	public BattleScreen(ScreenManager incScrMan, boolean isWildBattle, int enemyTeamIndex) {
		scrMan = incScrMan;
		env = scrMan.getEnv();
		enemyTeam = env.getBattles().get(enemyTeamIndex);
		this.enemyTeamIndex = enemyTeamIndex;
		this.isWildBattle = isWildBattle;
		this.wildMonster = env.getWildBattleMonster();
		this.pla = env.getPlayer();
		this.pla.preBattle();
		initialize();
		if(isWildBattle) enemy = wildMonster;
		else{
			enemyTeam.preBattle();
			enemy = enemyTeam.getActiveMonster();
		}
		update();
		frame.setVisible(true);
		if(isWildBattle) JOptionPane.showMessageDialog(frame, wildMonster.getName()+" appears!");
		else JOptionPane.showMessageDialog(frame, enemyTeam.getName()+" challenges you!");
	}
	
	public void postPlayerTurn() {
		//check if player killed their active on their own turn with RiskyMonster
		if(!pla.checkIfActiveMonster()) {
			endBattle(false);
			return;
		}
		
		ArrayList<String> preTurnMessages;
		if(isWildBattle) preTurnMessages = wildMonster.preTurnLogic();
		else preTurnMessages = enemyTeam.preTurnLogic();
		String full = "";
		for(String str : preTurnMessages) {
			full += str + "\n";
		}
		update();
		if(preTurnMessages.size()>0) {
			JOptionPane.showMessageDialog(frame, full);
		}
		
		if((isWildBattle && !wildMonster.isAwake()) || (!isWildBattle && !enemyTeam.checkIfActiveMonster())) {
			endBattle(true);
			return;
		}
		enemy = isWildBattle ? wildMonster : enemyTeam.getActiveMonster();
		Monster plaMonst = pla.getActiveMonster();
		full = "Enemy Turn: \n" + (isWildBattle ? wildMonster.makeRandomMove(plaMonst) : enemyTeam.makeRandomMove(plaMonst));
		update();
		JOptionPane.showMessageDialog(frame, full);
		//check if a risky monster killed itself on its own turn
		if((isWildBattle && !wildMonster.isAwake()) || (!isWildBattle && !enemyTeam.checkIfActiveMonster())) {
			endBattle(true);
			return;
		}
		enemy = isWildBattle ? wildMonster : enemyTeam.getActiveMonster();
		
		if(!pla.checkIfActiveMonster()) {
			endBattle(false);
			return;
		}
		
		//pre player turn
		preTurnMessages = pla.preTurnLogic();
		full = "";
		for(String str : preTurnMessages) {
			full += str + "\n";
		}
		update();
		if(preTurnMessages.size()>0) {
			JOptionPane.showMessageDialog(frame, full);
		}
		
		
		if(!pla.checkIfActiveMonster()) {
			endBattle(false);
			return;
		}
	}
	
	public void endBattle(boolean outcome) {
		pla.postBattle();
		if(outcome) {
			JOptionPane.showMessageDialog(frame, "You Won");
			if(isWildBattle) {
				wildMonster.rest();
				JOptionPane.showMessageDialog(frame, wildMonster.getName() + " joins your team!");
				pla.addMonster(wildMonster);
				env.setWildBattleMonster(null);
			}
			else {
				JOptionPane.showMessageDialog(frame, "Have some gold.");
				env.getBattles().remove(enemyTeamIndex);
			}
			pla.rewardPostBattle(env.getCurDay(), env.getDifficulty(), isWildBattle);
			
			if(pla.getTeam().size() > 4) {
				finishedWindow();
				scrMan.launchTeamScreen();
				return;
			}
			
			finishedWindow();
			scrMan.launchMainScreen();
		}
		else {
			if(isWildBattle) JOptionPane.showMessageDialog(frame, wildMonster.getName() + " eliminated your entire team! So Powerful");
			else JOptionPane.showMessageDialog(frame, enemyTeam.getName() + " won!");
			if(isWildBattle) wildMonster.rest();
			else enemyTeam.refreshTeam();
			finishedWindow();
			scrMan.launchMainScreen();
		}
	}
	
	void fleeBattle() {
		pla.postBattle();
		if(isWildBattle) wildMonster.rest();
		else enemyTeam.refreshTeam();
		finishedWindow();
		scrMan.launchMainScreen();
	}
	
	void updateItemUsableMonsters() {
		try {
			Item item = (Item) itemSelectionDropDownBox.getSelectedItem();
			ArrayList<Monster> usableOn = item.getMonstersUsableOn(pla.getTeam());
			Monster[] usableOnList = new Monster[usableOn.size()];
			for(int i=0; i<usableOn.size(); i++) {
				usableOnList[i] = usableOn.get(i);
			}
			itemUsableMonsters.setModel(new DefaultComboBoxModel(usableOnList));
		}
		catch(Exception exc) {
			
		}
	}
	
	public void update() {
		pla.checkIfActiveMonster();
		if(!isWildBattle) enemyTeam.checkIfActiveMonster();
		
		Monster plaMonst = pla.getActiveMonster();
		
		battleType.setText(isWildBattle ? "Wild Battle." : ("Team Battle: " + enemyTeam.getName()));
		playerMonsterName.setText(plaMonst.getName());
		enemyMonsterName.setText(enemy.getName());
		enemyTeamSizeLabel.setText("");
		if(!isWildBattle) {
			int enemyCount = 0;
			for(Monster monst : enemyTeam.getTeam()) {
				if(monst.isAwake()) enemyCount++;
			}
			enemyTeamSizeLabel.setText(enemyCount + "/" + enemyTeam.getTeam().size() + " monsters remaining.");
		}
		String info = "Health: " + enemy.getHealth() + "/" + enemy.getMaxHealth() + ". Damage: " + enemy.getTotalDamage() + "/" + enemy.getDamage() + ".";
		enemyHealthLabel.setText(info);
		info = "Health: " + plaMonst.getHealth() + "/" + plaMonst.getMaxHealth() + ". Damage: " + plaMonst.getTotalDamage() + "/" + plaMonst.getDamage() + ".";
		playerHealthLabel.setText(info);
		
		Item[] items = new Item[pla.getInventory().size()];
		for(int i=0;i<items.length;i++) {
			items[i] = pla.getInventory().get(i);
		}
		itemSelectionDropDownBox.setModel(new DefaultComboBoxModel(items));
		
		updateItemUsableMonsters();
		
		ArrayList<Monster> switchable = pla.getSwitchableMonsters();
		Monster[] switchableList = new Monster[switchable.size()];
		for(int i=0; i<switchable.size(); i++) {
			switchableList[i] = switchable.get(i);
		}
		monsterSwitch.setModel(new DefaultComboBoxModel(switchableList));
		
		String[] attackStrings = new String[plaMonst.getAttackStrings().size()];
		for(int i=0; i<plaMonst.getAttackStrings().size(); i++) {
			attackStrings[i] = plaMonst.getAttackStrings().get(i);
		}
		attackOptions.setModel(new DefaultComboBoxModel(attackStrings));
		
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
				
		//batle info:
		
		battleType = new JLabel();
		battleType.setText("battle type");
		battleType.setHorizontalAlignment(SwingConstants.CENTER);
		battleType.setFont(new Font("SimSun", Font.BOLD, 14));
		battleType.setBounds(27, 10, 488, 41);
		frame.getContentPane().add(battleType);
		
		JPanel panel = new JPanel();
		panel.setBounds(27, 59, 488, 344);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
				
		playerMonsterName = new JLabel();
		playerMonsterName.setText("player name");
		playerMonsterName.setIcon(null);
		playerMonsterName.setBounds(48, 245, 428, 60);
		panel.add(playerMonsterName);
		
		enemyMonsterName = new JLabel();
		enemyMonsterName.setText("enemy name");
		enemyMonsterName.setIcon(null);
		enemyMonsterName.setBounds(48, 54, 428, 60);
		panel.add(enemyMonsterName);
		
		enemyHealthLabel = new JLabel();
		enemyHealthLabel.setText("enemy info");
		enemyHealthLabel.setHorizontalAlignment(SwingConstants.LEFT);
		enemyHealthLabel.setBounds(48, 126, 428, 15);
		panel.add(enemyHealthLabel);
				
		playerHealthLabel = new JLabel();
		playerHealthLabel.setText("player info");
		playerHealthLabel.setHorizontalAlignment(SwingConstants.LEFT);
		playerHealthLabel.setBounds(48, 317, 428, 15);
		panel.add(playerHealthLabel);
		
		JLabel enemyTitleLabel = new JLabel("Enemy");
		enemyTitleLabel.setBounds(48, 36, 70, 15);
		panel.add(enemyTitleLabel);
		
		JLabel playerTitleLabel = new JLabel("Player");
		playerTitleLabel.setBounds(48, 223, 70, 15);
		panel.add(playerTitleLabel);
		
		enemyTeamSizeLabel = new JLabel("");
		enemyTeamSizeLabel.setBounds(271, 36, 217, 15);
		panel.add(enemyTeamSizeLabel);
		
		
		//moves info:
		
		itemSelectionDropDownBox = new JComboBox();
		itemSelectionDropDownBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateItemUsableMonsters();
			}
		});
		itemSelectionDropDownBox.setBackground(Color.WHITE);
		itemSelectionDropDownBox.setBounds(564, 148, 177, 23);
		frame.getContentPane().add(itemSelectionDropDownBox);
		
		attackOptions = new JComboBox();
		attackOptions.setBackground(Color.WHITE);
		attackOptions.setBounds(564, 272, 177, 24);
		frame.getContentPane().add(attackOptions);
		
		monsterSwitch = new JComboBox();
		monsterSwitch.setBackground(Color.WHITE);
		monsterSwitch.setBounds(564, 59, 177, 24);
		frame.getContentPane().add(monsterSwitch);
		
		itemUsableMonsters = new JComboBox();
		itemUsableMonsters.setBackground(Color.WHITE);
		itemUsableMonsters.setBounds(564, 183, 177, 24);
		frame.getContentPane().add(itemUsableMonsters);
		
		
		//buttons:
		
		JButton attackButton = new JButton("Attack");
		attackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String message = "Player turn:\n";
					int move = attackOptions.getSelectedIndex();
					message += pla.getActiveMonster().makeMove(move, enemy);
					update();
					JOptionPane.showMessageDialog(frame, message);
					postPlayerTurn();
				}
				catch(Exception excep) {
					JOptionPane.showMessageDialog(frame, "You must select an attack to attack.");
				}
			}
		});
		attackButton.setBounds(564, 308, 177, 41);
		frame.getContentPane().add(attackButton);
		
		JButton escapeBattleButton = new JButton("Escape Battle");
		escapeBattleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fleeBattle();
			}
		});
		escapeBattleButton.setBounds(564, 362, 177, 41);
		frame.getContentPane().add(escapeBattleButton);
		
		
		JButton changeMonsterButton = new JButton("Change Monster");
		changeMonsterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Monster monst = (Monster) monsterSwitch.getSelectedItem();
					pla.setActiveMonster(monst);
					JOptionPane.showMessageDialog(frame, "Switched to "+pla.getActiveMonster());
					update();
					postPlayerTurn();
				} 
				catch(NullPointerException excep) {
					JOptionPane.showMessageDialog(frame, "No other monsters to be switched to.");
				}
				catch(Exception excep) {
					JOptionPane.showMessageDialog(frame, "You must select a monster to switch to that monster.");
				}
			}
		});
		changeMonsterButton.setBounds(564, 95, 177, 41);
		frame.getContentPane().add(changeMonsterButton);
		
		JButton useItemButton = new JButton("Use Item");
		useItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Item item = (Item) itemSelectionDropDownBox.getSelectedItem();
					Monster monst = (Monster) itemUsableMonsters.getSelectedItem();
					Item[] currentItems = new Item[pla.getInventory().size()];
					pla.useItem(item, monst);
					update();
					JOptionPane.showMessageDialog(frame, "Used Item.");
					postPlayerTurn();
				} 
				catch(NullPointerException excep) {
					JOptionPane.showMessageDialog(frame, "No Item To Use");
				}
				catch(Exception excep) {
					JOptionPane.showMessageDialog(frame, "Select Item and Monster to use the Item on.");
				}
			}
			
		});
		useItemButton.setBounds(564, 219, 177, 41);
		frame.getContentPane().add(useItemButton);
	}
}