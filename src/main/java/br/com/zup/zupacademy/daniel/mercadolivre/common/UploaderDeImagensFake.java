package br.com.zup.zupacademy.daniel.mercadolivre.common;

import br.com.zup.zupacademy.daniel.mercadolivre.produto.fotoProduto.FotoProduto;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class UploaderDeImagensFake implements UploaderDeImagens{
    @Override
    public Set<FotoProduto> enviaImagensDeProduto(Collection<MultipartFile> arquivos) {
        return arquivos.stream()
                .map(foto -> new FotoProduto(foto.getOriginalFilename(), "www.urlsfalsas.com/"+ UUID.randomUUID()))
                .collect(Collectors.toSet());
    }
}
