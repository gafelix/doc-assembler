package br.com.looplex.docassembler.exceptions.handler.document;

import br.com.looplex.docassembler.exceptions.custom.document.DocumentBadRequestException;
import br.com.looplex.docassembler.exceptions.dto.DocumentExceptionDto;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;


@RestControllerAdvice
@Order(1)
public class DocumentFormInvalidHandler {

    @ExceptionHandler(DocumentBadRequestException.class)
    public ResponseEntity<DocumentExceptionDto> handleBadRequest(DocumentBadRequestException exception) {
        DocumentExceptionDto documentExceptionDto = DocumentExceptionDto
                .builder()
                .id(400L)
                .error(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(documentExceptionDto);
    }

}
