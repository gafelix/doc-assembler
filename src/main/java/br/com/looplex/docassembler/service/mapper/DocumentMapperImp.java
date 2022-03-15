package br.com.looplex.docassembler.service.mapper;

import br.com.looplex.docassembler.model.Document;
import br.com.looplex.docassembler.service.form.DocumentForm;
import org.springframework.stereotype.Service;

@Service
public class DocumentMapperImp implements DocumentMapper {

    @Override
    public Document dtoToEntity(DocumentForm documentForm) {
        return new Document(documentForm.getText(), documentForm.getChildren());
    }

}
