package atendimento;

import java.util.LinkedList;


public class Fila {
    private Pacientes paciente;
    
    private LinkedList<Pacientes> lista = new LinkedList<>();
    
    public void enqueue(Pacientes p){
        //desenvolver método que verifica prioridade e posiciona em
        //em primeiro ou último do lista
       lista.addLast(p);
        }
    
    public Pacientes dequeue(){
        if(lista.isEmpty()){
            System.out.println("A lista está vazia!");
        return null;
        }
        return lista.remove();
    }
    
    public void exibir(){
        System.out.println(lista);
    }
    
}
