package pt.technic.apps.minesfinder;

import javax.swing.*;
import java.awt.*;

public class ChallengeMode extends GameWindow {

	// --------------------------------------------
	private static int challengeWidth = 0;
	private static int challegneHeight = 0;
	private static int challengeMinesNum = 0;
	private static boolean ischallenge = false;
	public int getLife(int life){
		return life;
	}
	public static boolean isChallenge() {
		return ischallenge;
	}

	public static void setChallenge(boolean setChal) {
		ischallenge = setChal;
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

	@Override
	protected void victoryAction() {
		JOptionPane.showMessageDialog(
				null, "Congratulations. You managed to discover all the mines in "
						+ (minefield.getGameDuration() / 1000) + " seconds",
				"victory", JOptionPane.INFORMATION_MESSAGE);
		// --------------------------------------------------------
		if (isChallenge()) {
			setChallengeMinesNum(getChallengeMinesNum() + 4);
			setChallengeWidth(getChallengeWidth() + 3);
			setChallengeHeight(getChallengeHeight() + 3);
			ChallengeMode gameWindow = new ChallengeMode(
					new Minefield(getChallengeWidth(), getChallengeHeight(), getChallengeMinesNum()));
			gameWindow.setVisible(true);
		}
	}

	public ChallengeMode(Minefield minefield) {
		setLife(1);
		initComponents();
		setTitle("Challenge Game");
		isLeftPressed = false;
		isRightPressed = false;

		this.minefield = minefield;

		buttons = new ButtonMinefield[minefield.getWidth()][minefield.getHeight()];

		getContentPane().setLayout(new GridLayout(minefield.getWidth(), minefield.getHeight()));

		createButtons();

	}

}
