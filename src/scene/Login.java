package scene;

import component.Button;
import component.InputField;
import exception.ImageNotFoundException;
import exception.InputNotFilledException;
import exception.ThreadInterruptedException;
import exception.WrongFormatInputException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
	private int backgroundNumber = 0;
	private Thread backgroundThread;

	public Login() {
		// set screen
		setPrefHeight(700);
		setPrefWidth(1000);
		setPadding(new Insets(50, 320, 100, 320));
		setAlignment(Pos.CENTER);

		// set background
//		background = new Image(ClassLoader
//				.getSystemResource("images/loginBackground/background1-" + backgroundNumber + ".jpg").toString());
//		setBackground(new Background(new BackgroundImage(background, null, null, null,
//				new BackgroundSize(1000, 700, false, false, false, false))));

		// set logo
		logo = new ImageView(new Image(ClassLoader.getSystemResource("images/logo.png").toString()));
		logo.setFitWidth(200);
		logo.setPreserveRatio(true);

		// set input player1
		player1 = new InputField("PLAYER 1", "Input name (1- 8 characters)");
		// set input player2
		player2 = new InputField("PLAYER 2", "Input name (1- 8 characters)");

		// start button
		Button startButton = new Button("images/startButton", 0, 0) {
			@Override
			public void onClicked() {
				try {
					String name1 = player1.getText();
					String name2 = player2.getText();
					if (name1.isEmpty() || name2.isEmpty()) {
						throw new InputNotFilledException();
					} else if (name1.length() > 8 || name2.length() > 8) {
						throw new WrongFormatInputException();
					} else {
						Main.setup("game");
						backgroundThread.interrupt();
					}
				} catch (InputNotFilledException e) {
					System.out.println("InputNotFilledException in Login");
				} catch (WrongFormatInputException e) {
					System.out.println("WrongFormatInputException in Login");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		// set box
		VBox box = new VBox();
		box.setPadding(new Insets(20));
		box.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		box.setSpacing(15);
		box.setAlignment(Pos.CENTER);
		box.getChildren().addAll(logo, player1, player2, startButton);

		getChildren().add(box);

		this.backgroundThread = new Thread(() -> {
			int i = 1;
			try {
				while (true) {
					try {
						Thread.sleep(35);
						setBackground(i);
						i++;
						i %= 40;
					} catch (InterruptedException e) {
						throw new ThreadInterruptedException();
					} catch (Exception e) {
						throw e;
					}
				}
			} catch (ThreadInterruptedException e) {
				System.out.println("Login background thread is stoped");
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		this.backgroundThread.start();

		try {
			try {
				logo.setImage(new Image(ClassLoader.getSystemResource("images/logo.png").toString()));
			} catch (NullPointerException e) {
				throw new ImageNotFoundException(6);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (ImageNotFoundException exception) {
			logo.setImage(new Image(ClassLoader.getSystemResource("images/errorIcon.png").toString()));
			System.out.println("Can not setImage in Login logo");
		}

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

	public void setBackground(int number) {
		backgroundNumber = number;
		try {
			try {
				background = new Image(
						ClassLoader.getSystemResource("images/loginBackground/backgrounds1-" + backgroundNumber + ".jpg")
								.toString());
				setBackground(new Background(new BackgroundImage(background, null, null, null,
						new BackgroundSize(1000, 700, false, false, false, false))));
			} catch (NullPointerException e) {
				throw new ImageNotFoundException(6);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (ImageNotFoundException exception) {
			this.backgroundThread.interrupt();
			System.out.println("Can not setImage in Login background");
		}

	}

}
