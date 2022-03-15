package br.com.looplex.docassembler.service.dto;

import br.com.looplex.docassembler.model.Document;
import lombok.Getter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
public class DocumentDto {

    @NotNull
    private String text;
    @Nullable
    private List<Document> children;

}