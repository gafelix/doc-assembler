package br.com.looplex.docassembler.controller;

import br.com.looplex.docassembler.model.Document;
import br.com.looplex.docassembler.repository.DocumentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.util.Arrays;
import java.util.List;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@AutoConfigureTestEntityManager
@Transactional
public class DocumentControllerTest {

    private DocumentRepository documentRepository;
    private TestEntityManager testEntityManager;
    private MockMvc mockMvc;

    @Autowired
    public void setDocumentRepository(DocumentRepository documentRepository) { this.documentRepository = documentRepository; }

    @Autowired
    public void setTestEntityManager(TestEntityManager testEntityManager) { this.testEntityManager = testEntityManager; }

    @Autowired
    public void setMockMvc(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @BeforeEach
    public void createTestTree() {
        List<Document> children = Arrays.asList(
                new Document("2",
                        Arrays.asList(new Document("4", null),
                                new Document("5", null))),
                new Document("3", null));
        Document document = new Document("1", children);
        testEntityManager.persist(document);
    }

    @AfterEach
    public void resetDocumentIdIncrementation() {
        testEntityManager.getEntityManager().createNativeQuery("ALTER TABLE document ALTER COLUMN id RESTART WITH 1").executeUpdate();
    }

    @Test
    public void shouldPostDocumentTree() throws Exception {
        URI uri = URI.create("/document");
        String body = "{\"text\": \"1\", \"children\": [{\"text\": \"2\", \"children\": [{\"text\": \"4\", \"children\": []}, {\"text\": \"5\", \"children\": []}]}, {\"text\": \"3\", \"children\": []}]}";
        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType("application/json").content(body))
                .andExpect(MockMvcResultMatchers
                .status().isCreated());
    }

    @Test
    public void shouldGetDocumentByIdAndDisplayPreorderTree() throws Exception {
        URI uri = URI.create("/document/1?strategy=preorder");
        String expectedResponse = "{\"tree\": \"[1, 2, 4, 5, 3]\", \"strategy\": \"preorder\"}";
        mockMvc.perform(MockMvcRequestBuilders.get(uri))
                .andExpect(MockMvcResultMatchers
                        .content().json(expectedResponse));
    }

    @Test
    public void shouldGetDocumentByIdAndDisplayPostorderTree() throws Exception {
        URI uri = URI.create("/document/1?strategy=postorder");
        String expectedResponse = "{\"tree\": \"[4, 5, 2, 3, 1]\", \"strategy\": \"postorder\"}";
        mockMvc.perform(MockMvcRequestBuilders.get(uri))
                .andExpect(MockMvcResultMatchers
                        .content().json(expectedResponse));
    }

}