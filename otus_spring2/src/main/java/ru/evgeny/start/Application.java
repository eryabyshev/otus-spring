package ru.evgeny.start;

import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import ru.evgeny.quize.impl.Quize;
import ru.evgeny.quize.interfaces.IQuize;

@ComponentScan(basePackages = "ru.evgeny")
@EnableAspectJAutoProxy
@Configuration
@PropertySource("classpath:properties.properties")
public class Application {


    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(Application.class);
        context.refresh();
        IQuize quize = context.getBean(IQuize.class);
        quize.start();
        quize.getResult();
    }
}
