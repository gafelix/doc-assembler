package my.project.docassembler.bean;

import my.project.docassembler.bean.Impl.BasicSlugify;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationContext {

    @Bean
    public BasicSlugify getBasicSlugify() {
        return new BasicSlugify();
    }
}
