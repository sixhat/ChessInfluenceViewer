package net.sixhat.civ;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Civ {

	public JFrame		jf;
	public Board		board;
	public GUI			gui;
	public ControlPanel	ctp;
	public JTextField	texto;

	public Civ() {
		jf = new JFrame("Chess Influence Visualizer");

		jf.setSize(800, 600);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		board = new Board();
		board.setupBoardFen("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
		gui = new GUI(this, board);
		jf.setLayout(new BorderLayout());
		jf.add(gui, BorderLayout.CENTER);

		ctp = new ControlPanel(board, this);
		jf.add(ctp, BorderLayout.EAST);

		texto = new JTextField();
		texto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				board.setupBoardFen(texto.getText());
				gui.bd = board.board;
				board.printBoard();
				board.computeSquareValues();
				board.printBoardValues();
				jf.repaint();
			}
		});
		jf.add(texto, BorderLayout.SOUTH);
		jf.setVisible(true);
		board.computeSquareValues();
		jf.repaint();
	}

	public static void main(String[] args) {
		System.setProperty("apple.awt.application.name", "Chess Influence Visualizer");

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new Civ();

			}
		});
	}
}
