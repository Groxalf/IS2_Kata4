package kata4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Main {


    public static void main(String[] args) throws SQLException{
        Connection connection = createConnection("people.db");
        PersonLoader loader = new DatabasePersonLoader(connection);
        HistogramBuilder<Person> builder = new HistogramBuilder<>(loader.load());
        new ConsoleHistogramViewer<String>().show(builder.build((Person entity) -> entity.getMail().getDomain()));
    }


    private static Connection createConnection(String dbPath) throws SQLException {
        DriverManager.registerDriver(new org.sqlite.JDBC());
        return DriverManager.getConnection("jdbc:sqlite:" + dbPath);
    }

}
