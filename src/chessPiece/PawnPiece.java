package chessPiece;

public class PawnPiece extends ChessPiece {

	public PawnPiece(int x, int y, int player) {
		super(x, y, player, ClassLoader.getSystemResource("images/pawn"+player+".png").toString());
		// TODO Auto-generated constructor stub
	}

}
