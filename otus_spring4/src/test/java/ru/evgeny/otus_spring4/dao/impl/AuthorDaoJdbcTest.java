package ru.evgeny.otus_spring4.dao.impl;

import ru.evgeny.otus_spring4.dao.interfaces.AuthorDao;
import data.sorce.EmbeddedDataSource;
import ru.evgeny.otus_spring4.domain.Author;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AuthorDaoJdbcTest {

    private DriverManagerDataSource dataSource;
    private JdbcOperations operations;
    AuthorDao dao;
    private static final int COUNT = 200;
    private static final String TEST = "Test";
    private static final String A = "A";
    private static final String B = "B";
    private static final String C = "C";

    @Before
    public void initDataSorce() {
        dataSource = EmbeddedDataSource.getDataSource();
        operations = new JdbcTemplate(dataSource);
        dao = new AuthorDaoJdbc(operations);
    }

    @After
    public void dropTable() {
        dao.dropTable();
    }


    @Test
    public void TestX_getByName() {
        System.out.println(getClass().getName() +  " TestX_getByName()");
        dao.createTable();
        Random random = new Random();
        int randomNumber = random.nextInt(COUNT);
        for (int i = 0; i < randomNumber; i++) {
            if(i % 2 == 0)
                dao.insert(new Author(randomNumber, TEST + A, TEST + randomNumber));
            else
                dao.insert(new Author(randomNumber, TEST + B, TEST + randomNumber));
        }
        assertTrue(dao.getByName("TestA").stream().allMatch(a -> a.getFirstname().equals(TEST + A)));
        assertTrue(dao.getByLastName("TestB").stream().allMatch(a -> a.getFirstname().equals(TEST + B)));
        assertEquals(0, dao.getByName(TEST + C).size());
    }

    @Test
    public void TestX_getByLastName() {
        System.out.println(getClass().getName() + " TestX_getByLastName()");
        dao.createTable();
        Random random = new Random();
        int randomNumber = random.nextInt(COUNT);
        for (int i = 0; i < randomNumber; i++) {
            if(i % 2 == 0)
                dao.insert(new Author(randomNumber, TEST + randomNumber, TEST + A));
            else
                dao.insert(new Author(randomNumber, TEST + randomNumber, TEST + B));
        }
        assertTrue(dao.getByLastName(TEST + A).stream().allMatch(a -> a.getLastname().equals(TEST + A)));
        assertTrue(dao.getByLastName(TEST + B).stream().allMatch(a -> a.getLastname().equals(TEST + B)));
        assertEquals(0, dao.getByLastName(TEST + C).size());
    }

    @Test
    public void TestX_getById() {
        System.out.println(getClass().getName() + " TestX_getById");
        dao.createTable();
        dao.insert(new Author(1l, TEST, TEST));
        Author author = dao.getById(1l);
        assertEquals(author, new Author(1l, TEST, TEST));
    }


    @Test
    public void TestB_insert() {
        System.out.println(getClass().getName() + " TestB_insert()");
        dao.createTable();
        Random random = new Random();
        int randomNumber = random.nextInt(COUNT);
        for (int i = 0; i < randomNumber; i++)
            dao.insert(new Author(randomNumber, TEST + randomNumber, TEST + randomNumber));
        assertEquals(dao.count(), randomNumber);
    }

    @Test
    public void TestA_createTable() {
        System.out.println(getClass().getName() + " TestA_createTable");
        dao.createTable();
    }
}