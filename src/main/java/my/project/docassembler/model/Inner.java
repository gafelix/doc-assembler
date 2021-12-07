package my.project.docassembler.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;

import java.util.List;

@JsonTypeName("inner")
public class Inner implements Document {

    @JsonProperty("children") @Getter private List<Document> documents;

    @Override
    public String getText() {
        String nodesText = "";
        for(Document document: this.getDocuments()) { nodesText += document.getText() + " "; }
        return nodesText;
    }

}
