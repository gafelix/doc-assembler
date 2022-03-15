package br.com.looplex.docassembler.service.mapper;

import br.com.looplex.docassembler.model.Document;
import br.com.looplex.docassembler.service.dto.DocumentDto;
import br.com.looplex.docassembler.service.form.DocumentForm;


public interface DocumentMapper {

    Document formToEntity(DocumentForm documentForm);

}
