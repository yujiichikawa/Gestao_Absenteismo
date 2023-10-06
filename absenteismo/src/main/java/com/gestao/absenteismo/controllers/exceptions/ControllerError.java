package com.gestao.absenteismo.controllers.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;

public class ControllerError {
  private Instant timestamp;
  private Integer status;
  private HttpStatus error;
  private String message;
  private String path;

  public ControllerError(){}
  public ControllerError(Integer status, HttpStatus error, String message, String path) {
    this.timestamp = Instant.now();
    this.status = status;
    this.error = error;
    this.message = message;
    this.path = path;
  }


  public Instant getTimestamp() {
    return timestamp;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public HttpStatus getError() {
    return error;
  }

  public void setError(HttpStatus error) {
    this.error = error;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  
}
