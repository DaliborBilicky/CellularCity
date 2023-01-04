package tools;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
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
     * https://stackoverflow.com/questions/6714045/how-to-resize-jlabel-imageicon
     *
     * @param imagePath cesta k obrazku
     * @param scale     hodnota na ktoru sa nastavi velkost icony
     * @return icona nastavena na spravnu velkost.
     */
    public ImageIcon getImageIcon(String imagePath, int scale) {
        ImageIcon imageIcon = new ImageIcon(imagePath);
        java.awt.Image image = imageIcon.getImage();
        java.awt.Image newImage = image.getScaledInstance(
            scale, scale, java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newImage);
        return imageIcon;
    }

    /**
     * Overloadovanie pretoze nie vzdy je potrebne nastavit velkost ikony.
     *
     * @param imagePath cesta k obrazku
     * @return ikona s pozadovanou velkostou
     */
    public ImageIcon getImageIcon(String imagePath) {
        return this.getImageIcon(imagePath, 100);
    }
}
