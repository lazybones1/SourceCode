package pt.technic.apps.minesfinder;

import javax.swing.*;
import java.awt.*;

public class ClassicMode extends GameWindow {

	private static String level = "";
	public static void setLevel(String setlevel) {
		level = setlevel;
	}
	
	private void initLife() {
		if(level.equals("Easy")) {
			setLife(1);
		}
		else if (level.equals("Nomal")){
			setLife(2);
		}
		else if(level.equals("Hard")) {
			setLife(3);
		}
	}

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
		
		initLife();
		initComponents();

		setTitle("Classic Game");

		this.minefield = minefield;
		this.record = record;

		buttons = new ButtonMinefield[minefield.getWidth()][minefield.getHeight()];

		getContentPane().setLayout(new GridLayout(minefield.getWidth(), minefield.getHeight()));

		createButtons();

	}

}
