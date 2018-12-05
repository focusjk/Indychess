package exception;

import javafx.scene.control.Alert;

public class WrongFormatInputException extends Exception {
	
	public WrongFormatInputException() {
		System.err.println("Player name isn't correct");
		
		Alert alert = new Alert(Alert.AlertType.INFORMATION, "Player name should have 1-8 characters");
		alert.setTitle("Input Error");
		alert.setHeaderText("Please Fill Player Name in correct format");
		alert.show();
	}
}
