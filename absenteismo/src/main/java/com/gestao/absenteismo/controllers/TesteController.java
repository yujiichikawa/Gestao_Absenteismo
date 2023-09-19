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

import com.gestao.absenteismo.dtos.ColaboradorRecord;
import com.gestao.absenteismo.dtos.ComunicadoRecord;
import com.gestao.absenteismo.dtos.GestorRecord;
import com.gestao.absenteismo.models.Colaborador;
import com.gestao.absenteismo.models.Comunicado;
import com.gestao.absenteismo.models.Gestor;
import com.gestao.absenteismo.repositories.ColaboradorRepository;
import com.gestao.absenteismo.repositories.ComunicadoRepository;
import com.gestao.absenteismo.repositories.GestorRepository;

@RestController
@RequestMapping("/teste")
public class TesteController {
  @Autowired
  private GestorRepository gestorRepository;
  @Autowired
  private ComunicadoRepository comunicadoRepository;
  @Autowired
  private ColaboradorRepository colaboradorRepository;

  @GetMapping("/mensagem/all")
  public ResponseEntity<Object> comunicadoAll(){
    List<Comunicado> comunicados = comunicadoRepository.findAll();
    if(comunicados.isEmpty()) return ResponseEntity.status(HttpStatus.OK).body("Lista vazia");
    return ResponseEntity.status(HttpStatus.OK).body(comunicados);
  }

  @PostMapping("/gestor/{id_gestor}/mensagem/enviar/{id_colaborador}")
  public ResponseEntity<Object> comunicadoSave(@PathVariable(name = "id_gestor") Long id_gestor,
  @PathVariable(name = "id_colaborador") Long id_colaborador, @RequestBody ComunicadoRecord comunicadoRecord){
    Optional<Gestor> geOptional = gestorRepository.findById(id_gestor);
    if(geOptional.isPresent()){
      Optional<Colaborador> cOptional = colaboradorRepository.findById(id_colaborador);
      if(cOptional.isPresent()){
        var gestor = geOptional.get();
        var comunicado = new Comunicado();
        var colaborador = cOptional.get();
        
        BeanUtils.copyProperties(comunicadoRecord, comunicado);
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
  @PostMapping("/gestor/save")
  public ResponseEntity<Gestor> saveGestor(@RequestBody GestorRecord gestorRecord){
    var gestor = new Gestor();
    
    BeanUtils.copyProperties(gestorRecord, gestor);
    return ResponseEntity.status(HttpStatus.CREATED).body(gestorRepository.save(gestor));
  }

  @GetMapping("/gestor/all")
  public ResponseEntity<List<Gestor>> listall(){
    return ResponseEntity.status(HttpStatus.OK).body(gestorRepository.findAll());
  }

  @GetMapping("/gestor/{id}")
  public ResponseEntity<Object> listById(@PathVariable Long id){
    Optional<Gestor> fuOptional = gestorRepository.findById(id);
    if(fuOptional.isPresent()) {return ResponseEntity.status(HttpStatus.OK).body(fuOptional);}
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nao encontrado");
    
  }

  @PutMapping("/gestor/update/{id}")
  public ResponseEntity<Object> updateById(@PathVariable Long id,@RequestBody GestorRecord gestorRecord){
    Optional<Gestor> funcionario = gestorRepository.findById(id);
    if(funcionario.isEmpty()) {return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nao encontrado");}
    var funcionarioModel = funcionario.get();
    BeanUtils.copyProperties(gestorRecord, funcionarioModel);
    return ResponseEntity.status(HttpStatus.OK).body(gestorRepository.save(funcionarioModel));
  }

  @DeleteMapping("/gestor/delete/{id}")
  public ResponseEntity<Object> deleteGestor(@PathVariable Long id){
    Optional<Gestor> funcionario = gestorRepository.findById(id);
    if(funcionario.isPresent()) {
      gestorRepository.delete(funcionario.get());
      return ResponseEntity.status(HttpStatus.OK).body("Gerente removido");
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nao encontrado");
  } 

  @PostMapping("/gestor/{id}/colaborador/save")
  public ResponseEntity<Object> save_colaborador(@PathVariable Long id,   @RequestBody ColaboradorRecord colaboradorRecord){
    var gestor = gestorRepository.findById(id);
    if(gestor.isPresent()){
      var colaborador = new Colaborador();
      var gestorconvert = gestor.get();

      BeanUtils.copyProperties(colaboradorRecord, colaborador);

      gestorconvert.setColaboradores(colaborador);
      colaborador.setGestor(gestorconvert);

      return ResponseEntity.status(HttpStatus.CREATED).body(colaboradorRepository.save(colaborador));
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Gestor não existente");
  }



}
