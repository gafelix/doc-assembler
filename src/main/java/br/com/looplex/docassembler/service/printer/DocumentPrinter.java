package br.com.looplex.docassembler.service.printer;

import br.com.looplex.docassembler.model.Document;
import br.com.looplex.docassembler.model.InternalDocument;

import java.util.List;


public interface DocumentPrinter {

    void saveTree(InternalDocument internalDocument, List<Document> tree, DocumentPrinter documentPrinter);

}
