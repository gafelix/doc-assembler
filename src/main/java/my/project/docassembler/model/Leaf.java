package my.project.docassembler.model;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.Setter;

@JsonTypeName("leaf")
public class Leaf implements Document {

    @Setter @Getter private String text;

}
