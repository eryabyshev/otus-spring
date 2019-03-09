package data.sorce;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class EmbeddedDataSource {
    private static final String DRIVER_CLASS_NAME = "org.h2.Driver";
    private static final String URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";

    private DriverManagerDataSource ds;
    private static EmbeddedDataSource instance;

    private EmbeddedDataSource() {
        ds = new DriverManagerDataSource();
        ds.setDriverClassName(DRIVER_CLASS_NAME);
        ds.setUrl(URL);
    }

    public static DriverManagerDataSource getDataSource() {
        if(instance == null)
            instance = new EmbeddedDataSource();
        return instance.ds;
    }
}
