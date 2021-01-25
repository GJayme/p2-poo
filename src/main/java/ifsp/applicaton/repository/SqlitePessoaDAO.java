package ifsp.applicaton.repository;

import ifsp.domain.entities.pessoa.Pessoa;
import ifsp.domain.usercases.pessoa.PessoaDAO;

import java.util.List;
import java.util.Optional;

public class SqlitePessoaDAO implements PessoaDAO {
    @Override
    public String create(Pessoa type) {
        return null;
    }

    @Override
    public Optional<Pessoa> readOne(String key) {
        return Optional.empty();
    }

    @Override
    public List<Pessoa> readAll() {
        return null;
    }

    @Override
    public boolean update(Pessoa type) {
        return false;
    }

    @Override
    public boolean delete(Pessoa type) {
        return false;
    }

    @Override
    public boolean deleteByKey(String key) {
        return false;
    }
}
