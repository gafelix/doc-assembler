package br.com.looplex.docassembler.exceptions.handler.global;

import br.com.looplex.docassembler.exceptions.Logger;
import br.com.looplex.docassembler.exceptions.dto.DocumentExceptionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;


@RestControllerAdvice
public class GlobalExceptionHandler extends Logger {

    @ExceptionHandler({Exception.class})
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<DocumentExceptionDto> handleException(Exception exception) {
        super.logException(exception);
        DocumentExceptionDto documentExceptionDto = DocumentExceptionDto
                .builder()
                .id(Long.valueOf(500))
                .error(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(documentExceptionDto);
    }

}
