package ifsp.applicaton.repository;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseBuilder {

    public void buildDatabaseIfMissing() {
        if (isDatabaseMissing()) {
            System.out.println("Database não encontrada. Construindo database: \n");
            buildTables();
        }
    }

    private boolean isDatabaseMissing() {
        return !Files.exists(Paths.get("database.db"));
    }

    private void buildTables() {
        try (Statement statement = ConnectionFactory.createStatement()) {
            statement.addBatch(createPessoaTable());
            statement.addBatch(createVendaTable());
            statement.executeBatch();

            System.out.println("Base de dados criada com sucesso!");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private String createPessoaTable() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE Pessoa (\n");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT, \n");
        builder.append("nome TEXT NOT NULL, \n");
        builder.append("cpf TEXT NOT NULL, \n");
        builder.append("sexo TEXT NOT NULL, \n");
        builder.append("data_nascimento DATE NOT NULL, \n");
        builder.append("salario NUMERIC, \n");
        builder.append("turno TEXT, \n");
        builder.append("pontos_fidelidade NUMERIC \n");
        builder.append("); \n");

        System.out.println(builder.toString());
        return builder.toString();
    }

    private String createVendaTable() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE Venda (\n");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT, \n");
        builder.append("data_venda DATE NOT NULL, \n");
        builder.append("funcionario_responsavel TEXT NOT NULL, \n");
        builder.append("cliente TEXT NOT NULL, \n");
        builder.append("valor_venda NUMERIC NOT NULL, \n");
        builder.append("nome_produto TEXT NOT NULL, \n");
        builder.append("categoria TEXT NOT NULL, \n");
        builder.append("FOREIGN KEY(funcionario_responsavel) REFERENCES Pessoa(id), \n");
        builder.append("FOREIGN KEY(cliente) REFERENCES Pessoa(id) \n");
        builder.append("); \n");

        System.out.println(builder.toString());
        return builder.toString();
    }
}
