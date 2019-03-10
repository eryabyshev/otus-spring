package ru.evgeny.otus_spring4.dao.impl;

import ru.evgeny.otus_spring4.dao.interfaces.GenreDao;
import ru.evgeny.otus_spring4.dao.interfaces.PublisherDao;
import data.sorce.EmbeddedDataSource;
import ru.evgeny.otus_spring4.domain.Genre;
import ru.evgeny.otus_spring4.domain.Publisher;
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

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PublisherDaoJdbcTest {

    private DriverManagerDataSource dataSource;
    private JdbcOperations operations;
    PublisherDao dao;
    private static final int COUNT = 200;
    private static final String PUBLISHER_NAME = "Publisher";
    private static final String ADDRESS = "Address";
    private static final String PHONE = "999-999-999";
    private static final long ID = 1l;

    @Before
    public void initDataSorce() {
        dataSource = EmbeddedDataSource.getDataSource();
        operations = new JdbcTemplate(dataSource);
        dao = new PublisherDaoJdbc(operations);
    }

    @After
    public void dropTable() {
        dao.dropTable();
    }


    private static List<Publisher> getPublisherMockList() {
        List<Publisher> genreList = new ArrayList<>();
        Random random = new Random();
        int randomNumber = random.nextInt(COUNT);
        for (int i = 0; i < randomNumber; i++) {
            genreList.add(new Publisher(i, PUBLISHER_NAME + i, ADDRESS + i, PHONE +i));
        }
        return genreList;
    }

    @Test
    public void TestB_insert() {
        List<Publisher> publisherMockList = getPublisherMockList();
        dao.createTable();
        publisherMockList.forEach(publisher -> dao.insert(publisher));
        assertEquals(publisherMockList.size(), dao.count());
    }

    @Test
    public void TestX_getByName() {
        System.out.println(getClass().getName() + "getByName()");
        List<Publisher> publisherMockList = getPublisherMockList();
        dao.createTable();
        publisherMockList.forEach(publisher -> dao.insert(publisher));
        List<Publisher> byName = dao.getByName(PUBLISHER_NAME + ID);
        assertTrue(byName.stream().allMatch(publisher -> (PUBLISHER_NAME + ID).equals(publisher.getName())));
    }

    @Test
    public void TestX_getById() {
        System.out.println(getClass().getName() + "getById()");
        List<Publisher> publisherMockList = getPublisherMockList();
        dao.createTable();
        publisherMockList.forEach(publisher -> dao.insert(publisher));
        Publisher byName = dao.getById(ID);
        assertEquals(byName.getId(), ID);
    }

    @Test
    public void TestA_createTable() {
        System.out.println(getClass().getName() + "createTable()");
        dao.createTable();
    }
}