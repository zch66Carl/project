package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
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

public class TeamScreen {

	private JFrame frame;

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
		
		JLabel lblNewLabel = new JLabel("Team");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("SimSun", Font.PLAIN, 14));
		lblNewLabel.setBounds(29, 10, 125, 31);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(39, 51, 774, 140);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Active Team");
		lblNewLabel_1.setBounds(332, 10, 92, 15);
		lblNewLabel_1.setFont(new Font("SimSun", Font.PLAIN, 14));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Monster 1");
		btnNewButton.setBounds(29, 47, 126, 49);
		panel.add(btnNewButton);
		
		JButton btnMonster = new JButton("Monster 2");
		btnMonster.setBounds(177, 47, 126, 49);
		panel.add(btnMonster);
		
		JButton btnMonster_1 = new JButton("Monster 3");
		btnMonster_1.setBounds(326, 47, 126, 49);
		panel.add(btnMonster_1);
		
		JButton btnMonster_2 = new JButton("Monster 4");
		btnMonster_2.setBounds(477, 47, 126, 49);
		panel.add(btnMonster_2);
		
		JButton btnMonster_3 = new JButton("Monster 5");
		btnMonster_3.setBounds(638, 47, 126, 49);
		panel.add(btnMonster_3);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(39, 225, 774, 209);
		frame.getContentPane().add(panel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Backup Monsters");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("SimSun", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(304, 10, 148, 15);
		panel_1.add(lblNewLabel_1_1);
		
		JButton btnNewButton_1 = new JButton("Monster 1");
		btnNewButton_1.setBounds(29, 47, 126, 49);
		panel_1.add(btnNewButton_1);
		
		JButton btnMonster_4 = new JButton("Monster 2");
		btnMonster_4.setBounds(177, 47, 126, 49);
		panel_1.add(btnMonster_4);
		
		JButton btnMonster_1_1 = new JButton("Monster 3");
		btnMonster_1_1.setBounds(326, 47, 126, 49);
		panel_1.add(btnMonster_1_1);
		
		JButton btnMonster_2_1 = new JButton("Monster 4");
		btnMonster_2_1.setBounds(477, 47, 126, 49);
		panel_1.add(btnMonster_2_1);
		
		JButton btnMonster_3_1 = new JButton("Monster 5");
		btnMonster_3_1.setBounds(638, 47, 126, 49);
		panel_1.add(btnMonster_3_1);
		
		JButton btnNewButton_1_1 = new JButton("Monster 6");
		btnNewButton_1_1.setBounds(29, 129, 126, 49);
		panel_1.add(btnNewButton_1_1);
		
		JButton btnMonster_4_1 = new JButton("Monster 7");
		btnMonster_4_1.setBounds(177, 129, 126, 49);
		panel_1.add(btnMonster_4_1);
		
		JButton btnMonster_1_1_1 = new JButton("Monster 8");
		btnMonster_1_1_1.setBounds(326, 129, 126, 49);
		panel_1.add(btnMonster_1_1_1);
		
		JButton btnMonster_2_1_1 = new JButton("Monster 9");
		btnMonster_2_1_1.setBounds(477, 129, 126, 49);
		panel_1.add(btnMonster_2_1_1);
		
		JButton btnMonster_3_1_1 = new JButton("Monster 10");
		btnMonster_3_1_1.setBounds(638, 129, 126, 49);
		panel_1.add(btnMonster_3_1_1);
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
