package chessPiece;

public interface Movable {
	void onClicked();
	boolean isMovable();
	boolean canKillKing();
}
