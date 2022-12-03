package ui;

import tools.CellType;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Graph extends JLabel {
    private static final int GRAPH_WIDTH = 100;
    private static final int GRAPH_HEIGHT = 100;
    private final int posX;
    private final int posY;

    public Graph(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        this.setBackground(Color.BLACK);
        this.setBounds(posX, posY, GRAPH_WIDTH, GRAPH_HEIGHT);
    }

    private ImageIcon getImage(String imagePath) {
        ImageIcon imageIcon = new ImageIcon(imagePath);
        Image image = imageIcon.getImage();
        Image newimage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimage);
        return imageIcon;
    }

    public void setRightGraph(CellType[][] grid) {
        int residential = 0;
        int commercial = 0;
        int industrial = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == CellType.RESIDENTIAL) {
                    residential++;
                } else if (grid[i][j] == CellType.COMMERCIAL) {
                    commercial++;
                } else if (grid[i][j] == CellType.INDUSTRIAL) {
                    industrial++;
                }
            }
        }
        if (residential > commercial && residential > industrial) {
            this.setIcon(getImage("res/graphs/1.png"));
        }
        if (residential == commercial && residential > industrial) {
            this.setIcon(getImage("res/graphs/2.png"));
        }
        if (commercial > industrial && commercial > residential) {
            this.setIcon(getImage("res/graphs/3.png"));
        }
        if (commercial == industrial && commercial > residential) {
            this.setIcon(getImage("res/graphs/4.png"));
        }
        if (industrial > residential && industrial > commercial) {
            this.setIcon(getImage("res/graphs/5.png"));
        }
        if (industrial == residential && industrial > commercial) {
            this.setIcon(getImage("res/graphs/6.png"));
        }
        if (residential == commercial && commercial == industrial) {
            Random random = new Random();
            int randomZone = random.nextInt(0, 2);
            if (randomZone == 0) {
                residential++;
            }
            if (randomZone == 1) {
                commercial++;
            }
            if (randomZone == 2) {
                industrial++;
            }
        }
    }
}
