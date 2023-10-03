package com.gestao.absenteismo.dtos;

import com.gestao.absenteismo.enums.Atuacao;
import com.gestao.absenteismo.models.Contato;
import com.gestao.absenteismo.models.Contrato;
import com.gestao.absenteismo.models.Endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;



public class FuncionarioDTO {
  @NotBlank
  private String nome;
  @Size(min = 11,max = 11)
  private String cpf;
  @NotNull
  private Atuacao atuacao;
  @NotNull
  private Contrato contrato;
  @NotNull
  private Contato contato;
  @NotNull
  private Endereco endereco;

  public FuncionarioDTO() {
  }
  public FuncionarioDTO(String nome, String cpf, Atuacao atuacao, Contrato contrato, Contato contato,
      Endereco endereco) {
    this.nome = nome;
    this.cpf = cpf;
    this.atuacao = atuacao;
    this.contrato = contrato;
    this.contato = contato;
    this.endereco = endereco;
  }
  public String getNome() {
    return nome;
  }
  public void setNome(String nome) {
    this.nome = nome;
  }
  public String getCpf() {
    return cpf;
  }
  public void setCpf(String cpf) {
    this.cpf = cpf;
  }
  public Atuacao getAtuacao() {
    return atuacao;
  }
  public void setAtuacao(Atuacao atuacao) {
    this.atuacao = atuacao;
  }
  public Contrato getContrato() {
    return contrato;
  }
  public void setContrato(Contrato contrato) {
    this.contrato = contrato;
  }
  public Contato getContato() {
    return contato;
  }
  public void setContato(Contato contato) {
    this.contato = contato;
  }
  public Endereco getEndereco() {
    return endereco;
  }
  public void setEndereco(Endereco endereco) {
    this.endereco = endereco;
  }

}
