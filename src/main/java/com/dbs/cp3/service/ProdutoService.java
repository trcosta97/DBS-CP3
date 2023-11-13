package com.dbs.cp3.service;

import com.dbs.cp3.domain.Produto;
import com.dbs.cp3.exception.ProdutoNotFoundException;
import com.dbs.cp3.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public Produto save(Produto produto){
        return repository.save(produto);
    }

    public Produto get(Long id) throws Exception {
        Optional<Produto> optionalProduto = repository.findById(id);
        if(optionalProduto.isPresent()){
            return optionalProduto.get();
        }

        throw new ProdutoNotFoundException("Id inválido");
    }

    public List<Produto> getAll(){
        return repository.findAll();
    }

    public Produto update(Long id, Produto newData) throws Exception {
        Optional<Produto> optionalProduto = repository.findById(id);
        if(optionalProduto.isPresent()){
            Produto updatedProduto = optionalProduto.get();
            if(newData.getNome() != null){
                updatedProduto.setNome(newData.getNome());
            }
            if(newData.getDescricao() != null){
                updatedProduto.setDescricao(newData.getDescricao());
            }
            if(newData.getPreco() != null){
                updatedProduto.setPreco(newData.getPreco());
            }
            return repository.save(updatedProduto);
        }
        throw new ProdutoNotFoundException("Id inválido");
    }

    public void delete(Long id) throws Exception {
        Optional<Produto> optionalProduto = repository.findById(id);
        if(optionalProduto.isPresent()){
            Produto produto = optionalProduto.get();
            repository.delete(produto);
            return;
        }
        throw new ProdutoNotFoundException("Id inválido");
    }

}
