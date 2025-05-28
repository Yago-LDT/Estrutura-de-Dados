package gerenciadores;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class GerenciadorES extends JFrame {

    private final DefaultListModel<String> listaModeloProcessos;
    private final java.util.List<Processo> processos; // lista geral de processos
    private final Processo[] bufferCircular;
    private int inicioBuffer = 0;
    private int fimBuffer = 0;
    private int tamanhoBuffer = 0;
    private final int capacidadeBuffer = 5; // capacidade fixa do buffer circular

    private final JButton botaoAdicionar, botaoProcessar, botaoResetar;
    private final JList<String> listaProcessos;
    private final JList<String> listaBuffer;
    private final JTextField nomeCampo, tamanhoCampo;
    private final DefaultListModel<String> modeloBuffer;

    public GerenciadorES() {
        super("Gerenciador de E/S com Buffer Circular");

        setLayout(new BorderLayout());

        processos = new ArrayList<>();
        bufferCircular = new Processo[capacidadeBuffer];

        // Modelo da lista de processos
        listaModeloProcessos = new DefaultListModel<>();
        listaProcessos = new JList<>(listaModeloProcessos);

        // Modelo e lista do buffer circular
        modeloBuffer = new DefaultListModel<>();
        listaBuffer = new JList<>(modeloBuffer);

        // Painel de entrada
        JPanel painelEntrada = new JPanel();
        painelEntrada.add(new JLabel("Nome:"));
        nomeCampo = new JTextField(8);
        painelEntrada.add(nomeCampo);

        painelEntrada.add(new JLabel("Tamanho:"));
        tamanhoCampo = new JTextField(5);
        painelEntrada.add(tamanhoCampo);

        botaoAdicionar = new JButton("Adicionar Processo");
        painelEntrada.add(botaoAdicionar);
        
        botaoResetar = new JButton("Resetar");
        painelEntrada.add(botaoResetar);

        botaoProcessar = new JButton("Processar E/S (Remover da fila)");
        painelEntrada.add(botaoProcessar);

        add(painelEntrada, BorderLayout.NORTH);

        // Painel central com listas
        JPanel painelListas = new JPanel(new GridLayout(1, 2));

        painelListas.add(new JScrollPane(listaProcessos));
        painelListas.add(new JScrollPane(listaBuffer));

        add(painelListas, BorderLayout.CENTER);

        // Ações dos botões
        botaoAdicionar.addActionListener(e -> {
            String nome = nomeCampo.getText().trim();
            int tamanho;
            try {
                tamanho = Integer.parseInt(tamanhoCampo.getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Tamanho inválido");
                return;
            }
            if (nome.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nome não pode ser vazio");
                return;
            }
            

            Processo p = new Processo(nome, tamanho);
            processos.add(p);
            listaModeloProcessos.addElement(p.toString());

            // Adiciona no buffer circular (simula processo aguardando E/S)
            if (adicionarBuffer(p)) {
                atualizarListaBuffer();
                JOptionPane.showMessageDialog(this, "Processo adicionado na fila de E/S (buffer circular).");
            } else {
                JOptionPane.showMessageDialog(this, "Buffer circular cheio! Processo não pode entrar na fila.");
            }
        });
        
        botaoResetar.addActionListener(e -> {

    processos.clear();
    listaModeloProcessos.clear();

   
    Arrays.fill(bufferCircular, null); 
    inicioBuffer = 0;
    fimBuffer = 0;
    tamanhoBuffer = 0;
    modeloBuffer.clear();

    listaBuffer.clearSelection();
    repaint();
});

        botaoProcessar.addActionListener(e -> {
            Processo p = removerBuffer();
            if (p != null) {
                atualizarListaBuffer();
                JOptionPane.showMessageDialog(this, "Processo " + p.nome + " processado e removido da fila de E/S.");
            } else {
                JOptionPane.showMessageDialog(this, "Buffer circular vazio.");
            }
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    

    private boolean adicionarBuffer(Processo p) {
        if (tamanhoBuffer == capacidadeBuffer) {
            return false; // buffer cheio
        }
        bufferCircular[fimBuffer] = p;
        fimBuffer = (fimBuffer + 1) % capacidadeBuffer;
        tamanhoBuffer++;
        return true;
    }

    private Processo removerBuffer() {
        if (tamanhoBuffer == 0) {
            return null; // buffer vazio
        }
        Processo p = bufferCircular[inicioBuffer];
        bufferCircular[inicioBuffer] = null;
        inicioBuffer = (inicioBuffer + 1) % capacidadeBuffer;
        tamanhoBuffer--;
        return p;
    }

    private void atualizarListaBuffer() {
        modeloBuffer.clear();
        for (int i = 0; i < tamanhoBuffer; i++) {
            int index = (inicioBuffer + i) % capacidadeBuffer;
            Processo p = bufferCircular[index];
            modeloBuffer.addElement(p.toString());
                    }
    }
    
public static void main(String[] args) {
        SwingUtilities.invokeLater(GerenciadorES::new);
    }

    static class Processo {
        String nome;
        int tamanho;

        Processo(String nome, int tamanho) {
            this.nome = nome;
            this.tamanho = tamanho;
        }

        public String toString() {
            return nome + " (" + tamanho + "KB)";
        }
    }
}