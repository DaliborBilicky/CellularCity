package game;

import tools.Canvas;
import tools.CellType;
import tools.MouseInput;
import ui.CheckBox;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel {
    public static final int CELL_SIZE = 25;
    private static final int PANEL_WIDTH = 1600;
    private static final int PANEL_HEIGHT = 900;
    private static final int SIDE_PANEL_WIDTH = (PANEL_WIDTH - PANEL_HEIGHT) / 2;
    private static final int GAME_PANEL_WIDTH = PANEL_WIDTH - SIDE_PANEL_WIDTH;

    private final MouseInput mouseInput;
    private final JLabel label;
    private final BufferedImage[][] grid;

    private final BufferedImage[] imageList;
    private CheckBox checkBox, checkBox1, checkBox2,
            checkBox3, checkBox4, checkBox5, checkBox6;

    public GamePanel() {
        this.mouseInput = new MouseInput(CELL_SIZE, GAME_PANEL_WIDTH);
        this.imageList = new BufferedImage[]{
                this.getImage(CellType.EMPTY_CELL.getImagePath()[0]),
                this.getImage(CellType.RESIDENTIAL.getImagePath()[0]),
                this.getImage(CellType.COMMERCIAL.getImagePath()[0]),
                this.getImage(CellType.INDUSTRIAL.getImagePath()[0]),
                this.getImage(CellType.PIPE.getImagePath()[0]),
                this.getImage(CellType.POWER_LINE.getImagePath()[0]),
                this.getImage(CellType.ROAD.getImagePath()[0]),
        };

        this.grid = new BufferedImage[PANEL_HEIGHT / CELL_SIZE][GAME_PANEL_WIDTH / CELL_SIZE];
        for (int i = 0; i < PANEL_HEIGHT / CELL_SIZE; i++) {
            for (int j = 0; j < GAME_PANEL_WIDTH / CELL_SIZE; j++) {
                this.grid[i][j] = this.imageList[0];
            }
        }

        this.setBackground(Color.CYAN);
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setFocusable(true);
        this.setLayout(null);

        this.mouseInputs();
        this.label = new JLabel();

        this.setCheckBoxes();
    }

    public BufferedImage getImage(String imagePath) {
        BufferedImage image;
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        return image;
    }

    public void paintComponent(Graphics graphics) {
        Canvas canvas = new Canvas(graphics, CELL_SIZE, SIDE_PANEL_WIDTH);
        super.paintComponent(graphics);
        canvas.drawGrid(PANEL_WIDTH, PANEL_HEIGHT);
        canvas.drawEnergyBuildings(this.imageList[0], this.imageList[0]);
        canvas.drawInfra(grid, PANEL_HEIGHT / CELL_SIZE, GAME_PANEL_WIDTH / CELL_SIZE);
        this.setModifiedGrid(this.mouseInput.getPosY(), this.mouseInput.getPosX(), this.imageList[0], this.checkBox);
        this.checkBox.setSwitchLook();
        this.setModifiedGrid(this.mouseInput.getPosY(), this.mouseInput.getPosX(), this.imageList[1], this.checkBox1);
        this.checkBox1.setSwitchLook();
        this.setModifiedGrid(this.mouseInput.getPosY(), this.mouseInput.getPosX(), this.imageList[2], this.checkBox2);
        this.checkBox2.setSwitchLook();
        this.setModifiedGrid(this.mouseInput.getPosY(), this.mouseInput.getPosX(), this.imageList[3], this.checkBox3);
        this.checkBox3.setSwitchLook();
        this.setModifiedGrid(this.mouseInput.getPosY(), this.mouseInput.getPosX(), this.imageList[4], this.checkBox4);
        this.checkBox4.setSwitchLook();
        this.setModifiedGrid(this.mouseInput.getPosY(), this.mouseInput.getPosX(), this.imageList[5], this.checkBox5);
        this.checkBox5.setSwitchLook();
        this.setModifiedGrid(this.mouseInput.getPosY(), this.mouseInput.getPosX(), this.imageList[6], this.checkBox6);
        this.checkBox6.setSwitchLook();
    }

    public void setFpsLabel(String text) {
        this.label.setText(text);
        this.label.setBounds(PANEL_WIDTH - 65, 0, 60, 20);
        this.label.setFont(new Font("Arial", Font.BOLD, 16));
        this.add(this.label);
    }

    private void setCheckBoxes() {
        this.checkBox = new CheckBox(GAME_PANEL_WIDTH + CELL_SIZE, 0, CellType.EMPTY_CELL);
        this.add(this.checkBox);
        this.checkBox1 = new CheckBox(GAME_PANEL_WIDTH + CELL_SIZE, 75, CellType.RESIDENTIAL);
        this.add(this.checkBox1);
        this.checkBox2 = new CheckBox(GAME_PANEL_WIDTH + CELL_SIZE, 150, CellType.COMMERCIAL);
        this.add(this.checkBox2);
        this.checkBox3 = new CheckBox(GAME_PANEL_WIDTH + CELL_SIZE, 225, CellType.INDUSTRIAL);
        this.add(this.checkBox3);
        this.checkBox4 = new CheckBox(GAME_PANEL_WIDTH + CELL_SIZE, 300, CellType.PIPE);
        this.add(this.checkBox4);
        this.checkBox5 = new CheckBox(GAME_PANEL_WIDTH + CELL_SIZE, 375, CellType.POWER_LINE);
        this.add(this.checkBox5);
        this.checkBox6 = new CheckBox(GAME_PANEL_WIDTH + CELL_SIZE, 450, CellType.ROAD);
        this.add(this.checkBox6);
    }

    private void mouseInputs() {
        this.addMouseListener(this.mouseInput);
        this.addMouseMotionListener(this.mouseInput);
    }


    private void setModifiedGrid(int i, int j, BufferedImage cellImage, CheckBox checkBox) {
        if (checkBox.wasSelected()) {
            this.grid[i][j] = cellImage;
        }
    }
}
