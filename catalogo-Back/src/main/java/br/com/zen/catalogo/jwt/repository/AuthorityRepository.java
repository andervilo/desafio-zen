package br.com.zen.catalogo.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zen.catalogo.jwt.model.Authority;


public interface AuthorityRepository extends JpaRepository<Authority, Long> {
  Authority findByName(String name);
}
