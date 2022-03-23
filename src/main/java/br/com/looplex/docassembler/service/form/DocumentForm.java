package br.com.looplex.docassembler.service.form;

import br.com.looplex.docassembler.model.Document;
import lombok.Getter;
import org.springframework.lang.Nullable;

import java.util.List;


@Getter
public class DocumentForm {

    @Nullable
    private String text;
    @Nullable
    private List<Document> children;

}