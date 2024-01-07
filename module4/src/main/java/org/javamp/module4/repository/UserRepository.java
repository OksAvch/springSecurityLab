package org.javamp.module4.repository;

import org.javamp.module4.data.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserData, String> {

    @Query("SELECT u.userName FROM UserData u WHERE u.enabled = false")
    List<String> findBlockedUsernames();
}
