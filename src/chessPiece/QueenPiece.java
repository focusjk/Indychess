package chessPiece;

import main.Main;

public class QueenPiece extends ChessPiece {

	public QueenPiece(double x, double y, int player) {
		super(x, y, player, "images/queen");
	}

	@Override
	protected void onActive() {
		Main.getGameScreen().resetBoard();
		int x = (int) getX();
		int y = (int) getY();
		Main.getGameScreen().setClickedChess(this);
		getMove(x - 1, y - 1, -1, -1, true);
		getMove(x, y - 1, 0, -1, true);
		getMove(x + 1, y - 1, 1, -1, true);
		getMove(x - 1, y, -1, 0, true);
		getMove(x + 1, y, 1, 0, true);
		getMove(x - 1, y + 1, -1, 1, true);
		getMove(x, y + 1, 0, 1, true);
		getMove(x + 1, y + 1, 1, 1, true);
	}

	private boolean getMove(int x, int y, int addX, int addY, boolean isClicked) {
		if (x > 6 || x < 1 || y > 6 || y < 1)
			return false;
		ChessPiece temp = Main.getGameScreen().findChessPiece(x, y);
		if (temp != null && temp.getPlayer() == Main.getGameScreen().getTurn())
			return false;
		if (isClicked) {
			Main.getGameScreen().getBoard()[x][y].active();
			if (temp == null)
				getMove(x + addX, y + addY, addX, addY, isClicked);
		}
		return true;
	}

	private boolean findKing(int x, int y, int addX, int addY) {
		if (x > 6 || x < 1 || y > 6 || y < 1)
			return false;
		ChessPiece temp = Main.getGameScreen().findChessPiece(x, y);
		if (temp != null && temp.getPlayer() == Main.getGameScreen().getTurn())
			return false;
		if(temp instanceof KingPiece)
			return true;
		if (temp == null)
			return findKing(x + addX, y + addY, addX, addY);

		return false;
	}

	@Override
	public boolean isMovable() {
		int x = (int) getX();
		int y = (int) getY();
		return getMove(x - 1, y - 1, -1, -1, false) || getMove(x, y - 1, 0, -1, false)
				|| getMove(x + 1, y - 1, 1, -1, false) || getMove(x - 1, y, -1, 0, false)
				|| getMove(x + 1, y, 1, 0, false) || getMove(x - 1, y + 1, -1, 1, false)
				|| getMove(x, y + 1, 0, 1, false) || getMove(x + 1, y + 1, 1, 1, false);
	}

	@Override
	public boolean canKillKing() {
		int x = (int) getX();
		int y = (int) getY();
		return findKing(x - 1, y - 1, -1, -1) || findKing(x, y - 1, 0, -1) || findKing(x + 1, y - 1, 1, -1)
				|| findKing(x - 1, y, -1, 0) || findKing(x + 1, y, 1, 0) || findKing(x - 1, y + 1, -1, 1)
				|| findKing(x, y + 1, 0, 1) || findKing(x + 1, y + 1, 1, 1);
	}

}
