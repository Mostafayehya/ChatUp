package eg.gov.iti.jets.data.dao;


import domains.User;

public interface UserDao {
    int insertUser(User user);
    User getUserByPhone(String phone);
    int updateUserData(User user);
    int updateUserMode(User user);

}
