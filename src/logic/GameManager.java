package logic;

import java.util.ArrayList;
import java.util.Random;

import chessPiece.ChessPiece;
import chessPiece.BishopPiece;
import chessPiece.KingPiece;
import chessPiece.KnightPiece;
import chessPiece.PawnPiece;
import chessPiece.QueenPiece;
import chessPiece.RookPiece;
import scene.Game;

public class GameManager {
	private ArrayList<ChessPiece> chessPiece;
	private Game gameScreen;

	public GameManager(Game gameScreen) {
		super();
		this.gameScreen = gameScreen;
			
		initialChess();
		gameScreen.addStar();
	}
	
	public void initialChess() {
		chessPiece = new ArrayList<>();
		for (int j = 1; j <= 2; j++) {
			for (int i = 1; i <= 6; i++) {
				if (i == 4 && j == 1) {
					chessPiece.add(new KingPiece(i, j, 1));
					gameScreen.addChessPiece(new KingPiece(i, j, 1));
				} else {
					Random rand = new Random();
					int random = rand.nextInt(10) + 1;
					if (random == 1) {
						chessPiece.add(new BishopPiece(i, j, 1));
						gameScreen.addChessPiece(new BishopPiece(i, j, 1));
					} else if (random == 2) {
						chessPiece.add(new QueenPiece(i, j, 1));
						gameScreen.addChessPiece(new QueenPiece(i, j, 1));
					} else if (random == 3) {
						chessPiece.add(new KnightPiece(i, j, 1));
						gameScreen.addChessPiece(new KnightPiece(i, j, 1));
					} else if (random == 4) {
						chessPiece.add(new RookPiece(i, j, 1));
						gameScreen.addChessPiece(new RookPiece(i, j, 1));
					} else {
						chessPiece.add(new PawnPiece(i, j, 1));
						gameScreen.addChessPiece(new PawnPiece(i, j, 1));
					}
				}
			}
		}
		for (int j = 5; j <= 6; j++) {
			for (int i = 1; i <= 6; i++) {
				if (i == 4 && j == 6) {
					chessPiece.add(new KingPiece(i, j, 2));
					gameScreen.addChessPiece(new KingPiece(i, j, 2));
				} else {
					Random rand = new Random();
					int random = rand.nextInt(10) + 1;
					if (random == 1) {
						chessPiece.add(new BishopPiece(i, j, 2));
						gameScreen.addChessPiece(new BishopPiece(i, j, 2));
					} else if (random == 2) {
						chessPiece.add(new QueenPiece(i, j, 2));
						gameScreen.addChessPiece(new QueenPiece(i, j, 2));
					} else if (random == 3) {
						chessPiece.add(new KnightPiece(i, j, 2));
						gameScreen.addChessPiece(new KnightPiece(i, j, 2));
					} else if (random == 4) {
						chessPiece.add(new RookPiece(i, j, 2));
						gameScreen.addChessPiece(new RookPiece(i, j, 2));
					} else {
						chessPiece.add(new PawnPiece(i, j, 2));
						gameScreen.addChessPiece(new PawnPiece(i, j, 2));
					}
				}
			}
		}
	}

	
}
