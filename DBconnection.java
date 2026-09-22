package StudentManagemetPortal;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBconnection {
    private static final String url="jdbc:mysql://localhost:3306/ampcode";
    private static final String user="root";
    private static final String password="root";

    //connection
    private static final HikariDataSource Ds;
    static {
        try {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(url);
            config.setUsername(user);
            config.setPassword(password);
            config.setMaximumPoolSize(5);
            Ds = new HikariDataSource(config);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } /// try-catch end
    }/// static end
    public static HikariDataSource getDs()throws Exception {
        return Ds;
    }
}
