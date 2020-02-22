/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taskbag_1418295_1428585;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Implementação da bolsa de tarefas
 *
 * @author daniel/lucio
 */
public class TaskBagImpl implements TaskBag {

    private int id_task;
    private int id_worker;
    private int id_owner;
    private ArrayList<ObjectTask> tasks;

    public TaskBagImpl() {
        this.id_task = 0;
        this.id_worker = 0;
        this.tasks = new ArrayList<ObjectTask>();
    }

    @Override
    /**
     * Pega o maior identificador de tarefa cadastrada na bolsa de tarefas
     *
     * @return Maior identificador de tarefa cadastrada na bolsa de tarefas
     * @throws RemoteException Refere-se a um erro ocorrido ou no servidor, ou
     * uma chamada a uma função que não é compartilhada entre o cliente e o
     * servidor, ou até um erro ao empacotar ou desempacotar um objeto
     */
    public int getMaxId_task() throws RemoteException {
        return this.id_task;
    }

    @Override
    /**
     * Pega o maior identificador de proprietário cadastrado na bolsa de tarefas
     *
     * @return Maior identificador de proprietário cadastrado na bolsa de tarefas
     * @throws RemoteException Refere-se a um erro ocorrido ou no servidor, ou
     * uma chamada a uma função que não é compartilhada entre o cliente e o
     * servidor, ou até um erro ao empacotar ou desempacotar um objeto
     */
    public int getMaxId_owner() throws RemoteException {
        return this.id_owner;
    }

    @Override
    /**
     * Pega o maior identificador de máquina trabalhadora cadastrada na bolsa de
     * tarefas
     *
     * @return Maior identificador de máquina trabalhadora cadastrada na bolsa
     * de tarefas
     * @throws RemoteException Refere-se a um erro ocorrido ou no servidor, ou
     * uma chamada a uma função que não é compartilhada entre o cliente e o
     * servidor, ou até um erro ao empacotar ou desempacotar um objeto
     */
    public int getMaxId_worker() throws RemoteException {
        return this.id_worker;
    }

    @Override
    /**
     * Pega a quantidade de tarefas que já foram finalizadas daquele
     * proprietário
     *
     * @param id_owner Identificador do proprietário
     * @return Quantidade de tarefas finalizadas do proprietário em questão
     * @throws RemoteException Refere-se a um erro ocorrido ou no servidor, ou
     * uma chamada a uma função que não é compartilhada entre o cliente e o
     * servidor, ou até um erro ao empacotar ou desempacotar um objeto
     */
    public int getSizeTasksOk(int id_owner) throws RemoteException {
        int cont = 0;
        for (ObjectTask t : tasks) { // Percorre toda lista de tarefas
            // Captura a quantidade de tarefas do proprietario em questão, e que foram concluidas
            if (t.getId_worker() == id_worker && t.getIs_finally() == true) {
                cont++;
            }
        }
        return cont;
    }

    @Override
    /**
     * Pega a quantidade de tarefas pendentes a serem realizadas na bolsa de
     * tarefas
     *
     * @return Quantidade de tarefas pendentes
     * @throws RemoteException Refere-se a um erro ocorrido ou no servidor, ou
     * uma chamada a uma função que não é compartilhada entre o cliente e o
     * servidor, ou até um erro ao empacotar ou desempacotar um objeto
     */
    public int getSizeTasksPending() throws RemoteException {
        int cont = 0;
        for (ObjectTask t : tasks) { // Percorre toda lista de tarefas
            if (t.getIs_finally() == false) { // Captura a quantidade de tarefas pendentes
                cont++;
            }
        }
        return cont;
    }

    @Override
    /**
     * Pega a próxima tarefa a ser realizada na bolsa e define qual trabalhador
     * que pegou a tarefa para desenvolver
     *
     * @param id_worker Identificador do trabalhador
     * @return Tarefa a ser realizada
     * @throws RemoteException Refere-se a um erro ocorrido ou no servidor, ou
     * uma chamada a uma função que não é compartilhada entre o cliente e o
     * servidor, ou até um erro ao empacotar ou desempacotar um objeto
     */
    public ObjectTask getProxTask(int id_worker) throws RemoteException {
        for (ObjectTask t : tasks) { // Percorre toda lista de tarefas
            if (t.getId_worker() == -1) { // Captura apenas as tarefas que não tem trabalhador
                t.setId_worker(id_worker); // Define que esse trabalhador é quem vai realizar a tarefa
                return t; // Retorna a tarefa
            }
        }
        return null;
    }

    @Override
    /**
     * Adiciona uma tarefa a bolsa de tarefas
     *
     * @param task Tarefa a ser inserida na bolsa de tarefas
     * @return Identificador da tarefa inserida
     * @throws RemoteException Refere-se a um erro ocorrido ou no servidor, ou
     * uma chamada a uma função que não é compartilhada entre o cliente e o
     * servidor, ou até um erro ao empacotar ou desempacotar um objeto
     */
    public int addTask(ObjectTask task) throws RemoteException {
        task.setId_task(++id_task); // Seta o id da tarefa
        tasks.add(task);
        System.out.println("Add task... ");
        return id_task; // Retorna o id da tarefa
    }

