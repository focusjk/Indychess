package chessPiece;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public abstract class ChessPiece extends Pane {
	private double x;
	private double y;
	private int player;
	private String image;
	private ImageView img;
	private boolean isClicked;

	public ChessPiece(double x, double y, int player, String img) {
		super();
		this.x = x;
		this.y = y;
		this.player = player;
		this.image = img + player;
		this.isClicked = false;

		// set screen
		setPrefHeight(80);
		setPrefWidth(80);
		setImg(this.image);
		setX(x);
		setY(y);

		setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(!isClicked)setImg(image + "gray");
				event.consume();
			}
		});

		setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(isClicked) setImg(image + "red");
				else setImg(image);
				event.consume();
			}
		});
		
		setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				
				if(isClicked) setImg(image);
				else setImg(image + "red");
				isClicked = !isClicked;
				event.consume();
			}
		});
		
	}

	public String getImageName() {
		return this.image;
	}

	public void setImg(String image) {
		if (getChildren().contains(img))
			getChildren().remove(img);
		img = new ImageView(new Image(ClassLoader.getSystemResource(image + ".png").toString()));
		getChildren().add(img);
	}

	public double getX() {
		return this.x;
	}

	public void setX(double x) {
		this.x = x;
		setLayoutX(180 + y * 80);
	}

	public double getY() {
		return this.y;
	}

	public void setY(double y) {
		this.y = y;
		setLayoutY(30 + x * 80);
	}

	public int getPlayer() {
		return player;
	}

	public void setPlayer(int player) {
		this.player = player;
	}

}
