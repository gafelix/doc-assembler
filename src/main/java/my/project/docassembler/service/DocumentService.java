package my.project.docassembler.service;

import my.project.docassembler.model.Document;
import org.springframework.stereotype.Service;

@Service
public interface DocumentService {

    String view(Document document);

}
