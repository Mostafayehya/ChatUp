package eg.gov.iti.jets.data.dao;

import domains.Gender;
import domains.Mode;
import domains.Status;
import domains.User;
import eg.gov.iti.jets.data.DataBaseConnection;

import eg.gov.iti.jets.utilities.JavaSqlTimeConverter;

import java.sql.*;
import java.time.LocalDate;

public class UserDaoImpl implements UserDao {
    Connection connection;

    public UserDaoImpl() {
        DataBaseConnection dataBaseConnection = DataBaseConnection.getInstance();
        connection = dataBaseConnection.getConnection();
    }

    @Override
    public int insertUser(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into user values (?,?,?,?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1, user.getPhoneNumber());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getPicture());
            preparedStatement.setString(6, user.getGender().name());
            preparedStatement.setString(7, user.getCountry());
            preparedStatement.setDate(8, JavaSqlTimeConverter.convertJavadateToSqlDate(user.getDateOfBirth()));
            preparedStatement.setString(9, user.getBio());
            preparedStatement.setString(10, user.getStatus().name());
            preparedStatement.setString(11, user.getMode().name());
            int result = preparedStatement.executeUpdate();
            preparedStatement.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public User getUserByPhone(String phone) {

        return null;
    }

    @Override
    public User getUserByPhoneAndPassword(String phone, String password) {

        User user = new User();

        try {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery("select * from user where phoneNumber = " + phone);
            if (rs.getString(4).equals(password)) {
                user.setName(rs.getString(2));
                user.setEmail(rs.getString(3));
                user.setPicture(rs.getString(5));
                user.setGender((Gender) rs.getObject(6));
                user.setCountry(rs.getString(7));
                user.setDateOfBirth((LocalDate) rs.getObject(8));
                user.setBio(rs.getString(9));
                user.setStatus((Status) rs.getObject(10));
                user.setMode((Mode) rs.getObject(11));
            }
            statement.close();
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
