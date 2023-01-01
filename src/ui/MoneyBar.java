package ui;

import enums.CellType;
import enums.CheckBoxType;

import javax.swing.*;
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.*;

public class MoneyBar extends JProgressBar {
    private static final int BAR_WIDTH = 400;
    private static final int BAR_HEIGHT = 75;
    private static final int BUDGED = 25000;
    private static final int GOAL = 100000;
    private static final int[] CELL_FEES = new int[]{100, 150, 50, 30};
    private static final int RESOURCES_EXPENSES = 40;
    private static final int ZONES_INCOME = 6;
    private final int[] account;
    private int[] counters;
    private int[] tempCounters;
    private int zoneLimit;

    public MoneyBar(int posX, int posY) {
        this.tempCounters = new int[CheckBoxType.values().length - 1];
        this.account = new int[2];
        this.zoneLimit = 50;

        this.setBounds(
            posX - (BAR_WIDTH / 2),
            posY - (BAR_HEIGHT / 2),
            BAR_WIDTH,
            BAR_HEIGHT);
        this.setFocusable(false);
        this.setStringPainted(true);
        this.setOwnColors();
        this.setBackground(Color.DARK_GRAY);
        this.setMinimum(-GOAL);
        this.setMaximum(GOAL);
        this.setFont(new Font("Arial", Font.BOLD, 42));
    }

    public int getAccount() {
        return this.account[0] + this.account[1];
    }

    public void setBar() {
        int temp = this.account[0] + this.account[1];
        if (temp < 0) {
            this.setForeground(new Color(237, 28, 36));
        } else {
            this.setForeground(new Color(30, 230, 86));
        }
        this.setValue(temp);
        this.setString(temp + " $");
    }

    public void countCells(CellType[][] grid, CellType[][] grid1) {
        this.counters = new int[CheckBoxType.values().length - 1];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                for (int g = 0; g < this.counters.length; g++) {
                    for (CellType cellType : CheckBoxType.values()[g].getCellTypes()) {
                        if (grid[i][j].name().equals(cellType.name())
                            || grid1[i][j].name().equals(cellType.name())) {
                            this.counters[g]++;

                        }
                    }
                }
            }
        }
    }

    public void calculateFees() {
        if (this.counters != this.tempCounters) {
            this.account[0] = BUDGED;
            this.tempCounters = this.counters;
            for (int i = 0; i < this.counters.length; i++) {
                this.account[0] -= this.counters[i] * CELL_FEES[i];
            }
        }
    }

    public void calculateIncome() {
        if (this.counters[0] < this.zoneLimit) {
            this.account[1] += (this.counters[0] * ZONES_INCOME) -
                (RESOURCES_EXPENSES * (this.zoneLimit / 100));
        } else {
            this.zoneLimit += 100;
        }
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
                return Color.BLACK;
            }
        });
    }
}
// new Color(12, 102, 36);
