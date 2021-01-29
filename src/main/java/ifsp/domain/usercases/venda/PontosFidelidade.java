package ifsp.domain.usercases.venda;

public enum PontosFidelidade {
    UM_PONTO(1.00, 5.00,1.0),
    DOIS_PONTOS(5.00, 10.00,2.0),
    TRES_PONTOS(10.00, 15.00,3.0),
    QUATRO_PONTOS(15.00, Double.MAX_VALUE,4.0);

    private Double minimo;
    private Double maximo;
    private Double pontos;

    PontosFidelidade(Double minimo, Double maximo, Double pontos) {
        this.minimo = minimo;
        this.maximo = maximo;
        this.pontos = pontos;
    }

    public static Double getPontos(Double valor) {

        for (PontosFidelidade pontosFidelidade : PontosFidelidade.values() ) {
            if (valor >= pontosFidelidade.minimo && valor < pontosFidelidade.maximo) {
                return pontosFidelidade.pontos;
            }
        }

        return 0.0;
    }
}
