package br.com.looplex.docassembler.service.mapper;

import br.com.looplex.docassembler.model.Document;
import br.com.looplex.docassembler.service.dto.DocumentDto;

public interface DocumentMapper {

    public Document dtoToEntity(DocumentDto documentDto);

}
