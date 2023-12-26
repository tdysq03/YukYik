package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

public class MainMenu implements ActionListener{

	private JFrame frame,infoFrame,scoreFrame;
	private String name;
	private static ArrayList<String> howToPlay;
	private ArrayList<String> allPlayer;
	private static Data data;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu window = new MainMenu();
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
	public MainMenu() {
		data = new Data();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(63,76,90));
		frame.setBounds(100, 100, 584, 762);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setUndecorated(true);
		frame.setVisible(true);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/heart.png")));
		frame.setTitle("YUK\u0E46YIK\u0E46");
		
		JButton exitButton = new JButton("");
		exitButton.setName("exit");
		exitButton.setBackground(Color.LIGHT_GRAY);
		exitButton.setFont(new Font("BankGothic Md BT", Font.PLAIN, 9));
		exitButton.setBounds(544, 10, 30, 30);
		exitButton.setIcon(new ImageIcon(this.getClass().getResource("/images/exit.png")));
		exitButton.addActionListener(this);
		frame.getContentPane().add(exitButton);
						
		JButton startButton = new JButton("START");
		startButton.setName("start");
		startButton.setForeground(new Color(119, 136, 153));
		startButton.setBackground(new Color(220, 220, 220));
		startButton.setFont(new Font("BankGothic Md BT", Font.BOLD, 20));
		startButton.setBounds(183, 531, 228, 44);
		startButton.addActionListener(this);
		frame.getContentPane().add(startButton);
		
		JButton scoreButton = new JButton("SCORE");
		scoreButton.setName("score");
		scoreButton.setForeground(new Color(119, 136, 153));
		scoreButton.setBackground(new Color(220, 220, 220));
		scoreButton.setFont(new Font("BankGothic Md BT", Font.BOLD, 20));
		scoreButton.setBounds(183, 586, 228, 44);
		scoreButton.addActionListener(this);
		frame.getContentPane().add(scoreButton);
		
		JButton helpButton = new JButton("HELP");
		helpButton.setName("help");
		helpButton.setForeground(new Color(119, 136, 153));
		helpButton.setBackground(new Color(220, 220, 220));	
		helpButton.setFont(new Font("BankGothic Md BT", Font.BOLD, 20));
		helpButton.setBounds(183, 641, 228, 44);
		helpButton.addActionListener(this);
		frame.getContentPane().add(helpButton);
		
		JLabel background = new JLabel("");
		background.setBounds(0, 0, 584, 480);
		background.setIcon(new ImageIcon(this.getClass().getResource("/images/mainmenubackground.gif")));
		frame.getContentPane().add(background);
				
