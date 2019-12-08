package pt.technic.apps.minesfinder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MinesFinder extends javax.swing.JFrame {

	private RecordTable recordEasy;
	private RecordTable recordMedium;
	private RecordTable recordHard;

	/**
	 * Creates new form MinesFinder
	 */
	public MinesFinder() {
		initComponents();

		recordEasy = new RecordTable();
		recordMedium = new RecordTable();
		recordHard = new RecordTable();

		readGameRecords();

		labelEasyName.setText(recordEasy.getName());
		labelEasyPoints.setText(Long.toString(recordEasy.getScore() / 1000));

		labelMediumName.setText(recordMedium.getName());
		labelMediumPoints.setText(Long.toString(recordMedium.getScore() / 1000));

		labelHardName.setText(recordHard.getName());
		labelHardPoints.setText(Long.toString(recordHard.getScore() / 1000));

		recordEasy.addRecordTableListener(new RecordTableListener() {
			@Override
			public void recordUpdated(RecordTable record) {
				recordEasyUpdated(record);
			}
		});

		recordMedium.addRecordTableListener(new RecordTableListener() {
			@Override
			public void recordUpdated(RecordTable record) {
				recordMediumUpdated(record);
			}
		});

		recordHard.addRecordTableListener(new RecordTableListener() {
			@Override
			public void recordUpdated(RecordTable record) {
				recordHardUpdated(record);
			}
		});
	}

	private void recordEasyUpdated(RecordTable record) {
		labelEasyName.setText(record.getName());
		labelEasyPoints.setText(Long.toString(record.getScore() / 1000));
		saveGameRecords();
	}

	private void recordMediumUpdated(RecordTable record) {
		labelMediumName.setText(record.getName());
		labelMediumPoints.setText(Long.toString(record.getScore() / 1000));
		saveGameRecords();
	}

	private void recordHardUpdated(RecordTable record) {
		labelHardName.setText(record.getName());
		labelHardPoints.setText(Long.toString(record.getScore() / 1000));
		saveGameRecords();
	}

	private void saveGameRecords() {
		ObjectOutputStream oos = null;
		try {
			File f = new File(System.getProperty("user.home") + File.separator + ".minesfinder.records");
			oos = new ObjectOutputStream(new FileOutputStream(f));
			oos.writeObject(recordEasy);
			oos.writeObject(recordMedium);
			oos.writeObject(recordHard);
			oos.close();
		} catch (IOException ex) {
			Logger.getLogger(MinesFinder.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void readGameRecords() {
		ObjectInputStream ois = null;
		File f = new File(System.getProperty("user.home") + File.separator + ".minesfinder.records");
		if (f.canRead()) {
			try {
				ois = new ObjectInputStream(new FileInputStream(f));
				recordEasy = (RecordTable) ois.readObject();
				recordMedium = (RecordTable) ois.readObject();
				recordHard = (RecordTable) ois.readObject();
				ois.close();
			} catch (IOException | ClassNotFoundException ex) {
				Logger.getLogger(MinesFinder.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void initComponents() {
		panelTitle = new javax.swing.JLabel();
		panelRecords = new javax.swing.JPanel();
		Records = new javax.swing.JLabel();
		labelEasy = new javax.swing.JLabel();
		labelEasyName = new javax.swing.JLabel();
		labelEasyPoints = new javax.swing.JLabel();
		labelMedium = new javax.swing.JLabel();
		labelMediumName = new javax.swing.JLabel();
		labelMediumPoints = new javax.swing.JLabel();
		labelHard = new javax.swing.JLabel();
		labelHardName = new javax.swing.JLabel();
		labelHardPoints = new javax.swing.JLabel();
		panelBtns = new javax.swing.JPanel();
		btnEasy = new javax.swing.JButton();
		btnMedium = new javax.swing.JButton();
		btnHard = new javax.swing.JButton();
		btnmulti = new javax.swing.JButton(); 
		btnCustom = new javax.swing.JButton(); 
		btnChallenge = new javax.swing.JButton();
		btnBGMCtrl = new javax.swing.JButton();
		btnExit = new javax.swing.JButton();
		String fontStyle = "Noto Sans";
		String setRecordLabel = "Player";

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("MinesFinder");
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		setPreferredSize(new java.awt.Dimension(600, 450));
		setResizable(false);

		panelTitle.setBackground(new java.awt.Color(136, 135, 217));
		panelTitle.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
		panelTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		panelTitle.setText("Minesfinder");
		panelTitle.setOpaque(true);
		getContentPane().add(panelTitle, java.awt.BorderLayout.PAGE_START);

		panelRecords.setBackground(new java.awt.Color(118, 206, 108));

		Records.setFont(new java.awt.Font(fontStyle, 1, 18)); // NOI18N
		Records.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		Records.setText("Records");

		labelEasy.setFont(new java.awt.Font(fontStyle, 0, 14)); // NOI18N
		labelEasy.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		labelEasy.setText("Easy");

		labelEasyName.setText(setRecordLabel);

		labelEasyPoints.setText("9999");

		labelMedium.setFont(new java.awt.Font(fontStyle, 0, 14)); // NOI18N
		labelMedium.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		labelMedium.setText("Medium");

		labelMediumName.setText(setRecordLabel);

		labelMediumPoints.setText("9999");

		labelHard.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
		labelHard.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		labelHard.setText("Hard");

		labelHardName.setText(setRecordLabel);

		labelHardPoints.setText("9999");

		javax.swing.GroupLayout panelRecordsLayout = new javax.swing.GroupLayout(panelRecords);
		panelRecords.setLayout(panelRecordsLayout);
		panelRecordsLayout.setHorizontalGroup(panelRecordsLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(labelEasy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE)
				.addComponent(Records, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE)
				.addGroup(panelRecordsLayout.createSequentialGroup().addContainerGap()
						.addGroup(panelRecordsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(panelRecordsLayout.createSequentialGroup().addComponent(labelEasyName)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(labelEasyPoints))
								.addComponent(labelMedium, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(panelRecordsLayout.createSequentialGroup().addComponent(labelMediumName)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(labelMediumPoints))
								.addComponent(labelHard, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(panelRecordsLayout.createSequentialGroup().addComponent(labelHardName)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(labelHardPoints)))
						.addContainerGap()));
		panelRecordsLayout.setVerticalGroup(panelRecordsLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(panelRecordsLayout.createSequentialGroup().addComponent(Records).addGap(18, 18, 18)
						.addComponent(labelEasy).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(panelRecordsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(labelEasyPoints).addComponent(labelEasyName))
						.addGap(18, 18, 18).addComponent(labelMedium)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(panelRecordsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(labelMediumPoints).addComponent(labelMediumName))
						.addGap(18, 18, 18).addComponent(labelHard)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(panelRecordsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(labelHardPoints).addComponent(labelHardName))
						.addGap(0, 169, Short.MAX_VALUE)));

		getContentPane().add(panelRecords, java.awt.BorderLayout.LINE_START);

		panelBtns.setLayout(new java.awt.GridLayout(2, 0));

		btnmulti.setText("multi");
		btnmulti.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnmultiActionPerformed(evt);
			}
		});
		panelBtns.add(btnmulti);

		btnEasy.setIcon(
				new javax.swing.ImageIcon(getClass().getResource("/pt/technic/apps/minesfinder/resources/easy.png"))); // NOI18N
		btnEasy.setText("Easy");
		btnEasy.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnEasyActionPerformed(evt);
			}
		});
		panelBtns.add(btnEasy);

		btnMedium.setIcon(
				new javax.swing.ImageIcon(getClass().getResource("/pt/technic/apps/minesfinder/resources/medium.png"))); // NOI18N
		btnMedium.setText("Medium");
		btnMedium.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnMediumActionPerformed(evt);
			}
		});
		panelBtns.add(btnMedium);

		btnHard.setIcon(
				new javax.swing.ImageIcon(getClass().getResource("/pt/technic/apps/minesfinder/resources/hard.png"))); // NOI18N
		btnHard.setText("Hard");
		btnHard.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnHardActionPerformed(evt);
			}
		});
		panelBtns.add(btnHard);

		btnCustom.setText("Custom");
		btnCustom.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnCustomActionPerformed(evt);
			}
		});
		panelBtns.add(btnCustom);

		
		btnChallenge.setText("challenge"); 
		btnChallenge.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnChallengeActionPerformed(evt);
			}
		});
		panelBtns.add(btnChallenge);
		
		btnBGMCtrl.setText("BGM ON");
		btnBGMCtrl.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnBGMCtrlActionListener(evt);				
			}			
		});
		panelBtns.add(btnBGMCtrl);
	
		
		btnExit.setText("Exit");
		btnExit.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnExitActionPerformed(evt);
			}
		});
		panelBtns.add(btnExit);

		getContentPane().add(panelBtns, java.awt.BorderLayout.CENTER);

		pack();

	}
	
	private void btnmultiActionPerformed(ActionEvent evt) {
		JTextField userName = new JTextField(8);
		JPanel userPanel = new JPanel();
		userPanel.add(new JLabel("Name"));
		userPanel.add(userName);		

		int result = JOptionPane.showConfirmDialog(null, userPanel, "Please Enter details",
				JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			GameWindow.setLife(1);
			MultiplayMode clientgameWindow = new MultiplayMode(new Minefield(16, 16, 40), userName.getText());
			clientgameWindow.setVisible(true);
		}
	}

	private void btnEasyActionPerformed(java.awt.event.ActionEvent evt) {
		ClassicMode.setLevel("Easy");
		ClassicMode gameWindow = new ClassicMode(new Minefield(9, 9, 10), recordEasy);
		gameWindow.setVisible(true);
	}

	private void btnMediumActionPerformed(java.awt.event.ActionEvent evt) {
		ClassicMode.setLevel("Nomal");
		ClassicMode gameWindow = new ClassicMode(new Minefield(16, 16, 40), recordMedium);
		gameWindow.setVisible(true);
	}

	private void btnHardActionPerformed(java.awt.event.ActionEvent evt) {
		ClassicMode.setLevel("Hard");
		ClassicMode gameWindow = new ClassicMode(new Minefield(16, 30, 90), recordHard);
		gameWindow.setVisible(true);
	}
	
	
	private void btnCustomActionPerformed(ActionEvent evt) {
		JTextField customWidth = new JTextField(5);
		JTextField customHeight = new JTextField(5);
		JTextField customMinesNum = new JTextField(5);
		JTextField life = new JTextField(5);

		JPanel customPanel = new JPanel();
		customPanel.add(new JLabel("W"));
		customPanel.add(customWidth);
		customPanel.add(new JLabel("H"));
		customPanel.add(customHeight);
		customPanel.add(new JLabel("M"));
		customPanel.add(customMinesNum);
		customPanel.add(new JLabel("L"));
		customPanel.add(life);
		
		int result = JOptionPane.showConfirmDialog(null, customPanel, "Please Enter details",
				JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			
			CustomMode.setCustomLife(Integer.parseInt(life.getText()));
			
			CustomMode gameWindow = new CustomMode(new Minefield(Integer.parseInt(customWidth.getText()),
					Integer.parseInt(customHeight.getText()), Integer.parseInt(customMinesNum.getText())));
			gameWindow.setVisible(true);
		}
	}
		
		
	private void btnChallengeActionPerformed(ActionEvent evt) {
		ChallengeMode.setChallengeWidth(4);
		ChallengeMode.setChallengeHeight(4);
		ChallengeMode.setChallengeMinesNum(1);
		ChallengeMode.setChallenge(true);
		ChallengeMode gameWindow = new ChallengeMode(new Minefield(ChallengeMode.getChallengeWidth(),
				ChallengeMode.getChallengeHeight(), ChallengeMode.getChallengeMinesNum()));
		gameWindow.setVisible(true);

	}
	
	private void btnBGMCtrlActionListener(ActionEvent evt) {
		if(btnBGMCtrl.getText().equals("BGM ON")) {
			SoundEffect.bgmStop();
			btnBGMCtrl.setText("BGM OFF");
		}
		else {
			SoundEffect.bgmClip();
			btnBGMCtrl.setText("BGM ON");
		}				
	}

	private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {
		System.exit(0);
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JLabel Records;
	private javax.swing.JButton btnEasy;
	private javax.swing.JButton btnExit;
	private javax.swing.JButton btnHard;
	private javax.swing.JButton btnMedium;
	private javax.swing.JButton btnmulti; 
	private javax.swing.JButton btnCustom; 
	private javax.swing.JButton btnChallenge;
	private javax.swing.JButton btnBGMCtrl;
	private javax.swing.JLabel labelEasy;
	private javax.swing.JLabel labelEasyName;
	private javax.swing.JLabel labelEasyPoints;
	private javax.swing.JLabel labelHard;
	private javax.swing.JLabel labelHardName;
	private javax.swing.JLabel labelHardPoints;
	private javax.swing.JLabel labelMedium;
	private javax.swing.JLabel labelMediumName;
	private javax.swing.JLabel labelMediumPoints;
	private javax.swing.JPanel panelBtns;
	private javax.swing.JPanel panelRecords;
	private javax.swing.JLabel panelTitle;	
}
