package pt.technic.apps.minesfinder;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import javafx.application.Platform;

public class MultiplayMode extends GameWindow {

	private Socket socket;
	private String userName;

	public MultiplayMode(Minefield minefield, String userName) {
		startClient("211.33.5.110", 9876);
		this.userName = userName;

		initComponents();
		setTitle("Multi Game");

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
						send(userName + " " + "broke");
					} else {
						send(userName + "  " + "finish");

					}
				}
			}
		};

		MouseListener mouseListener = new MouseListener() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (SwingUtilities.isLeftMouseButton(e)) {
					isLeftPressed = true;
				} else if (SwingUtilities.isRightMouseButton(e)) {
					isRightPressed = true;
				}

				if (isLeftPressed && isRightPressed) {

				}

				if (e.getButton() == MouseEvent.BUTTON3) {
					ButtonMinefield botao = (ButtonMinefield) e.getSource();
					int x = botao.getCol();
					int y = botao.getLine();
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
		for (int x = 0; x < minefield.getWidth(); x++) {
			for (int y = 0; y < minefield.getHeight(); y++) {
				buttons[x][y] = new ButtonMinefield(x, y);
				buttons[x][y].addActionListener(action);
				buttons[x][y].addMouseListener(mouseListener);
				getContentPane().add(buttons[x][y]);
			}
		}
	}

	// 클라이언트 메소드
	private void startClient(String IP, int port) {
		Thread thread = new Thread() {
			public void run() {
				try {
					socket = new Socket(IP, port);
					receive();
				} catch (Exception e) {
					if (!socket.isClosed()) {
						stopClient();
						System.out.println("서버 접속 실패");
						Platform.exit();
					}
				}

			}
		};
		thread.start();
	}
	//public -> private
	private void stopClient() {
		try {
			if (socket != null && !socket.isClosed()) {
				socket.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//public -> private
	private void receive() {
		while (true) {
			try {
				InputStream in = socket.getInputStream();
				byte[] buffer = new byte[512];
				int length = in.read(buffer);
				if (length == -1)
					throw new IOException();
				String message = new String(buffer, 0, length, "UTF-8");
				String[] strarr = message.split(" ");
				if (strarr[0].equals(userName)) {
					if (strarr[1].equals("finish")) {
						JOptionPane.showMessageDialog(null, userName + " win", "Result!",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, userName + " lose", "Result!",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					if (strarr[1].equals("finish")) {
						JOptionPane.showMessageDialog(null, userName + " lose", "Result!",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, userName + " win", "Result!",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
				setVisible(false);
				stopClient();
			} catch (Exception e) {
				stopClient();
				break;
			}
		}
	}
	//public -> private
	private void send(String message) { 
		Thread thread = new Thread() {
			public void run() {
				try {
					OutputStream out = socket.getOutputStream();
					byte[] buffer = message.getBytes("UTF-8");
					out.write(buffer);
					out.flush();
				} catch (Exception e) {
					stopClient();
				}
			}
		};
		thread.start();
	}
}
