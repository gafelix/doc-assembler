package my.project.docassembler.model;
import lombok.Getter;
import lombok.Setter;

public class Leaf implements Document {

    @Setter @Getter private String text;

}
