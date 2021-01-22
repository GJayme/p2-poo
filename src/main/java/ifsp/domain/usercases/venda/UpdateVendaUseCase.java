package ifsp.domain.usercases.venda;

import ifsp.domain.entities.venda.Venda;
import ifsp.domain.usercases.utils.EntityNotFoundException;

public class UpdateVendaUseCase {
    private VendaDAO vendaDAO;

    public UpdateVendaUseCase(VendaDAO vendaDAO) {
        this.vendaDAO = vendaDAO;
    }

    public boolean update(Venda venda){
        if (vendaDAO.readOne(venda.getId()).isEmpty()){
            throw new EntityNotFoundException("Venda não encontrada.");
        }
        return vendaDAO.update(venda);
    }
}
