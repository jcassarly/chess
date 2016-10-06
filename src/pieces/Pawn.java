package pieces;

import gameboard.Square;
import helpers.Move;
import java.util.ArrayList;

/**
 *
 * @author Jared Cassarly and David Wood
 */
public class Pawn extends Piece {
    
    public Pawn(boolean isWhite) {
        super(isWhite, 1);
    }
    
    @Override
    public boolean[][] getMoveSet(Square[][] sqs)
    {
        boolean[][] rtn = new boolean[sqs.length][sqs[0].length]; //initializes array of boolean values
        for (int r = 0; r < rtn.length; r++) {
            for (int c = 0; c < rtn[0].length; c++) {
                rtn[r][c] = false;
            }
        }
        ArrayList<Move> log = sqs[0][0].getTheParent().getTheParent().getLog();
        Move last = null;
        if (log.size() > 0) {
            last = log.get(log.size() - 1);
        }
        for (int r = 0; r < rtn.length; r++) {
            for (int c = 0; c < rtn[0].length; c++) {
                if (sqs[r][c].getPiece() == this) {
                    // if white and not at other side of board from start
                    if (this.isWhite() && r > 0) {
                        // straight ahead
                        if (sqs[r-1][c].getPiece() == null) {
                            rtn[r-1][c] = true;
                            // if first move, then allow double move forward
                            if (this == sqs[6][c].getPiece() && sqs[4][c].getPiece() == null) {
                                rtn[4][c] = true;
                            }
                        }
                        
                        
                        
                        // attack right
                        if (c < 7 && sqs[r-1][c+1].getPiece() != null && !sqs[r-1][c+1].getPiece().isWhite()) {
                            rtn[r-1][c+1] = true;
                        }
                        // En Passant
                        else if (c < 7 && r == 3 && last != null 
                                && sqs[r-1][c+1].getPiece() == null && sqs[r][c+1].getPiece() != null 
                                && sqs[r][c+1].getPiece().getPieceID() == 7
                                && last.getX1() == r-2 && last.getY1() == c+1
                                && last.getX2() == r && last.getY2() == c+1) {
                            rtn[r-1][c+1] = true;
                        }
                        
                        // attack left
                        if (c > 0 && sqs[r-1][c-1].getPiece() != null && !sqs[r-1][c-1].getPiece().isWhite()) {
                            rtn[r-1][c-1] = true;
                        }
                        // En Passant
                        else if (c > 0 && r == 3 && last != null 
                                && sqs[r-1][c-1].getPiece() == null && sqs[r][c-1].getPiece() != null 
                                && sqs[r][c-1].getPiece().getPieceID() == 7
                                && last.getX1() == r-2 && last.getY1() == c-1
                                && last.getX2() == r && last.getY2() == c-1) {
                            rtn[r-1][c-1] = true;
                        }
                    }
                    // if black and not at other side of board from start
                    else if (!this.isWhite() && r < 7) {
                        // straight ahead
                        if (sqs[r+1][c].getPiece() == null) {
                            rtn[r+1][c] = true;
                            // if first move, then allow double move forward
                            if (this == sqs[1][c].getPiece() && sqs[3][c].getPiece() == null) {
                                rtn[3][c] = true;
                            }
                        }
                        
                        // attack right
                        if (c < 7 && sqs[r+1][c+1].getPiece() != null && sqs[r+1][c+1].getPiece().isWhite()) {
                            rtn[r+1][c+1] = true;
                        }
                        // En Passant
                        else if (c < 7 && r == 4 && last != null 
                                && sqs[r+1][c+1].getPiece() == null && sqs[r][c+1].getPiece() != null 
                                && sqs[r][c+1].getPiece().getPieceID() == 1
                                && last.getX1() == r+2 && last.getY1() == c+1
                                && last.getX2() == r && last.getY2() == c+1) {
                            rtn[r+1][c+1] = true;
                        }
                        
                        // attack left
                        if (c > 0 && sqs[r+1][c-1].getPiece() != null && sqs[r+1][c-1].getPiece().isWhite()) {
                            rtn[r+1][c-1] = true;
                        }
                        // En Passant
                        else if (c > 0 && r == 4 && last != null 
                                && sqs[r+1][c-1].getPiece() == null && sqs[r][c-1].getPiece() != null 
                                && sqs[r][c-1].getPiece().getPieceID() == 1
                                && last.getX1() == r+2 && last.getY1() == c-1
                                && last.getX2() == r && last.getY2() == c-1) {
                            rtn[r+1][c-1] = true;
                        }
                    }
                }
            }
        }
        return rtn;
        
    }

}
