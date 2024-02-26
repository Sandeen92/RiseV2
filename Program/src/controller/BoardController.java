package controller;

import entity.*;
import entity.player.*;
import view.*;

public class BoardController {

    private Dice dice1;
    private Dice dice2;
    private DicePanel dicePanel;
    private PlayerList playerList;

    public BoardController(){
        this.dice1 = new Dice();
        this.dice2 = new Dice();
        this.playerList = new PlayerList();
        this.dicePanel = new DicePanel(this);
    }


    public int rollDices(){
        int roll1 = dice1.roll();
        int roll2 = dice2.roll();
        setDiceImages();
        return roll1 + roll2;
    }

    public void setDiceImages(){
        dicePanel.setDiceImages(dice1.getCurrentValueImage(), dice2.getCurrentValueImage());
    }

    public void endTurn(){

    }
}
