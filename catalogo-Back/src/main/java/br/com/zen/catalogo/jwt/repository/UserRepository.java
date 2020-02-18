package br.com.zen.catalogo.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zen.catalogo.jwt.model.User;

/**
 * Created by fan.jin on 2016-10-15.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername( String username );
}

