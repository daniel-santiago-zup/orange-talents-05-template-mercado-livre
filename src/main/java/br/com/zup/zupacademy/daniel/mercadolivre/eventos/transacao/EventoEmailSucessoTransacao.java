package br.com.zup.zupacademy.daniel.mercadolivre.eventos.transacao;

import br.com.zup.zupacademy.daniel.mercadolivre.common.Emails;
import br.com.zup.zupacademy.daniel.mercadolivre.compra.Compra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventoEmailSucessoTransacao implements EventoSucessoTransacao{
    @Autowired
    Emails emails;

    @Override
    public void processa(Compra compra) {
        emails.compraRealizadaComSucesso(compra);
    }
}
