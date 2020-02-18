package br.com.zen.catalogo.jwt.service;

import java.util.List;

import br.com.zen.catalogo.jwt.model.Authority;
import br.com.zen.catalogo.jwt.model.UserRoleName;

public interface AuthorityService {
  List<Authority> findById(Long id);

  List<Authority> findByname(UserRoleName name);

}
