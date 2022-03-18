package br.com.looplex.docassembler.service.printer;

import br.com.looplex.docassembler.model.Document;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class DocumentPrinterPicker {

    private BeanFactory beanFactory;

    public String printTree(Document document, DocumentPrinterStrategy strategy) {
        DocumentPrinter documentPrinter = beanFactory.getBean(strategy.value(), DocumentPrinter.class);
        return documentPrinter.display(document);
    }

}