package br.com.zup.zupacademy.daniel.mercadolivre.common;

import br.com.zup.zupacademy.daniel.mercadolivre.produto.fotoProduto.FotoProduto;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.Set;

public interface UploaderDeImagens {
    Set<FotoProduto> enviaImagensDeProduto(Collection<MultipartFile> arquivo);
}
