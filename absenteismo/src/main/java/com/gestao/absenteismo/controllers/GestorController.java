package com.gestao.absenteismo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestao.absenteismo.dtos.ComunicadoDTO;
import com.gestao.absenteismo.dtos.FuncionarioDTO;
import com.gestao.absenteismo.models.Colaborador;
import com.gestao.absenteismo.models.Comunicado;
import com.gestao.absenteismo.models.Gestor;
import com.gestao.absenteismo.repositories.ColaboradorRepository;
import com.gestao.absenteismo.repositories.ComunicadoRepository;
import com.gestao.absenteismo.repositories.ContatoRepository;
import com.gestao.absenteismo.repositories.EnderecoRepository;
import com.gestao.absenteismo.repositories.GestorRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/gestor")
public class GestorController {
  @Autowired
  private GestorRepository gestorRepository;
  @Autowired
  private ColaboradorRepository colaboradorRepository;
  @Autowired
  private EnderecoRepository enderecoRepository;
  @Autowired
  private ContatoRepository contatoRepository;
  @Autowired
  private ComunicadoRepository comunicadoRepository;

  @PostMapping("/cadastro")
  public ResponseEntity<Gestor> cadastro_gestor(@Valid @RequestBody FuncionarioDTO funcionarioDTO){
    var gestor = new Gestor();
    BeanUtils.copyProperties(funcionarioDTO, gestor);
    enderecoRepository.save(gestor.getEndereco());
    contatoRepository.save(gestor.getContato());
    return ResponseEntity.status(HttpStatus.CREATED).body(gestorRepository.save(gestor));
  }

  @PostMapping("/{cpf}/colaborador/cadastro")
  public ResponseEntity<Object> cadastro_colaborador(@PathVariable String cpf,@Valid @RequestBody FuncionarioDTO funcionarioDTO){
    var gestor = gestorRepository.findByCpf(cpf);
    if(gestor.isPresent()){
      var colaborador = new Colaborador();
      var gestorconvert = gestor.get();

      BeanUtils.copyProperties(funcionarioDTO, colaborador);

      gestorconvert.setColaboradores(colaborador);
      colaborador.setGestor(gestorconvert);
      contatoRepository.save(colaborador.getContato());
      enderecoRepository.save(colaborador.getEndereco());
      return ResponseEntity.status(HttpStatus.CREATED).body(colaboradorRepository.save(colaborador));
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Gestor não existente");

  }

  @GetMapping("/lista")
  public ResponseEntity<List<Gestor>> listar_todos(){
    return ResponseEntity.status(HttpStatus.OK).body(gestorRepository.findAll());
  }

  @PutMapping("/update/{cpf}")
  public ResponseEntity<Object> updateById(@PathVariable String cpf,@Valid @RequestBody FuncionarioDTO funcionarioDTO){
    Optional<Gestor> funcionario = gestorRepository.findByCpf(cpf);
    if(funcionario.isEmpty()) {return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nao encontrado");}
    var funcionarioModel = funcionario.get();
    BeanUtils.copyProperties(funcionarioDTO, funcionarioModel);
    return ResponseEntity.status(HttpStatus.OK).body(gestorRepository.save(funcionarioModel));
  }

  @DeleteMapping("/delete/{cpf}")
  public ResponseEntity<Object> deleteGestor(@PathVariable String cpf){
    Optional<Gestor> funcionario = gestorRepository.findByCpf(cpf);
    if(funcionario.isPresent()) {
      if(funcionario.get().getColaboradores().isEmpty()){
        gestorRepository.delete(funcionario.get());
        return ResponseEntity.status(HttpStatus.OK).body("Gerente removido");
      }
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Gerente com colaboradores");
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nao encontrado");
  } 

  @PostMapping("/{cpf_gestor}/mensagem/enviar/{cpf_colaborador}")
  public ResponseEntity<Object> comunicadoSave(@PathVariable(name = "cpf_gestor") String cpf_gestor,
  @PathVariable(name = "cpf_colaborador") String cpf_colaborador,@Valid @RequestBody ComunicadoDTO comunicadoDTO){
    Optional<Gestor> geOptional = gestorRepository.findByCpf(cpf_gestor);
    if(geOptional.isPresent()){
      Optional<Colaborador> cOptional = colaboradorRepository.findByCpf(cpf_colaborador);
      if(cOptional.isPresent()){
        var gestor = geOptional.get();
        var comunicado = new Comunicado();
        var colaborador = cOptional.get();
        
        BeanUtils.copyProperties(comunicadoDTO, comunicado);

        gestor.setComunicados(comunicado);
        colaborador.setComunicados(comunicado);
        comunicado.setGestor(gestor);
        comunicado.setColaborador(colaborador);
        
        comunicadoRepository.save(comunicado);
        return ResponseEntity.status(HttpStatus.CREATED).body("Criado");
      }
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Colaborador não encontrado");
    }
    
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Gestor não reconhecido");
  }
}
