package simpe.spring.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import simpe.spring.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserRepository implements SimpleSpringRepository<User, Long> {
    @Autowired
    private Connection connection;

    @Override
    public List<User> findAll() {
        String query = "SELECT *\n" + "FROM users;";
        List<User> userList = new ArrayList<>();

        try (Statement statement = this.connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                long id = resultSet.getLong(1);
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(3);

                userList.add(new User(id, firstName, lastName));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return userList;
    }

    @Override
    public Optional<User> findById(Long userId) {
        String query = "SELECT *\n" + "FROM users\n" + "WHERE id = ?;";
        User user = null;

        try (PreparedStatement preparedStatement = this.connection.prepareStatement(query)) {
            preparedStatement.setLong(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getLong(1);
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(3);

                user = new User(id, firstName, lastName);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return Optional.ofNullable(user);
    }

    @Override
    public void save(User user) {
        String query = "INSERT INTO public.users(\n" +
                "\tfirstname, lastname)\n" +
                "\tVALUES (?, ?);";

        Boolean result = null;

        try (PreparedStatement preparedStatement = this.connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());

            preparedStatement.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
