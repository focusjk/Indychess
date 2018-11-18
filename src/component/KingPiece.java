package component;

import java.util.ArrayList;

public class KingPiece extends ChessPiece {

	public KingPiece(String owner, ChessLocation chessLocation) {
		super(owner, chessLocation);
		if (owner.equalsIgnoreCase("player1")) {
			id = 'K';
		} else if (owner.equalsIgnoreCase("player2")) {
			id = 'k';
		}

	}

	@Override
	public boolean moveTo(ChessLocation chessLocation) {
		if (possibleMove().contains(chessLocation)) {
			this.chessLocation = chessLocation;
			return true;
		}
		return false;
	}

	@Override
	public ArrayList<ChessLocation> possibleMove() {
		ArrayList<ChessLocation> possibleMoveList = new ArrayList<ChessLocation>();
		int currentRow = chessLocation.getRow();
		int currentCol = chessLocation.getCol();

		for (int col = -1; col <= 1; col++) {
			for (int row = -1; row <= 1; row++) {
				ChessLocation lo = new ChessLocation(currentCol + col, currentRow + row);

				if (lo.isOnBoard() && !lo.equals(chessLocation) && !possibleMoveList.contains(lo)) {
					possibleMoveList.add(lo);
				}
			}
		}

		return possibleMoveList;
	}

}
