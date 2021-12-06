package my.project.docassembler;
import lombok.Getter;
import lombok.Setter;

public class Leaf implements Document {

    @Setter @Getter private String text;

}
