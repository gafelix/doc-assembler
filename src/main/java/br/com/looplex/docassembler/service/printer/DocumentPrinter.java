package br.com.looplex.docassembler.service.printer;

import br.com.looplex.docassembler.model.Document;

import java.util.List;


public interface DocumentPrinter {

    void saveTree(Document document, List<Document> tree);

}