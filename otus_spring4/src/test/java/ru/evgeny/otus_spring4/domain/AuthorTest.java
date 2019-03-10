package ru.evgeny.otus_spring4.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class AuthorTest {

    @Test
    public void hashCodeTest() {
        Author a = new Author(1, "TestA", "TestA");
        Author b = new Author(1, "TestA", "TestA");
        assertEquals(a, b);
        Author c = new Author(2, "TestA", "TestA");
        assertNotEquals(a, c);
        Author d = new Author(1, "TestB", "TestA");
        assertNotEquals(a, d);
        Author e = new Author(1, "TestA", "TestB");
        assertNotEquals(a, e);
    }
}