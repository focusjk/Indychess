package component;

import java.util.ArrayList;

public class RookPiece extends ChessPiece {

	public RookPiece(String owner, ChessLocation chessLocation) {
		super(owner, chessLocation);
		if (owner.equalsIgnoreCase("player1")) {
			id = 'R';
		} else if (owner.equalsIgnoreCase("player2")) {
			id = 'r';
		}

	}

	@Override
	public ArrayList<ChessLocation> possibleMove() {
		ArrayList<ChessLocation> possibleMoveList = new ArrayList<ChessLocation>();
		int currentRow = chessLocation.getRow();
		int currentCol = chessLocation.getCol();

		for (int row = 1; row <= 8; row++) {
			ChessLocation lo = new ChessLocation(currentCol, row);
			if (lo.isOnBoard() && !lo.equals(chessLocation) && !possibleMoveList.contains(lo)) {
				possibleMoveList.add(lo);
			}
		}

		for (int col = 1; col <= 8; col++) {
			ChessLocation lo = new ChessLocation(col, currentRow);
			if (lo.isOnBoard() && !lo.equals(chessLocation) && !possibleMoveList.contains(lo)) {
				possibleMoveList.add(lo);
			}
		}

		return possibleMoveList;
	}

}