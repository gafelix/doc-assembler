package br.com.looplex.docassembler.service;

import br.com.looplex.docassembler.exceptions.custom.DocumentBadRequestException;
import br.com.looplex.docassembler.exceptions.custom.DocumentNotFoundException;
import br.com.looplex.docassembler.model.Document;
import br.com.looplex.docassembler.repository.DocumentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;

@AllArgsConstructor
@Service
public class RepositoriesManager {

    private DocumentRepository documentRepository;

    public Document createDocument(Document document) throws DocumentBadRequestException {
        Document savedDocument;
        try {
            savedDocument = documentRepository.save(document);
        } catch (ConstraintViolationException exception) {
            throw new DocumentBadRequestException("Request for creating new document is invalid. Check if one or more parameters are missing.");
        }
        return savedDocument;
    }

    public Document findById(Long id) throws DocumentNotFoundException {
        return documentRepository.findById(id).orElseThrow(
                () -> new DocumentNotFoundException(String.format("Document of ID %s was not found. Have you created the resource?", id))
        );
    }

}
