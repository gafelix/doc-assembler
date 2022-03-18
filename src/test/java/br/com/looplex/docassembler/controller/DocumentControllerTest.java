package br.com.looplex.docassembler.controller;

import br.com.looplex.docassembler.model.Document;
import br.com.looplex.docassembler.repository.DocumentRepository;
import lombok.AllArgsConstructor;
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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.net.URI;
import java.util.Arrays;
import java.util.List;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class DocumentControllerTest {

    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private MockMvc mockMvc;
    @PersistenceContext
    private EntityManager entityManager;

    @BeforeEach
    public void createTestTree() {
        List<Document> children = Arrays.asList(
                Document.builder()
                        .children(Arrays.asList(
                                Document.builder()
                                        .text("4")
                                        .build(),
                                Document.builder()
                                        .text("5")
                                        .build()
                        ))
                        .text("2")
                        .build(),
                Document.builder()
                        .text("3")
                        .build()
        );
        Document document = Document.builder()
                .children(children)
                .text("1")
                .build();
        documentRepository.save(document);
    }

    @AfterEach
    public void resetDocumentIdIncrementation() {
        entityManager.createNativeQuery("ALTER TABLE document ALTER COLUMN id RESTART WITH 1").executeUpdate();
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
        URI uri = URI.create("/document/1?strategy=PREORDER");
        String expectedResponse = "{\"tree\": \"[1, 2, 4, 5, 3]\", \"strategy\": \"PREORDER\"}";
        mockMvc.perform(MockMvcRequestBuilders.get(uri))
                .andExpect(MockMvcResultMatchers
                        .content().json(expectedResponse));
    }

    @Test
    public void shouldGetDocumentByIdAndDisplayPostorderTree() throws Exception {
        URI uri = URI.create("/document/1?strategy=POSTORDER");
        String expectedResponse = "{\"tree\": \"[4, 5, 2, 3, 1]\", \"strategy\": \"POSTORDER\"}";
        mockMvc.perform(MockMvcRequestBuilders.get(uri))
                .andExpect(MockMvcResultMatchers
                        .content().json(expectedResponse));
    }

}