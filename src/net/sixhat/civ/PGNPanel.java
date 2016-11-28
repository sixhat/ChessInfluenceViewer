package net.sixhat.civ;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class PGNPanel extends JPanel {
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 9026978649878631361L;
	JButton						begining;
	JButton						back;
	JButton						fwd;
	JButton						end;
	JTextArea					text;
	JFileChooser				gameFile;
	JButton						openFile;
	File						pgnFile;
	private JFrame				jf;
	private GUI					gui;
	private Board				b;

	public PGNPanel() {
		setupPanel();
	}

	public void setupPanel() {
		begining = new JButton("|<");
		back = new JButton("<");
		fwd = new JButton(">");
		end = new JButton(">|");
		text = new JTextArea("PGN HERE");
		// text.setMaximumSize(new Dimension(200, 300));
		// text.setPreferredSize(new Dimension(200, 400));
		gameFile = new JFileChooser();
		openFile = new JButton("Open PGN");

		openFile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int returnValue = gameFile.showOpenDialog(PGNPanel.this);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					pgnFile = gameFile.getSelectedFile();

					b.setupBoardFen("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
					gui.bd = b.board;
					b.printBoard();
					b.computeSquareValues();
					b.printBoardValues();
					jf.repaint();

				}
			}
		});

		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.anchor = GridBagConstraints.NORTH;
		c.weighty = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy = 0;
		this.add(begining, c);
		this.add(back, c);
		this.add(fwd, c);
		this.add(end, c);
		c.gridy = 1;
		c.gridwidth = 4;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		this.add(text, c);

		c.weighty = 0;
		c.fill = GridBagConstraints.CENTER;
		c.gridy = 2;
		c.ipady = 0;
		this.add(openFile, c);
	}

	public PGNPanel(JFrame jf, GUI gui, Board b) {
		this.jf = jf;
		// TODO Auto-generated constructor stub
		this.gui = gui;
		this.b = b;
		setupPanel();

	}
}
