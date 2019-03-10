package ru.evgeny.otus_spring4.servise.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.evgeny.otus_spring4.dao.interfaces.AuthorDao;
import ru.evgeny.otus_spring4.dao.interfaces.BookDao;
import ru.evgeny.otus_spring4.dao.interfaces.GenreDao;
import ru.evgeny.otus_spring4.dao.interfaces.PublisherDao;
import ru.evgeny.otus_spring4.domain.Author;
import ru.evgeny.otus_spring4.domain.Book;
import ru.evgeny.otus_spring4.domain.Genre;
import ru.evgeny.otus_spring4.domain.Publisher;
import ru.evgeny.otus_spring4.servise.interfaces.IShellDataBaseServise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShellLibraryService implements IShellDataBaseServise {

    private AuthorDao authorDao;
    private BookDao bookDao;
    private GenreDao genreDao;
    private PublisherDao publisherDao;


    @Autowired
    public ShellLibraryService(AuthorDao authorDao, BookDao bookDao, GenreDao genreDao, PublisherDao publisherDao) {
        this.authorDao = authorDao;
        this.bookDao = bookDao;
        this.genreDao = genreDao;
        this.publisherDao = publisherDao;
    }


    @Override
    public void initTables() {
        initAllTables();
        fillGenreTable();
        fillAuthorTable();
        fillPublisherTable();
        fillBookTable();
    }

    //continue -> https://github.com/evgenyrybishew/otus-spring/tree/master/otus_spring4

    private void initAllTables() {
        authorDao.dropTable();
        bookDao.dropTable();
        genreDao.dropTable();
        publisherDao.dropTable();

        authorDao.createTable();
        bookDao.createTable();
        genreDao.createTable();
        publisherDao.createTable();
    }


    private void fillGenreTable() {
        genreDao.insert(genreMap.get(BUSINESS_LITERATURE));
        genreDao.insert(genreMap.get(DETECTIVES_AND_THRILLERS));
        genreDao.insert(genreMap.get(DOCUMENTARY_LITERATURE));
        genreDao.insert(genreMap.get(DRAMATURGY));
        genreDao.insert(genreMap.get(ART_ART_HISTORY_DESIGN));
        genreDao.insert(genreMap.get(COMPUTERS_AND_INTERNET));
        genreDao.insert(genreMap.get(SCIENCE_EDUCATION));
    }

    private void fillAuthorTable() {
        authorDao.insert(authorMap.get(AUTHOR1));
        authorDao.insert(authorMap.get(AUTHOR2));
        authorDao.insert(authorMap.get(AUTHOR3));
        authorDao.insert(authorMap.get(AUTHOR4));
        authorDao.insert(authorMap.get(AUTHOR5));
    }

    private void fillPublisherTable() {
        publisherDao.insert(publisher);
    }

    private void fillBookTable() {
        bookDao.insert(new Book(1, "Стартап без бюджета",
                authorMap.get(AUTHOR1),
                genreMap.get(BUSINESS_LITERATURE),
                publisher));

        bookDao.insert(new Book(2, "Доставляя счастье",
                authorMap.get(AUTHOR2),
                genreMap.get(BUSINESS_LITERATURE),
                publisher));

        bookDao.insert(new Book(3, "«Убийство на улице Морг»",
                authorMap.get(AUTHOR3),
                genreMap.get(DETECTIVES_AND_THRILLERS),
                publisher));

        bookDao.insert(new Book(4, "Рассказы о Шерлоке Холмсе",
                authorMap.get(AUTHOR4),
                genreMap.get(DETECTIVES_AND_THRILLERS),
                publisher));

        bookDao.insert(new Book(5, "Linux API. Исчерпывающее руководство",
                authorMap.get(AUTHOR5),
                genreMap.get(COMPUTERS_AND_INTERNET),
                publisher));
    }

    private static final String BUSINESS_LITERATURE = "Business literature";
    private static final String DETECTIVES_AND_THRILLERS = "Detectives and Thrillers";
    private static final String DOCUMENTARY_LITERATURE = "Documentary literature";
    private static final String DRAMATURGY = "DRAMATURGY";
    private static final String ART_ART_HISTORY_DESIGN = "Art, Art History, Design";
    private static final String COMPUTERS_AND_INTERNET = "Computers and Internet";
    private static final String SCIENCE_EDUCATION = "Science Education";

    private static final String PUBLISHER = "Test Publisher";
    private static final String ADDRESS = "4 25st Some";
    private static final String PHONE = "123-456-789";

    private static final Publisher publisher = new Publisher(1, PUBLISHER, ADDRESS, PHONE);

    private static final Map<String, Genre> genreMap = new HashMap<String, Genre>(){{
        genreMap.put(BUSINESS_LITERATURE, new Genre(1, BUSINESS_LITERATURE));
        genreMap.put(DETECTIVES_AND_THRILLERS, new Genre(2, DETECTIVES_AND_THRILLERS));
        genreMap.put(DOCUMENTARY_LITERATURE, new Genre(3, DOCUMENTARY_LITERATURE));
        genreMap.put(DRAMATURGY, new Genre(4, DRAMATURGY));
        genreMap.put(ART_ART_HISTORY_DESIGN, new Genre(5, ART_ART_HISTORY_DESIGN));
        genreMap.put(COMPUTERS_AND_INTERNET, new Genre(6, COMPUTERS_AND_INTERNET));
        genreMap.put(SCIENCE_EDUCATION, new Genre(7, SCIENCE_EDUCATION));

    }};

    private static final String AUTHOR1 = "Майк Микаловиц";
    private static final String AUTHOR2 = "Тони Шей";
    private static final String AUTHOR3 = "Эдгар Аллан По";
    private static final String AUTHOR4 = "Артур Конан Дойл";
    private static final String AUTHOR5 = "Керриск М.";


    private static Map<String, Author> authorMap = new HashMap<String, Author>(){{
        authorMap.put(AUTHOR1, new Author(1, AUTHOR1.split(" ")[0], AUTHOR1.split(" ")[1]));
        authorMap.put(AUTHOR2, new Author(1, AUTHOR2.split(" ")[0], AUTHOR2.split(" ")[1]));
        authorMap.put(AUTHOR3, new Author(1, AUTHOR3.split(" ")[0], AUTHOR3.split(" ")[1] + AUTHOR3.split(" ")[2]));
        authorMap.put(AUTHOR4, new Author(1, AUTHOR4.split(" ")[0], AUTHOR4.split(" ")[1] + AUTHOR4.split(" ")[2]));
        authorMap.put(AUTHOR5, new Author(1, AUTHOR4.split(" ")[0], AUTHOR4.split(" ")[1]));
    }};


    @Override
    public void searchBookByName(String name) {
        printBookInfo(bookDao.getByName(name));
    }

    @Override
    public void searchBookByAuthorName(String name) {
        printBookInfo(byAuthor(authorDao.getByName(name)));
    }

    private List<Book> byAuthor(List<Author> authors) {
        List<Book> bookList = new ArrayList<>();
        for(Author author : authors) {
            bookList.addAll(bookDao.getByAuthor(author));
        }
        return bookList;
    }

    @Override
    public void searchBookByAuthorLastName(String lastname) {
        printBookInfo(byAuthor(authorDao.getByLastName(lastname)));
    }


    @Override
    public void searchBookByPublisher(String name) {
        printBookInfo(bookDao.getByPublisher(new Publisher(0, name, null, null)));
    }

    @Override
    public void addNewBook() {
        System.out.println("It's not work now!!!");

    }

    private void printBookInfo(List<Book> books) {
        for (int i = 0; i < books.size(); i++)
            System.out.println(i + 1  + ". " + books.get(i).toString());

    }
}
