package com.gestao.absenteismo.dtos;

import com.gestao.absenteismo.enums.Atuacao;
import com.gestao.absenteismo.enums.Cargo;
import com.gestao.absenteismo.models.Contato;

public record GestorRecord(String nome,String cpf,Cargo cargo,Atuacao atuacao,Contato contato) {
}
