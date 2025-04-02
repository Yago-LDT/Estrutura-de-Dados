package aula6;

public class Aula6 {
    public static void main(String[] args) {
        ArvoreBinaria arvore = new ArvoreBinaria();

        arvore.inserir(50);

        arvore.inserir(30);

        arvore.inserir(70);

        arvore.inserir(20);

        arvore.inserir(40);



        System.out.print("Percorrendo a árvore em ordem: ");

        arvore.inOrder(arvore.raiz);
    }
    
}
