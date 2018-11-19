package chess;

import component.ChessPiece;

/**
 * The Chessboard class is responsiable for mulipulating the board
 * by moving and removing pieces.
 */
public class ChessBoard {
    private ChessPiece[][] board;
    public static final int BOARD_SIZE=6;

    /**
     * Creates the board instance.
     */
    public ChessBoard() {
        board = new ChessPiece[BOARD_SIZE][BOARD_SIZE];
    }

    /**
     * Checks if piece is at a specificed location.
     * @param row Row of the location.
     * @param col Col of the location.
     * @return Boolean of if theres a piece on location or not.
     */
    public boolean isPieceAt(int row, int col) {
        return board[row][col] != null;
    }

    /**
     * Places piece at location, if piece already exisit there, the old piece will be overwritten.
     * @param piece The piece to move.
     * @param location The location to move to.
     */
    public void placePieceAt(ChessPiece piece, ChessLocation location) {
        if (isPieceAt(location.getRow(), location.getCol())) {
            removePieceAt(location);
        }
        if (piece.getChessLocation() != null) {
            removePieceAt(piece.getChessLocation());
        }
        board[location.getRow()][location.getCol()] = piece;
        piece.setChessLocation(location);
    }

    /**
     * Removes piece at location.
     * @param location The location to remove at.
     */
    private void removePieceAt(ChessLocation location) {
        board[location.getRow()][location.getCol()] = null;
    }

    /**
     * Checks if the location is within the board.
     * @param location The location to check.
     * @return Boolean of if the location is in bounds or not.
     */
    public static boolean locationInBounds(ChessLocation location) {
        return location.getRow() >= 0 && 
               location.getRow() < BOARD_SIZE && 
               location.getCol() >= 0 &&
               location.getCol() < BOARD_SIZE;
    }

    /**
     * Gets piece at location of board.
     * @param location Location to find piece at.
     * @return Piece at location/
     */
    public ChessPiece getPieceAt(ChessLocation location) {
        return board[location.getRow()][location.getCol()];
    }

    /**
     * Displays board with P being Piece.
     */
    @Override
    public String toString() {
        String s = "  0 1 2 3 4 5\n";
        for (int row = 0; row < BOARD_SIZE; row++) {
            s += row;
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (board[row][col] != null) {
                    s += " " + board[row][col].getId();
                } else {
                    s += " -";
                }
            }
            s += "\n";
        }
        return s;
    }
}
