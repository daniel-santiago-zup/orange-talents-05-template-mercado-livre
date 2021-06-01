package br.com.zup.zupacademy.daniel.mercadolivre.common;

import br.com.zup.zupacademy.daniel.mercadolivre.compra.Compra;
import br.com.zup.zupacademy.daniel.mercadolivre.produto.perguntaProduto.PerguntaProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@Service
public class Emails {

    @Autowired
    GerenciadorDeEmail gerenciadorDeEmail;

    private final String remetente = "remetente@email.com";

    public void novaPergunta(@Valid PerguntaProduto perguntaProduto) {
        gerenciadorDeEmail.enviaEmail(remetente,
                "destinatario@email.com",
                "Uma nova pergunta foi feita",
                perguntaProduto.getTitulo());
    }

    public void novaCompra(@Valid Compra compra) {
        gerenciadorDeEmail.enviaEmail(remetente,
                compra.emailDoAnunciante(),
                "Tentativa de compra do seu produto " + compra.nomeDoProdutoComprado(),
                String.format("O usuário %s iniciou o processo de compra so seu produto %s",compra.emailDoComprador(),compra.nomeDoProdutoComprado()));
    }

    public void compraRealizadaComSucesso(@Valid Compra compra) {
        gerenciadorDeEmail.enviaEmail(remetente,
                compra.emailDoComprador(),
                "Uma compra do produto "+compra.nomeDoProdutoComprado()+" foi realizada com sucesso",
                String.format("O produto %s foi comprado com sucesso",compra.nomeDoProdutoComprado()));
    }

    public void falhaNaCompra(@Valid Compra compra, UriComponentsBuilder uriComponentsBuilder) {
        gerenciadorDeEmail.enviaEmail(remetente,
                compra.emailDoComprador(),
                "Falha na compra do produto "+compra.nomeDoProdutoComprado(),
                String.format("Houve uma falha no processo de compra do produtor %s \n" +
                        "para tentar realizar o processo de pagamento novamente viste o link %s",
                        compra.nomeDoProdutoComprado(),
                        compra.geraLinkDePagamento(uriComponentsBuilder)));
    }
}
