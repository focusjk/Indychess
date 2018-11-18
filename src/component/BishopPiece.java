package component;

import java.util.ArrayList;

public class BishopPiece extends ChessPiece {

	public BishopPiece(String owner, ChessLocation chessLocation) {
		super(owner, chessLocation);
		if (owner.equalsIgnoreCase("player1")) {
			id = 'B';
		} else if (owner.equalsIgnoreCase("player2")) {
			id = 'b';
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

		for (int i = -8; i <= 8; i++) {
			ChessLocation lo = new ChessLocation(currentRow + i, currentCol + i);
			if (lo.isOnBoard() && !lo.equals(chessLocation) && !possibleMoveList.contains(lo)) {
				possibleMoveList.add(lo);
			}
			lo = new ChessLocation(currentRow - i, currentCol + i);
			if (lo.isOnBoard() && !lo.equals(chessLocation) && !possibleMoveList.contains(lo)) {
				possibleMoveList.add(lo);
			}
		}

		return possibleMoveList;
	}

}
