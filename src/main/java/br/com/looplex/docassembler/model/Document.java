package br.com.looplex.docassembler.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Document {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @OneToMany
    private List<Document> children;
    private String text;

}
