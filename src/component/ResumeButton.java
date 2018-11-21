package component;

public class ResumeButton extends Button implements Clickable {

	public ResumeButton(String imgName, double x, double y) {
		super(imgName, x, y);
	}

	@Override
	public void onClicked() {
		System.out.println("Clicked Resume");

	}

}
