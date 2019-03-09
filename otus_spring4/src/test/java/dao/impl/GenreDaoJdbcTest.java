package dao.impl;

import dao.interfaces.GenreDao;
import data.sorce.EmbeddedDataSource;
import domain.Genre;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GenreDaoJdbcTest {


    private DriverManagerDataSource dataSource;
    private JdbcOperations operations;
    GenreDao dao;
    private static final int COUNT = 200;
    private static final String GENRE = "Genre";
    private static final long ID = 1l;


    @Before
    public void initDataSorce() {
        dataSource = EmbeddedDataSource.getDataSource();
        operations = new JdbcTemplate(dataSource);
        dao = new GenreDaoJdbc(operations);
    }

    @After
    public void dropTable() {
        dao.dropTable();
    }


    @Test
    public void getById() {
        System.out.println(getClass().getName() + "getById()");
        List<Genre> genreList = getMockGenreList();
        dao.createTable();
        genreList.forEach(genre -> dao.insert(genre));
        Genre genre = dao.getById(ID);
        assertEquals(ID, genre.getId());
    }


    @Test
    public void TestX_getByName() {
        System.out.println(getClass().getName() + "getByName()");
        List<Genre> genreList = getMockGenreList();
        dao.createTable();
        genreList.forEach(genre -> dao.insert(genre));
        Genre genre = dao.getByName(GENRE + 1);
        assertEquals(GENRE + 1, genre.getName());
    }


    private static List<Genre> getMockGenreList() {
        List<Genre> genreList = new ArrayList<>();
        Random random = new Random();
        int randomNumber = random.nextInt(COUNT);
        for (int i = 0; i < randomNumber; i++) {
            genreList.add(new Genre(i, GENRE + i));
        }
        return genreList;
    }


    @Test
    public void TestB_insert() {
        System.out.println(getClass().getName() + "insert()");
        dao.createTable();
        List<Genre> genreList = getMockGenreList();
        genreList.forEach(genre -> dao.insert(genre));
        assertEquals(genreList.size(), dao.cout());
    }

    @Test
    public void TestA_createTable() {
        System.out.println(getClass().getName() + "TestA_createTable()");
        dao.createTable();
    }


}