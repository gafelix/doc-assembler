package br.com.looplex.docassembler.exceptions.custom.document;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class DocumentBadRequestException extends RuntimeException {

    public DocumentBadRequestException() {
        super();
    }

    public DocumentBadRequestException(String message) {
        super(message);
    }

    public DocumentBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public DocumentBadRequestException(Throwable cause) {
        super(cause);
    }

}
