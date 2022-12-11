package tools;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Image {
    /**
     * Metoda nacita obrazok zo zadanej cetsty k obrazku.
     * Ak sa obrazok nenajde vyhodi chybu a program bezi dalej.
     * !!! Metoda je zobrana z TvaryV4 trieda Obrazok. !!!
     */
    public BufferedImage getMyImage(String imagePath) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException exception) {
            JOptionPane.showMessageDialog(
                null,
                "Subor " + imagePath + " sa nenasiel.");
        }
        return image;
    }
}
