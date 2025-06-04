package gerenciadores;

public class Processo {
    public String nome;
    public int tamanho;

    public Processo(String nome, int tamanho) {
        this.nome = nome;
        this.tamanho = tamanho;
    }

    @Override
    public String toString() {
        return nome + " (" + tamanho + "KB)";
    }
}
