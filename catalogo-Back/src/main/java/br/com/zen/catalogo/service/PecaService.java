package br.com.zen.catalogo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.zen.catalogo.model.Peca;
import br.com.zen.catalogo.repository.PecaRepository;

@Service
public class PecaService implements IService<Peca>{

    @Autowired
    private PecaRepository repository;

	@Override
	public List<Peca> findAll() {
		return repository.findAll();
	}

	@Override
	public Page<Peca> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public Peca findById(Long id) {
        Optional<Peca> model = repository.findById(id);
		if(model.isPresent()) return model.get();
		return null;
	}

	@Override
	public Peca create(Peca object) {
		return repository.save(object);
	}

	@Override
	public Peca update(Long id, Peca object) {
        if(repository.existsById(id)) {
			return repository.saveAndFlush(object);			
		}
		return null;
	}

	@Override
	public boolean delete(Long id) {
		Optional<Peca> model = repository.findById(id);
		if(model.isPresent()) {
			repository.delete(model.get());	
			return true;			
		}
		return false;
	}

}