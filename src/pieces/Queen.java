package pieces;

import gameboard.Square;

/**
 *
 * @author Jared Cassarly and David Wood
 */
public class Queen extends Piece {
    
    public Queen(boolean isWhite) {
        super(isWhite, 5);
    }

    @Override
    public boolean[][] getMoveSet(Square[][] sqs) {
        boolean[][] rMoveset = this.getMoveSetRook(sqs);
        boolean[][] bMoveset = this.getMoveSetBishop(sqs);
        
        if (bMoveset.length == rMoveset.length && bMoveset[0].length == rMoveset[0].length) {
            boolean[][] rtn = new boolean[sqs.length][sqs[0].length]; //initializes array of boolean values
            for (int i = 0; i < rtn.length; i++) {
                for (int c = 0; c < rtn[0].length; c++) {
                    rtn[i][c] = rMoveset[i][c] || bMoveset[i][c];
                }
            }
            return rtn;
        }
        else {
            boolean[][] rtn = new boolean[sqs.length][sqs[0].length]; //initializes array of boolean values
            for (int i = 0; i < rtn.length; i++) {
                for (int c = 0; c < rtn[0].length; c++) {
                    rtn[i][c] = false;
                }
            }
            return rtn;
        }
    }
    
    private boolean[][] getMoveSetRook(Square[][] sqs)
    {
        boolean[][] rtn = new boolean[sqs.length][sqs[0].length]; //initializes array of boolean values
        for (int r = 0; r < rtn.length; r++) {
            for (int c = 0; c < rtn[0].length; c++) {
                rtn[r][c] = false;
            }
        }
        
        int x = -1;
        int y = -1;
        
        for (int r = 0; r < rtn.length; r++) {
            for (int c = 0; c < rtn[0].length; c++) {
                if (sqs[r][c].getPiece() == this) {                 
                    x = r;
                    y = c;
                }
            }
        }
        
        if (x >= 0 && y >= 0) {
            int r = x;
            int c = y+1;
            while (c < rtn[0].length) {
                // if its another piece of the opposite color
                if (sqs[r][c].getPiece() != null && sqs[r][c].getPiece().isWhite() != this.isWhite()) {
                    // return one last true
                    rtn[r][c] = true;
                    break;
                }
                // if its another piece and the same color
                else if (sqs[r][c].getPiece() != null && sqs[r][c].getPiece().isWhite() == this.isWhite()) {
                    // stop this path
                    break;
                }
                // if theres nothing there
                else if (sqs[r][c].getPiece() == null) {
                    rtn[r][c] = true;
                }
                //r++;
                c++;
            }
            
            r = x-1;
            c = y;
            while (r >= 0) {
                // if its another piece of the opposite color
                if (sqs[r][c].getPiece() != null && sqs[r][c].getPiece().isWhite() != this.isWhite()) {
                    rtn[r][c] = true;
                    break;
                }
                // if its another piece and the same color
                else if (sqs[r][c].getPiece() != null && sqs[r][c].getPiece().isWhite() == this.isWhite()) {
                    break;
                }
                // if theres nothing there
                else if (sqs[r][c].getPiece() == null) {
                    rtn[r][c] = true;
                }
                r--;
                //c--;
            }
            
            r = x;
            c = y-1;
            while (c >= 0) {
                // if its another piece of the opposite color
                if (sqs[r][c].getPiece() != null && sqs[r][c].getPiece().isWhite() != this.isWhite()) {
                    rtn[r][c] = true;
                    break;
                }
                // if its another piece and the same color
                else if (sqs[r][c].getPiece() != null && sqs[r][c].getPiece().isWhite() == this.isWhite()) {
                    break;
                }
                // if theres nothing there
                else if (sqs[r][c].getPiece() == null) {
                    rtn[r][c] = true;
                }
                //r--;
                c--;
            }
            
            r = x+1;
            c = y;
            while (r < rtn.length) {
                // if its another piece of the opposite color
                if (sqs[r][c].getPiece() != null && sqs[r][c].getPiece().isWhite() != this.isWhite()) {
                    rtn[r][c] = true;
                    break;
                }
                // if its another piece and the same color
                else if (sqs[r][c].getPiece() != null && sqs[r][c].getPiece().isWhite() == this.isWhite()) {
                    break;
                }
                // if theres nothing there
                else if (sqs[r][c].getPiece() == null) {
                    rtn[r][c] = true;
                }
                r++;
                //c++;
            }
        }
        return rtn;
    }
    
