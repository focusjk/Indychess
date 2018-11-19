package component;

import java.util.ArrayList;

public class PawnPiece extends ChessPiece {

	public PawnPiece(String owner, ChessLocation chessLocation) {
		super(owner, chessLocation);
		if (owner.equalsIgnoreCase("player1")) {
			id = 'P';
		} else if (owner.equalsIgnoreCase("player2")) {
			id = 'p';
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
		ChessLocation lo;
		int row = 1;
		if (owner == "player2")
			row = -1;

		for (int col = -1; col <= 1; col++) {
			lo = new ChessLocation(currentCol + col, currentRow + row);
			if (lo.isOnBoard() && !lo.equals(chessLocation) && !possibleMoveList.contains(lo)) {
				possibleMoveList.add(lo);
			}
		}
		return possibleMoveList;
	}

}
