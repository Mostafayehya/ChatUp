package eg.gov.iti.jets.data.dao;

import domains.User;
import eg.gov.iti.jets.data.DataBaseConnection;

import eg.gov.iti.jets.utilities.JavaSqlTimeConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao{
    Connection connection;
    public UserDaoImpl(){
        DataBaseConnection dataBaseConnection = DataBaseConnection.getInstance();
        connection = dataBaseConnection.getConnection();
    }
    @Override
    public int insertUser(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into user values (?,?,?,?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1,user.getPhoneNumber());
            preparedStatement.setString(2,user.getName());
            preparedStatement.setString(3,user.getEmail());
            preparedStatement.setString(4,user.getPassword());
            preparedStatement.setString(5,user.getPicture());
            preparedStatement.setString(6,user.getGender().name());
            preparedStatement.setString(7,user.getCountry());
            preparedStatement.setDate(8, JavaSqlTimeConverter.convertJavadateToSqlDate(user.getDateOfBirth()));
            preparedStatement.setString(9,user.getBio());
            preparedStatement.setString(10,user.getStatus().name());
            preparedStatement.setString(11,user.getMode().name());
            int result =  preparedStatement.executeUpdate();
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
    public int updateUserData(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update user set name=?, country=?,email=?,bio=?   where phoneNumber=?");
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getCountry());
            preparedStatement.setString(3,user.getEmail());
            preparedStatement.setString(4,user.getBio());
            preparedStatement.setString(5,user.getPhoneNumber());
            int result =  preparedStatement.executeUpdate();
            preparedStatement.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }



    @Override
    public int updateUserMode(User user) {
        try {
        PreparedStatement preparedStatement = connection.prepareStatement("update user set mode=?   where phone=?");
        preparedStatement.setString(1,user.getMode().name());
        preparedStatement.setString(2,user.getPhoneNumber());
            int result =  preparedStatement.executeUpdate();
            preparedStatement.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }




}
