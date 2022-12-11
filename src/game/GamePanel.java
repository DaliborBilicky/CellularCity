package game;

import tools.Canvas;
import tools.CellType;
import tools.Grid;
import tools.Image;
import tools.MouseInput;
import ui.CheckBox;
import ui.Graph;

import javax.swing.JPanel;
import java.awt.*;

/**
 * Trieda na vykreslovanie komponentov do GameFramu.
 * !!! To, ze trieda dedi od JPanel mam naucene z internetu. !!!
 */
public class GamePanel extends JPanel {
    public static final int CELL_SIZE = 35;
    private static final int PANEL_WIDTH =
        (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private static final int PANEL_HEIGHT =
        (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    private static final int SIDE_PANEL_WIDTH = (PANEL_WIDTH - PANEL_HEIGHT) / 2;
    private static final int GAME_PANEL_WIDTH = PANEL_WIDTH - SIDE_PANEL_WIDTH;
    private final CheckBox[] checkBoxes;
    private final Graph graph;
    private final Grid grid;
    private final MouseInput mouseInput;

    public GamePanel() {
        this.checkBoxes = new CheckBox[CellType.values().length];
        this.mouseInput = new MouseInput(CELL_SIZE, GAME_PANEL_WIDTH, PANEL_HEIGHT);
        this.graph = new Graph(GAME_PANEL_WIDTH + CELL_SIZE * 2, 700);
        this.grid = new Grid(PANEL_HEIGHT, GAME_PANEL_WIDTH, CELL_SIZE);
        this.add(this.graph);
        this.setPanel();
        this.setCheckBoxes();
        this.setMouseInputs();
    }

    /**
     * Metoda na vykreslovanie tvarou a obrazkov pomocou Graphics.
     * !!! Nazov metody a riadok pod tym mam naucene z internetu. !!!
     */
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Canvas canvas = new Canvas(graphics, CELL_SIZE);
        canvas.drawGrid(GAME_PANEL_WIDTH, PANEL_HEIGHT);
        canvas.drawInfra(this.grid.getGrid());
        this.checkBoxesFunction();
        canvas.drawEnergyBuildings(
            new Image().getMyImage("res/Power.png"),
            new Image().getMyImage("res/Water.png"));
        this.graph.setRightGraph(this.grid.getGrid());
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
        for (int i = 0; i < CellType.values().length; i++) {
            this.checkBoxes[i] = new CheckBox(
                GAME_PANEL_WIDTH + SIDE_PANEL_WIDTH / 2,
                25 + (100 * i),
                CellType.values()[i]);
            this.add(this.checkBoxes[i]);
        }
    }

    /**
     * Metoda interuje cez check boxy a ked je daky z nich oznaceny vykona telo
     * cyklu. To pozostava z nastavenia bunky a typu bunky do 2D array. Na konci
     * uz len premeni check box iconu na selected
     */
    private void checkBoxesFunction() {
        for (int index = 0; index < this.checkBoxes.length; index++) {
            if (this.checkBoxes[index].wasSelected() && this.mouseInput.getWasClicked()) {
                if (this.mouseInput.getPosY() >= this.mouseInput.getPosYReleased() ||
                    this.mouseInput.getPosX() >= this.mouseInput.getPosXReleased()) {
                    for (int i = this.mouseInput.getPosY(); i >= this.mouseInput.getPosYReleased(); i--) {
                        for (int j = this.mouseInput.getPosX(); j >= this.mouseInput.getPosXReleased(); j--) {
                            this.grid.getGrid()
                                [i][j] = CellType.values()[index];
                        }
                    }
                }
                if (this.mouseInput.getPosY() <= this.mouseInput.getPosYReleased() ||
                    this.mouseInput.getPosX() <= this.mouseInput.getPosXReleased()) {
                    for (int i = this.mouseInput.getPosY(); i <= this.mouseInput.getPosYReleased(); i++) {
                        for (int j = this.mouseInput.getPosX(); j <= this.mouseInput.getPosXReleased(); j++) {
                            this.grid.getGrid()
                                [i][j] = CellType.values()[index];
                        }
                    }
                }
                if (this.mouseInput.getPosY() <= this.mouseInput.getPosYReleased() ||
                    this.mouseInput.getPosX() >= this.mouseInput.getPosXReleased()) {
                    for (int i = this.mouseInput.getPosY(); i <= this.mouseInput.getPosYReleased(); i++) {
                        for (int j = this.mouseInput.getPosX(); j >= this.mouseInput.getPosXReleased(); j--) {
                            this.grid.getGrid()
                                [i][j] = CellType.values()[index];
                        }
                    }
                }
                if (this.mouseInput.getPosY() >= this.mouseInput.getPosYReleased() ||
                    this.mouseInput.getPosX() <= this.mouseInput.getPosXReleased()) {
                    for (int i = this.mouseInput.getPosY(); i >= this.mouseInput.getPosYReleased(); i--) {
                        for (int j = this.mouseInput.getPosX(); j <= this.mouseInput.getPosXReleased(); j++) {
                            this.grid.getGrid()
                                [i][j] = CellType.values()[index];
                        }
                    }
                }
                this.resetXYPos();
                /*
                todo inac to neviem vyriesit
                 */
            } else if (!this.checkBoxes[0].wasSelected()
                && !this.checkBoxes[1].wasSelected()
                && !this.checkBoxes[2].wasSelected()
                && !this.checkBoxes[3].wasSelected()
                && !this.checkBoxes[4].wasSelected()
                && !this.checkBoxes[5].wasSelected()
                && !this.checkBoxes[6].wasSelected()) {
                this.resetXYPos();
            }
            this.checkBoxes[index].setLook();
        }
    }

    /**
     * Metoda pridava do Listenerov moju triedu MouseInput
     * Oddelene od konstruktora pre citatelnost.
     */
    private void setMouseInputs() {
        this.addMouseListener(this.mouseInput);
        this.addMouseMotionListener(this.mouseInput);
    }

    private void resetXYPos() {
        this.mouseInput.setPosX(0);
        this.mouseInput.setPosY(0);
        this.mouseInput.setPosXReleased(0);
        this.mouseInput.setPosYReleased(0);
        this.grid.getGrid()[0][0] = CellType.values()[0];
    }
}
