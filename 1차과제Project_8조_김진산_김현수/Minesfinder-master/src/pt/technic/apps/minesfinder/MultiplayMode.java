package pt.technic.apps.minesfinder;

import javafx.application.Platform;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MultiplayMode extends GameWindow {

	private final static Logger logger = Logger.getGlobal();

	private Socket socket;
	private String userName;

	public MultiplayMode(Minefield minefield, String userName) {
		startClient("211.33.5.110", 9876);
		this.userName = userName;

		setLife(1);
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
			//
			setVisible(false);
		}
	}

	//
	private void startClient(String ip, int port) {
		Thread thread = new Thread() {
			public void run() {
				try {
					socket = new Socket(ip, port);
					receive();
				} catch (Exception e) {
					if (!socket.isClosed()) {
						stopClient();
						logger.log(Level.INFO, "server access fail");
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
			logger.log("exception msg" + e.printStackTrace().toString();
		}
	}

	// public -> private
	private void receive() {
		String dialog = "Result!";
		while (true) {
			try {
				InputStream in = socket.getInputStream();
				byte[] buffer = new byte[512];
				int length = in.read(buffer);
				if (length == -1)
					throw new IOException();
				String message = new String(buffer, 0, length, "UTF_8");
				String[] strarr = message.split(" ");
				if (strarr[0].equals(userName)) {
					if (strarr[1].equals("finish")) {
						JOptionPane.showMessageDialog(null, userName + " win", dialog, JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, userName + " lose", dialog,
								JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					if (strarr[1].equals("finish")) {
						JOptionPane.showMessageDialog(null, userName + " lose", dialog,
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, userName + " win", dialog, JOptionPane.INFORMATION_MESSAGE);
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
					byte[] buffer = message.getBytes("UTF_8");
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
