package chessPiece;

public class KingPiece extends ChessPiece {

	public KingPiece(int x, int y, int player) {
		super(x, y, player, ClassLoader.getSystemResource("images/king"+player+".png").toString());
		// TODO Auto-generated constructor stub
	}

}
