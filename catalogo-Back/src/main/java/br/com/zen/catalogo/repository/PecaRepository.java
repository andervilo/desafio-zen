package br.com.zen.catalogo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zen.catalogo.model.Peca;

public interface PecaRepository extends JpaRepository<Peca, Long>{
    
}