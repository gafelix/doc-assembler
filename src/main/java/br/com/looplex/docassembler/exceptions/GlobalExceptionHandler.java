package br.com.looplex.docassembler.exceptions;

import br.com.looplex.docassembler.exceptions.dto.DocumentExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;

import static br.com.looplex.docassembler.exceptions.ExceptionUtil.getStackTrace;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseEntity<DocumentExceptionDto> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException exception) {
        String message = String.format("%s should be %s and %s is not.",
                exception.getName(),
                exception.getRequiredType().getSimpleName(),
                exception.getValue());
        DocumentExceptionDto documentExceptionDto = DocumentExceptionDto
                .builder()
                .error(message)
                .stacktrace(getStackTrace(exception))
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(documentExceptionDto);
    }

}
