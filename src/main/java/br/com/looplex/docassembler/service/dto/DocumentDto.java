package br.com.looplex.docassembler.service.dto;

import br.com.looplex.docassembler.model.Document;
import lombok.Builder;
import lombok.Getter;

import java.util.List;


@Getter
@Builder
public class DocumentDto {

    private String text;
    private List<DocumentDto> children;

}
