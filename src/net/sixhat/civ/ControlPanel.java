package net.sixhat.civ;

import java.awt.Graphics;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ControlPanel extends JPanel {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	public JButton				bPawn, bRook, bBishop, bKnight, bQueen, bKing;
	public Board				b;
	public Civ					jf;

	public ControlPanel(Board b, Civ civ) {

		this.b = b;
		this.jf = civ;
		this.add(new JButton(new AbstractAction("Reset") {
			private static final long	serialVersionUID	= 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				b.setupBoardFen("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
				civ.gui.bd = b.board;
				b.computeSquareValues();
				b.printBoardValues();
				civ.jf.repaint();
			}
		}));

		this.add(new JButton(new AbstractAction("Clear Board") {
			private static final long	serialVersionUID	= 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				b.setupBoardFen("8/8/8/8/8/8/8/8 w KQkq - 0 1");
				civ.gui.bd = b.board;
				b.computeSquareValues();
				b.printBoardValues();
				civ.jf.repaint();
			}
		}));

	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
	}
}
