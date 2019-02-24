package ru.evgeny.start;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.evgeny.quize.impl.Quize;
import ru.evgeny.quize.interfaces.IQuize;


public class Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.refresh();
        IQuize quize = context.getBean(Quize.class);

        quize.start();
        quize.getResult();
    }
}
