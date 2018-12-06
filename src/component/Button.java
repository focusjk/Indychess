package component;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;

public abstract class Button extends ImageView {
	private String imageName;
	private AudioClip clickSound = new AudioClip(ClassLoader.getSystemResource("sound/clickSound.mp3").toString());

	public Button(String imageName, double x, double y) {
		super();
		this.imageName = imageName;
		setImage(1);
		if (x != 0)
			setLayoutX(x);
		if (y != 0)
			setLayoutY(y);

		setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				setImage(2);
				event.consume();
			}
		});

		setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				setImage(1);
				event.consume();
			}
		});

		setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				clickSound.play();
				onClicked();
				event.consume();
			}
		});
	}

	public abstract void onClicked();

	private void setImage(int type) {
		try {
			if (type == 1) {
				setImage(new Image(ClassLoader.getSystemResource(imageName + ".png").toString()));
			} else if (type == 2) {
				setImage(new Image(ClassLoader.getSystemResource(imageName + "2.png").toString()));
			}
		} catch (NullPointerException e) {
			setImage(new Image(ClassLoader.getSystemResource("images/errorIcon.png").toString()));
			System.err.println("Button Image is not found");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
