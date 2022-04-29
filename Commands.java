package com.company;
import java.sql.*;
public class Commands {
    private static final String USERNAME = "root";
            private static final String PASSWORD = "password";
            private static final String CONN_STRING = "jdbc:mysql://localhost:3306/mziuri";
            public static Connection getConnection() {
                Connection connection = null;
                try {
                    connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return connection;
            }
            public int create_table() {
                boolean res = false;
                try {
                    Statement drop_statement = getConnection().createStatement();
                    drop_statement.execute("drop table if exists mziuri.contacts");
                    PreparedStatement create_statement = getConnection().prepareStatement("create table mziuri.contacts (owner_id INT(11), contact_id INT(11), name VARCHAR(40), lastname VARCHAR(40), idNum VARCHAR(40), mobile VARCHAR(40))");
            res = create_statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res ? 1 : -1;
    }
    public int add_contact(int owner_id, int contact_id, String name, String lastname, String idNum, String mobile) {
        int res = -1;
        try {
            PreparedStatement insert_statement = getConnection().prepareStatement("insert into mziuri.contacts values(?, ?, ?, ?, ?, ?)");
            insert_statement.setInt(1, owner_id);
            insert_statement.setInt(2, contact_id);
            insert_statement.setString(3, name);
            insert_statement.setString(4, lastname);
            insert_statement.setString(5, idNum);
            insert_statement.setString(6, mobile);
            res = insert_statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
    public void see_contacts_by_name(String name) {
        try {
            Statement see_statement = getConnection().createStatement();
            ResultSet resultSet = see_statement.executeQuery("select * from mziuri.contacts");
            while (resultSet.next()) {
                if (resultSet.getString(3).equals(name)) {
                    System.out.println(resultSet.getInt(1) + " " + resultSet.getInt(2) + " " + resultSet.getString(3) + " " + resultSet.getString(4) + " " + resultSet.getString(5) + " " + resultSet.getString(6));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void see_contacts_by_mobile(String mobile) {
        try {
            Statement see_statement = getConnection().createStatement();
            ResultSet resultSet = see_statement.executeQuery("select * from mziuri.contacts");
            while (resultSet.next()) {
                if (resultSet.getString(6).equals(mobile)) {
                    System.out.println(resultSet.getInt(1) + " " + resultSet.getInt(2) + " " + resultSet.getString(3) + " " + resultSet.getString(4) + " " + resultSet.getString(5) + " " + resultSet.getString(6));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void see_contacts_by_idNum(String idNum) {
        try {
            Statement see_statement = getConnection().createStatement();
            ResultSet resultSet = see_statement.executeQuery("select * from mziuri.contacts");
            while (resultSet.next()) {
                if (resultSet.getString(5).equals(idNum)) {
                    System.out.println(resultSet.getInt(1) + " " + resultSet.getInt(2) + " " + resultSet.getString(3) + " " + resultSet.getString(4) + " " + resultSet.getString(5) + " " + resultSet.getString(6));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public int delete_contact_by_ownerId(int id) {
        int res = -1;
        try {
            PreparedStatement statement = getConnection().prepareStatement("delete from mziuri.contacts where owner_id=?");
            statement.setInt(1, id);
            res = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
    public int edit_contact_by_owner_id(int id, int newId) {
        int res = -1;
        try {
            PreparedStatement statement = getConnection().prepareStatement("update mziuri.contacts set owner_id=? where owner_id=?");
            statement.setInt(1, newId);
            statement.setInt(2, id);
            res = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
}