package br.com.zup.zupacademy.daniel.mercadolivre.eventos.transacao;

import br.com.zup.zupacademy.daniel.mercadolivre.common.Emails;
import br.com.zup.zupacademy.daniel.mercadolivre.compra.Compra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class EventoEmailFalhaTrasacao implements EventoFalhaTrasacao{
    @Autowired
    Emails emails;
    @Override
    public void processa(Compra compra) {
        emails.falhaNaCompra(compra, UriComponentsBuilder.newInstance().scheme("http").host("localhost:8080"));
    }
}
