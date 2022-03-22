package br.com.looplex.docassembler.model;

import br.com.looplex.docassembler.service.printer.DocumentPrinter;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class LeafDocument extends Document {

    @NonNull
    @NotNull
    private String text;

    public void traverse(List<Document> tree, DocumentPrinter documentPrinter) {
        tree.add(this);
    }

}