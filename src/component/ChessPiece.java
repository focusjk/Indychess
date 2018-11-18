package component;

import java.util.ArrayList;

public abstract class ChessPiece {
	protected String owner;
	protected char id;
	protected ChessLocation chessLocation;

	public ChessPiece(String owner, ChessLocation chessLocation) {
		this.owner = owner;
		this.chessLocation = chessLocation;
	}

	public abstract boolean moveTo(ChessLocation chessLocation);

	public abstract ArrayList<ChessLocation> possibleMove();

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public char getId() {
		return id;
	}

	public void setId(char id) {
		this.id = id;
	}

	public ChessLocation getChessLocation() {
		return chessLocation;
	}

	public void setChessLocation(ChessLocation chessLocation) {
		this.chessLocation = chessLocation;
	}
	
	

}
