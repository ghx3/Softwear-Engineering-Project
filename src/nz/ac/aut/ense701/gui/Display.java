package nz.ac.aut.ense701.gui;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import nz.ac.aut.ense701.gameModel.Utils.GameEventListener;
import nz.ac.aut.ense701.gameModel.Utils.GameState;
import nz.ac.aut.ense701.gameModel.Utils.MoveDirection;
import nz.ac.aut.ense701.main.GameController;
import nz.ac.aut.ense701.main.Handler;

public class Display implements GameEventListener, KeyListener {

    private Handler handler;
    private JFrame frame;//frfame instance
    private Canvas canvas;//game is draw here

    private String title;
    private int width, height;//size of the frame, but applys to canvas + menu

    // Variables declaration - Menu                    
    private JButton btnCollect;
    private JButton btnCount;
    private JButton btnDrop;
    private JButton btnUse;
    private JLabel lblKiwisCounted;
    private JLabel lblPredators;
    private JList listInventory;
    private JList listObjects;
    private JProgressBar progBackpackSize;
    private JProgressBar progBackpackWeight;
    private JProgressBar progPlayerStamina;
    private JLabel txtKiwisCounted;
    private JLabel txtPlayerName;
    private JLabel txtPredatorsLeft;
    // End of variables declaration 

