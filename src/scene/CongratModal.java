package scene;

import component.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import main.Main;

public class CongratModal extends Pane {

	public CongratModal(String winnerName) {
		setPrefHeight(700);
		setPrefWidth(1000);
		setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0.7), null, null)));
		
		ImageView img = new ImageView(
				new Image(ClassLoader.getSystemResource("images/congratModal/congrat0.jpg").toString()));
		img.setFitHeight(600);
		img.setFitWidth(450);
		img.setLayoutX(275);
		img.setLayoutY(50);

		Text name = new Text(winnerName.toUpperCase());
		name.setFont(Font.font("AndaleMono", 40));
		name.setWrappingWidth(300);
		name.setTextAlignment(TextAlignment.CENTER);
		name.setLayoutX(350);
		name.setLayoutY(120);
		
		Button winner = new Button("images/winnerButton", 0, 0) {
			@Override
			public void onClicked() {
				Main.setup("login");
			}
		};
		winner.setLayoutX(350);
		winner.setLayoutY(530);
		getChildren().addAll(img, winner, name);

		Thread t = new Thread(() -> {
			int i = 1;
			while (true) {
				try {
					Thread.sleep(40);
					img.setImage(new Image(
							ClassLoader.getSystemResource("images/congratModal/congrat" + i + ".jpg").toString()));
					i++;
					i %= 133;
					System.out.println(i);
				} catch (InterruptedException e) {
					e.printStackTrace();
					System.out.println("Stop Background Thread");
					break;
				}
			}
		});
		t.start();
	}

}
