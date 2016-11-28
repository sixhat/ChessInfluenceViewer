package net.sixhat.civ;

public class Board {
	Square[][]	board;
	// Square[] board120x;
	String		files	= "abcdefgh";
	String		rows	= "12345678";

	public Board() {
		board = new Square[8][8];
		// board120x = new Square[120];
	}

	private void whiteIncreaseValue(int x, int y) {
		if (0 <= x && x < 8 && 0 <= y && y < 8) {
			board[x][y].value++;
			board[x][y].white++;
		}
	}

	private void blackDecreaseValue(int x, int y) {
		if (0 <= x && x < 8 && 0 <= y && y < 8) {
			board[x][y].value--;
			board[x][y].black++;
		}
	}

	public void computeSquareValues() {
		for (Square[] squares : board) {
			for (Square s : squares) {
				s.value = 0;
				s.white = 0;
				s.black = 0;
			}
		}

		int x, y;
		int[] xx = { -2, -1, 1, 2 };
		int[] yy = { -2, -1, 1, 2 };
		for (int f = 0; f < 8; f++) {
			for (int r = 0; r < 8; r++) {
				switch (board[f][r].piece) {
				// White Pieces first
					case "R":
						// horizontal
						x = f + 1;
						while (x < 8) {
							whiteIncreaseValue(x, r);
							if (!"-KQR".contains(board[x][r].piece)) {
								break;
							}
							if ("K".contains(board[x][r].piece)) {
								whiteIncreaseValue(x + 1, r);
								break;
							}
							x++;
						}
						x = f - 1;
						while (x >= 0) {
							whiteIncreaseValue(x, r);
							if (!"-KQR".contains(board[x][r].piece)) {
								break;
							}
							if ("K".contains(board[x][r].piece)) {
								whiteIncreaseValue(x - 1, r);
								break;
							}
							x--;
						}
						// Vertical
						y = r + 1;
						while (y < 8) {
							whiteIncreaseValue(f, y);
							if (!"-KQR".contains(board[f][y].piece)) {
								break;
							}
							if ("K".contains(board[f][y].piece)) {
								whiteIncreaseValue(f, y + 1);
								break;
							}
							y++;
						}
						y = r - 1;
						while (y >= 0) {
							whiteIncreaseValue(f, y);
							if (!"-KQR".contains(board[f][y].piece)) {
								break;
							}
							if ("K".contains(board[f][y].piece)) {
								whiteIncreaseValue(f, y - 1);
								break;
							}
							y--;
						}
						break;
					case "r":
						// horizontal
						x = f + 1;
						while (x < 8) {
							blackDecreaseValue(x, r);
							if (!"-kqr".contains(board[x][r].piece)) {
								break;
							}
							if ("k".contains(board[x][r].piece)) {
								blackDecreaseValue(x + 1, r);
								break;
							}
							x++;
						}
						x = f - 1;
						while (x >= 0) {
							blackDecreaseValue(x, r);
							if (!"-kqr".contains(board[x][r].piece)) {
								break;
							}
							if ("k".contains(board[x][r].piece)) {
								blackDecreaseValue(x - 1, r);
								break;
							}
							x--;
						}
						// Vertical
						y = r + 1;
						while (y < 8) {
							blackDecreaseValue(f, y);
							if (!"-kqr".contains(board[f][y].piece)) {
								break;
							}
							if ("k".contains(board[f][y].piece)) {
								blackDecreaseValue(f, y + 1);
								break;
							}
							y++;
						}
						y = r - 1;
						while (y >= 0) {
							blackDecreaseValue(f, y);
							if (!"-kqr".contains(board[f][y].piece)) {
								break;
							}
							if ("k".contains(board[f][y].piece)) {
								blackDecreaseValue(f, y - 1);
								break;
							}
							y--;
						}
						break;
					case "N":
						for (int x1 : xx) {
							for (int y1 : yy) {
								if ((Math.abs(x1) + Math.abs(y1)) == 3) {
									int x2 = x1 + f;
									int y2 = y1 + r;
									if (x2 >= 0 && x2 < 8 && y2 >= 0 && y2 < 8) {
										whiteIncreaseValue(x2, y2);
									}
								}
							}
						}
						break;
					case "n":
						for (int x1 : xx) {
							for (int y1 : yy) {
								if ((Math.abs(x1) + Math.abs(y1)) == 3) {
									int x2 = x1 + f;
									int y2 = y1 + r;
									if (x2 >= 0 && x2 < 8 && y2 >= 0 && y2 < 8) {
										blackDecreaseValue(x2, y2);
									}
								}
							}
						}
						break;
					case "B":
						x = f + 1;
						y = r + 1;
						while (x < 8 && y < 8) {
							whiteIncreaseValue(x, y);
							if (!"-BPQK".contains(board[x][y].piece)) {
								break;
							}
							if ("PK".contains(board[x][y].piece)) {
								whiteIncreaseValue(x + 1, y + 1);
								break;
							}

							x++;
							y++;
						}
						x = f - 1;
						y = r - 1;
						while (x >= 0 && y >= 0) {
							whiteIncreaseValue(x, y);
							if (!"-BQK".contains(board[x][y].piece)) {
								break;
							}
							if ("K".contains(board[x][y].piece)) {
								whiteIncreaseValue(x - 1, y - 1);
								break;
							}
							x--;
							y--;
						}
						x = f + 1;
						y = r - 1;
						while (x < 8 && y >= 0) {
							whiteIncreaseValue(x, y);
							if (!"-BQK".contains(board[x][y].piece)) {
								break;
							}
							if ("K".contains(board[x][y].piece)) {
								whiteIncreaseValue(x + 1, y - 1);
								break;
							}
							x++;
							y--;
						}
						x = f - 1;
						y = r + 1;
						while (x >= 0 && y < 8) {
							whiteIncreaseValue(x, y);
							if (!"-BPQK".contains(board[x][y].piece)) {
								break;
							}
							if ("PK".contains(board[x][y].piece)) {
								whiteIncreaseValue(x - 1, y + 1);
								break;
							}
							x--;
							y++;
						}
						break;
					case "b":
						x = f + 1;
						y = r + 1;
						while (x < 8 && y < 8) {
							blackDecreaseValue(x, y);
							if (!"-bqk".contains(board[x][y].piece)) {
								break;
							}
							if ("k".contains(board[x][y].piece)) {
								blackDecreaseValue(x + 1, y + 1);
								break;
							}
							x++;
							y++;
						}
						x = f - 1;
						y = r - 1;
						while (x >= 0 && y >= 0) {
							blackDecreaseValue(x, y);
							if (!"-bpqk".contains(board[x][y].piece)) {
								break;
							}
							if ("pk".contains(board[x][y].piece)) {
								blackDecreaseValue(x - 1, y - 1);
								break;
							}
							x--;
							y--;
						}
						x = f + 1;
						y = r - 1;
						while (x < 8 && y >= 0) {
							blackDecreaseValue(x, y);
							if (!"-bpqk".contains(board[x][y].piece)) {
								break;
							}
							if ("pk".contains(board[x][y].piece)) {
								blackDecreaseValue(x + 1, y - 1);
								break;
							}
							x++;
							y--;
						}
						x = f - 1;
						y = r + 1;
						while (x >= 0 && y < 8) {
							blackDecreaseValue(x, y);
							if (!"-bqk".contains(board[x][y].piece)) {
								break;
							}
							if ("k".contains(board[x][y].piece)) {
								blackDecreaseValue(x - 1, y + 1);
								break;
							}
							x--;
							y++;
						}
						break;
					case "Q":
						// Rook + Bishop?
						x = f + 1;
						while (x < 8) {
							whiteIncreaseValue(x, r);
							if (!"-KQR".contains(board[x][r].piece)) {
								break;
							}
							if ("K".contains(board[x][r].piece)) {
								whiteIncreaseValue(x + 1, r);
								break;
							}
							x++;
						}
						x = f - 1;
						while (x >= 0) {
							whiteIncreaseValue(x, r);
							if (!"-KQR".contains(board[x][r].piece)) {
								break;
							}
							if ("K".contains(board[x][r].piece)) {
								whiteIncreaseValue(x - 1, r);
								break;
							}
							x--;
						}
						// Vertical
						y = r + 1;
						while (y < 8) {
							whiteIncreaseValue(f, y);
							if (!"-KQR".contains(board[f][y].piece)) {
								break;
							}
							if ("K".contains(board[f][y].piece)) {
								whiteIncreaseValue(f, y + 1);
								break;
							}
							y++;
						}
						y = r - 1;
						while (y >= 0) {
							whiteIncreaseValue(f, y);
							if (!"-KQR".contains(board[f][y].piece)) {
								break;
							}
							if ("K".contains(board[f][y].piece)) {
								whiteIncreaseValue(f, y - 1);
								break;
							}
							y--;
						}
						x = f + 1;
						y = r + 1;
						while (x < 8 && y < 8) {
							whiteIncreaseValue(x, y);
							if (!"-KQBP".contains(board[x][y].piece)) {
								break;
							}
							if ("KP".contains(board[x][y].piece)) {
								whiteIncreaseValue(x + 1, y + 1);
								break;
							}
							x++;
							y++;
						}
						x = f - 1;
						y = r - 1;
						while (x >= 0 && y >= 0) {
							whiteIncreaseValue(x, y);
							if (!"-KQB".contains(board[x][y].piece)) {
								break;
							}
							if ("K".contains(board[x][y].piece)) {
								whiteIncreaseValue(x - 1, y - 1);
								break;
							}
							x--;
							y--;
						}
						x = f + 1;
						y = r - 1;
						while (x < 8 && y >= 0) {
							whiteIncreaseValue(x, y);
							if (!"-KQB".contains(board[x][y].piece)) {
								break;
							}
							if ("K".contains(board[x][y].piece)) {
								whiteIncreaseValue(x + 1, y - 1);
								break;
							}
							x++;
							y--;
						}
						x = f - 1;
						y = r + 1;
						while (x >= 0 && y < 8) {
							whiteIncreaseValue(x, y);
							if (!"-KQBP".contains(board[x][y].piece)) {
								break;
							}
							if ("KP".contains(board[x][y].piece)) {
								whiteIncreaseValue(x - 1, y + 1);
								break;
							}
							x--;
							y++;
						}
						break;
					case "q":
						x = f + 1;
						while (x < 8) {
							blackDecreaseValue(x, r);
							if (!"-kqr".contains(board[x][r].piece)) {
								break;
							}
							if ("k".contains(board[x][r].piece)) {
								blackDecreaseValue(x + 1, r);
								break;
							}
							x++;
						}
						x = f - 1;
						while (x >= 0) {
							blackDecreaseValue(x, r);
							if (!"-kqr".contains(board[x][r].piece)) {
								break;
							}
							if ("k".contains(board[x][r].piece)) {
								blackDecreaseValue(x - 1, r);
								break;
							}
							x--;
						}
						// Vertical
						y = r + 1;
						while (y < 8) {
							blackDecreaseValue(f, y);
							if (!"-kqr".contains(board[f][y].piece)) {
								break;
							}
							if ("k".contains(board[f][y].piece)) {
								blackDecreaseValue(f, y + 1);
								break;
							}
							y++;
						}
						y = r - 1;
						while (y >= 0) {
							blackDecreaseValue(f, y);
							if (!"-kqr".contains(board[f][y].piece)) {
								break;
							}
							if ("k".contains(board[f][y].piece)) {
								blackDecreaseValue(f, y - 1);
								break;
							}
							y--;
						}
						x = f + 1;
						y = r + 1;
						while (x < 8 && y < 8) {
							blackDecreaseValue(x, y);
							if (!"-kqb".contains(board[x][y].piece)) {
								break;
							}
							if ("k".contains(board[x][y].piece)) {
								blackDecreaseValue(x + 1, y + 1);
								break;
							}
							x++;
							y++;
						}
						x = f - 1;
						y = r - 1;
						while (x >= 0 && y >= 0) {
							blackDecreaseValue(x, y);
							if (!"-kqbp".contains(board[x][y].piece)) {
								break;
							}
							if ("kp".contains(board[x][y].piece)) {
								blackDecreaseValue(x - 1, y - 1);
								break;
							}
							x--;
							y--;
						}
						x = f + 1;
						y = r - 1;
						while (x < 8 && y >= 0) {
							blackDecreaseValue(x, y);
							if (!"-kqbp".contains(board[x][y].piece)) {
								break;
							}
							if ("kp".contains(board[x][y].piece)) {
								blackDecreaseValue(x + 1, y - 1);
								break;
							}
							x++;
							y--;
						}
						x = f - 1;
						y = r + 1;
						while (x >= 0 && y < 8) {
							blackDecreaseValue(x, y);
							if (!"-kqb".contains(board[x][y].piece)) {
								break;
							}
							if ("k".contains(board[x][y].piece)) {
								blackDecreaseValue(x - 1, y + 1);
								break;
							}
							x--;
							y++;
						}
						break;
					case "K":
						for (int dx = -1; dx < 2; dx++) {
							for (int dy = -1; dy < 2; dy++) {
								if (Math.abs(dx) + Math.abs(dy) != 0) {
									x = f + dx;
									y = r + dy;
									if (x >= 0 && x < 8 && y >= 0 && y < 8) {
										whiteIncreaseValue(x, y);
									}
								}
							}
						}
						break;
					case "k":
						for (int dx = -1; dx < 2; dx++) {
							for (int dy = -1; dy < 2; dy++) {
								if (Math.abs(dx) + Math.abs(dy) != 0) {
									x = f + dx;
									y = r + dy;
									if (x >= 0 && x < 8 && y >= 0 && y < 8) {
										blackDecreaseValue(x, y);
									}
								}
							}
						}
						break;
					case "P":
						if (r + 1 < 8 && f > 0) {
							whiteIncreaseValue(f - 1, r + 1);
						}
						if (r + 1 < 8 && f < 7) {
							whiteIncreaseValue(f + 1, r + 1);
						}
						break;
					case "p":
						if (r - 1 >= 0 && f > 0) {
							blackDecreaseValue(f - 1, r - 1);
						}
						if (r - 1 >= 0 && f < 7) {
							blackDecreaseValue(f + 1, r - 1);
						}
						break;
					default:
						break;
				}
			}
		}
	}

