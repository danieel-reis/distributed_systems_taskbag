package taskbag_1418295_1428585;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Objeto criado para representar uma matriz. Basicamente, uma matriz pode ser
 * denominada como um conjunto de células que tem sua indenficação de linha e
 * coluna da matriz
 *
 * @author daniel/lucio
 */
public class ObjectMatriz implements Serializable {

    private int size_line;
    private int size_column;
    private ArrayList<ObjectCell> cell;

    public ObjectMatriz() {
    }

    public ObjectMatriz(int size_line, int size_column, ArrayList<ObjectCell> cell) {
        this.size_line = size_line;
        this.size_column = size_column;
        this.cell = cell;
    }

    /**
     * Captura todas as células de uma dada linha da matriz.
     *
     * @param line Linha da matriz
     * @return Array de células pertencentes a linha procurada
     */
    public ArrayList<ObjectCell> getLine(int line) {
        ArrayList<ObjectCell> lines = new ArrayList<ObjectCell>();
        int ini = size_column * line;
        int fim = ini + size_column;
        for (int l = ini; l < fim; l++) {
            lines.add(cell.get(l));
        }
        return lines;
    }

    /**
     * Captura todas as células de uma dada linha da matriz.
     *
     * @param column Coluna da matriz
     * @return Array de células pertencentes a coluna procurada
     */
    public ArrayList<ObjectCell> getColumn(int column) {
        ArrayList<ObjectCell> lines = new ArrayList<ObjectCell>();
        int pos = column;
        for (int l = 0; l < size_column; l++) {
            lines.add(cell.get(pos));
            pos += size_column;
        }
        return lines;
    }

    public int getSize_line() {
        return size_line;
    }

    public void setSize_line(int size_line) {
        this.size_line = size_line;
    }

    public int getSize_column() {
        return size_column;
    }

    public void setSize_column(int size_column) {
        this.size_column = size_column;
    }

    public ArrayList<ObjectCell> getCell() {
        return cell;
    }

    public void setCell(ArrayList<ObjectCell> cell) {
        this.cell = cell;
    }

}
