package dao.impl;

import com.sun.org.apache.bcel.internal.generic.LASTORE;
import dao.interfaces.AuthorDao;
import dao.interfaces.BookDao;
import dao.interfaces.GenreDao;
import dao.interfaces.PublisherDao;
import data.sorce.EmbeddedDataSource;
import domain.Author;
import domain.Book;
import domain.Genre;
import domain.Publisher;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static java.util.Collections.singletonList;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BookDaoJdbcTest {

    private DriverManagerDataSource dataSource;
    private JdbcOperations operations;

    @Mock
    private AuthorDao authorDao;
    @Mock
    private GenreDao genreDao;
    @Mock
    private PublisherDao publisherDao;
    private BookDao dao;
    private static final int COUNT = 200;


    @Before
    public void init() {
        initDataSorce();
        initMock();
        initAuthorDaoMock();
        initPublisherDaoMock();
        initGenreDaoMock();
    }


    private void initDataSorce() {
        dataSource = EmbeddedDataSource.getDataSource();
        operations = new JdbcTemplate(dataSource);
        dao = new BookDaoJdbc(operations, authorDao, genreDao, publisherDao);
    }

    private void initMock() {
        MockitoAnnotations.initMocks(this);

    }

    private static final String FIRST_NAME = "FirstName";
    private static final String LAST_NAME = "LastName";
    private static final long ID = 1l;

    private void initAuthorDaoMock() {
        authorDao = mock(authorDao.getClass());
        when(authorDao.getById(anyLong())).thenReturn(new Author(ID, FIRST_NAME, LAST_NAME));
        when(authorDao.getByLastName(anyString())).thenReturn(singletonList(new Author(ID, FIRST_NAME, LAST_NAME)));
        when(authorDao.getByName(anyString())).thenReturn(singletonList(new Author(ID, FIRST_NAME, LAST_NAME)));
        when(authorDao.getById(anyLong())).thenReturn(new Author(ID, FIRST_NAME, LAST_NAME));
    }

    private static final String PUBLISHER_NAME = "Publisher";
    private static final String ADDRESS = "Address";
    private static final String PHONE = "999-999-999";


    private void initPublisherDaoMock() {
        publisherDao = mock(publisherDao.getClass());
        when(publisherDao.getById(anyLong())).thenReturn(new Publisher(ID, PUBLISHER_NAME, ADDRESS, PHONE));
        when(publisherDao.getByName(anyString())).thenReturn(getPublisherList());
    }

    private static List<Publisher> getPublisherList() {
        Random r = new Random();
        int randomNmber = r.nextInt(COUNT);
        List<Publisher> list = new ArrayList<>();
        for (int i = 0; i < randomNmber; i++) {
            list.add(new Publisher(i, PUBLISHER_NAME + i, ADDRESS + i, PHONE + i));
        }
        return list;
    }


    private static final String GENRE = "Genre";

    private void initGenreDaoMock() {
        genreDao = mock(genreDao.getClass());
        when(genreDao.getById(anyLong())).thenReturn(new Genre(ID, GENRE));
        when(genreDao.getByName(anyString())).thenReturn(new Genre(ID, GENRE));
    }


    @After
    public void dropTable() {
        dao.dropTable();
    }


    @Test
    public void TestX_getById() {
        System.out.println(getClass().getName() + " getById()");
        List<Book> bookList = getBookMookList();
        dao.createTable();
        bookList.forEach(book -> dao.insert(book));
        int number = bookList.size() - 1;
        assertEquals(dao.getById(number).getId(), number);
    }

    @Test
    public void TestX_getByAuthor() {
        System.out.println(getClass().getName() + " getByAuthor()");
        List<Book> bookList = getBookMookList();
        dao.createTable();
        bookList.forEach(book -> dao.insert(book));
        int number = bookList.size();
        List<Book> result = dao.getByAuthor(new Author(ID, FIRST_NAME, LAST_NAME));
        assertTrue(result.stream().allMatch(book -> book.getAuthor().getId() == ID));
    }

    @Test
    public void TestX_getByName() {
        System.out.println(getClass().getName() + " getByName()");
        List<Book> bookList = getBookMookList();
        dao.createTable();
        bookList.forEach(book -> dao.insert(book));
        int number = bookList.size();
        List<Book> result = dao.getByName(BOOK + number);
        assertTrue(result.stream().allMatch(book -> book.getName().equals(BOOK + number)));
    }



    @Test
    public void TestX_getByPublisher() {
        System.out.println(getClass().getName() + " getByPublisher()");
        List<Book> bookList = getBookMookList();
        dao.createTable();
        bookList.forEach(book -> dao.insert(book));
        int number = bookList.size();
        List<Book> result = dao.getByPublisher(new Publisher(number, PUBLISHER_NAME, ADDRESS, PHONE));
        assertTrue(result.stream().allMatch(r -> r.getPublisher().getId() == number));
    }

    @Test
    public void TestB_insert() {
        System.out.println(getClass().getName() + " insert()");
        List<Book> bookList = getBookMookList();
        dao.createTable();
        bookList.forEach(book -> dao.insert(book));
        assertEquals(bookList.size(), dao.count());
    }

    private static final String BOOK = "Book";

    private static List<Book> getBookMookList() {
        Random random = new Random();
        int randomNumber = random.nextInt(COUNT);
        List<Book> bookList = new ArrayList<>();
        for (int i = 0; i < randomNumber; i++) {
            Author author = new Author(i, FIRST_NAME + i, LAST_NAME + i);
            Genre genre = new Genre(i, "Genre" + i);
            Publisher publisher = new Publisher(i, PUBLISHER_NAME + i, ADDRESS + i, PHONE);
            Book book = new Book(i, BOOK + i, author, genre, publisher);
            bookList.add(book);
        }
        return bookList;
    }


    @Test
    public void TestA_createTable() {
        System.out.println(getClass().getName() + " createTable()");
        dao.createTable();
    }
}