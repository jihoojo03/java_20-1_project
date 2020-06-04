package TheMafia;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUITest extends JFrame {

	private Image screenImage;
	private Graphics screenGraphic;

	private Image gameBackground = new ImageIcon(Main.class.getResource("../images/hotelRoom.png")).getImage();
	private Image background = new ImageIcon(Main.class.getResource("../images/introbackground.jpg")).getImage();
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.jpg")));

	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/exitButton.jpg"));
	private ImageIcon startButtonBasicImage = new ImageIcon(Main.class.getResource("../images/startButtonBasic.jpg"));
	private ImageIcon startButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/startButtonEntered.jpg"));

	private JButton exitButton = new JButton(exitButtonEnteredImage);
	private JButton startButton = new JButton(startButtonBasicImage);

	private JLabel textBox = new JLabel();
	private JLabel nameBox = new JLabel();
	
	private Music backgroundMusic = new Music("01_introMusic.mp3", true);
	
	private Game game = new Game();
	
	private int mouseX, mouseY;
	
	private boolean isGameScreen = false;
	
	private String currentPart;
	private int currentId;
	private int currentTotalNum;
	private int currentMusic;

	GUITest() {
		createMenu();
		setUndecorated(true);
		setTitle("The Mafia");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);
		
		musicStart();
		makeExitButton();
		makeMenuBar();
		makeStartButton();
		
	}

	public void createMenu() {
		JMenuBar mb = new JMenuBar();
		JMenu screenMenu = new JMenu("스토리");

		screenMenu.add(new JMenuItem("진행상황"));
		screenMenu.add(new JMenuItem("분기점 선택"));
		screenMenu.add(new JMenuItem("등장인물"));
		screenMenu.addSeparator();
		screenMenu.add(new JMenuItem("Exit"));

		mb.add(screenMenu);
		mb.add(new JMenu("편집"));
		mb.add(new JMenu("실행"));
		mb.add(new JMenu("도움말"));
		setJMenuBar(mb);
	}
	
	public void makeExitButton() {
		exitButton.setBounds(430, 00, 50, 30);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				try {
					Thread.sleep(500);
				} catch(InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}

		});
		add(exitButton);
	}
	
	public void makeMenuBar() {
		menuBar.setBounds(0, 0, 480, 30);
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY - 30);
			}
		});
		add(menuBar);
	}

	public void makeStartButton() {
		startButton.setBounds(120, 500, 240, 80);
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				startButton.setIcon(startButtonEnteredImage);
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startButtonBasicImage);
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				startButton.setVisible(false);
				musicEnd();
				isGameScreen = true;
				
				currentPart = "1";
				game.readCsv(currentPart);
				currentId = game.getFirstId();
				currentTotalNum = game.getTotalNum();
				System.out.println(currentId);
				System.out.println(currentTotalNum);
				
				showName();
				showText();

			}

		});
		add(startButton);

	}
	
	public void showName(){
		int userNum = game.getCharacter(currentId);
		if(userNum == 1) nameBox.setText("Kyle");
		else if(userNum == 2) nameBox.setText("Rachel");
		else if(userNum == 3) nameBox.setText("Brad");
		else if(userNum == 4) nameBox.setText("Erica");
		else if(userNum == 5) nameBox.setText("Alex");
		else if(userNum == 6) nameBox.setText("Neal");
		
		nameBox.setBounds(30, 470, 80, 30);
		nameBox.setFont(new Font("Serif", Font.BOLD, 25));
		nameBox.setForeground(Color.WHITE);
		add(nameBox);
	}
	
	public void showText() {
		String text = game.getScript(currentId);
		textBox.setText(text);
		textBox.setBounds(30, 540, 480, 180);
		textBox.setFont(new Font("Serif", Font.PLAIN, 30));
		textBox.setVerticalAlignment(SwingConstants.TOP);
		textBox.setHorizontalAlignment(SwingConstants.LEFT);
		textBox.setForeground(Color.WHITE);
		textBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				textBox.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				textBox.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if((currentId % 1000) < currentTotalNum) currentId = game.getNextId(currentId);
				
				textBox.setText(game.getScript(currentId));
				setGraphic(game.getPlace(currentId));
				musicListener(game.getBGM(currentId));
				showName();
			}

		});
		add(textBox);
	}
	
	public void setGraphic(int num) {
		if(num == 1) gameBackground = new ImageIcon(Main.class.getResource("../images/hotelRoom.png")).getImage();
		else if(num == 2) gameBackground = new ImageIcon(Main.class.getResource("../images/lobby.png")).getImage();
		
	}
	
	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw(screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	}

	public void screenDraw(Graphics g) {
		g.drawImage(background, 0, 30, null);
		if(isGameScreen) {
			g.drawImage(gameBackground, 0, 30, null);
		}
		paintComponents(g);
		this.repaint();
	}
	
	
	public void musicStart() {
		backgroundMusic.start();
	}
	
	public void musicEnd() {
		backgroundMusic.close();
	}
	
	public void musicListener(int num) {
		if(currentMusic != num) {
			if(num == 0) musicEnd();
			if(num == 1) {
				musicEnd();
				backgroundMusic = new Music("01_introMusic.mp3", true);
				musicStart();
			}
			else if(num == 2) {
				musicEnd();
				backgroundMusic = new Music("02_search.mp3", true);
				musicStart();
			}
			else if(num == 3) {
				musicEnd();
				backgroundMusic = new Music("03_dark.mp3", true);
				musicStart();
			}
			else if(num == 4) {
				musicEnd();
				backgroundMusic = new Music("04_danger.mp3", true);
				musicStart();
			}
			else if(num == 5) {
				musicEnd();
				backgroundMusic = new Music("05_urgency.mp3", true);
				musicStart();
			}
			else if(num == 6) {
				musicEnd();
				backgroundMusic = new Music("06_conflict.mp3", true);
				musicStart();
			}
			else if(num == 7) {
				musicEnd();
				backgroundMusic = new Music("07_expose.mp3", true);
				musicStart();
			}
			else if(num == 8) {
				musicEnd();
				backgroundMusic = new Music("08_nothingness.mp3", true);
				musicStart();
			}
			else if(num == 9) {
				musicEnd();
				backgroundMusic = new Music("09_theEnd.mp3", true);
				musicStart();
			}
			else if(num == 10) {
				musicEnd();
				backgroundMusic = new Music("10_scary.mp3", true);
				musicStart();
			}
			
		}
		currentMusic = num;
	}

}
