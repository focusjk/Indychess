package component;

import java.util.ArrayList;
import java.util.Random;

import chessPiece.ChessPiece;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Star extends Pane {
	private int x;
	private int y;
	private ImageView img;

	public Star(ArrayList<ChessPiece> chessPiece) {
		super();
		while (true) {
			Random random1 = new Random();
			Random random2 = new Random();
			int x = random1.nextInt(5) + 1;
			int y = random2.nextInt(5) + 1;

			boolean isAvailable = true;
			for (int i = 0; i < chessPiece.size(); i++) {
				if (chessPiece.get(i).getX() == x && chessPiece.get(i).getY() == y) {
					isAvailable = false;
					break;
				}
			}
			if (isAvailable) {
				setX(x);
				setY(y);
				break;
			}

		}
		// set screen
		setPrefHeight(80);
		setPrefWidth(80);
		img = new ImageView();
		try {
			img.setImage(new Image(ClassLoader.getSystemResource("images/star.png").toString()));
		} catch (NullPointerException e) {
			System.err.println("Star Image is not found");
			img.setImage(new Image(ClassLoader.getSystemResource("images/errorIcon.png").toString()));
		} catch (Exception e) {
			e.printStackTrace();
		}
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
