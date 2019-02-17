package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.swingViewer.ViewPanel;
import org.graphstream.ui.view.Viewer;

import gameDriver.GameDriver;
import gameDriver.PlayersTypes;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.Font;
import java.util.concurrent.TimeUnit;

public class GUIGame extends JFrame {
	static GUIGame frame;
	JPanel panel;
	private JPanel contentPane;
	private JTextField troopsField;
	private JTextField toattField;
	private JTextField fromattField;
	private GameDriver gameDriver;
	Viewer viewer;
	Graph graph;
	int[] playerType;
	JLabel lblPlayerNo;
	JLabel lbltroops;
	JButton btnDrop;
	JButton btnAttack;
	JButton btnFinishTurn;
	private JLabel lblPlayer;
	private JLabel lblPlayer_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new GUIGame(2, 2);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUIGame(int p1, int p2) {

		initializeGameDriver(p1, p2);

		playerType = new int[2];
		playerType[0] = p1;
		playerType[1] = p2;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1366, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		troopsField = new JTextField();
		troopsField.setBounds(950, 138, 86, 20);
		contentPane.add(troopsField);
		troopsField.setColumns(10);

		JLabel lblDropTroopsAt = new JLabel("drop troops at");
		lblDropTroopsAt.setBounds(950, 88, 86, 39);
		contentPane.add(lblDropTroopsAt);

		lbltroops = new JLabel("28");
		lbltroops.setBounds(1040, 141, 100, 14);
		contentPane.add(lbltroops);

		btnDrop = new JButton("drop");
		btnDrop.setBounds(947, 179, 89, 23);
		contentPane.add(btnDrop);
		btnDrop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int drop = Integer.parseInt(troopsField.getText());
				if (gameDriver.getGame().getGraph().getOwner(drop) == gameDriver.getTurn()) {
					gameDriver.playHumanDeploymentTurn(drop);
					troopsField.setText("");
					updateGraph();
					updateControlState();
				} else {

					int pane = JOptionPane.showConfirmDialog(frame, "error you don't own :" + drop, "ERROR",
							JOptionPane.DEFAULT_OPTION);

				}
			}
		});

		lblPlayerNo = new JLabel("player no: ");
		lblPlayerNo.setBounds(950, 11, 86, 30);
		contentPane.add(lblPlayerNo);

		JLabel lblAttackFrom = new JLabel("attack from :");
		lblAttackFrom.setBounds(950, 237, 86, 20);
		contentPane.add(lblAttackFrom);

		fromattField = new JTextField();
		fromattField.setBounds(950, 268, 86, 20);
		contentPane.add(fromattField);
		fromattField.setColumns(10);

		JLabel lblTo = new JLabel("to :");
		lblTo.setBounds(950, 311, 86, 23);
		contentPane.add(lblTo);

		toattField = new JTextField();
		toattField.setBounds(950, 354, 86, 20);
		contentPane.add(toattField);
		toattField.setColumns(10);

		btnAttack = new JButton("attack");
		btnAttack.setBounds(950, 413, 89, 23);
		contentPane.add(btnAttack);
		btnAttack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int fromatt = Integer.parseInt(fromattField.getText());
				int toatt = Integer.parseInt(toattField.getText());
				if (gameDriver.getGame().canAttack(fromatt, toatt)) {
					gameDriver.playHumanAttackTurn(fromatt, toatt);
					fromattField.setText("");
					toattField.setText("");
					updateGraph();
				} else {

					int pane = JOptionPane.showConfirmDialog(frame, "Illegal attack from " + fromatt + " to " + toatt,
							"ERROR", JOptionPane.DEFAULT_OPTION);

				}
			}
		});

		btnFinishTurn = new JButton("finish turn");
		btnFinishTurn.setBounds(950, 498, 108, 23);
		contentPane.add(btnFinishTurn);

		lblPlayer = new JLabel("player 1");
		lblPlayer.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 20));
		lblPlayer.setForeground(new Color(0, 51, 255));
		lblPlayer.setBackground(new Color(255, 255, 255));
		lblPlayer.setBounds(950, 569, 131, 46);
		contentPane.add(lblPlayer);

		lblPlayer_1 = new JLabel("player 2");
		lblPlayer_1.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 20));
		lblPlayer_1.setBackground(new Color(255, 255, 255));
		lblPlayer_1.setForeground(new Color(255, 102, 0));
		lblPlayer_1.setBounds(950, 615, 124, 54);
		contentPane.add(lblPlayer_1);
		btnFinishTurn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (playerType[gameDriver.getTurn()] > 3) {
					gameDriver.playAITurn();
				} else if (playerType[gameDriver.getTurn()] != 2) {
					gameDriver.playDeploymentTurn();
					gameDriver.playAttackTurn();
				}
				gameDriver.changeTurn();
				gameDriver.initializeTurn(gameDriver.getTurn());

				updateGraph();
				updateControlState();
			}
		});

		drawGraph();

	}

	void updateControlState() {
		panel.setBorder(BorderFactory.createLineBorder(Color.blue, 5));
		if (gameDriver.getTurn() == 1) {
			panel.setBorder(BorderFactory.createLineBorder(Color.orange, 5));
		}
		if (gameDriver.getGame().gameEnded()) {
			int pane = JOptionPane.showConfirmDialog(frame,
					"player : " + (gameDriver.getGame().getGraph().getOwner(1) + 1) + " won the Game",
					"Game is finished", JOptionPane.DEFAULT_OPTION);
			System.exit(0);
			frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		}
		lblPlayerNo.setText("player no:" + (gameDriver.getTurn() + 1));

		lbltroops.setText(gameDriver.getGame().getPlayer(gameDriver.getTurn()).getAvailableTroops() + " to drop");

		troopsField.setEnabled(true);
		btnDrop.setEnabled(true);
		fromattField.setEnabled(true);
		toattField.setEnabled(true);
		btnAttack.setEnabled(true);
		if (playerType[gameDriver.getTurn()] != 2) {
			System.out.println(playerType[gameDriver.getTurn()]);
			troopsField.setEnabled(false);
			btnDrop.setEnabled(false);
			fromattField.setEnabled(false);
			toattField.setEnabled(false);
			btnAttack.setEnabled(false);
		}
	}

	void drawGraph() {

		ArrayList<Integer>[] adjacencyList = gameDriver.getGame().getGraph().adjacencyList;
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel(new GridLayout()) {
			@Override
			public Dimension getPreferredSize() {
				return new Dimension(830, 600);
			}
		};
		// panel.setSize(800, 600);
		panel.setLocation(120, 120);

		graph = new SingleGraph("Risk", false, true);

		for (Integer i = 1; i < adjacencyList.length; i++) {

			graph.addNode(i.toString()).addAttribute("ui.label",
					"(" + i + ")  Troops:" + gameDriver.getGame().getGraph().getTroopsInVertex(i) + "  C:"
							+ gameDriver.getGame().getGraph().getContinentOfVertex(i));
			if (gameDriver.getGame().getGraph().getOwner(i) == 0) {
				graph.getNode(i.toString()).addAttribute("ui.style", "size: 50px; fill-color: rgb(0,100,255);");
			} else {
				graph.getNode(i.toString()).addAttribute("ui.style", "size: 50px; fill-color: rgb(255,100,0);");
			}
		}
		for (Integer i = 1; i < adjacencyList.length; i++) {
			for (Integer j = 0; j < adjacencyList[i].size(); j++) {

				graph.addEdge(i + "+" + adjacencyList[i].get(j), i.toString(), adjacencyList[i].get(j).toString());
			}
		}
		viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
		viewer.enableAutoLayout();
		ViewPanel viewPanel = viewer.addDefaultView(false);
		panel.add(viewPanel);
		frame.getContentPane().add(panel);
		frame.pack();
		// frame.setLocationRelativeTo(null);
		// frame.setVisible(true);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		contentPane.add(panel);
		updateControlState();

	}

	void updateGraph() {

		ArrayList<Integer>[] adjacencyList = gameDriver.getGame().getGraph().adjacencyList;
		for (Integer i = 1; i < adjacencyList.length; i++) {
			graph.getNode(i.toString()).addAttribute("ui.label",
					"(" + i + ")  Troops:" + gameDriver.getGame().getGraph().getTroopsInVertex(i) + "  C:"
							+ gameDriver.getGame().getGraph().getContinentOfVertex(i));
			if (gameDriver.getGame().getGraph().getOwner(i) == 0) {
				graph.getNode(i.toString()).addAttribute("ui.style", "size: 50px; fill-color: rgb(0,100,255);");
			} else {
				graph.getNode(i.toString()).addAttribute("ui.style", "size: 50px; fill-color: rgb(255,100,0);");
			}
		}
		
	}

	void initializeGameDriver(int p1, int p2) {
		gameDriver = new GameDriver(PlayersTypes.values()[p1], PlayersTypes.values()[p2]);

	}
}
