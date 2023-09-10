package com.gestao.absenteismo.enums;

public enum Cargo {
  GERENTE_DE_EQUIPE("Gerente de Equipe"),
  COLABORADOR("Colaborador");

  private String cargo;
  Cargo(String cargo){
    this.cargo = cargo;
  }

  public String getCargo() {
    return cargo;
  }
  
}
