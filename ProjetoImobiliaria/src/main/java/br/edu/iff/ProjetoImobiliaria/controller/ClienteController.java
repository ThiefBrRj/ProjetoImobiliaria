package br.edu.iff.ProjetoImobiliaria.controller;

import br.edu.iff.ProjetoImobiliaria.model.Cliente;
import br.edu.iff.ProjetoImobiliaria.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/cliente/api")
public class ClienteController {

    @Autowired
    private ClienteService cs;

    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(this.cs.findAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity getOne(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.cs.findById(id));
    }

    @PostMapping
    public ResponseEntity save(@RequestBody Cliente cliente) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.cs.save(cliente));
    }

    @PutMapping
    public ResponseEntity update(@RequestBody Cliente cliente) {
        cliente.setId(null);
        this.cs.update(cliente);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        this.cs.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
}
