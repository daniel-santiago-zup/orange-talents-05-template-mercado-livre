package br.com.zup.zupacademy.daniel.mercadolivre.transacao.gatewayDePagamentoRequests;

import br.com.zup.zupacademy.daniel.mercadolivre.compra.Compra;
import br.com.zup.zupacademy.daniel.mercadolivre.transacao.StatusTransacao;
import br.com.zup.zupacademy.daniel.mercadolivre.transacao.Transacao;
import br.com.zup.zupacademy.daniel.mercadolivre.validadores.ValorUnico;

import javax.validation.constraints.NotNull;

public class RetornoPaypalRequest implements GatewayDePagamentoRequest{

    @NotNull
    @ValorUnico(entidade = Transacao.class, nomeCampo = "idTransacaoGateway")
    private Long idTransacaoGateway;
    @NotNull
    private Integer statusTransacao;

    public RetornoPaypalRequest(Long idTransacaoGateway, Integer statusTransacao) {
        this.idTransacaoGateway = idTransacaoGateway;
        this.statusTransacao = statusTransacao;
    }

    @Override
    public Transacao converte(Compra compra) {
        StatusTransacao statusTransacao1;
        if (statusTransacao.equals(1)) {
            statusTransacao1 = StatusTransacao.SUCESSO;
        } else if (statusTransacao.equals(0)) {
            statusTransacao1 = StatusTransacao.FALHA;
        } else {
            throw new IllegalArgumentException("Status informado não é aceito");
        }

        return new Transacao(idTransacaoGateway,statusTransacao1,compra);
    }
}
