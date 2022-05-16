package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import testproject.GameEnvironment;

import javax.swing.JProgressBar;
import javax.swing.JButton;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TeamScreen {

	private JFrame frame;
	private GameEnvironment env;
	
	public TeamScreen(GameEnvironment incomingEnv) {
		env = incomingEnv;
		initialize();
		frame.setVisible(true);
	}
	
	public void closeWindow() {
		frame.dispose();
	}
	
	public void finishedWindow() {
		env.closeTeamScreen(this);
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
		teamScreenLabel.setBounds(29, 10, 125, 31);
		frame.getContentPane().add(teamScreenLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(39, 51, 774, 140);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel activeTeamLabel = new JLabel("Active Team");
		activeTeamLabel.setBounds(332, 10, 92, 15);
		activeTeamLabel.setFont(new Font("SimSun", Font.PLAIN, 14));
		activeTeamLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(activeTeamLabel);
		
		JButton monsterOneButton = new JButton("Monster 1");
		monsterOneButton.setBounds(29, 47, 126, 49);
		panel.add(monsterOneButton);
		
		JButton monsterTwoButton = new JButton("Monster 2");
		monsterTwoButton.setBounds(177, 47, 126, 49);
		panel.add(monsterTwoButton);
		
		JButton monsterThreeButton = new JButton("Monster 3");
		monsterThreeButton.setBounds(326, 47, 126, 49);
		panel.add(monsterThreeButton);
		
		JButton monsterFourButton = new JButton("Monster 4");
		monsterFourButton.setBounds(477, 47, 126, 49);
		panel.add(monsterFourButton);
		
		JButton monsterFiveButton = new JButton("Monster 5");
		monsterFiveButton.setBounds(638, 47, 126, 49);
		panel.add(monsterFiveButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(39, 225, 774, 209);
		frame.getContentPane().add(panel_1);
		
		JLabel backupMonsterLabel = new JLabel("Backup Monsters");
		backupMonsterLabel.setHorizontalAlignment(SwingConstants.CENTER);
		backupMonsterLabel.setFont(new Font("SimSun", Font.PLAIN, 14));
		backupMonsterLabel.setBounds(304, 10, 148, 15);
		panel_1.add(backupMonsterLabel);
		
		JButton backupOneButton = new JButton("Monster 1");
		backupOneButton.setBounds(29, 47, 126, 49);
		panel_1.add(backupOneButton);
		
		JButton backupTwoButton = new JButton("Monster 2");
		backupTwoButton.setBounds(177, 47, 126, 49);
		panel_1.add(backupTwoButton);
		
		JButton backupThreeButton = new JButton("Monster 3");
		backupThreeButton.setBounds(326, 47, 126, 49);
		panel_1.add(backupThreeButton);
		
		JButton backupFourButton = new JButton("Monster 4");
		backupFourButton.setBounds(477, 47, 126, 49);
		panel_1.add(backupFourButton);
		
		JButton backupFiveButton = new JButton("Monster 5");
		backupFiveButton.setBounds(638, 47, 126, 49);
		panel_1.add(backupFiveButton);
		
		JButton backupSixButton = new JButton("Monster 6");
		backupSixButton.setBounds(29, 129, 126, 49);
		panel_1.add(backupSixButton);
		
		JButton backupSevenButton = new JButton("Monster 7");
		backupSevenButton.setBounds(177, 129, 126, 49);
		panel_1.add(backupSevenButton);
		
		JButton backupEightButton = new JButton("Monster 8");
		backupEightButton.setBounds(326, 129, 126, 49);
		panel_1.add(backupEightButton);
		
		JButton backupNineButton = new JButton("Monster 9");
		backupNineButton.setBounds(477, 129, 126, 49);
		panel_1.add(backupNineButton);
		
		JButton backupTenButton = new JButton("Monster 10");
		backupTenButton.setBounds(638, 129, 126, 49);
		panel_1.add(backupTenButton);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		btnNewButton.setBounds(362, 456, 132, 43);
		frame.getContentPane().add(btnNewButton);
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
