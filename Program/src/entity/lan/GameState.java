package entity.lan;

import controller.BoardController;
import controller.LanController;
import entity.Dice;
import entity.player.Player;
import entity.player.PlayerList;
import entity.player.PlayerRanks;
import entity.tiles.Property;

import java.util.ArrayList;
import java.util.List;

public class GameState {


    private Player activePlayer;
    private int stepsToTake;

    private String identifier;
    //a problem right now can be that we can not be sure what the GameState object we're sending is for.
    //Maybe each GameState object can have an identifier like a String.

    public GameState(){

    }


    /**
     * GameState that can be used to update the Board pieces for the fellow Clients.
     * @param stepsToTake x
     * @param player x
     */
    public GameState(int stepsToTake, Player player){
        identifier = "Piece Movement";
        setStepsToTake(stepsToTake);
        this.activePlayer = player;
    }

    public GameState(String NameOfProperty, Player player){
        identifier = "Property ";
        setStepsToTake(stepsToTake);
        this.activePlayer = player;
    }
    public GameState(boolean buttonValue, GameClient gameClient){
        identifier = "Button";

    }

    public void setStepsToTake(int stepsToTake) {
        this.stepsToTake = stepsToTake;
    }

    public int getStepsToTake() {
        return stepsToTake;
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    public void setPropertyList(ArrayList<Property> properties) {
        activePlayer.setPropertiesOwned(properties);
    }

    public void setRank(PlayerRanks playerRank) {
        activePlayer.setPlayerRank(playerRank);
    }
}
