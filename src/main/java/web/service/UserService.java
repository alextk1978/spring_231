package web.service;

import web.model.User;

import java.util.List;

public interface UserService {

    List getAllUsers();

    User getUserById(Long id);

    boolean saveUser(User user);

    boolean deleteUserById(Long id);

    void updateUser(User user, long id);

}
