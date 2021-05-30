package br.com.zup.zupacademy.daniel.mercadolivre.compra;

public enum GatewayDePagamento {
    PAYPAL {
        @Override
        public String processaPagamento(Long idDaCompra, String urlDeRetornoAposPagamento) {
            return String.format("http://www.paypal.com?buyerId=%s&redirectUrl=%s",idDaCompra,urlDeRetornoAposPagamento);
        }
    },
    PAGSEGURO {
        @Override
        public String processaPagamento(Long idDaCompra, String urlDeRetornoAposPagamento) {
            return String.format("http://www.pagseguro.com?returnId=%s&redirectUrl=%s",idDaCompra,urlDeRetornoAposPagamento);
        }
    };

    public abstract String processaPagamento(Long idDaCompra, String urlDeRetornoAposPagamento);

}
