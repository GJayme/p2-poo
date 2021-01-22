package ifsp.domain.usercases.venda;

import ifsp.domain.entities.venda.Venda;
import ifsp.domain.usercases.utils.EntityNotFoundException;

public class DeleteVendaUseCase {

    private VendaDAO vendaDAO;

    public DeleteVendaUseCase(VendaDAO vendaDAO) {
        this.vendaDAO = vendaDAO;
    }

    public boolean delete (Integer id){
        if (id == null || vendaDAO.readOne(id).isEmpty()){
            throw new EntityNotFoundException("Venda não encontrada.");
        }
        return vendaDAO.deleteByKey(id);
    }

    public boolean delete (Venda venda){
        if (venda == null || vendaDAO.readOne(venda.getId()).isEmpty()){
            throw new EntityNotFoundException("Venda não encontrada.");
        }
        return vendaDAO.delete(venda);
    }
}
