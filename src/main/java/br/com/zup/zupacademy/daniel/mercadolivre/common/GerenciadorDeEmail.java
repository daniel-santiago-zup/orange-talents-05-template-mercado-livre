package br.com.zup.zupacademy.daniel.mercadolivre.common;

public interface GerenciadorDeEmail {
    void enviaEmail(String remetente,String destinatario, String assunto, String corpo);
}