    @Override
    /**
     * Remove uma tarefa da bolsa de tarefas
     *
     * @param id_task Identificador da tarefa
     * @return Tarefa removida
     * @throws RemoteException Refere-se a um erro ocorrido ou no servidor, ou
     * uma chamada a uma função que não é compartilhada entre o cliente e o
     * servidor, ou até um erro ao empacotar ou desempacotar um objeto
     */
    public ObjectTask removeTask(int id_task) throws RemoteException {
        ObjectTask t = tasks.get(id_task); // Guarda a tarefa
        tasks.remove(id_task);  // Apaga da lista de tarefas
        return t; // Retorna a tarefa
    }

    /**
     * Pega todas as tarefas de um proprietário
     *
     * @param id_owner Identificador do proprietário
     * @return Lista de tarefas ordenadas pelos seus identificadores
     * @throws RemoteException Refere-se a um erro ocorrido ou no servidor, ou
     * uma chamada a uma função que não é compartilhada entre o cliente e o
     * servidor, ou até um erro ao empacotar ou desempacotar um objeto
     */
    @Override
    public ArrayList<ObjectTask> readTasks(int id_owner) throws RemoteException {
        ArrayList<ObjectTask> task = new ArrayList<ObjectTask>();
        for (ObjectTask t : tasks) { // Percorre toda lista de tarefas
            if (t.getId_worker() == id_worker) { // Captura as tarefas daquele proprietario
                task.add(t);
            }
        }
        Collections.sort(tasks); // Ordenar as tarefas por id_task 
        return task;
    }

    @Override
    /**
     * Atualiza uma tarefa
     *
     * @param id_task Identificador da tarefa
     * @param task Tarefa
     * @throws RemoteException Refere-se a um erro ocorrido ou no servidor, ou
     * uma chamada a uma função que não é compartilhada entre o cliente e o
     * servidor, ou até um erro ao empacotar ou desempacotar um objeto
     */
    public void refreshTask(int id_task, ObjectTask task) throws RemoteException {
        task.setIs_running(false); // Não está mais rodando
        tasks.set(id_task, task); // Atualiza a tarefa com aquele id para o novo objeto tarefa
    }

    @Override
    /**
     * Testa se o proprietário ainda tem tarefas a serem realizadas na bolsa, ou
     * se todas já foram finalizadas
     *
     * @param id_owner Identificador do proprietário
     * @return Booleano identificando se tem ou não tem tarefas a serem
     * realizadas que são do tal proprietário
     * @throws RemoteException Refere-se a um erro ocorrido ou no servidor, ou
     * uma chamada a uma função que não é compartilhada entre o cliente e o
     * servidor, ou até um erro ao empacotar ou desempacotar um objeto
     */
    public boolean isEmptyTask(int id_owner) throws RemoteException {
        for (ObjectTask t : tasks) { // Percorre toda lista de tarefas
            if (t.getId_owner() == id_owner && t.getIs_finally() == false) { // Captura apenas a tarefa daquele dono que não foi finalizada
                return false; // Falta alguma(s) tarefa(s) a ser executada(s)
            }
        }
        return true; // Todas tarefas já foram executadas
    }

    @Override
    /**
     * Apaga todas as tarefas de um proprietário da bolsa
     *
     * @param id_owner Identificador do proprietário
     * @throws RemoteException Refere-se a um erro ocorrido ou no servidor, ou
     * uma chamada a uma função que não é compartilhada entre o cliente e o
     * servidor, ou até um erro ao empacotar ou desempacotar um objeto
     */
    public void clearTask(int id_owner) throws RemoteException {
        for (ObjectTask t : tasks) { // Percorre toda lista de tarefas
            if (t.getId_owner() == id_owner) { // Captura apenas as tarefas daquele dono
                tasks.remove(t); // Remove do arrayList a tarefa
            }
        }
    }

    @Override
    /**
     * Finaliza uma tarefa de um proprietário
     *
     * @param id_task Identificador da tarefa
     * @param id_owner Identificador do proprietário
     * @throws RemoteException Refere-se a um erro ocorrido ou no servidor, ou
     * uma chamada a uma função que não é compartilhada entre o cliente e o
     * servidor, ou até um erro ao empacotar ou desempacotar um objeto
     */
    public void finallyTask(int id_task, int id_owner) throws RemoteException {
        for (ObjectTask t : tasks) { // Percorre toda lista de tarefas
            if (t.getId_owner() == id_owner && t.getId_task() == id_task) { // Captura apenas a tarefa daquele dono com aquele id
                tasks.get(id_task).setIs_finally(true); // Seta ele como finalizado
            }
        }
    }

}
