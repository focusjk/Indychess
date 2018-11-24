package chessPiece;

import main.Main;

public class QueenPiece extends ChessPiece {

	public QueenPiece(double x, double y, int player) {
		super(x, y, player, "images/queen");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onClicked() {
		Main.getGameScreen().resetBoard();
		int x = (int) getX();
		int y = (int) getY();
		Main.getGameScreen().setClickedChess(this);
		getMove(x - 1, y - 1, -1, -1);
		getMove(x, y - 1, 0, -1);
		getMove(x + 1, y - 1, 1, -1);
		getMove(x - 1, y, -1, 0);
		getMove(x + 1, y, 1, 0);
		getMove(x - 1, y + 1, -1, 1);
		getMove(x, y + 1, 0, 1);
		getMove(x + 1, y + 1, 1, 1);
	}

	public void getMove(int x, int y, int addX, int addY) {
		if (x > 6 || x < 1 || y > 6 || y < 1)
			return;
		ChessPiece temp = Main.getGameScreen().findChessPiece(x, y);
		if (temp != null && temp.getPlayer() == Main.getGameScreen().getTurn())
			return;
		Main.getGameScreen().getBoard()[x][y].active();
		if(temp==null) getMove(x + addX, y + addY, addX, addY);
	}

}
