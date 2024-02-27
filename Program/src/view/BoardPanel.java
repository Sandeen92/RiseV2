package view;

import controller.BoardController;
import entity.player.Player;
import entity.player.PlayerList;
import entity.tiles.TileInfo;
import utilities.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;

public class BoardPanel extends JPanel {

    private BoardController boardController;
    private TileInfo info;
    private GUITile[] guiTiles;
    private JPanel[] panelarray = new JPanel[40];
    private JLabel lblNewLabel = new JLabel();
    private JLabel lblPlayer;
    private Color players;
    private String playerName = "Player";

    public BoardPanel(BoardController boardController) {
        info = new TileInfo();
        guiTiles = new GUITile[40];
        this.boardController = boardController;
        initAllPanels();
        initGUITiles();
        initTurnLabel();
    }

    public void initAllPanels(){
        setPreferredSize(new Dimension(750, 750));
        setLayout(null);
        Listener listener = new Listener();


        int initialX = 649;
        int initialY = 650;
        int width = 101;
        int height = 100;
        int stepX = -62;
        int stepY = -62;

        for (int i = 0; i < panelarray.length; i++) {
            panelarray[i] = new JPanel();
            panelarray[i].setOpaque(false);
            panelarray[i].setBounds(initialX + (i % 11) * stepX, initialY + (i / 11) * stepY, width, height);
            add(panelarray[i]);
            panelarray[i].addMouseListener(listener);
            panelarray[i].setLayout(new BorderLayout());
        }

        lblNewLabel.setBounds(0, -136, 1050, 1022);
        lblNewLabel.setIcon(Constants.BoardImages.BOARD_IMAGE);
        add(lblNewLabel);
    }

    public void initGUITiles(){
        addEmptyGridPanels();
        for(int i = 0; i < panelarray.length; i ++) {
            panelarray[i].add(guiTiles[i]);
        }
    }

    private void initTurnLabel() {
        setPreferredSize(new Dimension(250,50));
        setBackground(players);

        lblPlayer = new JLabel(playerName);
        lblPlayer.setHorizontalAlignment(SwingConstants.CENTER);
        lblPlayer.setForeground(Color.white);
        lblPlayer.setVisible(true);
        lblPlayer.setPreferredSize(new Dimension(240,45));
        lblPlayer.setFont(new Font("ALGERIAN", Font.BOLD, 14 ));
        lblPlayer.setBorder(BorderFactory.createLineBorder(Color.white));
        add(lblPlayer);
    }

    public void updateTurnLabel(String playerName, Color color) {
        lblPlayer.setOpaque(true);
        lblPlayer.setBackground(color);
        lblPlayer.setText(playerName+"'s turn");
    }

    public void removePlayer(int position) {
        guiTiles[position].removePlayer(position);
    }

    public void setPlayerToTile(int position, Player player) {
        guiTiles[position].setPlayer(player);
    }

    public void movePlayerOnBoard(Player player) {
        guiTiles[player.getPosition()].setPlayer(player);
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
























    public class Listener implements MouseListener, Serializable {

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
