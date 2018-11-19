package component;

import chess.ChessGame;
import chess.ChessLocation;

public class Rook extends ChessPiece {
    
    /**
     * Creates a new Rook piece.
     * @param owner Owner string.
     * @param initialLocation Location to set Rook in.
     * @param game Game that the Rook belongs too.
     */
    public Rook(String owner, ChessLocation initialLocation, ChessGame game) {
        super(owner, initialLocation, game);
        if (owner.equalsIgnoreCase("player1")) {
            id = 'R';
        } else if (owner.equalsIgnoreCase("player2")) {
            id = 'r';
        }
    }

    /** Checks if more is valid for Rook, then moves the piece.
     * @return Valid move or not.
     */
    @Override
    public boolean moveTo(ChessLocation location) {
        if ((chessLocation.getRow() == location.getRow()) !=
            (chessLocation.getCol() == location.getCol())) {

            return checkLineOfSight(chessLocation, location) && super.moveTo(location);
        }
        return false;
    }

    /**
     * Updates the threatening locations.
     */
    @Override
    protected void updateThreateningLocation() {
        threateningLocations.clear();

        super.updateVertical(1);
        super.updateVertical(-1);
        super.updateHorizontal(1);
        super.updateHorizontal(-1);
    }
}
