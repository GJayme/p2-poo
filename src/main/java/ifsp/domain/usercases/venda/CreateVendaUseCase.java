package ifsp.domain.usercases.venda;

import ifsp.domain.entities.venda.Venda;
import ifsp.domain.usercases.pessoa.UpdatePessoaUseCase;

public class CreateVendaUseCase {

    private VendaDAO vendaDAO;
    private UpdatePessoaUseCase updatePessoaUseCase;

    public CreateVendaUseCase(VendaDAO vendaDAO, UpdatePessoaUseCase updatePessoaUseCase) {
        this.vendaDAO = vendaDAO;
        this.updatePessoaUseCase = updatePessoaUseCase;
    }

    public Integer create(Venda venda){

        Double pontos = PontosFidelidade.getPontos(venda.getValorVenda());
        venda.getCliente().addPontosFidelidade(pontos);
        updatePessoaUseCase.update(venda.getCliente());

        return vendaDAO.create(venda);
    }
}
