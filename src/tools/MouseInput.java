package tools;

import enums.CellType;
import enums.CheckBoxType;
import enums.GridState;
import enums.Warning;
import game.Account;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Trieda implementuje Listenerer a obashuje metody na overrideovanie.
 * !!! Napad na vznik triedy je z internetu. !!!
 * https://www.youtube.com/watch?v=6Tj6XYGWfko
 */
public class MouseInput implements MouseListener {
    private final Grid grid;
    private final Account account;
    private int cellSize;
    private int gamePanelWidth;
    private int gamePanelHeight;
    private int posX;
    private int posY;
    private int posXReleased;
    private int posYReleased;
    private boolean isClicked;

    /**
     * @param grid    trieda
     * @param account trieda
     */
    public MouseInput(Grid grid, Account account) {
        this.grid = grid;
        this.account = account;
    }

    /**
     * Nastavovanie hodnout v ktorych pracuje trieda.
     *
     * @param cellSize        velkost bunky
     * @param gamePanelWidth  sirka hracieho platna
     * @param gamePanelHeight vyska hracieho platna
     */
    public void setDimensions(
        int gamePanelWidth, int gamePanelHeight, int cellSize) {
        this.gamePanelWidth = gamePanelWidth;
        this.gamePanelHeight = gamePanelHeight;
        this.cellSize = cellSize;
    }

    /**
     * @return hodnuta ci sa kliklo s misou
     */
    public boolean isClicked() {
        return this.isClicked;
    }

    /**
     * Metoda podla podmienok vyplni oznacenu cast hracieho pola vybranou
     * bunkou.
     *
     * @param checkBoxType enum
     * @param index        hodnota na ktorom check boxe sa nachadzame
     */
    public void drag(CheckBoxType checkBoxType, int index) {
        if (this.posX > -1 && this.posY > -1) {

            /*
            Podmienka vyplna smerom na severozapad
             */
            if (this.posY >= this.posYReleased || this.posX >= this.posXReleased) {
                for (int i = this.posY; i >= this.posYReleased; i--) {
                    for (int j = this.posX; j >= this.posXReleased; j--) {
                        this.setCell(i, j, checkBoxType, index);
                    }
                }
            }

            /*
            Podmienka vyplna smerom na juhovychod
             */
            if (this.posY <= this.posYReleased || this.posX <= this.posXReleased) {
                for (int i = this.posY; i <= this.posYReleased; i++) {
                    for (int j = this.posX; j <= this.posXReleased; j++) {
                        this.setCell(i, j, checkBoxType, index);
                    }
                }
            }

            /*
            Podmienka vyplna smerom na juhozapad
             */
            if (this.posY <= this.posYReleased || this.posX >= this.posXReleased) {
                for (int i = this.posY; i <= this.posYReleased; i++) {
                    for (int j = this.posX; j >= this.posXReleased; j--) {
                        this.setCell(i, j, checkBoxType, index);
                    }
                }
            }

            /*
            Podmienka vyplna smerom na severovychod
             */
            if (this.posY >= this.posYReleased || this.posX <= this.posXReleased) {
                for (int i = this.posY; i >= this.posYReleased; i--) {
                    for (int j = this.posX; j <= this.posXReleased; j++) {
                        this.setCell(i, j, checkBoxType, index);
                    }
                }
            }
        }
    }

    /**
     * Vynulovanie pozicii.
     */
    public void resetPos() {
        this.posX = -1;
        this.posY = -1;
        this.posXReleased = -1;
        this.posYReleased = -1;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    /**
     * Metoda nastavje prve pozicie na tie kde bola mys stlacena.
     *
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getX() < this.gamePanelWidth && e.getY() < this.gamePanelHeight) {
            this.posX = (e.getX() - (e.getX() % this.cellSize)) / this.cellSize;
            this.posY = (e.getY() - (e.getY() % this.cellSize)) / this.cellSize;
        }
        this.isClicked = false;
    }

    /**
     * Metoda nastavuje druhe pozicie na tie kde bola mys pustena.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getX() < this.gamePanelWidth && e.getY() < this.gamePanelHeight) {
            this.posXReleased = (
                e.getX() - (e.getX() % this.cellSize)) / this.cellSize;
            this.posYReleased = (
                e.getY() - (e.getY() % this.cellSize)) / this.cellSize;
        }
        this.isClicked = true;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * Oddelena metoda pre lepsiu citatelnost.
     * Prideluje na vybrane pozicie typ bunky. Taktiez sa ale kontroluje ci
     * sme nepresiahly rozpocet.
     *
     * @param i            i-ty riadok
     * @param j            j-ty stlpec
     * @param checkBoxType typ check boxu
     * @param index        pozicia na ktorej je pozadovany typ bunky
     */
    private void setCell(int i, int j, CheckBoxType checkBoxType, int index) {
        this.account.countCells();
        this.account.calculateFees();

        /*
        Podmienka ignoruje ak sa uzivatel snazi pridat do hracieho pola
        prazdnu bunku.
         */
        if (checkBoxType.getCellTypes()[index].name()
            .equals(CellType.EMPTY_CELL.name())) {
            if (GridState.UNDERGROUND.isActive()) {
                this.grid.setUndergroundGridCell(
                    i, j, checkBoxType.getCellTypes()[index]);
                this.grid.setZoneConnectionToRoad(i, j, Warning.EMPTY);
            } else {
                this.grid.setOvergroundGridCell(
                    i, j, checkBoxType.getCellTypes()[index]);
                this.grid.setZoneConnectionToRoad(i, j, Warning.EMPTY);
            }
        } else {
            if (this.account.getAccount() > this.account.getMinimum()) {
                if (GridState.UNDERGROUND.isActive()) {
                    this.grid.setUndergroundGridCell(
                        i, j, checkBoxType.getCellTypes()[index]);
                } else {
                    this.grid.setOvergroundGridCell(
                        i, j, checkBoxType.getCellTypes()[index]);
                    for (int num = 0; num < CheckBoxType.ZONE.getCellTypes().length; num++) {
                        if (checkBoxType.getCellTypes()[num].name()
                            .equals(CheckBoxType.ZONE.getCellTypes()[num].name())) {
                            this.grid.setZoneConnectionToRoad(i, j, Warning.NO_ROAD);
                        }
                    }
                }
            }
        }
    }
}
