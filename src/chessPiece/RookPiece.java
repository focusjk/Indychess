package chessPiece;

public class RookPiece extends ChessPiece {

	public RookPiece(int x, int y, int player) {
		super(x, y, player, ClassLoader.getSystemResource("images/rook"+player+".png").toString());
	}

}
