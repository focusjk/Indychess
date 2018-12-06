package component;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Random;

public class PlayerProfile extends Pane {
	private int player;
	private String name;
	private Text nameTag;
	private ImageView arrow;
	private ImageView emoji;
	private int arrowPosition;
	private int up;
	private Thread arrowThread;

	public PlayerProfile(String name, int player) {
		super();
		this.player = player;
		this.name = name;

		// set emoji1
		Random rand = new Random();
		int number = rand.nextInt(9) + 1;
		emoji = new ImageView();
		emoji.setLayoutX(10);
		emoji.setLayoutY(10);
		setImg(number);

		// text
		nameTag = new Text(name);
		nameTag.setFont(Font.loadFont(ClassLoader.getSystemResource("font/CopperplateBold.ttf").toString(), 40));
		nameTag.setFill(Color.BLACK);

		Text playerTag = new Text("PLAYER " + this.player);
		playerTag.setFont(Font.loadFont(ClassLoader.getSystemResource("font/CopperplateBold.ttf").toString(), 15));
		playerTag.setFill(Color.GRAY);

		// arrow
		arrow = new ImageView();
		arrow.setVisible(false);
		setImg(0);

		arrowPosition = 0;
		up = 1;
		arrowThread = new Thread("arrow") {
			@Override
			public void run() {
				while (true) {
					try {
						sleep(60);
						arrowPosition += 1;
						if (arrowPosition > 10) {
							up *= -1;
							arrowPosition %= 10;
						}
						if (player == 1) {
							if (up == 1)
								arrow.setLayoutY(150 + arrowPosition);
							else
								arrow.setLayoutY(150 + 10 - arrowPosition);
						} else {
							if (up == 1)
								arrow.setLayoutY(490 - arrowPosition);
							else
								arrow.setLayoutY(490 - 10 + arrowPosition);
						}
					} catch (InterruptedException e) {
						System.err.println("Arrow: Thread is interrupted");
						break;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		};
		arrowThread.start();

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
		return name;
	}

	public void setName(String name) {
		this.name = name;
		nameTag.setText(name);
	}

	private void setImg(int type) {
		try {
			if (type == 0) {
				arrow.setImage(
						new Image(ClassLoader.getSystemResource("images/redArrow" + this.player + ".png").toString()));
			} else {
				emoji.setImage(new Image(ClassLoader.getSystemResource("images/emoji" + type + ".png").toString()));
			}
		} catch (NullPointerException e) {
			if (type == 0)
				arrow.setImage(new Image(ClassLoader.getSystemResource("images/errorIcon.png").toString()));
			else
				emoji.setImage(new Image(ClassLoader.getSystemResource("images/errorIcon.png").toString()));
			System.err.println("Player Profile Image is not found");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setTurn(boolean isPlaying) {
		arrow.setVisible(isPlaying);
	}

	public void stopThread() {
		this.arrowThread.interrupt();
	}

}
