package chess;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import component.*;

/**
 * The ChessGame class is used for holding chess objects which contains the
 * board, pieces and handles alternating turns.
 */
public class ChessGame {

	private ChessBoard chessBoard;
	private King player1King;
	private King player2King;

	/**
	 * Create new instances of nessary properties.
	 */
	public ChessGame() {
		chessBoard = new ChessBoard();
		setupTeam(0, "player1");
		setupTeam(ChessBoard.BOARD_SIZE - 1, "player2");
	}

	/**
	 * Sets up pieces for each player.
	 * 
	 * @param side   Starting side of the player
	 * @param player String of the player
	 */
	private void setupTeam(int side, String player) {
		int[] ar = { 0, 1, 2, 3, 4, 5 };
		shuffleArray(ar);

		ChessPiece r1 = new Rook(player, new ChessLocation(side, ar[0]), this);

		ChessPiece n1 = new Knight(player, new ChessLocation(side, ar[1]), this);

		ChessPiece b1 = new Bishop(player, new ChessLocation(side, ar[2]), this);

		ChessPiece q = new Queen(player, new ChessLocation(side, ar[3]), this);

		ChessPiece p = new Pawn(player, new ChessLocation(side, ar[4]), this);

		if (player.equalsIgnoreCase("player1")) {
			player1King = new King(player, new ChessLocation(side, ar[5]), this);
		} else {
			player2King = new King(player, new ChessLocation(side, ar[5]), this);
		}
	}

	public void shuffleArray(int[] ar) {
		Random rnd = ThreadLocalRandom.current();
		for (int i = ar.length - 1; i > 0; i--) {
			int index = rnd.nextInt(i + 1);
			// Simple swap
			int a = ar[index];
			ar[index] = ar[i];
			ar[i] = a;
		}
	}

	/**
	 * Returns the ChessBoard.
	 * 
	 * @return The board object of the chess game.
	 */
	public ChessBoard getChessBoard() {
		return chessBoard;
	}

	public King getPlayer1King() {
		return player1King;
	}

	public King getPlayer2King() {
		return player2King;
	}
}
