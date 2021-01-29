package ifsp.applicaton.repository;

import ifsp.domain.entities.pessoa.Cliente;
import ifsp.domain.entities.pessoa.Funcionario;
import ifsp.domain.entities.pessoa.Pessoa;
import ifsp.domain.entities.pessoa.PessoaTipo;
import ifsp.domain.usercases.pessoa.PessoaDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqlitePessoaDAO implements PessoaDAO {
    @Override
    public String create(Pessoa pessoa) {
        String sql = "INSERT INTO Pessoa(nome, cpf, sexo, data_nascimento, salario, turno, pontos_fidelidade, tipo) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, pessoa.getNome());
            stmt.setString(2, pessoa.getCpf());
            stmt.setString(3, pessoa.getSexo());
            stmt.setString(4, pessoa.getDataNascimento().toString());

            if (pessoa instanceof Funcionario) {
                stmt.setDouble(5, ((Funcionario) pessoa).getSalario());
                stmt.setString(6, ((Funcionario) pessoa).getTurno());
            } else {
                stmt.setNull(5, Types.VARCHAR);
                stmt.setNull(6, Types.VARCHAR);
            }

            if (pessoa instanceof Cliente) {
                stmt.setDouble(7, ((Cliente) pessoa).getPontosFidelidade());
            } else {
                stmt.setNull(7, Types.VARCHAR);
            }

            stmt.setString(8, pessoa.getTipo().toString());

            stmt.execute();
            return pessoa.getCpf();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<Pessoa> readOne(String cpf) {
        String sql = "SELECT * FROM Pessoa WHERE cpf = ?";
        Pessoa pessoa = null;

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                pessoa = resultSetToEntity(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(pessoa);
    }

    private Pessoa resultSetToEntity(ResultSet rs) throws SQLException {
        String nome = rs.getString("nome");
        String sexo = rs.getString("sexo");
        String cpf = rs.getString("cpf");
        LocalDate dataNascimento = LocalDate.parse(rs.getString("data_nascimento"));
        Double salario = rs.getDouble("salario");
        String turno = rs.getString("turno");
        Double pontosFidelidade = rs.getDouble("pontos_fidelidade");
        PessoaTipo pessoaTipo = PessoaTipo.toEnum(rs.getString("tipo"));

        if (PessoaTipo.FUNCIONARIO.equals(pessoaTipo)) {
            return new Funcionario(nome, sexo, cpf, dataNascimento, pessoaTipo, salario, turno);
        } else {
            return new Cliente(nome, sexo, cpf, dataNascimento, pessoaTipo, pontosFidelidade);
        }
    }

    @Override
    public List<Pessoa> readAll() {
        List<Pessoa> pessoas = new ArrayList<>();
        String sql = "SELECT * FROM Pessoa";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                Pessoa pessoa = resultSetToEntity(rs);
                pessoas.add(pessoa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pessoas;
    }

    @Override
    public boolean update(Pessoa pessoa) {
        String sql = "UPDATE Pessoa SET nome = ?, sexo = ?, data_nascimento = ?, pontos_fidelidade = ?, " +
                "salario = ?, turno = ? WHERE cpf = ?";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, pessoa.getNome());
            stmt.setString(2, pessoa.getSexo());
            stmt.setString(3, String.valueOf(pessoa.getDataNascimento()));

            if (pessoa instanceof Cliente) {
                stmt.setDouble(4, ((Cliente) pessoa).getPontosFidelidade());
            } else {
                stmt.setNull(4, Types.VARCHAR);
            }

            if (pessoa instanceof Funcionario) {
                stmt.setDouble(5, ((Funcionario) pessoa).getSalario());
                stmt.setString(6, ((Funcionario) pessoa).getTurno());
            } else {
                stmt.setNull(5, Types.VARCHAR);
                stmt.setNull(6, Types.VARCHAR);
            }

            stmt.setString(7, pessoa.getCpf());

            stmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Pessoa pessoa) {
        if (pessoa == null || pessoa.getCpf() == null) {
            throw new IllegalArgumentException("Pessoa e o campo CPF não devem ser nulos.");
        }
        return deleteByKey(pessoa.getCpf());
    }

    @Override
    public boolean deleteByKey(String cpf) {
        String sql = "DELETE FROM Pessoa WHERE cpf = ?";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, cpf);
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
