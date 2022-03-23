package br.com.looplex.docassembler.service.printer;

import br.com.looplex.docassembler.model.Document;
import br.com.looplex.docassembler.model.InternalDocument;
import br.com.looplex.docassembler.service.traversal.DocumentTraversal;
import br.com.looplex.docassembler.service.traversal.InternalDocumentTraversal;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("leftside")
public class DocumentPrinterLeftside implements DocumentPrinter {

    public void saveTree(InternalDocumentTraversal internalDocumentTraversal,
                         List<DocumentTraversal> tree,
                         DocumentPrinter documentPrinter) {
        List<DocumentTraversal> children = internalDocumentTraversal.getChildren();
        for(DocumentTraversal child : children) {
            child.traverse(tree, documentPrinter);
        }
    }

}