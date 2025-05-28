public class buffercircular_1 {
    private final int capacidade = 5;
    private final int[] buffer = new int[capacidade];

    private int writeIndex = 0;
    private int readIndex = 0;
    private int count = 0;

    public synchronized void escrever(int dado) throws InterruptedException {
        while (count == capacidade) {
            System.out.println("Buffer cheio. Produtor aguardando...");
            wait();
        }

        buffer[writeIndex] = dado;
        System.out.println("Produzido: " + dado + " \nEscrito: " + writeIndex);

        writeIndex = (writeIndex + 1) % capacidade;
        count++;

        System.out.println("Itens no buffer: " + count + "\n");
        notifyAll();
    }

    public synchronized int ler() throws InterruptedException {
        while (count == 0) {
            System.out.println("Buffer vazio. Consumidor aguardando...");
            wait();
        }

        int dado = buffer[readIndex];
        System.out.println("Consumido: " + dado + " \nLido: " + readIndex);

        readIndex = (readIndex + 1) % capacidade;
        count--;

        System.out.println("Itens no buffer: " + count + "\n");
        notifyAll();
        return dado;
    }
}
