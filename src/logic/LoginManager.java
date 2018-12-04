package logic;


import component.InputField;
import exception.InputNotFilledException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import main.Main;
import scene.Login;

public class LoginManager {
	private InputField player1Input;
	private InputField player2Input;
	private Button button;
	private Thread backgroundThread;
	private Login loginScreen;

	public LoginManager(Login loginScreen, InputField player1Input, InputField player2Input, Button button) {
		super();
		this.loginScreen = loginScreen;
		setPlayer1Input(player1Input);
		setPlayer2Input(player2Input);
		setButton(button);

		this.backgroundThread = new Thread(() -> {
			int i = 1;
			while (true) {
				try {
					Thread.sleep(35);
					loginScreen.setBgNumber(i);
					i++;
					i %= 40;
					System.out.println(i);
				} catch (InterruptedException e) {
					e.printStackTrace();
					System.out.println("Stop Background Thread");
					break;
				}
			}
		});
		this.backgroundThread.start();
	}

	private class ButtonEventHandler implements EventHandler<ActionEvent> {

		private InputField player1Input;
		private InputField player2Input;

		public ButtonEventHandler(InputField player1Input, InputField player2Input) {
			this.player1Input = player1Input;
			this.player2Input = player2Input;
		}

		@Override
		public void handle(ActionEvent arg0) {
			try {
				String name1 = this.player1Input.getText();
				String name2 = this.player2Input.getText();
				if (name1.isEmpty() || name2.isEmpty()) {
//					throw new InputNotFilledException(0);
					Alert a = new Alert(Alert.AlertType.INFORMATION, "Please Fill Player Name");
					a.show();
					throw new InputNotFilledException(0);
				}
				Main.setup("game");
				backgroundThread.interrupt();
			} catch (InputNotFilledException e) {
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}

	}

	public InputField getPlayer1Input() {
		return player1Input;
	}

	public void setPlayer1Input(InputField player1Input) {
		this.player1Input = player1Input;
	}

	public InputField getPlayer2Input() {
		return player2Input;
	}

	public void setPlayer2Input(InputField player2Input) {
		this.player2Input = player2Input;
	}

	public Button getButton() {
		return button;
	}

	public void setButton(Button button) {
		this.button = button;
		this.button.setOnAction(new ButtonEventHandler(player1Input, player2Input));

	}

	public void stopBg() {
		backgroundThread.interrupt();
	}

}
