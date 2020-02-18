package br.com.zen.catalogo.jwt.service;

import java.util.List;

import br.com.zen.catalogo.jwt.model.User;
import br.com.zen.catalogo.jwt.model.UserRequest;

/**
 * Created by fan.jin on 2016-10-15.
 */
public interface UserService {
  void resetCredentials();

  User findById(Long id);

  User findByUsername(String username);

  List<User> findAll();

  User save(UserRequest user);
}
