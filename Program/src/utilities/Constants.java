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


    public class PlayerTokenImages {
        public static final ImageIcon RED_PAWN = resizeImage(new ImageIcon(tokenPath + "Red_pawn.svg.png"));
        public static final ImageIcon YELLOW_PAWN = resizeImage(new ImageIcon(tokenPath + "Yellow_pawn.svg.png"));
        public static final ImageIcon GREEN_PAWN = resizeImage(new ImageIcon(tokenPath + "Green_pawn.svg.png"));
        public static final ImageIcon BLUE_PAWN = resizeImage(new ImageIcon(tokenPath + "Blue_pawn.svg.png"));
        public static final ImageIcon PURPLE_PAWN = resizeImage(new ImageIcon(tokenPath + "Purple_pawn.svg.png"));
        public static final ImageIcon ORANGE_PAWN = resizeImage(new ImageIcon(tokenPath + "Orange_pawn.svg.png"));
        public static final ImageIcon CYAN_PAWN = resizeImage(new ImageIcon(tokenPath + "Cyan_pawn.svg.png"));
        public static final ImageIcon MAGENTA_PAWN = resizeImage(new ImageIcon(tokenPath + "Magenta_pawn.svg.png"));
        public static final ImageIcon RED_KNIGHT = resizeImage(new ImageIcon(tokenPath + "Red_knight.svg.png"));
        public static final ImageIcon YELLOW_KNIGHT = resizeImage(new ImageIcon(tokenPath + "Yellow_knight.svg.png"));
        public static final ImageIcon GREEN_KNIGHT = resizeImage(new ImageIcon(tokenPath + "Green_knight.svg.png"));
        public static final ImageIcon BLUE_KNIGHT = resizeImage(new ImageIcon(tokenPath + "Blue_knight.svg.png"));
        public static final ImageIcon PURPLE_KNIGHT = resizeImage(new ImageIcon(tokenPath + "Purple_knight.svg.png"));
        public static final ImageIcon ORANGE_KNIGHT = resizeImage(new ImageIcon(tokenPath + "Orange_knight.svg.png"));
        public static final ImageIcon CYAN_KNIGHT = resizeImage(new ImageIcon(tokenPath + "Cyan_knight.svg.png"));
        public static final ImageIcon MAGENTA_KNIGHT = resizeImage(new ImageIcon(tokenPath + "Magenta_knight.svg.png"));
        public static final ImageIcon RED_ROOK = resizeImage(new ImageIcon(tokenPath + "Red_rook.svg.png"));
        public static final ImageIcon YELLOW_ROOK = resizeImage(new ImageIcon(tokenPath + "Yellow_rook.svg.png"));
        public static final ImageIcon GREEN_ROOK = resizeImage(new ImageIcon(tokenPath + "Green_rook.svg.png"));
        public static final ImageIcon BLUE_ROOK = resizeImage(new ImageIcon(tokenPath + "Blue_rook.svg.png"));
        public static final ImageIcon PURPLE_ROOK = resizeImage(new ImageIcon(tokenPath + "Purple_rook.svg.png"));
        public static final ImageIcon ORANGE_ROOK = resizeImage(new ImageIcon(tokenPath + "Orange_rook.svg.png"));
        public static final ImageIcon CYAN_ROOK = resizeImage(new ImageIcon(tokenPath + "Cyan_rook.svg.png"));
        public static final ImageIcon MAGENTA_ROOK = resizeImage(new ImageIcon(tokenPath + "Magenta_rook.svg.png"));
        public static final ImageIcon RED_KING = resizeImage(new ImageIcon(tokenPath + "Red_king.svg.png"));
        public static final ImageIcon YELLOW_KING = resizeImage(new ImageIcon(tokenPath + "Yellow_king.svg.png"));
        public static final ImageIcon GREEN_KING = resizeImage(new ImageIcon(tokenPath + "Green_king.svg.png"));
        public static final ImageIcon BLUE_KING = resizeImage(new ImageIcon(tokenPath + "Blue_king.svg.png"));
        public static final ImageIcon PURPLE_KING = resizeImage(new ImageIcon(tokenPath + "Purple_king.svg.png"));
        public static final ImageIcon ORANGE_KING = resizeImage(new ImageIcon(tokenPath + "Orange_king.svg.png"));
        public static final ImageIcon CYAN_KING = resizeImage(new ImageIcon(tokenPath + "Cyan_king.svg.png"));
        public static final ImageIcon MAGENTA_KING = resizeImage(new ImageIcon(tokenPath + "Magenta_king.svg.png"));

        private static ImageIcon resizeImage(ImageIcon originalIcon) {
            Image originalImage = originalIcon.getImage();
            int width = 40;
            int height = 40;
            Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(resizedImage);
        }
    }


    public class TileImages {
        public static final ImageIcon FORTUNE_TILE = new ImageIcon(tilePath + "fortune.png");
        public static final ImageIcon GO_TILE = new ImageIcon(tilePath + "Go.png");
        public static final ImageIcon GOTOJAIL_TILE = new ImageIcon(tilePath + "gojail.png");
        public static final ImageIcon JAIL_TILE = new ImageIcon(tilePath + "jail.png");
        public static final ImageIcon TAX_TILE = new ImageIcon(tilePath + "tax.png");
        public static final ImageIcon WORK_TILE = new ImageIcon(tilePath + "Work.png");
        public static final ImageIcon BANK_TILE = new ImageIcon(tilePath + "bank.png");
        public static final ImageIcon PROPERTY_WOOD_TILE = new ImageIcon(tilePath + "Wood.png");
        public static final ImageIcon PROPERTY_STONE_TILE = new ImageIcon(tilePath + "stone.png");
        public static final ImageIcon PROPERTY_FISHSTALL_TILE = new ImageIcon(tilePath + "fish.png");
        public static final ImageIcon PROPERTY_VEGGIESTALL_TILE = new ImageIcon(tilePath + "veggieStall.png");
        public static final ImageIcon PROPERTY_BAKERSTALL_TILE = new ImageIcon(tilePath + "bakerStall.png");
        public static final ImageIcon PROPERTY_TANNER_TILE = new ImageIcon(tilePath + "tanner.png");
        public static final ImageIcon PROPERTY_MILL_TILE = new ImageIcon(tilePath + "mill.png");
        public static final ImageIcon PROPERTY_SMITH_TILE = new ImageIcon(tilePath + "smith.png");
        public static final ImageIcon PROPERTY_BATH_TILE = new ImageIcon(tilePath + "bath.png");
        public static final ImageIcon PROPERTY_BAKERY_TILE = new ImageIcon(tilePath + "bakerStore.png");
        public static final ImageIcon PROPERTY_BUTCHER_TILE = new ImageIcon(tilePath + "butcher.png");
        public static final ImageIcon SUNDAY_CHURCH_TILE = new ImageIcon(tilePath + "sundaychurch.png");
        public static final ImageIcon PROPERTY_WAREHOUSE_TILE = new ImageIcon(tilePath + "warehouse.png");
        public static final ImageIcon PROPERTY_ALCHEMY_TILE = new ImageIcon(tilePath + "alch.png");
        public static final ImageIcon PROPERTY_STABLE_TILE = new ImageIcon(tilePath + "stable.png");
        public static final ImageIcon PROPERTY_COBBLER_TILE = new ImageIcon(tilePath + "cobbler.png");
        public static final ImageIcon PROPERTY_GENERALSTORE_TILE = new ImageIcon(tilePath + "general.png");
        public static final ImageIcon PROPERTY_SILVERSMITH_TILE = new ImageIcon(tilePath + "silver.png");
        public static final ImageIcon PROPERTY_ARMORER_TILE = new ImageIcon(tilePath + "armor.png");
        public static final ImageIcon PROPERTY_TAILOR_TILE = new ImageIcon(tilePath + "tailor.png");
        public static final ImageIcon PROPERTY_WEAPONSMITH_TILE = new ImageIcon(tilePath + "weapon.png");
        public static final ImageIcon PROPERTY_CHURCH_TILE = new ImageIcon(tilePath + "churchp.png");
        public static final ImageIcon PROPERTY_CASTLE_TILE = new ImageIcon(tilePath + "castle.png");
        public static final ImageIcon TAVERN_TILE = new ImageIcon(tilePath + "tavern.png");
    }


    public class DiceImages {
        public static final ImageIcon WHITE_1 = resizeImg(new ImageIcon(dicePath + "faceValue1White.png"));
        public static final ImageIcon WHITE_2 = resizeImg(new ImageIcon(dicePath + "faceValue2White.png"));
        public static final ImageIcon WHITE_3 = resizeImg(new ImageIcon(dicePath + "faceValue3White.png"));
        public static final ImageIcon WHITE_4 = resizeImg(new ImageIcon(dicePath + "faceValue4White.png"));
        public static final ImageIcon WHITE_5 = resizeImg(new ImageIcon(dicePath + "faceValue5White.png"));
        public static final ImageIcon WHITE_6 = resizeImg(new ImageIcon(dicePath + "faceValue6White.png"));

        public static final ImageIcon RED_1 = resizeImg(new ImageIcon(dicePath + "faceValue1.png"));
        public static final ImageIcon RED_2 = resizeImg(new ImageIcon(dicePath + "faceValue2.png"));
        public static final ImageIcon RED_3 = resizeImg(new ImageIcon(dicePath + "faceValue3.png"));
        public static final ImageIcon RED_4 = resizeImg(new ImageIcon(dicePath + "faceValue4.png"));
        public static final ImageIcon RED_5 = resizeImg(new ImageIcon(dicePath + "faceValue5.png"));
        public static final ImageIcon RED_6 = resizeImg(new ImageIcon(dicePath + "faceValue6.png"));



        public static ImageIcon resizeImg(ImageIcon icon){
            int diceWidth = GameWindow.screenSize.width / 40;
            int diceHeight = GameWindow.screenSize.height / 20;

            Image resizedImage = icon.getImage().getScaledInstance(diceWidth, diceHeight, Image.SCALE_SMOOTH);
            return new ImageIcon(resizedImage);
        }
    }


    public class BoardImages {

        public static final ImageIcon BOARD_IMAGE = new ImageIcon(imagePath + "RiseBoard750.png");

        public static ImageIcon getPaperBackgroundImage() {
            BufferedImage tempImg = null;
            try {
                tempImg = ImageIO.read(new File(imagePath + "backpaper.jpg"));

            } catch (IOException e) {

                e.printStackTrace();
            }

            Image img = tempImg.getScaledInstance(600, 350, Image.SCALE_SMOOTH);

            return new ImageIcon(img);
        }

        public static ImageIcon getStartMenuImage(){
            BufferedImage tempImg = null;
            try {
                tempImg = ImageIO.read(new File(imagePath + "fancyRoll.jpg"));

            } catch (IOException e) {

                e.printStackTrace();
            }

            Image img = tempImg.getScaledInstance(900,860, Image.SCALE_SMOOTH);

            return new ImageIcon(img);
        }


        public static ImageIcon getBackgroundImage() {
            BufferedImage tempImg = null;
            try {
                tempImg = ImageIO.read(new File(imagePath + "back2jpg.jpg"));

            } catch (IOException e) {

                e.printStackTrace();
            }

            Image img = tempImg.getScaledInstance(GameWindow.screenWidth, GameWindow.screenHeight, Image.SCALE_SMOOTH);

            return new ImageIcon(img);
        }
    }


    public class AudioFiles{

        public static File bgMusic = new File(musicPath + "bgMusic.wav");
        public static File duraw = new File(musicPath + "duraw.wav");
        public static File victory = new File(musicPath + "victory.wav");
        public static File hips = new File(musicPath + "Hips Don't Lie.wav");
        public static File yeah = new File(musicPath + "Yeah!.wav");
    }
}
