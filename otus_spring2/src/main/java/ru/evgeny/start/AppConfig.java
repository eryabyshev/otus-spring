package ru.evgeny.start;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import ru.evgeny.quize.impl.CsvQuestionLoader;
import ru.evgeny.quize.impl.Quize;
import ru.evgeny.quize.interfaces.IQuestionLoader;
import ru.evgeny.quize.interfaces.IQuize;

@PropertySource("classpath:properties.properties")
@Configuration
public class AppConfig {

    @Bean
    IQuestionLoader iQuestionLoader(@Value("${csv.path}")String path) {
        return new CsvQuestionLoader(path);
    }
    @Bean
    IQuize iQuize(IQuestionLoader iQuestionLoader) {
        return new Quize(iQuestionLoader);
    }


    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
