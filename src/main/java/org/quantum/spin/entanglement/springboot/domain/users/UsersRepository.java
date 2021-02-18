package org.quantum.spin.entanglement.springboot.domain.users;

import org.quantum.spin.entanglement.springboot.domain.posts.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users, Long> {

    @Query("SELECT P FROM Users P ORDER BY P.id DESC")
    List<Users> findAllDesc();

}