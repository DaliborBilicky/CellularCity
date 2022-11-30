package tools;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Canvas {
    private final Graphics graphics;
    private final int cellSize;
    private final int sidePanelWidth;

    public Canvas(Graphics graphics, int cellSize, int sidePanelWidth) {
        this.graphics = graphics;
        this.cellSize = cellSize;
        this.sidePanelWidth = sidePanelWidth;
    }

    public void drawGrid(int wWidth, int wHeight) {
        for (int i = 0; i <= (wWidth - this.sidePanelWidth) / this.cellSize; i++) {
            this.graphics.drawLine(
                    i * this.cellSize,
                    0,
                    i * this.cellSize,
                    wHeight);
            this.graphics.drawLine(
                    0,
                    i * cellSize,
                    wWidth - this.sidePanelWidth,
                    i * this.cellSize);
        }
    }

    public void drawEnergyBuildings(BufferedImage image1, BufferedImage image2) {
        int sideSize = 4;
        this.graphics.drawImage(
                image1,
                0,
                0,
                this.cellSize * sideSize,
                this.cellSize * sideSize,
                null);
        this.graphics.drawImage(
                image2,
                this.cellSize * sideSize,
                0,
                this.cellSize * sideSize,
                this.cellSize * sideSize,
                null);
    }

    public void drawInfra(BufferedImage[][] grid, int height, int width) {
        for (int i = 0; i < height; i++) {
            if (i > 3) {
                for (int j = 0; j < width; j++) {
                    if (j > 7) {
                        this.graphics.drawImage(
                                grid[i][j],
                                this.cellSize * j,
                                this.cellSize * i,
                                this.cellSize,
                                this.cellSize,
                                null);
                    }
                }
            }
        }
    }
}
