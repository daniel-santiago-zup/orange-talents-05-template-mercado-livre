package br.com.zup.zupacademy.daniel.mercadolivre.transacao;

import br.com.zup.zupacademy.daniel.mercadolivre.compra.Compra;
import br.com.zup.zupacademy.daniel.mercadolivre.compra.CompraRepository;
import br.com.zup.zupacademy.daniel.mercadolivre.eventos.transacao.EventosNovaTransacao;
import br.com.zup.zupacademy.daniel.mercadolivre.transacao.gatewayDePagamentoRequests.GatewayDePagamentoRequest;
import br.com.zup.zupacademy.daniel.mercadolivre.transacao.gatewayDePagamentoRequests.RetornoPagSeguroRequest;
import br.com.zup.zupacademy.daniel.mercadolivre.transacao.gatewayDePagamentoRequests.RetornoPaypalRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class TransacaoController {

    @Autowired
    CompraRepository compraRepository;
    @Autowired
    EventosNovaTransacao eventosNovaTransacao;

    @PostMapping("/transacao-pagseguro/{idCompra}")
    @Transactional
    public void cadastraTransacaoPagSeguro(@PathVariable Long idCompra, @Valid RetornoPagSeguroRequest request, UriComponentsBuilder uriComponentsBuilder) {
        processaCompra(idCompra,request,uriComponentsBuilder);
    }

    @PostMapping("/transacao-paypal/{idCompra}")
    @Transactional
    public void cadastraTransacaoPaypal(@PathVariable Long idCompra, @Valid RetornoPaypalRequest request, UriComponentsBuilder uriComponentsBuilder) {
        processaCompra(idCompra,request,uriComponentsBuilder);
    }

    private void processaCompra(Long idCompra, GatewayDePagamentoRequest request, UriComponentsBuilder uriComponentsBuilder) {
        Compra compra = compraRepository.findById(idCompra).orElseThrow(() -> new IllegalArgumentException("Compra fornecida não foi encontrada"));
        compra.adicionaTransacao(request.converte(compra));
        eventosNovaTransacao.processa(compra);
    }
}
