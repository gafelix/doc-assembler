package br.com.looplex.docassembler.service.printer;

import br.com.looplex.docassembler.model.Document;
import br.com.looplex.docassembler.model.InternalDocument;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("leftside")
public class DocumentPrinterLeftside implements DocumentPrinter {

    public void saveTree(InternalDocument internalDocument, List<Document> tree, DocumentPrinter documentPrinter) {
        List<Document> children = internalDocument.getChildren();
        for(Document child : children) {
            child.traverse(tree, documentPrinter);
        }
    }

}