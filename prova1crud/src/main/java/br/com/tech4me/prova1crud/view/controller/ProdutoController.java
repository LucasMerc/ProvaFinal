package br.com.tech4me.prova1crud.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
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

import br.com.tech4me.prova1crud.service.ProdutoService;
import br.com.tech4me.prova1crud.shared.ProdutoDto;
import br.com.tech4me.prova1crud.view.model.ProdutoRequest;
import br.com.tech4me.prova1crud.view.model.ProdutoResponse;

@RequestMapping("/api/produto")
@RestController
public class ProdutoController {

    @Autowired
    private ProdutoService servico;
    ModelMapper mapper = new ModelMapper();


    @GetMapping
    public ResponseEntity <List<ProdutoResponse>> obterProdutos() {
        
        List <ProdutoDto> pDto = servico.obterProdutos();
        List <ProdutoResponse> produtosResponse = pDto.stream()
        .map(p -> mapper.map(pDto, ProdutoResponse.class))
        .collect(Collectors.toList());
        return new ResponseEntity<>(produtosResponse, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<ProdutoResponse> criarProduto (@RequestBody ProdutoRequest prequest){
        ProdutoDto pDto = mapper.map(prequest, ProdutoDto.class);
        pDto = servico.criarProduto(pDto);
        ProdutoResponse prodResponse = mapper.map(pDto, ProdutoResponse.class);
        return new ResponseEntity<>(prodResponse, HttpStatus.CREATED);

    }
    
    @GetMapping(value = "/{id}")
    public ResponseEntity <ProdutoResponse> obterProdutoPorId(@PathVariable String id){

        Optional<ProdutoDto> pDto = servico.ObterProdutoPorId(id);

        if(pDto.isPresent()) {
            ProdutoResponse response = mapper.map(pDto.get(), ProdutoResponse.class);
            return new ResponseEntity<>(response, HttpStatus.FOUND);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    

    @PutMapping
    public ResponseEntity <ProdutoResponse> atualizarProduto(@PathVariable String id,@RequestBody ProdutoRequest produtoRequest){

        ProdutoDto pDto = mapper.map(produtoRequest, ProdutoDto.class);
        pDto = servico.atualizarProduto(id, pDto);
        ProdutoResponse response = mapper.map(pDto, ProdutoResponse.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
     }

    @DeleteMapping(value = "/{id}")
        public ResponseEntity <String> removerProdutoPorId(@PathVariable String id){
            servico.removerProdutoPorId(id);
            return new ResponseEntity<String>("Removido com sucesso!", HttpStatus.OK);
        } 

    
    @GetMapping (value = "/contagem")
    public ResponseEntity <Long> totalProdutos(){
        return new ResponseEntity<Long>(servico.totalProdutos(), HttpStatus.OK);

    }
    

 }
