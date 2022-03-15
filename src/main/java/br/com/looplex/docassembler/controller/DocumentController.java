package br.com.looplex.docassembler.controller;

import br.com.looplex.docassembler.model.Document;
import br.com.looplex.docassembler.service.dto.DocumentDto;
import br.com.looplex.docassembler.service.mapper.DocumentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/document")
public class DocumentController {

    private DocumentMapper documentMapper;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public void setDocumentMapper(DocumentMapper documentMapper) {
        this.documentMapper = documentMapper;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<HttpStatus> createDocument(@Valid @RequestBody DocumentDto documentDto) {
        Document document = documentMapper.dtoToEntity(documentDto);
        entityManager.persist(document);
        return ResponseEntity.ok().build();
    }

}
