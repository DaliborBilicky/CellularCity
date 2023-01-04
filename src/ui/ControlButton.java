package ui;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;

/**
 * Trieda dedi a upravuje zakladnu triedu JButton na specifickejsie
 * poziadavky.
 * !!! Dedenie mam naucene z internetu!!!
 */
public class ControlButton extends JButton {
    /**
     * Konstanta uchovava string hodnoty ktore sa zobrazia na tlacidle.
     */
    private static final String[] NAMES = new String[]{"SAVE", "QUIT"};

    /**
     * Konstruktor nastavuje JButton podla poziadaviek.
     *
     * @param posX   int
     * @param posY   int
     * @param width  int
     * @param height int
     */
    public ControlButton(int posX, int posY, int width, int height) {
        this.setBounds(
            posX - (width / 2),
            posY,
            width,
            height);
        this.setFocusable(false);
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setBackground(Color.LIGHT_GRAY);
        this.setBorderPainted(true);
        this.setFont(new Font("Arial", Font.BOLD, 32));
    }

    /**
     * Metoda nastavuje meno tlacidla podla indexu.
     *
     * @param index int
     */
    public void setName(int index) {
        this.setText(NAMES[index]);
    }
}
