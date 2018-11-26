package scene;

import java.util.Random;

import component.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class StarModal extends Pane {

	public StarModal() {
		setPrefHeight(700);
		setPrefWidth(1000);
		setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0.7), null, null)));
		
		Random rand = new Random();
		int x = rand.nextInt(5);
		ImageView card = new ImageView(new Image(ClassLoader.getSystemResource("images/card"+x +".png").toString()));
		card.setMouseTransparent(true);
		card.setLayoutX(362.5);
		card.setLayoutY(150);
		
		Button ok = new Button("images/okButton", 0, 0) {
			@Override
			public void onClicked() {
			}
		};
		ok.setLayoutX(450);
		ok.setLayoutY(500);
		getChildren().addAll(card, ok);
	}

}
