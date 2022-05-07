package br.com.tech4me.prova1crud.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tech4me.prova1crud.model.Venda;
import br.com.tech4me.prova1crud.repository.VendaRepository;
import br.com.tech4me.prova1crud.shared.VendaDto;

@Service
public class VendaServiceImpl implements VendaService {

    @Autowired
    private VendaRepository repositorio;
    ModelMapper mapper = new ModelMapper();

    @Override
    public VendaDto criarVenda(VendaDto vDto) {

        Venda venda = mapper.map(vDto, Venda.class);

        venda =  repositorio.save(venda);

        VendaDto venda1 = mapper.map(venda, VendaDto.class);

        return venda1;

    }

    @Override
    public List<VendaDto> obterVendas() {
        List<Venda> vendas = repositorio.findAll();

        List<VendaDto> vDto = vendas.stream()
        .map(p -> mapper.map(p, VendaDto.class))
        .collect(Collectors.toList());

        return vDto;

    }

    @Override
    public Optional <VendaDto> ObterVendaPorId(String id) {

        Optional <Venda> vendas = repositorio.findById(id);

        if(vendas.isPresent()){

            VendaDto vDto = mapper.map(vendas.get(), VendaDto.class);
            return Optional.of(vDto);
        }
            return Optional.empty();
    }

    @Override
    public String removerVendaPorId(String id) {
            repositorio.deleteById(id);
            return String.format("VENDA DELETADO COM SUCESSO");
        
    }

    @Override
    public void removerTodasAsVendas(String id) {
            repositorio.deleteAll();
        
    }

    @Override
    public Long totalVendas() {
        return repositorio.count();
    }

   

        
        
    }