package br.com.looplex.docassembler.service.mapper;

import br.com.looplex.docassembler.model.Document;
import br.com.looplex.docassembler.service.dto.DocumentDto;
import org.springframework.stereotype.Service;

@Service
public class DocumentMapperImp implements DocumentMapper {

    public Document dtoToEntity(DocumentDto documentDto) {
        return new Document(documentDto.getText(), documentDto.getChildren());
    }

}
