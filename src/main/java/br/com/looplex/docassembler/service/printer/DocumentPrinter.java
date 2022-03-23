package br.com.looplex.docassembler.service.printer;

import br.com.looplex.docassembler.service.traversal.DocumentTraversal;
import br.com.looplex.docassembler.service.traversal.InternalDocumentTraversal;

import java.util.List;


public interface DocumentPrinter {

    void saveTree(InternalDocumentTraversal internalDocumentTraversal,
                  List<DocumentTraversal> tree,
                  DocumentPrinter documentPrinter);

}