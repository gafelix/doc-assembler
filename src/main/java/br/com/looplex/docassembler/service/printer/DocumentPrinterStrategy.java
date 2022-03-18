package br.com.looplex.docassembler.service.printer;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public enum DocumentPrinterStrategy {

    PREORDER("preorder"),
    POSTORDER("postorder");

    private final String value;

    public String value() {
        return value;
    }

}