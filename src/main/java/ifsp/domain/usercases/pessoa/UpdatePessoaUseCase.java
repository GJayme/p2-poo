package ifsp.domain.usercases.pessoa;

import ifsp.domain.entities.pessoa.Pessoa;
import ifsp.domain.usercases.utils.EntityNotFoundException;

public class UpdatePessoaUseCase {

    private PessoaDAO pessoaDAO;

    public UpdatePessoaUseCase(PessoaDAO pessoaDAO) {
        this.pessoaDAO = pessoaDAO;
    }

    public boolean update(Pessoa pessoa){
        if (pessoaDAO.readOne(pessoa.getCpf()).isEmpty()){
            throw new EntityNotFoundException("Pessoa não encontrada.");
        }
        return pessoaDAO.update(pessoa);
    }
}
