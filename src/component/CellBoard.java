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
		setImg(this.color);
		setLayoutX(260 + (80 * (x - 1)));
		setLayoutY(110 + (80 * (y - 1)));
		setMouseTransparent(true);
	}

	public void inactive() {
		setImg(this.color);
		setMouseTransparent(true);
		isActive = false;
	}

	public void active() {
		setImg(3);
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

	private void setImg(int type) {
		if (type == 1)
			setImage(new Image(ClassLoader.getSystemResource("images/cell1.png").toString()));
		else if (type == 2)
			setImage(new Image(ClassLoader.getSystemResource("images/cell2.png").toString()));
		else if (type == 3)
			setImage(new Image(ClassLoader.getSystemResource("images/cell3.png").toString()));

	}

}
