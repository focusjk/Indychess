package chessPiece;

import main.Main;

public class PawnPiece extends ChessPiece {
	private boolean isFirstMove;
	private boolean isQueen;

	public PawnPiece(double x, double y, int player) {
		super(x, y, player, "images/pawn");
		isFirstMove = true;
		isQueen = false;
	}

	@Override
	public void onClicked() {
		Main.getGameScreen().resetBoard();
		int x = (int) getX();
		int y = (int) getY();
		Main.getGameScreen().setClickedChess(this);
		if (isQueen) {
			queenMove(x - 1, y - 1, -1, -1);
			queenMove(x, y - 1, 0, -1);
			queenMove(x + 1, y - 1, 1, -1);
			queenMove(x - 1, y, -1, 0);
			queenMove(x + 1, y, 1, 0);
			queenMove(x - 1, y + 1, -1, 1);
			queenMove(x, y + 1, 0, 1);
			queenMove(x + 1, y + 1, 1, 1);
		} else {
			if (getPlayer() == 1) {
				if (x <= 6 && x >= 1 && y + 1 <= 6 && y + 1 >= 1
						&& Main.getGameScreen().findChessPiece(x, y + 1) == null)
					Main.getGameScreen().getBoard()[x][y + 1].active();
				if (isFirstMove && x <= 6 && x >= 1 && y + 2 <= 6 && y + 2 >= 1
						&& Main.getGameScreen().findChessPiece(x, y + 2) == null
						&& Main.getGameScreen().findChessPiece(x, y + 1) == null)
					Main.getGameScreen().getBoard()[x][y + 2].active();
				toKill(x - 1, y + 1);
				toKill(x + 1, y + 1);
			} else {
				if (x <= 6 && x >= 1 && y - 1 <= 6 && y - 1 >= 1
						&& Main.getGameScreen().findChessPiece(x, y - 1) == null)
					Main.getGameScreen().getBoard()[x][y - 1].active();
				if (isFirstMove && x <= 6 && x >= 1 && y - 2 <= 6 && y - 2 >= 1
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

	public void queenMove(int x, int y, int addX, int addY) {
		if (x > 6 || x < 1 || y > 6 || y < 1)
			return;
		ChessPiece temp = Main.getGameScreen().findChessPiece(x, y);
		if (temp != null && temp.getPlayer() == Main.getGameScreen().getTurn())
			return;
		Main.getGameScreen().getBoard()[x][y].active();
		if (temp == null)
			queenMove(x + addX, y + addY, addX, addY);
	}

	public void setIsFirstMove() {
		isFirstMove = false;
	}

	public void setQueen(int y) {
		if((getPlayer() == 1 && y == 6 )|| (getPlayer() == 2 && y == 1 ))
			isQueen = true;
	}
	
	

}
