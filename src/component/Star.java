package component;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Star extends Pane {
	private int x;
	private int y;

	public Star(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	
		// set screen
		setPrefHeight(80);
		setPrefWidth(80);

		ImageView img = new ImageView(new Image(ClassLoader.getSystemResource("images/star.png").toString()));
		setLayoutX(180 + x * 80);
		setLayoutY(30 + y * 80);
		getChildren().add(img);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
