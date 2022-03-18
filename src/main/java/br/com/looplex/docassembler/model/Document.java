package br.com.looplex.docassembler.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Document> children;
    @NonNull
    private String text;

}