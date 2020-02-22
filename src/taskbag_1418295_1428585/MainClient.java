/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taskbag_1418295_1428585;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Implementação do cliente
 *
 * @author daniel/lucio
 */
public class MainClient {

    private static Scanner in;

    /**
     * Permite a impressão dos dados de uma matriz
     *
     * @param size_line Quantidade de linhas da matriz
     * @param size_column Quantidade de colunas da matriz
     * @param matriz Matriz de dados
     */
    public static void printMatriz(int size_line, int size_column, ObjectMatriz matriz) {
        for (int l = 0; l < size_line; l++) {
            for (int c = 0; c < size_column; c++) {
                System.out.print(matriz.getLine(l).get(c).getValue() + " ");
            }
            System.out.println("");
        }
    }

    /**
     * Permite gerar automaticamente os dados de uma matriz
     *
     * @param size_line Quantidade de linhas da matriz
     * @param size_column Quantidade de colunas da matriz
     * @return Matriz gerada
     */
    public static ObjectMatriz generatorMatriz(int size_line, int size_column) {
        Random r = new Random(1000);
        ArrayList<ObjectCell> cell = new ArrayList<ObjectCell>();

        for (int l = 0; l < size_line; l++) {
            for (int c = 0; c < size_column; c++) {
                cell.add(new ObjectCell(l, c, r.nextFloat()));
            }
        }
        return new ObjectMatriz(size_line, size_column, cell);
    }

    /**
     * Permite ler os dados de uma matriz
     *
     * @param size_line Quantidade de linhas da matriz
     * @param size_column Quantidade de colunas da matriz
     * @return Matriz preenchida
     */
    public static ObjectMatriz readMatriz(int size_line, int size_column) {
        ArrayList<ObjectCell> cell = new ArrayList<ObjectCell>();

        for (int l = 0; l < size_line; l++) {
            for (int c = 0; c < size_column; c++) {
                System.out.printf("Insert the value in position [" + l + "][ " + c + "]: ");
                cell.add(new ObjectCell(l, c, in.nextDouble()));
            }
        }
        return new ObjectMatriz(size_line, size_column, cell);
    }

    /**
     * Permite realizar a multiplicacao de uma matriz pela outra de forma local,
     * sem enviar nada a bolsa de tarefas.
     *
     * @param matrizA MatrizA
     * @param matrizB MatrizB
     * @return MatrizA x MatrizB
     */
    public static ObjectMatriz multiplyLocal(ObjectMatriz matrizA, ObjectMatriz matrizB) {
        ArrayList<ObjectCell> cell = new ArrayList<ObjectCell>();
        double value;
        for (int l = 0; l < matrizA.getSize_line(); l++) {
            for (int c = 0; c < matrizB.getSize_column(); c++) {
                value = 0;
                // Para cada linha da matrizA x coluna da matrizB, percorre ambas linhas e retorna a multiplicação entre ambos
                for (int l1 = 0; l1 < matrizA.getSize_line(); l1++) {
                    for (int c1 = 0; c1 < matrizB.getSize_column(); c1++) {
                        value += matrizA.getLine(l).get(l1).getValue() * matrizB.getColumn(c).get(c1).getValue();
                    }
                }
                cell.add(new ObjectCell(l, c, value));
            }
        }
        return new ObjectMatriz(matrizA.getSize_line(), matrizB.getSize_column(), cell);
    }

    /**
     * Pega os dados das matrizes lidas e insere várias tarefas. Dessa maneira,
     * cada tarefa inserida na bolsa equivale a uma linha da matrizA x uma
     * coluna da matrizB.
     *
     * @param taskBag Objeto remoto
     * @param id_owner Identificador do proprietário da tarefa
     * @param matrizA MatrizA
     * @param matrizB MatrizB
     * @return Quantidade de tarefas inseridas na bolsa
     * @throws RemoteException Refere-se a um erro ocorrido ou no servidor, ou
     * uma chamada a uma função que não é compartilhada entre o cliente e o
     * servidor, ou até um erro ao empacotar ou desempacotar um objeto
     */
    public static int insertTaskInTaskBag(TaskBag taskBag, int id_owner, ObjectMatriz matrizA, ObjectMatriz matrizB) throws RemoteException {
        int cont = 0;
        for (int l = 0; l < matrizA.getSize_line(); l++) {
            for (int c = 0; c < matrizB.getSize_column(); c++) {
                taskBag.addTask(new ObjectTask(taskBag.getMaxId_task() + 1, id_owner, l, c, matrizA.getLine(l), matrizB.getColumn(c)));
                cont++;
            }
        }
        return cont;
    }

