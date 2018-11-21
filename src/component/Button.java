package component;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public abstract class Button extends ImageView implements Clickable {
	private String imgName;
	public Button(String imgName, double x, double y) {
		super();
		this.imgName = imgName;
		setImg(imgName);
		if (x != 0)
			setLayoutX(x);
		if (y != 0)
			setLayoutY(y);
		
		setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				setImg(imgName+"2");
				event.consume();
			}
		});
		
		setOnMouseExited(new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent event) {
				setImg(imgName);
				event.consume();
			}
		});

	}
	public void setImg(String imgName) {
		setImage(new Image(ClassLoader.getSystemResource(imgName+".png").toString()));
	}
	
}
