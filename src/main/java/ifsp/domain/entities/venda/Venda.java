package ifsp.domain.entities.venda;

import ifsp.domain.entities.pessoa.Cliente;
import ifsp.domain.entities.pessoa.Funcionario;

import java.time.LocalDate;

public class Venda {
    private Integer id;
    private LocalDate dataVenda;
    private Double valorVenda;
    private String nomeProduto;
    private CategoriaProduto categoriaProduto;

    private Cliente cliente;
    private Funcionario funcionario;

    public Venda() {
    }

    public Venda(Integer id, LocalDate dataVenda, Double valorVenda, String nomeProduto, CategoriaProduto categoriaProduto, Cliente cliente, Funcionario funcionario) {
        this.id = id;
        this.dataVenda = dataVenda;
        this.valorVenda = valorVenda;
        this.nomeProduto = nomeProduto;
        this.categoriaProduto = categoriaProduto;
        this.cliente = cliente;
        this.funcionario = funcionario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }

    public Double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(Double valorVenda) {
        this.valorVenda = valorVenda;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public CategoriaProduto getCategoriaProduto() {
        return categoriaProduto;
    }

    public void setCategoriaProduto(CategoriaProduto categoriaProduto) {
        this.categoriaProduto = categoriaProduto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Venda{" +
                "id=" + id +
                ", dataVenda=" + dataVenda +
                ", valorVenda=" + valorVenda +
                ", nomeProduto='" + nomeProduto + '\'' +
                ", categoriaProduto=" + categoriaProduto +
                ", cliente=" + cliente +
                ", funcionario=" + funcionario +
                '}';
    }
}
