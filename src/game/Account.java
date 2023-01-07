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
     * Konstanta GOAL uchovava cielovy rozpocet. BUDGET je rozpocet s ktorym
     * hrac zacina. MINIMUM je najnizsie mozna hodnota ku ktorej sa da dostat.
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
    private int[] counters;
    private int[] tempCounters;
    private int fees;
    private int income;

    /**
     * @param gridHandler kostruktor si pyta gridHandler aby s nim mohla
     *                    trieda pracovat.
     */
    public Account(GridHandler gridHandler) {
        this.gridHandler = gridHandler;
        this.tempCounters = new int[CheckBoxType.values().length - 1];
    }

    public int getMinimum() {
        return MINIMUM;
    }

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
        this.counters = new int[CheckBoxType.values().length - 1];
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
        if (this.counters != this.tempCounters) {
            this.fees = 0;
            this.tempCounters = this.counters;
            for (int i = 0; i < this.counters.length; i++) {
                this.fees += this.counters[i] * CELL_FEES[i];
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
        this.income -= (this.counters[2] * RESOURCES_EXPENSES);
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
        for (int g = 0; g < this.counters.length; g++) {
            for (CellType cellType : CheckBoxType.values()[g].getCellTypes()) {
                if (this.gridHandler.getOvergroundGrid()[i][j].name()
                    .equals(cellType.name())
                    || this.gridHandler.getUndergroundGrid()[i][j].name()
                    .equals(cellType.name())) {
                    this.counters[g]++;
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
