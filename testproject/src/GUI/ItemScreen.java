package GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ListSelectionModel;

import game.GameEnvironment;
import game.Item;
import game.monsters.Monster;

import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

/**
 * Item screen shows players inventory and team that allows items to be used out of battle.
 * @author chenz
 *
 */
public class ItemScreen {
	private JFrame frame;
	private JList<Item> inventoryList;
	private JList<Monster> teamList;
	
	private GameEnvironment env;
	
	/**
	 * Creates the item screen, initializing the gui and sets the game environment.
	 * @param incomingEnv GameEnvironment. The incoming game environment.
	 */
	public ItemScreen(GameEnvironment incomingEnv) {
		env = incomingEnv;
		initialize();
		updateItems();
		frame.setVisible(true);
	}
	
	/**
	 * Closes the window.
	 */
	private void closeWindow() {
		frame.dispose();
	}
	
	/**
	 * Updates the items gui list after one is used.
	 */
	private void updateItems() {
		DefaultListModel<Item> inventoryListModel = new DefaultListModel<>();
		inventoryListModel.addAll(env.getPlayer().getInventory());
		inventoryList.setModel(inventoryListModel);
		updateMonsters();
	}
	
	/**
	 * Updates the monsters gui list to contain only the player's monsters which the currently selected item can be used on.
	 */
	private void updateMonsters() {
		try {
			Item item = inventoryList.getSelectedValue();
			ArrayList<Monster> usableOn = item.getMonstersUsableOn(env.getPlayer().getTeam());
			Monster[] monsters = new Monster[usableOn.size()];
			for(int i=0; i<usableOn.size(); i++) {
				monsters[i] = usableOn.get(i);
			}
			teamList.setListData(monsters);
			if(usableOn.size()==0) JOptionPane.showMessageDialog(frame, "This item is not usable on any of your monsters.");
		}
		catch (Exception exc){
			Monster[] monsters = new Monster[0];
			teamList.setListData(monsters);
		}
	}
	
	/**
	 * Changes to the shop screen.
	 */
	private void shopTransistion() {
		new ShopScreen(env);
		closeWindow();
	}
	
	/**
	 * Changes to the main screen.
	 */
	private void mainTransistion() {
		new MainScreen(env);
		closeWindow();
	}
	
	/**
	 * Attempts to use the selected item on the selected monster.
	 */
	private void useItem() {
		try {
			Item item = inventoryList.getSelectedValue();
			Monster monst = teamList.getSelectedValue();
			env.getPlayer().useItem(item, monst);
			updateItems();
		} 
		catch(NullPointerException excep) {
			JOptionPane.showMessageDialog(frame, "No Item To Use.");
		}
		catch(Exception excep) {
			JOptionPane.showMessageDialog(frame, "Select an item and a monster to use the item on.");
		}
	}
	
	/**
	 * Initialize the contents of the frame and contains the methods for buttons.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Monster Battler");
		frame.setBounds(100, 100, 700, 356);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel itemScreenLabel = new JLabel(env.getPlayer().getName() + "'s inventory");
		itemScreenLabel.setFont(new Font("SimSun", Font.PLAIN, 14));
		itemScreenLabel.setBounds(277, 0, 171, 35);
		frame.getContentPane().add(itemScreenLabel);
		
		JButton shopButton = new JButton("Shop");
		shopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shopTransistion();
			}
		});
		shopButton.setBounds(292, 213, 133, 29);
		frame.getContentPane().add(shopButton);
		
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainTransistion();
			}
		});
		backButton.setBounds(510, 213, 133, 29);
		frame.getContentPane().add(backButton);
		
		
		JScrollPane itemScrollPane = new JScrollPane();
		itemScrollPane.setBounds(55, 55, 301, 117);
		frame.getContentPane().add(itemScrollPane);
		inventoryList = new JList<Item>();
		inventoryList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				updateMonsters();
			}
		});
		itemScrollPane.setViewportView(inventoryList);
		inventoryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
				
		JScrollPane teamScrollPane = new JScrollPane();
		teamScrollPane.setBounds(366, 55, 277, 117);
		frame.getContentPane().add(teamScrollPane);
		
		teamList = new JList<Monster>();
		teamList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		teamScrollPane.setViewportView(teamList);
		
		JButton useItemButton = new JButton("Use Item");
		useItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				useItem();
			}
		});
		useItemButton.setBounds(55, 213, 133, 29);
		frame.getContentPane().add(useItemButton);
		
		JLabel monstersLabel = new JLabel("Monsters Usable On");
		monstersLabel.setBounds(409, 36, 202, 14);
		frame.getContentPane().add(monstersLabel);
		
		JLabel itemsLabel = new JLabel("Items");
		itemsLabel.setBounds(173, 36, 48, 14);
		frame.getContentPane().add(itemsLabel);
	}
}
