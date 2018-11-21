package chessPiece;

public class KnightPiece extends ChessPiece {

	public KnightPiece(int x, int y, int player) {
		super(x, y, player, ClassLoader.getSystemResource("images/knight"+player+".png").toString());
		// TODO Auto-generated constructor stub
	}

}
