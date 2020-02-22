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

/**
 * Implementação da máquina trabalhadora
 *
 * @author daniel/lucio
 */
public class MainWorkingMachine {

    /**
     * Programa máquina trabalhadora
     *
     * @param args Argumentos da máquina trabalhadora
     *
     * @throws RemoteException Refere-se a um erro ocorrido ou no servidor, ou
     * uma chamada a uma função que não é compartilhada entre o cliente e o
     * servidor, ou até um erro ao empacotar ou desempacotar um objeto
     * @throws NotBoundException Exceção que indica que o serviço remoto não
     * está registrado
     * @throws InterruptedException Exceção lançada quando uma Thread está
     * aguardando, dormindo ou pausada e uma outra Thread a interrompe
     */
    public static void main(String args[]) throws RemoteException, NotBoundException, InterruptedException {
        System.out.println("Insert the 2 parameters: host porta");
        if (args.length != 2) {
            System.out.println("Parameter error!");
        } else {
            Registry registry = LocateRegistry.getRegistry(args[0].trim(), Integer.valueOf(args[1].trim()));
            TaskBag stub = (TaskBag) registry.lookup("TaskBagMM");

            int id_worker = stub.getMaxId_worker() + 1;

            while (true) { // Executa enquanto existir tarefas
                ObjectTask task = stub.getProxTask(id_worker);
                if (task != null) {
                    System.out.println("Running... Pending " + stub.getSizeTasksPending());
                    MultiplyMatriz multiplyMatriz = new MultiplyMatriz();
                    multiplyMatriz.execute(stub, task);
                } else { // Se não existir nenhuma tarefa, aguardar 0.1 segundos
                    Thread.sleep(100);
                }
            }
        }
    }

}
