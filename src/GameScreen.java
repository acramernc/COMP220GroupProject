import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.JSlider;

import javax.swing.JButton;

public class GameScreen implements ActionListener {

	private JFrame frame;
	JButton button;
	JButton button_1;
	JButton button_2;
	JButton button_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameScreen window = new GameScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		System.out.println("Done");
	}

	/**
	 * Create the application.
	 */
	public GameScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(){
		frame = new JFrame();
		frame.setBounds(100, 100, 910, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File(".\\CardImages\\10c.png"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		Image dimg = img.getScaledInstance(83, 114,
		        Image.SCALE_SMOOTH);
		
		ImageIcon icon = new ImageIcon(dimg);
		
		//Player 2's Cards
		
		frame.getContentPane().setLayout(null);
		JLabel lblNewLabel = new JLabel(icon);
		lblNewLabel.setBounds(448, 0, 83, 114);
		frame.getContentPane().add(lblNewLabel);
		
		
		JLabel lblNewLabel_1 = new JLabel(icon);
		lblNewLabel_1.setBounds(355, 0, 83, 114);
		frame.getContentPane().add(lblNewLabel_1);
		
		//Player 3's Cards
		
		JLabel lblNewLabel_2 = new JLabel(icon);
		lblNewLabel_2.setBounds(712, 264, 83, 114);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel(icon);
		lblNewLabel_3.setBounds(805, 264, 83, 114);
		frame.getContentPane().add(lblNewLabel_3);
		
		//Player 1's Cards
		
		JLabel lblNewLabel_4 = new JLabel(icon);
		lblNewLabel_4.setBounds(10, 264, 83, 114);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel(icon);
		lblNewLabel_5.setBounds(103, 264, 83, 114);
		frame.getContentPane().add(lblNewLabel_5);
		
		//Your Cards
		
		JLabel lblNewLabel_6 = new JLabel(icon);
		lblNewLabel_6.setBounds(355, 492, 83, 114);
		frame.getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel(icon);
		lblNewLabel_7.setBounds(448, 492, 83, 114);
		frame.getContentPane().add(lblNewLabel_7);
		
		//Dealer's Cards
		
		JLabel lblNewLabel_8 = new JLabel(icon);
		lblNewLabel_8.setBounds(225, 264, 83, 114);
		frame.getContentPane().add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel(icon);
		lblNewLabel_9.setBounds(320, 264, 83, 114);
		frame.getContentPane().add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel(icon);
		lblNewLabel_10.setBounds(412, 264, 83, 114);
		frame.getContentPane().add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel(icon);
		lblNewLabel_11.setBounds(504, 264, 83, 114);
		frame.getContentPane().add(lblNewLabel_11);
		
		JLabel lblNewLabel_12 = new JLabel(icon);
		lblNewLabel_12.setBounds(596, 264, 83, 114);
		frame.getContentPane().add(lblNewLabel_12);
		
		//Labels
		
		JLabel lblPlayer = new JLabel("Player 2");
		lblPlayer.setBounds(421, 125, 47, 14);
		frame.getContentPane().add(lblPlayer);
		
		JLabel lblPlayer_1 = new JLabel("Player 1");
		lblPlayer_1.setBounds(75, 389, 47, 14);
		frame.getContentPane().add(lblPlayer_1);
		
		JLabel lblPlayer_2 = new JLabel("Player 3");
		lblPlayer_2.setBounds(779, 389, 47, 14);
		frame.getContentPane().add(lblPlayer_2);

		JLabel lblDealer = new JLabel("Dealer");
		lblDealer.setBounds(426, 389, 105, 14);
		frame.getContentPane().add(lblDealer);
		
		JButton btnPlaceBet = new JButton("Place Bet");
		btnPlaceBet.setBounds(397, 447, 89, 35);
		frame.getContentPane().add(btnPlaceBet);
		
		JLabel lblYou = new JLabel("You");
		lblYou.setBounds(318, 548, 47, 14);
		frame.getContentPane().add(lblYou);
		
		JLabel label = new JLabel("$150");
		label.setBounds(541, 548, 47, 14);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("$50");
		label_1.setBounds(551, 417, 47, 14);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("$150");
		label_2.setBounds(431, 150, 73, 14);
		frame.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("$150");
		label_3.setBounds(85, 414, 47, 14);
		frame.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("$150");
		label_4.setBounds(789, 414, 47, 14);
		frame.getContentPane().add(label_4);
		
		JLabel lblNewLabel_13 = new JLabel("Bet");
		lblNewLabel_13.setBounds(318, 193, 47, 14);
		frame.getContentPane().add(lblNewLabel_13);
		
		JLabel lblNewLabel_14 = new JLabel("Pot");
		lblNewLabel_14.setBounds(541, 193, 47, 14);
		frame.getContentPane().add(lblNewLabel_14);
		
		JLabel label_5 = new JLabel("$10");
		label_5.setBounds(318, 218, 47, 14);
		frame.getContentPane().add(label_5);
		
		JLabel label_6 = new JLabel("$100");
		label_6.setBounds(541, 218, 47, 14);
		frame.getContentPane().add(label_6);
		
		button = new JButton("Fold");
		button.addActionListener(this);
		button.setBounds(600, 447, 89, 35);
		frame.getContentPane().add(button);
		
		button_1 = new JButton("Check");
		button_1.addActionListener(this);
		button_1.setBounds(298, 446, 89, 35);
		frame.getContentPane().add(button_1);
		
		button_2 = new JButton("Call");
		button_2.addActionListener(this);
		button_2.setBounds(499, 446, 89, 35);
		frame.getContentPane().add(button_2);
		
		button_3 = new JButton("Raise");
		button_3.addActionListener(this);
		button_3.setBounds(199, 447, 89, 35);
		frame.getContentPane().add(button_3);
		
		//Betting Slider
		JSlider slider = new JSlider();
		slider.setBounds(341, 401, 200, 48);
		frame.getContentPane().add(slider);
		
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button) {
			System.out.println("Fold");
		}
		else if (e.getSource() == button_1) {
			System.out.println("Check");
		}
		else if (e.getSource() == button_2) {
			System.out.println("Call");
		}
		else if (e.getSource() == button_3) {
			System.out.println("Raise");
		}
		
	}

}
