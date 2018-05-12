package uts.praktikum.pemrograman2.db;

import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.db.DerbyEmbeddedDatabaseType;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

public class Connection {
  private static ConnectionSource connectionSource;
  private static String DB_URL = "jdbc:derby:db_students;create=true";

  public static ConnectionSource getInstance() {
    if (connectionSource == null) {
      DatabaseType databaseType = new DerbyEmbeddedDatabaseType();
      try {
        connectionSource = new JdbcConnectionSource(DB_URL, databaseType);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return connectionSource;
  }
}