	public Square getSquare(String sq) {
		if (sq.length() == 2) {
			char f = sq.charAt(0);
			char r = sq.charAt(1);
			int file = files.indexOf(f);
			int row = rows.indexOf(r);
			return board[file][row];
		}
		return null;
	}

	public void emptyBoard() {
		board = new Square[8][8];
	}

	public void printBoardNames() {
		System.out.println("=================================");
		for (int i = 7; i >= 0; i--) {
			for (int j = 0; j < 8; j++) {
				System.out.print("|" + board[j][i].name + " ");
			}
			System.out.println("|");
		}
		System.out.println("=================================");
	}

	public void printBoardValues() {
		System.out.println("=================================");
		for (int i = 7; i >= 0; i--) {
			for (int j = 0; j < 8; j++) {
				System.out.format("|%2d ", board[j][i].value);
			}
			System.out.println("|");
		}
		System.out.println("=================================");
	}

	public void printBoard() {
		System.out.println("=================================");
		for (int i = 7; i >= 0; i--) {
			for (int j = 0; j < 8; j++) {
				System.out.print("| " + board[j][i].piece + " ");
			}
			System.out.println("|");
		}
		System.out.println("=================================");
	}

	public void setupBoardFen(String fen) {
		// Example fen rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0
		// 1
		emptyBoard();
		String[] fena = fen.trim().split(" ");
		String[] rank = fena[0].trim().split("/");
		int row = 8;
		for (String r1 : rank) {
			row--;
			int pos = 0;
			// System.out.println(r1);
			String[] r = r1.trim().split("");
			for (String e : r) {
				if (e.length() > 0) {
					if (rows.indexOf(e) == -1) {
						// Piece
						board[pos++][row] = new Square(e);
					} else {
						// Empty Space Rep
						for (int i = 0; i < Integer.parseInt(e); i++) {
							board[pos++][row] = new Square();
						}
					}
				}
			}
		}
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board[j][i].name = "" + files.substring(j, j + 1) + (i + 1);
			}
		}
	}

	public void move(int[] move) {
		for (int i = 0; i < move.length; i++) {
			if (move[i] < 0 || move[i] > 7) {
				return;
			}
		}
		board[move[2]][move[3]] = board[move[0]][move[1]];
		board[move[0]][move[1]] = new Square();
	}
}
