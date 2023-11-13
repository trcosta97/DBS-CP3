package com.dbs.cp3.controller;

import com.dbs.cp3.domain.Produto;
import com.dbs.cp3.domain.atualizarProdutoDTO;
import com.dbs.cp3.domain.salvarProdutoDTO;
import com.dbs.cp3.service.ProdutoService;
import com.dbs.cp3.validation.ProdutoValidation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService service;
    @Autowired
    private ProdutoValidation validation;



    @PostMapping
    public ResponseEntity<Produto> create(@RequestBody @Valid salvarProdutoDTO dados, UriComponentsBuilder uriBuilder) throws Exception {
        validation.validatePreco(dados.preco());

        var produto = new Produto(dados);
        Produto produtoSalvo = service.save(produto);

        var uri = uriBuilder.path("/produto/{id}").buildAndExpand(produtoSalvo.getId()).toUri();
        return ResponseEntity.created(uri).body(produtoSalvo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> get(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(service.get(id));
    }

    @GetMapping
    public ResponseEntity<List<Produto>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody @Valid atualizarProdutoDTO dados) throws Exception {
        validation.validatePreco(dados.preco());

        return ResponseEntity.ok(service.update(id, new Produto(dados)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}

