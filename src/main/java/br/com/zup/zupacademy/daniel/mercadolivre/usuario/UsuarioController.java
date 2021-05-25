package br.com.zup.zupacademy.daniel.mercadolivre.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping
    public void cadastraUsuario(@RequestBody @Valid UsuarioRequest usuarioRequest) {
        usuarioRepository.save(usuarioRequest.converte());
    }
}
