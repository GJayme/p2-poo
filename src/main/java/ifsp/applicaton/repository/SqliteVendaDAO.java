package ifsp.applicaton.repository;

import ifsp.domain.entities.venda.Venda;
import ifsp.domain.usercases.venda.VendaDAO;

import java.util.List;
import java.util.Optional;

public class SqliteVendaDAO implements VendaDAO {
    @Override
    public Integer create(Venda type) {
        return null;
    }

    @Override
    public Optional<Venda> readOne(Integer key) {
        return Optional.empty();
    }

    @Override
    public List<Venda> readAll() {
        return null;
    }

    @Override
    public boolean update(Venda type) {
        return false;
    }

    @Override
    public boolean delete(Venda type) {
        return false;
    }

    @Override
    public boolean deleteByKey(Integer key) {
        return false;
    }
}
