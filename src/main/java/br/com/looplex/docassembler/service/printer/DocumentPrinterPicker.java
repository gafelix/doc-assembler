package br.com.looplex.docassembler.service.printer;

import br.com.looplex.docassembler.model.Document;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentPrinterPicker {

    private BeanFactory beanFactory;
    private DocumentPrinter documentPrinter;

    @Autowired
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public String printTree(Document document, String strategy) {
        documentPrinter = beanFactory.getBean(strategy, DocumentPrinter.class);
        return documentPrinter.display(document);
    }

}
