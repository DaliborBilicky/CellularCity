package game;

import enums.CellType;
import enums.CheckBoxType;
import enums.GridView;
import tools.Canvas;
import tools.Grid;
import tools.Image;
import tools.MouseInput;
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
    private CheckBox bulldozerCheckBoxes;
    private CheckBox viewCheckBox;

    public Panel() {
        this.checkBoxes = new CheckBox[CheckBoxType.values().length];
        this.buttons = new Button[CheckBoxType.values().length];
        this.mouseInput = new MouseInput(CELL_SIZE, GAME_PANEL_WIDTH, PANEL_HEIGHT);
        this.graph = new Graph(GAME_PANEL_WIDTH + SIDE_PANEL_WIDTH / 4, 750);
        this.grid = new Grid(PANEL_HEIGHT, GAME_PANEL_WIDTH, CELL_SIZE);
        this.label = new JLabel();
        this.canvas = new Canvas(CELL_SIZE);

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
        for (int i = 0; i < 3; i++) {
            this.setCheckBoxesLook(this.checkBoxes[i], i, this.buttons[i].getCounter());
        }
        //this.checkBoxesFunction();
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

    public void setCheckBoxesLook(CheckBox checkBox, int checkBoxIndex, int index) {
        checkBox.setLook(
            CheckBoxType.values()[checkBoxIndex].getCellTypes()[index].getImageIcons()[0],
            CheckBoxType.values()[checkBoxIndex].getCellTypes()[index].getImageIcons()[1]
        );
        this.bulldozerCheckBoxes.setLook(
            CellType.EMPTY_CELL.getImageIcons()[0],
            CellType.EMPTY_CELL.getImageIcons()[1]);
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
            this.buttons[i].setCounter(CheckBoxType.values()[i].getCellTypes().length);
            this.buttons[i].addActionListener(this);
        }
    }

    public Button[] getButtons() {
        return this.buttons;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < this.buttons.length; i++) {
            if (e.getSource() == this.buttons[i]) {
                if (this.buttons[i].getCounter() == 0) {
                    this.buttons[i].resetCounter();
                } else {
                    System.out.println(i + ". -> " + this.buttons[i].getCounter());
                    this.buttons[i].subtractFromCounter();
                }
            }
        }
    }

    /**
     * Metoda interuje cez check boxy a ked je daky z nich oznaceny vykona telo
     * cyklu. To pozostava z nastavenia bunky a typu bunky do 2D array. Na konci
     * uz len premeni check box iconu na selected
     */
//    private void checkBoxesFunction() {
//        for (int index = 0; index < this.checkBoxes.length; index++) {
//            if (this.checkBoxes[index].wasSelected() && this.mouseInput.isClicked()) {
//                this.mouseInput.dragMouseEastNorth(this.grid, index);
//                this.mouseInput.dragMouseEastSouth(this.grid, index);
//                this.mouseInput.dragMouseWestNorth(this.grid, index);
//                this.mouseInput.dragMouseWestSouth(this.grid, index);
//                this.mouseInput.resetPos();
//                this.grid.getGrid()[0][0] = CellTools.values()[0];
//                /*
//                todo inac to neviem vyriesit
//                 */
//            } else if (!this.checkBoxes[0].wasSelected()
//                && !this.checkBoxes[1].wasSelected()
//                && !this.checkBoxes[2].wasSelected()
//                && !this.checkBoxes[3].wasSelected()
//                && !this.checkBoxes[4].wasSelected()
//                && !this.checkBoxes[5].wasSelected()
//                && !this.checkBoxes[6].wasSelected()) {
//                this.mouseInput.resetPos();
//                this.grid.getGrid()[0][0] = CellTools.values()[0];
//            }
//            this.checkBoxes[index].setLook();
//        }
//    }
}
