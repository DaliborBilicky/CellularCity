package tools;

import enums.CellType;

import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Saver {

    /**
     * Metoda zapisuje typ bunky, i-ty riadok a j-ty stlpec z 2d array.
     * Ignoruje ale prazdne bunky.
     *
     * @param filePath cesta k suboru
     * @param grid     hodnoty ktore metoda zapisuje
     */
    public void saveGame(String filePath, CellType[][] grid) {
        try {
            PrintWriter writer = new PrintWriter(filePath);
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    if (!grid[i][j].name().equals(CellType.EMPTY_CELL.name())) {
                        writer.format("%s;%d;%d%n", grid[i][j], i, j);
                    }
                }
            }
            writer.close();
        } catch (FileNotFoundException exception) {
            /*
            Riadok kodu je totozny s riadkom v triede Image.
             */
            JOptionPane.showMessageDialog(
                null,
                "File " + filePath + " not found.");
        }
    }

    /**
     * Metoda cita zo suboru a rozdeluje riadok podla znaku ";" a zapisuje
     * tieto hodnoty do ArrayListu.
     *
     * @param filePath cesta k suboru z ktoreho cita
     * @return ArrayList
     */
    public ArrayList<String[]> loadGame(String filePath) {
        ArrayList<String[]> file = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(filePath));
            while (scanner.hasNextLine()) {
                file.add(scanner.nextLine().split(";"));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(
                null,
                "File " + filePath + " not found.");
        }
        return file;
    }
}
