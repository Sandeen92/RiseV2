package view;

import controller.BoardController;
import entity.player.Player;
import entity.player.PlayerList;
import entity.tiles.TileInfo;
import utilities.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.Serializable;

public class BoardPanel extends JPanel {

    private BoardController boardController;
    private TileInfo info;
    private GUITile[] guiTiles;
    private JPanel[] panelarray = new JPanel[40];
    private JLabel lblNewLabel = new JLabel();

    public BoardPanel(BoardController boardController) {
        info = new TileInfo();
        guiTiles = new GUITile[40];
        this.boardController = boardController;
        initAllPanels();
        initGUITiles();
    }

    public void initAllPanels(){
        setPreferredSize(new Dimension(750, 750));
        setLayout(null);
        MouseListener mouseListener = new MouseListener();

        initBottomRow();
        initLeftRow();
        initTopRow();
        initRightRow();

        for (int i = 0; i < panelarray.length; i++) {
            add(panelarray[i]);
            panelarray[i].addMouseListener(mouseListener);
            panelarray[i].setLayout(new BorderLayout());
        }

        lblNewLabel.setBounds(0, -136, 1050, 1022);
        lblNewLabel.setIcon(Constants.BoardImages.BOARD_IMAGE);
        add(lblNewLabel);
    }

    public void initBottomRow(){
        int[] boundsX = {649, 587, 525, 467, 412, 346, 283, 225, 163, 103};
        int[] boundsY = {650, 651, 650, 650, 650, 650, 650, 651, 651, 650};
        int[] boundsWidth = {101, 60, 60, 60, 53, 66, 66, 60, 66, 60};
        int[] boundsHeight = {100, 99, 100, 100, 100, 100, 100, 99, 99, 100};

        for (int i = 0; i < 10; i++) {
            panelarray[i] = new JPanel();
            panelarray[i].setOpaque(false);
            panelarray[i].setBounds(boundsX[i], boundsY[i], boundsWidth[i], boundsHeight[i]);
            add(panelarray[i]);
        }
    }

    public void initLeftRow(){
        int[] boundsX = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] boundsY = {650, 587, 527, 465, 407, 345, 281, 218, 162, 103};
        int[] boundsWidth = {101, 101, 101, 101, 101, 101, 101, 101, 101, 101};
        int[] boundsHeight = {100, 62, 62, 62, 62, 62, 62, 62, 62, 62};

