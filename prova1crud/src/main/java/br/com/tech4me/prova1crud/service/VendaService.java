package br.com.tech4me.prova1crud.service;

import java.util.List;
import java.util.Optional;


import br.com.tech4me.prova1crud.shared.VendaDto;

public interface VendaService {

    VendaDto criarVenda(VendaDto venda);
    List<VendaDto> obterVendas();
    Optional <VendaDto> ObterVendaPorId(String id);
    String removerVendaPorId(String id);
    void removerTodasAsVendas(String id);
    Long totalVendas();
}

