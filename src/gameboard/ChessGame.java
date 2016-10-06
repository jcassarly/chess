/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameboard;

import helpers.Message;
import helpers.ModalityPanel;
import helpers.Move;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import pieces.Piece;

/**
 *
 * @author Jared Cassarly
 */
public class ChessGame extends javax.swing.JFrame {
    
    // <editor-fold defaultstate="collapsed" desc="User Variables">
    private String filename;
    private ArrayList<Move> log;
    
    private int whiteUndos;
    private int blackUndos;
    private boolean useUndoLimits;
    private boolean autosave;
    private boolean isBeginner;
    
    public final static int DEFAULT_UNDOS = 3;
    
    public final static String PLACES = "/board.csv";
    public final static String LOG = "/log.csv";
    public final static String GRAVEYARD = "/graveyard.csv";
    public final static String UNDOS_LEFT = "/undos.csv";
    
    public final static String DEFAULT_FILENAME = "default";
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructor">
    /**
     * Creates new form ChessGame
     */
    public ChessGame() {
        // initialize variables to defaults
        filename = DEFAULT_FILENAME;
        log = new ArrayList<>();
        useUndoLimits = false;
        autosave = true;
        isBeginner = true;
        whiteUndos = DEFAULT_UNDOS;
        blackUndos = DEFAULT_UNDOS;
        
        // set up to save to the default save file
        (new File(DEFAULT_FILENAME)).mkdir();
        File f = new File(filename + LOG);
        if (!(f.exists() && !f.isDirectory())) {
            // create an empty default file if the file does not exist
            try {
                PrintWriter out = new PrintWriter(f);
                out.close();
            } catch (FileNotFoundException ex) {
                Message.errorMessage("Unidentified bug, please report");
            }
        }
        // if it already exists, load in the move log and reset to defaults
        else {
            loadMoveLog();
        }
        resetDefaultLog();
        
        // initialize the components in the window
        initComponents();
        
        // set the title of the window
        this.setTitle("Chess");
        
        // set the undo label to default values
        this.setUndoLabel();
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Accessor and Small Mutator Methods">
    /**
     * Get the graveyard that corresponds to the given color
     * @param isWhite the color in which true is white and false is black
     * @return the graveyard of color
     */
    public Graveyard getGraveyard(boolean isWhite) {
        if (isWhite) {
            return whiteGraveyard;
        }
        else {
            return blackGraveyard;
        }
    }
    
    /**
     * Get the current filename of the save file in use
     * @return the current filename
     */
    public String getFilename() {
        return filename;
    }
    
    /**
     * Set the current filename of the save file to the specified filename
     * @param f the specified filename to set as the save file name
     */
    public void setFilename(String f) {
        filename = f;
    }
    
    /**
     * Set the label that indicates "Check", "Checkmate", and "Stalemate" to the specified value
     * @param s String to set the label to
     */
    public void setCheckLabel(String s) {
        check.setText(s);
    }
    
    /**
     * Get the move log
     * @return the move log ArrayList
     */
    public ArrayList<Move> getLog() {
        return log;
    }
    
    /**
     * Check if the Check Label is null or not
     * @return true if the Check Label is null, false otherwise
     */
    public boolean isCheckLabelNull() {
        return check == null;
    }
    
    /**
     * Set the label that indicates whose turn it is
     * @param isWhite if the color of the current move is white, isWhite should be true, false otherwise
     */
    public void setCurrentMoveLabel(boolean isWhite) {
        String currentMoveLabel = (isWhite) ? "White": "Black";
        currentMove.setText(currentMoveLabel);
    }
    
    /**
     * Check if the Current Move Label is null or not
     * @return true if the Current Move Label is null, false otherwise
     */
    public boolean isCurrentMoveLabelNull() {
        return currentMove == null;
    }
    
    /**
     * Set the label that shows the number of undos left to whatever they are currently set at
     * If limits on undos are not in effect, this method makes the Undo Label indicate they have infinite undos
     */
    public void setUndoLabel() {
        if (whiteUndosRemaining != null) {
            if (useUndoLimits) {
                whiteUndosRemaining.setText("White Undos Remaining: " + whiteUndos);
                blackUndosRemaining.setText("Black Undos Remaining: " + blackUndos);
            }
            else {
                whiteUndosRemaining.setText("White Undos Remaining: Infinite");
                blackUndosRemaining.setText("Black Undos Remaining: Infinite");
            }
        }
    }
    
    /**
     * Set the number of undos a specified color has
     * @param isWhite the specified color; true if white, false if black
     * @param i number of undos to set the color to have
     */
    public void setUndos(boolean isWhite, int i) {
        if (isWhite) {
            whiteUndos = i;
        }
        else {
            blackUndos = i;
        }
    }
    
    /**
     * Turn on or off the autosave feature
     * @param on true if autosave should be turned on, false otherwise
     */
    public void setAutosave(boolean on) {
        autosave = on;
    }
    
    /**
     * Checks if autosave is on
     * @return true if autosave is on, false otherwise
     */
    public boolean isAutosave() {
        return autosave;
    }
    
    /**
     * Set the experience level of the game
     * @param isBeginner true if beginner, false otherwise
     */
    public void setExperience(boolean isBeginner) {
        this.isBeginner = isBeginner;
    }
    
    /**
     * Check what the experience level of the game is
     * @return true if beginner, false otherwise
     */
    public boolean isBeginner() {
        return isBeginner;
    }
    // </editor-fold>

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gameboard = new ChessBoard(this);
        whiteGraveyard = new Graveyard(true);
        blackGraveyard = new Graveyard(false);
        separator = new javax.swing.JSeparator();
        MoveLabel = new javax.swing.JLabel();
        currentMove = new javax.swing.JTextField();
        reset = new javax.swing.JButton();
        undo = new javax.swing.JButton();
        check = new javax.swing.JLabel();
        whiteUndosRemaining = new javax.swing.JLabel();
        blackUndosRemaining = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        loadMenu = new javax.swing.JMenuItem();
        reloadMenu = new javax.swing.JMenuItem();
        saveAsMenu = new javax.swing.JMenuItem();
        saveMenu = new javax.swing.JMenuItem();
        autosaveCheckbox = new javax.swing.JCheckBoxMenuItem();
        undoMenu = new javax.swing.JMenu();
        undoMenuItem = new javax.swing.JMenuItem();
        limitUndos = new javax.swing.JCheckBoxMenuItem();
        setWhiteUndos = new javax.swing.JMenuItem();
        setBlackUndos = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        beginner = new javax.swing.JRadioButtonMenuItem();
        advanced = new javax.swing.JRadioButtonMenuItem();
        helpMenu = new javax.swing.JMenu();
        helpMenuItem = new javax.swing.JMenuItem();
        aboutMenu = new javax.swing.JMenu();
        contactInfo = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        MoveLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        MoveLabel.setText("Current Move:");

        currentMove.setEditable(false);
        currentMove.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        currentMove.setText("White");

        reset.setText("Reset");
        reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetActionPerformed(evt);
            }
        });

        undo.setText("Undo");
        undo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                undoActionPerformed(evt);
            }
        });

        check.setFont(new java.awt.Font("Tahoma", 3, 36)); // NOI18N

        whiteUndosRemaining.setText("White Undos Remaining: ");

        blackUndosRemaining.setText("Black Undos Remaining: ");

        fileMenu.setText("File");

        loadMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        loadMenu.setText("Load");
        loadMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadMenuActionPerformed(evt);
            }
        });
        fileMenu.add(loadMenu);

        reloadMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        reloadMenu.setText("Reload");
        reloadMenu.setEnabled(false);
        reloadMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reloadMenuActionPerformed(evt);
            }
        });
        fileMenu.add(reloadMenu);

        saveAsMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        saveAsMenu.setText("Save As");
        saveAsMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAsMenuActionPerformed(evt);
            }
        });
        fileMenu.add(saveAsMenu);

        saveMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        saveMenu.setText("Save");
        saveMenu.setEnabled(false);
        saveMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveMenuActionPerformed(evt);
            }
        });
        fileMenu.add(saveMenu);

        autosaveCheckbox.setSelected(true);
        autosaveCheckbox.setText("Autosave");
        autosaveCheckbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                autosaveCheckboxActionPerformed(evt);
            }
        });
        fileMenu.add(autosaveCheckbox);

        undoMenu.setText("Undo");

        undoMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        undoMenuItem.setText("Undo Move");
        undoMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                undoMenuItemActionPerformed(evt);
            }
        });
        undoMenu.add(undoMenuItem);

        limitUndos.setSelected(true);
        limitUndos.setText("Limit Undos");
        limitUndos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limitUndosActionPerformed(evt);
            }
        });
        undoMenu.add(limitUndos);

        setWhiteUndos.setText("Set White's Undos");
        setWhiteUndos.setEnabled(false);
        setWhiteUndos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setWhiteUndosActionPerformed(evt);
            }
        });
        undoMenu.add(setWhiteUndos);

        setBlackUndos.setText("Set Black's Undos");
        setBlackUndos.setEnabled(false);
        setBlackUndos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setBlackUndosActionPerformed(evt);
            }
        });
        undoMenu.add(setBlackUndos);

        fileMenu.add(undoMenu);

        jMenu1.setText("Experience Level");

        beginner.setSelected(true);
        beginner.setText("Beginner");
        beginner.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beginnerActionPerformed(evt);
            }
        });
        jMenu1.add(beginner);

        advanced.setText("Advanced");
        advanced.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                advancedActionPerformed(evt);
            }
        });
        jMenu1.add(advanced);

        fileMenu.add(jMenu1);

        jMenuBar1.add(fileMenu);

        helpMenu.setText("Help");

        helpMenuItem.setText("Help");
        helpMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(helpMenuItem);

        jMenuBar1.add(helpMenu);

        aboutMenu.setText("About");

        contactInfo.setText("Contact Info");
        contactInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contactInfoActionPerformed(evt);
            }
        });
        aboutMenu.add(contactInfo);

        jMenuBar1.add(aboutMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(gameboard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(whiteGraveyard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(blackGraveyard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(separator)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(MoveLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(currentMove, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(whiteUndosRemaining)
                            .addComponent(blackUndosRemaining))
                        .addGap(199, 199, 199)
                        .addComponent(check, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(reset, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(undo, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(gameboard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(whiteGraveyard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(blackGraveyard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(separator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(check, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(MoveLabel)
                            .addComponent(currentMove, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(reset))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(undo)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(whiteUndosRemaining)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(blackUndosRemaining)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    // <editor-fold defaultstate="collapsed" desc="Buttons">
    private void saveMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveMenuActionPerformed
        save();
    }//GEN-LAST:event_saveMenuActionPerformed

    private void saveAsMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAsMenuActionPerformed
        // Open the save menu
        ModalityPanel panel = new ModalityPanel(this);
        // if the filename after editting by the save menu is the default enable saving and reloading as the state has not really been saved to  a permanent file
        if (!filename.equals(DEFAULT_FILENAME)) {
            saveMenu.setEnabled(true);
            reloadMenu.setEnabled(true);
            save();
        }
        // if the filename has changed, disable saving because everything is currently saved
        else {
            saveMenu.setEnabled(false);
            reloadMenu.setEnabled(false);
        }
    }//GEN-LAST:event_saveAsMenuActionPerformed

    private void loadMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadMenuActionPerformed
        // Open the load menu
        ModalityPanel panel = new ModalityPanel(this, 0);
        // if the filename after editting by the save menu is the default enable saving and reloading as the state has not really been saved to  a permanent file
        if (!filename.equals(DEFAULT_FILENAME)) {
            load();
            saveMenu.setEnabled(true);
            reloadMenu.setEnabled(true);
            save();
        }
        // if the filename has changed, disable saving because everything is currently saved
        else {
            saveMenu.setEnabled(false);
            reloadMenu.setEnabled(false);
        }
    }//GEN-LAST:event_loadMenuActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        resetDefaultLog();
    }//GEN-LAST:event_formWindowClosing

    private void resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetActionPerformed
        // open the reset menu
        ModalityPanel panel = new ModalityPanel(this, new Integer(0));
    }//GEN-LAST:event_resetActionPerformed

    private void undoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_undoActionPerformed
        // if undos are limited
        if (useUndoLimits) {
            
            int undos = (!gameboard.getCurrentMove()) ? whiteUndos: blackUndos;
            if (undos > 0) {
                if (undo()) {
                    if (gameboard.getCurrentMove()) {
                        whiteUndos--;
                    }
                    else {
                        blackUndos--;
                    }
                    this.saveUndos();
                    this.setUndoLabel();
                }
            }
        }
        // else undo the last move
        else {
            undo();
        }
    }//GEN-LAST:event_undoActionPerformed

    private void setWhiteUndosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setWhiteUndosActionPerformed
        ModalityPanel panel = new ModalityPanel(this, true);
    }//GEN-LAST:event_setWhiteUndosActionPerformed

    private void setBlackUndosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setBlackUndosActionPerformed
        ModalityPanel panel = new ModalityPanel(this, false);
    }//GEN-LAST:event_setBlackUndosActionPerformed

    private void undoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_undoMenuItemActionPerformed
        undo.doClick();
    }//GEN-LAST:event_undoMenuItemActionPerformed

    private void helpMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpMenuItemActionPerformed
        Message.infoMessage("The pieces that can move have a blue outline. This depends on who's turn it is. "
                + "\nClick these buttons to show their possible movesets"
                + "\nThe movesets will now be outlined in blue, if nothing is outlined in blue,"
                + "\nthe piece has no available moves"
                + "\nIf the piece has available moves, you may choose where you would like the piece to move"
                + "\nIf you dont want to move that piece, click on the piece again, it will deselect it"
                + "\n"
                + "\nIn order to save your game, choose the file item on the menu bar and choose \"Save As\""
                + "\nThen choose the save file you want to use or create a new one."
                + "\nYou can now save to that file with the \"Save\" menu item"
                + "\n"
                + "\nIn order to load a previously saved game, choose the file from the \"Load\" menu item"
                + "\nYou can then save that file with \"Save\""
                + "\n"
                + "\nYou can undo moves with the undo button in the bottom right hand corner or the undo menu item"
                + "\nBy default, you have infinite undos, if you want to have a limited number of undos, "
                + "\nchoose the \"Limit Undos\" menu item"
                + "\nYou can now choose to set the number of undos for black pieces and/or white pieces"
                + "\n"
                + "\nYou can reset the board with the reset button, it will bring up a window "
                + "\nchecking to make sure you really want to reset because once you do, it overwrites the save"
                + "\nif you want to start a new game, use the save as and then reset or restart the program.", "Help");
    }//GEN-LAST:event_helpMenuItemActionPerformed

    private void contactInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contactInfoActionPerformed
        Message.infoMessage("Questions?"
                + "\nContact Jared Cassarly at"
                + "\n   jaredcassarly@gmail.com"
                + "\nContact David Wood at"
                + "\n   dpduganwood@gmail.com", "Contact Info");
    }//GEN-LAST:event_contactInfoActionPerformed

    private void autosaveCheckboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_autosaveCheckboxActionPerformed
        autosave = !autosave;
    }//GEN-LAST:event_autosaveCheckboxActionPerformed

    private void limitUndosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limitUndosActionPerformed
        useUndoLimits = !useUndoLimits;
        setWhiteUndos.setEnabled(useUndoLimits);
        setBlackUndos.setEnabled(useUndoLimits);
        this.setUndoLabel();
    }//GEN-LAST:event_limitUndosActionPerformed

    private void reloadMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reloadMenuActionPerformed
        load();
    }//GEN-LAST:event_reloadMenuActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        resetDefaultLog();
    }//GEN-LAST:event_formWindowClosed

    private void beginnerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beginnerActionPerformed
        advanced.setSelected(!beginner.isSelected());
        isBeginner = beginner.isSelected();
        gameboard.newMoveEnabling();
    }//GEN-LAST:event_beginnerActionPerformed

    private void advancedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_advancedActionPerformed
        beginner.setSelected(!advanced.isSelected());
        isBeginner = beginner.isSelected();
        gameboard.newMoveEnabling();
    }//GEN-LAST:event_advancedActionPerformed
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Main">
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ChessGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChessGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChessGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChessGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChessGame().setVisible(true);
            }
        });
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Undo">
    public boolean undo() {
        int n = log.size();
        if (n > 0) {
            
            // Reset the move before undoing
            gameboard.newMoveEnabling();
            gameboard.resetMoving();
            
            // get the last move's info
            Move undoMove = log.get(n - 1);
            
            // find the Squares on the board
            Square[][] board = gameboard.getBoard();
            Square original = board[undoMove.getX1()][undoMove.getY1()];
            Square current = board[undoMove.getX2()][undoMove.getY2()];
            
            // move the piece back
            original.setPiece(current.getPiece());
            
            // unload the sprite in the current spot
            Piece p = current.getPiece();
            helpers.PieceSprite ps = p.getSprite();
            ps.unloadSprite(current.getButton());
            current.setPiece(null);
            
            // if there was a capture, recreate a piece there, as long as it wasnt en passant
            if (undoMove.getCaptureID() > 0 && !undoMove.isEnPassant()) {
                current.setPiece(undoMove.getCaptureID());
                this.getGraveyard(!undoMove.isWhite()).removePiece(undoMove.getCaptureID());
            }
            // if the capture was an en passant
            else if (undoMove.getCaptureID() > 0 && undoMove.isEnPassant()) {
                board[undoMove.getX1()][undoMove.getY2()].setPiece(undoMove.getCaptureID());
                this.getGraveyard(!undoMove.isWhite()).removePiece(undoMove.getCaptureID());
            }
            
            // if the piece that moved was queened, then undo the queening
            if (undoMove.isQueened()) {
                if (undoMove.isWhite()) {
                    original.setPiece(1);
                }
                else {
                    original.setPiece(7);
                }
            }
            
            // if there was a castle, move the rook back to its original location
            if (undoMove.isCastle()) {
                int kingX = (undoMove.isWhite()) ? 7: 0;
                int rookID = (undoMove.isWhite()) ? Piece.ROOK_ID: Piece.ROOK_ID + 6;
                // if the castle was to the left
                if (board[kingX][3].getPiece() != null && board[kingX][3].getPiece().getPieceID() == rookID) {
                    board[kingX][0].setPiece(board[kingX][3].getPiece());
                    board[kingX][3].getPiece().getSprite().unloadSprite(board[kingX][3].getButton());
                    board[kingX][3].setPiece(null);
                }
                // if the castle was to the right
                else if (board[kingX][5].getPiece() != null && board[kingX][5].getPiece().getPieceID() == rookID) {
                    board[kingX][7].setPiece(board[kingX][5].getPiece());
                    board[kingX][5].getPiece().getSprite().unloadSprite(board[kingX][5].getButton());
                    board[kingX][5].setPiece(null);
                }
            }
            
            gameboard.setCurrentMove(undoMove.isWhite());
            setCurrentMoveLabel(undoMove.isWhite());
            gameboard.newMoveEnabling();
            
            log.remove(n - 1);
            if (this.isAutosave()) {
                save();
            }
            return true;
        }
        else {
            return false;
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Save">
    public void save() {
        if (filename != null) {
            saveBoard();
            saveGraveyards();
            saveLog();
            saveUndos();
            this.setTitle("Chess - " + filename.substring(6));
        }
        else {
            Message.errorMessage("Please Select a File");
        }
    }
    
    private void saveUndos() {
        try {
            PrintWriter writer = new PrintWriter(filename + UNDOS_LEFT);
            writer.println(whiteUndos + "," + blackUndos);
            
            writer.flush();
            writer.close();
        } catch (FileNotFoundException ex) {
            Message.errorMessage("An error occurred when writing the file - file was not found");
        }
    }
    
    
    private void saveBoard() {
        Square[][] board = gameboard.getBoard();
        try {
            PrintWriter writer = new PrintWriter(filename + PLACES);
            for (int r = 0; r < board.length; r++) {
                for (int c = 0; c < board[0].length; c++) {
                    // Write the selected Piece ID
                    if (board[r][c].getPiece() == null) {
                        writer.print(0);
                    }
                    else {
                        writer.print(board[r][c].getPiece().getPieceID());
                    }
                    
                    // Add the comma betweent he values if not at the end of a line
                    if (c == board[0].length - 1) {
                        writer.println();
                    }
                    else {
                        writer.print(",");
                    }
                }
            }
            
            writer.flush();
            writer.close();
        } catch (FileNotFoundException ex) {
            Message.errorMessage("An error occurred when writing the file - file was not found");
        }
    }
    
    public void saveLog(int x1, int y1, int x2, int y2, int captureID, boolean isQueened, boolean isCastle, boolean isEnPassant, boolean isWhite) {
        try {
            File f = new File(filename + LOG);
            
            PrintWriter writer = new PrintWriter(f);
            
            String s = x1 + "," + y1 + "," + x2 + "," + y2 + "," + captureID + "," + isQueened + "," + isCastle + "," + isEnPassant + "," + isWhite;
            log.add(new Move(s));
            for (int i = 0, n = log.size(); i < n; i++) {
                writer.println(log.get(i).toString());
            }
            
            writer.flush();
            writer.close();
        } catch (FileNotFoundException ex) {
            Message.errorMessage("An error occurred when writing the file - file was not found");
        } catch (NumberFormatException ex) {
            Message.errorMessage("The file was not formatted correctly when outputting");
        }
    }
    
    /**
     * Saves log as is
     */
    public void saveLog() {
        try {
            File f = new File(filename + LOG);
            
            PrintWriter writer = new PrintWriter(f);
            
            for (int i = 0, n = log.size(); i < n; i++) {
                writer.println(log.get(i).toString());
            }
            
            writer.flush();
            writer.close();
        } catch (FileNotFoundException ex) {
            Message.errorMessage("An error occurred when writing the file - file was not found");
        }
    }
    
    private void saveGraveyards() {
        try {
            PrintWriter writer = new PrintWriter(filename + GRAVEYARD);
            // write the white graveyard
            for (int i = 0, n = whiteGraveyard.getGraveyardSize(); i < n; i++) {
                // Write the selected Piece ID
                if (whiteGraveyard.getPiece(i) == null) {
                    writer.print(0);
                }
                else {
                    writer.print(whiteGraveyard.getPiece(i).getPieceID());
                }

                // Add the comma betweent he values if not at the end of a line
                if (i == n - 1) {
                    writer.println();
                }
                else {
                    writer.print(",");
                }
            }
            
            // write the blackgraveyard
            for (int i = 0, n = blackGraveyard.getGraveyardSize(); i < n; i++) {
                // Write the selected Piece ID
                if (blackGraveyard.getPiece(i) == null) {
                    writer.print(0);
                }
                else {
                    writer.print(blackGraveyard.getPiece(i).getPieceID());
                }

                // Add the comma betweent he values if not at the end of a line
                if (i == n - 1) {
                    writer.println();
                }
                else {
                    writer.print(",");
                }
            }
            
            writer.flush();
            writer.close();
        } catch (FileNotFoundException ex) {
            Message.errorMessage("An error occurred when writing the file - file was not found");
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Load">
    public void load() {
        if (filename != null) {
            gameboard.newMoveEnabling();
            loadBoard();
            loadCurrentMove();
            loadGraveyards();
            loadMoveLog();
            loadUndos();
            gameboard.newMoveEnabling();
            this.setCurrentMoveLabel(gameboard.getCurrentMove());
            this.setTitle("Chess - " + filename.substring(6));
        }
        else {
            Message.errorMessage("Please Select a File");
        }
    }
    
    private void loadUndos() {
        try {
            File file = new File(filename + UNDOS_LEFT);
            if (file.exists() && !file.isDirectory()) {
                // load in the undo numbers
                Scanner in = new Scanner(file);
                if (in.hasNextLine()) {
                    try {
                        String[] s = in.nextLine().split(",");
                        whiteUndos = Integer.parseInt(s[0]);
                        blackUndos = Integer.parseInt(s[1]);
                    } catch (NumberFormatException ex) {
                        Message.errorMessage("Undo file formatted incorrectly");
                    }
                }
                else {
                    whiteUndos = DEFAULT_UNDOS;
                    blackUndos = DEFAULT_UNDOS;
                }
                in.close();
            }
            else {
                throw new FileNotFoundException();
            }
        } catch (FileNotFoundException ex) {
            Message.errorMessage("File not found");
        }
    }
    
    public void loadBoard() {
        String csvSplitBy = ",";
        Square[][] board = gameboard.getBoard();
        int[][] ids = new int[board.length][board[0].length];
        
        try {
            File file = new File(filename + PLACES);
            if (file.exists() && !file.isDirectory()) {
                // load in the piece ids
                Scanner in = new Scanner(file);
                for (int i = 0; i < ids.length; i++) {
                    if (in.hasNextLine()) {
                        String line = in.nextLine();
                        String[] lineText = line.split(csvSplitBy);
                        try {
                            for (int j = 0; j < lineText.length; j++) {
                                ids[i][j] = Integer.parseInt(lineText[j]);
                            }
                        } catch (ArrayIndexOutOfBoundsException ex) {
                            Message.errorMessage(filename + PLACES + " was not formatted correctly.");
                        } catch (NumberFormatException ex) {
                            Message.errorMessage(filename + PLACES + " was formatted incorrectly.");
                        }
                        
                    }
                    else {
                        throw new FileNotFoundException();
                    }
                }
                in.close();
                
                // Set pieces
                
                for (int r = 0; r < board.length; r++) {
                    for (int c = 0; c < board[0].length; c++) {
                        board[r][c].setPiece(ids[r][c]);
                    }
                }
            }
            else {
                throw new FileNotFoundException();
            }
        } catch (FileNotFoundException ex) {
            Message.errorMessage("File not found");
        }
    }
    
    public void loadCurrentMove() {
        String csvSplitBy = ",";
        
        try {
            File file = new File(filename + LOG);
            if (file.exists() && !file.isDirectory()) {
                // get the last line in the file
                Scanner in = new Scanner(file);
                Move line = null;
                while (in.hasNextLine()) {
                    String nextLine = in.nextLine();
                    if (!(nextLine.equals("") || nextLine.equals("\n"))) {
                        line = new Move(nextLine);
                    }
                }
                in.close();
                if (line == null) {
                    gameboard.setCurrentMove(true);
                }
                else {
                    gameboard.setCurrentMove(!line.isWhite());
                }
            }
            else {
                PrintWriter out = new PrintWriter(file);
                out.close();
            }
        } catch (FileNotFoundException ex) {
            Message.errorMessage("File not found");
        } catch (NumberFormatException ex) {
            Message.errorMessage("File formatted incorrectly");
        }
    }
    
    public void loadGraveyards() {
        String csvSplitBy = ",";
        
        try {
            File file = new File(filename + GRAVEYARD);
            if (file.exists() && !file.isDirectory()) {
                // Read in the lines of the file
                Scanner in = new Scanner(file);
                String[] lines = new String[2];
                int i = 0;
                while (in.hasNextLine() && i < 2) {
                    String nextLine = in.nextLine();
                    lines[i] = nextLine;
                    i++;
                }
                
                
                in.close();
                
                if (lines[0] != null) {
                    String[] ids = lines[0].split(csvSplitBy);
                    whiteGraveyard.reset();
                    // Set the pieces of the white graveyard (first line of file) 
                    for (int j = 0; j < ids.length; j++) {
                        Square s = new Square();
                        s.setPiece(Integer.parseInt(ids[j]));
                        whiteGraveyard.add(s.getPiece());
                    }
                }
                if (lines[1] != null) {
                    
                    String[] ids = lines[1].split(csvSplitBy);
                    blackGraveyard.reset();
                    // Set the pieces of the black graveyard (second line of file)
                    for (int j = 0; j < ids.length; j++) {
                        Square s = new Square();
                        s.setPiece(Integer.parseInt(ids[j]));
                        blackGraveyard.add(s.getPiece());
                    }
                }
            }
            else {
                PrintWriter out = new PrintWriter(file);
                out.close();
            }
        } catch (FileNotFoundException ex) {
            Message.errorMessage("File not found");
        } catch (NumberFormatException ex) {
            Message.errorMessage("File formatted incorrectly");
        }
    }
    
    private void loadMoveLog() {
        try {
            File file = new File(filename + LOG);
            if (file.exists() && !file.isDirectory()) {
                log = new ArrayList<>();
                // get the last line in the file
                Scanner in = new Scanner(file);
                while (in.hasNextLine()) {
                    String nextLine = in.nextLine();
                    if (!(nextLine.equals("") || nextLine.equals("\n"))) {
                        log.add(new Move(nextLine));
                    }
                }
                in.close();
            }
            else {
                PrintWriter out = new PrintWriter(file);
                out.close();
            }
        } catch (FileNotFoundException ex) {
            Message.errorMessage("File not found");
        } catch (NumberFormatException ex) {
            Message.errorMessage("File formatted incorrectly");
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Reset">
    public void reset() {
        resetDefaultLog();
        gameboard.setDefault();
        whiteGraveyard.reset();
        blackGraveyard.reset();
        gameboard.newMoveEnabling();
        whiteUndos = 3;
        blackUndos = 3;
        this.setUndoLabel();
        save();
    }
    
    /**
     * Resets the default log
     */
    public void resetLog() {
        File f = new File(filename + LOG);
        if (f.exists() && !f.isDirectory()) {
            try {
                PrintWriter out = new PrintWriter(f);
                out.flush();
                out.close();
                log = new ArrayList<>();
            } catch (FileNotFoundException ex) {
                Message.errorMessage("Unidentified bug, please report");
            }
        }
    }
    
    public void resetDefaultLog() {
        File f = new File(DEFAULT_FILENAME + LOG);
        if (f.exists() && !f.isDirectory()) {
            try {
                PrintWriter out = new PrintWriter(f);
                out.flush();
                out.close();
                log = new ArrayList<>();
            } catch (FileNotFoundException ex) {
                Message.errorMessage("Unidentified bug, please report");
            }
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Netbeans Defined Variables">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel MoveLabel;
    private javax.swing.JMenu aboutMenu;
    private javax.swing.JRadioButtonMenuItem advanced;
    private javax.swing.JCheckBoxMenuItem autosaveCheckbox;
    private javax.swing.JRadioButtonMenuItem beginner;
    private gameboard.Graveyard blackGraveyard;
    private javax.swing.JLabel blackUndosRemaining;
    private javax.swing.JLabel check;
    private javax.swing.JMenuItem contactInfo;
    private javax.swing.JTextField currentMove;
    private javax.swing.JMenu fileMenu;
    private gameboard.ChessBoard gameboard;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuItem helpMenuItem;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JCheckBoxMenuItem limitUndos;
    private javax.swing.JMenuItem loadMenu;
    private javax.swing.JMenuItem reloadMenu;
    private javax.swing.JButton reset;
    private javax.swing.JMenuItem saveAsMenu;
    private javax.swing.JMenuItem saveMenu;
    private javax.swing.JSeparator separator;
    private javax.swing.JMenuItem setBlackUndos;
    private javax.swing.JMenuItem setWhiteUndos;
    private javax.swing.JButton undo;
    private javax.swing.JMenu undoMenu;
    private javax.swing.JMenuItem undoMenuItem;
    private gameboard.Graveyard whiteGraveyard;
    private javax.swing.JLabel whiteUndosRemaining;
    // End of variables declaration//GEN-END:variables
    // </editor-fold>
}
