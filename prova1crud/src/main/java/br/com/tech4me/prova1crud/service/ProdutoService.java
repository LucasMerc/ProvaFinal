package br.com.tech4me.prova1crud.service;

import java.util.List;
import java.util.Optional;


import br.com.tech4me.prova1crud.shared.ProdutoDto;

public interface ProdutoService {

    ProdutoDto criarProduto(ProdutoDto musica);
    List<ProdutoDto> obterProdutos();
    Optional <ProdutoDto> ObterProdutoPorId(String id);
    ProdutoDto atualizarProduto(String id, ProdutoDto musica);
    String removerProdutoPorId(String id);
    void removerTodosOsProdutos(String id);
    Long totalProdutos();
}
