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

public class StarModal extends Pane {

	public StarModal(ChessPiece clickedChess, Game gameScreen) {
		setPrefHeight(700);
		setPrefWidth(1000);
		setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0.7), null, null)));

		String cardName = "";
		Random rand = new Random();
		int x = rand.nextInt(5);
		int y;
		if (x == 4) {
			Random rand1 = new Random();
			y = rand1.nextInt(4);
			cardName += x + "." + y;
		} else {
			y = 0;
			cardName += x;
		}

		ImageView card = new ImageView(
				new Image(ClassLoader.getSystemResource("images/card" + cardName + ".png").toString()));
		card.setMouseTransparent(true);
		card.setLayoutX(362.5);
		card.setLayoutY(150);

		Button ok = new Button("images/okButton", 450, 500) {
			@Override
			public void onClicked() {
				if (x == 0) {
					clickedChess.setPlayer((clickedChess.getPlayer() % 2) + 1);
				} else if (x == 1) {
					gameScreen.changeTurn();
				} else if (x == 2) {
					int X = (int) clickedChess.getX();
					int Y = (int) clickedChess.getY();
					if (X - 1 <= 6 && X - 1 >= 1 && Y <= 6 && Y >= 1
							&& gameScreen.findChessPiece(X - 1, Y) == null)
						gameScreen.addChessPiece(new PawnPiece(X - 1, Y, clickedChess.getPlayer()));
					if (X + 1 <= 6 && X + 1 >= 1 && Y <= 6 && Y >= 1
							&& gameScreen.findChessPiece(X + 1, Y) == null)
						gameScreen.addChessPiece(new PawnPiece(X + 1, Y, clickedChess.getPlayer()));
					if (X <= 6 && X >= 1 && Y - 1 <= 6 && Y - 1 >= 1
							&& gameScreen.findChessPiece(X, Y - 1) == null)
						gameScreen.addChessPiece(new PawnPiece(X, Y - 1, clickedChess.getPlayer()));
					if (X <= 6 && X >= 1 && Y + 1 <= 6 && Y + 1 >= 1
							&& gameScreen.findChessPiece(X, Y + 1) == null)
						gameScreen.addChessPiece(new PawnPiece(X, Y + 1, clickedChess.getPlayer()));
				} else if (x == 3) {
					gameScreen.removeChessPiece(clickedChess);
				} else if (x == 4) {
					double X = clickedChess.getX();
					double Y = clickedChess.getY();
					int player = clickedChess.getPlayer();
					gameScreen.removeChessPiece(clickedChess);
					if (y == 0)
						gameScreen.addChessPiece(new BishopPiece(X, Y, player));
					else if (y == 1)
						gameScreen.addChessPiece(new QueenPiece(X, Y, player));
					else if (y == 2)
						gameScreen.addChessPiece(new KnightPiece(X, Y, player));
					else if (y == 3)
						gameScreen.addChessPiece(new RookPiece(X, Y, player));

				}
				gameScreen.removeStarModal();
				gameScreen.addStar();
			}
		};

		getChildren().addAll(card, ok);
	}

}
