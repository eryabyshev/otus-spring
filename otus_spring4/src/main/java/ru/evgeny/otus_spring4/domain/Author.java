package ru.evgeny.otus_spring4.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Author {
    private long id;
    private String firstname;
    private String lastname;


    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        Author author = (Author) obj;
        return author.id == id && firstname.equals(author.firstname) && lastname.equals(author.lastname);
    }
}
