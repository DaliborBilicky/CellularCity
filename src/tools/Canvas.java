package tools;

import enums.CellType;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * Trieda ktora vykresluje komplexnejsie veci ako len stvorec a pod.
 */
public class Canvas {
    private final int cellSize;
    private Graphics graphics;

    public Canvas(int cellSize) {
        this.cellSize = cellSize;
    }

    public void setGraphics(Graphics graphics) {
        this.graphics = graphics;
    }

    /**
     * Metoda zoberie parametre vysku a sirku a podla nich vykresli mriezku.
     */
    public void drawGrid(int width, int height) {
        for (int i = 0; i <= width / this.cellSize; i++) {
            for (int j = 0; j <= height / this.cellSize; j++) {
                this.graphics.drawLine(
                    i * this.cellSize,
                    0,
                    i * this.cellSize,
                    height - (height % this.cellSize));
                this.graphics.drawLine(
                    0,
                    j * cellSize,
                    width - (width % this.cellSize),
                    j * this.cellSize);
            }
        }
    }

    /**
     * Metoda zoberie pridelene obrazky a vykresli do laveho horneho rohu
     * elektraren a vodnu nadrz.
     */
    public void drawEnergyBuildings(BufferedImage power, BufferedImage water) {
        int sideSize = 2;
        this.graphics.fillRect(
            0,
            0,
            (sideSize * 2) * this.cellSize,
            sideSize * this.cellSize);
        this.graphics.drawImage(
            power,
            0,
            0,
            this.cellSize * sideSize,
            this.cellSize * sideSize,
            null);
        this.graphics.drawImage(
            water,
            this.cellSize * sideSize,
            0,
            this.cellSize * sideSize,
            this.cellSize * sideSize,
            null);
    }

    /**
     * Metoda zoberie 2D array a vykresli ho do mriezky.
     */
    public void drawGridWithInfra(CellType[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                this.graphics.drawImage(
                    grid[i][j].getBufferedImage(),
                    this.cellSize * j,
                    this.cellSize * i,
                    this.cellSize,
                    this.cellSize,
                    null);
            }
        }
    }
}
