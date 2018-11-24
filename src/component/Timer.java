package component;

import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import main.Main;

public class Timer extends HBox {
	private int currentTime;
	private long lastTimeTriggered;
	private AnimationTimer animationTimer;
	private ImageView icon;

	public Timer(PlayerProfile player1, PlayerProfile player2) {
		// set screen
		setPrefHeight(65);
		setPrefWidth(200);
		setAlignment(Pos.CENTER);
		setSpacing(10);
		setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));

		// set board
		icon = new ImageView(new Image(ClassLoader.getSystemResource("images/timer.png").toString()));

		// set timing
		Text timing = new Text();
		timing.setFont(Font.font("AndaleMono", 40));

		this.currentTime = 11;
		this.lastTimeTriggered = -1;
		this.animationTimer = new AnimationTimer() {

			@Override
			public void handle(long now) {
				lastTimeTriggered = (lastTimeTriggered < 0 ? now : lastTimeTriggered);

				if (now - lastTimeTriggered >= 1000000000 && currentTime > 0) {
					if (currentTime == 6) {
						timing.setFill(Color.RED);
						icon.setImage(new Image(ClassLoader.getSystemResource("images/timerred.png").toString()));
					}
					currentTime--;
					if (currentTime < 10)
						timing.setText("00:0" + currentTime);
					else
						timing.setText("00:" + currentTime);
					lastTimeTriggered = now;
				} else if (currentTime == 0) {
					currentTime = 10;
					timing.setFill(Color.BLACK);
					icon.setImage(new Image(ClassLoader.getSystemResource("images/timer.png").toString()));
//					Main.getGameScreen().changeTurn();
					Main.getGameScreen().setClickedChess(null);
					Main.getGameScreen().resetBoard();
				}
			}
		};
		this.animationTimer.start();

		getChildren().addAll(icon, timing);
	}

}