    /**
     * Pega as tarefas inseridas na bolsa pelo proprietário tal, de modo que
     * como cada tarefa inserida equivale a uma linha da matrizA x uma coluna da
     * matrizB, então por fim, pega essas tarefas e monta a matrizA x matrizB,
     * pois tem a referência de qual linha ta sendo multiplicada por qual
     * coluna, onde seja, em qual posição ficará na nova matriz.
     *
     * @param taskBag Objeto remoto
     * @param id_owner Identificador do proprietário da tarefa
     * @param size_line Quantidade de linhas da matrizAxB
     * @param size_column Quantidade de colunas da matrizAxB
     * @return MatrizAxB
     */
    public static ObjectMatriz getMatrizAxB(TaskBag taskBag, int id_owner, int size_line, int size_column) {
        ArrayList<ObjectCell> cell = new ArrayList<ObjectCell>();
        ArrayList<ObjectTask> tasks;
        try {
            tasks = taskBag.readTasks(id_owner);
            for (ObjectTask t : tasks) {
                cell.add(new ObjectCell(t.getCurrentLine(), t.getCurrentColumn(), t.getValue_multiplication()));
            }

        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
        return new ObjectMatriz(size_line, size_column, cell);
    }

    /**
     * Programa cliente
     *
     * @param args Argumentos do programa cliente
     * @throws InterruptedException Exceção lançada quando uma Thread está
     * aguardando, dormindo ou pausada e uma outra Thread a interrompe
     */
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Insert the 6 parameters: host porta flag sizeLineMatrizA sizeColumnMatrizALineMatrizB sizeColumnMatrizB");
        System.out.println("Observation: sizeColumnMatrizA = sizeLineMatrizB for multiply matrizA x matrizB and (flag = 0) => "
                + "read matriz or (flag = 1) => generator matriz");

        in = new Scanner(System.in);

        ObjectMatriz matrizA = new ObjectMatriz();
        ObjectMatriz matrizB = new ObjectMatriz();

        if (args.length != 6) {
            System.out.println("Parameter error!");
        } else {
            String host = args[0].trim();
            int porta = Integer.parseInt(args[1].trim());
            // Se 0 => ler, se 1 => gerar automatico
            int readOrGeneratorMatriz = Integer.parseInt(args[2].trim());
            int size_line_matrizA = Integer.parseInt(args[3].trim());
            int size_column_matrizA = Integer.parseInt(args[4].trim());
            int size_line_matrizB = size_column_matrizA;
            int size_column_matrizB = Integer.parseInt(args[5].trim());

            if (readOrGeneratorMatriz != 0 && readOrGeneratorMatriz != 1) {
                System.out.println("Parameter error!");
            } else {

                try {
                    Registry registry = LocateRegistry.getRegistry(host, porta);
                    TaskBag taskBag = (TaskBag) registry.lookup("TaskBagMM");

                    if (readOrGeneratorMatriz == 0) {
                        System.out.println("\n-------------------- Insert data matrizA --------------------");
                        matrizA = readMatriz(size_line_matrizA, size_column_matrizA);
                        System.out.println("\n\n-------------------- Insert data matrizB --------------------");
                        matrizB = readMatriz(size_line_matrizB, size_column_matrizB);

                    } else if (readOrGeneratorMatriz == 1) {
                        System.out.println("\n-------------------- Generator data matrizA --------------------");
                        matrizA = generatorMatriz(size_line_matrizA, size_column_matrizA);
                        System.out.println("\n\n-------------------- Generator data matrizB --------------------");
                        matrizB = generatorMatriz(size_line_matrizB, size_column_matrizB);
                    }

                    System.out.printf("\n\n----------------------------- Data -------------------------------");
                    System.out.println("\nMatriz A");
                    printMatriz(size_line_matrizA, size_column_matrizA, matrizA);
                    System.out.println("\nMatriz B");
                    printMatriz(size_line_matrizB, size_column_matrizB, matrizB);

                    // Running using working machine
                    long startTime = System.currentTimeMillis();
                    System.out.println("Insert tasks in taskBag");
                    int id_owner = taskBag.getMaxId_owner();
                    int qtdTask = insertTaskInTaskBag(taskBag, id_owner, matrizA, matrizB);

                    while (!taskBag.isEmptyTask(id_owner)) { // A cada um segundo verifica se as tarefas do proprietario tal já foram executadas
                        System.out.println("Please wait... " + taskBag.getSizeTasksOk(id_owner) + " / " + qtdTask);
                        Thread.sleep(1000);
                    }

                    long endTime = System.currentTimeMillis();
                    System.out.println("\n\n------------------- Multiply matrizA x matrizB --------------------");
                    printMatriz(size_line_matrizA, size_column_matrizB, getMatrizAxB(taskBag, id_owner, size_line_matrizA, size_column_matrizB));
                    System.out.println("Runtime = " + (endTime - startTime) + " ms");

                    // Running local
                    long startTime1 = System.currentTimeMillis();
                    System.out.println("Running local...");
                    ObjectMatriz matrizAxB = multiplyLocal(matrizA, matrizB);
                    long endTime1 = System.currentTimeMillis();
                    System.out.println("\n\n------------------- Multiply matrizA x matrizB --------------------");
                    printMatriz(size_line_matrizA, size_column_matrizB, matrizAxB);
                    System.out.println("Runtime = " + (endTime1 - startTime1) + " ms");

                } catch (RemoteException e) {
                    e.printStackTrace();
                } catch (NotBoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
