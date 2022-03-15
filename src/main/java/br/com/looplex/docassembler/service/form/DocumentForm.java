package br.com.looplex.docassembler.service.form;

import br.com.looplex.docassembler.model.Document;
import lombok.Getter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


@Getter
public class DocumentForm {

    @NotNull
    @Size(min = 0, max = 20)
    private String text;
    @Nullable
    private List<Document> children;

}