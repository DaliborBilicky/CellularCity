package ui;

import enums.CellType;
import tools.Image;

import javax.swing.JLabel;

/**
 * Trieda pridava na platno stlpcovy graf dopytu po zonach.
 */
public class Graph extends JLabel {
    private final int size;

    public Graph(int posX, int posY, int size) {
        this.size = size;
        this.setBounds(
            posX - (size / 2),
            posY,
            this.size,
            this.size);
        this.setFocusable(false);
        this.setGraphIcon(4);
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
        if (residential > commercial && residential > industrial) {
            this.setGraphIcon(1);
        }
        if (residential == commercial && residential > industrial) {
            this.setGraphIcon(2);
        }
        if (commercial > industrial && commercial > residential) {
            this.setGraphIcon(3);
        }
        if (commercial == industrial && commercial > residential) {
            this.setGraphIcon(4);
        }
        if (industrial > residential && industrial > commercial) {
            this.setGraphIcon(5);
        }
        if (industrial == residential && industrial > commercial) {
            this.setGraphIcon(6);
        }
    }

    private void setGraphIcon(int num) {
        this.setIcon(new Image().getImageIcon(
            String.format("res/graph/%d.png", num),
            this.size));
    }
}
