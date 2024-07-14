package events;

import java.io.File;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

import board.Board;
import dice.Dice;
import eastSidePanels.EastSidePanel;
import messageGui.DeathGUI;
import messageGui.FortuneTellerGUI;
import messageGui.SecretGui;
import messageGui.WinGui;
import player.Player;
import player.PlayerList;
import player.PlayerRanks;
import tiles.FortuneTeller;
import tiles.GoToJail;
import tiles.Jail;
import tiles.Property;
import tiles.SundayChurch;
import tiles.Tavern;
import tiles.Tax;
import tiles.Tile;
import tiles.Work;
import westSidePanel.WestSidePanel;

/**
 * The class handles all the events that occur when a player lands on a tile.
 * @author Seth Oberg, Rohan Samandari,Muhammad Abdulkhuder ,Sebastian Viro, Aevan Dino, Alexander Fleming.
 */

public class ManageEvents {
	private PlayerList playerList;
	private Board board;
	private Dice dice;
	private DeathGUI deathGUI;
	private FortuneTellerGUI msgGUI;
	private EastSidePanel eastPanel;
	private Random rand = new Random();
	private int roll;
	private int taxCounter = 0;
	private WestSidePanel westPanel;

	public ManageEvents(){

	}
	/**
	 * Constructor initializes objects in the parameter. Creates Death -and MessageGUI.
	 * @param board
	 * @param playerList
	 * @param pnlWest
	 * @param dice
	 * @param eastPanel
	 */
	public ManageEvents(Board board, PlayerList playerList, WestSidePanel pnlWest, Dice dice, EastSidePanel eastPanel) {
		this.dice = dice;
		this.westPanel = pnlWest;
		this.board = board;
		this.playerList = playerList;
		this.eastPanel = eastPanel;
		deathGUI = new DeathGUI();
		msgGUI = new FortuneTellerGUI();
		westPanel.setEventManager(this);
	}

	/**
	 * Method checks what type of tile the player has landed on.
	 * @param tile the player landed on.
	 * @param player, player who landed on a tile.
	 */
	public void newEvent(Tile tile, Player player) {
		player.checkPlayerRank();

		if (player.getPlayerRank() == PlayerRanks.KINGS) {
			new WinGui();
		}

		if (playerList.getLength() == 1) {
			new WinGui();
		}

		if (tile instanceof Property) {
			propertyEvent(tile, player);
		}

		if (tile instanceof Tax) {
			taxEvent(tile, player);
		}

		if (tile instanceof Jail) {
			jailEvent(tile, player);
		}

		if (tile instanceof GoToJail) {
			goToJailEvent(tile, player);
		}

		if (tile instanceof Tavern) {
			tavernEvent(tile, player);
		}

		if (tile instanceof SundayChurch) {
			churchEvent(player);
		}

		if (tile instanceof Work) {
			workEvent(tile, player);
		}

		if (tile instanceof FortuneTeller) {
			fortuneTellerEvent(tile, player);
		}
		eastPanel.addPlayerList(playerList);
	}

	private void dialogHandler(EventCases event, Tile tile, Player player){ //TODO: Handle missing events.
		switch (event){
			case Property -> {
				Property tempProperty = (Property) tile;
				westPanel.getEventPanel().setPropertyEvent(tempProperty, player);
			}
			case Tavern -> {
				Tavern tempTavern = (Tavern) tile;
				westPanel.getEventPanel().setTavernEvent(tempTavern, player);
			}
			case Fortune -> {
				//TODO: Implement fortune dialog
			}
			case Jail -> {
				//TODO: Implement jail dialog
			}
			case GoToJail -> {
				GoToJail tempJail = (GoToJail) tile;
				westPanel.getEventPanel().setGotoJailEvent(tempJail, player);
			}
			case Work -> {
				Work tempWork = (Work) tile;
				westPanel.getEventPanel().setWorkEvent(tempWork, player);
			}
			case Tax -> {
				Tax tempTax = (Tax) tile;
				westPanel.getEventPanel().setTaxEvent(tempTax, player);
			}
			case MissingFunds -> {
				Property tempProperty = (Property) tile;
				westPanel.getEventPanel().setMissingFundsEvent(tempProperty, player);
			}
			case PayRent -> {
				Property tempProperty = (Property) tile;
				westPanel.getEventPanel().setPayRentEvent(tempProperty, player);
			}
		}
	}
	/**
	 * This method is supposed to be called from any class that requires the current
	 * player to pay any amount, if the user does not have the amount required they
	 * should be removed from the game
	 */
	public void control(Player player, int amount) {
		if (player.getBalance() < amount) {
			player.setIsAlive(false);
			playerList.switchToNextPlayer();
			playerList.eliminatePlayer(player);
			playerList.updatePlayerList();
			eastPanel.addPlayerList(playerList.getList());
			dice.setPlayerList(playerList.getList());
			board.removePlayer(player);
			deathGUI.addGui();
		} 
	}

