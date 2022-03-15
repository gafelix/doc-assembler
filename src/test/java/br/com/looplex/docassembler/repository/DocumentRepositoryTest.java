package br.com.looplex.docassembler.repository;

import br.com.looplex.docassembler.model.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@DataJpaTest
@ActiveProfiles("test")
public class DocumentRepositoryTest {

    private DocumentRepository documentRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public void setDocumentRepository(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Test
    public void shouldFindDocumentById() {
        Document document = new Document("Empty root node", null);
        entityManager.persist(document);
        Long id = Long.valueOf(1);
        Optional<Document> foundDocument = documentRepository.findById(id);
        Assert.isTrue(foundDocument.isPresent(), "Document doesn't exist.");
    }

}
