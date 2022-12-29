package game;

import enums.CellType;
import enums.CheckBoxType;
import enums.GridView;
import tools.*;
import tools.Canvas;
import tools.Image;
import ui.ControlButton;
import ui.GameButton;
import ui.CheckBox;
import ui.Graph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

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
    private final ControlButton[] controlButtons;
    private final GameButton[] gameButtons;
    private final CheckBox[] checkBoxes;
    private final MouseInput mouseInput;
    private final Canvas canvas;
    private final JLabel label;
    private final Graph graph;
    private final Grid grid;
    private final View view;
    private final Save save;
    private CheckBox bulldozerCheckBoxes;
    private CheckBox viewCheckBox;

    public Panel() {
        this.graph = new Graph(GAME_PANEL_WIDTH + SIDE_PANEL_WIDTH / 4, 830);
        this.mouseInput = new MouseInput(CELL_SIZE, GAME_PANEL_WIDTH, PANEL_HEIGHT);
        this.checkBoxes = new CheckBox[CheckBoxType.values().length - 1];
        this.save = new Save();
        this.grid = new Grid(PANEL_HEIGHT, GAME_PANEL_WIDTH, CELL_SIZE, this.save);
        this.gameButtons = new GameButton[CheckBoxType.values().length - 1];
        this.controlButtons = new ControlButton[2];
        this.canvas = new Canvas(CELL_SIZE);
        this.label = new JLabel();
        this.view = new View();

        this.addMouseListener(this.mouseInput);
        this.add(this.graph);
        this.setCheckBoxes();
        this.setButtons();
        this.setPanel();
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
            new Image().getBufferedImage("res/power/Power.png"),
            new Image().getBufferedImage("res/water/Water.png"));
        if (this.view.isUnderground()) {
            this.canvas.drawGridWithInfra(this.grid.getUndergroundGrid());
        }
        this.graph.setRightGraph(this.grid.getOvergroundGrid());
        this.checkBoxesFunction();
    }

    /**
     * Metoda ktora vypisuje fps do okna hry.
     */
    public void setFpsLabel(String text) {
        this.label.setFont(new Font("Arial", Font.BOLD, 20));
        this.label.setBounds(PANEL_WIDTH - 25, 0, 25, 25);
        this.label.setForeground(Color.ORANGE);
        this.label.setText(text);
        this.add(this.label);
    }

    /**
     * Metoda na nastavenie hodnot panelu.
     * Oddelene od konstruktora pre citatelnost.
     */
    private void setPanel() {
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setFocusable(true);
        this.setLayout(null);
    }

    /**
     * Metoda zoberie array check boxov a interaciu ich prida na panel.
     */
    private void setCheckBoxes() {
        for (int i = 0; i < this.checkBoxes.length; i++) {
            this.checkBoxes[i] = new CheckBox(
                GAME_PANEL_WIDTH + SIDE_PANEL_WIDTH / 4,
                225 + (150 * i));
            this.add(this.checkBoxes[i]);
        }
        this.bulldozerCheckBoxes = new CheckBox(
            GAME_PANEL_WIDTH + SIDE_PANEL_WIDTH / 4,
            75);
        this.add(this.bulldozerCheckBoxes);
        this.viewCheckBox = new CheckBox(
            GAME_PANEL_WIDTH + SIDE_PANEL_WIDTH * 3 / 4,
            75);
        this.add(this.viewCheckBox);
    }

    public void setCheckBoxesLook(int checkBoxIndex, int index) {
        this.checkBoxes[checkBoxIndex].setLook(
            CheckBoxType.values()[checkBoxIndex]
                .getCellTypes()[index].getImageIcons()[0],
            CheckBoxType.values()[checkBoxIndex]
                .getCellTypes()[index].getImageIcons()[1]
        );
        this.bulldozerCheckBoxes.setLook(
            CheckBoxType.EMPTY_CELL.getCellTypes()[0].getImageIcons()[0],
            CheckBoxType.EMPTY_CELL.getCellTypes()[0].getImageIcons()[1]
        );
        this.viewCheckBox.setLook(
            GridView.OVERGROUND.getImageIcon(),
            GridView.UNDERGROUND.getImageIcon());
    }

    private void setButtons() {
        for (int i = 0; i < this.gameButtons.length; i++) {
            this.gameButtons[i] = new GameButton(
                GAME_PANEL_WIDTH + SIDE_PANEL_WIDTH * 3 / 4,
                225 + (150 * i));
            this.gameButtons[i].setCounterLimit(
                CheckBoxType.values()[i].getCellTypes().length);
            this.gameButtons[i].addActionListener(this);
            this.add(this.gameButtons[i]);
        }
        for (int i = 0; i < this.controlButtons.length; i++) {
            this.controlButtons[i] = new ControlButton(
                GAME_PANEL_WIDTH + SIDE_PANEL_WIDTH * (1 + 2 * i) / 4,
                1030
            );
            this.controlButtons[i].setName(i);
            this.controlButtons[i].addActionListener(this);
            this.add(this.controlButtons[i]);
        }
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
                "saves/saveOverground.txt", this.grid.getOvergroundGrid());
            this.save.saveGame(
                "saves/saveUnderground.txt", this.grid.getUndergroundGrid());
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
    private void checkBoxesFunction() {
        for (int i = 0; i < this.checkBoxes.length; i++) {
            if (this.checkBoxes[i].isSelected()
                && this.mouseInput.isClicked()) {
                if (i > 1 && this.view.isUnderground()) {
                    this.mouseInput.drag(
                        this.grid,
                        this.view,
                        CheckBoxType.values()[i],
                        this.gameButtons[i].getCounter());
                    this.mouseInput.resetPos();
                } else if (i < 2 && !this.view.isUnderground()) {
                    this.mouseInput.drag(
                        this.grid,
                        this.view,
                        CheckBoxType.values()[i],
                        this.gameButtons[i].getCounter());
                    this.mouseInput.resetPos();
                }
            }

            if (this.bulldozerCheckBoxes.isSelected()
                && this.mouseInput.isClicked()) {
                this.mouseInput.drag(
                    this.grid, this.view, CheckBoxType.EMPTY_CELL, 0);
            }

            if (!this.bulldozerCheckBoxes.isSelected()
                && !this.checkBoxes[0].isSelected()
                && !this.checkBoxes[1].isSelected()
                && !this.checkBoxes[2].isSelected()
                && !this.checkBoxes[3].isSelected()) {
                this.mouseInput.resetPos();
            }

            if (this.viewCheckBox.isSelected()) {
                this.view.setGridView(GridView.UNDERGROUND);
                this.setBackground(new Color(131, 101, 57));
            } else {
                this.view.setGridView(GridView.OVERGROUND);
                this.setBackground(new Color(80, 200, 120));
            }
            this.setCheckBoxesLook(i, this.gameButtons[i].getCounter());
        }
    }
}
