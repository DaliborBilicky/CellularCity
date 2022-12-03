package tools;

import java.awt.image.BufferedImage;

public class Grid {
    private final BufferedImage[][] imageGrid;
    private final CellType[][] typeGrid;

    public Grid(BufferedImage[] imageList, int panelHeight, int gamePanelWidth, int cellSize) {
        this.imageGrid = new BufferedImage[panelHeight / cellSize][gamePanelWidth / cellSize];
        this.typeGrid = new CellType[panelHeight / cellSize][gamePanelWidth / cellSize];

        for (int i = 0; i < panelHeight / cellSize; i++) {
            for (int j = 0; j < gamePanelWidth / cellSize; j++) {
                this.imageGrid[i][j] = imageList[0];
                this.typeGrid[i][j] = CellType.EMPTY_CELL;
            }
        }
    }

    public BufferedImage[][] getImageGrid() {
        return this.imageGrid;
    }

    public CellType[][] getTypeGrid() {
        return this.typeGrid;
    }
}
