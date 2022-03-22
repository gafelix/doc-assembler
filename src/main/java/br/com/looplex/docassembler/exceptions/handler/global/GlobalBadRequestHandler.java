package br.com.looplex.docassembler.exceptions.handler.global;

import br.com.looplex.docassembler.exceptions.dto.DocumentExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalBadRequestHandler {

    @ExceptionHandler({MethodArgumentTypeMismatchException.class, MethodArgumentNotValidException.class, IllegalArgumentException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseEntity<DocumentExceptionDto> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException exception) {
        DocumentExceptionDto documentExceptionDto = DocumentExceptionDto
                .builder()
                .id(Long.valueOf(400))
                .error(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(documentExceptionDto);
    }

}
