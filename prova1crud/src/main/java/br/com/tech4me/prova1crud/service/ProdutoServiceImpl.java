package br.com.tech4me.prova1crud.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tech4me.prova1crud.model.Produto;
import br.com.tech4me.prova1crud.repository.ProdutoRepository;
import br.com.tech4me.prova1crud.shared.ProdutoDto;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoRepository repositorio;
    ModelMapper mapper = new ModelMapper();

    @Override
    public ProdutoDto criarProduto(ProdutoDto produtoDto) {

        Produto produto = mapper.map(produtoDto, Produto.class);

        produto =  repositorio.save(produto);

        ProdutoDto pDto = mapper.map(produto, ProdutoDto.class);

        return pDto;

    }

    @Override
    public List<ProdutoDto> obterProdutos() {
        List<Produto> produtos = repositorio.findAll();

        List<ProdutoDto> pDto = produtos.stream()
        .map(p -> mapper.map(p, ProdutoDto.class))
        .collect(Collectors.toList());

        return pDto;

    }

    @Override
    public Optional <ProdutoDto> ObterProdutoPorId(String id) {

        Optional <Produto> produtos = repositorio.findById(id);

        if(produtos.isPresent()){

            ProdutoDto pDto = mapper.map(produtos.get(), ProdutoDto.class);
            return Optional.of(pDto);
        }
            return Optional.empty();
    }

    @Override
    public ProdutoDto atualizarProduto(String id, ProdutoDto pdto){

        Produto produto = mapper.map(pdto, Produto.class);

        produto.setId(id);

        produto = repositorio.save(produto);

        ProdutoDto dto = mapper.map(produto, ProdutoDto.class);

        return dto;
            
    }

    @Override
    public String removerProdutoPorId(String id) {
            repositorio.deleteById(id);
            return String.format("PRODUTO DELETADO COM SUCESSO");
        
    }

    @Override
    public void removerTodosOsProdutos(String id) {
            repositorio.deleteAll();
        
    }

    @Override
    public Long totalProdutos() {
        return repositorio.count();
    }

   

        
        
    }
    

