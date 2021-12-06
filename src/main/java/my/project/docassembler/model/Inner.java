package my.project.docassembler.model;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@JsonTypeName("inner")
public class Inner implements Document {

    @Getter private List<Document> nodes;

    @Override
    public String getText() {
        String nodesText = "";
        for(Document document: this.getNodes()) {
            nodesText += document.getText() + " ";
        }
        return nodesText;
    }

    public void add(Document document) {
        nodes.add(document);
    }

    public void remove(Document document) {
        nodes.remove(document);
    }
}
