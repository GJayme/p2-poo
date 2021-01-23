package ifsp.applicaton.main;

import ifsp.applicaton.view.WindowLoader;
import ifsp.domain.usercases.pessoa.CreatePessoaUseCase;
import ifsp.domain.usercases.pessoa.DeletePessoaUseCase;
import ifsp.domain.usercases.pessoa.ReadPessoaUseCase;
import ifsp.domain.usercases.pessoa.UpdatePessoaUseCase;
import ifsp.domain.usercases.venda.CreateVendaUseCase;
import ifsp.domain.usercases.venda.DeleteVendaUseCase;
import ifsp.domain.usercases.venda.ReadVendaUseCase;
import ifsp.domain.usercases.venda.UpdateVendaUseCase;

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
        WindowLoader.main(args);
    }

    private static void configureInjection() {

    }
}
