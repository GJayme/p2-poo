package ifsp.domain.usercases.venda;

import ifsp.domain.entities.venda.Venda;

import java.util.List;
import java.util.Optional;

public class ReadVendaUseCase {

    private VendaDAO vendaDAO;

    public ReadVendaUseCase(VendaDAO vendaDAO) {
        this.vendaDAO = vendaDAO;
    }

    public Optional<Venda> readOne(Integer id){
        if (id == null){
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }
        return vendaDAO.readOne(id);
    }

    public List<Venda> readByClienteCpf(String cpf) {
        if (cpf == null) {
            throw new IllegalArgumentException("CPF não pode ser nulo.");
        }
        return vendaDAO.readClienteByCpf(cpf);
    }

    public List<Venda> readFuncionarioByCpf(String cpf) {
        if (cpf == null) {
            throw new IllegalArgumentException("CPF não pode ser nulo.");
        }
        return vendaDAO.readFuncionarioByCpf(cpf);
    }

    public List<Venda> readAll(){
        return vendaDAO.readAll();
    }
}
