public class Tarefa implements Comparable<Tarefa> {
    
    public String nome;
    public int prioridade;
    
    public Tarefa(String n, int p){
        this.nome = n;
        this.prioridade = p;
    }
    
    public int compareTo(Tarefa outra){
        return Integer.compare(this.prioridade, outra.prioridade);
    }
    
    @Override
    public String toString(){
        return nome + " (Prioridade: " + prioridade + " )";
    }
}
