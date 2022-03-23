package br.com.looplex.docassembler.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class InternalDocument extends Document {

    @NotNull
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Document> children;

}