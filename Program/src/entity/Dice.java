package entity;

import javax.swing.ImageIcon;
import static utilities.Constants.DiceImages.*;

public class Dice {

	private int value = 1;
	private ImageIcon[] diceImages = {WHITE_1, WHITE_2, WHITE_3, WHITE_4, WHITE_5, WHITE_6};
	private ImageIcon currentValueImage;


	public Dice() {
		currentValueImage = diceImages[value - 1];
	}

	public int roll(){
		value = (int) (Math.random() * 6) + 1;
        currentValueImage = diceImages[value - 1];
        return value;
	}

	public int getValue() {
        return value;
    }
	public ImageIcon getCurrentValueImage() {
        return currentValueImage;
    }
    public ImageIcon[] getDiceImages() {
        return diceImages;
    }
}