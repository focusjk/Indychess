package main;

import javafx.stage.Stage;
import logic.GameManager;
import logic.LoginManager;
import scene.Game;
import scene.Login;
import javafx.application.Application;
import javafx.scene.Scene;

public class Main extends Application {
	private static Stage stage;
	private static Login loginScreen;
	private static Game gameScreen;
	private static LoginManager loginManager;
	private static GameManager gameManager;

	@Override
	public void start(Stage primaryStage) {
		try {
			stage = primaryStage;
			setup("login");
			primaryStage.setTitle("Indychess");
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static void setup(String managerType) {

		if (managerType.equals("login")) {
			loginScreen = new Login();
			loginManager = new LoginManager(loginScreen,loginScreen.getPlayer1(), loginScreen.getPlayer2(),
					loginScreen.getStartButton());
			setCurrentScene(new Scene(loginScreen));
		} else if (managerType.equals("game")) {
			gameScreen = new Game();
			gameManager = new GameManager(gameScreen);
			gameScreen.getPlayer1().setName(loginManager.getPlayer1Input().getText());
			gameScreen.getPlayer2().setName(loginManager.getPlayer2Input().getText());
			setCurrentScene(new Scene(gameScreen));
		}
	}

	public static void setCurrentScene(Scene scene) {
		stage.setScene(scene);
	}

	public static Login getLoginScreen() {
		return loginScreen;
	}

	public static void setLoginScreen(Login loginScreen) {
		Main.loginScreen = loginScreen;
	}

	public static Game getGameScreen() {
		return gameScreen;
	}

	public static void setGameScreen(Game gameScreen) {
		Main.gameScreen = gameScreen;
	}

}
