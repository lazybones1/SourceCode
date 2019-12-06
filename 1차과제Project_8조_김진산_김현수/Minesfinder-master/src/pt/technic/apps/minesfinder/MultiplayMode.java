package pt.technic.apps.minesfinder;

import javafx.application.Platform;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class MultiplayMode extends GameWindow {

	private Socket socket;
	private String userName;

	public MultiplayMode(Minefield minefield, String userName) {
		startClient("211.33.5.110", 9876);
		this.userName = userName;

		initComponents();
		setTitle("Multi Game");

		this.minefield = minefield;
		buttons = new ButtonMinefield[minefield.getWidth()][minefield.getHeight()];
		getContentPane().setLayout(new GridLayout(minefield.getWidth(), minefield.getHeight()));
		createButtons();

	}

	@Override
	protected void clickEvent() {
		if (minefield.isGameFinished()) {
			if (minefield.isPlayerDefeated()) {
				send(userName + " " + "broke");
			} else {
				send(userName + "  " + "finish");
			}
			//юс╫ц
			setVisible(false);
		}
	}

	//
	private void startClient(String IP, int port) {
		Thread thread = new Thread() {
			public void run() {
				try {
					socket = new Socket(IP, port);
					receive();
				} catch (Exception e) {
					if (!socket.isClosed()) {
						stopClient();
						System.out.println("server access fail");
						Platform.exit();
					}
				}

			}
		};
		thread.start();
	}

	// public -> private
	private void stopClient() {
		try {
			if (socket != null && !socket.isClosed()) {
				socket.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// public -> private
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

	// public -> private
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
