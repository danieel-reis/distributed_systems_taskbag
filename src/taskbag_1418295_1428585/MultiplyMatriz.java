/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taskbag_1418295_1428585;

import java.io.Serializable;
import java.rmi.RemoteException;

/**
 * Executa uma tarefa da bolsa de tarefas. Desse modo, se recebe um objeto de
 * uma tarefa, que tem todos os dados referentes a uma tarefa. Desse modo, o que
 * se faz é percorrer a linha da matrizA e multiplica por cada coluna da
 * matrizB, de modo a ir somando os resultados. Por fim, o que se faz é
 * atualizar esse resultado na bolsa de tarefas, além de setar a tarefa como
 * finalizada
 *
 * @author daniel/lucio
 */
public class MultiplyMatriz implements Serializable {

    /**
     * Executa uma tarefa
     *
     * @param taskBag Objeto remoto
     * @param task Tarefa a ser executada
     */
    public void execute(TaskBag taskBag, ObjectTask task) { // Executa a tarefa
        double value = 0;
        for (int l = 0; l < task.getLine().size(); l++) {
            for (int c = 0; c < task.getColumn().size(); c++) {
                value += task.getLine().get(l).getValue() * task.getColumn().get(c).getValue();
            }
        }
        task.setValue_multiplication(value); // Seta o valor do resultado

        try {
            taskBag.refreshTask(task.getId_task(), task); // Atualiza
            taskBag.finallyTask(task.getId_task(), task.getId_owner());// Finaliza
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }
}
