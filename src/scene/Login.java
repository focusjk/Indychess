package scene;

import component.Button;
import component.InputField;
import exception.InputNotFilledException;
import exception.TooLongInputException;
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
						throw new TooLongInputException();
					} else {
						for (int i = 0; i < name1.length(); i++) {
							int ascii = (int) name1.charAt(i);
							if (48 <= ascii && ascii <= 57 || 65 <= ascii && ascii <= 90 || 97 <= ascii && ascii <= 122
									|| ascii == 95 || ascii == 45)
								continue;
							else 
								throw new WrongFormatInputException();
						}
						for (int i = 0; i < name2.length(); i++) {
							int ascii = (int) name2.charAt(i);
							if (48 <= ascii && ascii <= 57 || 65 <= ascii && ascii <= 90 || 97 <= ascii && ascii <= 122
									|| ascii == 95 || ascii == 45)
								continue;
							else 
								throw new WrongFormatInputException();
						}
					}
					Main.setup("game");
					backgroundThread.interrupt();
				} catch (InputNotFilledException e) {
					System.out.println("InputNotFilledException in Login");
				} catch (WrongFormatInputException e) {
					System.out.println("WrongFormatInputException in Login");
				} catch (TooLongInputException e) {
					System.out.println("TooLongInputException in Login");
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
			while (true) {
				try {
					Thread.sleep(35);
					setBackground(i);
					i++;
					i %= 40;
				} catch (InterruptedException e) {
					System.err.println("Login: Thread is interrupted");
					break;
				} catch (Exception e) {
					throw e;
				}
			}
		});
		this.backgroundThread.start();
		try {
			logo.setImage(new Image(ClassLoader.getSystemResource("images/logo.png").toString()));
		} catch (NullPointerException e) {
			logo.setImage(new Image(ClassLoader.getSystemResource("images/errorIcon.png").toString()));
			System.out.println("Can not setImage in Login logo");
			System.err.println("Login Image is not found");
		} catch (Exception e) {
			e.printStackTrace();
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
			background = new Image(ClassLoader
					.getSystemResource("images/loginBackground/background1-" + backgroundNumber + ".jpg").toString());
			setBackground(new Background(new BackgroundImage(background, null, null, null,
					new BackgroundSize(1000, 700, false, false, false, false))));
		} catch (NullPointerException e) {
			this.backgroundThread.interrupt();
			System.err.println("Login Image is not found");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
