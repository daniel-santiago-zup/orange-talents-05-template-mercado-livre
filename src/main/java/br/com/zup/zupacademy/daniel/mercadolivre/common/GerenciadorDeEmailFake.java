package br.com.zup.zupacademy.daniel.mercadolivre.common;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class GerenciadorDeEmailFake implements GerenciadorDeEmail{
    @Override
    public void enviaEmail(String remetente, String destinatario, String assunto, String corpo) {
        System.out.println("\nAssunto: "+assunto+"\n\nCorpo: "+corpo+ "\n\nemail enviado com sucesso\n");
    }
}
