package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import game.GameEnvironment;
import game.Item;
import game.Purchaseable;
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
	private JFrame frmMonsterBattler;
	private JList<Item> inventoryList;
	private JList<Monster> teamList;
	
	private ScreenManager scrMan;
	private GameEnvironment env;
	
	public ItemScreen(ScreenManager incScrMan) {
		scrMan = incScrMan;
		env = scrMan.getEnv();
		initialize();
		updateItems();
		frmMonsterBattler.setVisible(true);
	}
	
	public void closeWindow() {
		frmMonsterBattler.dispose();
	}
	
	public void finishedWindow() {
		scrMan.closeItemsScreen(this);
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ItemScreen window = new ItemScreen();
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
	public ItemScreen() {
		initialize();
	}

	private void updateItems() {
		DefaultListModel<Item> inventoryListModel = new DefaultListModel<>();
		inventoryListModel.addAll(env.getPlayer().getInventory());
		inventoryList.setModel(inventoryListModel);
		updateMonsters();
	}
	
	private void updateMonsters() {
		try {
			Item item = inventoryList.getSelectedValue();
			ArrayList<Monster> usableOn = item.getMonstersUsableOn(env.getPlayer().getTeam());
			Monster[] monsters = new Monster[usableOn.size()];
			for(int i=0; i<usableOn.size(); i++) {
				monsters[i] = usableOn.get(i);
			}
			teamList.setListData(monsters);
			if(usableOn.size()==0) JOptionPane.showMessageDialog(frmMonsterBattler, "This item is not usable on any of your monsters.");
		}
		catch (Exception exc){
			Monster[] monsters = new Monster[0];
			teamList.setListData(monsters);
		}
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMonsterBattler = new JFrame();
		frmMonsterBattler.setTitle("Monster Battler");
		frmMonsterBattler.setBounds(100, 100, 700, 356);
		frmMonsterBattler.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMonsterBattler.getContentPane().setLayout(null);
		
		JLabel itemScreenLabel = new JLabel(env.getPlayer().getName() + "'s inventory");
		itemScreenLabel.setFont(new Font("SimSun", Font.PLAIN, 14));
		itemScreenLabel.setBounds(277, 0, 171, 35);
		frmMonsterBattler.getContentPane().add(itemScreenLabel);
		
		JButton shopButton = new JButton("Shop");
		shopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scrMan.launchShopScreen();
				closeWindow();
			}
		});
		shopButton.setBounds(292, 213, 133, 29);
		frmMonsterBattler.getContentPane().add(shopButton);
		
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		backButton.setBounds(510, 213, 133, 29);
		frmMonsterBattler.getContentPane().add(backButton);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(55, 55, 301, 117);
		frmMonsterBattler.getContentPane().add(scrollPane);
		inventoryList = new JList<Item>();
		inventoryList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				updateMonsters();
			}
		});
		scrollPane.setViewportView(inventoryList);
		inventoryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
				
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(366, 55, 277, 117);
		frmMonsterBattler.getContentPane().add(scrollPane_1);
		
		teamList = new JList<Monster>();
		teamList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setViewportView(teamList);
		
		JButton useItemButton = new JButton("Use Item");
		useItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Item item = inventoryList.getSelectedValue();
					Monster monst = teamList.getSelectedValue();
					env.getPlayer().useItem(item, monst);
					updateItems();
				} 
				catch(NullPointerException excep) {
					JOptionPane.showMessageDialog(frmMonsterBattler, "No Item To Use.");
				}
				catch(Exception excep) {
					JOptionPane.showMessageDialog(frmMonsterBattler, "Select an item and a monster to use the item on.");
				}
			}
		});
		useItemButton.setBounds(55, 213, 133, 29);
		frmMonsterBattler.getContentPane().add(useItemButton);
		
		JLabel lblNewLabel = new JLabel("Monsters Usable On");
		lblNewLabel.setBounds(409, 36, 202, 14);
		frmMonsterBattler.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Items");
		lblNewLabel_1.setBounds(173, 36, 48, 14);
		frmMonsterBattler.getContentPane().add(lblNewLabel_1);
	}
}
