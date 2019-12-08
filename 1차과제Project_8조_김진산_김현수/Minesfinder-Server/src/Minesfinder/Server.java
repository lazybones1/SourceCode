package Minesfinder;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;

public class Server extends Application {
	
	private final static Logger logger = Logger.getGlobal();
	
	public static ExecutorService threadPool;

	public static Vector<Client> clients = new Vector<Client>();

	private ServerSocket serverSocket;

	public void startServer(String IP, int port) {
		try {
			serverSocket = new ServerSocket();
			serverSocket.bind(new InetSocketAddress(IP, port));
		} catch (Exception e) {
			e.printStackTrace();
			if (!serverSocket.isClosed()) {
				stopServer();
			}
			return;
		}
		//  client access waiting thread 

		Runnable thread = new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Socket socket = serverSocket.accept();
						clients.add(new Client(socket));
						
						logger.log(Level.INFO, "client access" + socket.getRemoteSocketAddress() + " : "
								+ Thread.currentThread().getName());
						
//						System.out.println("client access" + socket.getRemoteSocketAddress() + " : "
//								+ Thread.currentThread().getName());
						
					} catch (Exception e) {
						if (!serverSocket.isClosed()) {
							stopServer();
						}
						break;
					}
				}
			}
		};
		threadPool = Executors.newCachedThreadPool();
		threadPool.submit(thread);
	}



	public void stopServer() {
		try {
			// close all socket in working
			Iterator<Client> iterator = clients.iterator();
			while (iterator.hasNext()) {
				Client client = iterator.next();
				client.socket.close();
				iterator.remove();
			}
			if (serverSocket != null && !serverSocket.isClosed()) {
				serverSocket.close();
			}

			if (threadPool != null && !threadPool.isShutdown()) {
				threadPool.shutdown();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void start(Stage primaryStage) {
		BorderPane root = new BorderPane();
		root.setPadding(new Insets(5));
		TextArea textArea = new TextArea();
		textArea.setEditable(false);
		textArea.setFont(new Font("Serif", 15));
		root.setCenter(textArea);
		Button toggleButton = new Button("Start");
		toggleButton.setMaxWidth(Double.MAX_VALUE);
		BorderPane.setMargin(toggleButton, new Insets(1, 0, 0, 0));
		root.setBottom(toggleButton);
		String IP = "127.0.0.1";
		int port = 9876;
		toggleButton.setOnAction(event -> {
			if (toggleButton.getText().contentEquals("Start")) {
				startServer(IP, port);
				Platform.runLater(() -> {
					String message = String.format("Start Sever \n", IP, port);
					textArea.appendText(message);
					toggleButton.setText("End");
				});
			} else {
				stopServer();
				Platform.runLater(() -> {
					String message = String.format("Server End \n", IP, port);
					textArea.appendText(message);
					toggleButton.setText("Start");
				});
			}
		});
		Scene scene = new Scene(root, 400, 400);
		primaryStage.setTitle("Game Server");
		primaryStage.setOnCloseRequest(event -> stopServer());
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
