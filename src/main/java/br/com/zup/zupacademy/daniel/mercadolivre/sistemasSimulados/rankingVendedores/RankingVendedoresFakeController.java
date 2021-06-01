package br.com.zup.zupacademy.daniel.mercadolivre.sistemasSimulados.rankingVendedores;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/ranking-vendedores")
public class RankingVendedoresFakeController {

    @PostMapping
    public String cadastraRankingVendedoresFake(@RequestBody @Valid RankingVendedoresFakeRequest request) {
        System.out.println("Compra cadastrada no ranking de vendedores");
        return "Compra cadastrada no ranking de vendedores";
    }
}
