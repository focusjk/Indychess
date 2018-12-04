package scene;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;

import java.util.ArrayList;

import chessPiece.ChessPiece;
import chessPiece.KingPiece;
import chessPiece.PawnPiece;
import component.Button;
import component.CellBoard;
import component.Clickable;
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
	private static ChessPiece clickedChess = null;
	private int turn = 1;
	private Star star = null;
	private boolean isEnd = false;
	private AudioClip selectSound = new AudioClip(ClassLoader.getSystemResource("sound/selectSound.mp3").toString());
	private AudioClip eatSound = new AudioClip(ClassLoader.getSystemResource("sound/eatSound.mp3").toString());
	private AudioClip disableSound = new AudioClip(ClassLoader.getSystemResource("sound/cantMoveSound.mp3").toString());

	public Game() {
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
				playCheckSound();
				getChildren().add(resumeModal);
				timer.stop();
			}
		};

		// set player
		player1 = new PlayerProfile("anonymous", 1);
		player1.setMouseTransparent(true);
		player2 = new PlayerProfile("anonymous", 2);
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
				System.out.println(event.toString());
				if (getChildren().contains(resumeModal)) {
					getChildren().remove(resumeModal);
					timer.start();
				}

				Object o = event.getTarget();
				if (o instanceof ChessPiece) {
					double x = ((ChessPiece) o).getX();
					double y = ((ChessPiece) o).getY();

					if (((ChessPiece) o).getPlayer() == turn) {
						((Clickable) o).onClicked();
						selectSound.play();
					} else if (clickedChess != null && ((ChessPiece) o).getPlayer() != turn
							&& board[(int) x][(int) y].isActive()) {
						if (clickedChess instanceof PawnPiece) {
							((PawnPiece) clickedChess).setIsFirstMove();
							((PawnPiece) clickedChess).setQueen((int) y);
						}
						resetBoard();
						removeChessPiece((ChessPiece) o);
						clickedChess.setPosition(x, y);
						changeTurn();
						eatSound.play();
					} else {
						disableSound.play();
					}
				} else if (o instanceof CellBoard && clickedChess != null) {
					double x = ((CellBoard) o).getPositionX();
					double y = ((CellBoard) o).getPositionY();

					if (clickedChess instanceof PawnPiece) {
						((PawnPiece) clickedChess).setIsFirstMove();
						((PawnPiece) clickedChess).setQueen((int) y);
					}

					if (star.getX() == x && star.getY() == y) {
						starModal = new StarModal(clickedChess);
						getChildren().add(starModal);
					}
					clickedChess.setPosition(x, y);
					resetBoard();
					changeTurn();
					eatSound.play();
				} else if (o instanceof Clickable) {
					((Clickable) o).onClicked();
					selectSound.play();
				}
				for (int i = 0; i < chessPiece.size(); i++) {
					chessPiece.get(i).setImg(chessPiece.get(i).getImageName());
				}

				if (!isEnd) {
					boolean isWin1 = true;
					boolean isWin2 = true;
					for (int i = 0; i < chessPiece.size(); i++) {
						if (chessPiece.get(i) instanceof KingPiece) {
							if (chessPiece.get(i).getPlayer() == 2) {
								isWin1 = false;
							} else {
								isWin2 = false;
							}
						}
					}
					if (isWin1 && !isWin2) {
						isEnd = true;
						getChildren().add(new CongratModal(player1.getName()));
					} else if (!isWin1 && isWin2) {
						isEnd = true;
						getChildren().add(new CongratModal(player2.getName()));
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
		this.star = new Star();
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
			player1.setTurn(true);
			player2.setTurn(false);
		}
		timer.reset();

	}

	public ChessPiece getClickedChess() {
		return clickedChess;
	}

	public void setClickedChess(ChessPiece clickedChess) {
		this.clickedChess = clickedChess;
	}

	public void removeStarModal() {
		if (getChildren().contains(starModal))
			getChildren().remove(starModal);
	}

	public ArrayList<ChessPiece> getChessPiece() {
		return chessPiece;
	}

	public Star getStar() {
		return star;
	}

}
