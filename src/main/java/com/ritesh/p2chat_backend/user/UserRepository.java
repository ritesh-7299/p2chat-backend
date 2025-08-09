package com.ritesh.p2chat_backend.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    void deleteByUserId(String userId);

    User findByUserId(String userId);
}
