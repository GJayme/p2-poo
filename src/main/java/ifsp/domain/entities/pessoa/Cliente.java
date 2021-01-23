package ifsp.domain.entities.pessoa;

import java.time.LocalDate;

public class Cliente extends Pessoa{
    private Double pontosFidelidade;

    public Cliente() {
    }

    public Cliente(String nome, String sexo, String cpf, LocalDate dataNascimento, Double pontosFidelidade) {
        super(nome, sexo, cpf, dataNascimento);
        this.pontosFidelidade = pontosFidelidade;
    }

    public Double getPontosFidelidade() {
        return pontosFidelidade;
    }

    public void setPontosFidelidade(Double pontosFidelidade) {
        this.pontosFidelidade = pontosFidelidade;
    }
}
