package scene;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import main.Main;

import java.util.ArrayList;

import chessPiece.ChessPiece;
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
	private ArrayList<ChessPiece> chessPiece = new ArrayList<ChessPiece>();
	private CellBoard[][] board = new CellBoard[7][7];
	private ChessPiece clickedChess = null;
	private int turn = 1;
	private Star star;

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
				getChildren().add(resumeModal);

			}
		};

		// set player
		player1 = new PlayerProfile("anonymous", 1);
		player2 = new PlayerProfile("anonymous", 2);

		// set timer
		timer = new Timer(player1, player2);
		timer.setLayoutX(400);
		timer.setLayoutY(15);

		getChildren().addAll(pauseButton, timer, player1, player2);

		// set mouse
		setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				System.out.println(event.toString());
				if (getChildren().contains(resumeModal))
					getChildren().remove(resumeModal);
				Object o = event.getTarget();
				if (o instanceof ChessPiece) {
					double x = ((ChessPiece) o).getX();
					double y = ((ChessPiece) o).getY();

					if (((ChessPiece) o).getPlayer() == turn) {
						((Clickable) o).onClicked();
					} else if (chessPiece.get(0).getClickedPiece() != null && ((ChessPiece) o).getPlayer() != turn) {
						if (chessPiece.get(0).getClickedPiece() instanceof PawnPiece) {
							((PawnPiece) chessPiece.get(0).getClickedPiece()).setIsFirstMove();
						}
						resetBoard();
						getChildren().remove(o);
						chessPiece.remove(o);
						chessPiece.get(0).getClickedPiece().setPosition(x, y);
						changeTurn();
					}
				} else if (o instanceof CellBoard && chessPiece.get(0).getClickedPiece() != null) {
					if (chessPiece.get(0).getClickedPiece() instanceof PawnPiece) {
						((PawnPiece) chessPiece.get(0).getClickedPiece()).setIsFirstMove();
					}
					double x = ((CellBoard) o).getPositionX();
					double y = ((CellBoard) o).getPositionY();
					chessPiece.get(0).getClickedPiece().setPosition(x, y);
					if (star.getX() == x && star.getY() == y) {
						Main.getGameManager().initialStar();
					}
					resetBoard();
					changeTurn();
				} else if (o instanceof Clickable) {
					((Clickable) o).onClicked();
				}
				for (int i = 0; i < chessPiece.size(); i++) {
					chessPiece.get(i).setImg(chessPiece.get(i).getImageName());
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

	public void addStar(Star e) {
		e.setMouseTransparent(true);
		this.star = e;
		getChildren().add(e);
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
	}

	public ChessPiece getClickedChess() {
		return clickedChess;
	}

	public void setClickedChess(ChessPiece clickedChess) {
		this.clickedChess = clickedChess;
	}
	

}
