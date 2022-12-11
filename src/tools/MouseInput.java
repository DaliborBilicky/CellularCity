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
    private int posXReleased;
    private int posYReleased;

    private boolean wasClicked;

    public MouseInput(int cellSize, int gamePanelWidth, int gamePanelHeight) {
        this.cellSize = cellSize;
        this.gamePanelWidth = gamePanelWidth;
        this.gamePanelHeight = gamePanelHeight;
    }

    public boolean getWasClicked() {
        return this.wasClicked;
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

    public int getPosXReleased() {
        return this.posXReleased;
    }

    public void setPosXReleased(int x) {
        this.posXReleased = x;
    }

    public int getPosYReleased() {
        return this.posYReleased;
    }

    public void setPosYReleased(int y) {
        this.posYReleased = y;
    }

    /**
     * Metoda nastavje x a y po kliknuti ak je splnena podmienka.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getX() < this.gamePanelWidth && e.getY() < this.gamePanelHeight) {
            this.posX = (e.getX() - (e.getX() % this.cellSize)) / this.cellSize;
            this.posY = (e.getY() - (e.getY() % this.cellSize)) / this.cellSize;
        }
        this.wasClicked = false;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getX() < this.gamePanelWidth && e.getY() < this.gamePanelHeight) {
            this.posXReleased = (e.getX() - (e.getX() % this.cellSize)) / this.cellSize;
            this.posYReleased = (e.getY() - (e.getY() % this.cellSize)) / this.cellSize;
        }
        this.wasClicked = true;
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
