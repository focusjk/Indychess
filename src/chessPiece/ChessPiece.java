package chessPiece;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class ChessPiece extends Pane {
	private int x;
	private int y;
	private int player;
	private String image;

	public ChessPiece(int x, int y, int player, String image) {
		super();
		this.x = x;
		this.y = y;
		this.player = player;
		this.image = image;

		// set screen
		setPrefHeight(80);
		setPrefWidth(80);

		ImageView img = new ImageView(new Image(this.image));
		setLayoutX(180 + y*80);
		setLayoutY(30 + x*80);
		getChildren().add(img);
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
		setLayoutX(180 + y*80);
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
		setLayoutY(30 + x*80);
	}

	public int getPlayer() {
		return player;
	}

	public void setPlayer(int player) {
		this.player = player;
	}

}
