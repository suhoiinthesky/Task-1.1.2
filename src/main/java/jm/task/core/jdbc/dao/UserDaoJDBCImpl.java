package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public static final UserDaoJDBCImpl INSTANCE = new UserDaoJDBCImpl();

    public static final String CREAT_TABLE_SQL = """
            CREATE TABLE IF NOT EXISTS users \s
            (id BIGINT NOT NULL AUTO_INCREMENT,\s
            name VARCHAR(255),\s
            lastName VARCHAR(255),\s
            age TINYINT,\s
            PRIMARY KEY (id))
            """;
    public static final String DELETE_TABLE_SQL = """
            DROP TABLE IF EXISTS users
            """;

    public static final String CLEAN_TABLE_SQL = """
            TRUNCATE TABLE users
            """;

    public static final String DROP_SQL = """
            DELETE FROM users
            WHERE id = ?
            """;
    public static final String SAVE_SQL = """
            INSERT INTO users(name, lastName, age) 
            VALUES(?,?,?)
            """;
    public static final String GET_ALL_SQL = """
            SELECT * FROM users
            """;


    private UserDaoJDBCImpl() {

    }


    public static UserDaoJDBCImpl getInstance() {
        return INSTANCE;
    }

    public void createUsersTable() {
        try (Connection connection = Util.getConnection();
             var prepareStatement = connection.prepareStatement(CREAT_TABLE_SQL)) {
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getConnection();
             var prepareStatement = connection.prepareStatement(DELETE_TABLE_SQL)) {
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getConnection();
             var prepareStatement = connection.prepareStatement(SAVE_SQL)) {
            prepareStatement.setString(1, name);
            prepareStatement.setString(2, lastName);
            prepareStatement.setByte(3, age);
            prepareStatement.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных ");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getConnection();
             var prepareStatement = connection.prepareStatement(DROP_SQL)) {
            prepareStatement.setLong(1, id);
            prepareStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<User> getAllUsers() {
        try (Connection connection = Util.getConnection();
             var prepareStatement = connection.prepareStatement(GET_ALL_SQL)) {
            var resultSet = prepareStatement.executeQuery();
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getConnection();
             var prepareStatement = connection.prepareStatement(CLEAN_TABLE_SQL)) {
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
