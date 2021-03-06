package ru.alextk.repository;

import ru.alextk.model.User;

import java.util.List;

public interface UserRepository {

    List<User> getAllUsers();

    void createUser(User user);

    void updateUser(User user);

    User readUser(long id);

    User deleteUser(long id);
}
