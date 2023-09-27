package com.gestao.absenteismo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestao.absenteismo.models.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco,Long>{
}
