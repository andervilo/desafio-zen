package br.com.zen.catalogo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zen.catalogo.model.Peca;
import br.com.zen.catalogo.service.PecaService;

@RestController
@RequestMapping("/api/v1/pecas")
@CrossOrigin(origins = "*")
public class PecaController {

    @Autowired
	private PecaService service;
    
	@GetMapping("")
	public ResponseEntity<?> listAll() {
		List<Peca> list = service.findAll();
		return ResponseEntity.ok().body(list);
    }
    
	@GetMapping("/{id}")
	public ResponseEntity<?> showById(@PathVariable Long id) {
        Peca entity = service.findById(id);
        
        Map<Object, Object> map = new HashMap<Object, Object>();

		if(entity==null) {
            map.put("message", HttpStatus.NOT_FOUND.value());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
		}
		return (ResponseEntity<?>) ResponseEntity.ok().body(entity);
    }
    
	@PostMapping(value="", consumes = "application/json")
	public ResponseEntity<?> create(@Valid @RequestBody Peca object) {
		List<String> messages = new ArrayList<String>();
        Map<Object, Object> map = new HashMap<Object, Object>();
        
		if(object == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Requisição feita com objeto Peça nulo!");
        }
        
        if(object != null) {
            if(StringUtils.isEmpty(object.getNome())) messages.add("Nome da peça é obrigatório!");

            if(object.getPesoLiquido() == null) messages.add("Peso líquido da peça é obrigatório!");

            if(object.getPesoBruto() == null) messages.add("Peso bruto da peça é obrigatório!");

            if(!StringUtils.isEmpty(object.getNome()) && object.getNome().length() > 200) messages.add("Nome da peça deve conter no máximo 200 caracteres!");
            
            if(!StringUtils.isEmpty(object.getVeiculoAplicacao()) && object.getVeiculoAplicacao().length() > 200) messages.add("Veículo de aplicação deve conter no máximo 200 caracteres!");

            if((object.getPesoLiquido() != null && object.getPesoBruto() != null) && (object.getPesoLiquido().compareTo(object.getPesoBruto()) == 1)) messages.add("Peso líquido não pode ser maior que peso bruto!");
        
            if(!CollectionUtils.isEmpty(messages)){
                map.put("success", false);
                map.put("statusCode", HttpStatus.BAD_REQUEST.value());
                map.put("errorMessage", messages);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            }
        }
		
        Peca entity = new Peca();
        
        try {
            entity = service.create(object);
        } catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Não foi possível executar esta operação! "+e.getMessage());
        }
		
		map.put("success", true);
        map.put("data", entity);
        map.put("statusCode", HttpStatus.OK.value());
		map.put("message", "Operação realizada com sucesso!");
		
		return ResponseEntity.ok().body(map);
    }
    
	@PutMapping(value="/{id}", consumes = "application/json")
	public ResponseEntity<?> update(Long id, @RequestBody Peca object) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		
		if(object == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Requisição feita com objeto Peça nulo!");
		}
        
        Peca entity = new Peca();

        try {
            entity = service.update(id, object);
        } catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Não foi possível executar esta operação! "+e.getMessage());
        }

        if(entity == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Objeto não encontrado!");
		}
		
		map.put("success", true);
		map.put("data", entity);
		map.put("message", "Operação realizada com sucesso!");
		
		return ResponseEntity.ok().body(map);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        
        Boolean deleted = service.delete(id);

        if(!deleted) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Objeto não encontrado!");
        }
        
        map.put("success", true);
        map.put("message", "Operação realizada com sucesso!");
        map.put("statusCode", HttpStatus.OK.value());
        
		return ResponseEntity.ok().body(map);
	}
}