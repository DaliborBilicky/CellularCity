package game;

import enums.CellType;
import enums.CheckBoxType;
import tools.Grid;

/**
 * Trieda ma na starosti pocitat prijem a vydaje.
 */
public class Account {
    /**
     * Konstanta GOAL uchovava cielovy rozpocet. BUDGET je rozpocet s ktorym
     * hrac zacina. MINIMUM je najnizsie mozna hodnota ku ktorej sa da dostat.
     */
    private static final int GOAL = 100000;
    private static final int BUDGED = 25000;
    private static final int MINIMUM = -5000;
    /**
     * Konstanty uchovavju hodnoty podla ktory trieda rata prijem a vydaje
     * mesta.
     */
    private static final int ZONES_INCOME = 6;
    private static final int RESOURCES_EXPENSES = 40;
    private static final int[] CELL_FEES = new int[]{100, 150, 50, 30};
    private final Grid grid;
    private int[] counters;
    private int[] tempCounters;
    private int zoneLimit;
    private int fees;
    private int income;

    /**
     * @param grid kostruktor si pyta grid aby s nim mohla trieda pracovat.
     */
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
        for (int i = 0; i < this.grid.getOvergroundGrid().length; i++) {
            for (int j = 0; j < this.grid.getOvergroundGrid()[i].length; j++) {
                this.addToSpecificCounter(i, j);
            }
        }
    }

    /**
     * Metoda vyrata vydaje ak sa obsah pocitadla zmeni voci docasnemu
     * pocitadlu. Prechadza cez list pocitadla a pripocitava do premenej
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
        /*
        Podmienka zvacsi limit ak pocet buniek zony v pocitadle prekroci limit.
         */
        if (this.counters[0] > this.zoneLimit) {
            this.zoneLimit += 50;
        }
    }

    /**
     * Metoda rata prijem. Za kazdu aktivnu bunku zony sa prirata konstanta a
     * od toho sa odcitaju vydaje na energie ktore sa linearne zvacsuju podla
     * limitu buniek.
     */
    public void calculateIncome() {
        this.income += (this.counters[0] * ZONES_INCOME) -
            (RESOURCES_EXPENSES * (this.zoneLimit / 50));
    }

    /**
     * Oddelena metoda aby sa lepsie cital kod v countCells metode. For
     * cyklus iteruje pocitadlo a druhy iteruje cez kategoriu buniek podla
     * hodnoty preveho cyklu. Podmienka kontroluje ci bunka z gridu sa rovna
     * bunke z for cyklu a podla toho zvacsi pocitadlo o jedna.
     *
     * @param i i-ty riadok
     * @param j j-ty stlpec
     */
    private void addToSpecificCounter(int i, int j) {
        for (int g = 0; g < this.counters.length; g++) {
            for (CellType cellType : CheckBoxType.values()[g].getCellTypes()) {
                if (this.grid.getOvergroundGrid()[i][j].name().equals(cellType.name())
                    || this.grid.getUndergroundGrid()[i][j].name().equals(cellType.name())) {
                    this.counters[g]++;
                }
            }
        }
    }
}
