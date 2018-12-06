package component;

import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;

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
	private static int timeLimit = 30;
	private int currentTime;
	private int currentTime2;
	private long lastTimeTriggered;
	private AnimationTimer animationTimer;
	private Text timing;

	public Timer(PlayerProfile player1, PlayerProfile player2) {
		// set screen
		setPrefHeight(65);
		setPrefWidth(200);
		setAlignment(Pos.CENTER);
		setSpacing(10);
		setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));

		// set timing
		timing = new Text("00:00");
		timing.setFont(Font.loadFont(ClassLoader.getSystemResource("font/Copperplate.ttf").toString(), 40));
		timing.setFill(Color.BLACK);

		resetTime();
		this.lastTimeTriggered = -1;
		this.animationTimer = new AnimationTimer() {

			@Override
			public void handle(long now) {
				if (currentTime < 0) {
					Main.getGameScreen().changeTurn();
				}

				if (currentTime < 10) {
					timing.setFill(Color.RED);
					if (currentTime2 % 100 < 10)
						timing.setText("0" + currentTime + ":0" + currentTime2 % 100);
					else
						timing.setText("0" + currentTime + ":" + currentTime2 % 100);
				} else {
					timing.setFill(Color.BLACK);
					if (currentTime2 % 100 < 10)
						timing.setText(currentTime + ":0" + currentTime2 % 100);
					else
						timing.setText(currentTime + ":" + currentTime2 % 100);
				}

				lastTimeTriggered = (lastTimeTriggered < 0 ? now : lastTimeTriggered);
				if (now - lastTimeTriggered >= 30000000) {
					currentTime2 -= 3;
					if (currentTime2 <= 0) {
						currentTime2 = 100;
						currentTime--;
					}
					lastTimeTriggered = now;
				}
			}
		};
		getChildren().addAll(timing);
	}

	public void start() {
		this.animationTimer.start();
	}

	public void stop() {
		this.animationTimer.stop();
	}

	public void resetTime() {
		this.currentTime = timeLimit;
		this.currentTime2 = 0;
	}


}
