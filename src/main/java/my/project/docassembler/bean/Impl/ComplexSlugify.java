package my.project.docassembler.bean.Impl;

import my.project.docassembler.bean.SlugifyBean;
import org.springframework.stereotype.Component;

public class ComplexSlugify implements SlugifyBean {

    @Override
    public String toSlug(String text) {
        return text + "logica diferente";
    }
}
