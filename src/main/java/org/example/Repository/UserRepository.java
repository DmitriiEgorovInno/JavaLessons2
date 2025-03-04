package org.example.Repository;

import org.example.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findUserByName(String name);


    @Modifying
    @Transactional
    @Query(value = "insert into users (name) values (:name)", nativeQuery = true)
    void createUser(@Param("name") String name);

    @Modifying
    @Transactional
    @Query(value = "delete from users", nativeQuery = true)
    void deleteAllUsers();

    @Modifying
    @Transactional
    @Query(value = "delete from users where name = :name", nativeQuery = true)
    void deleteUserByName(@Param("name") String name);
}
