package br.com.looplex.docassembler.service.traversal;

import br.com.looplex.docassembler.service.printer.DocumentPrinter;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
public class InternalDocumentTraversal implements DocumentTraversal {

    @Getter
    private List<DocumentTraversal> children;

    public void traverse(List<DocumentTraversal> tree, DocumentPrinter documentPrinter) {
        documentPrinter.saveTree(this, tree, documentPrinter);
    }

}