package br.com.looplex.docassembler.exceptions.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;


@Builder
@Getter
public class DocumentExceptionDto {

    private String error;
    private LocalDateTime timestamp;
    private List<String> stacktrace;

}
