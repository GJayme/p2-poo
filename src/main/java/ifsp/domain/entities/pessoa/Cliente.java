package ifsp.domain.entities.pessoa;

import ifsp.domain.entities.venda.Venda;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cliente extends Pessoa{
    private Double pontosFidelidade;

    public Cliente() {
    }

    List<Venda> compras = new ArrayList<>();

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

    public boolean addNovaCompra(Venda compra){
        compras.add(compra);
        return true;
    }

    public boolean removeCompra(Venda compra){
        compras.remove(compra);
        return true;
    }
}
