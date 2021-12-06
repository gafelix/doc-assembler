package my.project.docassembler.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import my.project.docassembler.model.Document;
import my.project.docassembler.model.DocumentWrapper;
import my.project.docassembler.vision.DocumentViewer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/document")
public class DocumentController {

    @PostMapping("/send")
    @ResponseBody
    public ResponseEntity<DocumentWrapper> getDocument(@RequestBody DocumentWrapper documents) {
        documents.getDocuments().forEach(doc -> System.out.println(doc.getText()));
        return new ResponseEntity<>(documents, HttpStatus.OK);
    }

}
