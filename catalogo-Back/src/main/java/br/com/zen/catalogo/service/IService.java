package br.com.zen.catalogo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IService<E> {

    public abstract List<E> findAll();
	
	public abstract Page<E> findAll(Pageable pageable);
	
	public abstract E findById(Long id);
	
	public abstract E create(E object);
	
	public abstract E update(Long id, E object);
	
	public abstract boolean delete(Long id);
}