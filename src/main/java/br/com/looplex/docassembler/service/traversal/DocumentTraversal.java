package br.com.looplex.docassembler.service.traversal;

import br.com.looplex.docassembler.service.printer.DocumentPrinter;

import java.util.List;

public interface DocumentTraversal {

    void traverse(List<DocumentTraversal> tree,
                   DocumentPrinter documentPrinter);

}
