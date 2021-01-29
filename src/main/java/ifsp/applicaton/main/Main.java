package ifsp.applicaton.main;

import ifsp.applicaton.repository.DatabaseBuilder;
import ifsp.applicaton.repository.SqlitePessoaDAO;
import ifsp.applicaton.repository.SqliteVendaDAO;
import ifsp.applicaton.view.WindowLoader;
import ifsp.domain.usercases.pessoa.*;
import ifsp.domain.usercases.venda.*;

public class Main {

    public static CreatePessoaUseCase createPessoaUseCase;
    public static DeletePessoaUseCase deletePessoaUseCase;
    public static ReadPessoaUseCase readPessoaUseCase;
    public static UpdatePessoaUseCase updatePessoaUseCase;

    public static CreateVendaUseCase createVendaUseCase;
    public static DeleteVendaUseCase deleteVendaUseCase;
    public static ReadVendaUseCase readVendaUseCase;
    public static UpdateVendaUseCase updateVendaUseCase;

    public static void main(String[] args) {
        configureInjection();
        setupDatabase();
        WindowLoader.main(args);
    }

    private static void setupDatabase() {
        DatabaseBuilder dbBuilder = new DatabaseBuilder();
        dbBuilder.buildDatabaseIfMissing();
    }

    private static void configureInjection() {
        PessoaDAO pessoaDAO = new SqlitePessoaDAO();
        createPessoaUseCase = new CreatePessoaUseCase(pessoaDAO);
        deletePessoaUseCase = new DeletePessoaUseCase(pessoaDAO);
        readPessoaUseCase = new ReadPessoaUseCase(pessoaDAO);
        updatePessoaUseCase = new UpdatePessoaUseCase(pessoaDAO);

        VendaDAO vendaDAO = new SqliteVendaDAO();
        createVendaUseCase = new CreateVendaUseCase(vendaDAO, updatePessoaUseCase);
        deleteVendaUseCase = new DeleteVendaUseCase(vendaDAO, updatePessoaUseCase);
        readVendaUseCase = new ReadVendaUseCase(vendaDAO);
        updateVendaUseCase = new UpdateVendaUseCase(vendaDAO);
    }
}
