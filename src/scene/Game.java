package scene;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import main.Main;
import chessPiece.ChessPiece;
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
//	 private ChessPiece[][] chessPiece = new ChessPiece[7][7];
	 private CellBoard[][] board = new CellBoard[7][7];

	public Game() {
		// set screen
		setPrefHeight(700);
		setPrefWidth(1000);

		setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));

		// set board
		for(int i = 1; i<= 6; i++) {
			for(int j = 1; j<= 6; j++) {
				board[i][j] = new CellBoard(i, j);
				getChildren().add(board[i][j]);
			}
		}
//		ImageView board = new ImageView(new Image(ClassLoader.getSystemResource("images/board.png").toString()));
//		board.setLayoutX(260);
//		board.setLayoutY(110);

		// set pause button
		pauseButton = new Button("images/pause", 920, 10) {
			@Override
			public void onClicked() {
				Main.setup("login");
			}
		};

		// set player
		player1 = new PlayerProfile("anonymous", 1);
		player2 = new PlayerProfile("anonymous", 2);

		// set timer
		timer = new Timer(player1, player2 );
		timer.setLayoutX(400);
		timer.setLayoutY(15);

		getChildren().addAll(pauseButton, timer, player1, player2);

		// set mouse
		setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				System.out.println(event.toString());
				if (event.getTarget().equals(pauseButton)) {
					getChildren().add(resumeModal);
				} else if (event.getTarget() instanceof Clickable) {
					System.out.println(event.toString());
					((Clickable) event.getTarget()).onClicked();

					if (getChildren().contains(resumeModal))
						getChildren().remove(resumeModal);
				} else {
					if (getChildren().contains(resumeModal))
						getChildren().remove(resumeModal);
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

	public void addChessPiece(ChessPiece e) {
		getChildren().add(e);
	}


	public void addStar(Star e) {
		getChildren().add(e);
	}
}
