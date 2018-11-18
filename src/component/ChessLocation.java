package component;

public class ChessLocation {
	public static final int BOARD_SIZE = 6;
	private int col;
	private int row;
	public static final String[] COL_CHAR = {"x","a","b","c","d","e","f","g","h"};

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
		return (col >= 1 && row >= 1 && col <= BOARD_SIZE && row <= BOARD_SIZE);
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
		return "("+Integer.toString(col)+","+Integer.toString(row)+")";
	}
}
