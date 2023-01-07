package game;

import enums.CellType;
import enums.CheckBoxType;
import enums.Warning;
import tools.GridHandler;

/**
 * Trieda ma na starosti pocitat prijem a vydaje mesta.
 */
public class Account {
    /**
     * GOAL uchovava cielovy rozpocet.
     * BUDGET je rozpocet s ktorym hrac zacina.
     * MINIMUM je najnizsie mozna hodnota ku ktorej sa da dostat.
     */
    private static final int GOAL = 100000;
    private static final int BUDGED = 40000;
    private static final int MINIMUM = -5000;
    /**
     * Konstanty uchovavju hodnoty podla ktory trieda rata prijem a vydaje
     * mesta.
     */
    private static final int[] ZONES_INCOME = new int[]{10, 12, 15};
    private static final int RESOURCES_EXPENSES = 20;
    private static final int[] CELL_FEES = new int[]{75, 115, 375};
    private final GridHandler gridHandler;
    private int[] zoneCounters;
    private int[] cellCounters;
    private int[] tempCellCounters;
    private int fees;
    private int income;

    /**
     * @param gridHandler kostruktor si pyta gridHandler aby s nim mohla
     *                    trieda pracovat.
     */
    public Account(GridHandler gridHandler) {
        this.gridHandler = gridHandler;
        this.tempCellCounters = new int[CheckBoxType.values().length - 1];
    }

    /**
     * @return konstana MINIMUM
     */
    public int getMinimum() {
        return MINIMUM;
    }

    /**
     * @return konstanta GOAL
     */
    public int getGoal() {
        return GOAL;
    }

    /**
     * Metoda vracia hodnotu uctu. Vypocita ju tak, ze nastavi ucet na
     * hodnotu rozpoctu a potom pripocita rozdiel prijmu a vydajov.
     *
     * @return account
     */
    public int getAccount() {
        int account = BUDGED;
        account += this.income - this.fees;
        return account;
    }

    /**
     * Metoda iteruje cez grid a nascitava pocet obsadenych buniek.
     */
    public void countCells() {
        this.cellCounters = new int[CheckBoxType.values().length - 1];
        this.zoneCounters = new int[CheckBoxType.ZONE.getCellTypes().length];
        for (int i = 0; i < this.gridHandler.getOvergroundGrid().length; i++) {
            for (int j = 0; j < this.gridHandler.getOvergroundGrid()[i].length; j++) {
                this.addToSpecificCounter(i, j);
            }
        }
    }

    /**
     * Metoda vyrata vydaje ak sa obsah pocitadla zmeni voci docasnemu
     * pocitadlu. Prejde cez list pocitadla a pripocitava do premenej
     * vydaje.
     */
    public void calculateFees() {
        if (this.cellCounters != this.tempCellCounters) {
            this.fees = 0;
            this.tempCellCounters = this.cellCounters;
            for (int i = 0; i < this.cellCounters.length; i++) {
                this.fees += this.cellCounters[i] * CELL_FEES[i];
            }
        }
    }

    /**
     * Metoda vypocita prijem pre kazdy druh zony a potom od prijmu odcita
     * vydaje na energie.
     */
    public void calculateIncome() {
        for (int i = 0; i < this.zoneCounters.length; i++) {
            this.income += (this.zoneCounters[i] * ZONES_INCOME[i]);
            this.zoneCounters[i] = 0;
        }
        this.income -= (this.cellCounters[2] * RESOURCES_EXPENSES);
    }

    /**
     * Oddelena metoda aby sa lepsie cital kod v countCells metode. For
     * cyklus iteruje pocitadlo a druhy iteruje cez kategoriu buniek podla
     * hodnoty preveho cyklu. V pripade splnenej podmienky sa pocitadlo
     * zvacsi o jedna.
     *
     * @param i i-ty riadok
     * @param j j-ty stlpec
     */
    private void addToSpecificCounter(int i, int j) {
        for (int g = 0; g < this.cellCounters.length; g++) {
            for (CellType cellType : CheckBoxType.values()[g].getCellTypes()) {
                if (this.gridHandler.getOvergroundGrid()[i][j].name()
                    .equals(cellType.name())
                    || this.gridHandler.getUndergroundGrid()[i][j].name()
                    .equals(cellType.name())) {
                    this.cellCounters[g]++;
                }
            }

            if (this.gridHandler.getOvergroundGrid()[i][j].name()
                .equals(CheckBoxType.ZONE.getCellTypes()[g].name())
                && this.gridHandler.getZonesConnection()[i][j].name()
                .equals(Warning.CONNECTED.name())) {
                this.zoneCounters[g]++;
            }
        }
    }
}
