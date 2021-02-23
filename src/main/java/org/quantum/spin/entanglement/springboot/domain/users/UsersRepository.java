package org.quantum.spin.entanglement.springboot.domain.users;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users, Long> {

    @Query("SELECT P FROM Users P ORDER BY P.id DESC")
    List<Users> findAllDesc();

    @Query("SELECT P FROM Users P ORDER BY P.id DESC")
    Page<Users> findAll(Pageable pageable);

}