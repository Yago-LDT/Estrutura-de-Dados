import java.util.Collections;
import java.util.PriorityQueue;

public class GerenciadorTarefa {

    public static void main(String[] args) {
        PriorityQueue<Tarefa> tarefas = new PriorityQueue<>();
        
        tarefas.add(new Tarefa("Lavar louça", 3));
        tarefas.add(new Tarefa("Estudar Java", 1));
        tarefas.add(new Tarefa("Fazer compras", 2));
        
        while(!tarefas.isEmpty()){
            System.out.println(tarefas.poll());
        }
    }
}
