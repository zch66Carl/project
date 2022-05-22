package GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import game.GameEnvironment;
import game.Item;
import game.Player;
import game.monsters.Monster;


import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JTextPane;

/**
 * Battle screen where player fights chosen opponent. Options to attack, switch monster, use items and escape battle.
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
	
	private JComboBox<Item> itemSelectionDropDownBox;
	private JComboBox<String> attackOptions;
	private JComboBox<Monster> monsterSwitch;
	private JComboBox<Monster> itemUsableMonsters;
	
	private ScreenManager scrMan;
	private GameEnvironment env;
	private Player pla;
	private int enemyTeamIndex;
	private Player enemyTeam;
	private Monster wildMonster;
	private boolean isWildBattle;
	private Monster enemy;
	
	/**
	 * The constructor for the battle screen, it initializes the relevant variables, and calls initialize to initialize the gui and then calls update to 
	 * display the gui properly. Finally it displays a battle start message to the player.
	 * @param incScrMan ScreenManager. The screen manager, used to get the GameEnvironment, and close the screen when done.
	 * @param isWildBattle boolean. Whether this battle is a wild battle or not.
	 * @param enemyTeamIndex int. The index of the enemy Player if this is a team battle.
	 */
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
	
	/**
	 * Runs all automatic parts of the battle, i.e. everything from straight after the player makes a move to the start of the player's next move, including
	 * making the enemy move, checking for persistent damage and ending the battle. It displays popups to inform the player of each event and updates the gui accordingly.
	 */
	private void postPlayerTurn() {
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
	
	/**
	 * Processes the end of the battle, rewarding the player if they won and reseting the enemy if they didn't.
	 * @param outcome boolean. The outcome of the battle.
	 */
	private void endBattle(boolean outcome) {
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
				scrMan.launchTeamScreen();
				scrMan.justCloseBattleScreen(this);
			}
			else {
				scrMan.closeBattleScreen(this);
			}
		}
		else {
			if(isWildBattle) JOptionPane.showMessageDialog(frame, wildMonster.getName() + " eliminated your entire team! So Powerful");
			else JOptionPane.showMessageDialog(frame, enemyTeam.getName() + " won!");
			if(isWildBattle) wildMonster.rest();
			else enemyTeam.refreshTeam();
			scrMan.closeBattleScreen(this);
		}
	}
	
	/**
	 * Ends the battle without processing an outcome.
	 */
	private void fleeBattle() {
		pla.postBattle();
		if(isWildBattle) wildMonster.rest();
		else enemyTeam.refreshTeam();
		scrMan.closeBattleScreen(this);
	}
	
	/**
	 * Updates the drop down selection for monsters to use an item on, as certain items are available for use on different monsters.
	 */
	private void updateItemUsableMonsters() {
		try {
			Item item = (Item) itemSelectionDropDownBox.getSelectedItem();
			ArrayList<Monster> usableOn = item.getMonstersUsableOn(pla.getTeam());
			Monster[] usableOnList = new Monster[usableOn.size()];
			for(int i=0; i<usableOn.size(); i++) {
				usableOnList[i] = usableOn.get(i);
			}
			itemUsableMonsters.setModel(new DefaultComboBoxModel<Monster>(usableOnList));
		}
		catch(Exception exc) {
			
		}
	}
	
	/**
	 * Updates all aspects of the gui, including the player and enemy monster health and damage, the player's items and attacks, and the switchable monsters.
	 */
	private void update() {
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
		itemSelectionDropDownBox.setModel(new DefaultComboBoxModel<Item>(items));
		
		updateItemUsableMonsters();
		
		ArrayList<Monster> switchable = pla.getSwitchableMonsters();
		Monster[] switchableList = new Monster[switchable.size()];
		for(int i=0; i<switchable.size(); i++) {
			switchableList[i] = switchable.get(i);
		}
		monsterSwitch.setModel(new DefaultComboBoxModel<Monster>(switchableList));
		
		String[] attackStrings = new String[plaMonst.getAttackStrings().size()];
		for(int i=0; i<plaMonst.getAttackStrings().size(); i++) {
			attackStrings[i] = plaMonst.getAttackStrings().get(i);
		}
		attackOptions.setModel(new DefaultComboBoxModel<String>(attackStrings));
		
	}
	
	/**
	 * Closes the window.
	 */
	public void closeWindow() {
		frame.dispose();
	}
	
	/**
	 * Method to call the screen transition from within the ActionEvents in initialize().
	 */
	public void finishedWindow() {
		scrMan.closeBattleScreen(this);
	}

	
	/**
	 * Initialize the contents of the frame and contains the methods for when buttons are pressed.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Monster Battler");
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
		
		itemSelectionDropDownBox = new JComboBox<Item>();
		itemSelectionDropDownBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateItemUsableMonsters();
			}
		});
		itemSelectionDropDownBox.setBackground(Color.WHITE);
		itemSelectionDropDownBox.setBounds(618, 148, 177, 23);
		frame.getContentPane().add(itemSelectionDropDownBox);
		
		attackOptions = new JComboBox<String>();
		attackOptions.setBackground(Color.WHITE);
		attackOptions.setBounds(618, 272, 177, 24);
		frame.getContentPane().add(attackOptions);
		
		monsterSwitch = new JComboBox<Monster>();
		monsterSwitch.setBackground(Color.WHITE);
		monsterSwitch.setBounds(618, 59, 177, 24);
		frame.getContentPane().add(monsterSwitch);
		
		itemUsableMonsters = new JComboBox<Monster>();
		itemUsableMonsters.setBackground(Color.WHITE);
		itemUsableMonsters.setBounds(618, 183, 177, 24);
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
		attackButton.setBounds(618, 308, 177, 41);
		frame.getContentPane().add(attackButton);
		
		JButton escapeBattleButton = new JButton("Escape Battle");
		escapeBattleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fleeBattle();
			}
		});
		escapeBattleButton.setBounds(618, 362, 177, 41);
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
		changeMonsterButton.setBounds(618, 95, 177, 41);
		frame.getContentPane().add(changeMonsterButton);
		
		JButton useItemButton = new JButton("Use Item");
		useItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Item item = (Item) itemSelectionDropDownBox.getSelectedItem();
					Monster monst = (Monster) itemUsableMonsters.getSelectedItem();
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
		useItemButton.setBounds(618, 219, 177, 41);
		frame.getContentPane().add(useItemButton);
		
		JLabel moveLabel = new JLabel("Make A Move:");
		moveLabel.setBounds(618, 37, 177, 15);
		frame.getContentPane().add(moveLabel);
		
		JTextPane changeMonsterText = new JTextPane();
		changeMonsterText.setEditable(false);
		changeMonsterText.setText("Change your monster on the field.");
		changeMonsterText.setBounds(521, 63, 85, 73);
		frame.getContentPane().add(changeMonsterText);
		
		JTextPane useItemText = new JTextPane();
		useItemText.setText("Use an item on one of your monsters.");
		useItemText.setEditable(false);
		useItemText.setBounds(521, 148, 85, 112);
		frame.getContentPane().add(useItemText);
		
		JTextPane attackText = new JTextPane();
		attackText.setText("Attack the enemy monster.");
		attackText.setEditable(false);
		attackText.setBounds(521, 276, 85, 73);
		frame.getContentPane().add(attackText);
		
		JTextPane fleeBattleText = new JTextPane();
		fleeBattleText.setText("Flee battle.");
		fleeBattleText.setEditable(false);
		fleeBattleText.setBounds(521, 364, 85, 39);
		frame.getContentPane().add(fleeBattleText);
	}
}