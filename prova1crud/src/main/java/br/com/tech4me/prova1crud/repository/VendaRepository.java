package br.com.tech4me.prova1crud.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import br.com.tech4me.prova1crud.model.Venda;

public interface VendaRepository extends MongoRepository<Venda , String> {

    
}