        for (int i = 10; i < 20; i++) {
            panelarray[i] = new JPanel();
            panelarray[i].setOpaque(false);
            panelarray[i].setBounds(boundsX[i+-10], boundsY[i-10], boundsWidth[i-10], boundsHeight[i-10]);
            add(panelarray[i]);
        }
    }

    public void initTopRow(){
        int[] boundsX = {0, 103, 163, 225, 283, 346, 407, 467, 525, 587};
        int[] boundsY = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] boundsWidth = {101, 60, 60, 60, 66, 60, 60, 60, 60, 60};
        int[] boundsHeight = {100, 100, 100, 100, 100, 100, 100, 100, 100, 100};

        for (int i = 20; i < 30; i++) {
            panelarray[i] = new JPanel();
            panelarray[i].setOpaque(false);
            panelarray[i].setBounds(boundsX[i-20], boundsY[i-20], boundsWidth[i-20], boundsHeight[i-20]);
            add(panelarray[i]);
        }
    }

    public void initRightRow(){
        int[] boundsX = {649, 649, 649, 649, 649, 649, 649, 649, 649, 649};
        int[] boundsY = {0, 103, 162, 228, 281, 345, 407, 465, 527, 587};
        int[] boundsWidth = {101, 101, 101, 101, 101, 101, 101, 101, 101, 101};
        int[] boundsHeight = {100, 62, 62, 62, 62, 62, 62, 62, 62, 62};

        for (int i = 30; i < 40; i++) {
            panelarray[i] = new JPanel();
            panelarray[i].setOpaque(false);
            panelarray[i].setBounds(boundsX[i-30], boundsY[i-30], boundsWidth[i-30], boundsHeight[i-30]);
            add(panelarray[i]);
        }
    }

    public void initGUITiles(){
        addEmptyGridPanels();
        for(int i = 0; i < panelarray.length; i ++) {
            panelarray[i].add(guiTiles[i]);
        }
    }

    public void removePlayer(Player player) {
        guiTiles[player.getPosition()].removePlayer(player);
    }

    public void setPlayerToTile(Player player) {
        guiTiles[player.getPosition()].setPlayer(player);
    }

    public void movePlayerOnBoard(Player player) {
        guiTiles[player.getPosition()].setPlayer(player);
        if ((player.getPosition() - 1) != -1) {
            guiTiles[player.getPosition()-1].removePlayer(player);
        }
        else {
            guiTiles[guiTiles.length-1].removePlayer(player);
        }
    }

    public void setPlayerIndexes(PlayerList playerList) {
        for (int i = 0; i < playerList.getLength(); i++) {
            Player p = playerList.getPlayerFromIndex(i);
            guiTiles[p.getPosition()].setPlayer(p);
        }
    }

    public void addEmptyGridPanels() {
        for (int i = 0; i < guiTiles.length; i++) {

            /**
             * If index is between 0-10 then labels are placed in north.
             */
            if(i < 11) {
                guiTiles[i] = new GUITile(1);
            }

            /**
             * If index is between 11-20 then labels are placed in east.
             */
            else if(i < 20) {
                guiTiles[i] = new GUITile(2);
            }

            /**
             * If index is between 20 and 30 then labels are placed in south.
             */
            else if(i < 31) {
                guiTiles[i] = new GUITile(3);
            }

            /**
             * If index is between 31-39 then labels are placed in west.
             */
            else if(i < 40) {
                guiTiles[i] = new GUITile(4);
            }
        }
    }





    public class MouseListener implements java.awt.event.MouseListener, Serializable {

        /**
         * Nothing happens when clicking
         */
        public void mouseClicked(MouseEvent e) {

        }
        /**
         * Method for when hovering over entity.tiles.
         */
        public void mouseEntered(MouseEvent e) {
            for (int i=0; i<panelarray.length; i++) {
                if (e.getSource()==panelarray[i]) {
                    if (i == 0 || i == 2 || i == 4 || i == 5 ||i == 7 ||
                            i == 10 || i == 15 ||i == 17 ||
                            i == 20 ||i == 22 || i == 25 || i == 30 ||
                            i == 33 ||i == 35 || i == 36 || i == 38){
                        boardController.setTitleText(info.getInfo(i), info.getTitle(i), Color.DARK_GRAY, Color.white);
                    }else if(i==26 || i==27 || i==29) {
                        boardController.setTitleText(boardController.getTileAtIndex(i).getTileInfo(),
                                boardController.getTileAtIndex(i).getTitle(), new Color(254,231,11, 255), Color.black);
                    }
                    else if(i == 12 || i == 28) {
                        boardController.setTitleText(boardController.getTileAtIndex(i).getTileInfo(),
                                boardController.getTileAtIndex(i).getName(), Color.DARK_GRAY, Color.white);
                    }
                    else {
                        boardController.setTitleText(boardController.getTileAtIndex(i).getTileInfo(),
                                boardController.getTileAtIndex(i).getTitle(), boardController.getTileAtIndex(i).getColor(), Color.white );
                    }
                }
            }
        }

        /**
         * Calls method to set default text when mouse leaves a tile.
         */
        public void mouseExited(MouseEvent e) {
            boardController.setTextDefault();
        }

        /**
         * Nothing happens when mouse is pressed.
         */
        public void mousePressed(MouseEvent e) {

        }

        /**
         * Nothing happens by releasing mouse.
         */
        public void mouseReleased(MouseEvent e) {

        }
    }
}
