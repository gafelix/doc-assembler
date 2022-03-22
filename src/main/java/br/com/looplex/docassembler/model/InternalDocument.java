package br.com.looplex.docassembler.model;

import br.com.looplex.docassembler.service.printer.DocumentPrinter;
import br.com.looplex.docassembler.service.printer.DocumentPrinterPicker;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

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

    @Transient
    public void traverse(List<Document> tree, DocumentPrinter documentPrinter) {
        documentPrinter.saveTree(this, tree, documentPrinter);
    }

}