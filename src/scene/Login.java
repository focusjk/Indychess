package scene;

import component.InputField;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class Login extends VBox{

	public Login() {
		//set screen
		setPrefHeight(700);
		setPrefWidth(1000);
		setPadding(new Insets(50,350,100,350));
		setSpacing(15);
		setAlignment(Pos.CENTER);
		
		//set logo
		ImageView logo = new ImageView(new Image(ClassLoader.getSystemResource("images/logo.png").toString()));
        logo.setFitWidth(200);
        logo.setPreserveRatio(true);
        
        getChildren().add(logo);
       
        //set input player1
        InputField player1 = new InputField("PLAYER 1", "Input name");
		getChildren().add(player1);
		
        //set input player2
        InputField player2 = new InputField("PLAYER 2", "Input name");
		getChildren().add(player2);
		
		//start button
		Button startButton = new Button("Let's start");
		getChildren().add(startButton);
	}
	

}
