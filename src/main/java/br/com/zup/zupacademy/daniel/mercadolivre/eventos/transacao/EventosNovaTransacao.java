package br.com.zup.zupacademy.daniel.mercadolivre.eventos.transacao;

import br.com.zup.zupacademy.daniel.mercadolivre.compra.Compra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class EventosNovaTransacao {
    @Autowired
    Set<EventoSucessoTransacao> eventosSucessoTransacao;
    @Autowired
    Set<EventoFalhaTrasacao> eventosFalhaTrasacao;

        public void processa(Compra compra) {
            if (compra.foiProcessadaComSucesso()) {
                eventosSucessoTransacao.forEach(e -> e.processa(compra));
            } else {
                eventosFalhaTrasacao.forEach(e -> e.processa(compra));
            }
        }
}
