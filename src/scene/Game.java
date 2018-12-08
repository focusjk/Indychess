package scene;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import main.Main;

import java.util.ArrayList;

import chessPiece.ChessPiece;
import chessPiece.KingPiece;
import chessPiece.Movable;
import component.Button;
import component.CellBoard;
import component.PlayerProfile;
import component.Star;
import component.Timer;

public class Game extends Pane {
	private PlayerProfile player1;
	private PlayerProfile player2;
	private Timer timer;
	private Button pauseButton;
	private ResumeModal resumeModal = new ResumeModal();
	private StarModal starModal;
	private ArrayList<ChessPiece> chessPiece = new ArrayList<ChessPiece>();
	private CellBoard[][] board = new CellBoard[7][7];
	private ChessPiece clickedChess = null;
	private int turn = 1;
	private Star star = null;
	private boolean isEnd = false;
	private AudioClip eatSound = new AudioClip(ClassLoader.getSystemResource("sound/eatSound.mp3").toString());
	private AudioClip disableSound = new AudioClip(ClassLoader.getSystemResource("sound/cantMoveSound.mp3").toString());
	private AudioClip starSound = new AudioClip(ClassLoader.getSystemResource("sound/promotionSound.mp3").toString());

	public Game(String player1Name, String player2Name) {
		// set screen
		setPrefHeight(700);
		setPrefWidth(1000);
		setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));

		// set board
		for (int i = 1; i <= 6; i++) {
			for (int j = 1; j <= 6; j++) {
				board[i][j] = new CellBoard((double) i, (double) j);
				getChildren().add(board[i][j]);
			}
		}

		// set pause button
		pauseButton = new Button("images/pause", 920, 10) {
			@Override
			public void onClicked() {
				getChildren().add(resumeModal);
				timer.stop();
			}
		};

		// set player
		player1 = new PlayerProfile(player1Name, 1);
		player1.setTurn(true);
		player1.setMouseTransparent(true);
		player2 = new PlayerProfile(player2Name, 2);
		player2.setTurn(false);
		player2.setMouseTransparent(true);

		// set timer
		timer = new Timer(player1, player2);
		timer.setLayoutX(400);
		timer.setLayoutY(15);
		timer.start();

		getChildren().addAll(pauseButton, timer, player1, player2);

		// set mouse
		setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				removeResumeModal();

				Object o = event.getTarget();
				if (o instanceof ChessPiece) {
					double x = ((ChessPiece) o).getX();
					double y = ((ChessPiece) o).getY();

					if (((ChessPiece) o).getPlayer() == turn) {
						((Movable) o).onClicked();
					} else if (clickedChess != null && ((ChessPiece) o).getPlayer() != turn
							&& board[(int) x][(int) y].isActive()) {
						removeChessPiece((ChessPiece) o);
						((Movable) clickedChess).onMove(x, y);
						changeTurn();
						eatSound.play();
					} else {
						disableSound.play();
					}
				} else if (o instanceof CellBoard && clickedChess != null) {
					double x = ((CellBoard) o).getPositionX();
					double y = ((CellBoard) o).getPositionY();
					if (star.getX() == x && star.getY() == y) {
						starSound.play();
						starModal = new StarModal(clickedChess, Main.getGameScreen());
						getChildren().add(starModal);
						timer.stop();
					}
					((Movable) clickedChess).onMove(x, y);
					changeTurn();
					eatSound.play();
				}
				for (int i = 0; i < chessPiece.size(); i++) {
					chessPiece.get(i).setImage(1);
				}

				if (!isEnd) {
					boolean isWin1 = true;
					boolean isWin2 = true;
					for (int i = 0; i < chessPiece.size(); i++) {
						if (chessPiece.get(i) instanceof KingPiece) {
							if (chessPiece.get(i).getPlayer() == 2)
								isWin1 = false;
							else
								isWin2 = false;
						}
					}

					if (isWin1 && !isWin2) {
						endGame(1);
					} else if (!isWin1 && isWin2) {
						endGame(2);
					} else {
						boolean canMove = false;

						for (int i = 0; i < chessPiece.size(); i++) {
							if (chessPiece.get(i).getPlayer() == turn && chessPiece.get(i).isMovable()) {
								canMove = true;
								break;
							}
						}
						if (!canMove)
							endGame(3);
					}
				}

				event.consume();
			}
		});

	}

	public PlayerProfile getPlayer1() {
		return player1;
	}

	public void setPlayer1(PlayerProfile player1) {
		this.player1 = player1;
	}

	public PlayerProfile getPlayer2() {
		return player2;
	}

	public void setPlayer2(PlayerProfile player2) {
		this.player2 = player2;
	}

	public Button getPauseButton() {
		return pauseButton;
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public int getTurn() {
		return turn;
	}

	public void addChessPiece(ChessPiece e) {
		chessPiece.add(e);
		getChildren().add(e);
	}

	public void removeChessPiece(ChessPiece e) {
		chessPiece.remove(e);
		getChildren().remove(e);
	}

	public void addStar() {
		if (getChildren().contains(star))
			getChildren().remove(star);
		this.star = new Star(chessPiece);
		this.star.setMouseTransparent(true);
		getChildren().add(this.star);
	}

	public CellBoard[][] getBoard() {
		return board;
	}

	public void resetBoard() {
		for (int i = 1; i <= 6; i++) {
			for (int j = 1; j <= 6; j++) {
				board[i][j].inactive();
			}
		}
	}

	public ChessPiece findChessPiece(int x, int y) {
		for (int i = 0; i < chessPiece.size(); i++) {
			if (chessPiece.get(i).getX() == (double) x && chessPiece.get(i).getY() == (double) y)
				return chessPiece.get(i);
		}
		return null;
	}

	public void changeTurn() {
		if (turn == 1) {
			turn = 2;
			player1.setTurn(false);
			player2.setTurn(true);
		} else {
			turn = 1;
			player2.setTurn(false);
			player1.setTurn(true);
		}
		timer.resetTime();
		resetBoard();
		setClickedChess(null);
	}

	public ChessPiece getClickedChess() {
		return clickedChess;
	}

	public void setClickedChess(ChessPiece clickedChess) {
		this.clickedChess = clickedChess;
	}

	public void removeStarModal() {
		if (getChildren().contains(starModal)) {
			getChildren().remove(starModal);
			timer.start();
		}
	}

	public void removeResumeModal() {
		if (getChildren().contains(resumeModal)) {
			getChildren().remove(resumeModal);
			timer.start();
		}
	}

	public ArrayList<ChessPiece> getChessPiece() {
		return chessPiece;
	}

	public Star getStar() {
		return star;
	}

	private void endGame(int type) {
		isEnd = true;
		timer.stop();
		player1.stopThread();
		player2.stopThread();
		if (type == 1)
			getChildren().add(new CongratModal(player1.getName(), "winner"));
		else if (type == 2)
			getChildren().add(new CongratModal(player2.getName(), "winner"));
		else
			getChildren().add(new CongratModal(player1.getName() + "\n & \n" + player2.getName(), "draw"));
	}

}
