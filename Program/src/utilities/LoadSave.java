package utilities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LoadSave {

    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static int width = (int)screenSize.getWidth();
    private static int height = (int)screenSize.getHeight();
    private static String resourceFolder = System.getProperty("user.dir") + "\\src\\resources\\";


    public static Image loadImage(String path) {
        BufferedImage tempImg = null;

        try {
            tempImg = ImageIO.read(new File("Program/images/back2jpg.jpg"));

        } catch (IOException e) {

            e.printStackTrace();
        }

        Image img = tempImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);


        return img;
    }
}
