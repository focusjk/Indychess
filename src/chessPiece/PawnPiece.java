package chessPiece;

import main.Main;

public class PawnPiece extends ChessPiece {
	private boolean isFirstMove;

	public PawnPiece(double x, double y, int player) {
		super(x, y, player, "images/pawn");
		isFirstMove = true;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onClicked() {
		Main.getGameScreen().resetBoard();
		int x = (int) getX();
		int y = (int) getY();
		isClicked = getX() * 10 + getY();
		clickedPiece = this; 
		if (isFirstMove) {
			if (getPlayer() == 1) {
				if (x <= 6 && x >= 1 && y + 1 <= 6 && y + 1 >= 1
						&& Main.getGameScreen().findChessPiece(x, y + 1) == null)
					Main.getGameScreen().getBoard()[x][y + 1].active();
				if (x <= 6 && x >= 1 && y + 2 <= 6 && y + 2 >= 1
						&& Main.getGameScreen().findChessPiece(x, y + 2) == null
						&& Main.getGameScreen().findChessPiece(x, y + 1) == null)
					Main.getGameScreen().getBoard()[x][y + 2].active();
				toKill(x - 1, y + 1);
				toKill(x + 1, y + 1);
			} else {
				if (x <= 6 && x >= 1 && y - 1 <= 6 && y - 1 >= 1
						&& Main.getGameScreen().findChessPiece(x, y - 1) == null)
					Main.getGameScreen().getBoard()[x][y - 1].active();
				if (x <= 6 && x >= 1 && y - 2 <= 6 && y - 2 >= 1
						&& Main.getGameScreen().findChessPiece(x, y - 2) == null
						&& Main.getGameScreen().findChessPiece(x, y - 1) == null)
					Main.getGameScreen().getBoard()[x][y - 2].active();
				toKill(x - 1, y - 1);
				toKill(x + 1, y - 1);
			}
		}

	}

	public void getMove1(int x, int y) {
		if (x > 6 || x < 1 || y > 6 || y < 1)
			return;
		if (Main.getGameScreen().findChessPiece(x, y) != null)
			return;
		Main.getGameScreen().getBoard()[x][y].active();
	}

	public void toKill(int x, int y) {
		if (x > 6 || x < 1 || y > 6 || y < 1)
			return;
		ChessPiece temp = Main.getGameScreen().findChessPiece(x, y);
		if (temp == null || temp.getPlayer() == Main.getGameScreen().getTurn())
			return;
		Main.getGameScreen().getBoard()[x][y].active();
	}

}
