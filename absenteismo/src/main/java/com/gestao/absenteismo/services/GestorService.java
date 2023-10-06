package com.gestao.absenteismo.services;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.gestao.absenteismo.dtos.ComunicadoDTO;
import com.gestao.absenteismo.dtos.FuncionarioDTO;
import com.gestao.absenteismo.models.Colaborador;
import com.gestao.absenteismo.models.Comunicado;
import com.gestao.absenteismo.models.Gestor;
import com.gestao.absenteismo.repositories.ComunicadoRepository;
import com.gestao.absenteismo.repositories.ContatoRepository;
import com.gestao.absenteismo.repositories.EnderecoRepository;
import com.gestao.absenteismo.repositories.GestorRepository;

@Controller
public class GestorService {
  @Autowired
  private GestorRepository gestorRepository;
  @Autowired
  private EnderecoRepository enderecoRepository;
  @Autowired
  private ContatoRepository contatoRepository;
  @Autowired
  private ComunicadoRepository comunicadoRepository;

  public Gestor cadastro(FuncionarioDTO funcionarioDTO){
    var gestor = new Gestor();
    BeanUtils.copyProperties(funcionarioDTO, gestor);
    enderecoRepository.save(gestor.getEndereco());
    contatoRepository.save(gestor.getContato());
    return gestorRepository.save(gestor);
  }

  public Optional<Gestor> findByGestor(String cpf){
    return gestorRepository.findByCpf(cpf);
  }

  public Gestor update(FuncionarioDTO funcionarioDTO, Gestor gestor){
    BeanUtils.copyProperties(funcionarioDTO, gestor);
    return gestorRepository.save(gestor);
  }

  public void delete(Gestor gestor){
    gestorRepository.delete(gestor);
  }

  public Comunicado enviar_comunicado(Gestor geOptional,Colaborador cOptional,ComunicadoDTO comunicadoDTO){
    var gestor = geOptional;
    var comunicado = new Comunicado();
    var colaborador = cOptional;
        
    BeanUtils.copyProperties(comunicadoDTO, comunicado);

    gestor.setComunicados(comunicado);
    colaborador.setComunicados(comunicado);
    comunicado.setGestor(gestor);
    comunicado.setColaborador(colaborador);
        
    return comunicadoRepository.save(comunicado);
  }
}
