package br.com.zup.zupacademy.daniel.mercadolivre.sistemasSimulados.notaFiscal;

public class NotaFiscalFakeRequest {
    private Long idCompra;
    private Long idComprador;

    public NotaFiscalFakeRequest(Long idCompra, Long idComprador) {
        this.idCompra = idCompra;
        this.idComprador = idComprador;
    }
}
