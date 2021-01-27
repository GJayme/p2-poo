package ifsp.domain.entities.pessoa;

import java.time.LocalDate;

public abstract class Pessoa {
    private String nome;
    private String sexo;
    private String cpf;
    private LocalDate dataNascimento;

    public Pessoa() {
    }

    public Pessoa(String nome, String sexo, String cpf, LocalDate dataNascimento) {
        this.nome = nome;
        this.sexo = sexo;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    abstract String getType();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "nome='" + nome + '\'' +
                ", sexo='" + sexo + '\'' +
                ", cpf='" + cpf + '\'' +
                ", dataNascimento=" + dataNascimento +
                '}';
    }
}
