package br.com.looplex.docassembler.controller;

import br.com.looplex.docassembler.model.Document;
import br.com.looplex.docassembler.repository.DocumentRepository;
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

    private DocumentMapper documentMapper;
    private DocumentPrinterPicker documentPrinterPicker;
    private DocumentRepository documentRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DocumentForm> createDocument(@Valid @RequestBody DocumentForm documentForm) {
        Document document = documentMapper.formToEntity(documentForm);
        documentRepository.save(document);
        URI uri = URI.create("/document/" + document.getId());
        return ResponseEntity
                .created(uri)
                .body(documentForm);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentTreeDto> viewDocument(@PathVariable Long id, @RequestParam(defaultValue = "PREORDER") DocumentPrinterStrategy strategy) {
        Document document = documentRepository.findById(id).orElseThrow();
        String tree = documentPrinterPicker.printTree(document, strategy);
        return ResponseEntity.ok().body(new DocumentTreeDto(tree, strategy.name()));
    }

}