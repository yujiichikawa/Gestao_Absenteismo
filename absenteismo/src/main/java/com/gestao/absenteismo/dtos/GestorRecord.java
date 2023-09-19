package com.gestao.absenteismo.dtos;

import com.gestao.absenteismo.enums.Atuacao;
import com.gestao.absenteismo.enums.Cargo;
import com.gestao.absenteismo.enums.TipoMoradia;

public record GestorRecord(String nome,String cpf,Cargo cargo,Atuacao atuacao,String telefone,String email,String cidade,String bairro,String rua,TipoMoradia moradia, Integer numero) {
}
