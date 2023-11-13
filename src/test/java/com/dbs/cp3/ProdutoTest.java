package com.dbs.cp3;


import com.dbs.cp3.domain.Produto;
import com.dbs.cp3.exception.ProdutoNotFoundException;
import com.dbs.cp3.repository.ProdutoRepository;
import com.dbs.cp3.service.ProdutoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ProdutoTest {

    @InjectMocks
    private ProdutoService service;

    @Mock
    private ProdutoRepository repository;

    @Test
    public void testFindByIdThrowProdutoNotFoundException() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ProdutoNotFoundException.class,()->service.get(1L));
    }

    @Test
    public void testFindByIdReturnSuccess() throws Exception {
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("Colgate Superwhite");
        produto.setDescricao("Creme dental");
        produto.setPreco(new BigDecimal("12.9"));

        Long idProduto = 1L;
        when(repository.findById(idProduto)).thenReturn(Optional.of(produto));

        //segunda fase executar a classe testadfa
        Produto produtoFound = service.get(idProduto);

        //terceira fase validar o resultado
        assertNotNull(produtoFound);
        assertEquals("Colgate Superwhite",produtoFound.getNome());
    }
}

