package component;

public class ChessLocation {
	private int col;
	private int row;
	public static final String[] COL_CHAR = { "a", "b", "c", "d", "e", "f", "g", "h" };

	public ChessLocation() {
		col = 0;
		row = 0;

	}

	public ChessLocation(int col, int row) {
		this.col = col;
		this.row = row;

	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof ChessLocation) {
			ChessLocation l = (ChessLocation) obj;
			return (row == l.getRow() && col == l.getCol());
		}
		return false;
	}

	public boolean isOnBoard() {
		return (col >= 0 && row >= 0 && col <= ChessBoard.BOARD_SIZE - 1 && row <= ChessBoard.BOARD_SIZE - 1);
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public void setCol(int col) {
		this.col = col;
	}

	@Override
	public String toString() {
		return "(" + Integer.toString(col) + "," + Integer.toString(row) + ")";
	}
}
