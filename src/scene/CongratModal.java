package scene;

import component.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import main.Main;

public class CongratModal extends Pane {
	private Thread backgroundThread;

	public CongratModal(String winnerName, String status) {
		setPrefHeight(700);
		setPrefWidth(1000);
		setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0.7), null, null)));

		Main.stopMusic();
		AudioClip congratSound = new AudioClip(ClassLoader.getSystemResource("sound/winSound.mp3").toString());
		congratSound.setCycleCount(AudioClip.INDEFINITE);
		congratSound.play();

		ImageView backgroundImg = new ImageView();
		backgroundImg.setFitHeight(600);
		backgroundImg.setFitWidth(450);
		backgroundImg.setLayoutX(275);
		backgroundImg.setLayoutY(50);

		this.backgroundThread = new Thread(() -> {
			int i = 1;
			while (true) {
				try {
					Thread.sleep(40);
					backgroundImg.setImage(new Image(
							ClassLoader.getSystemResource("images/congratModal/congrat" + i + ".jpg").toString()));
					i++;
					i %= 133;
				} catch (InterruptedException e) {
					System.err.println("CongratModal: Thread is interrupted");
					break;
				} catch (NullPointerException e) {
					backgroundThread.interrupt();
					backgroundImg.setImage(new Image(ClassLoader.getSystemResource("images/errorIcon.png").toString()));
					System.out.println("Congrat background thread is stoped");
					System.err.println("CongratModal Image is not found");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		this.backgroundThread.start();

		Text statusText = new Text(status.toUpperCase());
		statusText.setWrappingWidth(300);
		statusText.setTextAlignment(TextAlignment.CENTER);
		statusText.setLayoutX(350);
		statusText.setLayoutY(100);
		statusText.setFont(Font.loadFont(ClassLoader.getSystemResource("font/CopperplateBold.ttf").toString(), 20));
		statusText.setFill(Color.BLACK);

		Text name = new Text(winnerName.toUpperCase());
		name.setWrappingWidth(450);
		name.setTextAlignment(TextAlignment.CENTER);
		name.setLayoutX(275);
		name.setLayoutY(150);
		name.setFont(Font.loadFont(ClassLoader.getSystemResource("font/CopperplateBold.ttf").toString(), 50));
		name.setFill(Color.BLACK);

		Button winner = new Button("images/winnerButton", 0, 0) {
			@Override
			public void onClicked() {
				Main.setup("login");
				backgroundThread.interrupt();
				congratSound.stop();
			}
		};
		winner.setLayoutX(350);
		winner.setLayoutY(530);

		getChildren().addAll(backgroundImg, winner, statusText, name);
	}

}
