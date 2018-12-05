package component;

import java.util.ArrayList;
import java.util.Random;

import chessPiece.ChessPiece;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import main.Main;

public class Star extends Pane {
	private int x;
	private int y;

	public Star() {
		super();
		int x, y;
		while (true) {
			Random rand1 = new Random();
			Random rand2 = new Random();
			x = rand1.nextInt(5) + 1;
			y = rand2.nextInt(5) + 1;

			boolean t = true;
			ArrayList<ChessPiece> chessPiece = Main.getGameScreen().getChessPiece();
			for (int i = 0; i < chessPiece.size(); i++) {
				if (chessPiece.get(i).getX() == x && chessPiece.get(i).getY() == y) {
					t = false;
					break;
				}
			}
			if (t) {
				this.x = x;
				this.y = y;
				break;
			}

		}
		// set screen
		setPrefHeight(80);
		setPrefWidth(80);

		ImageView img = new ImageView(new Image(ClassLoader.getSystemResource("images/star.png").toString()));
		setLayoutX(180 + this.x * 80);
		setLayoutY(30 + this.y * 80);
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
