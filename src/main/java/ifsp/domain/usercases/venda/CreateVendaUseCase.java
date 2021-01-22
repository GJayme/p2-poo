package ifsp.domain.usercases.venda;

import ifsp.domain.entities.venda.Venda;
import ifsp.domain.usercases.utils.EntityAlreadyExistsException;

public class CreateVendaUseCase {

    private VendaDAO vendaDAO;

    public CreateVendaUseCase(VendaDAO vendaDAO) {
        this.vendaDAO = vendaDAO;
    }

    public Integer create(Venda venda){

        Integer id = venda.getId();
        if (vendaDAO.readOne(id).isPresent()){
            throw new EntityAlreadyExistsException("O ID dessa venda já foi utilizado.");
        }

        return vendaDAO.create(venda);
    }
}
