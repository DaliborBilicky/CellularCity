package ui;

import tools.CellType;

import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;

/**
 * Trieda nastavuje hodnoty pre check box ake potrebujem do programu.
 * !!! To, ze trieda dedi od JCheckBox mam naucene z internetu. !!!
 */
public class CheckBox extends JCheckBox {
    private static final int C_B_WIDTH = 200;
    private static final int C_B_HEIGHT = 75;
    private final CellType cellType;

    /**
     * V kostruktore okrem inicializacie sa nastavuju hodnoty pre check box.
     */
    public CheckBox(int posX, int posY, CellType cellType) {
        this.cellType = cellType;
        this.setBounds(posX - (C_B_WIDTH / 2), posY, C_B_WIDTH, C_B_HEIGHT);
        this.setFont(new Font("Arial", Font.BOLD, 20));
        this.setFocusable(false);
        this.setIconTextGap(15);
        this.setBackground(Color.LIGHT_GRAY);
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setBorderPainted(true);
    }

    /**
     * Metoda nastavi check box podla toho o aky typ (enum) ide.
     */
    public void setLook() {
        this.setText(this.cellType.getCellName());
        this.setIcon(this.getImageIcon(
            this.cellType.getImagePaths()[0]));
        if (this.isSelected()) {
            this.setSelectedIcon(this.getImageIcon(this.cellType.getImagePaths()[1]));
        }
    }

    /**
     * Metoda vracia ci check box bol oznaceny.
     */
    public boolean wasSelected() {
        return this.isSelected();
    }

    /**
     * Metoda zoberie cestu k ikone a vrati ikonu.
     */
    private ImageIcon getImageIcon(String iconPath) {
        return new ImageIcon(iconPath);
    }

}
