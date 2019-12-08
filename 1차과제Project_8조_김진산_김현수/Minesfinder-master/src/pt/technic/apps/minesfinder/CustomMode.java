package pt.technic.apps.minesfinder;

import java.awt.GridLayout;

public class CustomMode extends GameWindow {
	
	private static int customLife;
	
	public static void setCustomLife(int life) {
		customLife = life;
	}
	
	public CustomMode(Minefield minefield) {
		
		setLife(customLife);
		initComponents();
		setTitle("Custom Game");		

		this.minefield = minefield;

		buttons = new ButtonMinefield[minefield.getWidth()][minefield.getHeight()];

		getContentPane().setLayout(new GridLayout(minefield.getWidth(), minefield.getHeight()));
		
		createButtons();

	}

}
