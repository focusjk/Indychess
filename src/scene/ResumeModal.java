package scene;

import component.Button;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import main.Main;

public class ResumeModal extends VBox {

	public ResumeModal() {
		setPrefHeight(700);
		setPrefWidth(1000);
		setSpacing(25);
		setAlignment(Pos.CENTER);
		setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0.7), null, null)));

		Button resume = new Button("images/resumeButton", 0, 0) {
			@Override
			public void onClicked() {
				Main.getGameScreen().removeResumeModal();
			}
		};
		Button newgame = new Button("images/newgameButton", 0, 0) {
			@Override
			public void onClicked() {
				Main.setup("login");
			}
		};
		Button quit = new Button("images/quitButton", 0, 0) {
			@Override
			public void onClicked() {
				System.exit(0);
			}
		};
		getChildren().addAll(resume, newgame, quit);
	}

}
