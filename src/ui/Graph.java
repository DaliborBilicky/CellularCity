package ui;

import enums.CellType;
import tools.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Color;

/**
 * Trieda pridava na platno stlpcovy graf dopytu po zonach.
 */
public class Graph extends JLabel {
    private static final int GRAPH_WIDTH = 150;
    private static final int GRAPH_HEIGHT = 150;

    public Graph(int posX, int posY) {
        this.setBackground(Color.BLACK);
        this.setBounds(
            posX - (GRAPH_WIDTH / 2),
            posY - (GRAPH_HEIGHT / 2),
            GRAPH_WIDTH,
            GRAPH_HEIGHT);
        this.setIcon(
            new Image().getImageIcon("res/graphs/4.png", 150));
    }

    /**
     * Metoda cita 2D array a nascitava jedotlive typy buniek a podla tho
     * nastavi graf.
     */
    public void setRightGraph(CellType[][] grid) {
        int residential = 0;
        int commercial = 0;
        int industrial = 0;
        for (CellType[] cellTypes : grid) {
            for (CellType cellType : cellTypes) {
                switch (cellType) {
                    case RESIDENTIAL:
                        residential++;
                        break;
                    case COMMERCIAL:
                        commercial++;
                        break;
                    case INDUSTRIAL:
                        industrial++;
                        break;
                }
            }
        }
//todo skus sa zbavit toho slizoveho kodu.
        if (residential > commercial && residential > industrial) {
            this.setIcon(new Image().getImageIcon("res/graphs/1.png", 100));
        }
        if (residential == commercial && residential > industrial) {
            this.setIcon(new Image().getImageIcon("res/graphs/2.png", 100));
        }
        if (commercial > industrial && commercial > residential) {
            this.setIcon(new Image().getImageIcon("res/graphs/3.png", 100));
        }
        if (commercial == industrial && commercial > residential) {
            this.setIcon(new Image().getImageIcon("res/graphs/4.png", 100));
        }
        if (industrial > residential && industrial > commercial) {
            this.setIcon(new Image().getImageIcon("res/graphs/5.png", 100));
        }
        if (industrial == residential && industrial > commercial) {
            this.setIcon(new Image().getImageIcon("res/graphs/6.png", 100));
        }
    }
}