    public Display(String title, int width, int height, Handler handler) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.handler = handler;
        createDisplay();
    }

    private void createDisplay() {

        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        setAsGameListener();
        initComponents();
        //initIslandGrid();
        update();

        frame.pack();
        frame.setVisible(true);
        frame.addKeyListener(this);
        frame.setFocusable(true);

    }

    private void initComponents() {

        //Canvas is the same size as the width and heigth given
        //Screen will be bigger due to 
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setFocusable(false);
        

        JPanel pnlContent = new JPanel();

        JPanel pnlControls = InitMenuControl();
        
        pnlContent.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pnlContent.setLayout(new BorderLayout(10, 0));
        pnlContent.add(canvas, BorderLayout.WEST);
        pnlContent.add(pnlControls, BorderLayout.EAST);
        frame.getContentPane().add(pnlContent, BorderLayout.CENTER);

    }
    
    public JPanel InitMenuControl() {

        GridBagConstraints gridBagConstraints;
        JPanel pnlControls = new JPanel();
        pnlControls.setPreferredSize(new Dimension(280, height));
        pnlControls.setMaximumSize(new Dimension(280, height));
        pnlControls.setMinimumSize(new Dimension(280, height));

        JPanel pnlPlayer = new JPanel();
        JPanel pnlPlayerData = new JPanel();
        JLabel lblPlayerName = new JLabel();
        txtPlayerName = new JLabel();
        JLabel lblPlayerStamina = new JLabel();
        progPlayerStamina = new JProgressBar();

        JLabel lblBackpackWeight = new JLabel();
        progBackpackWeight = new JProgressBar();
        JLabel lblBackpackSize = new JLabel();
        progBackpackSize = new JProgressBar();

        lblPredators = new JLabel();
        lblKiwisCounted = new JLabel();
        txtKiwisCounted = new JLabel();
        txtPredatorsLeft = new JLabel();

        JPanel pnlInventory = new JPanel();
        JScrollPane scrlInventory = new JScrollPane();
        listInventory = new JList();
        btnDrop = new JButton();
        btnUse = new JButton();

        JPanel pnlObjects = new JPanel();
        JScrollPane scrlObjects = new JScrollPane();

        listObjects = new JList();
        btnCollect = new JButton();
        btnCount = new JButton();

        pnlControls.setLayout(new GridBagLayout());

        pnlPlayer.setBorder(BorderFactory.createTitledBorder("Player"));
        pnlPlayer.setLayout(new BorderLayout());

        pnlPlayerData.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        pnlPlayerData.setLayout(new GridBagLayout());

        lblPlayerName.setText("Name:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weighty = 1.0;
        pnlPlayerData.add(lblPlayerName, gridBagConstraints);

        txtPlayerName.setText("Player Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        pnlPlayerData.add(txtPlayerName, gridBagConstraints);

        lblPlayerStamina.setText("Stamina:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        pnlPlayerData.add(lblPlayerStamina, gridBagConstraints);

        progPlayerStamina.setStringPainted(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        pnlPlayerData.add(progPlayerStamina, gridBagConstraints);

        lblBackpackWeight.setText("Backpack Weight:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        pnlPlayerData.add(lblBackpackWeight, gridBagConstraints);

        progBackpackWeight.setStringPainted(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        pnlPlayerData.add(progBackpackWeight, gridBagConstraints);

        lblBackpackSize.setText("Backpack Size:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        pnlPlayerData.add(lblBackpackSize, gridBagConstraints);

        progBackpackSize.setStringPainted(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        pnlPlayerData.add(progBackpackSize, gridBagConstraints);

        lblPredators.setText("Predators Left:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pnlPlayerData.add(lblPredators, gridBagConstraints);

        lblKiwisCounted.setText("Kiwis Counted :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pnlPlayerData.add(lblKiwisCounted, gridBagConstraints);

        txtKiwisCounted.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pnlPlayerData.add(txtKiwisCounted, gridBagConstraints);

        txtPredatorsLeft.setText("P");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pnlPlayerData.add(txtPredatorsLeft, gridBagConstraints);

        pnlPlayer.add(pnlPlayerData, BorderLayout.WEST);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        pnlControls.add(pnlPlayer, gridBagConstraints);

        pnlInventory.setBorder(javax.swing.BorderFactory.createTitledBorder("Inventory"));
        pnlInventory.setLayout(new java.awt.GridBagLayout());
        listInventory.setModel(new javax.swing.AbstractListModel() {
            String[] strings = {"Item 1", "Item 2", "Item 3"};

            public int getSize() {
                return strings.length;
            }

            public Object getElementAt(int i) {
                return strings[i];
            }
        });
        listInventory.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listInventory.setVisibleRowCount(3);
        listInventory.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                
                listInventoryValueChanged(evt);
            }
        });
        scrlInventory.setViewportView(listInventory);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        pnlInventory.add(scrlInventory, gridBagConstraints);

        btnDrop.setText("Drop");
        btnDrop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDropActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlInventory.add(btnDrop, gridBagConstraints);

        btnUse.setText("Use");
        btnUse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUseActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlInventory.add(btnUse, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlControls.add(pnlInventory, gridBagConstraints);

        pnlObjects.setBorder(javax.swing.BorderFactory.createTitledBorder("Objects"));
        GridBagLayout pnlObjectsLayout = new java.awt.GridBagLayout();
        pnlObjectsLayout.columnWidths = new int[]{0, 5, 0};
        pnlObjectsLayout.rowHeights = new int[]{0, 5, 0};
        pnlObjects.setLayout(pnlObjectsLayout);

        listObjects.setModel(new javax.swing.AbstractListModel() {
            String[] strings = {"Item 1", "Item 2", "Item 3"};

            public int getSize() {
                return strings.length;
            }

            public Object getElementAt(int i) {
                return strings[i];
            }
        });
        listObjects.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listObjects.setVisibleRowCount(3);
        listObjects.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                
                listObjectsValueChanged(evt);
            }
        });

        scrlObjects.setViewportView(listObjects);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlObjects.add(scrlObjects, gridBagConstraints);

        btnCollect.setText("Collect");
        btnCollect.setToolTipText("");
        btnCollect.setMaximumSize(new java.awt.Dimension(61, 23));
        btnCollect.setMinimumSize(new java.awt.Dimension(61, 23));
        btnCollect.setPreferredSize(new java.awt.Dimension(61, 23));
        btnCollect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCollectActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlObjects.add(btnCollect, gridBagConstraints);

        btnCount.setText("Count");
        btnCount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCountActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlObjects.add(btnCount, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlControls.add(pnlObjects, gridBagConstraints);

        return pnlControls;
    }

    /**
     * Updates the state of the UI based on the state of the game.
     */
    private void update() {
        canvas.setFocusable(true);
        // update player information
        int[] playerValues = handler.getGameController().getPlayerValues();
        txtPlayerName.setText(handler.getGameController().getPlayerName());
        progPlayerStamina.setMaximum(playerValues[GameController.MAXSTAMINA_INDEX]);
        progPlayerStamina.setValue(playerValues[GameController.STAMINA_INDEX]);
        progBackpackWeight.setMaximum(playerValues[GameController.MAXWEIGHT_INDEX]);
        progBackpackWeight.setValue(playerValues[GameController.WEIGHT_INDEX]);
        progBackpackSize.setMaximum(playerValues[GameController.MAXSIZE_INDEX]);
        progBackpackSize.setValue(playerValues[GameController.SIZE_INDEX]);

        //Update Kiwi and Predator information
        txtKiwisCounted.setText(Integer.toString(handler.getGameController().getKiwiCount()));
        txtPredatorsLeft.setText(Integer.toString(handler.getGameController().getPredatorsRemaining()));

        // update inventory list
        listInventory.setListData(handler.getGameController().getPlayerInventory());
        listInventory.clearSelection();
        listInventory.setToolTipText(null);
        btnUse.setEnabled(false);
        btnDrop.setEnabled(false);

        // update list of visible objects
        listObjects.setListData(handler.getGameController().getOccupantsPlayerPosition());
        listObjects.clearSelection();
        listObjects.setToolTipText(null);
        btnCollect.setEnabled(false);
        btnCount.setEnabled(false);
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public JFrame getFrame() {
        return frame;
    }

    /**
     * This method is called by the game model every time something changes.
     * Trigger an update.
     */
    public void gameStateChanged() {
        update();

        // check for "game over" or "game won"
        if (handler.getGameController().getState() == GameState.LOST) {
            JOptionPane.showMessageDialog(
                    frame,
                    handler.getGameController().getLoseMessage(), "Game over!",
                    JOptionPane.INFORMATION_MESSAGE);
            handler.getGameController().createNewGame();
        } else if (handler.getGameController().getState() == GameState.WON) {
            JOptionPane.showMessageDialog(
                    frame,
                    handler.getGameController().getWinMessage(), "Well Done!",
                    JOptionPane.INFORMATION_MESSAGE);
            handler.getGameController().createNewGame();
        } else if (handler.getGameController().messageForPlayer()) {
            JOptionPane.showMessageDialog(
                    frame,
                    handler.getGameController().getPlayerMessage(), "Important Information",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void setAsGameListener() {
        handler.getGameController().addGameEventListener(this);
    }

    private void btnCollectActionPerformed(ActionEvent evt) {
        Object obj = listObjects.getSelectedValue();
        handler.getGameController().collectItem(obj);
    }

    private void btnDropActionPerformed(ActionEvent evt) {
        handler.getGameController().dropItem(listInventory.getSelectedValue());
    }

    private void listObjectsValueChanged(ListSelectionEvent evt) {
        listObjects.setFocusable(false);
        Object occ = listObjects.getSelectedValue();
        if (occ != null) {
            btnCollect.setEnabled(handler.getGameController().canCollect(occ));
            btnCount.setEnabled(handler.getGameController().canCount(occ));
            listObjects.setToolTipText(handler.getGameController().getOccupantDescription(occ));
        }
    }

    private void btnUseActionPerformed(ActionEvent evt) {
        handler.getGameController().useItem(listInventory.getSelectedValue());
    }

    private void listInventoryValueChanged(ListSelectionEvent evt) {
        listInventory.setFocusable(false);
        Object item = listInventory.getSelectedValue();
        btnDrop.setEnabled(true);
        if (item != null) {
            btnUse.setEnabled(handler.getGameController().canUse(item));
            listInventory.setToolTipText(handler.getGameController().getOccupantDescription(item));
        }
    }

    private void btnCountActionPerformed(ActionEvent evt) {
        handler.getGameController().countKiwi();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        handler.getGameController().getPlayer().move(e);
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
}
