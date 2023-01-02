package ui;

import enums.CellType;

import javax.swing.*;
import java.awt.Color;

/**
 * Trieda nastavuje hodnoty pre check box ake potrebujem do programu.
 * !!! To, ze trieda dedi od JCheckBox mam naucene z internetu. !!!
 */
public class CheckBox extends JCheckBox {
    /**
     * V kostruktore okrem inicializacie sa nastavuju hodnoty pre check box.
     */
    public CheckBox(int posX, int posY, int size) {
        this.setBounds(
            posX - (size / 2),
            posY,
            size,
            size);
        this.setFocusable(false);
        this.setBackground(Color.LIGHT_GRAY);
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setBorderPainted(true);
    }

    public void setLook(CellType cellType) {
        this.setIcon(cellType.getImageIcons()[0]);
        if (this.isSelected()) {
            this.setSelectedIcon(cellType.getImageIcons()[1]);
        } else {
            // resetovat ikonu lebo si program pamatal predchadzajucu ikonu
            this.setSelectedIcon(null);
        }
    }

    public void setLook(ImageIcon icon, ImageIcon selectedIcon) {
        this.setIcon(icon);
        if (this.isSelected()) {
            this.setSelectedIcon(selectedIcon);
        } else {
            // resetovat ikonu lebo si program pamatal predchadzajucu ikonu
            this.setSelectedIcon(null);
        }
    }
}
