package ifsp.domain.entities.pessoa;

import ifsp.domain.entities.venda.Venda;

import java.time.LocalDate;
import java.util.List;

public class Cliente extends Pessoa {
    private Double pontosFidelidade;

    private List<Venda> compras;

    public Cliente() {
    }

    public Cliente(String nome, String sexo, String cpf, LocalDate dataNascimento, PessoaTipo tipo, Double pontosFidelidade) {
        super(nome, sexo, cpf, dataNascimento, tipo);
        this.pontosFidelidade = pontosFidelidade;
    }

    public List<Venda> getCompras() {
        return compras;
    }

    public boolean addNovaCompra(Venda compra) {
        compras.add(compra);
        return true;
    }

    public Double getPontosFidelidade() {
        return pontosFidelidade;
    }

    public void setPontosFidelidade(Double pontosFidelidade) {
        this.pontosFidelidade = pontosFidelidade;
    }

    public void addPontosFidelidade(Double pontosFidelidade) {
        if (pontosFidelidade == null) {
            this.pontosFidelidade = 0.0;
            return;
        }
        this.pontosFidelidade += pontosFidelidade;
    }

    public void removePontosFidelidade(Double pontosFidelidade) {
        this.pontosFidelidade -= pontosFidelidade;
    }

    @Override
    public String toString() {
        return getNome();
    }
}
