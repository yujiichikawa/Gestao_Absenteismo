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

import com.gestao.absenteismo.dtos.GestorRecord;
import com.gestao.absenteismo.models.Gestor;
import com.gestao.absenteismo.repositories.GestorRepository;

@RestController
@RequestMapping("/teste")
public class TesteController {
  @Autowired
  private GestorRepository gestorRepository;

  @PostMapping("/gestor/save")
  public ResponseEntity<Gestor> saveGestor(@RequestBody GestorRecord gestorRecord){
    var funcionario = new Gestor();
    BeanUtils.copyProperties(gestorRecord, funcionario);
    return ResponseEntity.status(HttpStatus.CREATED).body(gestorRepository.save(funcionario));
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

}
