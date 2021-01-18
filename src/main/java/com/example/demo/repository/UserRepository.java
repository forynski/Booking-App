package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

//    User findByEmail(String username);

//    @Query(value = "SELECT u FROM user u where u.username like %:keyword% or u.email like %:keyword%",nativeQuery = false)
//    Page<User> findByKeyword(@Param("keyword") String keyword, Pageable pageable);
//
//    @Query(value = "SELECT u FROM user u where u.username = :usernameOrEmail or u.email = :usernameOrEmail",nativeQuery = false)
//    User findByUsernameAndEmail(@Param("usernameOrEmail") String usernameOrEmail);

}
