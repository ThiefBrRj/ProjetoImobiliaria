package br.edu.iff.ProjetoImobiliaria.controller;

import br.edu.iff.ProjetoImobiliaria.model.Aluga;
import br.edu.iff.ProjetoImobiliaria.service.AlugaService;
import javax.validation.Valid;
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
@RequestMapping(path = "/aluga/api")
public class AlugaController {

    @Autowired
    private AlugaService as;

    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(this.as.findAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity getOne(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.as.findById(id));
    }

    @PostMapping
    public ResponseEntity save(@Valid @RequestBody Aluga a) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.as.save(a));
    }

    @PutMapping
    public ResponseEntity update(@Valid @RequestBody Aluga a) {
        a.setId(null);
        this.as.update(a);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        this.as.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
