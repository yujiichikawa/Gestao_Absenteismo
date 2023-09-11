package com.gestao.absenteismo.enums;

public enum TipoComunicado {
  LEMBRETE_PRAZOS("Lembrete de Prazos"),
  AUSENCIA("Ausência"),
  ALERTA_ABSENTEISMO("Absenteismo");

  private String comunicado;

  TipoComunicado(String comunicado){
    this.comunicado = comunicado;
  }

  public String getComunicado() {
    return comunicado;
  }

}
