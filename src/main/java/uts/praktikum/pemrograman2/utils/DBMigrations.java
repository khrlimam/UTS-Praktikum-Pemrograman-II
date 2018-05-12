package uts.praktikum.pemrograman2.utils;

import com.j256.ormlite.table.TableUtils;
import uts.praktikum.pemrograman2.db.Connection;
import uts.praktikum.pemrograman2.models.Student;

import java.sql.SQLException;
import java.util.Arrays;

public class DBMigrations {
  private static Class[] TABLES = {Student.class};

  public static void migrateUp() {
    Arrays.stream(TABLES).forEach(aClass -> {
      try {
        TableUtils.createTableIfNotExists(Connection.getInstance(), aClass);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    });
  }
}
