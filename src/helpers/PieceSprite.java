package helpers;

import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToggleButton;

/**
 *
 * @author Jared Cassarly
 */
public class PieceSprite {
    
    final private String filename;
    final private Image sprite;
    
    /**
     * Creates a new PieceSprite using the piece's id to choose what image to use
     * @param id the identification number for the piece
     */
    public PieceSprite(int id) {
        filename = getFilename(id);
        sprite = getSprite(filename);
    }
    
    /**
     * Returns the image of the sprite at location "resources/" + f + ".png"
     * If an IOException is thrown, an ErrorMessage box appears
     * @param f the name of the .png file without the .png
     * @return the image at "resources/" + f + ".png"
     */
    private Image getSprite(String f) {
        try {
            Image img = ImageIO.read(getClass().getClassLoader().getResource("resources/" + f + ".png"));
            return img;
        } catch (IOException ex) {
            Message.errorMessage("Image did not load");
            return null;
        }
    }
    
    /**
     * Loads the sprite onto the specified JButton (the sprite is now the icon)
     * @param b the JButton to load the sprite onto
     */
    public void loadSprite(JButton b) {
        b.setIcon(new ImageIcon(sprite));
    }
    
    /**
     * Loads the sprite onto the specified JToggleButton (the sprite is now the icon)
     * @param b the JToggleButton to load the sprite onto
     */
    public void loadSprite(JToggleButton b) {
        b.setIcon(new ImageIcon(sprite));
    }
    
    /**
     * Unloads the sprite from the specified JButton
     * @param b the JButton to unload the sprite from.
     */
    public void unloadSprite(JButton b) {
        if (b.getIcon() != null) {
            b.setIcon(null);
        }
    }
    
    /**
     * Unloads the sprite from the specified JToggleButton
     * @param b the JToggleButton to unload the sprite from.
     */
    public void unloadSprite(JToggleButton b) {
        if (b.getIcon() != null) {
            b.setIcon(null);
        }
    }
    
    /**
     * Gives the coordinates of a sprite for a piece given the piece's id
     * @param id the id of the piece
     * @return the coordinates of the piece.  If the piece is not found, coordinates -1,-1 are returned
     */
    private static String getFilename(int id) {
        String rtn;
        switch (id) {
            case 1: rtn = "w_pawn"; break; // white pawn
            case 2: rtn = "w_knight"; break; // white knight
            case 3: rtn = "w_bishop"; break; // white bishop
            case 4: rtn = "w_rook"; break; // white rook
            case 5: rtn = "w_queen"; break; // white queen
            case 6: rtn = "w_king"; break; // white king
            case 7: rtn = "b_pawn"; break; // black pawn
            case 8: rtn = "b_knight"; break; // black knight
            case 9: rtn = "b_bishop"; break; // black bishop
            case 10: rtn = "b_rook"; break; // black rook
            case 11: rtn = "b_queen"; break; // black queen
            case 12: rtn = "b_king"; break; // black king
            default: rtn = null; break;// error as its not a piece
        }
        return rtn;
    }
    
}
