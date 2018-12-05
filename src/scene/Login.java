package scene;

import component.Button;
import component.InputField;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import main.Main;

public class Login extends VBox {

	private InputField player1;
	private InputField player2;
	private ImageView logo;
	private Image background;
	private Button startButton;
	private int bgNumber = 0;

	public Login() {
		// set screen
		setPrefHeight(700);
		setPrefWidth(1000);
		setPadding(new Insets(50, 320, 100, 320));
		setAlignment(Pos.CENTER);

		// set background
		background = new Image(
				ClassLoader.getSystemResource("images/loginBackground/background1-" + bgNumber + ".jpg").toString());
		setBackground(new Background(new BackgroundImage(background, null, null, null,
				new BackgroundSize(1000, 700, false, false, false, false))));

		// set box
		VBox box = new VBox();
		box.setPadding(new Insets(20));
		box.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		box.setSpacing(15);
		box.setAlignment(Pos.CENTER);

		// set logo
		logo = new ImageView(new Image(ClassLoader.getSystemResource("images/logo.png").toString()));
		logo.setFitWidth(200);
		logo.setPreserveRatio(true);

		// set input player1
		player1 = new InputField("PLAYER 1", "Input name");
		// set input player2
		player2 = new InputField("PLAYER 2", "Input name");

		// start button
		Button startButton = new Button("images/startButton", 0, 0) {
			@Override
			public void onClicked() {
				try {
					String name1 = player1.getText();
					String name2 = player2.getText();
					if (name1.isEmpty() || name2.isEmpty()) {
//						throw new InputNotFilledException(0);
						Alert a = new Alert(Alert.AlertType.INFORMATION, "Please Fill Player Name");
						a.show();
					} else {
						Main.setup("game");
						System.out.println("dsfsdfd");
						Main.getLoginManager().stopBg();
					}
//				} catch (InputNotFilledException e) {

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
//		startButton = new Button("Let's start");

		box.getChildren().addAll(logo, player1, player2, startButton);
		getChildren().add(box);
	}

	public InputField getPlayer1() {
		return player1;
	}

	public void setPlayer1(InputField player1) {
		this.player1 = player1;
	}

	public InputField getPlayer2() {
		return player2;
	}

	public void setPlayer2(InputField player2) {
		this.player2 = player2;
	}

	public ImageView getLogo() {
		return logo;
	}

	public void setLogo(ImageView logo) {
		this.logo = logo;
	}

	public Button getStartButton() {
		return startButton;
	}

	public void setStartButton(Button startButton) {
		this.startButton = startButton;
	}

	public void setBgNumber(int num) {
		bgNumber = num;
		background = new Image(
				ClassLoader.getSystemResource("images/loginBackground/background1-" + bgNumber + ".jpg").toString());
		setBackground(new Background(new BackgroundImage(background, null, null, null,
				new BackgroundSize(1000, 700, false, false, false, false))));
	}

}
