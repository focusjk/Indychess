package component;


import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class InputField extends VBox {

	private TextField textField;

	public InputField(String title, String promptText) {

		setPadding(new Insets(5));
		setSpacing(5);

		Label label = new Label(title);
		getChildren().add(label);

		textField = new TextField();
		textField.setPromptText(promptText);
		getChildren().add(textField);
	}

	public String getText() {
		return textField.getText().trim();
	}

}
