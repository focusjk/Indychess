package exception;

public class ImageNotFoundException extends Exception {

	public ImageNotFoundException(int code) {
//		System.err.println("Image is not found");
		if (code == 1)
			System.err.println("ChessPiece Image is not found");
		else if (code == 2)
			System.err.println("Button Image is not found");
		else if (code == 3)
			System.err.println("Player Profile Image is not found");
		else if (code == 4)
			System.err.println("Star Image is not found");
		else if (code == 5)
			System.err.println("CongratModal Image is not found");
		else if (code == 6)
			System.err.println("Login Image is not found");
		else
			System.err.println("Image is not found");
	}

}
