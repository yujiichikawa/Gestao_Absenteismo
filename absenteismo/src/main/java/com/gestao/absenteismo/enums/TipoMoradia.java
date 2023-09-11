package com.gestao.absenteismo.enums;

public enum TipoMoradia {
  APARTAMENTO("Apartamento"),
  CASA("Casa");

  private String tipo;

  TipoMoradia(String tipo){
    this.tipo = tipo;
  } 

  public String getTipo() {
    return tipo;
  }
}
