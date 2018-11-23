package chessPiece;

import javafx.util.Pair;

public class BishopPiece extends ChessPiece {
	
	public BishopPiece(double x, double y, int player) {
		super(x, y, player, "images/bishop");
		setMove();
	}
	
	@Override
	protected void setMove() {
		for(int i=1;i<6;i++) {
			move.add(new Pair<Integer, Integer>(i, i));
		}
	}
	@Override
	public void onClicked() {
		isClicked = getX() * 10 + getY();
		
	}

	@Override
	protected void getMovable() {
		// TODO Auto-generated method stub
		
	}
	

}
