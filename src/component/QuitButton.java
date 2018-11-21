package component;

public class QuitButton extends Button implements Clickable {

	public QuitButton() {
		super("images/resumeButton.png", 0, 0);
	}

	@Override
	public void onClicked() {
		System.out.println("Clicked Quit");

	}

}
