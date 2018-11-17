package component;

public class QueenPiece extends ChessPiece {

	public QueenPiece(int x, int y, int player) {
		super(x, y, player, ClassLoader.getSystemResource("images/queen"+player+".png").toString());
		// TODO Auto-generated constructor stub
	}

}
