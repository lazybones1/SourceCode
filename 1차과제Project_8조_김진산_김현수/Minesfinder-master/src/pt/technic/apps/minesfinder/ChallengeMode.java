package pt.technic.apps.minesfinder;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class ChallengeMode extends GameWindow {
	
	// --------------------------------------------
	private static int challengeWidth = 0;
	private static int challegneHeight = 0;
	private static int challengeMinesNum = 0;
	private static boolean challenge = false;

	public static boolean getChallenge() {
		return challenge;
	}

	public static void setChallenge(boolean chal) {
		challenge = chal;
	}

	public static int getChallengeWidth() {
		return challengeWidth;
	}

	public static void setChallengeWidth(int width) {
		challengeWidth = width;
	}

	public static int getChallengeHeight() {
		return challegneHeight;
	}

	public static void setChallengeHeight(int height) {
		challegneHeight = height;
	}

	public static int getChallengeMinesNum() {
		return challengeMinesNum;
	}

	public static void setChallengeMinesNum(int mines) {
		challengeMinesNum = mines;
	}

	// -----------------------------------------
	public ChallengeMode(Minefield minefield) {

		initComponents();
		setTitle("Challenge Game");
		isLeftPressed = false;
		isRightPressed = false;

		this.minefield = minefield;

		buttons = new ButtonMinefield[minefield.getWidth()][minefield.getHeight()];

		getContentPane().setLayout(new GridLayout(minefield.getWidth(), minefield.getHeight()));

		ActionListener action = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ButtonMinefield button = (ButtonMinefield) e.getSource();
				int x = button.getCol();
				int y = button.getLine();
				minefield.revealGrid(x, y);
				updateButtonsStates();

				if (minefield.isGameFinished()) {
					if (minefield.isPlayerDefeated()) {
						JOptionPane.showMessageDialog(null, "Oh, a mine broke", "Lost!",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null,
								"Congratulations. You managed to discover all the mines in "
										+ (minefield.getGameDuration() / 1000) + " seconds",
								"victory", JOptionPane.INFORMATION_MESSAGE);
						// --------------------------------------------------------
						if (getChallenge()) {
							setChallengeMinesNum(getChallengeMinesNum() + 4);
							setChallengeWidth(getChallengeWidth() + 3);
							setChallengeHeight(getChallengeHeight() + 3);
							setMoksum(1);
							ChallengeMode gameWindow = new ChallengeMode(
									new Minefield(getChallengeWidth(), getChallengeHeight(), getChallengeMinesNum()));
							gameWindow.setVisible(true);
						}
						// -------------------------------------------------------
					}
					setVisible(false);
				}
			}
		};

		MouseListener mouseListener = new MouseListener() {
			@Override
			public void mousePressed(MouseEvent e) {
				// ¾çÅ¬¸¯--------------------------------------------------------------------------------
				ButtonMinefield botao = (ButtonMinefield) e.getSource();
				int x = botao.getCol();
				int y = botao.getLine();
				if (minefield.getGridState(x, y) >= 1 && minefield.getGridState(x, y) <= 8) {
					if (SwingUtilities.isLeftMouseButton(e)) {
						isLeftPressed = true;
					} else if (SwingUtilities.isRightMouseButton(e)) {
						isRightPressed = true;
					}

					if (isLeftPressed && isRightPressed) {

						minefield.doubleCliked(x, y);
					}
				}
				// -------------------------------------------------------------------------------
				if (e.getButton() == MouseEvent.BUTTON3) {
					if (minefield.getGridState(x, y) == minefield.COVERED) {
						minefield.setMineMarked(x, y);
					} else if (minefield.getGridState(x, y) == minefield.MARKED) {
						minefield.setMineQuestion(x, y);
					} else if (minefield.getGridState(x, y) == minefield.QUESTION) {
						minefield.setMineCovered(x, y);
					}
					updateButtonsStates();
				}
			}

			@Override
			public void mouseClicked(MouseEvent me) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (SwingUtilities.isLeftMouseButton(e)) {
					isLeftPressed = false;
				} else if (SwingUtilities.isRightMouseButton(e)) {
					isRightPressed = false;
				}
			}

			@Override
			public void mouseEntered(MouseEvent me) {
			}

			@Override
			public void mouseExited(MouseEvent me) {
			}

		};
		for (int x = 0; x < minefield.getWidth(); x++) {
			for (int y = 0; y < minefield.getHeight(); y++) {
				buttons[x][y] = new ButtonMinefield(x, y);
				buttons[x][y].addActionListener(action);
				buttons[x][y].addMouseListener(mouseListener);
				getContentPane().add(buttons[x][y]);
			}
		}

	}

}
