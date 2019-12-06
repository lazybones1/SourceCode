package pt.technic.apps.minesfinder;

import java.awt.GridLayout;

public class CustomMode extends GameWindow {
	public CustomMode(Minefield minefield) {
		initComponents();
		setTitle("Custom Game");		

		this.minefield = minefield;

		buttons = new ButtonMinefield[minefield.getWidth()][minefield.getHeight()];

		getContentPane().setLayout(new GridLayout(minefield.getWidth(), minefield.getHeight()));
		
		createButtons();

	}

}
