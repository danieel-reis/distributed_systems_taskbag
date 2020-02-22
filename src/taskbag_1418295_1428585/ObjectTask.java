/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taskbag_1418295_1428585;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Objeto criado para representar uma tarefa. Basicamente, uma tarefa tem seu
 * identificador e está relacionada a um proprietario, um trabalhador que vai
 * realizar a tarefa. Para isso, tem um booleano pra saber se está sendo
 * executada e um booleano pra saber se foi terminada a tarefa. Uma tarefa, é
 * basicamente a referência de qual linha atual da matrizA e coluna atual da
 * matrizB será multiplicada, além de todas células pertencentes a linha da
 * matrizA e todas células pertencentes a coluna da matrizB. Desse modo, valor
 * se refere ao resultado da multiplição da linha da matrizA pela coluna da
 * matrizB
 *
 * @author daniel/lucio
 */
public class ObjectTask implements Serializable, Comparable<ObjectTask> {

    private int id_task;
    private int id_owner;
    private int id_worker;
    private boolean is_running;
    private boolean is_finally;
    private int currentLine;
    private int currentColumn;
    private ArrayList<ObjectCell> line;
    private ArrayList<ObjectCell> column;
    private double value_multiplication;

    public ObjectTask(int id_task, int id_owner, int currentLine, int currentColumn, ArrayList<ObjectCell> line, ArrayList<ObjectCell> column) {
        this.id_task = id_task;                             // ID da tarefa
        this.id_owner = id_owner;                           // ID do proprietário da tarefa
        this.is_running = false;                            // Inicialmente a tarefa não está sendo executada
        this.is_finally = false;                            // Inicialmente a tarefa não foi finalizada
        this.currentLine = currentLine;                     // Linha atual da matriz
        this.currentColumn = currentColumn;                 // Coluna atual da matriz
        this.line = line;                                   // Linha da matrizA, ou seja, todas células
        this.column = column;                               // Coluna da matrizB, ou seja, todas células
    }

    public int getId_task() {
        return id_task;
    }

    public void setId_task(int id_task) {
        this.id_task = id_task;
    }

    public int getId_owner() {
        return id_owner;
    }

    public void setId_owner(int id_owner) {
        this.id_owner = id_owner;
    }

    public int getId_worker() {
        return id_worker;
    }

    public void setId_worker(int id_worker) {
        this.id_worker = id_worker;
    }

    public boolean getIs_running() {
        return is_running;
    }

    public void setIs_running(boolean is_running) {
        this.is_running = is_running;
    }

    public boolean getIs_finally() {
        return is_finally;
    }

    public void setIs_finally(boolean is_finally) {
        this.is_finally = is_finally;
    }

    public int getCurrentLine() {
        return currentLine;
    }

    public void setCurrentLine(int currentLine) {
        this.currentLine = currentLine;
    }

    public int getCurrentColumn() {
        return currentColumn;
    }

    public void setCurrentColumn(int currentColumn) {
        this.currentColumn = currentColumn;
    }

    public ArrayList<ObjectCell> getLine() {
        return line;
    }

    public void setLine(ArrayList<ObjectCell> line) {
        this.line = line;
    }

    public ArrayList<ObjectCell> getColumn() {
        return column;
    }

    public void setColumn(ArrayList<ObjectCell> column) {
        this.column = column;
    }

    public double getValue_multiplication() {
        return value_multiplication;
    }

    public void setValue_multiplication(double value_multiplication) {
        this.value_multiplication = value_multiplication;
    }

    @Override
    public int compareTo(ObjectTask o) {
        if (this.getId_task() < o.getId_task()) {
            return -1;
        }
        if (this.getId_task() > o.getId_task()) {
            return 1;
        }
        return 0;
    }

}
