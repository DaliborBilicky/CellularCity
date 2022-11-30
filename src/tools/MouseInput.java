package tools;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInput implements MouseListener, MouseMotionListener {
    private final int cellSize;
    private final int gamePanelWidth;
    private int posX;
    private int posY;

    public MouseInput(int cellSize, int gamePanelWidth) {
        this.cellSize = cellSize;
        this.gamePanelWidth = gamePanelWidth;
    }

    public int getPosX() {
        return this.posX;
    }

    public int getPosY() {
        return this.posY;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getX() < this.gamePanelWidth) {
            this.posX = (e.getX() - (e.getX() % this.cellSize)) / this.cellSize;
            System.out.println(this.posX);
            this.posY = (e.getY() - (e.getY() % this.cellSize)) / this.cellSize;
            System.out.println(this.posY);
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
