package br.com.looplex.docassembler.service;

import br.com.looplex.docassembler.exceptions.custom.document.DocumentBadRequestException;
import br.com.looplex.docassembler.exceptions.custom.document.DocumentNotFoundException;
import br.com.looplex.docassembler.model.Document;
import br.com.looplex.docassembler.repository.DocumentRepository;
import br.com.looplex.docassembler.service.dto.DocumentTreeDto;
import br.com.looplex.docassembler.service.form.DocumentForm;
import br.com.looplex.docassembler.service.mapper.DocumentMapper;
import br.com.looplex.docassembler.service.printer.DocumentPrinterPicker;
import br.com.looplex.docassembler.service.printer.DocumentPrinterStrategy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;

@AllArgsConstructor
@Service
public class DocumentService {

    private DocumentRepository documentRepository;
    private DocumentMapper documentMapper;
    private DocumentPrinterPicker documentPrinterPicker;

    public Document createDocument(DocumentForm documentForm) throws DocumentBadRequestException {
        Document savedDocument;
        try {
            savedDocument = documentRepository.save(documentMapper.formToEntity(documentForm));
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

    public DocumentTreeDto displayDocumentTree(Long id, DocumentPrinterStrategy strategy) {
        Document document = findById(id);
        String tree = documentPrinterPicker.printTree(document, strategy);
        return new DocumentTreeDto(tree, strategy.name());
    }

}
