package component;

import java.util.ArrayList;

public class KnightPiece extends ChessPiece {

	public KnightPiece(String owner, ChessLocation chessLocation) {
		super(owner, chessLocation);
		if (owner.equalsIgnoreCase("player1")) {
			id = 'N';
		} else if (owner.equalsIgnoreCase("player2")) {
			id = 'n';
		}

	}

	@Override
	public ArrayList<ChessLocation> possibleMove() {
		ArrayList<ChessLocation> possibleMoveList = new ArrayList<ChessLocation>();
		int currentRow = chessLocation.getRow();
		int currentCol = chessLocation.getCol();

		int[] col = { 2, 2, 1, 1, -1, -1, -2, -2 };
		int[] row = { 1, -1, 2, -2, 2, -2, 1, -1 };
		for (int i = 0; i < 8; i++) {
			ChessLocation lo = new ChessLocation(currentCol + col[i], currentRow + row[i]);
			if (lo.isOnBoard() && !lo.equals(chessLocation) && !possibleMoveList.contains(lo)) {
				possibleMoveList.add(lo);
			}
		}
		return possibleMoveList;
	}

}
