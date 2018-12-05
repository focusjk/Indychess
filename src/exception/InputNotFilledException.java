package exception;

import javafx.scene.control.Alert;

public class InputNotFilledException extends Exception {
	
	public InputNotFilledException() {
		System.err.println("Player Name is empty");
		
		Alert alert = new Alert(Alert.AlertType.INFORMATION, "Both of players must fill the names");
		alert.setTitle("Input Error");
		alert.setHeaderText("Please Fill Player Name");
		alert.show();
	}
	
}
