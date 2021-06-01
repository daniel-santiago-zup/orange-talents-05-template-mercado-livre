package br.com.zup.zupacademy.daniel.mercadolivre.transacao.gatewayDePagamentoRequests;

import br.com.zup.zupacademy.daniel.mercadolivre.compra.Compra;
import br.com.zup.zupacademy.daniel.mercadolivre.transacao.Transacao;

public interface GatewayDePagamentoRequest {
    Transacao converte(Compra compra);
}
