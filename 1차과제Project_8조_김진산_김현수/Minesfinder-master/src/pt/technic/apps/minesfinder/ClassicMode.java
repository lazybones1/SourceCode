package pt.technic.apps.minesfinder;

import javax.swing.*;
import java.awt.*;

public class ClassicMode extends GameWindow {

	@Override
	protected void victoryAction() {
		JOptionPane.showMessageDialog(
				null, "Congratulations. You managed to discover all the mines in "
						+ (minefield.getGameDuration() / 1000) + " seconds",
				"victory", JOptionPane.INFORMATION_MESSAGE);
		boolean newRecord = minefield.getGameDuration() < record.getScore();

		if (newRecord) {
			String name = JOptionPane.showInputDialog("Enter your name");
			if (!name.equals(""))
				record.setRecord(name, minefield.getGameDuration());
		}
	}

	public ClassicMode(Minefield minefield, RecordTable record) {

		initComponents();

		setTitle("Classic Game");

		this.minefield = minefield;
		this.record = record;

		buttons = new ButtonMinefield[minefield.getWidth()][minefield.getHeight()];

		getContentPane().setLayout(new GridLayout(minefield.getWidth(), minefield.getHeight()));

		createButtons();

	}

}
