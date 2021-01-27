package ifsp.domain.entities.venda;

import java.util.Arrays;

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

    public static CategoriaProduto toEnum(String value) {
        return Arrays.stream(CategoriaProduto.values())
                .filter(a -> value.equals(a.toString()))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
