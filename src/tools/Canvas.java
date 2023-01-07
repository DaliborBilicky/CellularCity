package tools;

import enums.CellType;
import enums.Warning;

import java.awt.Graphics;

/**
 * Trieda ktora vykresluje komplexnejsie veci ako len stvorec, ciaru a pod.
 */
public class Canvas {
    private int cellSize;
    private Graphics graphics;

    public Canvas() {
    }

    /**
     * @param graphics grafika
     * @param cellSize velkost bunky
     */
    public void setCanvas(Graphics graphics, int cellSize) {
        this.graphics = graphics;
        this.cellSize = cellSize;
    }

    /**
     * Metoda vykresli vzdy pekne zarovnanu mriezku.
     *
     * @param width  sirka mriezky
     * @param height vyska mriezky
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
                    j * this.cellSize,
                    width - (width % this.cellSize),
                    j * this.cellSize);
            }
        }
    }

    /**
     * Metoda zoberie 2D array a vykresli ho do mriezky.
     *
     * @param grid array ktory vykresluje
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

    /**
     * Metoda zoberie 2D array a vykresli ho do mriezky.
     *
     * @param grid array ktory vykresluje
     */
    public void drawWarnings(Warning[][] grid) {
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
