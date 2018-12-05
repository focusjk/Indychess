package logic;

import component.Button;
import component.InputField;
import scene.Login;

public class LoginManager {
	private InputField player1Input;
	private InputField player2Input;
	private Thread backgroundThread;
	private Login loginScreen;

	public LoginManager(Login loginScreen, InputField player1Input, InputField player2Input, Button button) {
		super();
		this.loginScreen = loginScreen;
		setPlayer1Input(player1Input);
		setPlayer2Input(player2Input);
		

		this.backgroundThread = new Thread(() -> {
			int i = 1;
			while (true) {
				try {
					Thread.sleep(35);
					loginScreen.setBgNumber(i);
					i++;
					i %= 40;
					System.out.println(i);
				} catch (InterruptedException e) {
					e.printStackTrace();
					System.out.println("Stop Background Thread");
					break;
				}
			}
		});
		this.backgroundThread.start();
	}

	public InputField getPlayer1Input() {
		return player1Input;
	}

	public void setPlayer1Input(InputField player1Input) {
		this.player1Input = player1Input;
	}

	public InputField getPlayer2Input() {
		return player2Input;
	}

	public void setPlayer2Input(InputField player2Input) {
		this.player2Input = player2Input;
	}


	public void stopBg() {
		backgroundThread.interrupt();
	}

}
