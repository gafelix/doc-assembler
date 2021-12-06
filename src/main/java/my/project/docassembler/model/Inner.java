package my.project.docassembler.model;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

public class Inner implements Document {

    @Getter @Setter private List<Document> nodes;

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
