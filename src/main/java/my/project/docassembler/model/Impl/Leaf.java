package my.project.docassembler.model.Impl;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.Setter;
import my.project.docassembler.model.Document;

@JsonTypeName("leaf")
public class Leaf implements Document {

    @Setter @Getter private String text;

    public Leaf(String text) {
        this.text = text;
    }

}
