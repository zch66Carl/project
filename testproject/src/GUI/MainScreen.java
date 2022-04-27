package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

public class MainScreen {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreen window = new MainScreen();
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
	public MainScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 685, 378);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Player 1");
		lblNewLabel.setFont(new Font("SimSun", Font.PLAIN, 14));
		lblNewLabel.setBounds(40, 10, 116, 33);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Gold");
		lblNewLabel_1.setFont(new Font("SimSun", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(135, 19, 54, 15);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("500");
		lblNewLabel_2.setFont(new Font("SimSun", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(177, 19, 54, 15);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Day");
		lblNewLabel_3.setFont(new Font("SimSun", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(533, 18, 54, 15);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("5");
		lblNewLabel_4.setFont(new Font("SimSun", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(580, 19, 54, 15);
		frame.getContentPane().add(lblNewLabel_4);
		
		JButton btnNewButton = new JButton("Items");
		btnNewButton.setFont(new Font("SimSun", Font.PLAIN, 14));
		btnNewButton.setBounds(40, 88, 165, 66);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnTeam = new JButton("Team");
		btnTeam.setFont(new Font("SimSun", Font.PLAIN, 14));
		btnTeam.setBounds(230, 88, 165, 66);
		frame.getContentPane().add(btnTeam);
		
		JButton btnShop = new JButton("Shop");
		btnShop.setFont(new Font("SimSun", Font.PLAIN, 14));
		btnShop.setBounds(422, 88, 165, 66);
		frame.getContentPane().add(btnShop);
		
		JButton btnWildBattle = new JButton("Wild Battle");
		btnWildBattle.setFont(new Font("SimSun", Font.PLAIN, 14));
		btnWildBattle.setBounds(119, 187, 165, 66);
		frame.getContentPane().add(btnWildBattle);
		
		JButton btnPlayerBattle = new JButton("Player Battle");
		btnPlayerBattle.setFont(new Font("SimSun", Font.PLAIN, 14));
		btnPlayerBattle.setBounds(341, 187, 165, 66);
		frame.getContentPane().add(btnPlayerBattle);
		
		JButton btnNewButton_1 = new JButton("End Game");
		btnNewButton_1.setBounds(541, 282, 93, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_5 = new JLabel("Score");
		lblNewLabel_5.setFont(new Font("SimSun", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(255, 19, 54, 15);
		frame.getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_5_1 = new JLabel("2350");
		lblNewLabel_5_1.setFont(new Font("SimSun", Font.PLAIN, 14));
		lblNewLabel_5_1.setBounds(305, 19, 54, 15);
		frame.getContentPane().add(lblNewLabel_5_1);
	}

}
