package pt.technic.apps.minesfinder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author Gabriel Massadas
 */
public class GameWindow extends javax.swing.JFrame {

	protected JMenuBar jmb = new JMenuBar();
	protected JMenu jm = new JMenu();

	// private -> protected
	protected ButtonMinefield[][] buttons;
	protected Minefield minefield;
	protected RecordTable record;
	protected boolean isLeftPressed;
	protected boolean isRightPressed;

	//
	private static int life = 1;

	public static int getLife() {
		return life;
	}

	public static void setLife(int setlife) {
		life = setlife;
	}

	// -----------------
	/**
	 * Creates new form GameWindow
	 */

	// public -> protected
	protected void updateButtonsStates() {
		for (int x = 0; x < minefield.getWidth(); x++) {
			for (int y = 0; y < minefield.getHeight(); y++) {
				buttons[x][y].setEstado(minefield.getGridState(x, y));
			}
		}
	}

	protected void victoryAction() {
		JOptionPane.showMessageDialog(
				null, "Congratulations. You managed to discover all the mines in "
						+ (minefield.getGameDuration() / 1000) + " seconds",
				"victory", JOptionPane.INFORMATION_MESSAGE);
	}

	protected void defeatedAction() {
		JOptionPane.showMessageDialog(null, "Oh, a mine broke", "Lost!", JOptionPane.INFORMATION_MESSAGE);
	}

	protected void clickEvent() {
					if (minefield.isGameFinished()) {
						if (minefield.isPlayerDefeated()) {
							defeatedAction();
						} else {
							victoryAction();
			}
			setVisible(false);
		}
	}

	ActionListener action = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			ButtonMinefield button = (ButtonMinefield) e.getSource();
			int x = button.getCol();
			int y = button.getLine();
			minefield.revealGrid(x, y);
			// -----------------------------------------
			jm.setText("life : " + Integer.toString(getLife()));
			SoundEffect.clickClip();
			// ---------------------------------------
			updateButtonsStates();
			clickEvent();
		}
	};

	MouseListener mouseListener = new MouseListener() {
		@Override
		public void mousePressed(MouseEvent e) {
			// double click--------------------------------------------------------------------------------
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

	// Create buttons for the player
	public void createButtons() {
		for (int x = 0; x < minefield.getWidth(); x++) {
			for (int y = 0; y < minefield.getHeight(); y++) {
				buttons[x][y] = new ButtonMinefield(x, y);
				buttons[x][y].addActionListener(action);
				buttons[x][y].addMouseListener(mouseListener);
				getContentPane().add(buttons[x][y]);
			}
		}
	}

	// private -> protected
	@SuppressWarnings("unchecked")
	protected void initComponents() {

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("default");
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		// -------------------------------------------------------------------
		jm.setText("life : " + Integer.toString(getLife()));
		jmb.add(jm);
		setJMenuBar(jmb);
		// -------------------------------------------------------------------
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);

		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 1094, Short.MAX_VALUE));
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 553, Short.MAX_VALUE));

		pack();
	}// </editor-fold>//GEN-END:initComponents
}
