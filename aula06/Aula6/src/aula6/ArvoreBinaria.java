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

     public No buscar(No raiz, int chave) {
    if (raiz == null || raiz.valor == chave) {
        return raiz;
    }

    if (chave < raiz.valor) {
        return buscar(raiz.esquerda, chave);
    }

    return buscar(raiz.direita, chave);
}

    public No remover(No raiz, int valor) {
    if (raiz == null) {
        return raiz;
    }

    if (valor < raiz.valor) {
        raiz.esquerda = remover(raiz.esquerda, valor);
    } else if (valor > raiz.valor) {
        raiz.direita = remover(raiz.direita, valor);
    } else {
        if (raiz.esquerda == null) {
            return raiz.direita;
        } else if (raiz.direita == null) {
            return raiz.esquerda;
        }

        raiz.valor = menorValor(raiz.direita);
        raiz.direita = remover(raiz.direita, raiz.valor);
    }

    return raiz;
}

    private int menorValor(No raiz) {
    int menor = raiz.valor;
    while (raiz.esquerda != null) {
        menor = raiz.esquerda.valor;
        raiz = raiz.esquerda;
    }
    return menor;
}
}
