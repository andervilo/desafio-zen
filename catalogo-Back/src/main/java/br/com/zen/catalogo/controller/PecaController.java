package br.com.zen.catalogo.controller;

import java.util.ArrayList;
import java.util.List;

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

import br.com.zen.catalogo.model.Peca;
import br.com.zen.catalogo.response.ErrorResponse;
import br.com.zen.catalogo.response.SuccessResponse;
import br.com.zen.catalogo.service.PecaService;

@RestController
@RequestMapping("/api/v1/pecas")
@CrossOrigin(origins = "*")
public class PecaController {

    @Autowired
    private PecaService service;
    
    private ErrorResponse error = null;

    private SuccessResponse success = null;
    
	@GetMapping("")
	public ResponseEntity<?> listAll() {
		List<Peca> list = service.findAll();
		return ResponseEntity.ok().body(list);
    }
    
	@GetMapping("/{id}")
	public ResponseEntity<?> showById(@PathVariable Long id) {
        Peca entity = service.findById(id);

		if(entity==null) {
            this.error = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Recurso nao encontrado!");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(this.error);
		}
		return (ResponseEntity<?>) ResponseEntity.ok().body(entity);
    }
    
	@PostMapping("")
	public ResponseEntity<?> create(@Valid @RequestBody Peca object) {
		List<String> messages = new ArrayList<String>();
        
		if(object == null) {
            this.error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Requisição feita com objeto Peça nulo!");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(this.error);
        }
        
        if(object != null) {
            if(StringUtils.isEmpty(object.getNome())) messages.add("Nome da peça é obrigatório!");

            if(object.getPesoLiquido() == null) messages.add("Peso líquido da peça é obrigatório!");

            if(object.getPesoBruto() == null) messages.add("Peso bruto da peça é obrigatório!");

            if(!StringUtils.isEmpty(object.getNome()) && object.getNome().length() > 200) messages.add("Nome da peça deve conter no máximo 200 caracteres!");
            
            if(!StringUtils.isEmpty(object.getVeiculoAplicacao()) && object.getVeiculoAplicacao().length() > 200) messages.add("Veículo de aplicação deve conter no máximo 200 caracteres!");

            if((object.getPesoLiquido() != null && object.getPesoBruto() != null) && (object.getPesoLiquido().compareTo(object.getPesoBruto()) == 1)) messages.add("Peso líquido não pode ser maior que peso bruto!");
        
            if(!CollectionUtils.isEmpty(messages)){
                this.error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), StringUtils.collectionToDelimitedString(messages, ","));
			    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(this.error);
            }
        }
		
        try {
            service.create(object);
        } catch (Exception e) {
            this.error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Não foi possível executar esta operação! "+e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(this.error);
        }
		
		this.success = new SuccessResponse(HttpStatus.OK.value(), "Operação realizada com sucesso!");
		return ResponseEntity.ok().body(this.success);
    }
    
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Peca object) {
        List<String> messages = new ArrayList<String>();
        System.out.println(id);
        if(id == null) {
            
            this.error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Requisição feita com ID nulo!");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(this.error);
        }
		
		if(object == null) {
            this.error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Requisição feita com objeto Peça nulo!");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(this.error);
        }
        
        if(object != null) {
            if(StringUtils.isEmpty(object.getNome())) messages.add("Nome da peça é obrigatório!");

            if(object.getPesoLiquido() == null) messages.add("Peso líquido da peça é obrigatório!");

            if(object.getPesoBruto() == null) messages.add("Peso bruto da peça é obrigatório!");

            if(!StringUtils.isEmpty(object.getNome()) && object.getNome().length() > 200) messages.add("Nome da peça deve conter no máximo 200 caracteres!");
            
            if(!StringUtils.isEmpty(object.getVeiculoAplicacao()) && object.getVeiculoAplicacao().length() > 200) messages.add("Veículo de aplicação deve conter no máximo 200 caracteres!");

            if((object.getPesoLiquido() != null && object.getPesoBruto() != null) && (object.getPesoLiquido().compareTo(object.getPesoBruto()) == 1)) messages.add("Peso líquido não pode ser maior que peso bruto!");
        
            if(!CollectionUtils.isEmpty(messages)){
                this.error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), StringUtils.collectionToDelimitedString(messages, ","));
			    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(this.error);
            }
        }
        
        Peca entity = new Peca();

        try {
            entity = service.update(id, object);
        } catch (Exception e) {
			this.error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Não foi possível executar esta operação! "+e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(this.error);
        }

        if(entity == null) {
            this.error = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Objeto não encontrado!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(this.error);
        }
        
        this.success = new SuccessResponse(HttpStatus.OK.value(), "Operação realizada com sucesso!");
		return ResponseEntity.ok().body(this.success);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
        Boolean deleted = service.delete(id);

        if(!deleted) {
            this.error = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Objeto não encontrado!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(this.error);
        }
        
        this.success = new SuccessResponse(HttpStatus.OK.value(), "Operação realizada com sucesso!");
		return ResponseEntity.ok().body(this.success);
	}
}