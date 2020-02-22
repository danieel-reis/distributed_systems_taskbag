Compilar arquivos
    Em /rmi/taskbag_1418295_1428585
	Terminal: javac *.java

Servidor
    Em /rmi:
	Terminal: java -Djava.rmi.server.codebase=file:/rmi/taskbag_1418295_1428585. -cp .. taskbag_1418295_1428585.MainServer PORTA &


Cliente
    Em /rmi
	Terminal: java taskbag_1418295_1428585.MainClient host porta type flag sizeLineMatrizA sizeColumnMatrizALineMatrizB sizeColumnMatrizB
	Obs: sizeColumnMatrizA = sizeLineMatrizB para multiplicar matrizA x matrizB
	    (flag = 0) => cliente vai inserir os dados da matrizA e matrizB
	    (flag = 1) => cliente vai deixar que a matrizA e matrizB sejam geradas automaticamente
	    

Máquina trabalhadora
    Em /rmi
	Terminal: java taskbag_1418295_1428585.MainWorkingMachine host porta
