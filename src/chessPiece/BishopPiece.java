package chessPiece;

import main.Main;

public class BishopPiece extends ChessPiece {

	public BishopPiece(double x, double y, int player) {
		super(x, y, player, "images/bishop");
	}

	@Override
	public void onClicked() {
		Main.getGameScreen().resetBoard();
		int x = (int) getX();
		int y = (int) getY();
		Main.getGameScreen().setClickedChess(this);
		getMove(x - 1, y - 1, -1, -1);
		getMove(x + 1, y - 1, 1, -1);
		getMove(x - 1, y + 1, -1, 1);
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
