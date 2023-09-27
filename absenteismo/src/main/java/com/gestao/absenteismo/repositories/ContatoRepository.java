package com.gestao.absenteismo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestao.absenteismo.models.Contato;

public interface ContatoRepository extends JpaRepository<Contato,Long>{
}
