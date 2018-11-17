package logic;

import java.util.ArrayList;
import java.util.Random;

import component.BishopPiece;
import component.ChessPiece;
import component.KingPiece;
import component.KnightPiece;
import component.PawnPiece;
import component.PlayerProfile;
import component.QueenPiece;
import component.RookPiece;
import component.Star;
import component.Timer;
import javafx.scene.image.ImageView;
import scene.Game;

public class GameManager extends Manager {
	private ArrayList<ChessPiece> chess1;
	private ArrayList<ChessPiece> chess2;
	private Star star;
	private PlayerProfile player1;
	private PlayerProfile player2;
	private ImageView pauseButton;
	private Timer timer;
	private Game gameScreen;

	public GameManager(Game gameScreen) {
		super();
		this.gameScreen = gameScreen;
		this.player1 = gameScreen.getPlayer1();
		this.player2 = gameScreen.getPlayer2();
		setPauseButton(gameScreen.getPauseButton());
		this.timer = gameScreen.getTimer();

		initialChess();
		drawChess();
		initialStar();
		drawStar();
		this.player1.setTurn(true);
	}

	public void initialChess() {
		chess1 = new ArrayList<>();
		chess2 = new ArrayList<>();

		for (int i = 1; i <= 6; i++) {
			if (i == 3) {
				chess1.add(new KingPiece(1, i, 1));
			} else {
				Random rand = new Random();
				int random = rand.nextInt(5) + 1;
				if (random == 1) {
					chess1.add(new BishopPiece(1, i, 1));
				} else if (random == 2) {
					chess1.add(new QueenPiece(1, i, 1));
				} else if (random == 3) {
					chess1.add(new KnightPiece(1, i, 1));
				} else if (random == 4) {
					chess1.add(new PawnPiece(1, i, 1));
				} else if (random == 5) {
					chess1.add(new RookPiece(1, i, 1));
				}
			}
		}

		for (int i = 1; i <= 6; i++) {
			if (i == 3) {
				chess2.add(new KingPiece(6, i, 2));
			} else {
				Random rand = new Random();
				int random = rand.nextInt(5) + 1;
				if (random == 1) {
					chess2.add(new BishopPiece(6, i, 2));
				} else if (random == 2) {
					chess2.add(new QueenPiece(6, i, 2));
				} else if (random == 3) {
					chess2.add(new KnightPiece(6, i, 2));
				} else if (random == 4) {
					chess2.add(new PawnPiece(6, i, 2));
				} else if (random == 5) {
					chess2.add(new RookPiece(6, i, 2));
				}
			}
		}
	}

	public void drawChess() {
		for (int i = 0; i < 6; i++) {
			gameScreen.addChessPiece(chess1.get(i));
		}
		for (int i = 0; i < 6; i++) {
			gameScreen.addChessPiece(chess2.get(i));
		}
	}

	public void initialStar() {
		int x, y;
		while (true) {
			Random rand1 = new Random();
			Random rand2 = new Random();
			x = rand1.nextInt(5) + 1;
			y = rand2.nextInt(5) + 1;

			boolean t = true;
			for (int i = 0; i < 6; i++) {
				if (chess1.get(i).getX() == x && chess1.get(i).getY() == y) {
					t = false;
					break;
				}
			}
			for (int i = 0; i < 6; i++) {
				if (chess2.get(i).getX() == x && chess2.get(i).getY() == y) {
					t = false;
					break;
				}
			}
			if (t)
				break;
		}
		star = new Star(x, y);
	}

	public void drawStar() {
		gameScreen.addStar(star);
	}

	public void setPauseButton(ImageView pauseButton) {
		this.pauseButton = pauseButton;
		// TODO
	}

}
