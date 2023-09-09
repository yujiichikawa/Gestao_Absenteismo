package com.gestao.absenteismo.enums;

public enum Atuacao {
  LIMPEZA("Limpeza"),
  JARDINAGEM("Jardinagem"),
  PINTURA("Pintura"),
  ALPINISMO("Alpinismo"),
  ARTIFICE("Artífice");
  
  private String atuacao;
  Atuacao(String atuacao){
    this.atuacao = atuacao;
  }

  public String getAtuacao() {
    return atuacao;
  }
}
