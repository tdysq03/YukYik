package game;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;


public class GamePanel implements ActionListener {

	private JFrame frame;
	private JPanel panel,gameDisplay;
	private JLabel playerName,character,powerEnergyLabel,lifeEnergyLabel,stopwatchLabel,nameLabel;
	
	private static String name;
	private int number,count,step,lifeEnergy,score,bonusCollect;	
	private int[] queue;
	
	private ArrayList<ExerciseData>  exerciseData;
	private Stopwatch stopwatch;
	private Data data;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GamePanel window = new GamePanel(name);
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
	public GamePanel(String userInput) {
		
		name = userInput;
		number=0;count=0;step=1;lifeEnergy=5;
		data = new Data();
		try {
			exerciseData = data.getExerciseData();
			queue = data.getQueue();
		} catch (IOException e) {
			e.printStackTrace();
		}

		initialize();
		createGameController();
		createGameDisplay();
		
		}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(119, 136, 153));
		frame.setBounds(500, 100, 584, 762);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/heart.png")));
		frame.setTitle("YUK\u0E46YIK\u0E46");
		frame.setUndecorated(true);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		
		panel = new JPanel();
		panel.setBackground(new Color(119, 136, 153));
		panel.setBounds(0, 0, 584, 762);
		panel.setLayout(null);
		frame.getContentPane().add(panel);
		
		//Set background
		JLabel gameBackground = new JLabel("");
		gameBackground.setIcon(new ImageIcon(this.getClass().getResource("/images/gamebackgroung.png")));
		gameBackground.setBounds(0, 0, 584, 762);
		panel.add(gameBackground);
		
		//Show player's name
		playerName = new JLabel("PLAYER :");
		playerName.setForeground(Color.DARK_GRAY);
		playerName.setFont(new Font("Bai Jamjuree Light", Font.BOLD, 13));
		playerName.setBounds(40, 0, 300, 60);
		playerName.setText("PLAYER : "+name);
		panel.add(playerName);
		
		
		
		UIManager.put("OptionPane.background", Color.white);
		UIManager.put("Panel.background", Color.white);
		UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 14));
		UIManager.put("OptionPane.buttonFont", new Font("Arial", Font.PLAIN, 12));	
		}
	
	public void createGameController() {
		//Game controller
		JButton upButton = new JButton();
		upButton.setName("up");
		upButton.setIcon(new ImageIcon(this.getClass().getResource("/images/controlbtn1.png")));
		upButton.setBounds(105, 500, 65, 65);
		upButton.addActionListener(this);
		panel.add(upButton);
		JButton leftButton = new JButton();
		leftButton.setName("left");
		leftButton.setIcon(new ImageIcon(this.getClass().getResource("/images/controlbtn2.png")));
		leftButton.setBounds(40, 565, 65, 65);
		leftButton.addActionListener(this);
		panel.add(leftButton);
		JButton downButton = new JButton();
		downButton.setName("down");
		downButton.setIcon(new ImageIcon(this.getClass().getResource("/images/controlbtn1.png")));
		downButton.setBounds(105, 630, 65, 65);
		downButton.addActionListener(this);
		panel.add(downButton);
		JButton rightButton = new JButton();
		rightButton.setName("right");
		rightButton.setIcon(new ImageIcon(this.getClass().getResource("/images/controlbtn2.png")));			
		rightButton.setBounds(170, 565, 65, 65);
		rightButton.addActionListener(this);
		panel.add(rightButton);
				
		JButton resetButton = new JButton("RESET");
		resetButton.setName("reset");
		resetButton.setForeground(Color.LIGHT_GRAY);
		resetButton.setFont(new Font("BankGothic Md BT", Font.BOLD, 11));
		resetButton.setVerticalTextPosition( SwingConstants.BOTTOM ) ;
		resetButton.setHorizontalTextPosition ( SwingConstants.CENTER );
		resetButton.setOpaque(false);
		resetButton.setContentAreaFilled(false);
		resetButton.setBorderPainted(false);
		resetButton.setIcon(new ImageIcon(this.getClass().getResource("/images/redbtn.png")));
		resetButton.setBounds(470, 510, 80, 90);
		resetButton.addActionListener(this);
		panel.add(resetButton);
		
		JButton pauseButton = new JButton("PAUSE");
		pauseButton.setName("pause");
		pauseButton.setForeground(Color.LIGHT_GRAY);
		pauseButton.setFont(new Font("BankGothic Md BT", Font.BOLD, 11));
		pauseButton.setVerticalTextPosition(SwingConstants.BOTTOM) ;
		pauseButton.setHorizontalTextPosition (SwingConstants.CENTER);	
		pauseButton.setOpaque(false);
		pauseButton.setContentAreaFilled(false);
		pauseButton.setBorderPainted(false);
		pauseButton.setIcon(new ImageIcon(this.getClass().getResource("/images/redbtn.png")));
		pauseButton.setBounds(390, 540, 80, 90);
		pauseButton.addActionListener(this);
		panel.add(pauseButton);		
		
		JButton homeButton = new JButton("HOME");
		homeButton.setName("home");
		homeButton.setForeground(Color.LIGHT_GRAY);
		homeButton.setFont(new Font("BankGothic Md BT", Font.BOLD, 11));
		homeButton.setVerticalTextPosition(SwingConstants.BOTTOM) ;
		homeButton.setHorizontalTextPosition (SwingConstants.CENTER);	
		homeButton.setOpaque(false);
		homeButton.setContentAreaFilled(false);
		homeButton.setBorderPainted(false);
		homeButton.setIcon(new ImageIcon(this.getClass().getResource("/images/bluebtn.png")));
		homeButton.setBounds(220, 690, 80, 50);
		homeButton.addActionListener(this);
		panel.add(homeButton);
		
		JButton helpButton = new JButton("HELP");
		helpButton.setName("help");
		helpButton.setForeground(Color.LIGHT_GRAY);
		helpButton.setFont(new Font("BankGothic Md BT", Font.BOLD, 11));
		helpButton.setVerticalTextPosition(SwingConstants.BOTTOM) ;
		helpButton.setHorizontalTextPosition (SwingConstants.CENTER);	
		helpButton.setOpaque(false);
		helpButton.setContentAreaFilled(false);
		helpButton.setBorderPainted(false);
		helpButton.setIcon(new ImageIcon(this.getClass().getResource("/images/bluebtn.png")));
		helpButton.setBounds(300, 690, 70, 50);
		helpButton.addActionListener(this);
		panel.add(helpButton);
		
		//Let's exercise Label	
		JLabel label1 = new JLabel("Let's exercise");
		label1.setForeground(Color.DARK_GRAY);
		label1.setFont(new Font("Pretendo", Font.PLAIN, 15));
		label1.setBounds(40, 467, 130, 20);
		panel.add(label1);
				
		JLabel label2 = new JLabel("YUK\u0E46YIK\u0E46");
		label2.setForeground(Color.DARK_GRAY);
		label2.setFont(new Font("Bai Jamjuree Medium", Font.PLAIN, 20));
		label2.setBounds(160, 460, 300, 25);
		panel.add(label2);			
		}	
	
	
	public void createGameDisplay() {
		gameDisplay = new JPanel();
		gameDisplay.setBackground(new Color(247, 247, 247));
		gameDisplay.setBounds(105, 70, 374, 380);
		gameDisplay.setLayout(null);
		panel.add(gameDisplay);	
		
		//Show character 		
		character = new JLabel("");
		character.setIcon(new ImageIcon(this.getClass().getResource(exerciseData.get(queue[number]).getFirstStepPath())));
		character.setBounds(65, 15, 245, 335);
		gameDisplay.add(character);
		
		nameLabel = new JLabel("");
		nameLabel.setIcon(new ImageIcon(this.getClass().getResource(exerciseData.get(queue[number]).getName())));
		nameLabel.setBounds(244, 0, 130, 40);
		gameDisplay.add(nameLabel);
		
		stopwatchLabel = new JLabel("");
		stopwatchLabel.setIcon(new ImageIcon(this.getClass().getResource("/images/time.png")));
		stopwatchLabel.setForeground(new Color(119, 136, 153));
		stopwatchLabel.setFont(new Font("BankGothic Md BT", Font.BOLD, 15));
		stopwatchLabel.setBounds(10, 10, 200, 20);
		gameDisplay.add(stopwatchLabel);
		
		lifeEnergyLabel = new JLabel("5");
		lifeEnergyLabel.setIcon(new ImageIcon(this.getClass().getResource("/images/heart.png")));
		lifeEnergyLabel.setForeground(new Color(119, 136, 153));
		lifeEnergyLabel.setFont(new Font("BankGothic Md BT", Font.BOLD, 15));
		lifeEnergyLabel.setBounds(10, 30, 100, 20);
		gameDisplay.add(lifeEnergyLabel);
		
		powerEnergyLabel = new JLabel("0");
		powerEnergyLabel.setIcon(new ImageIcon(this.getClass().getResource("/images/power.png")));
		powerEnergyLabel.setForeground(new Color(119, 136, 153));
		powerEnergyLabel.setFont(new Font("BankGothic Md BT", Font.BOLD, 15));
		powerEnergyLabel.setBounds(10, 50, 100, 20);
		gameDisplay.add(powerEnergyLabel);
		
		//Create stopwatch
		stopwatch = new Stopwatch(0);
		stopwatch.getStopwatch(stopwatchLabel);
		
		JLabel displaybackground = new JLabel("");
		displaybackground.setIcon(new ImageIcon(this.getClass().getResource("/images/displaybackground.gif")));
		displaybackground.setBounds( 0, 0, 374, 380);
		gameDisplay.add(displaybackground);
		
	}

	private void endGame() throws IOException {
		stopwatch.stopStopwatch(true);
		bonusCollect = stopwatch.getBonusCollect();
		if(lifeEnergy==0) {
			score = number;
		}else {
			score = bonusCollect + (lifeEnergy*100) + (number*10);
		}
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
		LocalDateTime now = LocalDateTime.now();  
		String date = dtf.format(now);
		String playerData = name+"\t"+score+"\t"+date+"\n";
		data.addPlayerData(playerData);
		JOptionPane.showMessageDialog(null,"Congratulations!!\nYOUR SCORE : "+score);		
		frame.dispose();
		new MainMenu();	
		}
		


	private void checkDirection(String button) {	
		if (button.equals(exerciseData.get(queue[number]).getFirstDirection()) && step==1 ) {
			character.setIcon(new ImageIcon(this.getClass().getResource(exerciseData.get(queue[number]).getSecondStepPath())));
			step=2;count++;
		}else if (button.equals(exerciseData.get(queue[number]).getSecondDirection()) && step==2 ) {
			character.setIcon(new ImageIcon(this.getClass().getResource(exerciseData.get(queue[number]).getFirstStepPath())));
			step=1;count++;				
		}else {	
			JOptionPane.showMessageDialog(null,"OOPS!");
			lifeEnergy--;
			if(lifeEnergy==0) {
				try {
					endGame();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else {
				lifeEnergyLabel.setText(Integer.toString(lifeEnergy));}
				}	
			
		if(count==10) {
			count=0;
			number++;
			powerEnergyLabel.setText(Integer.toString(number));
			if(number==queue.length) {
				character.setIcon(new ImageIcon(this.getClass().getResource("/images/end.png")));	
				try {		
						endGame();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}else {
					character.setIcon(new ImageIcon(this.getClass().getResource(exerciseData.get(queue[number]).getFirstStepPath())));
					nameLabel.setIcon(new ImageIcon(this.getClass().getResource(exerciseData.get(queue[number]).getName())));
					}		
				}
		}	
	
@Override
	public void actionPerformed(ActionEvent event) {	
		if(event.getSource() instanceof JButton) {
			String button = ((JButton)event.getSource()).getName();
			switch(button){
			case "help":
				MainMenu.createHowToPlay();
				break;
			
			case "home":
				int home = JOptionPane.showConfirmDialog(null, "Exit to Main Menu?", "HOME", JOptionPane.YES_NO_OPTION);
				if (home == JOptionPane.YES_OPTION) {
					stopwatch.stopStopwatch(true);
					frame.dispose();	
					new MainMenu();	
					}
				break;
			
			case "reset":
				int reset = JOptionPane.showConfirmDialog(null, "Are you sure you want to reset?", "RESET", JOptionPane.YES_NO_OPTION);
				if (reset == JOptionPane.YES_OPTION) {
					stopwatch.stopStopwatch(true);
					new GamePanel(name);	
					frame.dispose();				
					}
				break;
			
			case "pause":
				stopwatch.stopStopwatch(true);
				int pause = JOptionPane.showConfirmDialog(null, "Resume Game", "PAUSE", JOptionPane.YES_NO_OPTION);
				while (pause == JOptionPane.NO_OPTION) {
					pause = JOptionPane.showConfirmDialog(null, "Resume Game", "PAUSE", JOptionPane.YES_NO_OPTION);
					}
				if (pause == JOptionPane.YES_OPTION) {
					pause = stopwatch.getCount();
					stopwatch = new Stopwatch(pause);
					stopwatch.getStopwatch(stopwatchLabel);
					}
				break;	
			
			default:
				checkDirection(button);	
				break;
			}
		}
	}	
}