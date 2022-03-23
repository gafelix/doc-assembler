package br.com.looplex.docassembler.service.printer;

import br.com.looplex.docassembler.service.mapper.DocumentMapper;
import br.com.looplex.docassembler.service.traversal.DocumentTraversal;
import br.com.looplex.docassembler.service.traversal.LeafDocumentTraversal;
import lombok.*;
import org.springframework.beans.factory.BeanFactory;
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
    @NonNull
    private DocumentMapper documentMapper;
    @Getter
    private DocumentPrinter documentPrinter;

    public void setPicker(DocumentPrinterStrategy strategy) {
        documentPrinter = beanFactory.getBean(strategy.value(), DocumentPrinter.class);
    }

    public String printTree(DocumentTraversal documentTraversal) {
        List<DocumentTraversal> documents = new ArrayList<>();
        documentTraversal.traverse(documents, documentPrinter);
        List<String> leafs = documents.stream()
                .map(leaf -> (LeafDocumentTraversal) leaf)
                .map(LeafDocumentTraversal::getText)
                .collect(Collectors.toList());
        String display = Arrays.toString(leafs.toArray());
        leafs.clear();
        return display;
    }

}