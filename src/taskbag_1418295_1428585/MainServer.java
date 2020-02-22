/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taskbag_1418295_1428585;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Implementação do servidor
 *
 * @author daniel/lucio
 */
public class MainServer {

    /**
     * Programa servidor
     *
     * @param args Argumentos do programa servidor
     */
    public static void main(String[] args) {
//        System.out.println("Insert the parameter: porta");
//
        try {
//            if (args.length != 1) {
//                System.out.println("Parameter error!");
//            } else {
                TaskBagImpl taskBag = new TaskBagImpl();
                TaskBag stub = (TaskBag) UnicastRemoteObject.exportObject(taskBag, 0);

                Registry registry = LocateRegistry.getRegistry(Integer.valueOf(args[0].trim()));
                registry.bind("TaskBagMM", stub);

                System.err.println("Servidor TaskBagMM rodando...");
//            }
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }

    }

}
