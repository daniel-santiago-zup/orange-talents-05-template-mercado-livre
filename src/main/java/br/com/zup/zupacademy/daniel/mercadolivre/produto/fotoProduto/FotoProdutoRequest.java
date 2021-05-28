package br.com.zup.zupacademy.daniel.mercadolivre.produto.fotoProduto;

import br.com.zup.zupacademy.daniel.mercadolivre.common.UploaderDeImagens;
import br.com.zup.zupacademy.daniel.mercadolivre.validadores.PossuiContentType;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class FotoProdutoRequest {
    @Size(min = 1)
    @PossuiContentType(comecaCom = "image/")
    private List<MultipartFile> fotosMultipartFile;

    public FotoProdutoRequest(List<MultipartFile> fotos) {
        this.fotosMultipartFile = fotos;
    }

    public Set<FotoProduto> converte(UploaderDeImagens uploaderDeImagens) {
        return uploaderDeImagens.enviaImagensDeProduto(this.fotosMultipartFile);
    }
}
