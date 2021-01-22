package ifsp.domain.usercases.pessoa;

import ifsp.domain.entities.pessoa.Pessoa;
import ifsp.domain.usercases.utils.EntityAlreadyExistsException;

public class CreatePessoaUseCase {
    private PessoaDAO pessoaDAO;

    public CreatePessoaUseCase(PessoaDAO pessoaDAO) {
        this.pessoaDAO = pessoaDAO;
    }

    public String create(Pessoa pessoa){
        String cpf = pessoa.getCpf();
        if (pessoaDAO.readOne(cpf).isPresent()){
            throw new EntityAlreadyExistsException("O CPF dessa pessoa já está cadastrado.");
        }

        return pessoaDAO.create(pessoa);
    }
}
