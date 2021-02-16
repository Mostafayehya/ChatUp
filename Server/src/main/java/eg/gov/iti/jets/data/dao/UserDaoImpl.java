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
    public UserDaoImpl(){
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
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from user where phoneNumber = ?");
            preparedStatement.setString(1,phone);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                User user  = new User(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),
                        resultSet.getString(4));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getUserByPhoneAndPassword(String phone, String password) {
        User user = null;
        try {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery("select * from user where phoneNumber = " + phone);
            if (rs.next() && rs.getString(4).equals(password)) {
                user = new User();
                user.setPhoneNumber(phone);
                user.setName(rs.getString(2));
                user.setEmail(rs.getString(3));
                user.setPicture(rs.getString(5));
                user.setGender(Gender.valueOf(rs.getObject(6).toString().toUpperCase()));
                user.setCountry(rs.getString(7));
                String s = rs.getObject(8).toString();
                LocalDate today = LocalDate.parse(s);
                user.setDateOfBirth(today);
                user.setBio(rs.getString(9));
                user.setStatus(Status.valueOf(rs.getObject(10).toString().toUpperCase()));
                user.setMode(Mode.valueOf(rs.getObject(11).toString().toUpperCase()));
            }
            statement.close();
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }

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

    @Override
    public int updateUserPass(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update user set password=?   where phone=?");
            preparedStatement.setString(1, user.getPassword());
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
