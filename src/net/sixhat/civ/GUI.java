package net.sixhat.civ;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GUI extends JPanel implements MouseListener {
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 2803471375824756500L;
	public Square[][]			bd;
	public Board				board;
	private BufferedImage[]		whites;
	private BufferedImage[]		blacks;

	private int[]				move;
	private Civ					civ;

	public GUI(Civ civ, Board bd) {
		this.civ = civ;
		this.board = bd;
		this.bd = board.board;
		whites = new BufferedImage[6];
		blacks = new BufferedImage[6];
		try {
			whites[0] = ImageIO.read(new File("imgs/pw.png"));
			whites[1] = ImageIO.read(new File("imgs/nw.png"));
			whites[2] = ImageIO.read(new File("imgs/bw.png"));
			whites[3] = ImageIO.read(new File("imgs/rw.png"));
			whites[4] = ImageIO.read(new File("imgs/qw.png"));
			whites[5] = ImageIO.read(new File("imgs/kw.png"));

			blacks[0] = ImageIO.read(new File("imgs/pb.png"));
			blacks[1] = ImageIO.read(new File("imgs/nb.png"));
			blacks[2] = ImageIO.read(new File("imgs/bb.png"));
			blacks[3] = ImageIO.read(new File("imgs/rb.png"));
			blacks[4] = ImageIO.read(new File("imgs/qb.png"));
			blacks[5] = ImageIO.read(new File("imgs/kb.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.addMouseListener(this);
	}

	@Override
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponents(g);
		Rectangle re = g.getClipBounds();
		int dim = Math.min(re.height, re.width) / 8;
		g.setColor(Color.black);
		for (int c = 0; c < 8; c++) {
			for (int r = 0; r < 8; r++) {
				Square sq = bd[c][r];

				if ((c + r) % 2 == 1) {
					g.setColor(Color.LIGHT_GRAY);
				} else {
					g.setColor(Color.GRAY);
				}
				g.fillRect(dim * c, 7 * dim - dim * r, dim, dim);
				if (sq.value > 0) {
					g.setColor(new Color(0.0f, 1.0f, 0.0f, Math.min(1.0f, sq.value / 5.0f)));
				}
				if (sq.value < 0) {
					g.setColor(new Color(1.0f, 0.0f, 0.0f, Math.min(1.0f, -sq.value / 5.0f)));
				}
				g.fillRect(dim * c, 7 * dim - dim * r, dim, dim);
				g.setColor(Color.black);
				g.drawRect(dim * c, 7 * dim - dim * r, dim, dim);

				// Draw Pieces
				if ("P".equals(sq.piece)) {
					g.drawImage(whites[0], dim * c, 7 * dim - dim * r, dim, dim, null);
				}
				if ("N".equals(sq.piece)) {
					g.drawImage(whites[1], dim * c, 7 * dim - dim * r, dim, dim, null);
				}
				if ("B".equals(sq.piece)) {
					g.drawImage(whites[2], dim * c, 7 * dim - dim * r, dim, dim, null);
				}
				if ("R".equals(sq.piece)) {
					g.drawImage(whites[3], dim * c, 7 * dim - dim * r, dim, dim, null);
				}
				if ("Q".equals(sq.piece)) {
					g.drawImage(whites[4], dim * c, 7 * dim - dim * r, dim, dim, null);
				}
				if ("K".equals(sq.piece)) {
					g.drawImage(whites[5], dim * c, 7 * dim - dim * r, dim, dim, null);
				}

				if ("p".equals(sq.piece)) {
					g.drawImage(blacks[0], dim * c, 7 * dim - dim * r, dim, dim, null);
				}
				if ("n".equals(sq.piece)) {
					g.drawImage(blacks[1], dim * c, 7 * dim - dim * r, dim, dim, null);
				}
				if ("b".equals(sq.piece)) {
					g.drawImage(blacks[2], dim * c, 7 * dim - dim * r, dim, dim, null);
				}
				if ("r".equals(sq.piece)) {
					g.drawImage(blacks[3], dim * c, 7 * dim - dim * r, dim, dim, null);
				}
				if ("q".equals(sq.piece)) {
					g.drawImage(blacks[4], dim * c, 7 * dim - dim * r, dim, dim, null);
				}
				if ("k".equals(sq.piece)) {
					g.drawImage(blacks[5], dim * c, 7 * dim - dim * r, dim, dim, null);
				}

				g.setColor(Color.DARK_GRAY);
				// if (!"-".equals(sq.piece)) {
				// g.drawString(sq.piece, dim * c + 8, 7 * dim - dim * r + 12);
				// }
				// g.drawString("" + sq.value, dim * c + 2, 7 * dim - dim * r +
				// dim / 2 + 6);
				g.drawString("" + sq.black, dim * c + 2, 7 * dim - dim * r + 12);
				g.drawString("" + sq.white, dim * c + 2, 7 * dim - dim * r + dim);

			}
		}
		// Highlight
		if (null != move) {
			g.setColor(Color.orange);
			g.drawRect(dim * move[0], 7 * dim - dim * move[1], dim, dim);
			g.drawRect(dim * move[0] + 1, 7 * dim - dim * move[1] + 1, dim - 2, dim - 2);
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Rectangle dim = this.getBounds();
		double mind = Math.min(dim.getWidth(), dim.getHeight());

		int sqx = (int) (8 * e.getX() / mind);
		int sqy = 7 - (int) (8 * e.getY() / mind);

		if (null == move) {
			move = new int[4];
			move[0] = sqx;
			move[1] = sqy;
		} else {
			move[2] = sqx;
			move[3] = sqy;
			board.move(move);
			move = null;
			board.computeSquareValues();
			board.printBoardValues();

		}

		System.out.println(sqx + ", " + sqy);

		this.repaint();

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
