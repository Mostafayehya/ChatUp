package eg.gov.iti.jets.data.dao;


import domains.User;

import java.sql.ResultSet;

public interface UserDao {
    int insertUser(User user);
    User getUserByPhone(String phone);
    User getUserByPhoneAndPassword(String phone, String password);
    ResultSet getAllByCountry();
    ResultSet getAllByGender();
    ResultSet getAllOnOff();

}
