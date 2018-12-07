package chessPiece;

import java.util.ArrayList;

import javafx.util.Pair;
import main.Main;

public class KingPiece extends ChessPiece {
	private ArrayList<Pair<Integer, Integer>> move;

	public KingPiece(double x, double y, int player) {
		super(x, y, player, "images/king");
		setMove();
	}

	private void setMove() {
		move = new ArrayList<Pair<Integer, Integer>>();
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
			if (X == Main.getGameScreen().getStar().getX() && Y == Main.getGameScreen().getStar().getY())
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

			if (X == Main.getGameScreen().getStar().getX() && Y == Main.getGameScreen().getStar().getY())
				continue;

			isMovable = true;
		}
		return isMovable;
	}
	

}
