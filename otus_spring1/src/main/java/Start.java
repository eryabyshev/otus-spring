import main.logic.RealizeQuestionPool;
import org.springframework.context.ApplicationContext;
import person.IPerson;
import person.PersonImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

public class Start {
    private  static Scanner IN = new Scanner(System.in);
    private static String UNDEFINDE = "undefind";
    private static String CONTEXT_PATH = "spring-context.xml";
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(CONTEXT_PATH);
        IPerson person = new PersonImpl(UNDEFINDE, UNDEFINDE);
        setNameForPersone(person);

        RealizeQuestionPool realizeQuestionPool = context.getBean(RealizeQuestionPool.class);
        realizeQuestionPool.startProcess().printAnswer();
    }

    private static void setNameForPersone(IPerson persone) {
        System.out.println("Enter name, please: ");
        persone.setFirstName(IN.nextLine());
        System.out.println("Enter last name, please: ");
        persone.setSecondName(IN.nextLine());
    }
}
