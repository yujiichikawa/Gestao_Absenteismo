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

import com.gestao.absenteismo.dtos.FuncionarioRecord;
import com.gestao.absenteismo.models.Funcionario;
import com.gestao.absenteismo.repositories.FuncionarioRepository;

@RestController
@RequestMapping("/teste")
public class TesteController {
  @Autowired
  private FuncionarioRepository funcionarioRepository;

  @PostMapping("/gestor/save")
  public ResponseEntity<Funcionario> saveGestor(@RequestBody FuncionarioRecord funcionarioRecord){
    var funcionario = new Funcionario();
    BeanUtils.copyProperties(funcionarioRecord, funcionario);
    return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioRepository.save(funcionario));
  }

  @GetMapping("/gestor/all")
  public ResponseEntity<List<Funcionario>> listall(){
    return ResponseEntity.status(HttpStatus.OK).body(funcionarioRepository.findAll());
  }

  @GetMapping("/gestor/{id}")
  public ResponseEntity<Object> listById(@PathVariable Long id){
    Optional<Funcionario> fuOptional = funcionarioRepository.findById(id);
    if(fuOptional.isPresent()) {return ResponseEntity.status(HttpStatus.OK).body(fuOptional);}
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nao encontrado");
    
  }

  @PutMapping("/gestor/update/{id}")
  public ResponseEntity<Object> updateById(@PathVariable Long id,@RequestBody FuncionarioRecord funcionarioRecord){
    Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
    if(funcionario.isEmpty()) {return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nao encontrado");}
    var funcionarioModel = funcionario.get();
    BeanUtils.copyProperties(funcionarioRecord, funcionarioModel);
    return ResponseEntity.status(HttpStatus.OK).body(funcionarioRepository.save(funcionarioModel));
  }

  @DeleteMapping("/gestor/delete/{id}")
  public ResponseEntity<Object> deleteGestor(@PathVariable Long id){
    Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
    if(funcionario.isPresent()) {
      funcionarioRepository.delete(funcionario.get());
      return ResponseEntity.status(HttpStatus.OK).body("Gerente removido");
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nao encontrado");
  } 

}
