package chessPiece;

import main.Main;

public class PawnPiece extends ChessPiece {
	private boolean isFirstMove;

	public PawnPiece(double x, double y, int player) {
		super(x, y, player, "images/pawn");
		isFirstMove = true;
	}

	@Override
	protected void onActive() {
		Main.getGameScreen().resetBoard();
		int x = (int) getX();
		int y = (int) getY();
		Main.getGameScreen().setClickedChess(this);
		if (getPlayer() == 1) {
			if (x <= 6 && x >= 1 && y + 1 <= 6 && y + 1 >= 1 && Main.getGameScreen().findChessPiece(x, y + 1) == null)
				Main.getGameScreen().getBoard()[x][y + 1].active();
			if (isFirstMove && x <= 6 && x >= 1 && y + 2 <= 6 && y + 2 >= 1
					&& Main.getGameScreen().findChessPiece(x, y + 2) == null
					&& Main.getGameScreen().findChessPiece(x, y + 1) == null)
				Main.getGameScreen().getBoard()[x][y + 2].active();
			toKill(x - 1, y + 1, true);
			toKill(x + 1, y + 1, true);
		} else {
			if (x <= 6 && x >= 1 && y - 1 <= 6 && y - 1 >= 1 && Main.getGameScreen().findChessPiece(x, y - 1) == null)
				Main.getGameScreen().getBoard()[x][y - 1].active();
			if (isFirstMove && x <= 6 && x >= 1 && y - 2 <= 6 && y - 2 >= 1
					&& Main.getGameScreen().findChessPiece(x, y - 2) == null
					&& Main.getGameScreen().findChessPiece(x, y - 1) == null)
				Main.getGameScreen().getBoard()[x][y - 2].active();
			toKill(x - 1, y - 1, true);
			toKill(x + 1, y - 1, true);
		}
	}

	@Override
	public void onMove(double x, double y) {
		setPosition(x, y);
		setIsFirstMove();
		setQueen();
	}

	private boolean toKill(int x, int y, boolean isClicked) {
		if (x > 6 || x < 1 || y > 6 || y < 1)
			return false;
		ChessPiece temp = Main.getGameScreen().findChessPiece(x, y);
		if (temp == null || temp.getPlayer() == Main.getGameScreen().getTurn())
			return false;
		if (isClicked)
			Main.getGameScreen().getBoard()[x][y].active();
		return true;
	}

	public void setIsFirstMove() {
		isFirstMove = false;
	}

	public void setQueen() {
		if ((getPlayer() == 1 && getY() == 6) || (getPlayer() == 2 && getY() == 1)) {
			Main.getGameScreen().removeChessPiece(this);
			Main.getGameScreen().addChessPiece(new QueenPiece(getX(), getY(), getPlayer()));
		}
	}

	@Override
	public boolean isMovable() {
		int x = (int) getX();
		int y = (int) getY();

		if (getPlayer() == 1) {
			if (x <= 6 && x >= 1 && y + 1 <= 6 && y + 1 >= 1 && Main.getGameScreen().findChessPiece(x, y + 1) == null)
				return true;
			if (isFirstMove && x <= 6 && x >= 1 && y + 2 <= 6 && y + 2 >= 1
					&& Main.getGameScreen().findChessPiece(x, y + 2) == null
					&& Main.getGameScreen().findChessPiece(x, y + 1) == null)
				return true;
			return toKill(x - 1, y + 1, false) || toKill(x + 1, y + 1, false);
		} else {
			if (x <= 6 && x >= 1 && y - 1 <= 6 && y - 1 >= 1 && Main.getGameScreen().findChessPiece(x, y - 1) == null)
				return true;
			if (isFirstMove && x <= 6 && x >= 1 && y - 2 <= 6 && y - 2 >= 1
					&& Main.getGameScreen().findChessPiece(x, y - 2) == null
					&& Main.getGameScreen().findChessPiece(x, y - 1) == null)
				return true;
			return toKill(x - 1, y - 1, false) || toKill(x + 1, y - 1, false);
		}
	}
}
