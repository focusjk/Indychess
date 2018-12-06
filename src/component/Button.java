package component;

import exception.ImageNotFoundException;
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
		try {
			setImage(1);
		} catch (ImageNotFoundException e) {
			System.out.println("Can not setImage in Button constructor");
		}
		if (x != 0)
			setLayoutX(x);
		if (y != 0)
			setLayoutY(y);

		setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				try {
					setImage(2);
				} catch (ImageNotFoundException e) {
					System.out.println("Can not setImage in Button mouse entered");
				}
				event.consume();
			}
		});

		setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				try {
					setImage(1);
				} catch (ImageNotFoundException e) {
					System.out.println("Can not setImage in Button mouse exited");
				}
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

	private void setImage(int type) throws ImageNotFoundException {
		try {
			if (type == 1) {
				setImage(new Image(ClassLoader.getSystemResource(imageName + ".png").toString()));
			} else if (type == 2) {
				setImage(new Image(ClassLoader.getSystemResource(imageName + "2.png").toString()));
			}
		} catch (NullPointerException e) {
			setImage(new Image(ClassLoader.getSystemResource("images/errorIcon.png").toString()));
			throw new ImageNotFoundException(2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
