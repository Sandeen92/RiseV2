package utilities;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Constants {

    private static String systemPath = System.getProperty("user.dir");
    private static String imagePath = systemPath + "\\Program\\src\\resources\\images\\";
    private static String tokenPath = imagePath + "playerToken\\";
    private static String dicePath = imagePath + "dice\\";
    private static String tilePath = imagePath + "tiles\\";
    private static String musicPath = systemPath + "\\Program\\src\\resources\\music\\";

    public class GameWindow {
        public static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        public static final int screenWidth = (int) screenSize.getWidth();
        public static final int screenHeight = (int) screenSize.getHeight();
    }
    public static boolean isMacOS() {
        String os = System.getProperty("os.name");
        return os.contains("Mac");
    }

    private static String getImagePath(){
        if(isMacOS()){
            return systemPath + "/Program/src/resources/images/";
        }
        return systemPath + "\\Program\\src\\resources\\images\\";
    }

    private static String getTokenPath(){
        if(isMacOS()){
            return getImagePath() +  "playerToken/";
        }
        return getImagePath() + "playerToken\\";
    }

    private static String getDicePath(){
        if(isMacOS()){
            return getImagePath() + "dice/";
        }
        return getImagePath() + "dice\\";
    }
    private static String getTilePath(){
        if(isMacOS()){
            return getImagePath() + "tiles/";
        }
        return getImagePath() + "tiles\\";
    }

    private static String getMusicPath(){
        if(isMacOS()){
            return systemPath + "/Program/src/resources/music/";
        }
        return systemPath + "\\Program\\src\\resources\\music\\";
    }

    public class PlayerTokenImages {
        public static final ImageIcon MAGENTA = new ImageIcon(getTokenPath() + "playerMagenta.jpg");
        public static final ImageIcon PURPLE = new ImageIcon(getTokenPath() + "playerPurple.jpg");
        public static final ImageIcon ORANGE = new ImageIcon(getTokenPath() + "playerOrange.jpg");
        public static final ImageIcon RED_PAWN = resizeImage(new ImageIcon(getTokenPath() + "Red_pawn.svg.png"));
        public static final ImageIcon YELLOW_PAWN = resizeImage(new ImageIcon(getTokenPath() + "Yellow_pawn.svg.png"));
        public static final ImageIcon GREEN_PAWN = resizeImage(new ImageIcon(getTokenPath() + "Green_pawn.svg.png"));
        public static final ImageIcon CYAN_PAWN = resizeImage(new ImageIcon(getTokenPath() + "Blue_pawn.svg.png"));
        public static final ImageIcon RED_KNIGHT = resizeImage(new ImageIcon(getTokenPath() + "Red_knight.svg.png"));
        public static final ImageIcon YELLOW_KNIGHT = resizeImage(new ImageIcon(getTokenPath() + "Yellow_knight.svg.png"));
        public static final ImageIcon GREEN_KNIGHT = resizeImage(new ImageIcon(getTokenPath() + "Green_knight.svg.png"));
        public static final ImageIcon CYAN_KNIGHT = resizeImage(new ImageIcon(getTokenPath() + "Blue_knight.svg.png"));
        public static final ImageIcon RED_ROOK = resizeImage(new ImageIcon(getTokenPath() + "Red_rook.svg.png"));
        public static final ImageIcon YELLOW_ROOK = resizeImage(new ImageIcon(getTokenPath() + "Yellow_rook.svg.png"));
        public static final ImageIcon GREEN_ROOK = resizeImage(new ImageIcon(getTokenPath() + "Green_rook.svg.png"));
        public static final ImageIcon CYAN_ROOK = resizeImage(new ImageIcon(getTokenPath() + "Blue_rook.svg.png"));
        public static final ImageIcon RED_KING = resizeImage(new ImageIcon(getTokenPath() + "Red_king.svg.png"));
        public static final ImageIcon YELLOW_KING = resizeImage(new ImageIcon(getTokenPath() + "Yellow_king.svg.png"));
        public static final ImageIcon GREEN_KING = resizeImage(new ImageIcon(getTokenPath()+ "Green_king.svg.png"));
        public static final ImageIcon CYAN_KING = resizeImage(new ImageIcon(getTokenPath() + "Blue_king.svg.png"));

        private static ImageIcon resizeImage(ImageIcon originalIcon) {
            Image originalImage = originalIcon.getImage();
            int width = 40;
            int height = 40;
            Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(resizedImage);
        }
    }


    public class TileImages {
        public static final ImageIcon FORTUNE_TILE = new ImageIcon(getTilePath() + "fortune.png");
        public static final ImageIcon GO_TILE = new ImageIcon(getTilePath() + "Go.png");
        public static final ImageIcon GOTOJAIL_TILE = new ImageIcon(getTilePath() + "gojail.png");
        public static final ImageIcon JAIL_TILE = new ImageIcon(getTilePath() + "jail.png");
        public static final ImageIcon TAX_TILE = new ImageIcon(getTilePath() + "tax.png");
        public static final ImageIcon WORK_TILE = new ImageIcon(getTilePath() + "Work.png");
        public static final ImageIcon BANK_TILE = new ImageIcon(getTilePath() + "bank.png");
        public static final ImageIcon PROPERTY_WOOD_TILE = new ImageIcon(getTilePath() + "Wood.png");
        public static final ImageIcon PROPERTY_STONE_TILE = new ImageIcon(getTilePath() + "stone.png");
        public static final ImageIcon PROPERTY_FISHSTALL_TILE = new ImageIcon(getTilePath() + "fish.png");
        public static final ImageIcon PROPERTY_VEGGIESTALL_TILE = new ImageIcon(getTilePath() + "veggieStall.png");
        public static final ImageIcon PROPERTY_BAKERSTALL_TILE = new ImageIcon(getTilePath() + "bakerStall.png");
        public static final ImageIcon PROPERTY_TANNER_TILE = new ImageIcon(getTilePath() + "tanner.png");
        public static final ImageIcon PROPERTY_MILL_TILE = new ImageIcon(getTilePath() + "mill.png");
        public static final ImageIcon PROPERTY_SMITH_TILE = new ImageIcon(getTilePath() + "smith.png");
        public static final ImageIcon PROPERTY_BATH_TILE = new ImageIcon(getTilePath() + "bath.png");
        public static final ImageIcon PROPERTY_BAKERY_TILE = new ImageIcon(getTilePath() + "bakery.png");
        public static final ImageIcon PROPERTY_BUTCHER_TILE = new ImageIcon(getTilePath() + "butcher.png");
        public static final ImageIcon SUNDAY_CHURCH_TILE = new ImageIcon(getTilePath() + "sundaychurch.png");
        public static final ImageIcon PROPERTY_WAREHOUSE_TILE = new ImageIcon(getTilePath() + "warehouse.png");
        public static final ImageIcon PROPERTY_ALCHEMY_TILE = new ImageIcon(getTilePath() + "alch.png");
        public static final ImageIcon PROPERTY_STABLE_TILE = new ImageIcon(getTilePath() + "stable.png");
        public static final ImageIcon PROPERTY_COBBLER_TILE = new ImageIcon(getTilePath() + "cobbler.png");
        public static final ImageIcon PROPERTY_GENERALSTORE_TILE = new ImageIcon(getTilePath() + "general.png");
        public static final ImageIcon PROPERTY_SILVERSMITH_TILE = new ImageIcon(getTilePath() + "silver.png");
        public static final ImageIcon PROPERTY_ARMORER_TILE = new ImageIcon(getTilePath() + "armor.png");
        public static final ImageIcon PROPERTY_TAILOR_TILE = new ImageIcon(getTilePath() + "tailor.png");
        public static final ImageIcon PROPERTY_WEAPONSMITH_TILE = new ImageIcon(getTilePath() + "weapon.png");
        public static final ImageIcon PROPERTY_CHURCH_TILE = new ImageIcon(getTilePath() + "churchp.png");
        public static final ImageIcon PROPERTY_CASTLE_TILE = new ImageIcon(getTilePath() + "castle.png");
        public static final ImageIcon TAVERN_TILE = new ImageIcon(getTilePath() + "tavern.png");
    }


    public class DiceImages {
        public static final ImageIcon WHITE_1 = resizeImg(new ImageIcon(getDicePath() + "faceValue1White.png"));
        public static final ImageIcon WHITE_2 = resizeImg(new ImageIcon(getDicePath() + "faceValue2White.png"));
        public static final ImageIcon WHITE_3 = resizeImg(new ImageIcon(getDicePath() + "faceValue3White.png"));
        public static final ImageIcon WHITE_4 = resizeImg(new ImageIcon(getDicePath() + "faceValue4White.png"));
        public static final ImageIcon WHITE_5 = resizeImg(new ImageIcon(getDicePath() + "faceValue5White.png"));
        public static final ImageIcon WHITE_6 = resizeImg(new ImageIcon(getDicePath() + "faceValue6White.png"));

        public static final ImageIcon RED_1 = resizeImg(new ImageIcon(getDicePath() + "faceValue1.png"));
        public static final ImageIcon RED_2 = resizeImg(new ImageIcon(getDicePath() + "faceValue2.png"));
        public static final ImageIcon RED_3 = resizeImg(new ImageIcon(getDicePath() + "faceValue3.png"));
        public static final ImageIcon RED_4 = resizeImg(new ImageIcon(getDicePath() + "faceValue4.png"));
        public static final ImageIcon RED_5 = resizeImg(new ImageIcon(getDicePath() + "faceValue5.png"));
        public static final ImageIcon RED_6 = resizeImg(new ImageIcon(getDicePath() + "faceValue6.png"));



        public static ImageIcon resizeImg(ImageIcon icon){
            int diceWidth = GameWindow.screenSize.width / 20;
            int diceHeight = GameWindow.screenSize.height / 10;

            Image resizedImage = icon.getImage().getScaledInstance(diceWidth, diceHeight, Image.SCALE_SMOOTH);
            return new ImageIcon(resizedImage);
        }
    }


    public class BoardImages {

        public static final ImageIcon BOARD_IMAGE = new ImageIcon(getImagePath() + "RiseBoard750.png");

        public static ImageIcon getPaperBackgroundImage() {
            BufferedImage tempImg = null;
            try {
                tempImg = ImageIO.read(new File(getImagePath() + "backpaper.jpg"));

            } catch (IOException e) {

                e.printStackTrace();
            }

            Image img = tempImg.getScaledInstance(600, 350, Image.SCALE_SMOOTH);

            return new ImageIcon(img);
        }

        public static ImageIcon getStartMenuImage(){
            BufferedImage tempImg = null;
            try {
                tempImg = ImageIO.read(new File(getImagePath() + "fancyRoll.jpg"));

            } catch (IOException e) {

                e.printStackTrace();
            }

            Image img = tempImg.getScaledInstance(900,860, Image.SCALE_SMOOTH);

            return new ImageIcon(img);
        }


        public static ImageIcon getBackgroundImage() {
            BufferedImage tempImg = null;
            try {
                tempImg = ImageIO.read(new File(getImagePath() + "back2jpg.jpg"));

            } catch (IOException e) {

                e.printStackTrace();
            }

            Image img = tempImg.getScaledInstance(GameWindow.screenWidth, GameWindow.screenHeight, Image.SCALE_SMOOTH);

            return new ImageIcon(img);
        }
    }


    public class AudioFiles{

        public static File bgMusic = new File(getMusicPath() + "bgMusic.wav");
        public static File duraw = new File(getMusicPath() + "duraw.wav");
        public static File victory = new File(getMusicPath() + "victory.wav");
        public static File hips = new File(getMusicPath() + "Hips Don't Lie.wav");
        public static File yeah = new File(getMusicPath() + "Yeah!.wav");
    }
}
