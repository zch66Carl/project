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
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

/**
 * Item screen shows players inventory and team that allows items to be used out of battle.
 * @author chenz
 *
 */
public class ItemScreen {

	private JFrame frame;
	private ScreenManager scrMan;
	private GameEnvironment env;
	
	public ItemScreen(ScreenManager incScrMan) {
		scrMan = incScrMan;
		env = scrMan.getEnv();
		initialize();
		frame.setVisible(true);
	}
	
	public void closeWindow() {
		frame.dispose();
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
	public ItemScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
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
				scrMan.launchShopScreen();
				closeWindow();
			}
		});
		shopButton.setBounds(292, 213, 133, 29);
		frame.getContentPane().add(shopButton);
		
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		backButton.setBounds(510, 213, 133, 29);
		frame.getContentPane().add(backButton);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(55, 55, 301, 117);
		frame.getContentPane().add(scrollPane);
		DefaultListModel<Item> inventoryListModel = new DefaultListModel<>();
		inventoryListModel.addAll(env.getPlayer().getInventory());
		JList<Item> inventoryList = new JList<>(inventoryListModel);
		scrollPane.setViewportView(inventoryList);
		inventoryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
				
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(366, 55, 277, 117);
		frame.getContentPane().add(scrollPane_1);
		
		DefaultListModel<Monster> teamListModel = new DefaultListModel<>();
		teamListModel.addAll(env.getPlayer().getTeam());
		JList<Monster> teamList = new JList<>(teamListModel);
		teamList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setViewportView(teamList);
		
		JButton useItemButton = new JButton("Use Item");
		useItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				inventoryList.getSelectedValue().useItem(teamList.getSelectedValue());
				Item[] currentItems = new Item[env.getPlayer().getInventory().size()];
				for(int i=0;i<currentItems.length;i++) {
					currentItems[i] = env.getPlayer().getInventory().get(i);
					
				}
				
				inventoryList.setListData(currentItems);
				} 
				catch(NullPointerException excep) {
					JOptionPane.showMessageDialog(frame, "No Item To Use");
				}
				catch(Exception excep) {
					JOptionPane.showMessageDialog(frame, "Select Item and Target Monster to use");
				}
			}
		});
		useItemButton.setBounds(55, 213, 133, 29);
		frame.getContentPane().add(useItemButton);
		
		JLabel lblNewLabel = new JLabel("Team");
		lblNewLabel.setBounds(485, 36, 48, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Items");
		lblNewLabel_1.setBounds(173, 36, 48, 14);
		frame.getContentPane().add(lblNewLabel_1);
	}
}