package tools;

import enums.CellType;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * Trieda ktora vykresluje komplexnejsie veci ako len stvorec, ciaru a pod.
 */
public class Canvas {
    private final int cellSize;
    private Graphics graphics;

    /**
     * @param cellSize velkost bunky
     */
    public Canvas(int cellSize) {
        this.cellSize = cellSize;
    }

    /**
     * @param graphics setter pre lepsiu manipulaciu s triedou.
     */
    public void setGraphics(Graphics graphics) {
        this.graphics = graphics;
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
     * Metoda vykresli do laveho horneho rohu elektraren a vodnu nadrz.
     *
     * @param power obrazok vodnej nadrze (kvapka)
     * @param water obrazok elektrarne (blesk)
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
}
