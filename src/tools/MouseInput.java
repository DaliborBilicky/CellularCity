package tools;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Trieda implementuje Listenerer a obashuje metody na overrideovanie.
 * !!! Napad na vznik triedy je z internetu. Link v dokumentacii !!!
 */
public class MouseInput implements MouseListener, MouseMotionListener {
    private final int cellSize;
    private final int gamePanelWidth;
    private final int gamePanelHeight;
    private int posX;
    private int posY;

    public MouseInput(int cellSize, int gamePanelWidth, int gamePanelHeight) {
        this.cellSize = cellSize;
        this.gamePanelWidth = gamePanelWidth;
        this.gamePanelHeight = gamePanelHeight;
    }

    public int getPosX() {
        return this.posX;
    }

    public void setPosX(int x) {
        this.posX = x;
    }

    public int getPosY() {
        return this.posY;
    }

    public void setPosY(int y) {
        this.posY = y;
    }

    /**
     * Metoda nastavje x a y po kliknuti ak je splnena podmienka.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getX() < this.gamePanelWidth && e.getY() < this.gamePanelHeight) {
            this.posX = (e.getX() - (e.getX() % this.cellSize)) / this.cellSize;
            this.posY = (e.getY() - (e.getY() % this.cellSize)) / this.cellSize;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
