package ifsp.domain.usercases.pessoa;

import ifsp.domain.entities.pessoa.Pessoa;
import ifsp.domain.entities.venda.Venda;
import ifsp.domain.usercases.utils.EntityNotFoundException;

public class DeletePessoaUseCase {

    private PessoaDAO pessoaDAO;

    public DeletePessoaUseCase(PessoaDAO pessoaDAO) {
        this.pessoaDAO = pessoaDAO;
    }

    public boolean delete (String cpf){
        if (cpf == null || pessoaDAO.readOne(cpf).isEmpty()){
            throw new EntityNotFoundException("Pessoa não encontrada.");
        }
        return pessoaDAO.deleteByKey(cpf);
    }

    public boolean delete (Pessoa pessoa){
        if (pessoa == null || pessoaDAO.readOne(pessoa.getCpf()).isEmpty()){
            throw new EntityNotFoundException("Pessoa não encontrada.");
        }
        return pessoaDAO.delete(pessoa);
    }
}
