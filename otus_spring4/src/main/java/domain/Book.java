package domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter

public class Book {
    private long id;
    private String  name;
    private Author author;
    private Genre genre;
    private Publisher publisher;

}
