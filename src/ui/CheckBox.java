package ui;

import enums.CellType;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import java.awt.Color;

/**
 * Trieda dedi a upravuje zakladnu triedu JCheckBox na specifickejsie
 * poziadavky.
 * !!! Dedenie mam naucene z internetu!!!
 */
public class CheckBox extends JCheckBox {

    /**
     * Konstruktor nastavuje JCheckBox podla poziadaviek.
     *
     * @param posX int
     * @param posY int
     * @param size rozmer check boxu
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


    /**
     * Metoda priradi ikony do sprav.
     *
     * @param icon         obycaja ikona
     * @param selectedIcon ikona ked je check box oznaceny
     */
    public void setLook(ImageIcon icon, ImageIcon selectedIcon) {
        this.setIcon(icon);
        if (this.isSelected()) {
            this.setSelectedIcon(selectedIcon);
        } else {
            // resetovat ikonu lebo si program pamatal predchadzajucu ikonu
            this.setSelectedIcon(null);
        }
    }

    /**
     * Overloadovanie pre lepsiu citatelnost pri pouzivani metody.
     *
     * @param cellType typ bunky podla ktoreho ma vyzerat check box
     */
    public void setLook(CellType cellType) {
        this.setIcon(cellType.getImageIcons()[0]);
        if (this.isSelected()) {
            this.setSelectedIcon(cellType.getImageIcons()[1]);
        } else {
            // resetovat ikonu lebo si program pamatal predchadzajucu ikonu
            this.setSelectedIcon(null);
        }
    }
}
