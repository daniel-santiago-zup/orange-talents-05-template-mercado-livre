package br.com.zup.zupacademy.daniel.mercadolivre.common;

import br.com.zup.zupacademy.daniel.mercadolivre.perguntaProduto.PerguntaProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class Emails {

    @Autowired
    GerenciadorDeEmail gerenciadorDeEmail;

    public void novaPergunta(@Valid PerguntaProduto perguntaProduto) {
        gerenciadorDeEmail.enviaEmail("remetente@email.com","destinatario@email.com","Uma nova pergunta foi feita", perguntaProduto.getTitulo());
    }
}
