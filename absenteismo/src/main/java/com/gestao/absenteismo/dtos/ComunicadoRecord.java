package com.gestao.absenteismo.dtos;

import com.gestao.absenteismo.enums.TipoComunicado;

public record ComunicadoRecord(TipoComunicado tipo,String mensagem) {
}
