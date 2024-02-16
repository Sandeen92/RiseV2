package westSidePanel;

import events.ManageEvents;
import player.PlayerList;
import tiles.Property;

import java.awt.BorderLayout;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.DefaultCaret;

/**
 * @author RohanSamandari, AevanDino
 *
 */
public class WestSidePanel extends JPanel {

	/**
	 * WestPanel which shows Info about each Boxes.
	 * 
	 * @author RohanSamandari
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblInfoTitle, lblHistoryTitle;
	private JPanel pnlSpace, pnlHeading, pnlInfo, pnlHistory;
	private EventPanel pnlEvent;
	private Font font = new Font("ALGERIAN", Font.BOLD, 18);
	private JTextArea txtTileInfo = new JTextArea();
	private JTextArea txtMessage = new JTextArea();
	private JScrollPane scroller = new JScrollPane(txtMessage);
	
	private DefaultCaret caret = (DefaultCaret)txtMessage.getCaret();
	
	private Border border = BorderFactory.createLineBorder(Color.DARK_GRAY);
	private String def = "\n\nMove your mouse on a tile \n   which you want to see \n"
			+ "      information about!";
	private String title = "Information";
	private Color titleColor = Color.DARK_GRAY;
	private ManageEvents eventManager;
	//private EventPanel eventPanel;

	public WestSidePanel() {
		  
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE); 

		/**
		 * HeadPanel with Information Label
		 */
		pnlSpace = new JPanel();

		pnlSpace.setPreferredSize(new Dimension(10, 15));
		pnlSpace.setOpaque(false);
		pnlHeading = new JPanel();
		pnlHeading.setBorder(border);
		pnlHeading.setPreferredSize(new Dimension(340, 80));
		lblInfoTitle = new JLabel(title);

		lblInfoTitle.setFont(new Font("ALGERIAN", Font.BOLD, 26));
		lblInfoTitle.setPreferredSize(new Dimension(320, 70));
		lblInfoTitle.setBorder(BorderFactory.createLineBorder(Color.black));
		lblInfoTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfoTitle.setBackground(titleColor);
		lblInfoTitle.setOpaque(true);
		lblInfoTitle.setForeground(Color.white);

		pnlHeading.setBackground(new Color(0, 0, 0, 20));
		pnlHeading.add(lblInfoTitle, BorderLayout.SOUTH);

		/**
		 * TileInformation Panel
		 */
		pnlInfo = new JPanel();
		pnlInfo.setPreferredSize(new Dimension(340, 255));
		pnlInfo.setBorder(border);
		pnlInfo.setBackground(new Color(0, 0, 0, 20));
		txtTileInfo.setForeground(new Color(71, 60, 50, 225));
		txtTileInfo.setPreferredSize(new Dimension(320, 300));
		txtTileInfo.setFont(font);
		txtTileInfo.setEditable(false);
		txtTileInfo.setMargin(new Insets(10, 10, 10, 10));
		txtTileInfo.setText(def);

		pnlInfo.add(txtTileInfo);

		/**
		 * Event Panel
		 *
		 * The text message below is the message that was appended to the scrollpane below.
		 */
		//txtMessage.setFont(new Font("Gabriola", Font.BOLD, 18));
		//txtMessage.setMargin(new Insets(10, 10, 10, 10));
		//txtMessage.setEditable(false);
		//txtMessage.setForeground(new Color(71, 60, 50, 225));
		/**
		 * The scroller was used to append history of the game.
		 */
		//scroller.setBackground(Color.white);
		//scroller.setForeground(Color.black);
		//scroller.setForeground(new Color(71, 60, 50, 225));
		//scroller.setPreferredSize(new Dimension(320, 405));
		//scroller.setAutoscrolls(true);

		pnlEvent = new EventPanel(this, font);

		/**
		 * The main Panel
		 */
		setOpaque(false);
		setPreferredSize(new Dimension(345, 860));
		setBackground(Color.yellow);
		setBorder(border);
		add(pnlSpace);
		add(pnlHeading);
		add(pnlInfo);
		add(pnlEvent);
	}
	/**
	 * This method is like the above method but only for those boxes which has
	 * default color.
	 * 
	 * @param info
	 * @param lblTitle
	 * @param titleColor
	 * @param titleTxtColor
	 */
	public void setTitleText(String info, String lblTitle, Color titleColor, Color titleTxtColor) {
		txtTileInfo.setText(info);
		lblInfoTitle.setText(lblTitle);
		lblInfoTitle.setBackground(titleColor);
		lblInfoTitle.setForeground(titleTxtColor);
	}

	/**
	 * sets the info text to default when mouse does not pointing on any box.
	 */
	public void setTextDefault() {
		txtTileInfo.setText(def);
		lblInfoTitle.setText(title);
		lblInfoTitle.setBackground(titleColor);
		lblInfoTitle.setForeground(Color.white);
	}

	/**
	 * Adds the history of the game and updates it.
	 * NOT IN USE AT THIS POINT
	 * @param res
	 */
	public void append(String res) {
		//txtMessage.append(res);
	}


	/**
	 * Allows access to the event manager.
	 * The method below are used to access the instances of the ManageEvents class as well as the EventPanel class
	 * @param eventManager
	 */
	public void setEventManager(ManageEvents eventManager) {
		this.eventManager = eventManager;
	}
	public ManageEvents getEventManager() {
		return eventManager;
	}
	public EventPanel getEventPanel(){
		return pnlEvent;
	}
}
