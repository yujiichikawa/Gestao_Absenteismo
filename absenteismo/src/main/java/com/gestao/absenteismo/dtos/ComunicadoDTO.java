package com.gestao.absenteismo.dtos;

import com.gestao.absenteismo.enums.TipoComunicado;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class ComunicadoDTO {
  @NotNull
  private TipoComunicado tipo;
  @NotEmpty
  private String mensagem;

  public ComunicadoDTO(){}

  public ComunicadoDTO(TipoComunicado tipo, String mensagem) {
    this.tipo = tipo;
    this.mensagem = mensagem;
  }

  public TipoComunicado getTipo() {
    return tipo;
  }

  public void setTipo(TipoComunicado tipo) {
    this.tipo = tipo;
  }

  public String getMensagem() {
    return mensagem;
  }

  public void setMensagem(String mensagem) {
    this.mensagem = mensagem;
  }
  
}
