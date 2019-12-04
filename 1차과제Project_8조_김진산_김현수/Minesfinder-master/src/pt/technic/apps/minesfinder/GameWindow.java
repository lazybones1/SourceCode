package pt.technic.apps.minesfinder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.*;

/**
 *
 * @author Gabriel Massadas
 */
public class GameWindow extends javax.swing.JFrame {
	
	
	protected JMenuBar jmb = new JMenuBar();	
	protected JMenu jm = new JMenu();
	
	//private -> protected 酒贰 5俺
	protected ButtonMinefield[][] buttons;
	protected Minefield minefield;
	protected RecordTable record;
	protected boolean isLeftPressed;
	protected boolean isRightPressed;
	
	// 格见-----------------
	private static int moksum = 1;
	public static int getMoksum() {
		return moksum;
	}

	public static void setMoksum(int setmoksum) {
		moksum = setmoksum;
	}	
	// -----------------
	/**
	 * Creates new form GameWindow
	 */

	//public -> protected
	protected void updateButtonsStates() {
		for (int x = 0; x < minefield.getWidth(); x++) {
			for (int y = 0; y < minefield.getHeight(); y++) {
				buttons[x][y].setEstado(minefield.getGridState(x, y));
			}
		}
	}
	
	//private -> protected
	@SuppressWarnings("unchecked")
	protected void initComponents() {
		
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("default");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
         //-------------------------------------------------------------------              
        jm.setText("巢篮 格见 : " + Integer.toString(getMoksum()));
        jmb.add(jm);
        setJMenuBar(jmb); 
        //-------------------------------------------------------------------
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout); 
        
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1094, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 553, Short.MAX_VALUE)
        );
        
       
        pack();
    }// </editor-fold>//GEN-END:initComponents
}
