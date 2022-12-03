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
    public void setSwitchLook() {
        switch (this.cellType) {
            case COMMERCIAL:
                this.setText(CellType.COMMERCIAL.getCellName());
                this.setIcon(this.getImageIcon(
                    CellType.COMMERCIAL.getImagePath()[0]));
                if (this.isSelected()) {
                    this.setSelectedIcon(this.getImageIcon(
                        CellType.COMMERCIAL.getImagePath()[1]));
                }
                break;

            case INDUSTRIAL:
                this.setText(CellType.INDUSTRIAL.getCellName());
                this.setIcon(this.getImageIcon(
                    CellType.INDUSTRIAL.getImagePath()[0]));
                if (this.isSelected()) {
                    this.setSelectedIcon(this.getImageIcon(
                        CellType.INDUSTRIAL.getImagePath()[1]));
                }
                break;

            case RESIDENTIAL:
                this.setText(CellType.RESIDENTIAL.getCellName());
                this.setIcon(this.getImageIcon(
                    CellType.RESIDENTIAL.getImagePath()[0]));
                if (this.isSelected()) {
                    this.setSelectedIcon(this.getImageIcon(
                        CellType.RESIDENTIAL.getImagePath()[1]));
                }
                break;

            case ROAD:
                this.setText(CellType.ROAD.getCellName());
                this.setIcon(this.getImageIcon(
                    CellType.ROAD.getImagePath()[0]));
                if (this.isSelected()) {
                    this.setSelectedIcon(this.getImageIcon(
                        CellType.ROAD.getImagePath()[1]));
                }
                break;

            case PIPE:
                this.setText(CellType.PIPE.getCellName());
                this.setIcon(this.getImageIcon(
                    CellType.PIPE.getImagePath()[0]));
                if (this.isSelected()) {
                    this.setSelectedIcon(this.getImageIcon(
                        CellType.PIPE.getImagePath()[1]));
                }
                break;

            case POWER_LINE:
                this.setText(CellType.POWER_LINE.getCellName());
                this.setIcon(this.getImageIcon(
                    CellType.POWER_LINE.getImagePath()[0]));
                if (this.isSelected()) {
                    this.setSelectedIcon(this.getImageIcon(
                        CellType.POWER_LINE.getImagePath()[1]));
                }
                break;
            case EMPTY_CELL:
                this.setText(CellType.EMPTY_CELL.getCellName());
                this.setIcon(this.getImageIcon(
                    CellType.EMPTY_CELL.getImagePath()[1]));
                if (this.isSelected()) {
                    this.setSelectedIcon(this.getImageIcon(
                        CellType.EMPTY_CELL.getImagePath()[2]));
                }
                break;
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
