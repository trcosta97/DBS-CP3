package com.dbs.cp3.validation;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProdutoValidation {

    public void validatePreco(BigDecimal preco) throws Exception {
        if (preco.compareTo(BigDecimal.ZERO) <= 0){
            throw new Exception("Preco não pode ser igual ou menor que 0");
        }
    }
}
