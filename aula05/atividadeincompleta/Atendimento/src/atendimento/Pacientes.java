package atendimento;

public class Pacientes {
    private String nome;
    private String prioridade;
    private int idade;
    
    public Pacientes(String n, String p, int i){
        this.nome = n;
        this.idade = i;
        this.prioridade = p;
        if(this.prioridade != "alta" && this.idade < 60){
            this.prioridade = "baixa";
        }else{
            this.prioridade = "média";
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }
  
    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
    
    
    
}
