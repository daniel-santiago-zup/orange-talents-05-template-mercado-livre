package br.com.zup.zupacademy.daniel.mercadolivre.sistemasSimulados.rankingVendedores;

public class RankingVendedoresFakeRequest {
    private Long idCompra;
    private Long idVendedor;

    public RankingVendedoresFakeRequest(Long idCompra, Long idVendedor) {
        this.idCompra = idCompra;
        this.idVendedor = idVendedor;
    }
}
