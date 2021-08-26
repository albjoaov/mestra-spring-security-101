package com.example.security.repository;

import com.example.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);

	// 	SELECT não necessário se não quisermos restringir as colunas: @Query("from User u join fetch u.perfis where u")
	@Query("select u from User u left join fetch u.perfis where u.id = :id")
	Optional<User> findByIdWithJoinFetch(Long id);


}
