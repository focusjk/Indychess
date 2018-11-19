package scene;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import component.ChessPiece;
import component.PlayerProfile;
import component.Star;
import component.Timer;

public class Game extends Pane {
	private PlayerProfile player1;
	private PlayerProfile player2;
	private ImageView pauseButton;
	private Timer timer;
//	private ChessPiece[][] chessPiece = new ChessPiece[7][7]; 

	public Game() {
		// set screen
		setPrefHeight(700);
		setPrefWidth(1000);

		setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));

		// set board
		ImageView board = new ImageView(new Image(ClassLoader.getSystemResource("images/board.png").toString()));
		board.setLayoutX(260);
		board.setLayoutY(110);

		// set pause button
		pauseButton = new ImageView(new Image(ClassLoader.getSystemResource("images/pause.png").toString()));
		pauseButton.setLayoutX(920);
		pauseButton.setLayoutY(10);
		
		// set player
		player1 = new PlayerProfile("anonymous", 1);
		player2 = new PlayerProfile("anonymous", 2);
		
		// set timer
		timer = new Timer();
		timer.setLayoutX(400);
		timer.setLayoutY(15);

		getChildren().addAll(board, pauseButton, timer, player1, player2);
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

	public ImageView getPauseButton() {
		return pauseButton;
	}

	public void setPauseButton(ImageView pauseButton) {
		this.pauseButton = pauseButton;
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}
	
	public void addChessPiece(ChessPiece e) {
//		chessPiece[e.getX()][e.getY()] = e;
		getChildren().add(e);
	}
	
//	public void moveChessPiece(ChessPiece e) {
//		chessPiece[e.getX()][e.getY()] = e;
//		e.setLayoutX(e.getX());
//		getChildren().add(e);
//	}
	
	public void addStar(Star e) {
		getChildren().add(e);
	}
}
