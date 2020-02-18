package br.com.zen.catalogo.jwt.service;

import java.util.List;

import br.com.zen.catalogo.jwt.model.Authority;

public interface AuthorityService {
  List<Authority> findById(Long id);

  List<Authority> findByname(String name);

}
