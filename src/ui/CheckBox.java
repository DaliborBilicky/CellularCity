package ui;

import javax.swing.*;
import java.awt.Color;

/**
 * Trieda nastavuje hodnoty pre check box ake potrebujem do programu.
 * !!! To, ze trieda dedi od JCheckBox mam naucene z internetu. !!!
 */
public class CheckBox extends JCheckBox {
    private static final int C_B_WIDTH = 125;
    private static final int C_B_HEIGHT = 125;

    /**
     * V kostruktore okrem inicializacie sa nastavuju hodnoty pre check box.
     */
    public CheckBox(int posX, int posY) {
        this.setBounds(
            posX - (C_B_WIDTH / 2),
            posY - (C_B_HEIGHT / 2),
            C_B_WIDTH,
            C_B_HEIGHT);
        this.setFocusable(false);
        this.setBackground(Color.LIGHT_GRAY);
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setBorderPainted(true);
    }

    public void setLook(ImageIcon imageIcon, ImageIcon selectedImageIcon) {
        this.setIcon(imageIcon);
        if (this.isSelected()) {
            this.setSelectedIcon(selectedImageIcon);
        } else {
            // resetovat ikonu lebo si program pamatal predchadzajucu ikonu
            this.setSelectedIcon(null);
        }
    }
}
