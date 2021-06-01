package br.com.zup.zupacademy.daniel.mercadolivre.eventos.transacao;

import br.com.zup.zupacademy.daniel.mercadolivre.compra.Compra;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class EventoRankingVendedores implements EventoSucessoTransacao {
    @Override
    public void processa(Compra compra) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> request = Map.of(
                "idCompra",compra.getId(),
                "idVendedor",compra.getIdComprador()
        );
        restTemplate.postForEntity("http://localhost:8080/ranking-vendedores",request, String.class);
    }
}
