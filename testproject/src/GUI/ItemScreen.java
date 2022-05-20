package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import testproject.GameEnvironment;
import testproject.Item;
import testproject.Purchaseable;
import testproject.monsters.Monster;

import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

public class ItemScreen {

	private JFrame frame;
	private ScreenManager scrMan;
	private GameEnvironment env;
	
	public ItemScreen(ScreenManager incScrMan, GameEnvironment incomingEnv) {
		scrMan = incScrMan;
		env = incomingEnv;
		initialize();
		frame.setVisible(true);
	}
	
	public void closeWindow() {
		frame.dispose();
	}
	
	public void finishedWindow() {
		scrMan.closeItemsScreen(this, env);
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
		
		JLabel itemScreenLabel = new JLabel(env.getPlayer().getName()+"'s items");
		itemScreenLabel.setFont(new Font("SimSun", Font.PLAIN, 14));
		itemScreenLabel.setBounds(55, 10, 140, 35);
		frame.getContentPane().add(itemScreenLabel);
		
		JButton shopButton = new JButton("Shop");
		shopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scrMan.launchShopScreen(env);
				closeWindow();
			}
		});
		shopButton.setBounds(253, 213, 133, 29);
		frame.getContentPane().add(shopButton);
		
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		backButton.setBounds(476, 213, 133, 29);
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
				} 
				catch(Exception excep) {
					JOptionPane.showMessageDialog(frame, "No Item to use");
				}
			}
		});
		useItemButton.setBounds(55, 213, 133, 29);
		frame.getContentPane().add(useItemButton);
	}
}
