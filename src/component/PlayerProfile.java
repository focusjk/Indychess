package component;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Random;

public class PlayerProfile extends Pane {
	private int number;
	private int player;
	private Text nameTag;
	private ImageView arrow;

	public PlayerProfile(String name, int player) {
		super();
		this.player = player;
		Random rand = new Random();
		this.number = rand.nextInt(9) + 1;

		// set screen
		setPrefHeight(700);
		setPrefWidth(1000);

		// set emoji1
		ImageView emoji = new ImageView(
				new Image(ClassLoader.getSystemResource("images/emoji" + this.number + ".png").toString()));
		emoji.setLayoutX(10);
		emoji.setLayoutY(10);

		// text
		nameTag = new Text(name);
//		nameTag.setFont(ClassLoader.getSystemResource("font/AndaleMono.ttf").toString());
		nameTag.setFont(Font.font("AndaleMono", 40));
		Text playerTag = new Text("PLAYER" + this.player);
		playerTag.setFont(Font.font("AndaleMono", 15));

		// arrow
		arrow = new ImageView(
				new Image(ClassLoader.getSystemResource("images/redArrow" + this.player + ".png").toString()));
		arrow.setVisible(false);

		if (this.player == 1) {
			emoji.setLayoutX(20);
			emoji.setLayoutY(20);
			nameTag.setLayoutX(139);
			nameTag.setLayoutY(60);
			playerTag.setLayoutX(140);
			playerTag.setLayoutY(85);

			// arrow
			arrow.setLayoutX(45);
			arrow.setLayoutY(150);

		} else {
			emoji.setLayoutX(20);
			emoji.setLayoutY(570);
			nameTag.setLayoutX(139);
			nameTag.setLayoutY(650);
			playerTag.setLayoutX(140);
			playerTag.setLayoutY(675);

			// arrow
			arrow.setLayoutX(45);
			arrow.setLayoutY(490);
		}

		getChildren().addAll(emoji, nameTag, playerTag, arrow);
	}

	public String getName() {
		return nameTag.getText();
	}

	public void setName(String name) {
		nameTag.setText(name);
	}

	public void setTurn(boolean e) {
		arrow.setVisible(e);
	}

}
