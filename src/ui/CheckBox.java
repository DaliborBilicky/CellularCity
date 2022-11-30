package ui;

import tools.CellType;

import javax.swing.*;
import java.awt.*;

public class CheckBox extends JCheckBox {
    private static final int C_B_WIDTH = 125;
    private static final int C_B_HEIGHT = 50;

    private final CellType cellType;

    public CheckBox(int posX, int posY, CellType cellType) {
        this.cellType = cellType;
        this.setBounds(posX, posY, C_B_WIDTH, C_B_HEIGHT);
    }

    public void setSwitchLook() {
        switch (this.cellType) {
            case COMMERCIAL:
                this.setText(CellType.COMMERCIAL.getCellName());
                this.setIcon(this.getImageIcon(CellType.COMMERCIAL.getImagePath()));
                if (this.isSelected()) {
                    this.setSelectedIcon(this.getImageIcon(CellType.COMMERCIAL.getImagePath()));
                }
                break;

            case INDUSTRIAL:
                this.setText(CellType.INDUSTRIAL.getCellName());
                this.setIcon(this.getImageIcon(CellType.INDUSTRIAL.getImagePath()));
                if (this.isSelected()) {
                    this.setSelectedIcon(this.getImageIcon(CellType.INDUSTRIAL.getImagePath()));
                }
                break;

            case RESIDENTIAL:
                this.setText(CellType.RESIDENTIAL.getCellName());
                this.setIcon(this.getImageIcon(CellType.RESIDENTIAL.getImagePath()));
                if (this.isSelected()) {
                    this.setSelectedIcon(this.getImageIcon(CellType.RESIDENTIAL.getImagePath()));
                }
                break;

            case ROAD:
                this.setText(CellType.ROAD.getCellName());
                this.setIcon(this.getImageIcon(CellType.ROAD.getImagePath()));
                if (this.isSelected()) {
                    this.setSelectedIcon(this.getImageIcon(CellType.ROAD.getImagePath()));
                }
                break;

            case WATER_PIPE:
                this.setText(CellType.WATER_PIPE.getCellName());
                this.setIcon(this.getImageIcon(CellType.WATER_PIPE.getImagePath()));
                if (this.isSelected()) {
                    this.setSelectedIcon(this.getImageIcon(CellType.WATER_PIPE.getImagePath()));
                }
                break;

            case POWER_LINE:
                this.setText(CellType.POWER_LINE.getCellName());
                this.setIcon(this.getImageIcon(CellType.POWER_LINE.getImagePath()));
                if (this.isSelected()) {
                    this.setSelectedIcon(this.getImageIcon(CellType.POWER_LINE.getImagePath()));
                }
                break;
        }
    }


    public boolean wasSelected() {
        return this.isSelected();
    }

    private ImageIcon getImageIcon(String iconPath) {
        ImageIcon icon = new ImageIcon(iconPath);
        Image image = icon.getImage();
        Image newimg = image.getScaledInstance(
                25, 25, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimg);
        return icon;
    }

}
