package br.com.looplex.docassembler.service.printer;

import br.com.looplex.docassembler.model.Document;
import br.com.looplex.docassembler.model.LeafDocument;
import lombok.*;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class DocumentPrinterPicker {

    @NonNull
    private BeanFactory beanFactory;
    @Getter
    private DocumentPrinter documentPrinter;

    public void setPicker(DocumentPrinterStrategy strategy) {
        documentPrinter = beanFactory.getBean(strategy.value(), DocumentPrinter.class);
    }

    public String printTree(Document document) {
        List<Document> documents = new ArrayList<>();
        document.traverse(documents, getDocumentPrinter());
        List<String> leafs = documents.stream().map(leaf -> (LeafDocument) leaf).map(LeafDocument::getText).collect(Collectors.toList());
        String display = Arrays.toString(leafs.toArray());
        leafs.clear();
        return display;
    }

}