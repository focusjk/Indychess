package component;


public class BishopPiece extends ChessPiece {

	public BishopPiece(int x, int y, int player) {
		super(x, y, player, ClassLoader.getSystemResource("images/bishop"+player+".png").toString());
		// TODO Auto-generated constructor stub
	}

}
