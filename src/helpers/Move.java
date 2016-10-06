package helpers;

/**
 *
 * @author Jared Cassarly
 */
final public class Move {
    final private int x1;
    final private int y1;
    final private int x2;
    final private int y2;
    final private int captureID;
    final private boolean isQueened;
    final private boolean isCastle;
    final private boolean isEnPassant;
    final private boolean isWhite;
    
    /**
     * Creates a new Move based on a string with (x1,y1), (x2,y2),
     * ID of the captured piece (0 if nothing captured),
     * whether or not the piece moving was queened (isQueened),
     * whether or not the move was a castle (isCastle),
     * and whether or not the move was white or black (true=white, false=black)
     * @param s in the form: integer,integer,integer,integer,integer,boolean,boolean,boolean
     *         which equals: x1,y1,x2,y2,captureID,isQueened,isCastle,isWhite
     */
    public Move(String s) {
        String[] parts = s.split(",");
        x1 = Integer.parseInt(parts[0]);
        y1 = Integer.parseInt(parts[1]);
        x2 = Integer.parseInt(parts[2]);
        y2 = Integer.parseInt(parts[3]);
        captureID = Integer.parseInt(parts[4]);
        isQueened = parts[5].equals("TRUE") || parts[5].equals("true");
        isCastle = parts[6].equals("TRUE") || parts[6].equals("true");
        isEnPassant = parts[7].equals("TRUE") || parts[7].equals("true");
        isWhite = parts[8].equals("TRUE") || parts[8].equals("true");
    }
    
    /**
     * Gives the starting row of the piece moving
     * @return the starting row
     */
    public int getX1() {
        return x1;
    }
    
    /**
     * Gives the starting column of the piece moving
     * @return the starting column
     */
    public int getY1() {
        return y1;
    }
    
    /**
     * Gives the ending row of the piece moving
     * @return the ending row
     */
    public int getX2() {
        return x2;
    }
    
    /**
     * Gives the ending column of the piece moving
     * @return the ending column
     */
    public int getY2() {
        return y2;
    }
    
    /**
     * Gives the ID of the piece that was captured in the move (the piece at x2,y2 before the move)
     * If no piece was captured, the ID is 0
     * @return the ID of the piece captured (1-12), if no piece was captured, 0 is returned
     */
    public int getCaptureID() {
        return captureID;
    }
    
    /**
     * Tells whether the move involved queening a piece
     * @return true if the move involved queening a piece. false otherwise
     */
    public boolean isQueened() {
        return isQueened;
    }
    
    /**
     * Tells whether the move involved castling
     * @return true if castling happened, false otherwise
     */
    public boolean isCastle() {
        return isCastle;
    }
    
    /**
     * Tells whether the move involved an En Passant
     * @return true is an En Passant happened, false otherwise
     */
    public boolean isEnPassant() {
        return isEnPassant;
    }
    
    /**
     * Tells whether the color of the piece moving was white or black
     * @return true if white, false if black
     */
    public boolean isWhite() {
        return isWhite;
    }
    
    /**
     * Gives the readout to that can be outputted to a log file with the string that is broken down in the constructor
     * @return the string in format x1,y1,x2,y2,captureID,isQueened,isCastle,isWhite
     */
    @Override
    public String toString() {
        return x1 + "," + y1 + "," + x2 + "," + y2 +  "," + captureID + "," + isQueened + "," + isCastle + "," + isEnPassant + "," + isWhite;
    }
}
