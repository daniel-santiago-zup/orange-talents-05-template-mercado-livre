package br.com.zup.zupacademy.daniel.mercadolivre.eventos.transacao;

import br.com.zup.zupacademy.daniel.mercadolivre.compra.Compra;

public interface EventoSucessoTransacao {
    void processa(Compra compra);
}