package br.com.looplex.docassembler;

import br.com.looplex.docassembler.exceptions.DocumentExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;


@SpringBootApplication
@Import(DocumentExceptionHandler.class)
public class DocAssemblerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DocAssemblerApplication.class, args);
    }

}
