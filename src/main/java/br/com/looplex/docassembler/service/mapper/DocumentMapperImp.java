package br.com.looplex.docassembler.service.mapper;

import br.com.looplex.docassembler.model.Document;
import br.com.looplex.docassembler.model.InternalDocument;
import br.com.looplex.docassembler.model.LeafDocument;
import br.com.looplex.docassembler.service.form.DocumentForm;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
public class DocumentMapperImp implements DocumentMapper {

    private boolean doesListExist(List<?> list) {
        return (!Objects.isNull(list) && !list.isEmpty());
    }

    @Override
    public Document toEntity(DocumentForm documentForm) {
        if(!doesListExist(documentForm.getChildren()))
            return new LeafDocument(documentForm.getText());
        else {
            return new InternalDocument(documentForm.getChildren());
        }
    }

}