package br.com.zup.zupacademy.daniel.mercadolivre.compra;

import br.com.zup.zupacademy.daniel.mercadolivre.transacao.StatusTransacao;
import org.springframework.web.util.UriComponentsBuilder;

public enum GatewayDePagamento {
    PAYPAL {
        @Override
        public String geraLinkDePagamento(Long idDaCompra, UriComponentsBuilder uriComponentsBuilder) {
            return String.format("http://www.paypal.com?buyerId=%s&redirectUrl=%s",
                    idDaCompra,
                    uriComponentsBuilder.path("/retorno-pagseguro/{id}").buildAndExpand(idDaCompra));
        }

        @Override
        public StatusTransacao processaStatusDePagamento(String statusRecebido) {
            if (statusRecebido.equals("1")) {
                return StatusTransacao.SUCESSO;
            } else if (statusRecebido.equals("0")) {
                return StatusTransacao.FALHA;
            }
            throw new IllegalArgumentException("Valor de status recebido não corresponde ao esperado");
        }
    },
    PAGSEGURO {
        @Override
        public String geraLinkDePagamento(Long idDaCompra, UriComponentsBuilder uriComponentsBuilder) {
            return String.format("http://www.pagseguro.com?returnId=%s&redirectUrl=%s",
                    idDaCompra,
                    uriComponentsBuilder.path("/retorno-pagseguro/{id}").buildAndExpand(idDaCompra));
        }

        @Override
        public StatusTransacao processaStatusDePagamento(String statusRecebido) {
            if (statusRecebido.equals("SUCESSO")) {
                return StatusTransacao.SUCESSO;
            } else if (statusRecebido.equals("ERRO")) {
                return StatusTransacao.FALHA;
            }
            throw new IllegalArgumentException("Valor de status recebido não corresponde ao esperado");
        }
    };

    public abstract String geraLinkDePagamento(Long idDaCompra, UriComponentsBuilder uriComponentsBuilder);
    public abstract StatusTransacao processaStatusDePagamento(String statusRecebido);

}
