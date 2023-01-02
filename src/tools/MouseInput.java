package tools;

import enums.CellType;
import enums.CheckBoxType;
import enums.GridState;
import game.Account;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Trieda implementuje Listenerer a obashuje metody na overrideovanie.
 * !!! Napad na vznik triedy je z internetu. Link v dokumentacii !!!
 */
public class MouseInput implements MouseListener {
    private final Account account;
    private final int cellSize;
    private final int gamePanelWidth;
    private final int gamePanelHeight;
    private int posX;
    private int posY;
    private int posXReleased;
    private int posYReleased;
    private boolean isClicked;

    public MouseInput(
        Account account, int cellSize, int gamePanelWidth, int gamePanelHeight) {
        this.account = account;
        this.cellSize = cellSize;
        this.gamePanelWidth = gamePanelWidth;
        this.gamePanelHeight = gamePanelHeight;
    }

    public boolean isClicked() {
        return this.isClicked;
    }

    public void drag(Grid grid, CheckBoxType checkBoxType, int index) {
        if (this.posX > -1 && this.posY > -1) {
            if (this.posY >= this.posYReleased || this.posX >= this.posXReleased) {
                for (int i = this.posY; i >= this.posYReleased; i--) {
                    for (int j = this.posX; j >= this.posXReleased; j--) {
                        this.setCell(grid, i, j, checkBoxType, index);
                    }
                }
            }
            if (this.posY <= this.posYReleased || this.posX <= this.posXReleased) {
                for (int i = this.posY; i <= this.posYReleased; i++) {
                    for (int j = this.posX; j <= this.posXReleased; j++) {
                        this.setCell(grid, i, j, checkBoxType, index);
                    }
                }
            }
            if (this.posY <= this.posYReleased || this.posX >= this.posXReleased) {
                for (int i = this.posY; i <= this.posYReleased; i++) {
                    for (int j = this.posX; j >= this.posXReleased; j--) {
                        this.setCell(grid, i, j, checkBoxType, index);
                    }
                }
            }
            if (this.posY >= this.posYReleased || this.posX <= this.posXReleased) {
                for (int i = this.posY; i >= this.posYReleased; i--) {
                    for (int j = this.posX; j <= this.posXReleased; j++) {
                        this.setCell(grid, i, j, checkBoxType, index);
                    }
                }
            }
        }
    }

    public void resetPos() {
        this.posX = -1;
        this.posY = -1;
        this.posXReleased = -1;
        this.posYReleased = -1;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getX() < this.gamePanelWidth && e.getY() < this.gamePanelHeight) {
            this.posX = (e.getX() - (e.getX() % this.cellSize)) / this.cellSize;
            this.posY = (e.getY() - (e.getY() % this.cellSize)) / this.cellSize;
        }
        this.isClicked = false;
    }

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

    private void setCell(
        Grid grid, int i, int j, CheckBoxType checkBoxType, int index) {
        this.account.countCells();
        this.account.calculateFees();
        if (checkBoxType.getCellTypes()[index].name()
            .equals(CellType.EMPTY_CELL.name())
            || this.account.getAccount() > this.account.getMinimum()) {
            if (GridState.UNDERGROUND.isActive()) {
                grid.setUndergroundGridCell(
                    i, j, checkBoxType.getCellTypes()[index]);
            } else {
                grid.setOvergroundGridCell(
                    i, j, checkBoxType.getCellTypes()[index]);
            }
        }
    }
}
