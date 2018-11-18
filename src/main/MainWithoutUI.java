package main;

import java.util.Scanner;

import component.*;

public class MainWithoutUI {

	public static void main(String[] args) {
		ChessBoard chessBoard = new ChessBoard();
		boolean gameOver = false;
		String currentPlayer = "player1";
		Scanner sc = new Scanner(System.in);
		int col, row, toCol, toRow;

		System.out.println(chessBoard.toString());
		// Only for Correct row and col
		while (!gameOver) {
			while (true) {
				System.out.print("Select Chess (input col row EX. 1 2) : ");
				col = sc.nextInt();
				row = sc.nextInt();
				if (new ChessLocation(col, row).isOnBoard()) {
					break;
				}
				System.out.println("Invalid col or row");
			}

			ChessPiece selectPiece = chessBoard.getPieceAt(new ChessLocation(col, row));

			System.out.println(selectPiece.possibleMove());

			while (true) {
				System.out.print("Select Where to Move (input col row EX. 1 2) : ");
				toCol = sc.nextInt();
				toRow = sc.nextInt();
				if (new ChessLocation(col, row).isOnBoard()) {
					break;
				}
				System.out.println("Invalid col or row");
			}

			chessBoard.placePieceAt(selectPiece, new ChessLocation(toCol, toRow));

			System.out.println(chessBoard.toString());
			break;
		}
	}
}
