package ui;

import tools.CellType;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Image;

public class Graph extends JLabel {
    private static final int GRAPH_WIDTH = 100;
    private static final int GRAPH_HEIGHT = 100;

    public Graph(int posX, int posY) {
        this.setBackground(Color.BLACK);
        this.setBounds(posX, posY, GRAPH_WIDTH, GRAPH_HEIGHT);
        this.setIcon(this.getImage("res/graphs/4.png"));
    }

    private ImageIcon getImage(String imagePath) {
        ImageIcon imageIcon = new ImageIcon(imagePath);
        Image image = imageIcon.getImage();
        Image newImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newImage);
        return imageIcon;
    }

    public void setRightGraph(CellType[][] grid) {
        int residential = 0;
        int commercial = 0;
        int industrial = 0;
        for (CellType[] cellTypes : grid) {
            for (CellType cellType : cellTypes) {
                if (cellType == CellType.RESIDENTIAL) {
                    residential++;
                } else if (cellType == CellType.COMMERCIAL) {
                    commercial++;
                } else if (cellType == CellType.INDUSTRIAL) {
                    industrial++;
                }
            }
        }
        if (residential > commercial && residential > industrial) {
            this.setIcon(this.getImage("res/graphs/1.png"));
        }
        if (residential == commercial && residential > industrial) {
            this.setIcon(this.getImage("res/graphs/2.png"));
        }
        if (commercial > industrial && commercial > residential) {
            this.setIcon(this.getImage("res/graphs/3.png"));
        }
        if (commercial == industrial && commercial > residential) {
            this.setIcon(this.getImage("res/graphs/4.png"));
        }
        if (industrial > residential && industrial > commercial) {
            this.setIcon(this.getImage("res/graphs/5.png"));
        }
        if (industrial == residential && industrial > commercial) {
            this.setIcon(this.getImage("res/graphs/6.png"));
        }
    }
}
