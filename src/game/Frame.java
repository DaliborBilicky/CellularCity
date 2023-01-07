package game;

import enums.CellType;

import javax.swing.JFrame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;


/**
 * Trieda sluzi na zobrazenie okna v pocitaci.
 */
public class Frame extends JFrame {
    /**
     * Nadpis okna
     */
    private static final String TITLE = "Cellular City";

    /**
     * Konstruktor nastavuje hodnoty okna podla poziadaviek.
     */
    public Frame(Panel panel) {
        /*
        Riadok kodu som si pozical z projektu ShapesGE aby som mohol dat
        svoju aplikaciu na fullscreen.
         */
        GraphicsDevice device = GraphicsEnvironment
            .getLocalGraphicsEnvironment().getScreenDevices()[0];


        this.setTitle(TITLE);
        this.setIconImage(CellType.COMMERCIAL.getBufferedImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setUndecorated(true);
        this.setVisible(true);
        this.add(panel);
        this.pack();
        // Riadok zo ShapesGE
        device.setFullScreenWindow(this);
        panel.requestFocus();
    }
}
