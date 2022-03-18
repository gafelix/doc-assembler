package br.com.looplex.docassembler.exceptions;

import br.com.looplex.docassembler.exceptions.custom.DocumentBadRequestException;
import br.com.looplex.docassembler.exceptions.custom.DocumentNotFoundException;
import br.com.looplex.docassembler.exceptions.dto.DocumentExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

import static br.com.looplex.docassembler.exceptions.ExceptionUtil.*;


@RestControllerAdvice
public class DocumentExceptionHandler {

    @ExceptionHandler(DocumentNotFoundException.class)
    public ResponseEntity<DocumentExceptionDto> handleNotFoundException(DocumentNotFoundException exception) {
        DocumentExceptionDto documentExceptionDto = DocumentExceptionDto
                .builder()
                .error(exception.getMessage())
                .stacktrace(getStackTrace(exception))
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(documentExceptionDto);
    }

    @ExceptionHandler(DocumentBadRequestException.class)
    public ResponseEntity<DocumentExceptionDto> handleBadRequest(DocumentBadRequestException exception) {
        DocumentExceptionDto documentExceptionDto = DocumentExceptionDto
                .builder()
                .error(exception.getMessage())
                .stacktrace(getStackTrace(exception))
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(documentExceptionDto);
    }

}
