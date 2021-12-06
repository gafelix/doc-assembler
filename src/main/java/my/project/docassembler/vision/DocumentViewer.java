package my.project.docassembler.vision;

import lombok.Getter;
import my.project.docassembler.model.Document;
import org.springframework.beans.factory.annotation.Autowired;

public class DocumentViewer {

    @Getter private Document document;

    @Autowired
    public DocumentViewer(Document document) {
        this.document = document;
    }

}
