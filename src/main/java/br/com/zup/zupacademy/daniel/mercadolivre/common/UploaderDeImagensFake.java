package br.com.zup.zupacademy.daniel.mercadolivre.common;

import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class UploaderDeImagensFake implements UploaderDeImagens{
    @Override
    public List<String>  enviaImagensDeProduto(Collection<MultipartFile> arquivos) {
        return arquivos.stream()
                .map(arquivo -> "www.urlsfalsas.com/"+ UUID.randomUUID())
                .collect(Collectors.toList());
    }
}
