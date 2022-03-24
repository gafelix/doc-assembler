package br.com.looplex.docassembler.repository;

import br.com.looplex.docassembler.model.Document;
import br.com.looplex.docassembler.model.LeafDocument;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.Assert;

import java.util.Optional;


@DataJpaTest
@ActiveProfiles("test")
public class DocumentRepositoryTest {

    @Autowired
    private DocumentRepository documentRepository;

    @Test
    public void shouldFindDocumentById() {
        Document document = LeafDocument.builder()
                        .text("Empty root node")
                        .build();
        documentRepository.save(document);
        Optional<Document> foundDocument = documentRepository.findById(1L);
        Assert.isTrue(foundDocument.isPresent(), "Document doesn't exist.");
    }

}