package game;

import enums.CellType;
import enums.CheckBoxType;
import tools.Grid;

public class Account {
    private static final int GOAL = 100000;
    private static final int BUDGED = 25000;
    private static final int MINIMUM = -5000;
    private static final int ZONES_INCOME = 6;
    private static final int RESOURCES_EXPENSES = 40;
    private static final int[] CELL_FEES = new int[]{100, 150, 50, 30};
    private final Grid grid;
    private int[] counters;
    private int[] tempCounters;
    private int zoneLimit;
    private int fees;
    private int income;

    public Account(Grid grid) {
        this.grid = grid;
        this.tempCounters = new int[CheckBoxType.values().length - 1];
        this.zoneLimit = 50;
    }

    public int getMinimum() {
        return MINIMUM;
    }

    public int getGoal() {
        return GOAL;
    }

    public int getAccount() {
        int account = BUDGED;
        account += this.income - this.fees;
        return account;
    }

    public void countCells() {
        this.counters = new int[CheckBoxType.values().length - 1];
        for (int i = 0; i < grid.getOvergroundGrid().length; i++) {
            for (int j = 0; j < grid.getOvergroundGrid()[i].length; j++) {
                this.addToSpecificCounter(i, j);
            }
        }
    }

    public void calculateFees() {
        if (this.counters != this.tempCounters) {
            this.fees = 0;
            this.tempCounters = this.counters;
            for (int i = 0; i < this.counters.length; i++) {
                this.fees += this.counters[i] * CELL_FEES[i];
            }
        }
        if (this.counters[0] > this.zoneLimit) {
            this.zoneLimit += 50;
        }
    }

    public void calculateIncome() {
        this.income += (this.counters[0] * ZONES_INCOME) -
            (RESOURCES_EXPENSES * (this.zoneLimit / 50));
    }

    private void addToSpecificCounter(int i, int j) {
        for (int g = 0; g < this.counters.length; g++) {
            for (CellType cellType : CheckBoxType.values()[g].getCellTypes()) {
                if (grid.getOvergroundGrid()[i][j].name().equals(cellType.name())
                    || grid.getUndergroundGrid()[i][j].name().equals(cellType.name())) {
                    this.counters[g]++;
                }
            }
        }
    }
}
