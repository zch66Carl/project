package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import game.GameEnvironment;
import game.Player;
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
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * Screen that shows all monsters in the team and allows player to rename monster.
 * @author chenz
 *
 */
public class TeamScreen {

	private JFrame frame;
	private JList<Monster> monsterList;
	
	private ScreenManager scrMan;
	private GameEnvironment env;
	private Player pla;
	private JTextField renameMonsterTxt;
	
	public TeamScreen(ScreenManager incScrMan) {
		scrMan = incScrMan;
		env = scrMan.getEnv();
		pla = env.getPlayer();
		initialize();
		frame.setVisible(true);
	}
	
	public void updateMonsterList() {
		Monster[] currentTeam = new Monster[pla.getTeam().size()];
		for(int i=0;i<currentTeam.length;i++) {
			currentTeam[i] = pla.getTeam().get(i);
			
		}
		monsterList.setListData(currentTeam);
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
		
		JButton backButton = new JButton("Main Menu");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(pla.getTeam().size() > 4) {
					JOptionPane.showMessageDialog(frame, "Must remove monsters to continue, as 4 is the max team size.");
				}
				else {
					finishedWindow();
				}
			}
		});
		backButton.setBounds(77, 390, 132, 43);
		frame.getContentPane().add(backButton);
		
		DefaultListModel<Monster> monsterListModel = new DefaultListModel<>();
		monsterListModel.addAll(pla.getTeam());
		monsterList = new JList<>(monsterListModel);
		monsterList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		monsterList.setBounds(77, 51, 701, 254);
		frame.getContentPane().add(monsterList);
		monsterList.getSelectedValue();
		
		JButton renameMonsterButton = new JButton("Rename Monster");
		renameMonsterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String newName = renameMonsterTxt.getText();
					if(newName.isEmpty()) {
						throw new RuntimeException("Name can not be empty");
					} else {
						monsterList.getSelectedValue().setName(newName);
		
						updateMonsterList();
					}
				} 
				catch (Exception excep) {
					JOptionPane.showMessageDialog(frame, "Please Select Monster To Rename and can not be empty");
				} 

			}
		});
		renameMonsterButton.setBounds(243, 390, 182, 43);
		frame.getContentPane().add(renameMonsterButton);
		
		renameMonsterTxt = new JTextField();
		renameMonsterTxt.setBounds(243, 357, 182, 21);
		frame.getContentPane().add(renameMonsterTxt);
		renameMonsterTxt.setColumns(10);
		
		JButton reorderMonsterButton = new JButton("Reorder");
		reorderMonsterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(monsterList.getSelectedValue() == null) throw new RuntimeException("Didn't select a monster");
					
					Monster monst = monsterList.getSelectedValue();
					pla.removeMonster(monst);
					pla.getTeam().add(0, monst);
		
					updateMonsterList();
				} 
				catch (Exception excep) {
					JOptionPane.showMessageDialog(frame, "Please Select Monster To Reorder.");
				} 
			}
		});
		reorderMonsterButton.setBounds(459, 390, 125, 43);
		frame.getContentPane().add(reorderMonsterButton);
		
		JButton removeMonsterButton = new JButton("Remove Monster");
		removeMonsterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(monsterList.getSelectedValue() == null) throw new RuntimeException("Didn't select a monster");
					
					Monster monst = monsterList.getSelectedValue();
					pla.removeMonster(monst);
					
					updateMonsterList();
				} 
				catch (Exception excep) {
					JOptionPane.showMessageDialog(frame, "You  Must Select Monster To Remove.");
				} 
			}
		});
		removeMonsterButton.setBounds(615, 390, 152, 43);
		frame.getContentPane().add(removeMonsterButton);
		monsterList.getSelectedValue();
		
	}
}