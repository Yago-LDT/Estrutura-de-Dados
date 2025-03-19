package aula5;

public class Fila {
    private int frente, tras, tamanho;
    private int capacidade;
    private int[] elementos;
 
    public Fila(int c){
        this.capacidade = c;
        elementos = new int[capacidade];
        frente = 0;
        tras = -1;
        tamanho = 0;
    }
    
    public void enqueue(int elemento){
        if(tamanho == capacidade){
            System.out.println("Fila cheia!");
            return;
        }
        
        tras = (tras + 1) % capacidade;
        elementos[tras] = elemento;
        tamanho++;
    }
    
    public int dequeue(){
        if(tamanho == 0){
            System.out.println("A fila está vazia!");
            return -1;
        }
        int removido = elementos[frente];
        frente = (frente +1)% capacidade;
        tamanho--;
        return removido;
    }
    
    public boolean isEmpty(){
        return tamanho == 0;
    }
    
    public void exibir(){
        for(int i = 0; i < tamanho; i++){
            System.out.print(elementos[(frente + i) % capacidade] + " ");
            // fazer a soma de frente + i enre aspas, todo o cálculo dentro de chaves.
        }
        System.out.println("");
        
    }
    
    public int peek(){
        if(tamanho == 0){
            System.out.println("A fila está vazia!");
            return -1;
           }
        return elementos[frente];
    }
    public static void main(String args[]){
        
        Fila lista1 = new Fila(10);
        lista1.enqueue(10);
        lista1.enqueue(20);
        lista1.enqueue(30);
        lista1.exibir();
        lista1.dequeue();
        lista1.exibir();
        
    }
}
