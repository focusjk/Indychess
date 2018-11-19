package component;

import java.util.ArrayList;

import chess.ChessBoard;
import chess.ChessGame;
import chess.ChessLocation;

/**
 * ChessPiece is a general piece class for chess.
 */
public abstract class ChessPiece implements ChessPieceInterface {

    protected ChessGame chessGame;
    protected String owner;
    protected ChessLocation chessLocation;
    protected char id;
    protected ArrayList<ChessLocation> threateningLocations;

    protected abstract void updateThreateningLocation();

    /**
     * Sets the private members of the ChessPiece. Such as it's owner
     * the lcoation and the game it belongs to.
     * @param owner Owner string.
     * @param initialLocation Location to set knight in.
     * @param game Game that the knight belongs too.
     */
    public ChessPiece(String owner, ChessLocation initialLocation, ChessGame game) {
        this.owner = owner;
        chessLocation = null; 
        chessGame = game;
        threateningLocations = new ArrayList<>();
        chessGame.getChessBoard().placePieceAt(this, initialLocation);
    }

    /**
     * Checks for the line of sight of the move.
     * @param start Start location.
     * @param end End location.
     * @return Valid move or not
     */
    protected boolean checkLineOfSight(ChessLocation start, ChessLocation end) {
        // Vertical
        if (start.getCol() == end.getCol()) { 
            int one = (start.getRow() - end.getRow() < 0) ? 1: -1;
            for (int row = start.getRow() + one; row < end.getRow(); row += one) {
                if (chessGame.getChessBoard().isPieceAt(row, start.getCol())) {
                    return false;
                }
            }
            return true;
        }

        // Horizontal
        if (start.getRow() == end.getRow()) {
            int one = (start.getCol() - end.getCol() < 0) ? 1: -1;
            for (int col = start.getCol() + one; col < end.getCol(); col += one) {
                if (chessGame.getChessBoard().isPieceAt(start.getRow(), col)) {
                    return false;
                }
            }
            return true;
        }

        // Diagonal
        // Case 1 : Slope -1
        // Case 2 : Slope 1
        if (start.getCol() - end.getCol() == 
            start.getRow() - end.getRow()) {

            int one = (start.getRow() - end.getRow() < 0) ? 1: -1;
            for (int inc = one; Math.abs(inc) < Math.abs(start.getRow() - end.getRow()); inc += one) {
                if (chessGame.getChessBoard().isPieceAt(start.getRow() + inc, start.getCol() + inc)) {
                    return false;
                }
            }
            return true;
        } else if (start.getCol() - end.getCol() * -1 == 
                   start.getRow() - end.getCol()) {

            int one = (start.getRow() - end.getRow() < 0) ? 1: -1;
            int negOne = one * -1;
            for (int inc = one; Math.abs(inc) < Math.abs(start.getRow() - end.getRow()); inc += one) {
                if (chessGame.getChessBoard().isPieceAt(start.getRow() + inc, start.getCol() + (inc * negOne))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Updates the threathening locations for a vertical direction.
     * @param one The direction to check in
     */
    protected void updateVertical(int one) {
        ChessLocation location = new ChessLocation(chessLocation.getRow() + one, chessLocation.getCol());
        int inc = one;
        while (ChessBoard.locationInBounds(location)) {
            ChessPiece piece = chessGame.getChessBoard().getPieceAt(location);
            if (piece != null) {
                if (!piece.getOwner().equalsIgnoreCase(owner)) {
                    threateningLocations.add(location); 
                    return;
                } else if (!chessLocation.equals(location)) {
                    threateningLocations.add(new ChessLocation(location.getRow() - one, location.getCol())); 
                    return;
                }
            } else {
                location = new ChessLocation(location.getRow() + one, location.getCol());
            }
        }
    }

    /**
     * Updates the threathening locations for a horizonal direction.
     * @param one The direction to check in
     */
    protected void updateHorizontal(int one) {
        ChessLocation location = new ChessLocation(chessLocation.getRow(), chessLocation.getCol() + one);
        while (ChessBoard.locationInBounds(location)) {
            ChessPiece piece = chessGame.getChessBoard().getPieceAt(location);
            if (piece != null) {
                if (!piece.getOwner().equalsIgnoreCase(owner)) {
                    threateningLocations.add(location); 
                    return;
                } else if (!chessLocation.equals(location)) {
                    threateningLocations.add(new ChessLocation(location.getRow(), location.getCol() - one)); 
                    return;
                }
            } else {
                location = new ChessLocation(location.getRow(), location.getCol() + one); 
            }
        }
    }

    /**
     * Updates the threathening locations for a diagonal direction.
     * @param rowOne The row direction to check in
     * @param colOne The col direction to check in
     */
    protected void updateDiagonal(int rowOne, int colOne) {
        ChessLocation location = new ChessLocation(chessLocation.getRow() + rowOne, chessLocation.getCol() + colOne);
        while (ChessBoard.locationInBounds(location)) {
            ChessPiece piece = chessGame.getChessBoard().getPieceAt(location);
            if (piece != null) {
                if (!piece.getOwner().equalsIgnoreCase(owner)) {
                    threateningLocations.add(location); 
                    return;
                } else if (!chessLocation.equals(location)) {
                    threateningLocations.add(new ChessLocation(location.getRow() - rowOne, location.getCol() - colOne)); 
                    return;
                }
            } else {
                location = new ChessLocation(location.getRow() + rowOne, location.getCol() + colOne);
            }
        }
    }

    /**
     * Sets the location of the ChessPiece.
     * @param newLocation The new location of the knight.
     */
    public boolean moveTo(ChessLocation newLocation) {
        ChessBoard board = chessGame.getChessBoard();
        ChessPiece oldPiece = board.getPieceAt(newLocation);
        
        if (oldPiece == null ||
            oldPiece.getOwner() != owner) {
            
            board.placePieceAt(this, newLocation);
            return true;
        }
        return false;
    }

    /**
     * Returns the location of piece.
     * @return ChessLocation of the ChessPiece.
     */
    public ChessLocation getChessLocation() {
        return chessLocation;
    }

    /**
     * Sets the location of the piece.
     * @param location new location.
     */
    public void setChessLocation(ChessLocation location) {
        chessLocation = location;
    }

    /**
     * Gets the owner String
     * @return Owner string
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Gets the id of the piece.
     * @return Char of the id.
     */
    public char getId() {
        return id;
    }

    /**
     * Gets threateningLocation
     * @return ArrayList of ChessLocations
     */
    public ArrayList<ChessLocation> getThreateningLocations() {
        return threateningLocations;
    }
}
