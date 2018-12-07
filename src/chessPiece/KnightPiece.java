package chessPiece;

import java.util.ArrayList;

import javafx.util.Pair;
import main.Main;

public class KnightPiece extends ChessPiece {
	private ArrayList<Pair<Integer, Integer>> move;

	public KnightPiece(double x, double y, int player) {
		super(x, y, player, "images/knight");
		setMove();
	}

	private void setMove() {
		move = new ArrayList<Pair<Integer, Integer>>();
		move.add(new Pair<Integer, Integer>(-1, -2));
		move.add(new Pair<Integer, Integer>(1, -2));
		move.add(new Pair<Integer, Integer>(-1, 2));
		move.add(new Pair<Integer, Integer>(1, 2));
		move.add(new Pair<Integer, Integer>(-2, -1));
		move.add(new Pair<Integer, Integer>(-2, 1));
		move.add(new Pair<Integer, Integer>(2, -1));
		move.add(new Pair<Integer, Integer>(2, 1));
	}

	@Override
	protected void onActive() {
		Main.getGameScreen().resetBoard();
		int x = (int) getX();
		int y = (int) getY();
		Main.getGameScreen().setClickedChess(this);
		for (int i = 0; i < move.size(); i++) {
			int X = x + move.get(i).getKey();
			int Y = y + move.get(i).getValue();
			if (X > 6 || X < 1 || Y > 6 || Y < 1)
				continue;
			ChessPiece temp = Main.getGameScreen().findChessPiece(X, Y);
			if (temp != null && temp.getPlayer() == Main.getGameScreen().getTurn())
				continue;
			Main.getGameScreen().getBoard()[X][Y].active();

		}
	}

	@Override
	public boolean isMovable() {
		boolean isMovable = false;
		int x = (int) getX();
		int y = (int) getY();
		
		for (int i = 0; i < move.size() && !isMovable; i++) {
			int X = x + move.get(i).getKey();
			int Y = y + move.get(i).getValue();
			
			if (X > 6 || X < 1 || Y > 6 || Y < 1)
				continue;
			
			ChessPiece temp = Main.getGameScreen().findChessPiece(X, Y);
			if (temp != null && temp.getPlayer() == Main.getGameScreen().getTurn())
				continue;

			isMovable = true;
		}
		
		return isMovable;
	}
	
}
