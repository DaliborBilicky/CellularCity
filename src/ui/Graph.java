package ui;

import tools.CellType;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Image;

/**
 * Trieda pridava na platno stlpcovy graf dopytu po zonach.
 */
public class Graph extends JLabel {
    private static final int GRAPH_WIDTH = 100;
    private static final int GRAPH_HEIGHT = 100;

    public Graph(int posX, int posY) {
        this.setBackground(Color.BLACK);
        this.setBounds(posX, posY, GRAPH_WIDTH, GRAPH_HEIGHT);
        this.setIcon(this.getImageIcon("res/graphs/4.png"));
    }

    /**
     * Metoda zoberie cestu k ikone a vrati ikonu.
     * !!! Aby sa rescalela ikona som nasiel na internete. !!!
     */
    private ImageIcon getImageIcon(String imagePath) {
        ImageIcon imageIcon = new ImageIcon(imagePath);
        Image image = imageIcon.getImage();
        Image newImage = image.getScaledInstance(
            100,
            100,
            Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newImage);
        return imageIcon;
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
            this.setIcon(this.getImageIcon("res/graphs/1.png"));
        }
        if (residential == commercial && residential > industrial) {
            this.setIcon(this.getImageIcon("res/graphs/2.png"));
        }
        if (commercial > industrial && commercial > residential) {
            this.setIcon(this.getImageIcon("res/graphs/3.png"));
        }
        if (commercial == industrial && commercial > residential) {
            this.setIcon(this.getImageIcon("res/graphs/4.png"));
        }
        if (industrial > residential && industrial > commercial) {
            this.setIcon(this.getImageIcon("res/graphs/5.png"));
        }
        if (industrial == residential && industrial > commercial) {
            this.setIcon(this.getImageIcon("res/graphs/6.png"));
        }
    }
}
