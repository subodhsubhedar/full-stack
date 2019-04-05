package com.myapp.library.menu.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myapp.library.entity.LibraryUser;

@Repository
public interface LibraryUserJpaRepository extends JpaRepository<LibraryUser, Long> {

	Optional<LibraryUser> findByUsername(String username);
}
