package my.project.docassembler.service.Impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.project.docassembler.bean.SlugifyBean;
import my.project.docassembler.model.Document;
import my.project.docassembler.repository.DocumentRepository;
import my.project.docassembler.service.DocumentService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    @Override
    public String view(Document document) {
        return document.getText();
    }
}
