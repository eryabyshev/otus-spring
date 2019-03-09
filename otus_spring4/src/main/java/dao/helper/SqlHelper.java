package dao.helper;

import org.springframework.jdbc.core.JdbcOperations;


import static rowmapper.MapperConstant.*;

public class SqlHelper {

    private SqlHelper() {}

    public static final String WHERE = " where ";
    public static final String CONDITION = " = ?";
    public static final String SELECT = "select * from public.";
    private static final String COUNT_FROM = "select count(*) from public.";
    private static final String DROP = "DROP TABLE IF EXISTS ";

    public static long count(JdbcOperations jdbcOperations, String tableName) {
        return jdbcOperations.queryForObject( COUNT_FROM + tableName, Long.class);
    }

    public static void dropTable(JdbcOperations jdbcOperations, String tableName) {
        jdbcOperations.execute(DROP + tableName);
    }

    private static final String CREATE_TABLE = "CREATE TABLE public.";
    private static final String PRIMARY_KEY = " bigserial PRIMARY KEY NOT NULL,\n";
    private static final String VARCHAR255 = " varchar(255) NOT NULL,\n";
    private static final String BIGINT = " bigint NOT NULL,\n";
    private static final String CREATE_UNIQUE_INDEX = "CREATE UNIQUE INDEX ";

    public static void createAuthorTable(JdbcOperations jdbcOperations) {
        String sql = CREATE_TABLE + AUTHOR +
                "(\n" +
                "    " + ID + PRIMARY_KEY +
                "    " + FIRST_NAME + VARCHAR255 +
                "    " + LAST_NAME + VARCHAR255 +
                ");\n" +
                CREATE_UNIQUE_INDEX + AUTHOR + "_" + ID + "_uindex ON public." + AUTHOR +" ("+ ID +");";
        jdbcOperations.execute(sql);

    }

    public static void createBookTable(JdbcOperations jdbcOperations) {
        String sql = CREATE_TABLE + BOOK +
                "(\n" +
                "    " + ID + PRIMARY_KEY +
                "    " + NAME + VARCHAR255 +
                "    " + AUTHOR +BIGINT +
                "    " + GENRE + BIGINT +
                "    " + PUBLISHER + BIGINT +
                ");\n" +
                CREATE_UNIQUE_INDEX + BOOK + "_" + ID + "_uindex ON public." + BOOK +" (" + ID +");";
        jdbcOperations.execute(sql);
    }

    public static void createGenreTable(JdbcOperations jdbcOperations) {
        String sql = CREATE_TABLE + GENRE +
                "(\n" +
                "    " + ID + PRIMARY_KEY +
                "    " + NAME + VARCHAR255 +
                ");\n" +
                CREATE_UNIQUE_INDEX + GENRE + "_" + ID + "_uindex ON public." + GENRE + " (" + ID + ");";
        jdbcOperations.execute(sql);
    }


    public static void createPublisherTable(JdbcOperations jdbcOperations) {
        String sql = CREATE_TABLE + PUBLISHER +
                "(\n" +
                "    " + ID + PRIMARY_KEY +
                "    " + NAME + VARCHAR255 +
                "    " + ADDRESS + VARCHAR255 +
                "    " + PHONE + VARCHAR255 +
                ");\n" +
                CREATE_UNIQUE_INDEX + PUBLISHER + "_" + ID + "_uindex ON public." + PUBLISHER + " (" + ID + ");";
        jdbcOperations.execute(sql);
    }
}
