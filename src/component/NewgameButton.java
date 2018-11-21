package component;

public class NewgameButton extends Button implements Clickable {

	public NewgameButton(String imgName, double x, double y) {
		super(imgName, x, y);
	}

	@Override
	public void onClicked() {
		System.out.println("Clicked New gaame");

	}

}
