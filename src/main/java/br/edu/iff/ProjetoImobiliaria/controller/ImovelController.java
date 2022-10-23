package br.edu.iff.ProjetoImobiliaria.controller;

import br.edu.iff.ProjetoImobiliaria.model.Imovel;
import br.edu.iff.ProjetoImobiliaria.service.ImovelService;
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
@RequestMapping(path = "/imovel/api")
public class ImovelController {

    @Autowired
    private ImovelService is;

    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(this.is.findAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity getOne(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.is.findById(id));
    }

    @PostMapping
    public ResponseEntity save(@Valid @RequestBody Imovel i) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.is.save(i));
    }

    @PutMapping
    public ResponseEntity update(@Valid @RequestBody Imovel i) {
        i.setId(null);
        this.is.update(i);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        this.is.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
