/*package br.com.looplex.docassembler.service.mapper;

import br.com.looplex.docassembler.model.InternalDocument;
import br.com.looplex.docassembler.service.form.DocumentForm;
import org.springframework.stereotype.Service;


@Service
public class DocumentMapperImp implements DocumentMapper {

    @Override
    public InternalDocument formToEntity(DocumentForm documentForm) {
        return InternalDocument.builder()
                .text(documentForm.getText())
                .children(documentForm.getChildren())
                .build();
    }

}*/