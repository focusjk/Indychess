package scene;

import java.util.Random;

import chessPiece.BishopPiece;
import chessPiece.ChessPiece;
import chessPiece.KnightPiece;
import chessPiece.PawnPiece;
import chessPiece.QueenPiece;
import chessPiece.RookPiece;
import component.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import main.Main;

public class StarModal extends Pane {

	public StarModal(ChessPiece clickedChess) {
		setPrefHeight(700);
		setPrefWidth(1000);
		setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0.7), null, null)));

		String imageName = "";
		int x;
		int y;
		Random rand = new Random();
		x = rand.nextInt(5);
		imageName += x;
		if (x == 4) {
			Random rand1 = new Random();
			y = rand1.nextInt(4);
			imageName += "." + y;
		} else {
			y=0;
		}

		ImageView card = new ImageView(
				new Image(ClassLoader.getSystemResource("images/card" + imageName + ".png").toString()));
		card.setMouseTransparent(true);
		card.setLayoutX(362.5);
		card.setLayoutY(150);

		Button ok = new Button("images/okButton", 450, 500) {
			@Override
			public void onClicked() {
				if (x == 0) {
					clickedChess.setPlayer((clickedChess.getPlayer() % 2) + 1);
				} else if (x == 1) {
					Main.getGameScreen().changeTurn();
				} else if (x == 2) {
					int X = (int) clickedChess.getX();
					int Y = (int) clickedChess.getY();
					if (X - 1 <= 6 && X - 1 >= 1 && Y <= 6 && Y >= 1
							&& Main.getGameScreen().findChessPiece(X - 1, Y) == null)
						Main.getGameScreen().addChessPiece(new PawnPiece(X - 1, Y, clickedChess.getPlayer()));
					if (X + 1 <= 6 && X + 1 >= 1 && Y <= 6 && Y >= 1
							&& Main.getGameScreen().findChessPiece(X + 1, Y) == null)
						Main.getGameScreen().addChessPiece(new PawnPiece(X + 1, Y, clickedChess.getPlayer()));
					if (X <= 6 && X >= 1 && Y - 1 <= 6 && Y - 1 >= 1
							&& Main.getGameScreen().findChessPiece(X, Y - 1) == null)
						Main.getGameScreen().addChessPiece(new PawnPiece(X, Y - 1, clickedChess.getPlayer()));
					if (X <= 6 && X >= 1 && Y + 1 <= 6 && Y + 1 >= 1
							&& Main.getGameScreen().findChessPiece(X, Y + 1) == null)
						Main.getGameScreen().addChessPiece(new PawnPiece(X, Y + 1, clickedChess.getPlayer()));
				} else if (x == 3) {
					Main.getGameScreen().removeChessPiece(clickedChess);
				} else if (x == 4) {
					double X = clickedChess.getX();
					double Y = clickedChess.getY();
					int player = clickedChess.getPlayer();
					Main.getGameScreen().removeChessPiece(clickedChess);
					if (y == 0)
						Main.getGameScreen().addChessPiece(new BishopPiece(X, Y, player));
					else if (y == 1)
						Main.getGameScreen().addChessPiece(new QueenPiece(X, Y, player));
					else if (y == 2)
						Main.getGameScreen().addChessPiece(new KnightPiece(X, Y, player));
					else if (y == 3)
						Main.getGameScreen().addChessPiece(new RookPiece(X, Y, player));

				}
				Main.getGameScreen().removeStarModal();
				Main.getGameScreen().addStar();
				
			}
		};

		getChildren().addAll(card, ok);
	}

}
