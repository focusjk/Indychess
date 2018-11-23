package chessPiece;

import javafx.util.Pair;

public class KingPiece extends ChessPiece {

	public KingPiece(double x, double y, int player) {
		super(x, y, player, "images/king");
		setMove();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setMove() {
		move.add(new Pair<Integer, Integer>(-1, -1));
		move.add(new Pair<Integer, Integer>(0, -1));
		move.add(new Pair<Integer, Integer>(1, -1));
		move.add(new Pair<Integer, Integer>(-1, 0));
		move.add(new Pair<Integer, Integer>(1, 0));
		move.add(new Pair<Integer, Integer>(-1, 1));
		move.add(new Pair<Integer, Integer>(0, 1));
		move.add(new Pair<Integer, Integer>(1, 1));

	}

	@Override
	public void onClicked() {
		int x = (int)getX();
		int y = (int)getY();
		isClicked = getX() * 10 + getY();
		for(int i =0;i<move.size();i++) {
//			board[x+move.get(i).getKey()][y+move.get(i).getValue()].active();
		}
		
	}

	@Override
	protected void getMovable() {
		// TODO Auto-generated method stub

	}

}
