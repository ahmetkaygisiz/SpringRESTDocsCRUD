package com.restdocs.repository;

import com.restdocs.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findByUsername(String username);

    @Transactional
    public void deleteByUsername(String username);
}
