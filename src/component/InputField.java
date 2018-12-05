package component;


import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class InputField extends VBox {
	private TextField textField;

	public InputField(String title, String promptText) {

		setPadding(new Insets(5));
		setSpacing(5);

		Label label = new Label(title);
		label.setFont(Font.loadFont(ClassLoader.getSystemResource("font/CopperplateBold.ttf").toString(), 20));
		label.setTextFill(Color.BLACK);

		textField = new TextField();
		textField.setPromptText(promptText);
		textField.setFont(Font.loadFont(ClassLoader.getSystemResource("font/Copperplate.ttf").toString(), 18));
		
		getChildren().addAll(label, textField);
	}

	public String getText() {
		return textField.getText().trim();
	}

}
