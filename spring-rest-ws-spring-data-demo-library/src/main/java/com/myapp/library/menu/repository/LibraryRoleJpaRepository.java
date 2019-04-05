package com.myapp.library.menu.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myapp.library.entity.LibraryRole;

@Repository
public interface LibraryRoleJpaRepository extends JpaRepository<LibraryRole, Long> {

	Optional<LibraryRole> findByName(String name);

} 