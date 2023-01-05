package ui;

import tools.Image;

import javax.swing.JLabel;

public class Thumb extends JLabel {
    private final Image image;
    private final int size;

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

    public void setRightThump(int percent) {
        if (75 <= percent && percent <= 100) {
            this.setIcon(this.image.getImageIcon(
                "res/thumb/Up.png",
                this.size)
            );
        }
        if (50 <= percent && percent < 75) {
            this.setIcon(this.image.getImageIcon(
                "res/thumb/Middle.png",
                this.size)
            );
        }
        if (0 <= percent && percent < 50) {
            this.setIcon(this.image.getImageIcon(
                "res/thumb/Down.png",
                this.size)
            );
        }
    }
}
