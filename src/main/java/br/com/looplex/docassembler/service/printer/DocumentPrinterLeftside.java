package br.com.looplex.docassembler.service.printer;

import br.com.looplex.docassembler.model.Document;
import br.com.looplex.docassembler.model.InternalDocument;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("leftside")
public class DocumentPrinterLeftside implements DocumentPrinter {

    public void saveTree(Document document, List<Document> tree) {
        if(document instanceof InternalDocument)
            for (Document child : ((InternalDocument) document).getChildren()) {
                saveTree(child, tree);
        } else {
            tree.add(document);
        }
    }

}