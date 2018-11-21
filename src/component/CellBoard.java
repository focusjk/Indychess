package component;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CellBoard extends ImageView {
	private int x;
	private int y;
	private int color;
	
	public CellBoard(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		this.color = ((x+y)%2)+1;
		setImage( new Image(ClassLoader.getSystemResource("images/cell" + this.color + ".png").toString()));
		setLayoutX(260 + (80 *(x-1)));
		setLayoutY(110 + (80 *(y-1)));
	}
	

}
