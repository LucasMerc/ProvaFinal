package br.com.tech4me.prova1crud.shared;

import java.sql.Date;

import br.com.tech4me.prova1crud.model.Produto;

public class VendaDto {

    
    private String idVenda;
    private Produto produtoVendido;
    private Date dataVenda;
    private int qtdVendido;
    private int valorVenda;

    
    public String getIdVenda() {
        return idVenda;
    }
    public void setIdVenda(String idVenda) {
        this.idVenda = idVenda;
    }
    public Produto getProdutoVendido() {
        return produtoVendido;
    }
    public void setProdutoVendido(Produto produtoVendido) {
        this.produtoVendido = produtoVendido;
    }
    public Date getDataVenda() {
        return dataVenda;
    }
    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }
    public int getQtdVendido() {
        return qtdVendido;
    }
    public void setQtdVendido(int qtdVendido) {
        this.qtdVendido = qtdVendido;
    }
    public int getValorVenda() {
        return valorVenda;
    }
    public void setValorVenda(int valorVenda) {
        this.valorVenda = valorVenda;
    }


    
    
}
