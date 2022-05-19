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
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

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
		
		DefaultListModel<Purchaseable> inventoryListModel = new DefaultListModel<>();
		inventoryListModel.addAll(env.getPlayer().getInventory());
		inventoryListModel.addAll(env.getPlayer().getTeam());
		JList<Purchaseable> inventoryList = new JList<>(inventoryListModel);
		inventoryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		inventoryList.setBounds(31, 319, 403, 181);
		frame.getContentPane().add(inventoryList);
		
		JButton buyButton = new JButton("Buy");
		buyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				env.getShop().buyPurchaseable(env.getPlayer(), shopList.getSelectedIndex());
				shopList.setListData(env.getShop().getStock());
			}
		});
		buyButton.setBounds(538, 152, 93, 23);
		frame.getContentPane().add(buyButton);
		
		
		
		
		JButton sellButton = new JButton("Sell");
		sellButton.setBounds(538, 380, 93, 23);
		frame.getContentPane().add(sellButton);
		
	}
}
