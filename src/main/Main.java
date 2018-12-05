package main;

import javafx.stage.Stage;
import logic.GameManager;
import logic.LoginManager;
import scene.Game;
import scene.Login;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.media.AudioClip;

public class Main extends Application {
	private static Stage stage;
	private static Login loginScreen;
	private static Game gameScreen;
	private static GameManager gameManager;
	private static LoginManager loginManager;
	private static AudioClip backgroundMusic;

	@Override
	public void start(Stage primaryStage) {
		try {
			backgroundMusic = new AudioClip(ClassLoader.getSystemResource("sound/background.mp3").toString());
			backgroundMusic.setCycleCount(AudioClip.INDEFINITE);
			backgroundMusic.setVolume(0.2);

			stage = primaryStage;
			setup("login");
			primaryStage.setTitle("Indychess");
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void stop() throws Exception {
		System.exit(0);
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static void setup(String managerType) {
		try {
			if (managerType.equals("login")) {
				loginScreen = new Login();
				loginManager = new LoginManager(loginScreen, loginScreen.getPlayer1(), loginScreen.getPlayer2(), loginScreen.getStartButton());
				setCurrentScene(new Scene(loginScreen));
				stopMusic();
				startMusic();
			} else if (managerType.equals("game")) {
				gameScreen = new Game();
				gameManager = new GameManager(gameScreen);
				gameScreen.getPlayer1().setName(loginManager.getPlayer1Input().getText());
				gameScreen.getPlayer2().setName(loginManager.getPlayer2Input().getText());
				setCurrentScene(new Scene(gameScreen));
			}
		} catch (Exception e) {
			e.printStackTrace();
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

	public static GameManager getGameManager() {
		return gameManager;
	}

	public static void setGameManager(GameManager gameManager) {
		Main.gameManager = gameManager;
	}

	public static void startMusic() {
		backgroundMusic.play();
	}

	public static void stopMusic() {
		backgroundMusic.stop();
	}

	public static LoginManager getLoginManager() {
		return loginManager;
	}

}
