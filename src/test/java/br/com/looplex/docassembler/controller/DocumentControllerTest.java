package br.com.looplex.docassembler.controller;

import br.com.looplex.docassembler.model.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class DocumentControllerTest {

    @PersistenceContext
    private EntityManager entityManager;
    private MockMvc mockMvc;

    @Autowired
    public void setMockMvc(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    @Transactional
    public void shouldGetDocumentByIdAndDisplayTree() throws Exception{
        URI uri = URI.create("/document/1?strategy=preorder");
        List<Document> children = Arrays.asList(
                new Document("Leaf one", null),
                new Document("Leaf two", null));
        Document document = new Document("Root node", children);
        entityManager.persist(document);
        String expectedResponse = "{\"tree\": \"Root node Leaf one Leaf two\", \"strategy\": \"preorder\"}";
        mockMvc.perform(MockMvcRequestBuilders.get(uri))
                .andExpect(MockMvcResultMatchers
                        .content().json(expectedResponse));
    }
}
