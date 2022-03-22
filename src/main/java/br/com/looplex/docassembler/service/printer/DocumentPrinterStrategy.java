package br.com.looplex.docassembler.service.printer;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public enum DocumentPrinterStrategy {

    LEFTSIDE("leftside");

    private final String value;

    public String value() {
        return value;
    }

}