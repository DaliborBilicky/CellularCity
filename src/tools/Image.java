package tools;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Image {
    /**
     * Metoda nacita obrazok zo zadanej cetsty k obrazku.
     * Ak sa obrazok nenajde vyhodi chybu a program bezi dalej.
     * !!! Metoda je zobrana z TvaryV4 trieda Obrazok. !!!
     */
    public BufferedImage getBufferedImage(String imagePath) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException exception) {
            JOptionPane.showMessageDialog(
                null,
                "File " + imagePath + " not found.");
        }
        return image;
    }

    /**
     * Metoda zoberie cestu k ikone a vrati ikonu.
     * !!! Aby sa rescalela ikona som nasiel na internete. !!!
     */
    public ImageIcon getImageIcon(String imagePath, int scale) {
        ImageIcon imageIcon = new ImageIcon(imagePath);
        java.awt.Image image = imageIcon.getImage();
        java.awt.Image newImage = image.getScaledInstance(
            scale, scale, java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newImage);
        return imageIcon;
    }
}
