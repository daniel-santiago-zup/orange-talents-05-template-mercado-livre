package br.com.zup.zupacademy.daniel.mercadolivre.sistemasSimulados.notaFiscal;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/nota-fiscal")
public class NotaFiscalFakeController {


    @PostMapping
    public String cadastraNotaFiscalFake(@RequestBody @Valid NotaFiscalFakeRequest request) {
        System.out.println("nota fiscal gerada");
        return "Nota fiscal gerada";
    }
}
