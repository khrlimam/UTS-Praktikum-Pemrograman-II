package uts.praktikum.pemrograman2.models;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Data;
import uts.praktikum.pemrograman2.db.Connection;

import java.sql.SQLException;

@DatabaseTable(tableName = "tbl_students")
@Data
public class Student {
  @DatabaseField(generatedId = true)
  private int id;
  @DatabaseField
  private String nim, nama;

  public static Dao<Student, Integer> Dao() {
    try {
      return DaoManager.createDao(Connection.getInstance(), Student.class);
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public String toString() {
    return String.format("NIM = %s, Nama = %s", getNim(), getNama());
  }
}
