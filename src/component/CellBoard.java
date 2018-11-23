package component;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CellBoard extends ImageView {
	private double x;
	private double y;
	private int color;
	private boolean isActive;

	public CellBoard(double x, double y) {
		super();
		this.x = x;
		this.y = y;
		this.color = (int) ((x + y) % 2) + 1;
		this.isActive = false;
		setImage(new Image(ClassLoader.getSystemResource("images/cell" + this.color + ".png").toString()));
		setLayoutX(260 + (80 * (x - 1)));
		setLayoutY(110 + (80 * (y - 1)));
		setMouseTransparent(true);
	}

	public void inactive() {
		setImage(new Image(ClassLoader.getSystemResource("images/cell" + this.color + ".png").toString()));
		setMouseTransparent(true);
		isActive = false;
	}

	public void active() {
		setImage(new Image(ClassLoader.getSystemResource("images/cell3.png").toString()));
		setMouseTransparent(false);
		isActive = true;
	}
	
	public boolean isActive() {
		return isActive;
	}

	public double getPositionX() {
		return x;
	}

	public double getPositionY() {
		return y;
	}

}
