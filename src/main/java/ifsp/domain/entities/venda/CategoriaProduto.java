package ifsp.domain.entities.venda;

public enum CategoriaProduto {
    BEBIDA("Bebida"),
    PASTEL("Pastel"),
    SOBREMESA("Sobremesa");

    private String label;

    CategoriaProduto(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
