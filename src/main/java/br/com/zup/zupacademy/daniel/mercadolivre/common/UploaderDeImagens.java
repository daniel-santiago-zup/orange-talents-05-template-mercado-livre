package br.com.zup.zupacademy.daniel.mercadolivre.common;

import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;

public interface UploaderDeImagens {
    List<String> enviaImagensDeProduto(Collection<MultipartFile> arquivo);
}
