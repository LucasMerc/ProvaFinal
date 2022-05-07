package br.com.tech4me.prova1crud.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import br.com.tech4me.prova1crud.service.VendaService;
import br.com.tech4me.prova1crud.shared.VendaDto;
import br.com.tech4me.prova1crud.view.model.VendaResponse;
import br.com.tech4me.prova1crud.view.model.VendaResquest;


@RequestMapping("/api/vendas")
@RestController
public class VendaController {

    @Autowired
    private VendaService servico;
    ModelMapper mapper = new ModelMapper();


    @GetMapping
    public ResponseEntity <List<VendaResponse>> obterVendas() {
        
        List <VendaDto> vDto = servico.obterVendas();
        List <VendaResponse> vResponse = vDto.stream()
        .map(p -> mapper.map(vDto, VendaResponse.class))
        .collect(Collectors.toList());
        return new ResponseEntity<>(vResponse, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<VendaResponse> criarVenda (@RequestBody VendaResquest vrequest){
        VendaDto vDto1 = mapper.map(vrequest, VendaDto.class);
        vDto1 = servico.criarVenda(vDto1);
        VendaResponse vendResponse = mapper.map(vDto1, VendaResponse.class);
        return new ResponseEntity<>(vendResponse, HttpStatus.CREATED);

    }
    
    @GetMapping(value = "/{id}/vendaPorId")
    public ResponseEntity <VendaResponse> obterVendaPorId(@PathVariable String id){

        Optional<VendaDto> vDto = servico.ObterVendaPorId(id);

        if(vDto.isPresent()) {
            VendaResponse response = mapper.map(vDto.get(), VendaResponse.class);
            return new ResponseEntity<>(response, HttpStatus.FOUND);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping (value = "/contagem/vendas")
    public ResponseEntity <Long> totalVendas(){
        return new ResponseEntity<Long>(servico.totalVendas(), HttpStatus.OK);

    }
    

 }
