package game;

import tools.Canvas;
import tools.CellType;
import tools.Grid;
import tools.MouseInput;
import ui.CheckBox;
import ui.Graph;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel {
    public static final int CELL_SIZE = 25;
    private static final int PANEL_WIDTH = 1600;
    private static final int PANEL_HEIGHT = 900;
    private static final int SIDE_PANEL_WIDTH = (PANEL_WIDTH - PANEL_HEIGHT) / 2;
    private static final int GAME_PANEL_WIDTH = PANEL_WIDTH - SIDE_PANEL_WIDTH;
    private final BufferedImage[] imageList;
    private final CellType[] cellTypesList;
    private final CheckBox[] checkBoxes;
    private final Graph graph;
    private final Grid grid;
    private final MouseInput mouseInput;

    public GamePanel() {
        this.checkBoxes = new CheckBox[]{
            new CheckBox(GAME_PANEL_WIDTH + SIDE_PANEL_WIDTH / 2, 25, CellType.EMPTY_CELL),
            new CheckBox(GAME_PANEL_WIDTH + SIDE_PANEL_WIDTH / 2, 125, CellType.RESIDENTIAL),
            new CheckBox(GAME_PANEL_WIDTH + SIDE_PANEL_WIDTH / 2, 225, CellType.COMMERCIAL),
            new CheckBox(GAME_PANEL_WIDTH + SIDE_PANEL_WIDTH / 2, 325, CellType.INDUSTRIAL),
            new CheckBox(GAME_PANEL_WIDTH + SIDE_PANEL_WIDTH / 2, 425, CellType.PIPE),
            new CheckBox(GAME_PANEL_WIDTH + SIDE_PANEL_WIDTH / 2, 525, CellType.POWER_LINE),
            new CheckBox(GAME_PANEL_WIDTH + SIDE_PANEL_WIDTH / 2, 625, CellType.ROAD),
        };
        this.imageList = new BufferedImage[]{
            this.getMyImage(CellType.EMPTY_CELL.getImagePath()[0]),
            this.getMyImage(CellType.RESIDENTIAL.getImagePath()[0]),
            this.getMyImage(CellType.COMMERCIAL.getImagePath()[0]),
            this.getMyImage(CellType.INDUSTRIAL.getImagePath()[0]),
            this.getMyImage(CellType.PIPE.getImagePath()[0]),
            this.getMyImage(CellType.POWER_LINE.getImagePath()[0]),
            this.getMyImage(CellType.ROAD.getImagePath()[0]),
            this.getMyImage("res/Power.png"),
            this.getMyImage("res/Water.png")
        };
        this.cellTypesList = new CellType[]{
            CellType.EMPTY_CELL,
            CellType.RESIDENTIAL,
            CellType.COMMERCIAL,
            CellType.INDUSTRIAL,
            CellType.PIPE,
            CellType.POWER_LINE,
            CellType.ROAD
        };
        this.mouseInput = new MouseInput(CELL_SIZE, GAME_PANEL_WIDTH, PANEL_HEIGHT);
        this.graph = new Graph(GAME_PANEL_WIDTH + CELL_SIZE * 2, 700);
        this.grid = new Grid(this.imageList, PANEL_HEIGHT, GAME_PANEL_WIDTH, CELL_SIZE);
        this.add(this.graph);
        this.mouseInputs();
        this.setCheckBoxes();
        this.setPanel();
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Canvas canvas = new Canvas(graphics, CELL_SIZE, SIDE_PANEL_WIDTH);
        canvas.drawGrid(PANEL_WIDTH, PANEL_HEIGHT);
        canvas.drawInfra(this.grid.getImageGrid());
        this.checkBoxesFunction();
        canvas.drawEnergyBuildings(this.imageList[7], this.imageList[8]);
        this.graph.setRightGraph(this.grid.getTypeGrid());
    }

    private void setPanel() {
        this.setBackground(new Color(80, 200, 120));
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setFocusable(true);
        this.setLayout(null);
    }

    private BufferedImage getMyImage(String imagePath) {
        BufferedImage image;
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        return image;
    }

    private void setCheckBoxes() {
        for (CheckBox checkBox : this.checkBoxes) {
            this.add(checkBox);
        }
    }

    private void checkBoxesFunction() {
        for (int i = 0; i < this.checkBoxes.length; i++) {
            if (this.checkBoxes[i].wasSelected()) {
                this.grid.getImageGrid()[this.mouseInput.getPosY()][this.mouseInput.getPosX()] = this.imageList[i];
                this.grid.getImageGrid()[0][0] = this.imageList[0];
                this.grid.getTypeGrid()[this.mouseInput.getPosY()][this.mouseInput.getPosX()] = this.cellTypesList[i];
                this.grid.getTypeGrid()[0][0] = this.cellTypesList[0];
                this.mouseInput.setPosX(0);
                this.mouseInput.setPosY(0);
            }
            this.checkBoxes[i].setSwitchLook();
        }
    }

    private void mouseInputs() {
        this.addMouseListener(this.mouseInput);
        this.addMouseMotionListener(this.mouseInput);
    }
}