	/**
	 * Method called when player lands on a property. Checks if it's availability and if the player has to pay rent or 
	 * can purchase the property.
	 * @param tile
	 * @param player
	 */
	public void propertyEvent(Tile tile, Player player) {
		Property tempProperty = (Property) tile;
		int tempInt = 0;

		if (tempProperty.getPurchaseable()) {
			if (player.getBalance() < tempProperty.getPrice()) {
				dialogHandler(EventCases.MissingFunds, tile, player);
			} else {
				dialogHandler(EventCases.Property, tile, player);
				//westPanel.getEventPanel().handleEvent(EventCases.Property, tempProperty, player);
			}
		} else if (!tempProperty.getPurchaseable()) {

			if (tempProperty.getLevel() == 0) {
				tempInt = tempProperty.getDefaultRent();

				control(player, tempInt);
				if (player.isAlive()) {
					dialogHandler(EventCases.PayRent, tile, player);
					//westPanel.append(player.getName() + " paid " + tempProperty.getTotalRent() + " GC to "
					//		+ tempProperty.getOwner().getName() + "\n");
					player.decreaseBalace(tempInt);
					player.decreaseNetWorth(tempInt);
					tempProperty.getOwner().increaseBalance(tempInt);
				}
			} else {
				tempInt = tempProperty.getTotalRent();
				control(player, tempInt);
				if (player.isAlive()) {
					dialogHandler(EventCases.PayRent, tile, player);
					//westPanel.append(player.getName() + " paid " + tempProperty.getTotalRent() + " GC to "
					//		+ tempProperty.getOwner().getName() + "\n");
					player.decreaseBalace(tempInt);
					tempProperty.getOwner().increaseBalance(tempInt);
				}
			}
		}
	}

	/**
	 * Method called when the player lands on a work tile.
	 * @param tile
	 * @param player
	 */
	public void workEvent(Tile tile, Player player) {

		Work tempWorkObject = (Work) tile;
		tempWorkObject.setPlayer(player);
		tempWorkObject.payPlayer(getRoll());

		//westPanel.append(player.getName() + " Got " + tempWorkObject.getPay() + " GC\n");
		dialogHandler(EventCases.Work, tile, player);

	}

	/**
	 * Method called when the player lands on a tax tile. 
	 * @param tile
	 * @param player
	 */
	public void taxEvent(Tile tile, Player player) {
		Tax tempTaxObject = (Tax) tile;
		int chargePlayer = tempTaxObject.getTax();

		control(player, chargePlayer);

		if (player.isAlive()) {
			//westPanel.append(player.getName() + " paid 200 GC in tax\n");
			player.decreaseBalace(chargePlayer);
			player.decreaseNetWorth(chargePlayer);
			taxCounter++;
			dialogHandler(EventCases.Tax, tile, player);
		}
		eastPanel.addPlayerList(playerList);

	}

	/**
	 * Gets the total tax paid by players
	 * @return total tax
	 */
	public int getChurchTax() {
		int totalTax = taxCounter * 200;
		return totalTax;
	}

