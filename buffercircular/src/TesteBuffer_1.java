public class TesteBuffer_1 {
    public static void main(String[] args) {
        buffercircular_1 buffer = new buffercircular_1();

        Thread produtor = new Thread(() -> {
            int dado = 1;
            while (true) {
                try {
                    buffer.escrever(dado);
                    dado++;
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread consumidor = new Thread(() -> {
            while (true) {
                try {
                    int dado = buffer.ler();
                    Thread.sleep(3000); 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        produtor.start();
        consumidor.start();
    }
}
