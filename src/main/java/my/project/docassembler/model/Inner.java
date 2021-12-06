package my.project.docassembler.model;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@JsonTypeName("inner")
public class Inner implements Document {

    @Getter private List<Document> documents;

    @Override
    public String getText() {
        String nodesText = "";
        for(Document document: this.getDocuments()) {
            nodesText += document.getText() + " ";
        }
        return nodesText;
    }

    public void add(Document document) {
        this.getDocuments().add(document);
    }

    public void remove(Document document) {
        this.getDocuments().remove(document);
    }

}
