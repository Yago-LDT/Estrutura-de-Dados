package aula6;

public class ArvoreBinaria {
    No raiz;
    
    public void inserir(int valor){
        raiz = inserirRec(raiz, valor);
    }
    
    private No inserirRec(No raiz, int valor){
        if(raiz == null){
            raiz = new No(valor);
            return raiz;
        }
        if(valor < raiz.valor){
            raiz.esquerda = inserirRec(raiz.esquerda, valor);
        }else if(valor > raiz.valor){
            raiz.direita = inserirRec(raiz.direita, valor);
        }
        
        return raiz;
    }
    
    public void inOrder(No raiz){
        if(raiz != null){
            inOrder(raiz.esquerda);
            System.out.println(raiz.valor + "");
            inOrder(raiz.direita);
        }
    }
}
