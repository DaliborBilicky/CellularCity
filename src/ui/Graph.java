package ui;

import enums.CellType;
import tools.Image;

import javax.swing.JLabel;

/**
 * Trieda pridava na platno stlpcovy graf dopytu po zonach. Taktiez dedi a
 * upravuje zakladnu triedu JLabel na specifickejsie poziadavky.
 */
public class Graph extends JLabel {
    /**
     * Formatovana cesta k obrazkom.
     */
    private static final String GRAPH = "res/graph/%d.png";
    private final int size;

    /**
     * Konstruktor nastavuje JLabel podla poziadaviek.
     *
     * @param posX int
     * @param posY int
     * @param size rozmer grafu
     */
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
     * Metoda cita 2D array a nascitava jedotlive typy buniek a podla toho
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

    /**
     * Oddelena metoda pre lepsiu citatelnost a udrzanie principu DRY
     *
     * @param graphNum cislo grafu
     */
    private void setGraphIcon(int graphNum) {
        this.setIcon(new Image().getImageIcon(
            String.format(GRAPH, graphNum),
            this.size));
    }
}
