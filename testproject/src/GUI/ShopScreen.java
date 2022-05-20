package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import testproject.GameEnvironment;
import testproject.Item;
import testproject.Purchaseable;
import testproject.monsters.Monster;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.ListModel;
import javax.swing.JScrollPane;

public class ShopScreen {

	private JFrame frame;
	private GameEnvironment env;
	
	public ShopScreen(GameEnvironment incomingEnv) {
		env = incomingEnv;
		initialize();
		frame.setVisible(true);
	}
	
	public void closeWindow() {
		frame.dispose();
	}
	
	public void finishedWindow() {
		env.closeShopScreen(this);
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShopScreen window = new ShopScreen();
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
	public ShopScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 747, 603);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel shopLabel = new JLabel("Shop");
		shopLabel.setHorizontalAlignment(SwingConstants.CENTER);
		shopLabel.setFont(new Font("SimSun", Font.BOLD, 14));
		shopLabel.setBounds(528, 10, 103, 35);
		frame.getContentPane().add(shopLabel);
		
		JLabel goldLabel = new JLabel("Gold");
		goldLabel.setBounds(31, 20, 54, 15);
		frame.getContentPane().add(goldLabel);
		
		JLabel currentGold = new JLabel(Integer.toString(env.getPlayer().getGold()));
		currentGold.setBounds(95, 20, 54, 15);
		frame.getContentPane().add(currentGold);
		
		JButton inventoryButton = new JButton("Inventory");
		inventoryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				env.launchItemsScreen();
				closeWindow();
			}
		});
		inventoryButton.setBounds(184, 16, 93, 23);
		frame.getContentPane().add(inventoryButton);
		
		JButton mainScreenButton = new JButton("Main Screen");
		mainScreenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		mainScreenButton.setBounds(287, 16, 119, 23);
		frame.getContentPane().add(mainScreenButton);
		
		
		JLabel stocklabel = new JLabel("Stock");
		stocklabel.setBounds(31, 45, 54, 15);
		frame.getContentPane().add(stocklabel);
		
		JLabel sellItemLabel = new JLabel("Item");
		sellItemLabel.setBounds(31, 294, 54, 15);
		frame.getContentPane().add(sellItemLabel);
		
		JLabel sellMonsterLabel = new JLabel("Monster");
		sellMonsterLabel.setBounds(31, 426, 54, 15);
		frame.getContentPane().add(sellMonsterLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 451, 403, 91);
		frame.getContentPane().add(scrollPane);
		
		DefaultListModel<Monster> teamListModel = new DefaultListModel<>();
		teamListModel.addAll(env.getPlayer().getTeam());
		JList<Monster> teamList = new JList<>(teamListModel);
		scrollPane.setViewportView(teamList);
		teamList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		DefaultListModel<Purchaseable> shopListModel = new DefaultListModel<>();
		for(int i=0;i<3;i++) {
			shopListModel.addElement((Monster) env.getShop().getStock()[i]);
		}
		for(int i=3;i<9;i++) {
			shopListModel.addElement((Item) env.getShop().getStock()[i]);
		}
		JList<Purchaseable> shopList = new JList<>(shopListModel);
		shopList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		shopList.setBounds(31, 71, 403, 205);
		frame.getContentPane().add(shopList);
		
		DefaultListModel<Item> inventoryListModel = new DefaultListModel<>();
		inventoryListModel.addAll(env.getPlayer().getInventory());
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(31, 319, 403, 85);
		frame.getContentPane().add(scrollPane_1);
		
		JList<Item> inventoryList = new JList<>(inventoryListModel);
		scrollPane_1.setViewportView(inventoryList);
		inventoryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JButton buyButton = new JButton("Buy");
		buyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					env.getShop().buyPurchaseable(env.getPlayer(), shopList.getSelectedIndex());
					shopList.setListData(env.getShop().getStock()); 
					Item[] currentItems = new Item[env.getPlayer().getInventory().size()];
					for(int i=0;i<currentItems.length;i++) {
						currentItems[i] = env.getPlayer().getInventory().get(i);
						}
						inventoryList.setListData(currentItems);
					
					Monster[] currentTeam = new Monster[env.getPlayer().getTeam().size()];
					for(int i=0;i<currentTeam.length;i++) {
						currentTeam[i] = env.getPlayer().getTeam().get(i);
					}
						teamList.setListData(currentTeam);
					
						currentGold.setText(Integer.toString(env.getPlayer().getGold()));	
					}
				
				catch (Exception excep) {
					JOptionPane.showMessageDialog(frame, "Please Select an item to buy");
				}
			}
		});
		buyButton.setBounds(538, 152, 119, 23);
		frame.getContentPane().add(buyButton);
		
		
		
		
		JButton sellItemButton = new JButton("Sell Item");
		sellItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					env.getShop().sellPurchaseable(env.getPlayer(), inventoryList.getSelectedValue());
					Item[] currentItems = new Item[env.getPlayer().getInventory().size()];
					for(int i=0;i<currentItems.length;i++) {
						currentItems[i] = env.getPlayer().getInventory().get(i);
						
					}
					
					inventoryList.setListData(currentItems);
				} catch (Exception excep) {
					JOptionPane.showMessageDialog(frame, "Please Select an item to sell");
				}
					currentGold.setText(Integer.toString(env.getPlayer().getGold()));
		
			}
		});
		sellItemButton.setBounds(538, 350, 119, 23);
		frame.getContentPane().add(sellItemButton);
		
		JButton sellMonsterButton = new JButton("Sell Monster");
		sellMonsterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					env.getShop().sellPurchaseable(env.getPlayer(), teamList.getSelectedValue());
					Monster[] currentTeam = new Monster[env.getPlayer().getTeam().size()];
					for(int i=0;i<currentTeam.length;i++) {
						currentTeam[i] = env.getPlayer().getTeam().get(i);
						
					}
					
					teamList.setListData(currentTeam);
				} catch (Exception excep) {
					JOptionPane.showMessageDialog(frame, "Please Select an item to sell");
				}
					currentGold.setText(Integer.toString(env.getPlayer().getGold()));
		
			}
		});
		sellMonsterButton.setBounds(538, 484, 119, 23);
		frame.getContentPane().add(sellMonsterButton);
		
		
	}
}
