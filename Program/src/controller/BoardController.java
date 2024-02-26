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
    private GamePanels mainFrame;
    private PlayerList playerList;

    public BoardController(GamePanels mainFrame) {
        this.dice1 = new Dice();
        this.dice2 = new Dice();
        this.board = new Board();
        this.playerList = new PlayerList();
        this.mainFrame = mainFrame;
    }


    public int rollDices(){
        int roll1 = dice1.roll();
        int roll2 = dice2.roll();
        setDiceImages();
        return roll1 + roll2;
    }

    public int getDiceSum(){
        return dice1.getValue() + dice2.getValue();
    }

    public void setDiceImages(){
        mainFrame.setDiceImages(dice1.getCurrentValueImage(), dice2.getCurrentValueImage());
    }

    public void removePlayer(Player player){
        mainFrame.removePlayer(player.getPosition());
    }

    public void eliminatePlayer(Player player){
        playerList.switchToNextPlayer();
        playerList.eliminatePlayer(player);
        playerList.updatePlayerList();
        addPlayerTabs();
    }

    public void endTurn(){

    }

    public void setPlayerToTile(Player player){
        mainFrame.setPlayerToTile(player.getPosition(), player);
    }
    public Tile getTileAtIndex(int index){
        return board.getTileInfoAtIndex(index);
    }

    public void setTitleText(String info, String lblTitle, Color titleColor, Color titleTxtColor) {
        mainFrame.setTitleText(info, lblTitle, titleColor, titleTxtColor);
    }

    public void setTextDefault() {
        mainFrame.setTextDefault();
    }

    public void addPlayerTabs(){
        mainFrame.addPlayerTabs(playerList);
    }

}
