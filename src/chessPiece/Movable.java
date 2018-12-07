package chessPiece;

public interface Movable {
	void onClicked();
	void onMove(double x, double y);
	boolean isMovable();
}
