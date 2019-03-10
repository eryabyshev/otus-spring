package ru.evgeny.otus_spring4.servise.interfaces;

public interface IShellDataBaseServise {
    void initTables();

    void searchBookByName(String name);
    void searchBookByAuthorName(String name);
    void searchBookByAuthorLastName(String lastname);
    void searchBookByPublisher(String name);

    void addNewBook();


}
