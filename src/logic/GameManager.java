package logic;

import java.util.ArrayList;
import java.util.Random;

import chessPiece.BishopPiece;
import chessPiece.ChessPiece;
import chessPiece.KingPiece;
import chessPiece.KnightPiece;
import chessPiece.PawnPiece;
import chessPiece.QueenPiece;
import chessPiece.RookPiece;
import component.Button;
import component.PlayerProfile;
import component.Star;
import component.Timer;
import javafx.scene.image.ImageView;
import scene.Game;

public class GameManager {
	private ArrayList<ChessPiece> chess1;
	private ArrayList<ChessPiece> chess2;
	private PlayerProfile player1;
	private PlayerProfile player2;
	private Timer timer;
	private Game gameScreen;

	public GameManager(Game gameScreen) {
		super();
		this.gameScreen = gameScreen;
		this.player1 = gameScreen.getPlayer1();
		this.player2 = gameScreen.getPlayer2();
		this.timer = gameScreen.getTimer();

		initialChess();
//		initialStar();
		drawChess();
		gameScreen.addStar();
		this.player1.setTurn(true);
	}

	public void initialChess() {
		chess1 = new ArrayList<>();
		chess2 = new ArrayList<>();

		for (int j = 1; j <= 2; j++) {
			for (int i = 1; i <= 6; i++) {
				if (i == 4 && j == 1) {
					chess1.add(new KingPiece(i, j, 1));
				} else {
					Random rand = new Random();
					int random = rand.nextInt(10) + 1;
					if (random == 1) {
						chess1.add(new BishopPiece(i, j, 1));
					} else if (random == 2) {
						chess1.add(new QueenPiece(i, j, 1));
					} else if (random == 3) {
						chess1.add(new KnightPiece(i, j, 1));
					} else if (random == 4) {
						chess1.add(new RookPiece(i, j, 1));
					} else {
						chess1.add(new PawnPiece(i, j, 1));
					}
				}

			}
		}
		for (int j = 5; j <= 6; j++) {
			for (int i = 1; i <= 6; i++) {
				if (i == 4 && j == 6) {
					chess2.add(new KingPiece(i, j, 2));
				} else {
					Random rand = new Random();
					int random = rand.nextInt(10) + 1;
					if (random == 1) {
						chess2.add(new BishopPiece(i, j, 2));
					} else if (random == 2) {
						chess2.add(new QueenPiece(i, j, 2));
					} else if (random == 3) {
						chess2.add(new KnightPiece(i, j, 2));
					} else if (random == 4) {
						chess2.add(new RookPiece(i, j, 2));
					} else {
						chess2.add(new PawnPiece(i, j, 2));
					}
				}
			}
		}
	}

	public void drawChess() {
		for (int i = 0; i < chess1.size(); i++) {
			gameScreen.addChessPiece(chess1.get(i));
		}
		for (int i = 0; i < chess2.size(); i++) {
			gameScreen.addChessPiece(chess2.get(i));
		}
	}
}
