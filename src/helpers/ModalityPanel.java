package helpers;

import gameboard.ChessGame;
import gameboard.QueeningPanel;
import gameboard.Square;
import java.awt.Dialog;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *
 * @author Jared Cassarly
 */
public class ModalityPanel extends JDialog {
    
    /**
     * Creates a new Dialog with modality for queening
     * @param owner
     * @param isWhite
     * @param s 
     */
    public ModalityPanel(JFrame owner, boolean isWhite, Square s) {
        super(owner, "Queen a Piece", Dialog.ModalityType.DOCUMENT_MODAL);
        this.add(new QueeningPanel(isWhite, s, this));
        pack();
        this.setVisible(true);
    }
    
    public ModalityPanel(ChessGame owner) {
        super(owner, "Choose a Save File", Dialog.ModalityType.DOCUMENT_MODAL);
        this.add(new FileChoice(owner, this));
        pack();
        this.setVisible(true);
    }
    
    public ModalityPanel(ChessGame owner, int signifyLoad) {
        super(owner, "Choose a Load File", Dialog.ModalityType.DOCUMENT_MODAL);
        FileChoice fc = new FileChoice(owner, this);
        fc.allowNew(false);
        this.add(fc);
        pack();
        this.setVisible(true);
    }
    
    public ModalityPanel(FileChoice parent, ModalityPanel owner) {
        super(owner, "Enter a new save name", Dialog.ModalityType.DOCUMENT_MODAL);
        this.add(new NewFile(parent, this));
        pack();
        this.setVisible(true);
    }
    
    /**
     * set undos
     * @param owner
     * @param isWhite 
     */
    public ModalityPanel(ChessGame owner, boolean isWhite) {
        super(owner, "Enter The Number of undos you want " + ((isWhite) ? "white": "black") + "to have.", Dialog.ModalityType.DOCUMENT_MODAL);
        this.add(new UndoSetter(owner, this, isWhite));
        pack();
        this.setVisible(true);
    }
    
    public ModalityPanel(ChessGame owner, Integer signifyReset) {
        super(owner, "Do you want to reset?", Dialog.ModalityType.DOCUMENT_MODAL);
        this.add(new ResetCheck(owner, this));
        pack();
        this.setVisible(true);
    }
}
