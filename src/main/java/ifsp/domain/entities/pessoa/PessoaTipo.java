package ifsp.domain.entities.pessoa;

import java.util.Arrays;

public enum PessoaTipo {
    CLIENTE("Cliente"),
    FUNCIONARIO("Funcionário");

    private String label;

    PessoaTipo(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }

    public static PessoaTipo toEnum(String value) {
        return Arrays.stream(PessoaTipo.values())
                .filter(a -> value.equals(a.toString()))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
