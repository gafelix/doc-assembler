package br.com.looplex.docassembler.service.printer;

import br.com.looplex.docassembler.model.Document;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


@Service("preorder")
public class DocumentPrinterPreorder implements DocumentPrinter {

    private List<String> nodes;

    public DocumentPrinterPreorder() {
        this.nodes = new ArrayList<>();
    }

    public void print(Document document) {
        nodes.add(document.getText());
        List<Document> children = document.getChildren();
        if(Objects.nonNull(children))
            for(Document child : children) {
                print(child);
            }
    }

    @Override
    public String display(Document document) {
        print(document);
        return Arrays.toString(nodes.toArray());
    }

}