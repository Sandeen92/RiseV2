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
        public static final ImageIcon MAGENTA = new ImageIcon(tokenPath + "playerMagenta.jpg");
        public static final ImageIcon PURPLE = new ImageIcon(tokenPath + "playerPurple.jpg");
        public static final ImageIcon ORANGE = new ImageIcon(tokenPath + "playerOrange.jpg");
        public static final ImageIcon RED_PAWN = resizeImage(new ImageIcon(tokenPath + "Red_pawn.svg.png"));
        public static final ImageIcon YELLOW_PAWN = resizeImage(new ImageIcon(tokenPath + "Yellow_pawn.svg.png"));
        public static final ImageIcon GREEN_PAWN = resizeImage(new ImageIcon(tokenPath + "Green_pawn.svg.png"));
        public static final ImageIcon CYAN_PAWN = resizeImage(new ImageIcon(tokenPath + "Blue_pawn.svg.png"));


        private static ImageIcon resizeImage(ImageIcon originalIcon) {
            Image originalImage = originalIcon.getImage();
            int width = 40;
            int height = 40;
            Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(resizedImage);
        }
    }



    public class DiceImages {
        public static final ImageIcon WHITE_1 = new ImageIcon(dicePath + "faceValue1White.png");
        public static final ImageIcon WHITE_2 = new ImageIcon(dicePath + "faceValue2White.png");
        public static final ImageIcon WHITE_3 = new ImageIcon(dicePath + "faceValue3White.png");
        public static final ImageIcon WHITE_4 = new ImageIcon(dicePath + "faceValue4White.png");
        public static final ImageIcon WHITE_5 = new ImageIcon(dicePath + "faceValue5White.png");
        public static final ImageIcon WHITE_6 = new ImageIcon(dicePath + "faceValue6White.png");

        public static final ImageIcon RED_1 = new ImageIcon(dicePath + "faceValue1.png");
        public static final ImageIcon RED_2 = new ImageIcon(dicePath + "faceValue2.png");
        public static final ImageIcon RED_3 = new ImageIcon(dicePath + "faceValue3.png");
        public static final ImageIcon RED_4 = new ImageIcon(dicePath + "faceValue4.png");
        public static final ImageIcon RED_5 = new ImageIcon(dicePath + "faceValue5.png");
        public static final ImageIcon RED_6 = new ImageIcon(dicePath + "faceValue6.png");
    }


    public class BoardImages {

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

        public static File musicFile = new File(musicPath + "bgMusic.wav");
    }
}
