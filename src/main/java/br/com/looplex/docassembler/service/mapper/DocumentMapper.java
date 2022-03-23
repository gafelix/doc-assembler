package br.com.looplex.docassembler.service.mapper;

import br.com.looplex.docassembler.model.Document;
import br.com.looplex.docassembler.service.form.DocumentForm;
import br.com.looplex.docassembler.service.traversal.DocumentTraversal;


public interface DocumentMapper {

    Document toEntity(DocumentForm documentForm);
    DocumentTraversal toDocumentTraversal(Document document);

}