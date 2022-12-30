package ui;

import enums.CellType;
import enums.CheckBoxType;
import tools.Image;

import javax.swing.*;
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.*;

public class MoneyBar extends JProgressBar {
    private static final int BAR_WIDTH = 400;
    private static final int BAR_HEIGHT = 75;
    private static final int BUDGED = 25000;
    private static final int ZONE_FEE = 500;
    private static final int ROAD_FEE = 200;
    private static final int PIPE_FEE = 200;
    private static final int WATER_FEE = 10;
    private static final int CABLE_FEE = 200;
    private static final int POWER_FEE = 10;
    private final int[] tempCounters;
    private int account;
    private int[] counters;

    public MoneyBar(int posX, int posY) {
        this.account = BUDGED;
        this.tempCounters = new int[CheckBoxType.values().length - 1];
        this.setBounds(
            posX - (BAR_WIDTH / 2),
            posY - (BAR_HEIGHT / 2),
            BAR_WIDTH,
            BAR_HEIGHT);
        this.setFocusable(false);
        this.setStringPainted(true);
        this.setOwnColors();
        this.setForeground(new Color(30, 230, 86));
        this.setBackground(Color.DARK_GRAY);
        this.setMinimum(0);
        this.setMaximum(100000);
        this.setFont(new Font("Arial", Font.BOLD, 42));
        this.setValue(BUDGED + BUDGED);
        this.setString(BUDGED + " $");
    }

    public void countCells(CellType[][] grid) {
        this.counters = new int[CheckBoxType.values().length - 1];
        for (CellType[] cellTypes : grid) {
            for (CellType cellType : cellTypes) {
                for (int i = 0; i < this.counters.length; i++) {
                    for (CellType cellType1 : CheckBoxType.values()[i].getCellTypes()) {
                        if (cellType.name().equals(cellType1.name())) {
                            this.counters[i]++;
                        }
                    }
                }
            }
        }
    }

    public void calculateCellFees() {
        int temp = 0;
        for (int i = 0; i < CheckBoxType.values().length - 1; i++) {
            switch (CheckBoxType.values()[i]) {
                case ZONE:
                    temp = this.counters[0];
                    this.account -= temp * ZONE_FEE;
                    break;
                case ROAD:
                    temp = this.counters[1];
                    this.account -= temp * ROAD_FEE;
                    break;
                case PIPE:
                    temp = this.counters[2];
                    this.account -= temp * PIPE_FEE;
                    break;
                case CABLE:
                    temp = this.counters[3];
                    this.account -= temp * CABLE_FEE;
                    break;
            }
        }
    }

    public void setNewMoneyText() {
        this.setMoneyText(this.account);
    }

    private void setMoneyText(int num) {
        this.setString(String.format("%d", num));
    }

    /**
     * https://stackoverflow.com/questions/3480125/setting-the-colors-of-a-jprogressbar-text
     */
    private void setOwnColors() {
        setUI(new BasicProgressBarUI() {
            protected Color getSelectionBackground() {
                return Color.LIGHT_GRAY;
            }

            protected Color getSelectionForeground() {
                return new Color(12, 102, 36);
            }
        });
    }

}
