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

		for (int i = 1; i <= 6; i++) {
			if (i == 3) {
				chess1.add(new KingPiece(i, 1, 1));
			} else {
				Random rand = new Random();
				int random = rand.nextInt(4) + 1;
				if (random == 1) {
					chess1.add(new BishopPiece(i, 1, 1));
				} else if (random == 2) {
					chess1.add(new QueenPiece(i, 1, 1));
				} else if (random == 3) {
					chess1.add(new KnightPiece(i, 1, 1));
				} else if (random == 4) {
					chess1.add(new RookPiece(i, 1, 1));
				}
			}
			chess1.add(new PawnPiece(i, 2, 1));
		}

		for (int i = 1; i <= 6; i++) {
			if (i == 3) {
				chess2.add(new KingPiece(i, 6, 2));
			} else {
				Random rand = new Random();
				int random = rand.nextInt(4) + 1;
				if (random == 1) {
					chess2.add(new BishopPiece(i, 6, 2));
				} else if (random == 2) {
					chess2.add(new QueenPiece(i, 6, 2));
				} else if (random == 3) {
					chess2.add(new KnightPiece(i, 6, 2));
				} else if (random == 4) {
					chess2.add(new RookPiece(i, 6, 2));
				}
			}
			chess2.add(new PawnPiece(i, 5, 2));
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
