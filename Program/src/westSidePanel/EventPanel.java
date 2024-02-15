package westSidePanel;

import player.Player;
import tiles.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @Author Alexander Fleming
 * This class is used to create the EventPanel which shows information about the current event.
 */

    //TODO: Add the rest of the events
    //TODO: Manage the different outcomes of the events

public class EventPanel extends JPanel{
    private WestSidePanel westPanel;
    private Border border = BorderFactory.createLineBorder(Color.DARK_GRAY);
    private JLabel title;
    private Font titleFont = new Font("ALGERIAN", Font.BOLD, 18);
    private Font font;

    public EventPanel(WestSidePanel westPanel, Font font){
        this.westPanel = westPanel;
        this.font = font;
        title = createTitle("Game Event");

        createEventPanel();
    }

    /**
     * This method is used to display the missing funds event.
     * @param property The property tile
     * @param player The player currently on the tile
     */
    public void setMissingFundsEvent(Property property, Player player){
        setEventPicture(property);
        addLabels(property.getName(), "You don't have enough money to purchase this.");
    }
    /**
     * This method is used to display the rent paid event.
     * @param property The property tile
     * @param player The player currently on the tile paying the rent
     */
    public void setPayRentEvent(Property property, Player player){
        setEventPicture(property);
        addLabels(property.getName(), player.getName() + " paid " + property.getTotalRent() + " GC to " + property.getOwner().getName());
    }
    /**
     * This method is used to set the event for the property tile.
     * @param property the property tile
     * @param player the player currently on the tile
     */
    public void setPropertyEvent(Property property, Player player){
        setEventPicture(property); //Sets the image
        addLabels(property.getName(), "Do you want to purchase this property for " + property.getPrice() + " GC");

        JButton yesButton = new JButton("YES");
        yesButton.addActionListener(e -> westPanel.getEventManager().purchaseTile(yesButton.getText(), property, player));
        JButton noButton = new JButton("NO");
        noButton.addActionListener(e -> westPanel.getEventManager().purchaseTile(noButton.getText(), property, player));

        add(yesButton);
        add(noButton);
    }

    /**
     * This method is used to set the event for the tavern tile.
     * @param tavern The tavern tile
     * @param player The player currently on the tile
     */
    public void setTavernEvent(Tavern tavern, Player player){
        setEventPicture(tavern);
        addLabels(tavern.getName(), "Do you want to purchase this property for " + tavern.getPrice() + " GC");

        JButton yesButton = new JButton("YES");
        yesButton.addActionListener(e -> westPanel.getEventManager().purchaseTavern(yesButton.getText(), tavern, player));
        JButton noButton = new JButton("NO");
        noButton.addActionListener(e -> westPanel.getEventManager().purchaseTavern(yesButton.getText(), tavern, player));

        add(yesButton);
        add(noButton);
    }
    /**
     * This method is used to set the event for the tax tile.
     * @param tax The tax tile
     * @param player The player
     */
    public void setTaxEvent(Tax tax, Player player){
        setEventPicture(tax);
        String text = "You have to pay " + tax.getTax() + " GC in taxes";
        addLabels(tax.getName(),text);
    }

    /**
     * This method is used to set the event for the work tile.
     * @param work The work tile
     * @param player The player
     */
    public void setWorkEvent(Work work, Player player){
        setEventPicture(work);
        String text = "You rolled a " + work.getRoll() + ". You get paid " + work.getPay() + " GC for your work!";
        addLabels("Work",text);
    }

    /**
     * This method is used to set the event for the GoToJail tile.
     * @param jail The GoToJail tile
     * @param player The player
     */
    public void setGotoJailEvent(GoToJail jail, Player player){
        setEventPicture(jail);
        addLabels(player.getName() + " has been jailed.", player.getName() + " is in jail for " + (2 - player.getJailCounter()) + " more turns.");
    }

    /**
     * Sets the picture for the current event.
     * @param tile The property tile
     */
    public void setEventPicture(Tile tile){
        JLabel lblPicture = new JLabel("");
        lblPicture.setBorder(null);
        lblPicture.setBounds(0, 0, 330/2, 480/2);
        BufferedImage img = loadImage(tile);
        Image resizedImg = img.getScaledInstance(lblPicture.getWidth(), lblPicture.getHeight(), Image.SCALE_SMOOTH);
        lblPicture.setIcon(new ImageIcon(resizedImg));
        add(lblPicture);
    }

    /**
     * Loads the image for the tile.
     * @param tile
     * @return
     */
    private BufferedImage loadImage(Tile tile){
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(
                    tile.getPicture().toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    /**
     * The methods below are used to create the event panel.
     */
    private void createEventPanel(){
        setPreferredSize(new Dimension(340, 475));
        setOpaque(false);
        setBorder(border);
        add(title);
    }
    public void resetEventPanel(){
        removeAll();
        add(title);
        revalidate();
        repaint();
    }
    /**
     * The methods below are used to create the title for the event panel.
     * @param title The title of the event panel
     */
    private JLabel createTitle(String title){
        JLabel lblTitle = new JLabel(title);
        lblTitle.setPreferredSize(new Dimension(320, 50));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBorder(BorderFactory.createLineBorder(Color.black));
        lblTitle.setForeground(Color.white);
        lblTitle.setFont(font);
        return lblTitle;
    }
    /**
     * The methods below are used to add labels for the tile into the event panel.
     * @param title The title of the tile
     * @param text The text of the tile
     */
    private void addLabels(String title, String text){ //To avoid writing the same code over and over
        JLabel lblTitle = new JLabel(title);
        lblTitle.setForeground(Color.white);
        lblTitle.setFont(titleFont);
        JLabel lblText = new JLabel(text);
        lblText.setForeground(Color.white);

        add(lblTitle);
        add(lblText);
    }
}
