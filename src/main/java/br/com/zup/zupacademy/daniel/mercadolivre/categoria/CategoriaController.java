package br.com.zup.zupacademy.daniel.mercadolivre.categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    CategoriaRepository categoriaRepository;

    @PostMapping
    public void cadastraCategoria(@RequestBody @Valid CategoriaRequest categoriaRequest) {
        categoriaRepository.save(categoriaRequest.converte(categoriaRepository));
    }
}
