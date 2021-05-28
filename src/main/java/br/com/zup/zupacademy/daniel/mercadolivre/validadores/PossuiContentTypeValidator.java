package br.com.zup.zupacademy.daniel.mercadolivre.validadores;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collection;

public class PossuiContentTypeValidator implements ConstraintValidator<PossuiContentType, Collection<MultipartFile>> {

    public String comecaCom;

    @Override
    public void initialize(PossuiContentType constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.comecaCom = constraintAnnotation.comecaCom();
    }

    @Override
    public boolean isValid(Collection<MultipartFile> multipartFiles, ConstraintValidatorContext constraintValidatorContext) {
        return  multipartFiles == null || multipartFiles.stream().anyMatch(foto -> foto.getContentType().startsWith(comecaCom));
    }
}