	/**
	 * Method called when players lands on a tavern tile, checks it's availability. 
	 * @param tile
	 * @param player
	 */
	public void tavernEvent(Tile tile, Player player) {
		Tavern tempTavernObj = (Tavern) tile;

		if (tempTavernObj.getPurchaseable()) {
			if (player.getBalance() < tempTavernObj.getPrice()) {
				dialogHandler(EventCases.MissingFunds, tile, player);
			} else {
				dialogHandler(EventCases.Tavern, tile, player); //
			}
		} else {
			int randomValue = 0;

			if (tempTavernObj.getOwner().getAmountOfTaverns() == 1) {
				randomValue = (getRoll() * 10);
			} else if (tempTavernObj.getOwner().getAmountOfTaverns() == 2) {
				randomValue = (getRoll() * 20);
			}
			
			control(player, randomValue);
			if (player.isAlive()) { //TODO: Implement using the new dialog UI, handle the randomValue variable within in some smart way
				JOptionPane.showMessageDialog(null, player.getName() + " paid " + randomValue + " GC to " 
						+ tempTavernObj.getOwner().getName());
				westPanel.append(player.getName() + " paid " + randomValue + " GC to "
						+ tempTavernObj.getOwner().getName() + "\n");
				tempTavernObj.getOwner().increaseBalance(randomValue);
				tempTavernObj.getOwner().increaseNetWorth(randomValue);
				player.decreaseBalace(randomValue);
			}
		}
	}

	/**
	 * Method for jailed players, giving them the option to pay bail if the have enough balance.
	 * @param tile
	 * @param player in jail
	 */
	public void jailEvent(Tile tile, Player player) { //TODO: Update the new dialog UI
		if (player.isPlayerInJail() && (player.getJailCounter()) < 2) {
			//westPanel.append(player.getName() + " is in jail for " + (2 - player.getJailCounter()) + " more turns\n"); TODO: These append messages can be logged
			player.increaseJailCounter();
			if (player.getBalance() > (player.getJailCounter() * 50)) {
				jailDialog(player);
			} else {
				JOptionPane.showMessageDialog(null, "You can not afford the bail");
				//TODO: Implement jail dialog
			}
		} else if (player.getJailCounter() >= 2) {
			player.setPlayerIsInJail(false);
			player.setJailCounter(0);
			dice.activateRollDice();
		}
	}

	/**
	 * Method to jail a player.
	 * @param tile
	 * @param player
	 */
	public void goToJailEvent(Tile tile, Player player) {
		player.setPlayerIsInJail(true);
		board.removePlayer(player);
		player.setPositionInSpecificIndex(10);
		board.setPlayer(player);
		dialogHandler(EventCases.GoToJail, tile, player);
		//westPanel.append(player.getName() + " is in jail for " + (2 - player.getJailCounter()) + " more turns\n");
	}

	/**
	 * Method called if the player lands on sunday church. Pays out all the collected tax then resets the counter.
	 * @param player
	 */
	public void churchEvent(Player player) {
		player.increaseBalance(200 * taxCounter);
		player.increaseNetWorth(200 * taxCounter);
		westPanel.append(player.getName() + " got " + taxCounter * 200 + " GC from the church\n");
		taxCounter = 0;
	}

	/**
	 * Method for a dialog if the player is able to purchase a property.
	 * @param property in question.
	 * @param player in question.
	 */
	public void purchaseTile(String source, Property property, Player player) {
		System.out.println(source);
		if (source.equals("YES") && (property.getPrice() <= player.getBalance())) {
			property.setOwner(player);
			player.addNewProperty(property);
			property.setPurchaseable(false);
			player.decreaseBalace(property.getPrice());
			//westPanel.append(player.getName() + " purchased " + property.getName() + "\n");
		}
		else {
			//westPanel.append(player.getName() + " did not purchase " + property.getName() + "\n");
		}
		westPanel.getEventPanel().resetEventPanel();
		eastPanel.addPlayerList(playerList);
	}
	/**
	 * Method for a dialog if the player wants to purchase a tavern.
	 * @param tavern, the to buy.
	 * @param player, player who landed on the tavern.
	 */
	public void purchaseTavern(String source, Tavern tavern, Player player) {
		if (source.equals("YES") && (tavern.getPrice() <= player.getBalance())) {
			tavern.setOwner(player);
			player.addNewTavern(tavern);
			tavern.setPurchaseable(false);
			player.decreaseBalace(tavern.getPrice());
			westPanel.append(player.getName() + " purchased " + tavern.getName() + "\n");
		} else {
			westPanel.append(player.getName() + " did not purchase " + tavern.getName() + "\n");
		}
		westPanel.getEventPanel().resetEventPanel();
		eastPanel.addPlayerList(playerList);
		eastPanel.revalidate();
		eastPanel.repaint();
	}

