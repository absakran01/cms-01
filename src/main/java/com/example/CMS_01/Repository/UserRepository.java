package com.example.CMS_01.Repository;


import java.util.Optional;

import com.example.CMS_01.Entity.User;
import org.springframework.data.repository.CrudRepository;



public interface UserRepository extends CrudRepository<User, Long> {
	 Optional<User> findByUsername(String username);
	// boolean existsByUsername(String username);
}