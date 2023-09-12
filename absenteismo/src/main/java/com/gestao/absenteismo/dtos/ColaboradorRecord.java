package com.gestao.absenteismo.dtos;

import com.gestao.absenteismo.enums.Atuacao;
import com.gestao.absenteismo.models.Contato;
import com.gestao.absenteismo.models.Endereco;

public record ColaboradorRecord(String nome,String cpf,Atuacao atuacao,Contato contato,Endereco endereco) {
}
