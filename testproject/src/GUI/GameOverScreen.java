package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;

public class GameOverScreen {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameOverScreen window = new GameOverScreen();
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
	public GameOverScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 741, 518);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Game Over");
		lblNewLabel.setFont(new Font("SimSun", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(212, 10, 239, 59);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Player Name:");
		lblNewLabel_1.setFont(new Font("SimSun", Font.BOLD, 18));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(45, 100, 195, 42);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Player 1");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("SimSun", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(421, 100, 195, 42);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Gold:");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setFont(new Font("SimSun", Font.BOLD, 18));
		lblNewLabel_1_2.setBounds(45, 156, 195, 42);
		frame.getContentPane().add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("100000");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3.setFont(new Font("SimSun", Font.BOLD, 18));
		lblNewLabel_1_3.setBounds(421, 152, 195, 42);
		frame.getContentPane().add(lblNewLabel_1_3);
		
		JButton btnNewButton = new JButton("Main Screen");
		btnNewButton.setFont(new Font("SimSun", Font.PLAIN, 18));
		btnNewButton.setBounds(45, 372, 203, 59);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnExitGame = new JButton("Exit Game");
		btnExitGame.setFont(new Font("SimSun", Font.PLAIN, 18));
		btnExitGame.setBounds(421, 372, 203, 59);
		frame.getContentPane().add(btnExitGame);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Days Completed:");
		lblNewLabel_1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2_1.setFont(new Font("SimSun", Font.BOLD, 18));
		lblNewLabel_1_2_1.setBounds(45, 208, 195, 42);
		frame.getContentPane().add(lblNewLabel_1_2_1);
		
		JLabel lblNewLabel_1_2_2 = new JLabel("6");
		lblNewLabel_1_2_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2_2.setFont(new Font("SimSun", Font.BOLD, 18));
		lblNewLabel_1_2_2.setBounds(421, 204, 195, 42);
		frame.getContentPane().add(lblNewLabel_1_2_2);
		
		JLabel lblNewLabel_1_2_3 = new JLabel("Score:");
		lblNewLabel_1_2_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2_3.setFont(new Font("SimSun", Font.BOLD, 18));
		lblNewLabel_1_2_3.setBounds(45, 260, 195, 42);
		frame.getContentPane().add(lblNewLabel_1_2_3);
		
		JLabel lblNewLabel_1_2_4 = new JLabel("23156");
		lblNewLabel_1_2_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2_4.setFont(new Font("SimSun", Font.BOLD, 18));
		lblNewLabel_1_2_4.setBounds(421, 260, 195, 42);
		frame.getContentPane().add(lblNewLabel_1_2_4);
	}
}
