package controller;

import entity.*;
import entity.player.*;
import entity.tiles.Tile;
import view.*;

import java.awt.*;

public class BoardController {

    private Dice dice1;
    private Dice dice2;
    private Board board;
    private MainPanel mainPanel;
    private PlayerList playerList;

    public BoardController(MainPanel mainPanel) {
        this.dice1 = new Dice();
        this.dice2 = new Dice();
        this.board = new Board();
        this.playerList = new PlayerList();
        this.mainPanel = mainPanel;
    }


    public int rollDices(){
        int roll1 = dice1.roll();
        int roll2 = dice2.roll();
        setDiceImages();

        if (roll1 == roll2) {
            return (roll1 + roll2) * 2;
        }
        return roll1 + roll2;
    }

    public int getDiceSum(){
        return dice1.getValue() + dice2.getValue();
    }

    public void setDiceImages(){
        mainPanel.setDiceImages(dice1.getCurrentValueImage(), dice2.getCurrentValueImage());
    }

    public void removePlayer(Player player){
        mainPanel.removePlayer(player.getPosition());
    }

    public void eliminatePlayer(Player player){
        playerList.switchToNextPlayer();
        playerList.eliminatePlayer(player);
        playerList.updatePlayerList();
        addPlayerTabs();
    }

    public void movePlayer(Player player){
        mainPanel.setPlayerToTile(player.getPosition(), player);
        mainPanel.updateTurnLabel(player.getName(), player.getPlayerColor());
    }

    public void endTurn(){

    }

    public void setPlayerToTile(Player player){
        mainPanel.setPlayerToTile(player.getPosition(), player);
    }
    public Tile getTileAtIndex(int index){
        return board.getTileInfoAtIndex(index);
    }

    public void setTitleText(String info, String lblTitle, Color titleColor, Color titleTxtColor) {
        mainPanel.setTitleText(info, lblTitle, titleColor, titleTxtColor);
    }

    public void setTextDefault() {
        mainPanel.setTextDefault();
    }

    public void addPlayerTabs(){
        mainPanel.addPlayerTabs(playerList);
    }

}
