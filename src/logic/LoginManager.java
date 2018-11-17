package logic;

import component.InputField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import main.Main;

public class LoginManager extends Manager {
	private InputField player1Input;
	private InputField player2Input;
	private Button button;

	public LoginManager(InputField player1Input, InputField player2Input, Button button) {
		super();
		setPlayer1Input(player1Input);
		setPlayer2Input(player2Input);
		setButton(button);
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
					Alert a = new Alert(Alert.AlertType.INFORMATION, "Please Fill Player Name");
					a.show();
				}
				Main.setup("game");
			} catch (Exception e) {
				// TODO
				
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

}
