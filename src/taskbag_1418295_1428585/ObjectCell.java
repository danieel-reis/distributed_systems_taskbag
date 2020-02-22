package taskbag_1418295_1428585;

import java.io.Serializable;

/**
 * Objeto que representa uma célula de uma matriz. Assim, uma célula ta ligada a
 * uma linha e uma coluna da matriz, além de ter um valor próprio
 *
 * @author daniel/lucio
 */
public class ObjectCell implements Serializable {

    private int line;
    private int column;
    private double value;

    public ObjectCell(int line, int column, double value) {
        this.line = line;
        this.column = column;
        this.value = value;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

}
