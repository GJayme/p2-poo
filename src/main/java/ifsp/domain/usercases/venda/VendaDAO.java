package ifsp.domain.usercases.venda;

import ifsp.domain.entities.venda.Venda;
import ifsp.domain.usercases.utils.DAO;

import java.util.List;

public interface VendaDAO extends DAO<Venda, Integer> {
    List<Venda> readClienteByCpf(String cpf);
    List<Venda> readFuncionarioByCpf(String cpf);
}
