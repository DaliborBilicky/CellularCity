package game;

import enums.CellType;
import enums.CheckBoxType;
import enums.GridView;
import tools.*;
import tools.Canvas;
import tools.Image;
import ui.Button;
import ui.CheckBox;
import ui.Graph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Trieda na vykreslovanie komponentov do GameFramu.
 * !!! To, ze trieda dedi od JPanel mam naucene z internetu. !!!
 */
public class Panel extends JPanel implements ActionListener {
    public static final int CELL_SIZE = 35;
    private static final int PANEL_WIDTH =
        (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private static final int PANEL_HEIGHT =
        (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    private static final int SIDE_PANEL_WIDTH = (PANEL_WIDTH - PANEL_HEIGHT) / 2;
    private static final int GAME_PANEL_WIDTH = PANEL_WIDTH - SIDE_PANEL_WIDTH;
    private final CheckBox[] checkBoxes;
    private final Button[] buttons;
    private final Graph graph;
    private final Grid grid;
    private final MouseInput mouseInput;
    private final JLabel label;
    private final Canvas canvas;
    private final View view;
    private CheckBox bulldozerCheckBoxes;
    private CheckBox viewCheckBox;

    public Panel() {
        this.checkBoxes = new CheckBox[CheckBoxType.values().length - 1];
        this.buttons = new Button[CheckBoxType.values().length - 1];
        this.mouseInput = new MouseInput(CELL_SIZE, GAME_PANEL_WIDTH, PANEL_HEIGHT);
        this.graph = new Graph(GAME_PANEL_WIDTH + SIDE_PANEL_WIDTH / 4, 750);
        this.grid = new Grid(PANEL_HEIGHT, GAME_PANEL_WIDTH, CELL_SIZE);
        this.label = new JLabel();
        this.canvas = new Canvas(CELL_SIZE);
        this.view = new View();

        this.add(this.graph);
        this.setPanel();
        this.setCheckBoxes();
        this.setButtons();
        this.addMouseListener(this.mouseInput);
    }

    /**
     * Metoda na vykreslovanie tvarou a obrazkov pomocou Graphics.
     * !!! Nazov metody a riadok pod tym mam naucene z internetu. !!!
     */
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        this.canvas.setGraphics(graphics);
        this.canvas.drawGrid(GAME_PANEL_WIDTH, PANEL_HEIGHT);
        this.canvas.drawEnergyBuildings(
            new Image().getBufferedImage("res/resources/Power.png"),
            new Image().getBufferedImage("res/resources/Water.png"));
        this.canvas.drawGridWithInfra(this.grid.getUndergroundGrid());
        this.canvas.drawGridWithInfra(this.grid.getOvergroundGrid());
        this.graph.setRightGraph(this.grid.getOvergroundGrid());
        this.checkBoxesFunction();
    }

    /**
     * Metoda ktora vypisuje fps do okna hry.
     */
    public void setFpsLabel(String text) {
        this.label.setFont(new Font("Arial", Font.BOLD, 12));
        this.label.setBounds(0, 0, 60, 20);
        this.label.setForeground(Color.GREEN);
        this.label.setText(text);
        this.add(this.label);
    }

    /**
     * Metoda na nastavenie hodnot panelu.
     * Oddelene od konstruktora pre citatelnost.
     */
    private void setPanel() {
        this.setBackground(new Color(80, 200, 120));
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
                250 + (150 * i));
            this.add(this.checkBoxes[i]);
        }
        this.bulldozerCheckBoxes = new CheckBox(
            GAME_PANEL_WIDTH + SIDE_PANEL_WIDTH / 4,
            100);
        this.add(this.bulldozerCheckBoxes);
        this.viewCheckBox = new CheckBox(
            GAME_PANEL_WIDTH + SIDE_PANEL_WIDTH * 3 / 4,
            100);
        this.add(this.viewCheckBox);
    }

    public void setCheckBoxesLook(int checkBoxIndex, int index) {
        System.out.println(checkBoxIndex + " - " + index);
        this.checkBoxes[checkBoxIndex].setLook(
            CheckBoxType.values()[checkBoxIndex].getCellTypes()[index].getImageIcons()[0],
            CheckBoxType.values()[checkBoxIndex].getCellTypes()[index].getImageIcons()[1]
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
        for (int i = 0; i < this.buttons.length; i++) {
            this.buttons[i] = new Button(
                GAME_PANEL_WIDTH + SIDE_PANEL_WIDTH * 3 / 4,
                250 + (150 * i));
            this.add(this.buttons[i]);
            this.buttons[i].setObjective(CheckBoxType.values()[i].getCellTypes().length);
            this.buttons[i].addActionListener(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < this.buttons.length; i++) {
            if (e.getSource() == this.buttons[i]) {
                System.out.println(i + ". -> " + this.buttons[i].getCounter());
                this.buttons[i].addToCounter();
            }
        }
    }

    /**
     * Metoda interuje cez check boxy a ked je daky z nich oznaceny vykona telo
     * cyklu. To pozostava z nastavenia bunky a typu bunky do 2D array. Na konci
     * uz len premeni check box iconu na selected
     */
    private void checkBoxesFunction() {
        for (int i = 0; i < this.checkBoxes.length; i++) {
            if (this.checkBoxes[i].isSelected() && this.mouseInput.isClicked()) {
                this.mouseInput.drag(
                    this.grid,
                    this.view,
                    CheckBoxType.values()[i],
                    this.buttons[i].getCounter());
                this.reset();
            } else if (this.bulldozerCheckBoxes.isSelected() && this.mouseInput.isClicked()) {
                this.mouseInput.drag(
                    this.grid, this.view, CheckBoxType.EMPTY_CELL, 0);
                this.reset();
            } else if (!this.bulldozerCheckBoxes.isSelected()
                && !this.checkBoxes[0].isSelected()
                && !this.checkBoxes[1].isSelected()
                && !this.checkBoxes[2].isSelected()) {
                this.reset();
            }
            this.setCheckBoxesLook(i, this.buttons[i].getCounter());
        }
    }

    private void reset() {
        this.mouseInput.resetPos();
        this.grid.setOvergroundGridCell(0, 0, CellType.EMPTY_CELL);
        this.grid.setUndergroundGridCell(0, 0, CellType.EMPTY_CELL);
    }
}
