package taskbag_1418295_1428585;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Interface que define as funções compartilhadas entre o servidor e o cliente
 *
 * @author daniel/lucio
 */
public interface TaskBag extends Remote {

    /**
     * Pega o maior identificador de tarefa cadastrada na bolsa de tarefas
     *
     * @return Maior identificador de tarefa cadastrada na bolsa de tarefas
     * @throws RemoteException Refere-se a um erro ocorrido ou no servidor, ou
     * uma chamada a uma função que não é compartilhada entre o cliente e o
     * servidor, ou até um erro ao empacotar ou desempacotar um objeto
     */
    public int getMaxId_task() throws RemoteException;

    /**
     * Pega o maior identificador de proprietário cadastrado na bolsa de tarefas
     *
     * @return Maior identificador de proprietário cadastrado na bolsa de tarefas
     * @throws RemoteException Refere-se a um erro ocorrido ou no servidor, ou
     * uma chamada a uma função que não é compartilhada entre o cliente e o
     * servidor, ou até um erro ao empacotar ou desempacotar um objeto
     */
    public int getMaxId_owner() throws RemoteException;

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
    public int getMaxId_worker() throws RemoteException;

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
    public int getSizeTasksOk(int id_owner) throws RemoteException;

    /**
     * Pega a quantidade de tarefas pendentes a serem realizadas na bolsa de
     * tarefas
     *
     * @return Quantidade de tarefas pendentes
     * @throws RemoteException Refere-se a um erro ocorrido ou no servidor, ou
     * uma chamada a uma função que não é compartilhada entre o cliente e o
     * servidor, ou até um erro ao empacotar ou desempacotar um objeto
     */
    public int getSizeTasksPending() throws RemoteException;

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
    public ObjectTask getProxTask(int id_worker) throws RemoteException;

    /**
     * Adiciona uma tarefa a bolsa de tarefas
     *
     * @param task Tarefa a ser inserida na bolsa de tarefas
     * @return Identificador da tarefa inserida
     * @throws RemoteException Refere-se a um erro ocorrido ou no servidor, ou
     * uma chamada a uma função que não é compartilhada entre o cliente e o
     * servidor, ou até um erro ao empacotar ou desempacotar um objeto
     */
    public int addTask(ObjectTask task) throws RemoteException;

    /**
     * Remove uma tarefa da bolsa de tarefas
     *
     * @param id_task Identificador da tarefa
     * @return Tarefa removida
     * @throws RemoteException Refere-se a um erro ocorrido ou no servidor, ou
     * uma chamada a uma função que não é compartilhada entre o cliente e o
     * servidor, ou até um erro ao empacotar ou desempacotar um objeto
     */
    public ObjectTask removeTask(int id_task) throws RemoteException;

    /**
     * Pega todas as tarefas de um proprietário
     *
     * @param id_owner Identificador do proprietário
     * @return Lista de tarefas ordenadas pelos seus identificadores
     * @throws RemoteException Refere-se a um erro ocorrido ou no servidor, ou
     * uma chamada a uma função que não é compartilhada entre o cliente e o
     * servidor, ou até um erro ao empacotar ou desempacotar um objeto
     */
    public ArrayList<ObjectTask> readTasks(int id_owner) throws RemoteException;

    /**
     * Atualiza uma tarefa
     *
     * @param id_task Identificador da tarefa
     * @param task Tarefa
     * @throws RemoteException Refere-se a um erro ocorrido ou no servidor, ou
     * uma chamada a uma função que não é compartilhada entre o cliente e o
     * servidor, ou até um erro ao empacotar ou desempacotar um objeto
     */
    public void refreshTask(int id_task, ObjectTask task) throws RemoteException;

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
    public boolean isEmptyTask(int id_owner) throws RemoteException;

    /**
     * Apaga todas as tarefas de um proprietário da bolsa
     *
     * @param id_owner Identificador do proprietário
     * @throws RemoteException Refere-se a um erro ocorrido ou no servidor, ou
     * uma chamada a uma função que não é compartilhada entre o cliente e o
     * servidor, ou até um erro ao empacotar ou desempacotar um objeto
     */
    public void clearTask(int id_owner) throws RemoteException;

    /**
     * Finaliza uma tarefa de um proprietário
     *
     * @param id_task Identificador da tarefa
     * @param id_owner Identificador do proprietário
     * @throws RemoteException Refere-se a um erro ocorrido ou no servidor, ou
     * uma chamada a uma função que não é compartilhada entre o cliente e o
     * servidor, ou até um erro ao empacotar ou desempacotar um objeto
     */
    public void finallyTask(int id_task, int id_owner) throws RemoteException;

}
