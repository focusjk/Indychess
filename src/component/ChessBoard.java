package component;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ChessBoard {
	public static final int BOARD_SIZE = 6;
	private ChessPiece[][] board;

	public ChessBoard() {
		board = new ChessPiece[BOARD_SIZE][BOARD_SIZE];
		String owner;
		ChessLocation chessLocation = new ChessLocation(2, 2);
		// player1
		owner = "player1";

		ChessPiece[] pieceList1 = { new KingPiece(owner, chessLocation), new QueenPiece(owner, chessLocation),
				new BishopPiece(owner, chessLocation), new KnightPiece(owner, chessLocation),
				new RookPiece(owner, chessLocation), new PawnPiece(owner, chessLocation) };
		shuffleArray(pieceList1);
		for (int i = 0; i < pieceList1.length; i++) {
			setNewPiece(pieceList1[i], new ChessLocation(i, 0));
		}
		// player2
		owner = "player2";

		ChessPiece[] pieceList2 = { new KingPiece(owner, chessLocation), new QueenPiece(owner, chessLocation),
				new BishopPiece(owner, chessLocation), new KnightPiece(owner, chessLocation),
				new RookPiece(owner, chessLocation), new PawnPiece(owner, chessLocation) };
		shuffleArray(pieceList2);
		for (int i = 0; i < pieceList2.length; i++) {
			setNewPiece(pieceList2[i], new ChessLocation(i, 5));
		}
	}

	public ArrayList<ChessLocation> canMoveTo(ChessPiece piece) {
		ArrayList<ChessLocation> canMoveToList = new ArrayList<ChessLocation>();
		ArrayList<ChessLocation> possibleMoveList = piece.possibleMove();
		for (ChessLocation lo : possibleMoveList) {
			//System.out.println(board[lo.getCol()][lo.getRow()]);
			if (!isPieceAt(lo.getCol(), lo.getRow())) {
				canMoveToList.add(lo);
			} else if (getPieceAt(lo).getOwner() != piece.getOwner()) {
				canMoveToList.add(lo);
			}
		}
		return canMoveToList;
	}

	public boolean isPieceAt(int col, int row) {
		return board[col][row] != null;
	}
	
	public void setNewPiece(ChessPiece piece, ChessLocation location) {
		board[location.getCol()][location.getRow()] = piece;
		piece.setChessLocation(location);
	}

	public void placePieceAt(ChessPiece piece, ChessLocation location) {
		if (isPieceAt(location.getCol(), location.getRow())) {
			removePieceAt(location);
		}
		if (piece.getChessLocation() != null) {
			removePieceAt(piece.getChessLocation());
		}
		board[location.getCol()][location.getRow()] = piece;
		piece.setChessLocation(location);
	}

	private void removePieceAt(ChessLocation location) {
		board[location.getCol()][location.getRow()] = null;
	}

	public ChessPiece getPieceAt(ChessLocation location) {
		return board[location.getCol()][location.getRow()];
	}

	public void shuffleArray(ChessPiece[] ar) {
		Random rnd = ThreadLocalRandom.current();
		for (int i = ar.length - 1; i > 0; i--) {
			int index = rnd.nextInt(i + 1);
			ChessPiece a = ar[index];
			ar[index] = ar[i];
			ar[i] = a;
		}
	}

	@Override
	public String toString() {
		String s = "";
		for (int row = BOARD_SIZE - 1; row >= 0; row--) {
			s += row;
			for (int col = 0; col < BOARD_SIZE; col++) {
				if (board[col][row] != null) {
					s += " " + board[col][row].getId();
				} else {
					s += " -";
				}
			}
			s += "\n";
		}
		s += " ";
		for (int col = 0; col < BOARD_SIZE; col++) {
			s += " " + Integer.toString(col);
		}
		s += "\n";
		return s;
	}
}