	/**
	 * @return roll of the dice.
	 */
	public int getRoll() {
		return dice.getRoll();
	}

	/**
	 * Sets the roll of the dice.
	 * @param dice
	 */
	public void setRoll(Dice dice) {
		this.roll = dice.getRoll();
	}

	/**
	 * Message for the prisoner to choose if the player wants to pay the bail and
	 * get free
	 * @param player in jail.
	 */
	public void jailDialog(Player player) { //TODO: Implement the new dialog UI
		int yesOrNo = JOptionPane.showConfirmDialog(null,
				"Do you want to pay the bail\nWhich is " + (player.getJailCounter() * 50) + " GC?", "JOption",
				JOptionPane.YES_NO_OPTION);
		int totalBail = player.getJailCounter() * 50;
		if (yesOrNo == 0 && (totalBail <= player.getBalance())) {
			player.setJailCounter(0);
			player.setPlayerIsInJail(false);
			westPanel.append(player.getName() + " paid the bail and\ngot free from jail\n");
			dice.activateRollDice();
		} else {
			westPanel.append(player.getName() + " did not pay tha bail\n and is still in jail\n");
		}
	}
	
	/**
	 * Method for FortuneTeller, small chance for a secret event to trigger.
	 * @param tile, tile the player landed on.
	 * @param player, player in question.
	 */
	private void fortuneTellerEvent(Tile tile, Player player) { //TODO: Implement the new dialog UI
		FortuneTeller tempCard = (FortuneTeller) tile;
		if (rand.nextInt(10) == 0) {
			new SecretGui();
			new Thread(new SecretSleeper(tempCard, player));
			eastPanel.addPlayerList(playerList);

		} else {
			fortune(tempCard, player);
		}
	}

	/**
	 * Method that either withdraws or adds gold coins to a player depending on the type of fortune.
	 * @param tempCard, instance of FortuneTeller 
	 * @param player, player who landed on the tile
	 */
	public void fortune(FortuneTeller tempCard, Player player) { //TODO: Implement the new dialog UI
		tempCard.setAmount(rand.nextInt(600) - 300);
		if (tempCard.getAmount() < 0) {
			int pay = (tempCard.getAmount() * -1);
			tempCard.setIsBlessing(false);
			tempCard.setFortune("CURSE");
			control(player, pay);
			if (player.isAlive() == true) {
				westPanel.append(player.getName() + " paid " + pay + " GC\n");
				player.decreaseBalace(pay);
				player.decreaseNetWorth(pay);
				msgGUI.newFortune(false, pay);
			}

		} else {
			tempCard.setIsBlessing(true);
			tempCard.setFortune("BLESSING");
			player.increaseBalance(tempCard.getAmount());
			player.increaseNetWorth(tempCard.getAmount());
			westPanel.append(player.getName() + " received " + tempCard.getAmount() + " CG\n");
			msgGUI.newFortune(true, tempCard.getAmount());
		}
	}	
	
	/**
	 * This class is an easter egg. That gives the player 5 fortunes.
	 * @author Sebastian viro ,Muhammad Abdulkhuder
	 *
	 */
	private class SecretSleeper extends Thread {

		private FortuneTeller tempCard;
		private Player player;
		private Clip clip;
			
		/**
		 * @param tempCard
		 * @param player
		 * Starts the sleeper
		 */
		public SecretSleeper(FortuneTeller tempCard, Player player) {
			this.tempCard = tempCard;
			this.player = player;
			start();

		}
		
		public void run() {
			try {
				for (int i = 0; i < 5; i++) {
					File musicPath = new File("Program/music/duraw.wav");				
					AudioInputStream ais = AudioSystem.getAudioInputStream(musicPath);
					clip = AudioSystem.getClip();
					clip.open(ais);
					clip.start();
					fortune(tempCard, player);
					Thread.sleep(3000);
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {

			}
		}
	}
}
