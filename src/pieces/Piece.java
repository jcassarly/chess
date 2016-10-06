package pieces;

import gameboard.Square;
import helpers.PieceSprite;

/**
 *
 * @author Jared Cassarly and David Wood
 */
public abstract class Piece {
    
    final static public int KING_ID = 6;
    final static public int QUEEN_ID = 5;
    final static public int ROOK_ID = 4;
    final static public int BISHOP_ID = 3;
    final static public int KNIGHT_ID = 2;
    final static public int PAWN_ID = 1;
    
    final private boolean isWhite;
    final protected int ID; // ID of the piece itself, not taking into account color, getPieceID() does that
    //final protected Square parent; // - this variable may be necessary so that checking which piece is being used on the board is easier
    private final PieceSprite sprite;
    private boolean isQueened;
    
    /**
     * Constructs  a new Piece given a color and id number of the type of piece
     * @param isWhite whether or not the piece is white or black, true for white, false for black
     * @param id the ID number of the piece, 1-6, 1=pawn, 2=knight, 3=bishop, 4=rook, 5=queen, 6=king
     */
    public Piece(boolean isWhite, int id) {
        this.isWhite = isWhite;
        isQueened = false;
        ID = id;
        sprite = new PieceSprite(this.getPieceID());
    }
    
    /**
     * Returns the PieceSprite object 
     * @return the PieceSprite
     */
    public PieceSprite getSprite() {
        return sprite;
    }
    
    /**
     * Gives the boolean color value
     * @return whether the piece is white of not.  true = white, false = black
     */
    public boolean isWhite() {
        return isWhite;
    }
    
    
    /**
     * Gives the ID of the piece assuming the id number of the piece and the color
     * @return the ID of the piece. 
     * 1=white pawn, 2=white knight, 3=white bishop, 4=white rook, 5=white queen, 6=white king
     * 7=black pawn, 8=black knight, 9=black bishop, 10=black rook, 11=black queen, 12=black king
     */
    public int getPieceID() {
        if (isWhite) {
            return ID;
        }
        else {
            return ID + 6;
        }
    }
    
    /**
     * Gives the legal and illegal move-set of the piece
     * @param sqs the 8x8 array or squares used in a chess game, containing a Piece object, either null or a type of piece
     * @return the legal move-set
     */
    public boolean[][] getMoveSet(Square[][] sqs) {
        // rtn = the variable to be returned
        boolean[][] rtn = new boolean[sqs.length][sqs[0].length];
        // goes through the array of squares and if the move is legal, 
        // the corresponding space in the rtn array is set true true, false otherwise
        for (int r = 0; r < sqs.length; r++) {
            for (int c = 0; c <  sqs[0].length; c++) {
                if (this.isLegal(sqs[r][c])) {
                    rtn[r][c] = true;
                }
                else {
                    rtn[r][c] = false;
                }
            }
        }
        // returns the legal move-set
        return rtn;
    }
    
    public boolean canCastle(Square[][] sqs, boolean isMoveToRight) {
        return false;
    }
    
    /**
     * Checks if moving to a reference to a square is a legal move the piece contained in the current square
     * @param sq the reference to a square to check if moving to that square is legal
     * @return true if the move is legal, false otherwise
     */
    public boolean isLegal(Square sq) {
        // returns true for now for testing purposes until piece specific methods are created.
        return true;
    }
    
}
