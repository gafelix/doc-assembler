package br.com.looplex.docassembler.exceptions.handler.global;

import br.com.looplex.docassembler.exceptions.dto.DocumentExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({Exception.class})
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<DocumentExceptionDto> handleException(Exception exception) {
        DocumentExceptionDto documentExceptionDto = DocumentExceptionDto
                .builder()
                .id(500L)
                .error(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(documentExceptionDto);
    }

}
