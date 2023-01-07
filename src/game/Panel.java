package game;

import enums.CellType;
import enums.CheckBoxType;
import enums.GridState;
import tools.Canvas;
import tools.GridHandler;
import tools.MouseInput;
import ui.CheckBox;
import ui.ControlButton;
import ui.GameButton;
import ui.Graph;
import ui.MoneyBar;
import ui.Thumb;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Trieda sluzi na vykreslovanie grafiky
 * !!! Dedenie mam naucene z internetu!!!
 */
public class Panel extends JPanel implements ActionListener {
    /**
     * Konstanty pre uchovanie rozmeru platna.
     * !!! Pouzitie Toolkit triedy mam z internetu. !!!
     */
    private static final int PANEL_WIDTH =
        (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private static final int PANEL_HEIGHT =
        (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    /**
     * Konstany pre uchovanie rozmeru hracieho pola a bocneho panelu. Su
     * vypocitane podla rozmeru platna.
     */
    private static final int SIDE_PANEL_WIDTH =
        (PANEL_WIDTH - PANEL_HEIGHT) / 2;
    private static final int GAME_PANEL_WIDTH = PANEL_WIDTH - SIDE_PANEL_WIDTH;
    /**
     * Konstanta ktora uchovava velkost stvorca v mriezke.
     */
    private static final int CELL_SIZE = 35;
    /**
     * Farby pozadia
     */
    private static final Color BROWN = new Color(131, 101, 57);
    private static final Color GREEN = new Color(80, 200, 120);
    private final MouseInput mouseInput;
    private final Canvas canvas;
    private final GridHandler gridHandler;
    private final Account account;
    private Thumb thumb;
    private MoneyBar moneyBar;
    private Graph graph;
    private ControlButton[] controlButtons;
    private GameButton[] gameButtons;
    private CheckBox[] checkBoxes;
    private CheckBox bulldozerCheckBoxes;
    private CheckBox viewCheckBox;


    /**
     * V konstruktore okrem incializacie komponentov sa nastavuje aj panel
     * podla poziadaviek.
     */
    public Panel(
        GridHandler gridHandler, Canvas canvas,
        MouseInput mouseInput, Account account) {
        this.gridHandler = gridHandler;
        this.canvas = canvas;
        this.mouseInput = mouseInput;
        this.account = account;

        this.gridHandler.setGrids(
            GAME_PANEL_WIDTH,
            PANEL_HEIGHT,
            CELL_SIZE);
        this.mouseInput.setDimensions(
            GAME_PANEL_WIDTH,
            PANEL_HEIGHT,
            CELL_SIZE);

        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setFocusable(true);
        this.setLayout(null);
        this.setUICheckBoxes();
        this.setUIButtons();
        this.setGraph();
        this.setUIThumb();
        this.setUIMoneyBar();
        this.addMouseListener(this.mouseInput);
    }

    /**
     * Metoda na vykreslovanie tvarou a obrazkov pomocou Graphics.
     * !!! To ze metodu ma nazov "paintComponent", pozaduje parameter
     * Graphics a obsahuje riadok "super.paintComponent(graphics);" mam z
     * internetu. !!!
     */
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        this.canvas.setCanvas(graphics, CELL_SIZE);
        this.canvas.drawGrid(GAME_PANEL_WIDTH, PANEL_HEIGHT);
        this.canvas.drawGridWithInfra(this.gridHandler.getOvergroundGrid());
        this.canvas.drawWarnings(this.gridHandler.getZonesConnection());
        if (GridState.UNDERGROUND.isActive()) {
            this.canvas.drawGridWithInfra(this.gridHandler.getUndergroundGrid());
        }

        this.moneyBar.updateBar(this.account.getAccount());
        this.graph.setRightGraph(this.gridHandler.getOvergroundGrid());
        this.thumb.calculateSatisfaction(this.gridHandler.getZonesConnection());
    }

    /**
     * Metoda ktora po obdrzani eventu a porovnani ci event pochadza od
     * spravneho tlacidla vykona telo podmienky.
     *
     * @param e event, ktorá sa má spracovať
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        for (GameButton button : this.gameButtons) {
            if (e.getSource() == button) {
                button.increaseCounter();
            }
        }
        if (e.getSource() == this.controlButtons[0]) {
            this.gridHandler.saveGrids();
        }
        if (e.getSource() == this.controlButtons[1]) {
            System.exit(0);
        }
    }

    /**
     * Metoda kontroluje aky check box bol oznaceny a podla toho vykona telo
     * podmienky.
     */
    public void checkBoxesAction() {
        /*
          Cyklus iteruje cez list check boxov a po spleni podmienky priradi
          bunku do platna. Podmienka pozostava z kotroly ci hra je pod/nad
          zemou a povoluje priradovat bunky iba do tej mriezky do ktorej patria.
         */
        for (int i = 0; i < this.checkBoxes.length; i++) {
            if (this.checkBoxes[i].isSelected()
                && this.mouseInput.isClicked()) {
                if (i > 1 && GridState.UNDERGROUND.isActive()
                    || i < 2 && GridState.OVERGROUND.isActive()) {
                    this.mouseInput.drag(CheckBoxType.values()[i],
                        this.gameButtons[i].getCounter());
                }
                this.mouseInput.resetPos();
            }
        }

        if (this.bulldozerCheckBoxes.isSelected()
            && this.mouseInput.isClicked()) {
            this.mouseInput.drag(CheckBoxType.EMPTY_CELL, 0);
            this.mouseInput.resetPos();
        }

        this.checkIfAreChecked();
        this.stateSwitch();
    }

    /**
     * Metoda nastavuje vzhlad check boxov.
     */
    public void setCheckBoxesLook() {
        for (int i = 0; i < this.checkBoxes.length; i++) {
            this.checkBoxes[i].setLook(
                CheckBoxType.values()[i]
                    .getCellTypes()[this.gameButtons[i].getCounter()]);
        }
        this.bulldozerCheckBoxes.setLook(
            CellType.EMPTY_CELL);
        this.viewCheckBox.setLook(
            GridState.OVERGROUND.getImageIcon(),
            GridState.UNDERGROUND.getImageIcon());
    }

    /**
     * Metoda resetuje pozicie ak nie je ziadny check box oznaceny.
     */
    private void checkIfAreChecked() {
        int counter = 0;
        for (CheckBox checkBox : this.checkBoxes) {
            if (!this.bulldozerCheckBoxes.isSelected()
                && !checkBox.isSelected()) {
                counter++;
            }
        }
        if (counter == this.checkBoxes.length) {
            this.mouseInput.resetPos();
        }
    }

    /**
     * Nastavuje pozadie Platna podla stavu hry a tiez nastavuje ktory stav
     * je prave aktivny.
     */
    private void stateSwitch() {
        if (this.viewCheckBox.isSelected()) {
            GridState.OVERGROUND.setActive(false);
            GridState.UNDERGROUND.setActive(true);
            this.setBackground(BROWN);
        } else {
            GridState.OVERGROUND.setActive(true);
            GridState.UNDERGROUND.setActive(false);
            this.setBackground(GREEN);
        }
    }

    /**
     * Metoda nastvuje CheckBoxy a pridava ich na Platno.
     */
    private void setUICheckBoxes() {
        this.checkBoxes = new CheckBox[CheckBoxType.values().length - 1];
        for (int i = 0; i < this.checkBoxes.length; i++) {
            this.checkBoxes[i] = new CheckBox(
                GAME_PANEL_WIDTH + SIDE_PANEL_WIDTH / 4,
                PANEL_HEIGHT * (i + 1) / 7,
                PANEL_HEIGHT / 8);
            this.add(this.checkBoxes[i]);
        }
        this.bulldozerCheckBoxes = new CheckBox(
            GAME_PANEL_WIDTH + SIDE_PANEL_WIDTH / 4,
            0,
            PANEL_HEIGHT / 8);
        this.add(this.bulldozerCheckBoxes);
        this.viewCheckBox = new CheckBox(
            GAME_PANEL_WIDTH + SIDE_PANEL_WIDTH * 3 / 4,
            0,
            PANEL_HEIGHT / 8);
        this.add(this.viewCheckBox);
    }

    /**
     * Metoda nastavuje tlacidla a pridava ich na Platno.
     */
    private void setUIButtons() {
        this.gameButtons = new GameButton[CheckBoxType.values().length - 1];
        for (int i = 0; i < this.gameButtons.length; i++) {
            this.gameButtons[i] = new GameButton(
                GAME_PANEL_WIDTH + SIDE_PANEL_WIDTH * 3 / 4,
                PANEL_HEIGHT * (i + 1) / 7,
                PANEL_HEIGHT / 8);
            this.gameButtons[i].setCounterLimit(
                CheckBoxType.values()[i].getCellTypes().length);
            this.gameButtons[i].addActionListener(this);
            this.add(this.gameButtons[i]);
        }
        this.controlButtons = new ControlButton[2];
        for (int i = 0; i < this.controlButtons.length; i++) {
            this.controlButtons[i] = new ControlButton(
                GAME_PANEL_WIDTH + SIDE_PANEL_WIDTH * (1 + 2 * i) / 4,
                PANEL_HEIGHT * 6 / 7,
                SIDE_PANEL_WIDTH / 2,
                PANEL_HEIGHT / 14);
            this.controlButtons[i].setName(i);
            this.controlButtons[i].addActionListener(this);
            this.add(this.controlButtons[i]);
        }
    }

    /**
     * Metoda nastavuje a pridava na Platno ukazatel spokojnosti (Thumb).
     */
    private void setUIThumb() {
        this.thumb = new Thumb(
            GAME_PANEL_WIDTH + SIDE_PANEL_WIDTH / 4,
            PANEL_HEIGHT * 4 / 7,
            PANEL_HEIGHT / 9);
        this.add(this.thumb);
    }

    /**
     * Metoda nastavuje a pridava na Platno graf dopytu po zonach.
     */
    private void setGraph() {
        this.graph = new Graph(
            GAME_PANEL_WIDTH + SIDE_PANEL_WIDTH * 3 / 4,
            PANEL_HEIGHT * 4 / 7,
            PANEL_HEIGHT / 9);
        this.add(this.graph);
    }

    /**
     * Metoda nastavuje a pridava na Platno ukazatel zostatku penazi.
     */
    private void setUIMoneyBar() {
        this.moneyBar = new MoneyBar(
            GAME_PANEL_WIDTH + SIDE_PANEL_WIDTH / 2,
            PANEL_HEIGHT * 5 / 7,
            SIDE_PANEL_WIDTH,
            PANEL_HEIGHT / 12);
        this.moneyBar.setMinMax(
            this.account.getMinimum(), this.account.getGoal());
        this.add(this.moneyBar);
    }
}
