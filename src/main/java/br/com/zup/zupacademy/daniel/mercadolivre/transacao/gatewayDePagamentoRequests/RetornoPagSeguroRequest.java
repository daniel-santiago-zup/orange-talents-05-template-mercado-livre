package br.com.zup.zupacademy.daniel.mercadolivre.transacao.gatewayDePagamentoRequests;

import br.com.zup.zupacademy.daniel.mercadolivre.compra.Compra;
import br.com.zup.zupacademy.daniel.mercadolivre.transacao.StatusTransacao;
import br.com.zup.zupacademy.daniel.mercadolivre.transacao.Transacao;
import br.com.zup.zupacademy.daniel.mercadolivre.validadores.ValorUnico;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RetornoPagSeguroRequest implements GatewayDePagamentoRequest{

    @NotNull
    @ValorUnico(entidade = Transacao.class, nomeCampo = "idTransacaoGateway")
    private Long idTransacaoGateway;
    @NotBlank
    private String statusTransacao;

    public RetornoPagSeguroRequest(Long idTransacaoGateway, String statusTransacao) {
        this.idTransacaoGateway = idTransacaoGateway;
        this.statusTransacao = statusTransacao;
    }

    @Override
    public Transacao converte(Compra compra) {
        StatusTransacao statusTransacao1;
        if (statusTransacao.equalsIgnoreCase("SUCESSO")) {
            statusTransacao1 = StatusTransacao.SUCESSO;
        } else if (statusTransacao.equalsIgnoreCase("ERRO")) {
            statusTransacao1 = StatusTransacao.FALHA;
        } else {
            throw new IllegalArgumentException("Status informado não é aceito");
        }

        return new Transacao(idTransacaoGateway,statusTransacao1,compra);
    }
}
