package br.com.looplex.docassembler.controller;

import br.com.looplex.docassembler.model.Document;
import br.com.looplex.docassembler.service.DocumentService;
import br.com.looplex.docassembler.service.dto.DocumentTreeDto;
import br.com.looplex.docassembler.service.form.DocumentForm;
import br.com.looplex.docassembler.service.mapper.DocumentMapper;
import br.com.looplex.docassembler.service.printer.DocumentPrinterPicker;
import br.com.looplex.docassembler.service.printer.DocumentPrinterStrategy;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;


@AllArgsConstructor
@RestController
@RequestMapping("/document")
public class DocumentController {

    private DocumentService documentService;

    @PostMapping
    @Transactional
    public ResponseEntity<Document> createDocument(@Valid @RequestBody DocumentForm documentForm) {
        Document document = documentService.createDocument(documentForm);
        URI location = URI.create("/document/" + document.getId());
        return ResponseEntity
                .created(location)
                .body(document);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentTreeDto> viewDocument(@PathVariable Long id, @Valid @RequestParam(defaultValue = "PREORDER") DocumentPrinterStrategy strategy) {
        DocumentTreeDto documentTreeDto = documentService.displayDocumentTree(id, strategy);
        return ResponseEntity
                .ok()
                .body(documentTreeDto);
    }

}