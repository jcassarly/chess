package pieces;

import gameboard.Square;
import gameboard.ChessBoard;
import helpers.Move;
import java.util.ArrayList;

/**
 *
 * @author Jared Cassarly and David Wood
 */
public class King extends Piece{
    
    public King(boolean isWhite) {
        super(isWhite, 6);
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
                    
                    // move up
                    if (r > 0) {
                        if (sqs[r-1][c].getPiece() == null) {
                            rtn[r-1][c] = true;
                        }
                        if (sqs[r-1][c].getPiece() != null && sqs[r-1][c].getPiece().isWhite() != this.isWhite()) {
                            rtn[r-1][c] = true;
                        }
                    }
                    // move left
                     if (c > 0) {
                        if (sqs[r][c-1].getPiece() == null) {
                            rtn[r][c-1] = true;
                        }
                        if (sqs[r][c-1].getPiece() != null && sqs[r][c-1].getPiece().isWhite() != this.isWhite()) {
                            rtn[r][c-1] = true;
                        }
                    }
                    // move down
                     if (r < rtn.length-1) {
                        if (sqs[r+1][c].getPiece() == null) {
                            rtn[r+1][c] = true;
                        }
                        if (sqs[r+1][c].getPiece() != null && sqs[r+1][c].getPiece().isWhite() != this.isWhite()) {
                            rtn[r+1][c] = true;
                        }
                    }
                    // move right
                     if (c < rtn[0].length-1) {
                        if (sqs[r][c+1].getPiece() == null) {
                            rtn[r][c+1] = true;
                        }
                        if (sqs[r][c+1].getPiece() != null && sqs[r][c+1].getPiece().isWhite() != this.isWhite()) {
                            rtn[r][c+1] = true;
                        }
                    }
                    // move down and right
                     if (r < rtn.length-1 && c < rtn[0].length-1) {
                        if (sqs[r+1][c+1].getPiece() == null) {
                            rtn[r+1][c+1] = true;
                        }
                        if (sqs[r+1][c+1].getPiece() != null && sqs[r+1][c+1].getPiece().isWhite() != this.isWhite()) {
                            rtn[r+1][c+1] = true;
                        }
                    }
                    // move up and right
                     if (r > 0 && c < rtn[0].length-1) {
                        if (sqs[r-1][c+1].getPiece() == null) {
                            rtn[r-1][c+1] = true;
                        }
                        if (sqs[r-1][c+1].getPiece() != null && sqs[r-1][c+1].getPiece().isWhite() != this.isWhite()) {
                            rtn[r-1][c+1] = true;
                        }
                    }
                    // move down and left
                     if (r < rtn.length-1 && c > 0) {
                        if (sqs[r+1][c-1].getPiece() == null) {
                            rtn[r+1][c-1] = true;
                        }
                        if (sqs[r+1][c-1].getPiece() != null && sqs[r+1][c-1].getPiece().isWhite() != this.isWhite()) {
                            rtn[r+1][c-1] = true;
                        }
                    }
                    // move down and left
                     if (r > 0 && c > 0) {
                        if (sqs[r-1][c-1].getPiece() == null) {
                            rtn[r-1][c-1] = true;
                        }
                        if (sqs[r-1][c-1].getPiece() != null && sqs[r-1][c-1].getPiece().isWhite() != this.isWhite()) {
                            rtn[r-1][c-1] = true;
                        }
                    }             
                }
            }
        }
        return rtn;        
    }
    
    @Override
    public boolean canCastle(Square[][] sqs, boolean isMoveToRight)
    {
        ArrayList<Move> log = sqs[0][0].getTheParent().getTheParent().getLog();
        boolean canCastle = true;
        int xRow = (this.isWhite()) ? 7: 0;
        int yRook = (isMoveToRight) ? 7: 0;
        // check to make sure the king and rook have not moved
        for (int i = 0, n = log.size(); i < n; i++)
        {
            // if the king has moved or something has captured it somehow return false
            if ((log.get(i).getX1() == xRow && log.get(i).getY1() == 4) || (log.get(i).getX2() == xRow && log.get(i).getY2() == 4))
            {
                return false;
            }
            
            // if the rook has moved or something has captured it return false
            if ((log.get(i).getX1() == xRow && log.get(i).getY1() == yRook) || (log.get(i).getX2() == xRow && log.get(i).getY2() == yRook))
            {
                return false;
            }
        }
        
        int colFirstCheck = (isMoveToRight) ? 5: 3;
        int colSecondCheck = (isMoveToRight) ? 6: 2;
        
        // Check to make sure spaces are empty in direction
        if (sqs[xRow][colFirstCheck].getPiece() != null || sqs[xRow][colSecondCheck].getPiece() != null) 
        {
            return false;
        }
        
        if (!isMoveToRight && sqs[xRow][1].getPiece() != null) 
        {
            return false;
        }
        
        // if the king is in check return false
        if (ChessBoard.isKingInCheck(this.isWhite(), sqs)) 
        {
            return false;
        }
        
        // Check if the king would move through check
        if (!ChessBoard.takesOutOfCheck(sqs[xRow][4], sqs[xRow][colFirstCheck], sqs)) 
        {
            return false;
        }
        
        // Check if the king would move into check
        if (!ChessBoard.takesOutOfCheck(sqs[xRow][4], sqs[xRow][colSecondCheck], sqs))
        {
            return false;
        }
        
        // nothing meant false had to be returned, so return true because the king can check
        return true;
    }
}
