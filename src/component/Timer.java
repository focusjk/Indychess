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
	private static int timeLimit = 10;
	private int currentTime;
	private int currentTime2;
	private long lastTimeTriggered;
	private AnimationTimer animationTimer;
	private ImageView icon;
	private Text timing;

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
		timing = new Text();
		timing.setFont(Font.font("AndaleMono", 40));
		timing.setWrappingWidth(115);

		setTimeMax();
		this.lastTimeTriggered = -1;
		this.animationTimer = new AnimationTimer() {

			@Override
			public void handle(long now) {
				if (currentTime < 0) {
					Main.getGameScreen().changeTurn();
					Main.getGameScreen().setClickedChess(null);
					Main.getGameScreen().resetBoard();
				}

				if (currentTime < 10) {
					if (currentTime2%100 < 10)
						timing.setText("0" + currentTime + ":0" + currentTime2%100);
					else
						timing.setText("0" + currentTime + ":" + currentTime2%100);
				} else {
					if (currentTime2 < 10)
						timing.setText(currentTime + ":0" + currentTime2%100);
					else
						timing.setText(currentTime + ":" + currentTime2%100);
				}

				if (currentTime <= 5) {
					timing.setFill(Color.RED);
					icon.setImage(new Image(ClassLoader.getSystemResource("images/timerred.png").toString()));
				} else {
					timing.setFill(Color.BLACK);
					icon.setImage(new Image(ClassLoader.getSystemResource("images/timer.png").toString()));
				}

				lastTimeTriggered = (lastTimeTriggered < 0 ? now : lastTimeTriggered);
				if (now - lastTimeTriggered >= 10000000) {
//					currentTime--;
					currentTime2--;
					if (currentTime2 == 0) {
						currentTime2 = 100;
						currentTime--;
//						//1 000 000 000
					}
					lastTimeTriggered = now;
				}
			}
		};
		getChildren().addAll(icon, timing);
	}

	public void start() {
		this.animationTimer.start();
	}

	public void reset() {
		setTimeMax();
	}

	public void setTimeMax() {
		this.currentTime = timeLimit;
		this.currentTime2 = 100;
	}

	public void stop() {
		this.animationTimer.stop();
	}

}
