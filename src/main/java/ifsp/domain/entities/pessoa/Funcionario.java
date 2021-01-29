package ifsp.domain.entities.pessoa;

import java.time.LocalDate;

public class Funcionario extends Pessoa{
    private Double salario;
    private String turno;

    public Funcionario() {
    }

    public Funcionario(String nome, String sexo, String cpf, LocalDate dataNascimento, PessoaTipo tipo, Double salario, String turno) {
        super(nome, sexo, cpf, dataNascimento, tipo);
        this.salario = salario;
        this.turno = turno;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    @Override
    public String toString() {
        return getNome();
    }
}
