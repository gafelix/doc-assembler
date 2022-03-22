package br.com.looplex.docassembler.controller;

import br.com.looplex.docassembler.model.Document;
import br.com.looplex.docassembler.model.InternalDocument;
import br.com.looplex.docassembler.model.LeafDocument;
import br.com.looplex.docassembler.repository.DocumentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
                InternalDocument.builder()
                        .children(Arrays.asList(
                                LeafDocument.builder()
                                        .text("1")
                                        .build(),
                                LeafDocument.builder()
                                        .text("2")
                                        .build()
                        ))
                        .build(),
                LeafDocument.builder()
                        .text("3")
                        .build()
        );
        InternalDocument internalDocument = InternalDocument.builder()
                .children(children)
                .build();
        documentRepository.save(internalDocument);
    }

    @AfterEach
    public void resetDocumentIdIncrementation() {
        entityManager.createNativeQuery("ALTER TABLE document ALTER COLUMN id RESTART WITH 1").executeUpdate();
    }

    @Test
    public void shouldPostDocumentTree() throws Exception {
        URI uri = URI.create("/document");
        String body = "{\"children\": [{\"children\": [{\"text\": \"1\"}, {\"text\": \"2\"}]}, {\"text\": \"3\"}]}";
        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                        .contentType("application/json").content(body))
                .andExpect(MockMvcResultMatchers
                        .status().isCreated());
    }

    @Test
    public void shouldGetDocumentByIdAndDisplayPreorderTree() throws Exception {
        URI uri = URI.create("/document/1?strategy=LEFTSIDE");
        String expectedResponse = "{\"tree\": \"[1, 2, 3]\", \"strategy\": \"LEFTSIDE\"}";
        mockMvc.perform(MockMvcRequestBuilders.get(uri))
                .andExpect(MockMvcResultMatchers
                        .content().json(expectedResponse));
    }

}