package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import game.GameEnvironment;
import game.monsters.Monster;

import javax.swing.JProgressBar;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JTextField;

public class TeamScreen {

	private JFrame frame;
	private ScreenManager scrMan;
	private GameEnvironment env;
	private JTextField renameMonsterTxt;
	
	public TeamScreen(ScreenManager incScrMan) {
		scrMan = incScrMan;
		env = scrMan.getEnv();
		initialize();
		frame.setVisible(true);
	}
	
	public void closeWindow() {
		frame.dispose();
	}
	
	public void finishedWindow() {
		scrMan.closeTeamScreen(this);
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeamScreen window = new TeamScreen();
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
	public TeamScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 880, 568);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel teamScreenLabel = new JLabel("Team");
		teamScreenLabel.setHorizontalAlignment(SwingConstants.CENTER);
		teamScreenLabel.setFont(new Font("SimSun", Font.PLAIN, 14));
		teamScreenLabel.setBounds(52, 10, 125, 31);
		frame.getContentPane().add(teamScreenLabel);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		btnNewButton.setBounds(548, 346, 132, 43);
		frame.getContentPane().add(btnNewButton);
		
		DefaultListModel<Monster> monsterListModel = new DefaultListModel<>();
		monsterListModel.addAll(env.getPlayer().getTeam());
		JList<Monster> monsterList = new JList<>(monsterListModel);
		monsterList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		monsterList.setBounds(77, 51, 701, 254);
		frame.getContentPane().add(monsterList);
		monsterList.getSelectedValue();
		
		JButton renameMonsterButton = new JButton("Rename Monster");
		renameMonsterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String newName = renameMonsterTxt.getText();
				monsterList.getSelectedValue().setName(newName);
				closeWindow();
				scrMan.launchTeamScreen();
			}
		});
		renameMonsterButton.setBounds(229, 346, 132, 43);
		frame.getContentPane().add(renameMonsterButton);
		
		renameMonsterTxt = new JTextField();
		renameMonsterTxt.setBounds(111, 357, 66, 21);
		frame.getContentPane().add(renameMonsterTxt);
		renameMonsterTxt.setColumns(10);
		monsterList.getSelectedValue();
		
	}
}
