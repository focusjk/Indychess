package main;

import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			Scene scene1 = new Scene(new scene.Login());
			primaryStage.setTitle("Indychess");
			primaryStage.setScene(scene1);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
