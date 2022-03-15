package br.com.looplex.docassembler.controller;

import br.com.looplex.docassembler.model.Document;
import br.com.looplex.docassembler.repository.DocumentRepository;
import br.com.looplex.docassembler.service.dto.DocumentTreeDto;
import br.com.looplex.docassembler.service.form.DocumentForm;
import br.com.looplex.docassembler.service.mapper.DocumentMapper;
import br.com.looplex.docassembler.service.printer.DocumentPrinterPicker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;


@RestController
@RequestMapping("/document")
public class DocumentController {

    private DocumentMapper documentMapper;
    private DocumentPrinterPicker documentPrinterPicker;
    private DocumentRepository documentRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public void setDocumentMapper(DocumentMapper documentMapper) {
        this.documentMapper = documentMapper;
    }

    @Autowired
    public void setDocumentPrinter(DocumentPrinterPicker documentPrinterPicker) {
        this.documentPrinterPicker = documentPrinterPicker;
    }

    @Autowired
    public void setDocumentRepository(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DocumentForm> createDocument(@Valid @RequestBody DocumentForm documentForm) {
        Document document = documentMapper.formToEntity(documentForm);
        entityManager.persist(document);
        URI uri = URI.create("/document/" + document.getId());
        return ResponseEntity
                .created(uri)
                .body(documentForm);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentTreeDto> viewDocument(@PathVariable Long id, @RequestParam(defaultValue = "preorder") String strategy) {
        Optional<Document> document = documentRepository.findById(id);
        if(document.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        String tree = documentPrinterPicker.printTree(document.get(), strategy);
        return ResponseEntity.ok().body(new DocumentTreeDto(tree, strategy));
    }
}