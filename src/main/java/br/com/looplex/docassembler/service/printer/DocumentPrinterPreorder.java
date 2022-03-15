package br.com.looplex.docassembler.service.printer;

import br.com.looplex.docassembler.model.Document;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service("preorder")
public class DocumentPrinterPreorder implements DocumentPrinter {

    private String allNodes;

    public DocumentPrinterPreorder() {
        this.allNodes = "";
    }

    @Override
    public String print(Document document) {
        allNodes += document.getText() + " ";
        List<Document> children = document.getChildren();
        if(Objects.nonNull(children))
            for(Document child : children) {
                print(child);
            }
        return allNodes.substring(0, allNodes.length() - 1);
    }

}
