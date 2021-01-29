package ifsp.domain.usercases.venda;

import ifsp.domain.entities.venda.Venda;
import ifsp.domain.usercases.pessoa.UpdatePessoaUseCase;
import ifsp.domain.usercases.utils.EntityNotFoundException;

public class DeleteVendaUseCase {

    private VendaDAO vendaDAO;
    private UpdatePessoaUseCase updatePessoaUseCase;

    public DeleteVendaUseCase(VendaDAO vendaDAO, UpdatePessoaUseCase updatePessoaUseCase) {
        this.vendaDAO = vendaDAO;
        this.updatePessoaUseCase = updatePessoaUseCase;
    }

    public boolean delete (Integer id){
        if (id == null || vendaDAO.readOne(id).isEmpty()){
            throw new EntityNotFoundException("Venda não encontrada.");
        }

        Venda vendaSelected = vendaDAO.readOne(id).get();

        Double pontos = PontosFidelidade.getPontos(vendaSelected.getValorVenda());
        vendaSelected.getCliente().removePontosFidelidade(pontos);
        updatePessoaUseCase.update(vendaSelected.getCliente());


        return vendaDAO.deleteByKey(id);
    }

    public boolean delete (Venda venda){
        if (venda == null || vendaDAO.readOne(venda.getId()).isEmpty()){
            throw new EntityNotFoundException("Venda não encontrada.");
        }

        Double pontos = PontosFidelidade.getPontos(venda.getValorVenda());
        venda.getCliente().removePontosFidelidade(pontos);
        updatePessoaUseCase.update(venda.getCliente());

        return vendaDAO.delete(venda);
    }
}
