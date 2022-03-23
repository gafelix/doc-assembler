package br.com.looplex.docassembler.service.mapper;

import br.com.looplex.docassembler.model.Document;
import br.com.looplex.docassembler.model.InternalDocument;
import br.com.looplex.docassembler.model.LeafDocument;
import br.com.looplex.docassembler.service.form.DocumentForm;
import br.com.looplex.docassembler.service.traversal.DocumentTraversal;
import br.com.looplex.docassembler.service.traversal.InternalDocumentTraversal;
import br.com.looplex.docassembler.service.traversal.LeafDocumentTraversal;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class DocumentMapperImp implements DocumentMapper {

    private boolean doesListExist(List<?> list) {
        return (!Objects.isNull(list) && !list.isEmpty());
    }

    @Override
    public Document toEntity(DocumentForm documentForm) {
        if(!doesListExist(documentForm.getChildren()))
            return new LeafDocument(documentForm.getText());
        else {
            return new InternalDocument(documentForm.getChildren());
        }
    }

    private void convertTree(List<DocumentTraversal> previous, List<Document> children) {
        for(Document child: children) {
            if(child instanceof InternalDocument) {
                ArrayList<DocumentTraversal> documentTraversals = new ArrayList<>();
                convertTree(documentTraversals, ((InternalDocument) child).getChildren());
                previous.add(new InternalDocumentTraversal(documentTraversals));
            }
            if(child instanceof LeafDocument) {
                previous.add(new LeafDocumentTraversal(((LeafDocument) child).getText()));
            }
        }
    }

    private InternalDocumentTraversal toInternalDocumentTraversal(List<Document> children) {
        ArrayList<DocumentTraversal> documentTraversals = new ArrayList<>();
        convertTree(documentTraversals, children);
        return new InternalDocumentTraversal(documentTraversals);
    }

    @Override
    public DocumentTraversal toDocumentTraversal(Document document) {
        if(document instanceof LeafDocument)
            return new LeafDocumentTraversal(((LeafDocument) document).getText());
        if(document instanceof InternalDocument)
            return toInternalDocumentTraversal(((InternalDocument) document).getChildren());
        return null;
    }

}