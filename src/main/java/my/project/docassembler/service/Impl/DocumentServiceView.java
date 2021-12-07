package my.project.docassembler.service.Impl;

import lombok.AllArgsConstructor;
import my.project.docassembler.model.Document;
import my.project.docassembler.service.DocumentService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
@AllArgsConstructor
public class DocumentServiceView implements DocumentService {

    @Override
    public String view(Document document) { return document.getText(); }
}
