package gameboard;

import java.awt.Color;
import javax.swing.JButton;
import pieces.*;

/**
 *
 * @author Jared Cassarly
 */
public class Square extends javax.swing.JPanel {
    
    final static public Color DARK_WOOD = new Color(177,141,42);
    final static public Color LIGHT_WOOD = new Color(215,173,57);
    
    final static public Color WHITE_GRAVEYARD = Color.DARK_GRAY;
    final static public Color BLACK_GRAVEYARD = Color.GRAY;
    
    final static public Color HAS_MORE = Color.CYAN;
    final static public Color MOVING = Color.GREEN;
    
    final static public Color ENABLED = Color.BLUE;
    final static public Color DISABLED = Color.BLACK;
    
    private boolean enabled;
    private ChessBoard parent;
    
    private Piece p;

    /**
     * Creates new form Square
     */
    public Square() {
        p = null;
        enabled = true;
        initComponents();
    }
    
    /**
     * Creates a new square that has either the dark wood or light wood
     * @param darkOrLight the color to set the Square background color
     */
    public Square(Color darkOrLight) {
        p = null;
        enabled = true;
        initComponents();
        button.setBackground(darkOrLight);
        button.setForeground(darkOrLight);
    }
    
    /**
     * Set the ChessBoard that this Square is contained in.
     * @param parent the ChessBoard to set as the parent
     */
    public void setParent(ChessBoard parent) {
        this.parent = parent;
    }
    
    /**
     * Returns the ChessBoard that contains this Square
     * @return the ChessBoard that contains this Square
     */
    public ChessBoard getTheParent() {
        return parent;
    }
    
    /**
     * Returns the Piece this Square contains
     * @return the Piece this Square contains
     */
    public Piece getPiece() {
        return p;
    }
    
    /**
     * Set the piece this Square should have and loads the sprite
     * @param piece the Piece to have this Square contains
     */
    public void setPiece(Piece piece) {
        p = piece;
        
        if (p != null) {
            p.getSprite().loadSprite(button);
        }
    }
    
    /**
     * Set the piece this Square should have and load the sprite if setSprite is true
     * @param piece the Piece this Square should have
     * @param setSprite load sprite of piece if true, do not load if false
     */
    public void setPiece(Piece piece, boolean setSprite) {
        p = piece;
        
        if (p != null && setSprite) {
            p.getSprite().loadSprite(button);
        }
    }
    
    /**
     * Set the piece this Square should have based on the color and id of the piece to be created
     * @param isWhite true for white, false for black
     * @param id the ID of the piece that does not include color (1-6)
     */
    public void setPiece(boolean isWhite, int id) {
        switch (id) {
            case 1: p = new Pawn(isWhite); break;
            case 2: p = new Knight(isWhite); break;
            case 3: p = new Bishop(isWhite); break;
            case 4: p = new Rook(isWhite); break;
            case 5: p = new Queen(isWhite); break;
            case 6: p = new King(isWhite); break;
            default: p = null; break;
        }
        
        if (p != null) {
            p.getSprite().loadSprite(button);
        }
    }
    
    /**
     * Set the piece this Square should have based on the id number of the piece (1-6 for white, 7-12 for black)
     * @param id the of the Piece to be added to the Square (1-12)
     */
    public void setPiece(int id) {
        // unload the sprite if the piece exists
        if (p != null) {
            p.getSprite().unloadSprite(button);
        }
        switch (id) {
            // 1-6 make white pieces
            case 1: p = new Pawn(true); break;
            case 2: p = new Knight(true); break;
            case 3: p = new Bishop(true); break;
            case 4: p = new Rook(true); break;
            case 5: p = new Queen(true); break;
            case 6: p = new King(true); break;
            // 7-12 make black pieces
            case 7: p = new Pawn(false); break;
            case 8: p = new Knight(false); break;
            case 9: p = new Bishop(false); break;
            case 10: p = new Rook(false); break;
            case 11: p = new Queen(false); break;
            case 12: p = new King(false); break;
            default: p = null; break;
        }
        
        // load the sprite if piece exists
        if (p != null) {
            p.getSprite().loadSprite(button);
        }
    }
    
    /**
     * Returns the button owned by this Square
     * @return the button owned by this Square
     */
    public JButton getButton() {
        return button;
    }
    
    /**
     * Returns whether the Square is enabled
     * @return true if the square is enabled, false otherwise
     */
    public boolean isPanelEnabled() {
        return enabled;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        button = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 0, 0));

        button.setBackground(new java.awt.Color(215, 173, 57));
        button.setForeground(new java.awt.Color(177, 141, 42));
        button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(button, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(button, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonActionPerformed
        if (parent != null) {
            if (parent.isMoving()) {
                parent.moveToLocation(this);
            }
            else {
                parent.setupForMove(this);
            }
        }
    }//GEN-LAST:event_buttonActionPerformed

    /**
     * Enable or disable the Square for clicking and change the background to reflect that
     * @param enabled true if the Square should be enabled, false otherwise
     */
    public void setPanelEnabled(boolean enabled) {
        if (enabled) {
            this.setBackground(ENABLED);
            this.setForeground(ENABLED);
        }
        else {
            this.setBackground(DISABLED);
            this.setForeground(DISABLED);
        }
        this.enabled = enabled;
    }
    
    /**
     * Get the row of the Piece in the board of squares in the parent ChessBoard
     * @return the row of the Piece, returns -1 if not found
     */
    public int getPieceX() {
        Square[][] board = parent.getBoard();
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board.length; c++) {
                if (this == board[r][c]) {
                    return r;
                }
            }
        }
        return -1;
    }
    
    /**
     * Get the column of the Piece in the board if squares in the parent ChessBoard
     * @return the column of the Piece, returns -1 if not found
     */
    public int getPieceY() {
        Square[][] board = parent.getBoard();
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board.length; c++) {
                if (this == board[r][c]) {
                    return c;
                }
            }
        }
        return -1;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button;
    // End of variables declaration//GEN-END:variables

}
