package chessPiece;

import java.util.ArrayList;

import component.Clickable;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import main.Main;

public abstract class ChessPiece extends Pane implements Clickable {
	private double x;
	private double y;
	private int player;
	private String image;
	private ImageView img = new ImageView();

	public ChessPiece(double x, double y, int player, String img) {
		super();
		this.x = x;
		this.y = y;
		this.player = player;
		this.image = img + player;

		// set screen
		setPrefHeight(80);
		setPrefWidth(80);
		setX(x);
		setY(y);
		this.img = new ImageView(new Image(ClassLoader.getSystemResource(this.image+".png").toString()));
		this.img.setMouseTransparent(true);
		getChildren().add(this.img);

		setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (this.equals(Main.getGameScreen().getClickedChess()))
					setImg(image + "gray");
				event.consume();
			}
		});

		setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				setImg(image);
				event.consume();
			}
		});
		
	}

	@Override
	public void onClicked() {
		Main.getGameScreen().setClickedChess(this);
		Main.getGameScreen().resetBoard();
//		setMove();
//		getMovable();
	}

	public String getImageName() {
		return this.image;
	}

	public void setImg(String image) {
		if (this.equals(Main.getGameScreen().getClickedChess()))
			img.setImage(new Image(ClassLoader.getSystemResource(image + "red.png").toString()));
		else
			img.setImage(new Image(ClassLoader.getSystemResource(image + ".png").toString()));
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
	
}
