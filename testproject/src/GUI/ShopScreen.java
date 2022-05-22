package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import game.GameEnvironment;
import game.Item;
import game.Purchaseable;
import game.Shop;
import game.monsters.Monster;

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
import javax.swing.JTextPane;

/**
 * Shop screen allows player to buy and sell items/monsters.
 * @author chenz
 *
 */
public class ShopScreen {

	private JFrame frmMonsterBattler;
	private JList<String> teamList;
	private JList<String> inventoryList;
	private JList<String> shopList;
	private JLabel currentGold;
	private JTextPane stockPrices;
	
	private ScreenManager scrMan;
	private GameEnvironment env;
	private Shop shop;
	
	public ShopScreen(ScreenManager incScrMan) {
		scrMan = incScrMan;
		env = scrMan.getEnv();
		shop = env.getShop();
		initialize();
		update();
		frmMonsterBattler.setVisible(true);
	}
	
	public void closeWindow() {
		frmMonsterBattler.dispose();
	}
	
	public void finishedWindow() {
		scrMan.closeShopScreen(this);
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShopScreen window = new ShopScreen();
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
	public ShopScreen() {
		initialize();
	}

	private void update() {
		String[] currentTeam = new String[env.getPlayer().getTeam().size()];
		for(int i=0;i<currentTeam.length;i++) {
			Monster monst = env.getPlayer().getTeam().get(i);
			currentTeam[i] = monst.toString() + " Sell Price: " + monst.getSellPrice();
		}
		teamList.setListData(currentTeam);
				
		String[] currentInv = new String[env.getPlayer().getInventory().size()];
		for(int i=0;i<currentInv.length;i++) {
			Item item = env.getPlayer().getInventory().get(i);
			currentInv[i] = item.toString() + ". Sell Price: " + item.getSellPrice();
		}
		inventoryList.setListData(currentInv);
		
		
		ArrayList<String> listing = new ArrayList<String>();
		String prices = "";
		for(int i=0; i<9; i++) {
			if(shop.getStock()[i] != null) {
				if(i<3) listing.add(((Monster)shop.getStock()[i]).basicStr());
				else listing.add(shop.getStock()[i].toString());
				prices += shop.getStock()[i].getPrice() + "\n";
			}
		}
		String[] stockList = new String[listing.size()];
		for(int i=0; i<listing.size(); i++) {
			stockList[i] = listing.get(i);
		}
		shopList.setListData(stockList);
		stockPrices.setText(prices);
		
		
		currentGold.setText(Integer.toString(env.getPlayer().getGold()));
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMonsterBattler = new JFrame();
		frmMonsterBattler.setTitle("Monster Battler");
		frmMonsterBattler.setBounds(100, 100, 747, 603);
		frmMonsterBattler.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMonsterBattler.getContentPane().setLayout(null);
		
		JLabel shopLabel = new JLabel("Shop");
		shopLabel.setHorizontalAlignment(SwingConstants.CENTER);
		shopLabel.setFont(new Font("SimSun", Font.BOLD, 14));
		shopLabel.setBounds(0, 10, 103, 35);
		frmMonsterBattler.getContentPane().add(shopLabel);
		
		JLabel goldLabel = new JLabel("Gold");
		goldLabel.setBounds(267, 20, 54, 15);
		frmMonsterBattler.getContentPane().add(goldLabel);
		
		currentGold = new JLabel(Integer.toString(env.getPlayer().getGold()));
		currentGold.setBounds(333, 20, 54, 15);
		frmMonsterBattler.getContentPane().add(currentGold);
		
		JButton inventoryButton = new JButton("Inventory");
		inventoryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scrMan.launchItemsScreen();
				closeWindow();
			}
		});
		inventoryButton.setBounds(436, 16, 127, 23);
		frmMonsterBattler.getContentPane().add(inventoryButton);
		
		JButton mainScreenButton = new JButton("Main Screen");
		mainScreenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		mainScreenButton.setBounds(586, 16, 147, 23);
		frmMonsterBattler.getContentPane().add(mainScreenButton);
		
		
		JLabel stocklabel = new JLabel("Stock");
		stocklabel.setBounds(31, 59, 54, 15);
		frmMonsterBattler.getContentPane().add(stocklabel);
		
		JLabel sellItemLabel = new JLabel("Item");
		sellItemLabel.setBounds(31, 318, 54, 15);
		frmMonsterBattler.getContentPane().add(sellItemLabel);
		
		JLabel sellMonsterLabel = new JLabel("Monster");
		sellMonsterLabel.setBounds(31, 442, 127, 15);
		frmMonsterBattler.getContentPane().add(sellMonsterLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 469, 702, 91);
		frmMonsterBattler.getContentPane().add(scrollPane);
		
		DefaultListModel<String> teamListModel = new DefaultListModel<>();
		teamList = new JList<>(teamListModel);
		scrollPane.setViewportView(teamList);
		teamList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		DefaultListModel<String> shopListModel = new DefaultListModel<>();
		shopList = new JList<>(shopListModel);
		shopList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		shopList.setBounds(31, 86, 528, 220);
		frmMonsterBattler.getContentPane().add(shopList);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(31, 345, 702, 85);
		frmMonsterBattler.getContentPane().add(scrollPane_1);

		DefaultListModel<String> inventoryListModel = new DefaultListModel<>();
		inventoryList = new JList<>(inventoryListModel);
		scrollPane_1.setViewportView(inventoryList);
		inventoryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JButton buyButton = new JButton("Buy");
		buyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(shopList.getSelectedIndex() == -1) throw new RuntimeException("Nothing selected");
					int ind = shopList.getSelectedIndex();
					for(int i=0; i<9; i++) {
						if(shop.getStock()[i] == null) ind++;
						if(i==ind) {
							break;
						}
					}
					if(ind < 3 && env.getPlayer().getTeam().size()==4) {
						JOptionPane.showMessageDialog(frmMonsterBattler, "Can't buy a monster as team is full, go to the team screen to remove a monster.");
						return;
					}
					String message = shop.buyPurchaseable(env.getPlayer(), ind);
					JOptionPane.showMessageDialog(frmMonsterBattler, message);
				}
				catch (Exception excep) {
					JOptionPane.showMessageDialog(frmMonsterBattler, "Please Select an item to buy");
				}
				update();
			}
		});
		buyButton.setBounds(277, 55, 161, 23);
		frmMonsterBattler.getContentPane().add(buyButton);
		
		
		
		
		JButton sellItemButton = new JButton("Sell Item");
		sellItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(inventoryList.getSelectedIndex() == -1) throw new RuntimeException("Nothing selected");
					Item item = env.getPlayer().getInventory().get(inventoryList.getSelectedIndex());
					String message = shop.sellPurchaseable(env.getPlayer(), item);
					JOptionPane.showMessageDialog(frmMonsterBattler, message);
				} catch (Exception excep) {
					JOptionPane.showMessageDialog(frmMonsterBattler, "Please Select an item to sell");
				}
				update();
			}
		});
		sellItemButton.setBounds(267, 314, 161, 23);
		frmMonsterBattler.getContentPane().add(sellItemButton);
		
		JButton sellMonsterButton = new JButton("Sell Monster");
		sellMonsterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(teamList.getSelectedIndex() == -1) throw new RuntimeException("Nothing selected");
					Monster monst = env.getPlayer().getTeam().get(teamList.getSelectedIndex());
					String message = shop.sellPurchaseable(env.getPlayer(), monst);
					JOptionPane.showMessageDialog(frmMonsterBattler, message);
				} catch (Exception excep) {
					JOptionPane.showMessageDialog(frmMonsterBattler, "Please Select an item to sell");
				}
				update();
			}
		});
		sellMonsterButton.setBounds(267, 438, 161, 23);
		frmMonsterBattler.getContentPane().add(sellMonsterButton);
		
		JLabel priceLabel = new JLabel("Price");
		priceLabel.setBounds(596, 59, 54, 15);
		frmMonsterBattler.getContentPane().add(priceLabel);
		
		stockPrices = new JTextPane();
		stockPrices.setFont(new Font("Dialog", Font.BOLD, 13));
		stockPrices.setEditable(false);
		stockPrices.setBounds(573, 86, 160, 220);
		frmMonsterBattler.getContentPane().add(stockPrices);
		
		
	}
}
