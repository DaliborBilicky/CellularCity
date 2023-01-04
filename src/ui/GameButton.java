package ui;

import tools.Image;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Color;

/**
 * Trieda dedi a upravuje zakladnu triedu JButton na specifickejsie
 * poziadavky.
 * !!! Dedenie mam naucene z internetu!!!
 */
public class GameButton extends JButton {
    private int counterLimit;
    private int counter;

    /**
     * Konstruktor nastavuje JButton podla poziadaviek.
     *
     * @param posX int
     * @param posY int
     * @param size rozmer tlacidla
     */
    public GameButton(int posX, int posY, int size) {
        this.setBounds(
            posX - (size / 2),
            posY,
            size,
            size);
        this.setFocusable(false);
        this.setBackground(Color.LIGHT_GRAY);
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setBorderPainted(true);
        this.setIcon(new Image().getImageIcon(
            "res/tools/Button.png", 125));
    }

    /**
     * @return pocitadlo pre check box vedla ktoreho je tlacidlo
     */
    public int getCounter() {
        return this.counter;
    }

    /**
     * Metoda berie cislo a odcita jednotku aby nenastal
     * ArrayIndexOutOfBoundsException
     *
     * @param num int
     */
    public void setCounterLimit(int num) {
        this.counterLimit = num - 1;
    }

    /**
     * Metoda pripocitava jednotku k pocitadlu kym je splnena podmienka.
     */
    public void increaseCounter() {
        if (this.counter < this.counterLimit) {
            this.counter++;
        } else {
            this.counter = 0;
        }
    }
}
