package chessPiece;

import component.Clickable;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import main.Main;

public abstract class ChessPiece extends Pane implements Clickable {
	private double x;
	private double y;
	private int player;
	private String image;
	private ImageView img;
	private AudioClip selectSound = new AudioClip(ClassLoader.getSystemResource("sound/selectSound.mp3").toString());

	public ChessPiece(double x, double y, int player, String image) {
		super();
		this.x = x;
		this.y = y;
		this.player = player;
		this.image = image;

		// set screen
		setPrefHeight(80);
		setPrefWidth(80);
		setX(x);
		setY(y);
		img = new ImageView();
		setImage(1);
		img.setMouseTransparent(true);
		getChildren().add(this.img);

		setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (!this.equals(Main.getGameScreen().getClickedChess()))
					setImage(2);
				event.consume();
			}
		});

		setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				setImage(1);
				event.consume();
			}
		});

	}

	@Override
	public void onClicked() {
		Main.getGameScreen().setClickedChess(this);
		Main.getGameScreen().resetBoard();
		selectSound.play();
		onActive();		
	}
	public abstract void onActive();
	public abstract boolean isMovable();

	public void setImage(int type) {
		if (type == 1) {
			if (this.equals(Main.getGameScreen().getClickedChess()))
				img.setImage(new Image(ClassLoader.getSystemResource(image + player + "red.png").toString()));
			else
				img.setImage(new Image(ClassLoader.getSystemResource(image + player + ".png").toString()));
		} else if (type == 2) {
			img.setImage(new Image(ClassLoader.getSystemResource(image + player + "gray.png").toString()));
		}
	}

	public void setPosition(double x, double y) {
		if (this.equals(Main.getGameScreen().getClickedChess())) {
			setX(x);
			setY(y);
			Main.getGameScreen().setClickedChess(null);
		}
	}

	public double getX() {
		return this.x;
	}

	public void setX(double x) {
		this.x = x;
		setLayoutX(180 + x * 80);
	}

	public double getY() {
		return this.y;
	}

	public void setY(double y) {
		this.y = y;
		setLayoutY(30 + y * 80);
	}

	public int getPlayer() {
		return player;
	}

	public void setPlayer(int player) {
		this.player = player;
		setImage(1);
	}

}
