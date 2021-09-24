package web.service;

import org.springframework.stereotype.Service;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @PersistenceContext
    private EntityManager entityManager;

    public UserServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List getAllUsers() {
        return entityManager.createQuery(
                "select u from User u", User.class
        ).getResultList();
    }

    @Override
    public User getUserById(Long id) {
        TypedQuery<User> typedQuery = entityManager.createQuery(
                "select u from User u where u.id = :id", User.class
        );
        typedQuery.setParameter("id", id);
        return typedQuery.getResultList().stream().findAny().orElse(null);
    }

    @Override
    public boolean saveUser(User user) {
        if (user.getId() == 0) {
            entityManager.persist(user);
        } else {
            entityManager.merge(user);
        }
        return true;
    }

    @Override
    public boolean deleteUserById(Long id) {
        entityManager.remove(getUserById(id));
        return true;
    }

    @Override
    public void updateUser(User user, long id) {
        User updatedUser = getUserById(id);
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setAge(user.getAge());
        saveUser(updatedUser);
    }

}
