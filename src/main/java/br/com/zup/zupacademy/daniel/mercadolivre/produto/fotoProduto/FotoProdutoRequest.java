package br.com.zup.zupacademy.daniel.mercadolivre.produto.fotoProduto;

import br.com.zup.zupacademy.daniel.mercadolivre.common.UploaderDeImagens;
import br.com.zup.zupacademy.daniel.mercadolivre.validadores.PossuiContentType;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FotoProdutoRequest {
    @Size(min = 1)
    @PossuiContentType(comecaCom = "image/")
    private List<MultipartFile> fotosMultipartFile;

    public FotoProdutoRequest(List<MultipartFile> fotos) {
        this.fotosMultipartFile = fotos;
    }

    public Set<FotoProduto> converte(UploaderDeImagens uploaderDeImagens) {
        List<String> links = uploaderDeImagens.enviaImagensDeProduto(this.fotosMultipartFile);
        Set<FotoProduto> fotoProdutoSet = new HashSet<>();
        for (int i = 0; i < this.fotosMultipartFile.size() ;i++) {
            fotoProdutoSet.add(new FotoProduto(this.fotosMultipartFile.get(i).getOriginalFilename(), links.get(i)));
        }
        return fotoProdutoSet;
    }
}
