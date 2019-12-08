package Minesfinder;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

	private final static Logger logger = Logger.getGlobal();

	public Socket socket;

	public Client(Socket socket) {

		this.socket = socket;

		receive();

	}

	// message receive method

	private void receive() {

		Runnable thread = new Runnable() {

			@Override

			public void run() {
				try {
					while (true) {
						InputStream in = socket.getInputStream();
						byte[] buffer = new byte[512];
						DataInputStream dis = new DataInputStream(in);
//						int length = in.read(buffer);
//						while(length == -1) throw new IOException();

						logger.log(Level.INFO, "massage receive success" + socket.getRemoteSocketAddress() + " : "
								+ Thread.currentThread().getName());

//						String message = new String(buffer, 0, length, "UTF-8");
						String message = dis.readUTF();
						for (Client client : Server.clients) {
							client.send(message);
						}
					}
				} catch (Exception e) {
					try {

						logger.log(Level.INFO, "message receive fail" + socket.getRemoteSocketAddress() + " : "
								+ Thread.currentThread().getName());


					} catch (Exception e2) {
						logger.log(Level.INFO, "exception msg", e2);
					}
				}
			}
		};
		Server.threadPool.submit(thread);
	}

	// message send method

	private void send(String message) {
		Runnable thread = new Runnable() {
			@Override
			public void run() {
				try {

					logger.log(Level.INFO, "message send success" + socket.getRemoteSocketAddress() + " : "
							+ Thread.currentThread().getName());

					OutputStream out = socket.getOutputStream();
					byte[] buffer = message.getBytes("UTF-8");
					out.write(buffer);
					out.flush();
				} catch (Exception e) {
					try {
						
						logger.log(Level.INFO, "message send fail" + socket.getRemoteSocketAddress() + " : "
								+ Thread.currentThread().getName());

						
						Server.clients.remove(Client.this);
						socket.close();
					} catch (Exception e2) {
						logger.log(Level.INFO, "exception msg", e2);
					}
				}
			}
		};
		Server.threadPool.submit(thread);

	}
}
