package GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import game.GameEnvironment;
import game.Player;
import game.monsters.Monster;

import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.DefaultListModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

/**
 * Screen that shows all monsters in the team and allows player to rename monster.
 * @author chenz
 *
 */
public class TeamScreen {

	private JFrame frame;
	private JList<Monster> monsterList;
	
	private GameEnvironment env;
	private Player pla;
	private JTextField renameMonsterTxt;
	
	/**
	 * Initializes the gui and sets the game environment to the incoming one.
	 * @param incomingEnv GameEnvironment. The incoming game environment..
	 */
	public TeamScreen(GameEnvironment incomingEnv) {
		env = incomingEnv;
		pla = env.getPlayer();
		initialize();
		frame.setVisible(true);
	}
	
	/**
	 * Updates the gui monster list to contain the current team after renames, removals and reorders.
	 */
	private void updateMonsterList() {
		Monster[] currentTeam = new Monster[pla.getTeam().size()];
		for(int i=0;i<currentTeam.length;i++) {
			currentTeam[i] = pla.getTeam().get(i);
			
		}
		monsterList.setListData(currentTeam);
	}
	
	/**
	 * Closes the window.
	 */
	private void closeWindow() {
		frame.dispose();
	}
	
	/**
	 * Opens the main screen if team size at most 4.
	 */
	private void mainScreenTransistion() {
		if(pla.getTeam().size() > 4) {
			JOptionPane.showMessageDialog(frame, "Must remove monsters to continue, as 4 is the max team size.");
		}
		else {
			new MainScreen(env);
			closeWindow();
		}
	}
	
	/**
	 * Renames the selected monster.
	 */
	private void renameMonster() {
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
	
	/**
	 * Reorders the selected monster.
	 */
	private void reorderMonster() {
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
	
	/**
	 * Removes the selected monster from the player's team.
	 */
	private void removeMonster() {
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
	
	/**
	 * Initialize the contents of the frame and contains the methods for gui inputs.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Monster Battler");
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
				mainScreenTransistion();
			}
		});
		backButton.setBounds(77, 390, 111, 43);
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
				renameMonster();
			}
		});
		renameMonsterButton.setBounds(200, 390, 154, 43);
		frame.getContentPane().add(renameMonsterButton);
		
		renameMonsterTxt = new JTextField();
		renameMonsterTxt.setBounds(200, 357, 154, 21);
		frame.getContentPane().add(renameMonsterTxt);
		renameMonsterTxt.setColumns(10);
		
		JButton reorderMonsterButton = new JButton("Reorder");
		reorderMonsterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reorderMonster();
			}
		});
		reorderMonsterButton.setBounds(366, 390, 125, 43);
		frame.getContentPane().add(reorderMonsterButton);
		
		JButton removeMonsterButton = new JButton("Remove Monster (Permanent)");
		removeMonsterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeMonster();
			}
		});
		removeMonsterButton.setBounds(509, 390, 269, 43);
		frame.getContentPane().add(removeMonsterButton);
		
		JTextPane reorderHelpText = new JTextPane();
		reorderHelpText.setText("Select a monster, and reorder will move it to the top of your team.");
		reorderHelpText.setEditable(false);
		reorderHelpText.setBounds(366, 317, 125, 66);
		frame.getContentPane().add(reorderHelpText);
		monsterList.getSelectedValue();
		
	}
}