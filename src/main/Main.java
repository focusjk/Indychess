package main;

import javafx.stage.Stage;
import logic.GameManager;
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
				setLoginScreen(new Login());
				setCurrentScene(new Scene(loginScreen));
				stopMusic();
				startMusic();
			} else if (managerType.equals("game")) {
				setGameScreen(new Game(loginScreen.getPlayer1().getText(),loginScreen.getPlayer2().getText()));
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
		gameManager = new GameManager(gameScreen);
	}

	public static GameManager getGameManager() {
		return gameManager;
	}
	

	public static void startMusic() {
		backgroundMusic.play();
	}

	public static void stopMusic() {
		backgroundMusic.stop();
	}


}
