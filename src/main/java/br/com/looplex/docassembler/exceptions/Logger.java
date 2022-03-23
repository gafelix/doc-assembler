package br.com.looplex.docassembler.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Logger {

    public void logException(Exception exception) {
        log.error("Exception thrown", exception);
    }

}
