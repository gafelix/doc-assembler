package my.project.docassembler.model;
import com.fasterxml.jackson.annotation.JsonSubTypes;

@JsonSubTypes({@JsonSubTypes.Type(value = Inner.class, name = "inner"),
                @JsonSubTypes.Type(value = Leaf.class, name = "leaf")})
public interface Document {

    public String getText();

}
