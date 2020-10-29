package spaceBattleArena;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserInterface   {

	/**JFrame for the Games UI*/
	private static JFrame frmGameUI;
	/**GamePanel a subclass of JPanel for the Game to be drawn on*/
	private static GamePanel game;
	/**JPanel used for the level Select Menu*/
	private JPanel levelSelect;
	/**JPanel used for the Menu Interface*/
	private JPanel menu;
	/**JPanel used for the highScore Interface*/
	private JPanel highScore;
	/**JPanel used for the manual interface*/
	private JPanel manual;
	/**JPanel used for the settings interface*/
	private JPanel settings;
	/**Buffered Image for the Background Image in the menus */
	private ImageIcon staryBG;
	/**Custom Object to create sound to be used*/
	private static Sound bgm;
	
	/**Creates the UserInterface when called*/
	public UserInterface(){
		
		frmGameUI = new JFrame();
		frmGameUI.setBounds(300, 40, 680, 560);
		frmGameUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		staryBG = new ImageIcon(UserInterface.class.getResource("/spaceBattleArena/SimpleStaryBackgroundUI.png"));
		JLabel lblBG = new JLabel();
		lblBG.setBounds(0, 0, 680, 560);
		lblBG.setIcon(staryBG);
		
		ManualUI();
		SettingsUI();
		LevelSelectUI();
		MenuUI();
		HighScoreUI();
		
		
		frmGameUI.setVisible(true);
	}
	/**Creates the Menu User Interface*/
	private void MenuUI(){
		
		menu = new JPanel();
		menu.setBounds(0, 0, 680, 560);
		
		ImageIcon title = new ImageIcon(UserInterface.class.getResource("/spaceBattleArena/SpaceArenaTitle.png"));
		JLabel titleLabel = new JLabel("");
		titleLabel.setIcon(title);
		titleLabel.setBounds(40, 50, 680, 100);
		menu.add(titleLabel);
		
		frmGameUI.getContentPane().add(menu);
		menu.setLayout(null);
		
		JButton btnPlayGame = new JButton("Play Game");
		btnPlayGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				levelSelect.setVisible(true);
				menu.setVisible(false);
			}
		});
		btnPlayGame.setBounds(200, 200, 280, 40);
		menu.add(btnPlayGame);
		
		JButton btnSettings = new JButton("Settings");
		btnSettings.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				settings.setVisible(true);
				menu.setVisible(false);
			}
		});
		btnSettings.setBounds(200, 251, 280, 40);
		menu.add(btnSettings);
		
		JButton btnManual = new JButton("Manual");
		btnManual.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				manual.setVisible(true);
				menu.setVisible(false);
			}
		});
		btnManual.setBounds(200, 353, 280, 40);
		menu.add(btnManual);
		
		JButton btnHighScores = new JButton("High Scores");
		btnHighScores.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				highScore.setVisible(true);
				menu.setVisible(false);
			}
		});
		btnHighScores.setBounds(200, 302, 280, 40);
		menu.add(btnHighScores);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(200, 404, 280, 40);
		menu.add(btnExit);	
		
		JLabel lblBG = new JLabel();
		lblBG.setBounds(0, 0, 680, 560);
		lblBG.setIcon(staryBG);
		menu.add(lblBG);
	}
	
	/**Create the User Interface for the Game */
	private void GameUI(int lvl){
		
		frmGameUI.setBounds(300, 40, 1100, 800);
		game = new GamePanel(lvl);
		game.setBounds(0, 0, 1100, 800);
		frmGameUI.getContentPane().add(game);
		game.setFocusable(true);
		
	
	}
	/**Creates the Level Select User Interface for the Game, then hides it*/
	private void LevelSelectUI(){
		levelSelect = new JPanel();
		levelSelect.setBounds(0, 0, 680, 560);
		frmGameUI.getContentPane().add(levelSelect);
		levelSelect.setLayout(null);
		
		JButton btnBack = new JButton();
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				menu.setVisible(true);
				levelSelect.setVisible(false);
			}
		});
		btnBack.setBounds(10,10,80,20);
		btnBack.setText("Back");
		
		JButton btnLevel1 = new JButton("Level 1                                 Difficulty: * ");
		btnLevel1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				GameUI(1);
				levelSelect.setVisible(false);
				gameMusic();
			}
		});
		btnLevel1.setBounds(140, 150, 400, 30);
		levelSelect.add(btnLevel1);
		
		JButton btnLevel2 = new JButton("Level 2                                 Difficulty: ** ");
		btnLevel2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GameUI(2);
				levelSelect.setVisible(false);
				gameMusic();
			}
		});
		btnLevel2.setBounds(140, 225, 400, 30);
		levelSelect.add(btnLevel2);
		
		JButton btnLevel3 = new JButton("Level 3                                 Difficulty: *** ");
		btnLevel3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GameUI(3);
				levelSelect.setVisible(false);
				gameMusic();
			}
		});
		btnLevel3.setBounds(140, 300, 400, 30);
		levelSelect.add(btnLevel3);
		
		
		JLabel lblBG = new JLabel();
		lblBG.setBounds(0, 0, 680, 560);
		lblBG.setIcon(staryBG);
		levelSelect.add(lblBG);
		levelSelect.add(btnBack);
		levelSelect.setVisible(false);
		
		
	}
	/**Creates the Settings User Interface for the Game, then hides it*/
	private void SettingsUI(){
		
		settings = new JPanel();
		settings.setBounds(0, 0, 680, 560);
		frmGameUI.getContentPane().add(settings);
		settings.setLayout(null);
		
		JButton btnBack = new JButton();
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				menu.setVisible(true);
				settings.setVisible(false);
			}
		});
		btnBack.setBounds(10,10,80,20);
		btnBack.setText("Back");
		
		JPanel pnlNoOfEnemies = new JPanel();
		pnlNoOfEnemies.setBounds(140, 200, 400, 30);
		pnlNoOfEnemies.setBackground(Color.darkGray);
		
		JPanel pnlToggleObst = new JPanel();
		pnlToggleObst.setBounds(140, 300, 400, 30);
		pnlToggleObst.setBackground(Color.darkGray);
		
		settings.add(pnlNoOfEnemies);
		settings.add(pnlToggleObst);
		
		
		JLabel lblNoOfEnemies = new JLabel("Number of Enemies: *Coming Soon*");
		lblNoOfEnemies.setBounds(35, 10, 300, 20);
		lblNoOfEnemies.setForeground(Color.white);
		
		JLabel lblToggleObst = new JLabel("Toggle Obstacles: *Coming Soon*");
		lblToggleObst.setBounds(35, 10, 300, 20);
		lblToggleObst.setForeground(Color.white);
		
		pnlNoOfEnemies.add(lblNoOfEnemies);
		pnlToggleObst.add(lblToggleObst);
		
		
		JLabel lblBG = new JLabel();
		lblBG.setBounds(0, 0, 680, 560);
		lblBG.setIcon(staryBG);
		settings.add(lblBG);
		settings.add(btnBack);
		settings.setVisible(false);
		
	}
	
	/**Creates the Manual User Interface for the Game, then hides it*/
	private void ManualUI(){
		manual = new JPanel();
		manual.setBounds(0, 0, 680, 560);
		frmGameUI.getContentPane().add(manual);
		manual.setLayout(null);
		
		JButton btnBack = new JButton();
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				menu.setVisible(true);
				manual.setVisible(false);
			}
		});
		btnBack.setBounds(10,10,80,20);
		btnBack.setText("Back");
		
		JPanel pnlMission = new JPanel();
		pnlMission.setBounds(90, 60, 500, 190);
		pnlMission.setBackground(Color.lightGray);
		manual.add(pnlMission);
		
		JPanel pnlControls = new JPanel();
		pnlControls.setBounds(90, 280, 500, 190);
		pnlControls.setBackground(Color.lightGray);
		manual.add(pnlControls);
		
		JLabel lblBG = new JLabel();
		lblBG.setBounds(0, 0, 680, 560);
		lblBG.setIcon(staryBG);
		
		manual.add(btnBack);
		manual.add(lblBG);
		manual.setVisible(false);
		
	}
	
	/**Creates the HighScore User Interface for the Game, then hides it*/
	private void HighScoreUI(){
		
		highScore = new JPanel();
		highScore.setBounds(0,0, 680, 560);
		frmGameUI.getContentPane().add(highScore);
		highScore.setLayout(null);
		
		JButton btnBack = new JButton();
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				menu.setVisible(true);
				highScore.setVisible(false);
			}
		});
		btnBack.setBounds(10,10,80,20);
		btnBack.setText("Back");
		
		JLabel msg = new JLabel("HighScores *Coming Soon*");
		msg.setBounds(240,200,200,30);
		msg.setForeground(Color.white);
		
		JLabel lblBG = new JLabel();
		lblBG.setBounds(0, 0, 680, 560);
		lblBG.setIcon(staryBG);
		
		highScore.add(msg);
		highScore.add(lblBG);
		highScore.add(btnBack);
		highScore.setVisible(false);
	}

	/**Run when the game level loads to play background music*/
	public void gameMusic(){
		
		bgm = new Sound();
		bgm.setToBattleMusic();
		bgm.loop();

		
	}
}
	


