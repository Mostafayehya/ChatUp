package eg.gov.iti.jets.data.dao;

import eg.gov.iti.jets.domain.User;

public interface UserDao {
    int insertUser(User user);
    User getUserByPhone(String phone);

}
