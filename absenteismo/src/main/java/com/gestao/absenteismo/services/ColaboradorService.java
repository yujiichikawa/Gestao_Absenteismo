package com.gestao.absenteismo.services;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestao.absenteismo.dtos.FuncionarioDTO;
import com.gestao.absenteismo.models.Colaborador;
import com.gestao.absenteismo.models.Gestor;
import com.gestao.absenteismo.repositories.ColaboradorRepository;
import com.gestao.absenteismo.repositories.ContatoRepository;
import com.gestao.absenteismo.repositories.EnderecoRepository;

@Service
public class ColaboradorService {
  @Autowired
  private EnderecoRepository enderecoRepository;
  @Autowired
  private ContatoRepository contatoRepository;
  @Autowired
  private ColaboradorRepository colaboradorRepository;

  public Colaborador cadastro(FuncionarioDTO funcionarioDTO, Gestor gestor){
    var colaborador = new Colaborador();

    BeanUtils.copyProperties(funcionarioDTO, colaborador);

    gestor.setColaboradores(colaborador);
    colaborador.setGestor(gestor);
    contatoRepository.save(colaborador.getContato());
    enderecoRepository.save(colaborador.getEndereco());

    return colaboradorRepository.save(colaborador);
  }

  public Optional<Colaborador> findByColaborador(String cpf){
    return colaboradorRepository.findByCpf(cpf);
  }

  public void delete(Colaborador colaborador) {
    colaboradorRepository.delete(colaborador);
  }

  public Colaborador update(Colaborador colaborador,FuncionarioDTO funcionarioDTO){
    BeanUtils.copyProperties(funcionarioDTO, colaborador);
    return colaboradorRepository.save(colaborador);
  }
}
