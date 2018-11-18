package component;

public class ChessLocation {
	public static final int BOARD_SIZE = 6;
	private int row;
	private int col;
	public static final String[] COL_CHAR = {"X","A","B","C","D","E","F","G","H"};

	public ChessLocation() {
		row = 0;
		col = 0;
	}

	public ChessLocation(int row, int col) {
		this.row = row;
		this.col = col;
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
		return (row >= 1 && col >= 1 && row <= BOARD_SIZE && col <= BOARD_SIZE);
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
		return "("+Integer.toString(row)+","+Integer.toString(col)+")";
	}
}
