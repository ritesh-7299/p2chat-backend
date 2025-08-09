package com.ritesh.p2chat_backend.user;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final EntityManager entityManager;

    public void createUser(String userId) {
        User user = new User();
        user.setUserId(userId);
        repository.save(user);
    }

    public void deleteUser(String userId) {
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<User> query = cb.createQuery(User.class);
//
//        Root<User> user = query.from(User.class);
//        query.select(user).where(cb.equal(user.get("userId"), userId));
//
//        List<User> resultList = entityManager.createQuery(query).getResultList();
//        System.out.println(resultList.get(0));
//        repository.delete(resultList.get(0));
        repository.deleteByUserId(userId);
    }

    public User getUserByUserId(String userId) {
        return repository.findByUserId(userId);
    }
}
