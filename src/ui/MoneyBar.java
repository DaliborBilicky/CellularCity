package ui;

import javax.swing.JProgressBar;
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.Color;
import java.awt.Font;

/**
 * Trieda pridava na platno ukazatel penazi na ucte. Taktiez dedi a
 * upravuje zakladnu triedu JProgressBar na specifickejsie poziadavky.
 */
public class MoneyBar extends JProgressBar {

    /**
     * Konstruktor nastavuje JProgressBar podla poziadaviek.
     *
     * @param posX   int
     * @param posY   int
     * @param width  int
     * @param height int
     */
    public MoneyBar(int posX, int posY, int width, int height) {
        this.setBounds(
            posX - (width / 2),
            posY,
            width,
            height);
        this.setFocusable(false);
        this.setStringPainted(true);
        this.setOwnColors();
        this.setBackground(Color.DARK_GRAY);
        this.setFont(new Font("Arial", Font.BOLD, 42));
    }

    /**
     * @param min minimalna hodnota po kadial ide ukazatel
     * @param max maximalna hodnota po kadial ide ukazatel
     */
    public void setMinMax(int min, int max) {
        this.setMinimum(min);
        this.setMaximum(max);
    }

    /**
     * Metoda nastavuje to ako vyzera ukazatel podla zostatku na ucte.
     *
     * @param account zostatok na ucte
     */
    public void updateBar(int account) {
        if (account < 0) {
            this.setForeground(new Color(237, 28, 36));
        } else {
            this.setForeground(new Color(30, 230, 86));
        }
        this.setValue(account);
        this.setString(account + " $");
    }

    /**
     * Sluzi na prepisanie predvolenych hodnout.
     * !!! Metoda je z internetu. !!!
     * https://stackoverflow.com/questions/3480125/setting-the-colors-of-a-jprogressbar-text
     */
    private void setOwnColors() {
        setUI(new BasicProgressBarUI() {
            protected Color getSelectionBackground() {
                return Color.LIGHT_GRAY;
            }

            protected Color getSelectionForeground() {
                return Color.BLACK;
            }
        });
    }
}
