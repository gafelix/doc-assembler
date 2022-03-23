package br.com.looplex.docassembler.service.traversal;

import br.com.looplex.docassembler.service.printer.DocumentPrinter;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
public class LeafDocumentTraversal implements DocumentTraversal {

    @Getter
    private String text;

    @Override
    public void traverse(List<DocumentTraversal> tree, DocumentPrinter documentPrinter) {
        tree.add(this);
    }

}