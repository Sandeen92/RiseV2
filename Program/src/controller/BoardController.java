package controller;

import entity.*;
import entity.player.*;
import entity.tiles.Property;
import entity.tiles.Tavern;
import entity.tiles.Tile;
import view.*;

import java.awt.*;

import static java.lang.Thread.sleep;

public class BoardController {

    private Dice dice1;
    private Dice dice2;
    private Board board;
    private MainPanel mainPanel;
    private PlayerList playerList;
    private EventManager eventManager;
    private int roll1;
    private int roll2;

    public BoardController() {
        this.dice1 = new Dice();
        this.dice2 = new Dice();
        this.board = new Board();
        this.playerList = new PlayerList();
        this.mainPanel = new MainPanel(this);
        this.eventManager = new EventManager(this);
    }

    public BoardController(PlayerList playerList) {
        this.dice1 = new Dice();
        this.dice2 = new Dice();
        this.board = new Board();
        this.playerList = playerList;
        this.mainPanel = new MainPanel(this);
        this.eventManager = new EventManager(this);
    }

    public void setPlayerList(PlayerList playerList) {
        this.playerList = playerList;
    }

    public void startBoard() {
        mainPanel.startBoard();
    }

    public int rollDices(){
        roll1 = dice1.roll();
        roll2 = dice2.roll();
        setDiceImages();

        return checkIfDouble(roll1, roll2);
    }

    public int checkIfDouble(int roll1Value, int roll2Value){
        if (roll1Value == roll2Value) {
            return ((roll1Value + roll2Value) * 2);
        }
        return roll1Value + roll2Value;
    }

    public int getDiceSum(){
        return dice1.getValue() + dice2.getValue();
    }

    public void setDiceImages(){
        mainPanel.setDiceImages(dice1.getCurrentValueImage(), dice2.getCurrentValueImage());
    }

    public String addPlayerToList(String name, String icon){
        playerList.addNewPlayer(name, icon);
        return name;
    }

    public PlayerList getPlayerList(){
        return playerList;
    }

    public void removePlayer(Player player){
        mainPanel.removePlayer(player);
    }

    public void eliminatePlayer(Player player){
        playerList.switchToNextPlayer();
        playerList.eliminatePlayer(player);
        playerList.updatePlayerList();
        addPlayerTabs();
    }

    public void movePlayer(int diceRoll){
        Movement movement = new Movement(diceRoll);
        movement.start();
    }

    public void handleEventLandedOn(){
        Player activePlayer = playerList.getActivePlayer();
        Tile tile = board.getTiletIndex(activePlayer.getPosition());
        eventManager.newEvent(tile, activePlayer);
    }

    public void purchaseTavern(String text, Tavern tavern, Player player) {
        eventManager.purchaseTavern(text, tavern, player);
    }

    public void purchaseProperty(String text, Property property, Player player){
        eventManager.purchaseProperty(text, property, player);
    }

    public void endTurn(){
        playerList.switchToNextPlayer();
        Player activePlayer = playerList.getActivePlayer();
        mainPanel.updateTurnLabel(activePlayer.getName(), activePlayer.getPlayerColor());
        mainPanel.removeEventFromPanel();
    }

    public void setPlayerToTile(Player player){
        mainPanel.setPlayerToTile(player);
    }
    public Tile getTileAtIndex(int index){
        return board.getTiletIndex(index);
    }

    public void setTitleText(String info, String lblTitle, Color titleColor, Color titleTxtColor) {
        mainPanel.setTitleText(info, lblTitle, titleColor, titleTxtColor);
    }

    public void setMarker(Player player,int i) {
        mainPanel.setMarker(player, i);
    }
    public void setTextDefault() {
        mainPanel.setTextDefault();
    }

    public void addPlayerTabs(){
        mainPanel.addPlayerTabs();
    }

    public void updatePlayerTabs(){
        mainPanel.updatePlayerTabs();
    }

    public void appendWestPanel(String text){
        mainPanel.appendWestPanel(text);
    }
    public EventPanel getEventPanel(){
        return mainPanel.getEventPanel();
    }
    public Dice getDice1() {
        return dice1;
    }
    public Dice getDice2() {
        return dice2;
    }
    public void setDice1(Dice dice1) {
        this.dice1 = dice1;
    }
    public void setDice2(Dice dice2) {
        this.dice2 = dice2;
    }

    public int getRoll1() {
        return roll1;
    }

    public void setRoll1(int roll1) {
        this.roll1 = roll1;
    }

    public int getRoll2() {
        return roll2;
    }

    public void setRoll2(int roll2) {
        this.roll2 = roll2;
    }



    private class Movement extends Thread {
        int diceRoll;
        int flag = 0;
        Player activePlayer = playerList.getActivePlayer();
        int prevPosition;

        public Movement(int diceRoll) {
            this.diceRoll = diceRoll;
        }
        @Override
        public void run() {
            while (flag < diceRoll) {
                prevPosition = activePlayer.getPosition();
                activePlayer.setPosition(1);
                mainPanel.movePlayerOnBoard(activePlayer);
                flag++;
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            mainPanel.enableEndTurnBtn();
            handleEventLandedOn();
        }
    }
}
