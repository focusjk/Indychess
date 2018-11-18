package component;

import java.util.ArrayList;

public class QueenPiece extends ChessPiece {

	public QueenPiece(String owner, ChessLocation chessLocation) {
		super(owner, chessLocation);
		if (owner.equalsIgnoreCase("player1")) {
			id = 'Q';
		} else if (owner.equalsIgnoreCase("player2")) {
			id = 'q';
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
		
		for (int i = -8; i <= 8; i++) {
			ChessLocation lo = new ChessLocation(currentCol + i, currentRow + i);
			if (lo.isOnBoard() && !lo.equals(chessLocation) && !possibleMoveList.contains(lo)) {
				possibleMoveList.add(lo);
			}
			lo = new ChessLocation(currentCol - i, currentRow + i);
			if (lo.isOnBoard() && !lo.equals(chessLocation) && !possibleMoveList.contains(lo)) {
				possibleMoveList.add(lo);
			}
		}


		return possibleMoveList;
	}

}
