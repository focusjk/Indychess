package scene;

import javax.security.auth.callback.ChoiceCallback;

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
	private Thread t;
//	private String winnerName;
//	private Text name;

	public CongratModal(String winnerName, String status) {
		setPrefHeight(700);
		setPrefWidth(1000);
		setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0.7), null, null)));

		Main.stopMusic();
		AudioClip music = new AudioClip(ClassLoader.getSystemResource("sound/winSound.mp3").toString());
		music.setCycleCount(AudioClip.INDEFINITE);
		music.play();

		ImageView img = new ImageView(
				new Image(ClassLoader.getSystemResource("images/congratModal/congrat0.jpg").toString()));
		img.setFitHeight(600);
		img.setFitWidth(450);
		img.setLayoutX(275);
		img.setLayoutY(50);

		this.t = new Thread(() -> {
			int i = 1;
			while (true) {
				try {
					Thread.sleep(40);
					img.setImage(new Image(
							ClassLoader.getSystemResource("images/congratModal/congrat" + i + ".jpg").toString()));
					i++;
					i %= 133;
					System.out.println(i + "Focus");
				} catch (InterruptedException e) {
					e.printStackTrace();
					System.out.println("Stop Background Thread");
					break;
				}
			}
		});
		this.t.start();

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
				t.interrupt();
				music.stop();
				playCheckSound();
				System.out.println("dsgdsgsdgfewr24426ip30owe;krjld");
			}
		};
		winner.setLayoutX(350);
		winner.setLayoutY(530);
		getChildren().addAll(img, winner, statusText, name);

	}

}
