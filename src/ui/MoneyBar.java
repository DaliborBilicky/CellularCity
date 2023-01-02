package ui;

import javax.swing.*;
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.*;

public class MoneyBar extends JProgressBar {
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

    public void setMinMax(int min, int max) {
        this.setMinimum(min);
        this.setMaximum(max);
    }

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
