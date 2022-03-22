package br.com.looplex.docassembler.service.form;

import br.com.looplex.docassembler.model.Document;
import lombok.Getter;


@Getter
public class DocumentForm {

    private String type;
    private Document document;

}