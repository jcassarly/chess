package pieces;

import gameboard.Square;

/**
 *
 * @author Jared Cassarly and David Wood
 */
public class Knight extends Piece {
    
    public Knight(boolean isWhite) {
        super(isWhite, 2);
    }
public boolean[][] getMoveSet(Square[][] sqs)
    {
        boolean[][] rtn = new boolean[sqs.length][sqs[0].length]; //initializes array of boolean values
        for (int r = 0; r < rtn.length; r++) {
            for (int c = 0; c < rtn[0].length; c++) {
                rtn[r][c] = false;
            }
        }
        for (int r = 0; r < rtn.length; r++) {
            for (int c = 0; c < rtn[0].length; c++) {
                if (sqs[r][c].getPiece() == this) {
                    
                    if (r > 0 && c < rtn[0].length-2) {
                        if (sqs[r-1][c+2].getPiece() == null) { //blank square
                            rtn[r-1][c+2] = true;
                        }
                        if (sqs[r-1][c+2].getPiece() != null && sqs[r-1][c+2].getPiece().isWhite() != this.isWhite()) { //other color's piece (take piece)
                            rtn[r-1][c+2] = true;
                        }
                    }
                     if (r > 0 && c > 1) {
                        if (sqs[r-1][c-2].getPiece() == null) {
                            rtn[r-1][c-2] = true;
                        }
                        if (sqs[r-1][c-2].getPiece() != null && sqs[r-1][c-2].getPiece().isWhite() != this.isWhite()) {
                            rtn[r-1][c-2] = true;
                        }
                    }
                     if (r < rtn.length-1 && c < rtn[0].length-2) {
                        if (sqs[r+1][c+2].getPiece() == null) {
                            rtn[r+1][c+2] = true;
                        }
                        if (sqs[r+1][c+2].getPiece() != null && sqs[r+1][c+2].getPiece().isWhite() != this.isWhite()) {
                            rtn[r+1][c+2] = true;
                        }
                    }
                     if (r < rtn.length-1 && c > 1) {
                        if (sqs[r+1][c-2].getPiece() == null) {
                            rtn[r+1][c-2] = true;
                        }
                        if (sqs[r+1][c-2].getPiece() != null && sqs[r+1][c-2].getPiece().isWhite() != this.isWhite()) {
                            rtn[r+1][c-2] = true;
                        }
                    }
                     if (r < rtn.length-2 && c < rtn[0].length-1) {
                        if (sqs[r+2][c+1].getPiece() == null) {
                            rtn[r+2][c+1] = true;
                        }
                        if (sqs[r+2][c+1].getPiece() != null && sqs[r+2][c+1].getPiece().isWhite() != this.isWhite()) {
                            rtn[r+2][c+1] = true;
                        }
                    }
                     if (r > 1 && c < rtn[0].length-1) {
                        if (sqs[r-2][c+1].getPiece() == null) {
                            rtn[r-2][c+1] = true;
                        }
                        if (sqs[r-2][c+1].getPiece() != null && sqs[r-2][c+1].getPiece().isWhite() != this.isWhite()) {
                            rtn[r-2][c+1] = true;
                        }
                    }
                     if (r < rtn.length-2 && c > 0) {
                        if (sqs[r+2][c-1].getPiece() == null) {
                            rtn[r+2][c-1] = true;
                        }
                        if (sqs[r+2][c-1].getPiece() != null && sqs[r+2][c-1].getPiece().isWhite() != this.isWhite()) {
                            rtn[r+2][c-1] = true;
                        }
                    }
                     if (r > 1 && c > 0) {
                        if (sqs[r-2][c-1].getPiece() == null) {
                            rtn[r-2][c-1] = true;
                        }
                        if (sqs[r-2][c-1].getPiece() != null && sqs[r-2][c-1].getPiece().isWhite() != this.isWhite()) {
                            rtn[r-2][c-1] = true;
                        }
                    }             
                }
            }
        }
        return rtn;        
    }
}

