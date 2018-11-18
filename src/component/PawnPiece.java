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

		ChessLocation lo = new ChessLocation(currentRow + 1, currentCol);

		if (lo.isOnBoard() && !lo.equals(chessLocation) && !possibleMoveList.contains(lo)) {
			possibleMoveList.add(lo);
		}
		return possibleMoveList;
	}

}