    private boolean[][] getMoveSetBishop(Square[][] sqs)
    {
  
        boolean[][] rtn = new boolean[sqs.length][sqs[0].length]; //initializes array of boolean values
        for (int r = 0; r < rtn.length; r++) {
            for (int c = 0; c < rtn[0].length; c++) {
                rtn[r][c] = false;
            }
        }
        
        int x = -1;
        int y = -1;
        
        for (int r = 0; r < rtn.length; r++) {
            for (int c = 0; c < rtn[0].length; c++) {
                if (sqs[r][c].getPiece() == this) {                 
                    x = r;
                    y = c;
                }
            }
        }
        
        if (x >= 0 && y >= 0) {
            int r = x+1;
            int c = y+1;
            while (r < rtn.length && c < rtn[0].length) {
                // if its another piece of the opposite color
                if (sqs[r][c].getPiece() != null && sqs[r][c].getPiece().isWhite() != this.isWhite()) {
                    // return one last true
                    rtn[r][c] = true;
                    break;
                }
                // if its another piece and the same color
                else if (sqs[r][c].getPiece() != null && sqs[r][c].getPiece().isWhite() == this.isWhite()) {
                    // stop this path
                    break;
                }
                // if theres nothing there
                else if (sqs[r][c].getPiece() == null) {
                    rtn[r][c] = true;
                }
                r++;
                c++;
            }
            
            r = x+1;
            c = y-1;
            while (r < rtn.length && c >= 0) {
                // if its another piece of the opposite color
                if (sqs[r][c].getPiece() != null && sqs[r][c].getPiece().isWhite() != this.isWhite()) {
                    rtn[r][c] = true;
                    break;
                }
                // if its another piece and the same color
                else if (sqs[r][c].getPiece() != null && sqs[r][c].getPiece().isWhite() == this.isWhite()) {
                    break;
                }
                // if theres nothing there
                else if (sqs[r][c].getPiece() == null) {
                    rtn[r][c] = true;
                }
                r++;
                c--;
            }
            
            r = x-1;
            c = y-1;
            while (r >= 0 && c >= 0) {
                // if its another piece of the opposite color
                if (sqs[r][c].getPiece() != null && sqs[r][c].getPiece().isWhite() != this.isWhite()) {
                    rtn[r][c] = true;
                    break;
                }
                // if its another piece and the same color
                else if (sqs[r][c].getPiece() != null && sqs[r][c].getPiece().isWhite() == this.isWhite()) {
                    break;
                }
                // if theres nothing there
                else if (sqs[r][c].getPiece() == null) {
                    rtn[r][c] = true;
                }
                r--;
                c--;
            }
            
            r = x-1;
            c = y+1;
            while (r >= 0 && c < rtn[0].length) {
                // if its another piece of the opposite color
                if (sqs[r][c].getPiece() != null && sqs[r][c].getPiece().isWhite() != this.isWhite()) {
                    rtn[r][c] = true;
                    break;
                }
                // if its another piece and the same color
                else if (sqs[r][c].getPiece() != null && sqs[r][c].getPiece().isWhite() == this.isWhite()) {
                    break;
                }
                // if theres nothing there
                else if (sqs[r][c].getPiece() == null) {
                    rtn[r][c] = true;
                }
                r--;
                c++;
            }
        }
        return rtn;
        
    }
}
