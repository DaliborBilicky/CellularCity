package game;

import enums.CheckBoxType;
import enums.GridState;
import tools.*;
import tools.Canvas;
import tools.Image;
import ui.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Trieda na vykreslovanie komponentov do GameFramu.
 * !!! To, ze trieda dedi od JPanel mam naucene z internetu. !!!
 */
public class Panel extends JPanel implements ActionListener {
    private static final int PANEL_WIDTH =
        (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private static final int PANEL_HEIGHT =
        (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    private static final int SIDE_PANEL_WIDTH = (PANEL_WIDTH - PANEL_HEIGHT) / 2;
    private static final int GAME_PANEL_WIDTH = PANEL_WIDTH - SIDE_PANEL_WIDTH;
    private static final int CELL_SIZE = 35;
    private final MouseInput mouseInput;
    private final Canvas canvas;
    private final Save save;
    private final Grid grid;
    private final Account account;
    private Thumb thumb;
    private MoneyBar moneyBar;
    private Graph graph;
    private ControlButton[] controlButtons;
    private GameButton[] gameButtons;
    private CheckBox[] checkBoxes;
    private CheckBox bulldozerCheckBoxes;
    private CheckBox viewCheckBox;

    public Panel() {
        this.save = new Save();
        this.canvas = new Canvas(CELL_SIZE);
        this.grid = new Grid(
            PANEL_HEIGHT,
            GAME_PANEL_WIDTH,
            CELL_SIZE,
            this.save);
        this.account = new Account(this.grid);
        this.mouseInput = new MouseInput(
            this.grid,
            this.account,
            CELL_SIZE,
            GAME_PANEL_WIDTH,
            PANEL_HEIGHT);

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

    public Account getAccount() {
        return this.account;
    }

    public Grid getGrid() {
        return this.grid;
    }

    private void setUICheckBoxes() {
        this.checkBoxes = new CheckBox[CheckBoxType.values().length - 1];
        for (int i = 0; i < this.checkBoxes.length; i++) {
            this.checkBoxes[i] = new CheckBox(
                GAME_PANEL_WIDTH + SIDE_PANEL_WIDTH / 4,
                PANEL_HEIGHT * (i + 1) / 8,
                PANEL_HEIGHT / 9);
            this.add(this.checkBoxes[i]);
        }
        this.bulldozerCheckBoxes = new CheckBox(
            GAME_PANEL_WIDTH + SIDE_PANEL_WIDTH / 4,
            0,
            PANEL_HEIGHT / 9);
        this.add(this.bulldozerCheckBoxes);
        this.viewCheckBox = new CheckBox(
            GAME_PANEL_WIDTH + SIDE_PANEL_WIDTH * 3 / 4,
            0,
            PANEL_HEIGHT / 9);
        this.add(this.viewCheckBox);
    }

    private void setUIButtons() {
        this.gameButtons = new GameButton[CheckBoxType.values().length - 1];
        for (int i = 0; i < this.gameButtons.length; i++) {
            this.gameButtons[i] = new GameButton(
                GAME_PANEL_WIDTH + SIDE_PANEL_WIDTH * 3 / 4,
                PANEL_HEIGHT * (i + 1) / 8,
                PANEL_HEIGHT / 9);
            this.gameButtons[i].setCounterLimit(
                CheckBoxType.values()[i].getCellTypes().length);
            this.gameButtons[i].addActionListener(this);
            this.add(this.gameButtons[i]);
        }
        this.controlButtons = new ControlButton[2];
        for (int i = 0; i < this.controlButtons.length; i++) {
            this.controlButtons[i] = new ControlButton(
                GAME_PANEL_WIDTH + SIDE_PANEL_WIDTH * (1 + 2 * i) / 4,
                PANEL_HEIGHT * 7 / 8,
                SIDE_PANEL_WIDTH / 2,
                PANEL_HEIGHT / 14);
            this.controlButtons[i].setName(i);
            this.controlButtons[i].addActionListener(this);
            this.add(this.controlButtons[i]);
        }
    }

    private void setUIThumb() {
        this.thumb = new Thumb(
            GAME_PANEL_WIDTH + SIDE_PANEL_WIDTH / 4,
            PANEL_HEIGHT * 5 / 8,
            PANEL_HEIGHT / 9);
        this.add(this.thumb);
    }

    private void setGraph() {
        this.graph = new Graph(
            GAME_PANEL_WIDTH + SIDE_PANEL_WIDTH * 3 / 4,
            PANEL_HEIGHT * 5 / 8,
            PANEL_HEIGHT / 9);
        this.add(this.graph);
    }

    private void setUIMoneyBar() {
        this.moneyBar = new MoneyBar(
            GAME_PANEL_WIDTH + SIDE_PANEL_WIDTH / 2,
            PANEL_HEIGHT * 6 / 8,
            SIDE_PANEL_WIDTH,
            PANEL_HEIGHT / 12);
        this.moneyBar.setMinMax(this.account.getMinimum(), this.account.getGoal());
        this.add(this.moneyBar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (GameButton button : this.gameButtons) {
            if (e.getSource() == button) {
                button.increaseCounter();
            }
        }
        if (e.getSource() == this.controlButtons[0]) {
            this.save.saveGame(
                "save/saveOverground.txt", this.grid.getOvergroundGrid());
            this.save.saveGame(
                "save/saveUnderground.txt", this.grid.getUndergroundGrid());
        }
        if (e.getSource() == this.controlButtons[1]) {
            System.exit(0);
        }
    }

    /**
     * Metoda interuje cez check boxy a ked je daky z nich oznaceny vykona telo
     * cyklu. To pozostava z nastavenia bunky a typu bunky do 2D array. Na konci
     * uz len premeni check box iconu na selected
     */
    public void checkBoxesAction() {
        for (int i = 0; i < this.checkBoxes.length; i++) {
            if (this.checkBoxes[i].isSelected()
                && this.mouseInput.isClicked()) {
                if (i > 1 && GridState.UNDERGROUND.isActive()
                    || i < 2 && GridState.OVERGROUND.isActive()) {
                    this.mouseInput.drag(CheckBoxType.values()[i],
                        this.gameButtons[i].getCounter());
                    this.mouseInput.resetPos();
                }
            }

            if (this.bulldozerCheckBoxes.isSelected()
                && this.mouseInput.isClicked()) {
                this.mouseInput.drag(CheckBoxType.EMPTY_CELL, 0);
            }

            if (!this.bulldozerCheckBoxes.isSelected()
                && !this.checkBoxes[0].isSelected()
                && !this.checkBoxes[1].isSelected()
                && !this.checkBoxes[2].isSelected()
                && !this.checkBoxes[3].isSelected()) {
                this.mouseInput.resetPos();
            }

            if (this.viewCheckBox.isSelected()) {
                GridState.OVERGROUND.setActive(false);
                GridState.UNDERGROUND.setActive(true);
                this.setBackground(new Color(131, 101, 57));
            } else {
                GridState.OVERGROUND.setActive(true);
                GridState.UNDERGROUND.setActive(false);
                this.setBackground(new Color(80, 200, 120));
            }
        }
    }

    public void setCheckBoxesLook() {
        for (int i = 0; i < this.checkBoxes.length; i++) {
            this.checkBoxes[i].setLook(
                CheckBoxType.values()[i].getCellTypes()
                    [this.gameButtons[i].getCounter()]);
        }
        this.bulldozerCheckBoxes.setLook(
            CheckBoxType.EMPTY_CELL.getCellTypes()[0]);
        this.viewCheckBox.setLook(
            GridState.OVERGROUND.getImageIcon(),
            GridState.UNDERGROUND.getImageIcon());
    }

    /**
     * Metoda na vykreslovanie tvarou a obrazkov pomocou Graphics.
     * !!! Nazov metody a riadok pod tym mam naucene z internetu. !!!
     */
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        this.canvas.setGraphics(graphics);
        this.canvas.drawGrid(GAME_PANEL_WIDTH, PANEL_HEIGHT);
        this.canvas.drawGridWithInfra(this.grid.getOvergroundGrid());
        this.canvas.drawEnergyBuildings(
            new Image().getBufferedImage("res/tools/Power.png"),
            new Image().getBufferedImage("res/tools/Water.png"));
        if (GridState.UNDERGROUND.isActive()) {
            this.canvas.drawGridWithInfra(this.grid.getUndergroundGrid());
        }

        this.setCheckBoxesLook();
        this.moneyBar.updateBar(this.account.getAccount());
        this.graph.setRightGraph(this.grid.getOvergroundGrid());
        this.thumb.setRightThump(40);
    }
}
