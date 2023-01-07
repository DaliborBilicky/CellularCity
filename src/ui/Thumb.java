package ui;

import enums.Warning;
import tools.Image;

import javax.swing.JLabel;

/**
 * Trieda pridava na platno ukazovatel spokojnosti. Taktiez dedi a
 * upravuje zakladnu triedu JLabel na specifickejsie poziadavky.
 */
public class Thumb extends JLabel {
    /**
     * cesty k obrazkom
     */
    private static final String UP = "res/thumb/Up.png";
    private static final String MIDDLE = "res/thumb/Middle.png";
    private static final String DOWN = "res/thumb/Down.png";
    private final Image image;
    private final int size;

    /**
     * Konstruktor nastavuje JLabel podla poziadaviek.
     *
     * @param posX int
     * @param posY int
     * @param size rozmer grafu
     */
    public Thumb(int posX, int posY, int size) {
        this.image = new Image();
        this.size = size;
        this.setBounds(
            posX - (size / 2),
            posY,
            size,
            size);
        this.setFocusable(false);
    }

    /**
     * Metoda pocita pomer pripojenych zon k nepripojenym.
     *
     * @param grid array upozorneni
     */
    public void calculateSatisfaction(Warning[][] grid) {
        double onePercent;
        int percent;
        int good = 0;
        int bad = 0;
        for (Warning[] warnings : grid) {
            for (Warning warning : warnings) {
                switch (warning) {
                    case CONNECTED:
                        good++;
                        break;
                    case NO_ROAD, NO_WATER, NO_POWER:
                        bad++;
                        break;
                }
            }
        }

        onePercent = 100.0 / (bad + good);
        percent = (int) (good * onePercent);
        this.setRightThump(percent);
    }

    /**
     * @param percent hodnota podla ktorej nastavi ukazovatel.
     */
    private void setRightThump(int percent) {
        if (75 <= percent && percent <= 100) {
            this.setIcon(this.image.getImageIcon(UP, this.size)
            );
        }
        if (50 <= percent && percent < 75) {
            this.setIcon(this.image.getImageIcon(MIDDLE, this.size)
            );
        }
        if (0 <= percent && percent < 50) {
            this.setIcon(this.image.getImageIcon(DOWN, this.size)
            );
        }
    }
}
