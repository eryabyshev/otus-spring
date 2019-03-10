package ru.evgeny.otus_spring4.domain;

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


    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s",
                name,
                author.getFirstname() + " " + author.getLastname(),
                genre.getName(),
                publisher.getName());
    }
}
