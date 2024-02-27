package controller;

import java.io.File;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

import entity.EventCases;
import view.messageGui.DeathGUI;
import view.messageGui.FortuneTellerGUI;
import view.messageGui.SecretGui;
import view.messageGui.WinGui;
import entity.player.Player;
import entity.player.PlayerRanks;
import entity.tiles.FortuneTeller;
import entity.tiles.GoToJail;
import entity.tiles.Jail;
import entity.tiles.Property;
import entity.tiles.SundayChurch;
import entity.tiles.Tavern;
import entity.tiles.Tax;
import entity.tiles.Tile;
import entity.tiles.Work;
import view.WestPanel;

/**
 * The class handles all the events that occur when a entity.player lands on a tile.
 * @author Seth Oberg, Rohan Samandari,Muhammad Abdulkhuder ,Sebastian Viro, Aevan Dino, Alexander Fleming.
 */

public class EventManager {

	private BoardController boardController;
	private DeathGUI deathGUI;
	private FortuneTellerGUI msgGUI;
	private Random rand = new Random();
	private int taxCounter = 0;
	private WestPanel westPanel;


	public EventManager(BoardController boardController) {
		this.boardController = boardController;
		deathGUI = new DeathGUI();
		msgGUI = new FortuneTellerGUI();
	}

	public void setWestPanel(WestPanel westPanel) {
		this.westPanel = westPanel;
	}


	public void newEvent(Tile tile, Player player) {
		player.checkPlayerRank();

		if (player.getPlayerRank() == PlayerRanks.KING) {
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
		boardController.addPlayerTabs();
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

	public void control(Player player, int amount) {
		if (player.getBalance() < amount) {
			player.setIsAlive(false);
			boardController.eliminatePlayer(player);
			boardController.removePlayer(player);
			deathGUI.addGui();
		} 
	}

	public void propertyEvent(Tile tile, Player player) {
		Property tempProperty = (Property) tile;
		int tempInt = 0;

		if (tempProperty.getPurchaseable()) {
			if (player.getBalance() < tempProperty.getPrice()) {
				dialogHandler(EventCases.MissingFunds, tile, player);
			} else {
				dialogHandler(EventCases.Property, tile, player);
				//westPanel.getEventPanel().handleEvent(EventCases.Property, tempProperty, entity.player);
			}
		} else if (!tempProperty.getPurchaseable()) {

			if (tempProperty.getLevel() == 0) {
				tempInt = tempProperty.getDefaultRent();

				control(player, tempInt);
				if (player.isAlive()) {
					dialogHandler(EventCases.PayRent, tile, player);
					//westPanel.append(entity.player.getName() + " paid " + tempProperty.getTotalRent() + " GC to "
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
					//westPanel.append(entity.player.getName() + " paid " + tempProperty.getTotalRent() + " GC to "
					//		+ tempProperty.getOwner().getName() + "\n");
					player.decreaseBalace(tempInt);
					tempProperty.getOwner().increaseBalance(tempInt);
				}
			}
		}
	}

	public void workEvent(Tile tile, Player player) {

		Work tempWorkObject = (Work) tile;
		tempWorkObject.setPlayer(player);
		tempWorkObject.payPlayer(boardController.getDiceSum());

		//westPanel.append(entity.player.getName() + " Got " + tempWorkObject.getPay() + " GC\n");
		dialogHandler(EventCases.Work, tile, player);

	}

	public void taxEvent(Tile tile, Player player) {
		Tax tempTaxObject = (Tax) tile;
		int chargePlayer = tempTaxObject.getTax();

		control(player, chargePlayer);

		if (player.isAlive()) {
			//westPanel.append(entity.player.getName() + " paid 200 GC in tax\n");
			player.decreaseBalace(chargePlayer);
			player.decreaseNetWorth(chargePlayer);
			taxCounter++;
			dialogHandler(EventCases.Tax, tile, player);
		}
		boardController.addPlayerTabs();

	}

	public int getChurchTax() {
		int totalTax = taxCounter * 200;
		return totalTax;
	}

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
				randomValue = (boardController.getDiceSum() * 10);
			} else if (tempTavernObj.getOwner().getAmountOfTaverns() == 2) {
				randomValue = (boardController.getDiceSum() * 20);
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

	public void jailEvent(Tile tile, Player player) { //TODO: Update the new dialog UI
		if (player.isPlayerInJail() && (player.getJailCounter()) < 2) {
			//westPanel.append(entity.player.getName() + " is in jail for " + (2 - entity.player.getJailCounter()) + " more turns\n"); TODO: These append messages can be logged
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
		}
	}

	public void goToJailEvent(Tile tile, Player player) {
		player.setPlayerIsInJail(true);
		boardController.removePlayer(player);
		player.setPositionInSpecificIndex(10);
		boardController.setPlayerToTile(player);
		dialogHandler(EventCases.GoToJail, tile, player);
		//westPanel.append(entity.player.getName() + " is in jail for " + (2 - entity.player.getJailCounter()) + " more turns\n");
	}

	public void churchEvent(Player player) {
		player.increaseBalance(200 * taxCounter);
		player.increaseNetWorth(200 * taxCounter);
		westPanel.append(player.getName() + " got " + taxCounter * 200 + " GC from the church\n");
		taxCounter = 0;
	}

	public void purchaseTile(String source, Property property, Player player) {
		System.out.println(source);
		if (source.equals("YES") && (property.getPrice() <= player.getBalance())) {
			property.setOwner(player);
			player.addNewProperty(property);
			property.setPurchaseable(false);
			player.decreaseBalace(property.getPrice());
			//westPanel.append(entity.player.getName() + " purchased " + property.getName() + "\n");
		}
		else {
			//westPanel.append(entity.player.getName() + " did not purchase " + property.getName() + "\n");
		}
		westPanel.getEventPanel().resetEventPanel();
		boardController.addPlayerTabs();
	}

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
		boardController.addPlayerTabs();
	}


	public void jailDialog(Player player) { //TODO: Implement the new dialog UI
		int yesOrNo = JOptionPane.showConfirmDialog(null,
				"Do you want to pay the bail\nWhich is " + (player.getJailCounter() * 50) + " GC?", "JOption",
				JOptionPane.YES_NO_OPTION);
		int totalBail = player.getJailCounter() * 50;
		if (yesOrNo == 0 && (totalBail <= player.getBalance())) {
			player.setJailCounter(0);
			player.setPlayerIsInJail(false);
			westPanel.append(player.getName() + " paid the bail and\ngot free from jail\n");
		} else {
			westPanel.append(player.getName() + " did not pay tha bail\n and is still in jail\n");
		}
	}

	private void fortuneTellerEvent(Tile tile, Player player) { //TODO: Implement the new dialog UI
		FortuneTeller tempCard = (FortuneTeller) tile;
		if (rand.nextInt(10) == 0) {
			new SecretGui();
			new Thread(new SecretSleeper(tempCard, player));
			boardController.addPlayerTabs();

		} else {
			fortune(tempCard, player);
		}
	}

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
	 * This class is an easter egg. That gives the entity.player 5 fortunes.
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
					File musicPath = new File("music/duraw.wav");
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
