package TheMafia;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUITest extends JFrame {

	private Image screenImage;
	private Graphics screenGraphic;

	private Image gameBackground = new ImageIcon(Main.class.getResource("../images/hotelRoom.png")).getImage();
	private Image background = new ImageIcon(Main.class.getResource("../images/introbackground.jpg")).getImage();
	private Image person = new ImageIcon(Main.class.getResource("../images/Neal.png")).getImage();
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.jpg")));

	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/exitButton.jpg"));
	private ImageIcon startButtonBasicImage = new ImageIcon(Main.class.getResource("../images/startButtonBasic.jpg"));
	private ImageIcon startButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/startButtonEntered.jpg"));
	private ImageIcon choiceButtonBasicImage = new ImageIcon(Main.class.getResource("../images/choiceButton.png"));
	private ImageIcon choiceButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/choiceButtonSelected.png"));
	
	private JButton exitButton = new JButton(exitButtonEnteredImage);
	private JButton startButton = new JButton(startButtonBasicImage);
	private JButton choiceButton01 = new JButton(choiceButtonBasicImage);
	private JButton choiceButton02 = new JButton(choiceButtonBasicImage);
	private JButton choiceButton03 = new JButton(choiceButtonBasicImage);
	
	private JLabel textBox = new JLabel();
	private JLabel nameBox = new JLabel();
	private JLabel choiceBox01 = new JLabel();
	private JLabel choiceBox02 = new JLabel();
	private JLabel choiceBox03 = new JLabel();
	
	private Music backgroundMusic = new Music("01_introMusic.mp3", true);
	
	private Game game = new Game();
	
	private int mouseX, mouseY;
	
	private boolean isGameScreen = false;
	private boolean retry = false;
	
	private FileIO file = new FileIO();
	
	private String currentPart;
	private int currentId;
	private int currentTotalNum;
	private int currentMusic;
	private int currentPerson;
	private int lastChoiceId;
	private int lastChoice = 0;
	
	private Selection selection = new Selection();

	GUITest() {
		setUndecorated(true);
		setTitle("The Mafia");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);
		
		currentPart = "1";
		game.readCsv(currentPart);
		currentId = game.getFirstId();
		currentTotalNum = game.getTotalNum();
		
		createMenu();
		musicStart();
		makeExitButton();
		makeMenuBar();
		makeStartButton();
		
	}

	public void createMenu() {
		JMenuBar mb = new JMenuBar();
		JMenu screenMenu01 = new JMenu("Story");
		JMenu screenMenu02 = new JMenu("Save & Load");
		JMenu screenMenu03 = new JMenu("Help");

		JMenuItem item01 = new JMenuItem("Last Choice");
		JMenuItem item02 = new JMenuItem("Characters");
		JMenuItem item03 = new JMenuItem("Save");
		JMenuItem item04 = new JMenuItem("Load");
		JMenuItem item05 = new JMenuItem("Help");
		JMenuItem item06 = new JMenuItem("Exit");
		
		item01.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(lastChoiceId == 90001 && lastChoice == 1) JOptionPane.showMessageDialog(null, "<html><body>[분기점 선택] <br> 방에 다시 되돌아 오기를 선택했다.</body></html>");
				else if(lastChoiceId == 90001 && lastChoice == 2) JOptionPane.showMessageDialog(null, "<html><body>[분기점 선택] <br> 로비로 내려가서 사람들을 만나기로 했다.</body></html>");
				else if(lastChoiceId == 90002 && lastChoice == 1) JOptionPane.showMessageDialog(null, "<html><body>[분기점 선택] <br> 창문 밖으로 뛰어내리기로 선택했다.</body></html>");
				else if(lastChoiceId == 90002 && lastChoice == 2) JOptionPane.showMessageDialog(null, "<html><body>[분기점 선택] <br> 로비로 내려가서 사람들을 만나기로 했다.</body></html>");
				else if(lastChoiceId == 90003 && lastChoice == 1) JOptionPane.showMessageDialog(null, "<html><body>[분기점 선택] <br> 범인은 이 안에 있다고 이야기했다.</body></html>");
				else if(lastChoiceId == 90003 && lastChoice == 2) JOptionPane.showMessageDialog(null, "<html><body>[분기점 선택] <br> 범인은 외부인 이라고 이야기했다.</body></html>");
				else if(lastChoiceId == 90003 && lastChoice == 3) JOptionPane.showMessageDialog(null, "<html><body>[분기점 선택] <br> 범인은 귀신 이라고 이야기했다.</body></html>");
				else if(lastChoiceId == 90004 && lastChoice == 1) JOptionPane.showMessageDialog(null, "<html><body>[분기점 선택] <br> 설비실로 가야 한다고 이야기했다.</body></html>");
				else if(lastChoiceId == 90004 && lastChoice == 2) JOptionPane.showMessageDialog(null, "<html><body>[분기점 선택] <br> 여기서 기다려야 한다고 이야기했다.</body></html>");
				else if(lastChoiceId == 90005 && lastChoice == 1) JOptionPane.showMessageDialog(null, "<html><body>[분기점 선택] <br> 와인을 마시기로 했다.</body></html>");
				else if(lastChoiceId == 90005 && lastChoice == 2) JOptionPane.showMessageDialog(null, "<html><body>[분기점 선택] <br> 와인을 마시지 않기로 했다.</body></html>");
				else if(lastChoiceId == 90006 && lastChoice == 1) JOptionPane.showMessageDialog(null, "<html><body>[분기점 선택] <br> 와인을 마시지 말라고 이야기했다.</body></html>");
				else if(lastChoiceId == 90006 && lastChoice == 2) JOptionPane.showMessageDialog(null, "<html><body>[분기점 선택] <br> 가만히 있기로 했다.</body></html>");
				else if(lastChoiceId == 90007 && lastChoice == 1) JOptionPane.showMessageDialog(null, "<html><body>[분기점 선택] <br> 범인은 Brad라고 이야기했다.</body></html>");
				else if(lastChoiceId == 90007 && lastChoice == 2) JOptionPane.showMessageDialog(null, "<html><body>[분기점 선택] <br> 범인은 Erica라고 이야기했다.</body></html>");
				else if(lastChoiceId == 90007 && lastChoice == 3) JOptionPane.showMessageDialog(null, "<html><body>[분기점 선택] <br> 범인은 나라고 이야기했다.</body></html>");
				else if(lastChoiceId == 90008 && lastChoice == 1) JOptionPane.showMessageDialog(null, "<html><body>[분기점 선택] <br> 범인은 남자라고 이야기했다.</body></html>");
				else if(lastChoiceId == 90008 && lastChoice == 2) JOptionPane.showMessageDialog(null, "<html><body>[분기점 선택] <br> 범인은 여자라고 이야기했다.</body></html>");
				else if(lastChoiceId == 90008 && lastChoice == 3) JOptionPane.showMessageDialog(null, "<html><body>[분기점 선택] <br> 범인은 둘 다 인것 같다고 이야기했다.</body></html>");
				else if(lastChoiceId == 90009 && lastChoice == 1) JOptionPane.showMessageDialog(null, "<html><body>[분기점 선택] <br> 범인은 2명인 것 같다고 이야기했다.</body></html>");
				else if(lastChoiceId == 90009 && lastChoice == 2) JOptionPane.showMessageDialog(null, "<html><body>[분기점 선택] <br> 범인은 3명인 것 같다고 이야기했다.</body></html>");
				else if(lastChoiceId == 90009 && lastChoice == 3) JOptionPane.showMessageDialog(null, "<html><body>[분기점 선택] <br> 범인은 4명인 것 같다고 이야기했다.</body></html>");
				else if(lastChoiceId == 90010 && lastChoice == 1) JOptionPane.showMessageDialog(null, "<html><body>[분기점 선택] <br> 범인 중 한명이 Neal인 것 같다.</body></html>");
				else if(lastChoiceId == 90010 && lastChoice == 2) JOptionPane.showMessageDialog(null, "<html><body>[분기점 선택] <br> 범인 중 한명이 Brad인 것 같다.</body></html>");
				else if(lastChoiceId == 90010 && lastChoice == 3) JOptionPane.showMessageDialog(null, "<html><body>[분기점 선택] <br> 범인 중 한명이 Alex인 것 같다.</body></html>");
				else if(lastChoiceId == 90011 && lastChoice == 1) JOptionPane.showMessageDialog(null, "<html><body>[분기점 선택] <br> 범인 중 한명이 Rachael인 것 같다.</body></html>");
				else if(lastChoiceId == 90011 && lastChoice == 2) JOptionPane.showMessageDialog(null, "<html><body>[분기점 선택] <br> 범인 중 한명이 Erica인 것 같다.</body></html>");
				else if(lastChoiceId == 90011 && lastChoice == 3) JOptionPane.showMessageDialog(null, "<html><body>[분기점 선택] <br> 범인 중 한명이 제 3의 인물인 것 같다.</body></html>");
				else JOptionPane.showMessageDialog(null, "Either the diverging point did not appear, or it was a new start.");
			}

		});
		
		item02.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(currentId < 10091) JOptionPane.showMessageDialog(null, "<html><body> 1. Kyle <br> 2. ? ? ? <br> 3. ? ? ? <br> 4. ? ? ? <br> 5. ? ? ? <br> 6. ? ? ? </body></html>");
				else if(currentId < 10094) JOptionPane.showMessageDialog(null, "<html><body> 1. Kyle <br> 2. Rachael <br> 3. ? ? ? <br> 4. ? ? ? <br> 5. ? ? ? <br> 6. ? ? ? </body></html>");
				else if(currentId < 10095) JOptionPane.showMessageDialog(null, "<html><body> 1. Kyle <br> 2. Rachael <br> 3. Brad <br> 4. ? ? ? <br> 5. ? ? ? <br> 6. ? ? ? </body></html>");
				else if(currentId < 10098) JOptionPane.showMessageDialog(null, "<html><body> 1. Kyle <br> 2. Rachael <br> 3. Brad <br> 4. Erica <br> 5. ? ? ? <br> 6. ? ? ? </body></html>");
				else if(currentId < 10118) JOptionPane.showMessageDialog(null, "<html><body> 1. Kyle <br> 2. Rachael <br> 3. Brad <br> 4. Erica <br> 5. Alex <br> 6. ? ? ? </body></html>");
				else if(currentId < 10800) JOptionPane.showMessageDialog(null, "<html><body> 1. Kyle <br> 2. Rachael <br> 3. Brad <br> 4. Erica <br> 5. Alex <br> 6. Neal </body></html>");
				if(currentId == 90001) JOptionPane.showMessageDialog(null, "<html><body> 1. Kyle <br> 2. ? ? ? <br> 3. ? ? ? <br> 4. ? ? ? <br> 5. ? ? ? <br> 6. ? ? ? </body></html>");
				else if(currentId == 90002) JOptionPane.showMessageDialog(null, "<html><body> 1. Kyle <br> 2. ? ? ? <br> 3. ? ? ? <br> 4. ? ? ? <br> 5. ? ? ? <br> 6. ? ? ? </body></html>");
				else if(currentId >= 90003) JOptionPane.showMessageDialog(null, "<html><body> 1. Kyle <br> 2. Rachael <br> 3. Brad <br> 4. Erica <br> 5. Alex <br> 6. Neal </body></html>");
			}

		});
		
		item03.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				String[] str = new String[1];
				int[] id = new int[1];
				
				str[0] = game.getScript(currentId);
				id[0] = currentId;
				
				file.save_part(currentPart);
				file.save(str, id);
				
				JOptionPane.showMessageDialog(null, "<html><body> 저장이 완료되었습니다. </body></html>");
			}

		});
		
		item04.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				currentPart = file.getpart();
				currentId = file.load();
			}
		});
		
		item05.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "<html><body> 1. 이 게임은 분기점 선택 스토리 게임입니다. <br> 2. 당신의 선택이 미래를 결과를 바꿉니다. <br> 3. 저장과 불러오기를 잘 활용하세요.</body></html>");
			}

		});
		
		item06.addMouseListener(new MouseAdapter() {
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
		
		screenMenu01.add(item01);
		screenMenu01.add(item02);
		screenMenu02.add(item03);
		screenMenu02.add(item04);
		screenMenu03.add(item05);
		screenMenu03.addSeparator();
		screenMenu03.add(item06);

		mb.add(screenMenu01);
		mb.add(screenMenu02);
		mb.add(screenMenu03);

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
				if (retry == true) currentId = lastChoiceId;
				
				showName();
				showText();

			}

		});
		add(startButton);

	}
	
	public void showName(){
		int userNum = game.getCharacter(currentId);
		if(userNum == 1) nameBox.setText(" ");
		else if(userNum == 2 || userNum == 22) nameBox.setText("Kyle");
		else if(userNum == 3 || userNum == 23) nameBox.setText("Rachael");
		else if(userNum == 4) nameBox.setText("Brad");
		else if(userNum == 7) nameBox.setText("Neal");
		else if(userNum == 8) nameBox.setText("Alex");
		else if(userNum == 9) nameBox.setText("Erica");
		else if(userNum == 13 || userNum == 14 || userNum == 17 || userNum == 18 || userNum == 19 || userNum == 6) nameBox.setText("? ? ?");
		else nameBox.setText(" ");
		
		nameBox.setBounds(30, 470, 120, 30);
		nameBox.setFont(new Font("Serif", Font.BOLD, 25));
		nameBox.setForeground(Color.WHITE);
		add(nameBox);
	}
	
	public void showText() {
		String text = game.getScript(currentId);
		textBox.setText(text);
		textBox.setBounds(25, 530, 440, 180);
		textBox.setFont(new Font("Serif", Font.PLAIN, 26));
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
				if(currentId == 10302) currentId = 10303;
				else if((currentId / 10000) == 1) {		// Normal Script
					currentId = game.getNextId(currentId);
				}
				else if ((currentId / 10000) == 7) {	// Diverging point to Part4, Part5
					if(currentId % 70000 == 1) currentPart = "4";
					else if(currentId % 70000 == 2) currentPart = "5";
					game.readCsv(currentPart);
					currentId = game.getFirstId();
					currentTotalNum = game.getTotalNum();
				}
				else if ((currentId / 10000) == 8) {	// Ending
					retry = true;
					musicListener(1);
					textBox.setText(" ");
				}
				else if ((currentId / 10000) == 9) {	// Diverging point (Choice point)
					lastChoiceId = currentId;
					choiceButton01.setVisible(true);
					choiceButton02.setVisible(true);
					if(selection.getSelectionAmount(currentId) == 3)
						choiceButton03.setVisible(true);
					showButtonText();
					makeChoiceButton();
				}
				else if ((currentId % 10000) == 0) {
					if(currentPart.equals("1")) currentPart = "2";
					else if(currentPart.equals("2")) currentPart = "3";
					game.readCsv(currentPart);
					currentId = game.getFirstId();
					currentTotalNum = game.getTotalNum();
				}
				
				currentPerson = game.getCharacter(currentId);
				textBox.setText(game.getScript(currentId));
				setGraphic(game.getPlace(currentId));
				musicListener(game.getBGM(currentId));
				showName();
			}

		});
		add(textBox);
	}
	
	public void makeChoiceButton() {
		choiceButton01.setBounds(35, 120, 410, 80);
		choiceButton01.setBorderPainted(false);
		choiceButton01.setContentAreaFilled(false);
		choiceButton01.setFocusPainted(false);
		choiceButton01.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				choiceButton01.setIcon(choiceButtonEnteredImage);
				choiceButton01.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				choiceButton01.setIcon(choiceButtonBasicImage);
				choiceButton01.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				currentId = selection.getNextId(currentId, 1);
				lastChoice = 1;
				choiceButton01.setVisible(false);
				choiceButton02.setVisible(false);
				choiceButton03.setVisible(false);
				choiceBox01.setText(" ");
				choiceBox02.setText(" ");
				choiceBox03.setText(" ");
				currentPerson = game.getCharacter(currentId);
				textBox.setText(game.getScript(currentId));
				setGraphic(game.getPlace(currentId));
				musicListener(game.getBGM(currentId));
				showName();
				
			}

		});
		add(choiceButton01);
		
		choiceButton02.setBounds(35, 240, 410, 80);
		choiceButton02.setBorderPainted(false);
		choiceButton02.setContentAreaFilled(false);
		choiceButton02.setFocusPainted(false);
		choiceButton02.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				choiceButton02.setIcon(choiceButtonEnteredImage);
				choiceButton02.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				choiceButton02.setIcon(choiceButtonBasicImage);
				choiceButton02.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				currentId = selection.getNextId(currentId, 2);
				lastChoice = 2;
				choiceButton01.setVisible(false);
				choiceButton02.setVisible(false);
				choiceButton03.setVisible(false);
				choiceBox01.setText(" ");
				choiceBox02.setText(" ");
				choiceBox03.setText(" ");
				currentPerson = game.getCharacter(currentId);
				textBox.setText(game.getScript(currentId));
				setGraphic(game.getPlace(currentId));
				musicListener(game.getBGM(currentId));
				showName();
			}

		});
		add(choiceButton02);
		
		if(selection.getSelectionAmount(currentId) == 3) {
			choiceButton03.setBounds(35, 360, 410, 80);
			choiceButton03.setBorderPainted(false);
			choiceButton03.setContentAreaFilled(false);
			choiceButton03.setFocusPainted(false);
			choiceButton03.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					choiceButton03.setIcon(choiceButtonEnteredImage);
					choiceButton03.setCursor(new Cursor(Cursor.HAND_CURSOR));
				}
	
				@Override
				public void mouseExited(MouseEvent e) {
					choiceButton03.setIcon(choiceButtonBasicImage);
					choiceButton03.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
	
				@Override
				public void mousePressed(MouseEvent e) {
					currentId = selection.getNextId(currentId, 3);
					lastChoice = 3;
					choiceButton01.setVisible(false);
					choiceButton02.setVisible(false);
					choiceButton03.setVisible(false);
					choiceBox01.setText(" ");
					choiceBox02.setText(" ");
					choiceBox03.setText(" ");
					currentPerson = game.getCharacter(currentId);
					textBox.setText(game.getScript(currentId));
					setGraphic(game.getPlace(currentId));
					musicListener(game.getBGM(currentId));
					showName();
				}
	
			});
			add(choiceButton03);
		}
	}
	
	public void setGraphic(int num) {
		if(num == 1) gameBackground = new ImageIcon(Main.class.getResource("../images/dark.png")).getImage();
		else if(num == 11) gameBackground = new ImageIcon(Main.class.getResource("../images/hotelRoom.png")).getImage();
		else if(num == 12) gameBackground = new ImageIcon(Main.class.getResource("../images/corridor.png")).getImage();
		else if(num == 13) gameBackground = new ImageIcon(Main.class.getResource("../images/hotelLobby.png")).getImage();
		else if(num == 14) gameBackground = new ImageIcon(Main.class.getResource("../images/facilityRoom.png")).getImage();
		else if(num == 15) gameBackground = new ImageIcon(Main.class.getResource("../images/cafeteria.png")).getImage();
		else if(num == 16) gameBackground = new ImageIcon(Main.class.getResource("../images/hotelOffice.png")).getImage();
		else if(num == 17) gameBackground = new ImageIcon(Main.class.getResource("../images/prison.png")).getImage();
		else if(num == 18) gameBackground = new ImageIcon(Main.class.getResource("../images/outside.png")).getImage();
		else if(num == 23) gameBackground = new ImageIcon(Main.class.getResource("../images/hotelLobbyDark.png")).getImage();
		else if(num == 25) gameBackground = new ImageIcon(Main.class.getResource("../images/cafeteriaDark.png")).getImage();
		else if(num == 26) gameBackground = new ImageIcon(Main.class.getResource("../images/facilityRoomDark.png")).getImage();
		else gameBackground = new ImageIcon(Main.class.getResource("../images/dark.png")).getImage();
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
			if(currentPerson == 2) {
				person = new ImageIcon(Main.class.getResource("../images/Kyle.png")).getImage();
				g.drawImage(person, 190, 220, null);
			}
			else if(currentPerson == 3 || currentPerson == 13) {
				person = new ImageIcon(Main.class.getResource("../images/Rachael.png")).getImage();
				g.drawImage(person, 180, 220, null);
			}
			else if(currentPerson == 4 || currentPerson == 14) {
				person = new ImageIcon(Main.class.getResource("../images/Brad.png")).getImage();
				g.drawImage(person, 220, 220, null);
			}
			else if(currentPerson == 7 || currentPerson == 17) {
				person = new ImageIcon(Main.class.getResource("../images/Neal.png")).getImage();
				g.drawImage(person, 220, 220, null);
			}
			else if(currentPerson == 8 || currentPerson == 18) {
				person = new ImageIcon(Main.class.getResource("../images/Alex.png")).getImage();
				g.drawImage(person, 220, 220, null);
			}
			else if(currentPerson == 9 || currentPerson == 19) {
				person = new ImageIcon(Main.class.getResource("../images/Erica.png")).getImage();
				g.drawImage(person, 240, 220, null);
			}
			else {
				
			}
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
			else if(num == 11) {
				musicEnd();
				backgroundMusic = new Music("11_funny.mp3", true);
				musicStart();
			}
			
		}
		currentMusic = num;
	}
	
	public void showButtonText(){
		int userNum = currentId % 90000;
		
		if(userNum == 1) choiceBox01.setText("다시 방으로 돌아가자");
		else if(userNum == 2) choiceBox01.setText("창문 밖으로 뛰어내린다");
		else if(userNum == 3) choiceBox01.setText("범인은 이 안에 있다");
		else if(userNum == 4) choiceBox01.setText("설비실로 가야 한다");
		else if(userNum == 5) choiceBox01.setText("먹는다");
		else if(userNum == 6) choiceBox01.setText("수상하니 마시지 말라고 말한다");
		else if(userNum == 7) choiceBox01.setText("범인은 Brad");
		else if(userNum == 8) choiceBox01.setText("남자");
		else if(userNum == 9) choiceBox01.setText("2명");
		else if(userNum == 10) choiceBox01.setText("Neal");
		else if(userNum == 11) choiceBox01.setText("Rachael");
		
		choiceBox01.setVerticalAlignment(SwingConstants.CENTER);
		choiceBox01.setHorizontalAlignment(SwingConstants.CENTER);
		choiceBox01.setBounds(35, 120, 410, 80);
		choiceBox01.setFont(new Font("Serif", Font.BOLD, 25));
		choiceBox01.setForeground(Color.WHITE);
		add(choiceBox01);
		
		
		if(userNum == 1) choiceBox02.setText("로비로 내려간다");
		else if(userNum == 2) choiceBox02.setText("로비로 가서 사람들을 만나본다");
		else if(userNum == 3) choiceBox02.setText("범인은 외부인이다");
		else if(userNum == 4) choiceBox02.setText("여기서 기다린다");
		else if(userNum == 5) choiceBox02.setText("먹지 않는다");
		else if(userNum == 6) choiceBox02.setText("가만히 있는다");
		else if(userNum == 7) choiceBox02.setText("범인은 Erica");
		else if(userNum == 8) choiceBox02.setText("여자");
		else if(userNum == 9) choiceBox02.setText("3명");
		else if(userNum == 10) choiceBox02.setText("Brad");
		else if(userNum == 11) choiceBox02.setText("Erica");
		
		choiceBox02.setVerticalAlignment(SwingConstants.CENTER);
		choiceBox02.setHorizontalAlignment(SwingConstants.CENTER);
		choiceBox02.setBounds(35, 240, 410, 80);
		choiceBox02.setFont(new Font("Serif", Font.BOLD, 25));
		choiceBox02.setForeground(Color.WHITE);
		add(choiceBox02);
		
		
		if(userNum == 3) choiceBox03.setText("범인은 귀신이다");
		else if(userNum == 7) choiceBox03.setText("범인은 바로 나");
		else if(userNum == 8) choiceBox03.setText("둘 다");
		else if(userNum == 9) choiceBox03.setText("4명");
		else if(userNum == 10) choiceBox03.setText("Alex");
		else if(userNum == 11) choiceBox03.setText("제 3의 인물");
		
		choiceBox03.setVerticalAlignment(SwingConstants.CENTER);
		choiceBox03.setHorizontalAlignment(SwingConstants.CENTER);
		choiceBox03.setBounds(35, 360, 410, 80);
		choiceBox03.setFont(new Font("Serif", Font.BOLD, 25));
		choiceBox03.setForeground(Color.WHITE);
		add(choiceBox03);
	}

}
