package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class MonsterScreen {

	private JFrame frame;
	private JTextField renameMonsterTextBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MonsterScreen window = new MonsterScreen();
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
	public MonsterScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 555, 487);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel monsterName = new JLabel("Eagle");
		monsterName.setFont(new Font("SimSun", Font.PLAIN, 14));
		monsterName.setHorizontalAlignment(SwingConstants.CENTER);
		monsterName.setBounds(201, 10, 119, 33);
		frame.getContentPane().add(monsterName);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(31, 53, 451, 185);
		frame.getContentPane().add(panel);
		
		JLabel monsterInfoLabel = new JLabel("Monster Information");
		monsterInfoLabel.setFont(new Font("SimSun", Font.BOLD, 14));
		monsterInfoLabel.setBounds(137, 0, 149, 31);
		panel.add(monsterInfoLabel);
		
		JLabel maxHealthLabel = new JLabel("Max Health:");
		maxHealthLabel.setBounds(10, 29, 73, 25);
		panel.add(maxHealthLabel);
		
		JLabel damageLabel = new JLabel("Damage:");
		damageLabel.setBounds(10, 64, 73, 25);
		panel.add(damageLabel);
		
		JLabel currentHealthLabel = new JLabel("Current Health:");
		currentHealthLabel.setBounds(10, 102, 90, 15);
		panel.add(currentHealthLabel);
		
		JLabel abilityLabel = new JLabel("Ability:");
		abilityLabel.setBounds(10, 136, 84, 15);
		panel.add(abilityLabel);
		
		JLabel maxHealth = new JLabel("800");
		maxHealth.setBounds(183, 34, 54, 15);
		panel.add(maxHealth);
		
		JLabel damage = new JLabel("15-150");
		damage.setBounds(183, 69, 54, 15);
		panel.add(damage);
		
		JLabel currentHealth = new JLabel("35");
		currentHealth.setBounds(183, 102, 54, 15);
		panel.add(currentHealth);
		
		JLabel ability = new JLabel("Fly");
		ability.setBounds(183, 136, 54, 15);
		panel.add(ability);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBounds(296, 29, 134, 119);
		panel.add(lblNewLabel_3);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setIcon(new ImageIcon(MonsterScreen.class.getResource("/GUI/images/eagle_resized.jpg")));
		
		JLabel renameMonsterLabel = new JLabel("Rename Monster:");
		renameMonsterLabel.setFont(new Font("SimSun", Font.PLAIN, 14));
		renameMonsterLabel.setHorizontalAlignment(SwingConstants.LEFT);
		renameMonsterLabel.setBounds(31, 263, 112, 15);
		frame.getContentPane().add(renameMonsterLabel);
		
		renameMonsterTextBox = new JTextField();
		renameMonsterTextBox.setBounds(201, 260, 171, 21);
		frame.getContentPane().add(renameMonsterTextBox);
		renameMonsterTextBox.setColumns(10);
		
		JLabel addToTeamLabel = new JLabel("Add to team(Position):");
		addToTeamLabel.setFont(new Font("SimSun", Font.PLAIN, 14));
		addToTeamLabel.setHorizontalAlignment(SwingConstants.LEFT);
		addToTeamLabel.setBounds(31, 309, 171, 15);
		frame.getContentPane().add(addToTeamLabel);
		
		JComboBox positionOfTeamSelection = new JComboBox();
		positionOfTeamSelection.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
		positionOfTeamSelection.setBounds(235, 305, 34, 23);
		frame.getContentPane().add(positionOfTeamSelection);
		
		JButton addButton = new JButton("Add");
		addButton.setBounds(279, 305, 93, 23);
		frame.getContentPane().add(addButton);
		
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("SimSun", Font.PLAIN, 16));
		backButton.setBounds(186, 357, 134, 49);
		frame.getContentPane().add(backButton);
		
		JButton removeButton = new JButton("Remove");
		removeButton.setBounds(382, 305, 93, 23);
		frame.getContentPane().add(removeButton);
		
		JButton confirmRenameButton = new JButton("Confirm");
		confirmRenameButton.setBounds(386, 259, 89, 23);
		frame.getContentPane().add(confirmRenameButton);
	}
}
