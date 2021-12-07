package my.project.docassembler.model;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import my.project.docassembler.model.Impl.Inner;
import my.project.docassembler.model.Impl.Leaf;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.WRAPPER_OBJECT,
        property="type")
@JsonSubTypes({@JsonSubTypes.Type(value= Inner.class, name="inner"),
                @JsonSubTypes.Type(value= Leaf.class, name="leaf")})
public interface Document {

    String getText();

}
