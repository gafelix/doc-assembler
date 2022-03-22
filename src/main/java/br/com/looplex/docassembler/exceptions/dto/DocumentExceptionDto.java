package br.com.looplex.docassembler.exceptions.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;


@Builder
@Getter
public class DocumentExceptionDto {

    private Long id;
    private String error;
    private LocalDateTime timestamp;

}
