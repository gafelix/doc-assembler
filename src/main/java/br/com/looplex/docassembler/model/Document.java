package br.com.looplex.docassembler.model;

import lombok.Getter;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Document> children;
    private String text;

    public Document() {
    }

    public Document(String text, List<Document> children) {
        this.text = text;
        this.children = children;
    }

}