		UIManager.put("OptionPane.background", Color.white);
		UIManager.put("Panel.background", Color.white);
		UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 14));
		UIManager.put("OptionPane.buttonFont", new Font("Arial", Font.PLAIN, 12));
	}
	
	public void createExerciseInformation() {
		infoFrame = new JFrame();
		infoFrame.setBounds(100, 100, 620, 800);
		infoFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/heart.png")));
		infoFrame.setTitle("YUK\u0E46YIK\u0E46");
		infoFrame.setLocationRelativeTo(null);
		infoFrame.setResizable(false);
		infoFrame.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBackground(new Color(0,0,0));
		panel.setLayout(null);
		infoFrame.getContentPane().add(panel, BorderLayout.CENTER);
		
		JButton letsPlayGameButton = new JButton("LET'S PLAY GAME!!");
		letsPlayGameButton.setName("play");
		letsPlayGameButton.setForeground(new Color(255, 255, 255));
		letsPlayGameButton.setBackground(new Color(163, 184, 204));	
		letsPlayGameButton.setFont(new Font("BankGothic Md BT", Font.BOLD, 20));
		letsPlayGameButton.setBounds(151, 696, 297, 48);
		letsPlayGameButton.addActionListener(this);
		panel.add(letsPlayGameButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0,605,680 );
		panel.add(scrollPane);
		
		JLabel exerciseInformation = new JLabel("");
		exerciseInformation.setIcon(new ImageIcon(this.getClass().getResource("/images/aboutgame.gif")));
		scrollPane.setViewportView(exerciseInformation);
		
	}
	
	public static void createHowToPlay() {
		JFrame howtoframe = new JFrame();
		howtoframe.getContentPane().setBackground(new Color(63,76,90));
		howtoframe.setBounds(100, 100, 620, 800);
		howtoframe.setIconImage(Toolkit.getDefaultToolkit().getImage(MainMenu.class.getResource("/images/heart.png")));
		howtoframe.setTitle("YUK\u0E46YIK\u0E46");
		howtoframe.getContentPane().setLayout(null);
		howtoframe.setLocationRelativeTo(null);
		howtoframe.setVisible(true);
		howtoframe.setResizable(false);
				
		try {
			howToPlay = data.getHowtoplay();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String allText="";
		for(int i =1;i<howToPlay.size();i++) {
			allText += howToPlay.get(i)+"<br/>";
			}
		
		JLabel headLabel = new JLabel("");
		headLabel.setFont(new Font("Bai Jamjuree SemiBold", Font.PLAIN, 28));
		headLabel.setForeground(Color.WHITE);
		headLabel.setText(howToPlay.get(0));
		headLabel.setBounds(45, 20, 520, 40);
		howtoframe.getContentPane().add(headLabel);
		
		JLabel howToPlay = new JLabel("");
		howToPlay.setVerticalAlignment(SwingConstants.TOP);
		howToPlay.setFont(new Font("Bai Jamjuree Medium", Font.PLAIN, 14));
		howToPlay.setForeground(Color.WHITE);
		howToPlay.setBounds(45, 62, 520, 256);
		howToPlay.setText("<html>"+allText+"</html>");
		howtoframe.add(howToPlay);
		
		JLabel pic = new JLabel("");
		pic.setIcon(new ImageIcon(MainMenu.class.getResource("/images/howto.gif")));
		pic.setBounds(130, 270, 350, 450);
		howtoframe.getContentPane().add(pic);
		
	}
	
	public void createScoreBoard() {
		scoreFrame = new JFrame();
		scoreFrame.getContentPane().setBackground(new Color(63,76,90)); 
		scoreFrame.setBounds(100, 100, 620, 800);
		scoreFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(MainMenu.class.getResource("/images/heart.png")));
		scoreFrame.setTitle("YUK\u0E46YIK\u0E46");
		scoreFrame.setLocationRelativeTo(null);
		scoreFrame.setResizable(false);
		scoreFrame.getContentPane().setLayout(null);
		scoreFrame.setVisible(true);
		
		JLabel headLabel1 = new JLabel("");
		headLabel1.setIcon(new ImageIcon(this.getClass().getResource("/images/score.png")));
		headLabel1.setBackground(Color.WHITE);
		headLabel1.setForeground(Color.WHITE);
		headLabel1.setFont(new Font("Bai Jamjuree SemiBold", Font.PLAIN, 28));
		headLabel1.setBounds(0, 21, 604, 60);
	    scoreFrame.getContentPane().add(headLabel1);
	    
		JLabel headLabel2 = new JLabel("New Highest Score");
		headLabel2.setBackground(Color.WHITE);
		headLabel2.setForeground(Color.LIGHT_GRAY);
		headLabel2.setFont(new Font("Bai Jamjuree SemiBold", Font.PLAIN, 20));
		headLabel2.setBounds(10, 92, 300, 25);
	    scoreFrame.getContentPane().add(headLabel2);
	    
	    JLabel maxPlayer = new JLabel("");
	    maxPlayer.setForeground(Color.WHITE);
	    maxPlayer.setFont(new Font("Bai Jamjuree SemiBold", Font.PLAIN, 18));
		maxPlayer.setBounds(47, 120, 524, 90);
		scoreFrame.getContentPane().add(maxPlayer);
		
		JLabel headLabel3 = new JLabel("All Player Score");
		headLabel3.setForeground(Color.LIGHT_GRAY);
		headLabel3.setFont(new Font("Bai Jamjuree SemiBold", Font.PLAIN, 20));
		headLabel3.setBounds(10, 219, 300, 25);
	    scoreFrame.getContentPane().add(headLabel3);
	    
	    JScrollPane scrollPane = new JScrollPane();
	    scrollPane.setBackground(new Color(63, 76, 90));
	    scrollPane.setBounds(0, 255, 604, 506);
	    scoreFrame.getContentPane().add(scrollPane);
	    
	    Object columnNames[] = { "Name","Score","Date" };
		Object rowData[][] ={};
	   
	    JTable allTable = new JTable(new DefaultTableModel(rowData, columnNames));
		allTable.setForeground(Color.WHITE);
		allTable.setBackground(new Color(40,48,58));
		scrollPane.setViewportView(allTable);
	
	    DefaultTableModel model1 = (DefaultTableModel)allTable.getModel();
	    		
		String[] arrayOfStr;
		int max = 0;
		String[] maxData= {} ;
		try {				
			allPlayer = data.getPlayerData();
		} catch (IOException e) {
			e.printStackTrace();
			}
		for(int i =0;i<allPlayer.size();i++) {
		arrayOfStr = allPlayer.get(i).split("\t");
		model1.addRow(arrayOfStr);
		if(Integer.parseInt(arrayOfStr[1])>max) {
			max = Integer.parseInt(arrayOfStr[1]);
			maxData = arrayOfStr;
			}
		}
		
		String text = "Player: "+maxData[0]+"<br/>"+"Score: "+maxData[1]+"<br/>"+"Date: "+maxData[2];
		maxPlayer.setText("<html>"+text+"</html>");
		
	}
 
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() instanceof JButton) {
			String button = ((JButton)event.getSource()).getName();
			switch (button) {
				case "start":
					createExerciseInformation();
					break;
					
				case "play":
					name = JOptionPane.showInputDialog(null,"ENTER YOUR NAME");
					if(name==null) {
						JOptionPane.showMessageDialog(null, "Come play with us!");
					}else if (name.isEmpty()){
						JOptionPane.showMessageDialog(null, "You left the text field blank!");
					}else{
						new GamePanel(name);					
						infoFrame.dispose();
						frame.dispose();
						}			
					break;
				
				case "score": 	
					createScoreBoard();
					break;
					
				case "help": 
					createHowToPlay();
					break;			
				
				case "exit": 		
					frame.dispose();
					break;
			}	
		}
	}
}