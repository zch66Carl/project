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
	
	private ScreenManager scrMan;
	private GameEnvironment env;
	private Player pla;
	private Player enemyTeam;
	private Monster wildMonster;
	private boolean isWildBattle;
	private Monster enemy;
	
	public BattleScreen(ScreenManager incScrMan, boolean isWildBattle, Player enemyTeam) {
		scrMan = incScrMan;
		env = scrMan.getEnv();
		this.enemyTeam = enemyTeam;
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
		}
		enemy = isWildBattle ? wildMonster : enemyTeam.getActiveMonster();
		Monster plaMonst = pla.getActiveMonster();
		full = "Enemy Turn: \n" + (isWildBattle ? wildMonster.makeRandomMove(plaMonst) : enemyTeam.makeRandomMove(plaMonst));
		update();
		JOptionPane.showMessageDialog(frame, full);
		//check if a risky monster killed itself on its own turn
		if((isWildBattle && !wildMonster.isAwake()) || (!isWildBattle && !enemyTeam.checkIfActiveMonster())) {
			endBattle(true);
		}
		enemy = isWildBattle ? wildMonster : enemyTeam.getActiveMonster();
		
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
				//TODO: team size check
				env.setWildBattleMonster(null);
			}
			else {
				//TODO: remove the team battle from the array
			}
			pla.rewardPostBattle(env.getCurDay(), env.getDifficulty(), isWildBattle);
			
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
	
	public void update() {
		pla.checkIfActiveMonster();
		if(!isWildBattle) enemyTeam.checkIfActiveMonster();
		battleType.setText(isWildBattle ? "Wild Battle." : "Team Battle.");
		playerMonsterName.setText(pla.getActiveMonster().getName());
		enemyMonsterName.setText(enemy.getName());
		enemyHealthLabel.setText(enemy.getHealth() + "/" + enemy.getMaxHealth());
		playerHealthLabel.setText(pla.getActiveMonster().getHealth() + "/" + pla.getActiveMonster().getMaxHealth());
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
				
		battleType = new JLabel();
		battleType.setHorizontalAlignment(SwingConstants.CENTER);
		battleType.setFont(new Font("SimSun", Font.BOLD, 14));
		battleType.setBounds(27, 10, 165, 41);
		frame.getContentPane().add(battleType);
		
		JPanel panel = new JPanel();
		panel.setBounds(27, 59, 488, 344);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
				
		playerMonsterName = new JLabel();
		playerMonsterName.setIcon(null);
		playerMonsterName.setBounds(185, 210, 60, 60);
		panel.add(playerMonsterName);
		
		enemyMonsterName = new JLabel();
		enemyMonsterName.setIcon(null);
		enemyMonsterName.setBounds(185, 46, 60, 60);
		panel.add(enemyMonsterName);
		
		enemyHealthLabel = new JLabel();
		enemyHealthLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		enemyHealthLabel.setBounds(143, 21, 54, 15);
		panel.add(enemyHealthLabel);
				
		playerHealthLabel = new JLabel();
		playerHealthLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		playerHealthLabel.setBounds(143, 282, 54, 15);
		panel.add(playerHealthLabel);
		
		
		JButton attackButton = new JButton("Attack");
		attackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message = "Player turn:\n";
				message += pla.getActiveMonster().makeMove(0, enemy);
				update();
				JOptionPane.showMessageDialog(frame, message);
				postPlayerTurn();
			}
		});
		attackButton.setBounds(564, 403, 177, 41);
		frame.getContentPane().add(attackButton);
		
		Item[] itemList = new Item[pla.getInventory().size()];
		for(int i=0;i<itemList.length;i++) {
			itemList[i] = pla.getInventory().get(i);
		}
		JComboBox itemSelectionDropDownBox = new JComboBox();
		itemSelectionDropDownBox.setBackground(Color.WHITE);
		itemSelectionDropDownBox.setModel(new DefaultComboBoxModel(itemList));
		itemSelectionDropDownBox.setBounds(564, 279, 177, 23);
		frame.getContentPane().add(itemSelectionDropDownBox);
		
		
		
		JButton escapeBattleButton = new JButton("Escape Battle");
		escapeBattleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fleeBattle();
			}
		});
		escapeBattleButton.setBounds(564, 446, 177, 41);
		frame.getContentPane().add(escapeBattleButton);
		
		
		
		
		DefaultListModel<Monster> monsterListModel = new DefaultListModel<>();
		monsterListModel.addAll(pla.getTeam());
		JList<Monster> monsterList = new JList<>(monsterListModel);
		monsterList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		monsterList.setBounds(564, 59, 177, 134);
		frame.getContentPane().add(monsterList);
		monsterList.getSelectedValue();
		
		JButton changeMonsterButton = new JButton("Change Monster");
		changeMonsterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pla.setActiveMonster(monsterList.getSelectedValue());
				JOptionPane.showMessageDialog(frame, "Switched to "+pla.getActiveMonster());
			}
		});
		changeMonsterButton.setBounds(564, 226, 177, 41);
		frame.getContentPane().add(changeMonsterButton);
		
		JButton useItemButton = new JButton("Use Item");
		useItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					((Item) itemSelectionDropDownBox.getSelectedItem()).useItem(monsterList.getSelectedValue());
					Item[] currentItems = new Item[pla.getInventory().size()];
					for(int i=0;i<currentItems.length;i++) {
						currentItems[i] = pla.getInventory().get(i);
						
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
		useItemButton.setBounds(564, 314, 177, 41);
		frame.getContentPane().add(useItemButton);
		
		JLabel availableMonsterLabel = new JLabel("Available Monster");
		availableMonsterLabel.setHorizontalAlignment(SwingConstants.CENTER);
		availableMonsterLabel.setBounds(564, 23, 177, 23);
		frame.getContentPane().add(availableMonsterLabel);
		
		JComboBox attackOptions = new JComboBox();
		attackOptions.setBounds(564, 367, 177, 24);
		frame.getContentPane().add(attackOptions);
	}
}