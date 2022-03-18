package br.com.looplex.docassembler.service.printer;

import br.com.looplex.docassembler.model.Document;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


@Service("postorder")
public class DocumentPrinterPostorder implements DocumentPrinter {

    private List<String> nodes;

    public DocumentPrinterPostorder() {
        this.nodes = new ArrayList<>();
    }

    public void print(Document document) {
        List<Document> children = document.getChildren();
        if(Objects.nonNull(children))
            for(Document child : children) {
                print(child);
            }
        nodes.add(document.getText());
    }

    @Override
    public String display(Document document) {
        print(document);
        String tree = Arrays.toString(nodes.toArray());
        nodes.clear();
        return tree;
    }

}