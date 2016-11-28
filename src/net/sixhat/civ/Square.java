package net.sixhat.civ;

import java.awt.Color;

public class Square {
	Color	background;
	Color	highlight;
	String	piece;
	String	name;
	int		white	= 0;
	int		black	= 0;
	int		value	= 0;

	public Square() {
		this.piece = "-";
	}

	public Square(String piece) {
		this.piece = piece;
	}

	@Override
	public String toString() {
		return piece;
	}
}
