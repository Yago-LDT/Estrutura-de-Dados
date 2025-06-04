package gerenciadores;

public class buffercircular_1 {
    private final int capacidade = 5;
    private final Processo[] buffer = new Processo[capacidade];

    private int writeIndex = 0;
    private int readIndex = 0;
    private int count = 0;

    public synchronized void escrever(Processo dado) throws InterruptedException {
        while (count == capacidade) {
            System.out.println("Buffer cheio. Produtor aguardando...");
            wait();
        }

        buffer[writeIndex] = dado;
        System.out.println("Produzido: " + dado + " \nEscrito no índice: " + writeIndex);

        writeIndex = (writeIndex + 1) % capacidade;
        count++;

        System.out.println("Itens no buffer: " + count + "\n");
        notifyAll();
    }

    public synchronized Processo ler() throws InterruptedException {
        while (count == 0) {
            System.out.println("Buffer vazio. Consumidor aguardando...");
            wait();
        }

        Processo dado = buffer[readIndex];
        System.out.println("Consumido: " + dado + " \nLido no índice: " + readIndex);

        readIndex = (readIndex + 1) % capacidade;
        count--;

        System.out.println("Itens no buffer: " + count + "\n");
        notifyAll();
        return dado;
    }
}
