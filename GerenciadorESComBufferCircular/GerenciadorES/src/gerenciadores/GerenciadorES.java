package gerenciadores;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GerenciadorES extends JFrame {

    private final DefaultListModel<String> listaModeloProcessos;
    private final JList<String> listaProcessos;

    private final DefaultListModel<String> modeloConsumidos;
    private final JList<String> listaConsumidos;

    private final List<Processo> processos;
    private final buffercircular_1 buffer;

    private volatile int delayProducao = 2000; // delay inicial da produção (ms)

    public GerenciadorES() {
        super("Gerenciador de E/S com Buffer Circular");

        setLayout(new BorderLayout());

        processos = new ArrayList<>();
        buffer = new buffercircular_1();

        listaModeloProcessos = new DefaultListModel<>();
        listaProcessos = new JList<>(listaModeloProcessos);

        modeloConsumidos = new DefaultListModel<>();
        listaConsumidos = new JList<>(modeloConsumidos);

        // Painel com 2 colunas: produzidos | consumidos
        JPanel painelListas = new JPanel(new GridLayout(1, 2));
        painelListas.add(new JScrollPane(listaProcessos));
        painelListas.add(new JScrollPane(listaConsumidos));
        add(painelListas, BorderLayout.CENTER);

        // Painel topo com botões
        JPanel painelTopo = new JPanel();

        JButton botaoResetar = new JButton("Resetar");
        painelTopo.add(botaoResetar);

        JButton botaoAumentarDelay = new JButton("Aumentar Delay Produção");
        painelTopo.add(botaoAumentarDelay);

        JButton botaoDiminuirDelay = new JButton("Diminuir Delay Produção");
        painelTopo.add(botaoDiminuirDelay);

        add(painelTopo, BorderLayout.NORTH);

        // Ações dos botões
        botaoResetar.addActionListener(e -> {
            processos.clear();
            listaModeloProcessos.clear();
            modeloConsumidos.clear();
            repaint();
        });

        botaoAumentarDelay.addActionListener(e -> {
            delayProducao += 2000; // aumenta 2 segundos
            JOptionPane.showMessageDialog(this,
                "Delay da produção aumentado para " + delayProducao + " ms");
        });

        botaoDiminuirDelay.addActionListener(e -> {
            if (delayProducao > 2000) {
                delayProducao -= 2000; // diminui 2 segundos
                JOptionPane.showMessageDialog(this,
                    "Delay da produção diminuído para " + delayProducao + " ms");
            } else {
                JOptionPane.showMessageDialog(this,
                    "Delay já está no mínimo (2000 ms).");
            }
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(650, 350);
        setLocationRelativeTo(null);
        setVisible(true);

        iniciarThreadsAutomatizadas();
    }

    private void iniciarThreadsAutomatizadas() {

        new Thread(() -> {
            Random random = new Random();
            int id = 1;

            while (true) {
                try {
                    String nome = "P" + id++;
                    int tamanho = 10 + random.nextInt(90);
                    Processo p = new Processo(nome, tamanho);

                    buffer.escrever(p);

                    SwingUtilities.invokeLater(() -> {
                        processos.add(p);
                        listaModeloProcessos.addElement(p.toString());
                    });

                    Thread.sleep(delayProducao);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        new Thread(() -> {
            while (true) {
                try {
                    Processo p = buffer.ler();

                    SwingUtilities.invokeLater(() -> {
                        modeloConsumidos.addElement(p.toString());
                    });

                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GerenciadorES::new);
    }

}
