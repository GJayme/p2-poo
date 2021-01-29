package ifsp.applicaton.repository;

import ifsp.domain.entities.pessoa.Cliente;
import ifsp.domain.entities.pessoa.Funcionario;
import ifsp.domain.entities.venda.CategoriaProduto;
import ifsp.domain.entities.venda.Venda;
import ifsp.domain.usercases.venda.VendaDAO;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ifsp.applicaton.main.Main.readPessoaUseCase;

public class SqliteVendaDAO implements VendaDAO {
    @Override
    public Integer create(Venda venda) {
        String sql = "INSERT INTO Venda (data_venda, funcionario_responsavel, cliente, valor_venda, nome_produto, " +
                "categoria) VALUES (?, ?, ?, ?, ?, ?) ";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setDate(1, Date.valueOf(venda.getDataVenda()));
            stmt.setString(2, venda.getFuncionario().getCpf());
            stmt.setString(3, venda.getCliente().getCpf());
            stmt.setDouble(4, venda.getValorVenda());
            stmt.setString(5, venda.getNomeProduto());
            stmt.setString(6, venda.getCategoriaProduto().toString());
            stmt.execute();

            ResultSet rs = stmt.getGeneratedKeys();
            int generatedId = rs.getInt(1);

            return generatedId;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<Venda> readOne(Integer key) {
        String sql = "SELECT * FROM Venda WHERE id = ?";
        Venda venda = null;

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setInt(1, key);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                venda = resultSetToEntity(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(venda);
    }

    private Venda resultSetToEntity(ResultSet rs) throws SQLException {
        String funcionarioResponsavel = rs.getString("funcionario_responsavel");
        Funcionario funcionario = (Funcionario) readPessoaUseCase.readOne(funcionarioResponsavel).get();

        String clienteDaVenda = rs.getString("cliente");
        Cliente cliente = (Cliente) readPessoaUseCase.readOne(clienteDaVenda).get();

        return new Venda(
                rs.getInt("id"),
                rs.getDate("data_venda").toLocalDate(),
                rs.getDouble("valor_venda"),
                rs.getString("nome_produto"),
                CategoriaProduto.toEnum(rs.getString("categoria")),
                cliente,
                funcionario
        );
    }

    @Override
    public List<Venda> readAll() {
        String sql = "SELECT * FROM Venda";
        List<Venda> vendas = new ArrayList<>();

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Venda venda = resultSetToEntity(rs);
                vendas.add(venda);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vendas;
    }

    @Override
    public boolean update(Venda venda) {
        String sql = "UPDATE Venda SET data_venda = ?, funcionario_responsavel = ?, cliente = ?, valor_venda = ?, " +
                "nome_produto = ?, categoria = ? WHERE id = ?";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setDate(1, Date.valueOf(venda.getDataVenda()));
            stmt.setString(2, venda.getFuncionario().getCpf());
            stmt.setString(3, venda.getCliente().getCpf());
            stmt.setDouble(4, venda.getValorVenda());
            stmt.setString(5, venda.getNomeProduto());
            stmt.setString(6, venda.getCategoriaProduto().toString());
            stmt.setInt(7, venda.getId());
            stmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Venda venda) {
        if (venda == null || venda.getId() == null) {
            throw new IllegalArgumentException("Venda ou ID da venda, não podem ser nulos.");
        }
        return false;
    }

    @Override
    public boolean deleteByKey(Integer key) {
        String sql = "DELETE FROM Venda WHERE id = ?";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, key);
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Venda> readClienteByCpf(String cpf) {
        String sql = "SELECT Venda.* FROM Venda JOIN Pessoa On Venda.cliente = Pessoa.cpf " +
                "WHERE pessoa.cpf = ?";

        return getVendas(cpf, sql);
    }

    @Override
    public List<Venda> readFuncionarioByCpf(String cpf) {
        String sql = "SELECT Venda.* FROM Venda JOIN Pessoa On Venda.funcionario_responsavel = " +
                "Pessoa.cpf WHERE pessoa.cpf = ?";

        return getVendas(cpf, sql);
    }

    private List<Venda> getVendas(String cpf, String sql) {
        List<Venda> vendas = new ArrayList<>();

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Venda venda = resultSetToEntity(rs);
                vendas.add(venda);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vendas;
    }
}
