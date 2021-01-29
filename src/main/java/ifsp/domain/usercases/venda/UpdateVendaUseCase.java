package ifsp.domain.usercases.venda;

import ifsp.domain.entities.venda.Venda;
import ifsp.domain.usercases.pessoa.UpdatePessoaUseCase;
import ifsp.domain.usercases.utils.EntityNotFoundException;

public class UpdateVendaUseCase {

    private VendaDAO vendaDAO;
    private UpdatePessoaUseCase updatePessoaUseCase;

    public UpdateVendaUseCase(VendaDAO vendaDAO, UpdatePessoaUseCase updatePessoaUseCase) {
        this.vendaDAO = vendaDAO;
        this.updatePessoaUseCase = updatePessoaUseCase;
    }

    public boolean update(Venda venda){
        if (vendaDAO.readOne(venda.getId()).isEmpty()){
            throw new EntityNotFoundException("Venda não encontrada.");
        }

        Double pontos = PontosFidelidade.getPontos(venda.getValorVenda());
        venda.getCliente().addPontosFidelidade(pontos);
        updatePessoaUseCase.update(venda.getCliente());

        return vendaDAO.update(venda);
    }
}
