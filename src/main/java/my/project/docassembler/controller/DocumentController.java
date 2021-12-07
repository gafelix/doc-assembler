package my.project.docassembler.controller;
import lombok.AllArgsConstructor;
import lombok.Setter;
import my.project.docassembler.model.Document;
import my.project.docassembler.service.DocumentService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController //Declara @Controller + @ResponseBody
@RequestMapping("/document")
@AllArgsConstructor
public class DocumentController {

    private DocumentService documentService;

    @PostMapping("/send")
    public ResponseEntity<String> getDocument(@RequestBody Document document, @RequestParam String strategy) {
        return new ResponseEntity<>(documentService.view(document), HttpStatus.OK);
    }

    //public DocumentController(@Qualifier("name") DocumentService documentService) {
    //    this.documentService = documentService;
    //}
}
