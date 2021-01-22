package ifsp.domain.usercases.pessoa;

import ifsp.domain.entities.pessoa.Pessoa;
import ifsp.domain.entities.venda.Venda;
import ifsp.domain.usercases.venda.VendaDAO;

import java.util.List;
import java.util.Optional;

public class ReadPessoaUseCase {

    private PessoaDAO pessoaDAO;

    public ReadPessoaUseCase(PessoaDAO pessoaDAO) {
        this.pessoaDAO = pessoaDAO;
    }

    public Optional<Pessoa> readOne(String cpf){
        if (cpf == null){
            throw new IllegalArgumentException("CPF não pode ser nulo.");
        }
        return pessoaDAO.readOne(cpf);
    }

    public List<Pessoa> readAll(){
        return pessoaDAO.readAll();
    }
}
